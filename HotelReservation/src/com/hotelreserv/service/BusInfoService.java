package com.hotelreserv.service;

import java.util.List;

import com.hotelreserv.vo.BusInfo;
import com.hotelreserv.vo.BusReservation;

public interface BusInfoService {
	
	public List<BusInfo> selectAllBusList(); // 전체 셔틀버스 정보
	public int busReserv(BusReservation bus);
	
	
	
}
