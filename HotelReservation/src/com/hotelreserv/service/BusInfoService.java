package com.hotelreserv.service;

import java.util.List;

import com.hotelreserv.vo.BusInfo;
import com.hotelreserv.vo.BusReservation;

public interface BusInfoService {
	
	public List<BusInfo> selectAllBusList(); // 전체 셔틀버스 정보
	// public boolean busReserv(BusReservation bus); //
	public boolean insertReserve(BusReservation reservation); // insert.
	public boolean deleteReserve(BusReservation reservation); // update (버스예약삭제)
	public BusReservation selectOne(BusReservation reservation); // 단건조회.
	// (여러 사용자가 한가지 버스에 예약된다면 )
	//public List<BusReservation> selectList(BusReservation reservation); //예약목록.
	public List<BusReservation> reservAllList(BusReservation reservation);//예약목록.
	
	
	
	
	
}
