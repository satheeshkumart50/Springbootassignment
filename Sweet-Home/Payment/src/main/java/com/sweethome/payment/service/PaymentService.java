package com.sweethome.payment.service;

import com.sweethome.payment.entity.TransactionDetailsEntity;

public interface PaymentService {

	TransactionDetailsEntity payForRoom(TransactionDetailsEntity theTransactionDetailsEntity);

	TransactionDetailsEntity getTranscationBasedOnId(int transcationId);

}
