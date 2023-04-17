package com.sweethome.payment.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sweethome.payment.entity.TransactionDetailsEntity;


public interface PaymentServiceDao extends JpaRepository<TransactionDetailsEntity, Integer>{

}
