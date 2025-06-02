package com.hotelreserv.service;

import java.util.List;

import com.hotelreserv.common.HotelDAO;
import com.hotelreserv.vo.HotelReservation;
import com.hotelreserv.vo.HotelRoomInfo;

public class HotelServiceDAO implements HotelService {
	
	HotelDAO dao = new HotelDAO();

	@Override
	public List<HotelRoomInfo> roomList() {
		return dao.selectAllRoom();
	}

	@Override
	public int hotelRoomReserv(HotelReservation roomReserv) {
		return dao.roomRsv(roomReserv);
	}

	@Override
	public int hotelRoomReservCancle(HotelReservation cancleSelect) {
		return dao.cancleRsvRoom(cancleSelect);
	}

	@Override
	public HotelRoomInfo roomDetailInfo(int detailInfo) {
		
		return dao.selectDetailInfo(detailInfo);
	}

	
	
}
