package com.sweethome.booking.model;

public class TransactionDetailsModel {
	
    private int transactionId ;
	
    private String paymentMode ;
	
    private int bookingId;
	
    private String upiId;
	
    private String cardNumber;
	
	public TransactionDetailsModel() {
	}

	public TransactionDetailsModel(int transactionId, String paymentMode, int bookingId, String upiId, String cardNumber) {
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
