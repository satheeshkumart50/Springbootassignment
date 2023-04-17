package com.sweethome.payment.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity
@Table(name="transaction")
@JsonPropertyOrder({ "id", "paymentMode", "bookingId", "upiId", "cardNumber"})
public class TransactionDetailsEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="transactionId")
    @JsonProperty("id")
    private int transactionId ;
	
	@Column(name="paymentMode")
    private String paymentMode ;
	
	@Column(name="bookingId", nullable = false)
    private int bookingId;
	
	@Column(name="upiId", nullable = true)
    private String upiId;
	
	@Column(name="cardNumber", nullable = true)
    private String cardNumber;
	
	public TransactionDetailsEntity() {
	}

	public TransactionDetailsEntity(int transactionId, String paymentMode, int bookingId, String upiId, String cardNumber) {
		this.transactionId = transactionId;
		this.paymentMode = paymentMode;
		this.bookingId = bookingId;
		this.upiId = upiId;
		this.cardNumber = cardNumber;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public String getUpiId() {
		return upiId;
	}

	public void setUpiId(String upiId) {
		this.upiId = upiId;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	@Override
	public String toString() {
		return "TransactionDetailsEntity [transactionId=" + transactionId + ", paymentMode=" + paymentMode
				+ ", bookingId=" + bookingId + ", upiId=" + upiId + ", cardNumber=" + cardNumber + "]";
	}
	
}
