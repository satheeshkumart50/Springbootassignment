package com.sweethome.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sweethome.payment.entity.TransactionDetailsEntity;
import com.sweethome.payment.service.PaymentService;

/*
 * BookingController Class handles POST APIs related 
 * to make payment for a booked room
 */
@RestController
@RequestMapping(value="/transaction")
public class PaymentController {

	private PaymentService paymentService;
	
	@Autowired
	public PaymentController(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	/**This method generates the transaction id and save the transaction details to payment database
	 * @param theTransactionDetailsEntity
	 * @return
	 */
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer>  bookRoom(@RequestBody TransactionDetailsEntity theTransactionDetailsEntity){		
		TransactionDetailsEntity  transactionDetailsEntity = paymentService.payForRoom(theTransactionDetailsEntity);
		return new ResponseEntity<Integer>(transactionDetailsEntity.getTransactionId() , HttpStatus.CREATED);	
	}

	/**This method returns the transaction details for the given transaction id
	 * @param theTransactionDetailsEntity
	 * @return
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<TransactionDetailsEntity> getUser(@PathVariable(name = "id")  int transactionId){
		TransactionDetailsEntity  transactionDetailsEntity = paymentService.getTranscationBasedOnId(transactionId);
		return new ResponseEntity<TransactionDetailsEntity>(transactionDetailsEntity, HttpStatus.OK);
	}

}
