package com.hotelreserv.service;

import java.util.List;

import com.hotelreserv.vo.HotelReservation;
import com.hotelreserv.vo.HotelRoomInfo;

public interface HotelService {
	public List<HotelRoomInfo> roomList();
	public int hotelRoomReserv(HotelReservation roomReserv);
	
	
}
