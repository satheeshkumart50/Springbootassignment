package com.sweethome.booking.controller;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sweethome.booking.entity.BookingInfoEntity;
import com.sweethome.booking.exception.InValidPaymentException;
import com.sweethome.booking.model.ErrorModel;
import com.sweethome.booking.model.TransactionDetailsModel;
import com.sweethome.booking.service.BookingService;

/*
 * BookingController Class handles POST APIs related 
 * to Booking a room and making Payment for the booked room
 */
@RestController
@RequestMapping(value="/booking")
public class BookingController {

	private BookingService bookingService;
	private int roomPricePerDay = 1000;
	
	@Autowired
	public BookingController(BookingService bookingService) {
		this.bookingService = bookingService;
	}

	/**
	 * This method is used to book room and saves the details to booking table 
	 * @param theBookingInfoEntity
	 * @return ResponseEntity
	 */
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BookingInfoEntity>  bookRoom(@RequestBody BookingInfoEntity theBookingInfoEntity){	
		int noOfRooms = theBookingInfoEntity.getNumOfRooms();
		long noOfDays = ChronoUnit.DAYS.between(theBookingInfoEntity.getFromDate().toInstant(),theBookingInfoEntity.getToDate().toInstant());
		int roomPrice = noOfRooms*roomPricePerDay*(int)noOfDays;
		theBookingInfoEntity.setRoomPrice(roomPrice);

		String roomNumbers = "";
		ArrayList<String> alRoomNumbers = getRandomNumbers(noOfRooms);
		for (String roomNumber : alRoomNumbers) {
			roomNumbers = roomNumbers+","+roomNumber;
		}
		roomNumbers = roomNumbers.substring(1);
		theBookingInfoEntity.setRoomNumbers(roomNumbers);

		Date date = new Date(); 
		theBookingInfoEntity.setBookedOn(date);

		BookingInfoEntity  bookingInfoResponseEntity = bookingService.bookRoom(theBookingInfoEntity);
		return new ResponseEntity<BookingInfoEntity>(bookingInfoResponseEntity , HttpStatus.CREATED);

	}

	/**
	 * This method generates random numbers depending on the count input parameter 
	 * @param count
	 * @return ArrayList
	 */
	public static ArrayList<String> getRandomNumbers(int count){                
		Random rand = new Random();                
		int upperBound = 100;                
		ArrayList<String>numberList = new ArrayList<String>();                

		for (int i=0; i<count; i++){                
			numberList.add(String.valueOf(rand.nextInt(upperBound)));                
		}                

		return numberList;                
	}

	/**
	 * This method is used to make the payment for the reserved room by reaching out Payment service
	 * and send out a message to the kafka topic 
	 * @param transactionDetailsModel
	 * @return ResponseEntity
	 */
	@PostMapping(value="/{bookingId}/transaction",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BookingInfoEntity>  payForRoom(@RequestBody TransactionDetailsModel transactionDetailsModel){
		String paymentMode = transactionDetailsModel.getPaymentMode();
		if (!(paymentMode.equals("CARD") || paymentMode.equals("UPI"))) {
			throw new InValidPaymentException();
		}
		bookingService.findBookingId(transactionDetailsModel.getBookingId());
		BookingInfoEntity  bookingInfoResponseEntity = bookingService.payForRoom(transactionDetailsModel);
		return new ResponseEntity<BookingInfoEntity>(bookingInfoResponseEntity , HttpStatus.CREATED);	
	}

	/**
	 * This method handles exception when payment is made for the unbooked rooms 
	 * @param 
	 * @return ErrorModel
	 */
	@ExceptionHandler(NoSuchElementException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorModel handleRequestedResourceNotFoundException(){
		ErrorModel errorModel = new ErrorModel();
		errorModel.setMessage("Invalid Booking Id");
		errorModel.setStatusCode(HttpStatus.BAD_REQUEST.value());
		return errorModel;
	}

	/**
	 * This method handles exception when payment is made other than using CARD or UPI mode
	 * @param 
	 * @return ErrorModel
	 */
	@ExceptionHandler(InValidPaymentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorModel handleInValidPaymentException(){
		ErrorModel errorModel = new ErrorModel();
		errorModel.setMessage("Invalid mode of payment");
		errorModel.setStatusCode(HttpStatus.BAD_REQUEST.value());
		return errorModel;
	}
}
