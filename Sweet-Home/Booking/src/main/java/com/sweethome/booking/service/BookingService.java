package com.sweethome.booking.service;

import com.sweethome.booking.entity.BookingInfoEntity;
import com.sweethome.booking.model.TransactionDetailsModel;

public interface BookingService {

	public BookingInfoEntity bookRoom(BookingInfoEntity theBookingInfoEntity);

	public BookingInfoEntity payForRoom(TransactionDetailsModel transactionDetailsModel);
	
	public BookingInfoEntity findBookingId(int bookingId);

}
