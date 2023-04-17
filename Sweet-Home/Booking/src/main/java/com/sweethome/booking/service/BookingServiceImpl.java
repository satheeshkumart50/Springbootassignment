package com.sweethome.booking.service;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sweethome.booking.dao.BookingDao;
import com.sweethome.booking.entity.BookingInfoEntity;
import com.sweethome.booking.model.TransactionDetailsModel;

/*
 * BookingServiceImpl Class handles POST APIs from the controller class related 
 * to Booking a room, making Payment for the booked room and send notification message to kafka topic
 */
@Service
public class BookingServiceImpl implements BookingService {
	
	@Value("${paymentApp.url}")
	private String paymentAppUrl;
	@Value("${kafka.topic}")
	private String kafkaTopic;
	
	private BookingDao bookingDao;
	
	private RestTemplate restTemplate;
	
	private Producer<String, String> producer;
	
	
	@Autowired
	public BookingServiceImpl(BookingDao bookingDao, RestTemplate restTemplate, Producer<String, String> producer) {
		this.bookingDao = bookingDao;
		this.restTemplate = restTemplate;
		this.producer = producer;
	}

	/**
	 * This method is used to stores the BookingInfoEntity to Database
	 * @param theBookingInfoEntity
	 * @return bookingInfoEntity
	 */
	@Override
	public BookingInfoEntity bookRoom(BookingInfoEntity theBookingInfoEntity) {
		BookingInfoEntity bookingInfoEntity = bookingDao.save(theBookingInfoEntity);
		return bookingInfoEntity;
	}

	
	/**
	 * This method reaches out to the Payment services and retrieves the transaction id for the booked room
	 * @param theBookingInfoEntity
	 * @return bookingInfoEntity
	 */
	@Override
	public BookingInfoEntity payForRoom(TransactionDetailsModel transactionDetailsModel) {
		ResponseEntity<Integer> responseEntity = restTemplate.postForEntity(paymentAppUrl, transactionDetailsModel,
				Integer.class);
		Integer transcationId = responseEntity.getBody();
		BookingInfoEntity bookingInfoEntity = bookingDao.findById(transactionDetailsModel.getBookingId()).get();
		bookingInfoEntity.setTransactionId(transcationId);
		bookingDao.save(bookingInfoEntity);
		sendMessagetoNotificationService(bookingInfoEntity);
		return bookingInfoEntity;
	}

	/**
	 * This method sends the asynchronous notification message to Kafka topic
	 * @param theBookingInfoEntity
	 * @return 
	 */
	private void sendMessagetoNotificationService(BookingInfoEntity bookingInfo) {
		String message = "Booking confirmed for user with aadhaar number: " + bookingInfo.getAadharNumber() +    "    |    "  + "Here are the booking details:    " + bookingInfo.toString();
		producer.send(new ProducerRecord<String, String>(kafkaTopic, "booking", message));
	}

	/**
	 * This method finds the BookedRoom by using the bookingId and throws NoSuchElementException
	 * if the booking id is not found in database
	 * @param theBookingInfoEntity
	 * @return 
	 */
	@Override
	public BookingInfoEntity findBookingId(int bookingId) {
		return bookingDao.findById(bookingId).get();
	}

}
