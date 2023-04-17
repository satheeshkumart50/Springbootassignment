package com.sweethome.booking.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sweethome.booking.entity.BookingInfoEntity;

public interface BookingDao extends JpaRepository<BookingInfoEntity, Integer>{

}
