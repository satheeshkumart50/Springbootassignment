package com.sweethome.booking.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="booking")
@JsonPropertyOrder({ "id", "fromDate", "toDate", "aadharNumber", "roomNumbers", "roomPrice" , "transactionId", "bookedOn" })
public class BookingInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="bookingId")
    @JsonProperty("id")
    private int id ;

    @Column(name="fromDate",nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fromDate;
    
    @Column(name="toDate",nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date toDate;

    @Column(name="aadharNumber", nullable = true)
    private String aadharNumber ;
    
    @Column(name="numOfRooms")
    private int numOfRooms ;
    
    @Column(name="roomNumbers")
    private String roomNumbers ;
    
    @Column(name="roomPrice",nullable=false)
    private int roomPrice ;
    
    @Column(name="transactionId")
    private int transactionId ;

    @Column(name="bookedOn",nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date bookedOn;
    
   	public BookingInfoEntity() {
	}

	public BookingInfoEntity(int id, Date fromDate, Date toDate, String aadharNumber, int numOfRooms, String roomNumbers,
			int roomPrice, int transactionId, Date bookedOn) {
		this.id = id;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.aadharNumber = aadharNumber;
		this.numOfRooms = numOfRooms;
		this.roomNumbers = roomNumbers;
		this.roomPrice = roomPrice;
		this.transactionId = transactionId;
		this.bookedOn = bookedOn;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	@JsonIgnore
	public int getNumOfRooms() {
		return numOfRooms;
	}
	
	@JsonSetter
	public void setNumOfRooms(int numOfRooms) {
		this.numOfRooms = numOfRooms;
	}

	public String getRoomNumbers() {
		return roomNumbers;
	}

	public void setRoomNumbers(String roomNumbers) {
		this.roomNumbers = roomNumbers;
	}

	public int getRoomPrice() {
		return roomPrice;
	}

	public void setRoomPrice(int roomPrice) {
		this.roomPrice = roomPrice;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public Date getBookedOn() {
		return bookedOn;
	}

	public void setBookedOn(Date bookedOn) {
		this.bookedOn = bookedOn;
	}

	@Override
	public String toString() {
		return "BookingInfoEntity [id=" + id + ", fromDate=" + fromDate + ", toDate=" + toDate + ", aadharNumber="
				+ aadharNumber + ", numOfRooms=" + numOfRooms + ", roomNumbers=" + roomNumbers + ", roomPrice="
				+ roomPrice + ", transactionId=" + transactionId + ", bookedOn=" + bookedOn + "]";
	}
}

