package com.hotelreserv.service;

import java.util.List;

import com.hotelreserv.common.BusDAO;
import com.hotelreserv.vo.BusInfo;
import com.hotelreserv.vo.BusReservation;

public class BusServiceDAO implements BusInfoService {
	
	BusDAO dao = new BusDAO(); 
	
	@Override
	public List<BusInfo> selectAllBusList() {
		return dao.operBusAllList();
	}

	@Override
	public int busReserv(BusReservation bus) {
		
		return dao.busReserv(bus);
	}
	
	
	

}
