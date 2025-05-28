package com.hotelreserv.service;

import java.util.List;

import com.hotelreserv.common.HotelDAO;
import com.hotelreserv.vo.HotelRoomInfo;

public class HotelServiceDAO implements HotelService {
	
	HotelDAO dao = new HotelDAO();

	@Override
	public List<HotelRoomInfo> roomList() {

		return dao.selectAllRoom();
	}

	
	
}
