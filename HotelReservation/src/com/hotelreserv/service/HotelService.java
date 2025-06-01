package com.hotelreserv.service;

import java.util.List;

import com.hotelreserv.vo.HotelReservation;
import com.hotelreserv.vo.HotelRoomInfo;

public interface HotelService {
	public List<HotelRoomInfo> roomList(); // 룸 전체 정보
	public int hotelRoomReserv(HotelReservation roomReserv); // 객실예약
	public int hotelRoomReservCancle(HotelReservation roomselect); // 객실예약취소
 }
