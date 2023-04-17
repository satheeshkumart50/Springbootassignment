package com.sweethome.payment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sweethome.payment.dao.PaymentServiceDao;
import com.sweethome.payment.entity.TransactionDetailsEntity;

/*
 * PaymentServiceImpl Class handles POST APIs from the controller class related 
 * to making Payment for the booked room
 */
@Service
public class PaymentServiceImpl implements PaymentService {
	
	private PaymentServiceDao paymentServiceDao;
	
	@Autowired
	public PaymentServiceImpl(PaymentServiceDao paymentServiceDao) {
		this.paymentServiceDao = paymentServiceDao;
	}

	@Override
	public TransactionDetailsEntity payForRoom(TransactionDetailsEntity theTransactionDetailsEntity) {
		return paymentServiceDao.save(theTransactionDetailsEntity);
	}

	@Override
	public TransactionDetailsEntity getTranscationBasedOnId(int transactionId) {
		return paymentServiceDao.findById(transactionId).get();
	}

}
