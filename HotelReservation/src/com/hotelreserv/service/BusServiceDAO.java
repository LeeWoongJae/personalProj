package com.hotelreserv.service;

import java.util.List;

import com.hotelreserv.common.BusDAO;
import com.hotelreserv.vo.BusInfo;
import com.hotelreserv.vo.BusReservation;

public class BusServiceDAO implements BusInfoService {

	BusDAO dao = new BusDAO();
	BusInfo busInfo = new BusInfo();

	@Override
	public List<BusInfo> selectAllBusList() {
		// 버스 정보
		return dao.operBusAllList();
	}//

	@Override
	public boolean insertReserve(BusReservation reservation) {
		// 버스 예약
		int max = 30; // 탑승 정원값을 저장
		if(dao.busNumFlowChk(reservation.getBusNo()) < max) {
			// 버스 예약 리스트의 size 만큼만 정원에서 증감하면 된다
			// 입력한 버스번호를 받아오면 입력받은 버스의 예약자 수와 max값을 비교
			// 비교될 예약자수가 max값보다 적으면 예약진행
			dao.insert(reservation);
			return true;
			
		} // end of if
		return  false;
	}//

	@Override
	public boolean deleteReserve(BusReservation reservation) {
		return dao.busRsvDel(reservation);
	}//

	@Override
	public BusReservation selectOne(BusReservation reservation) {
		// 버스 번호를 입력하면 해당한 버스의 예약한 단건 반환 
		// (한건만 있으면 상관없는데 같은 버스번호로 다른 사용자가 예약한건까지 반환)
		
		return dao.selectOne(reservation);
	}//

	@Override
	public List<BusReservation> reservAllList(BusReservation reservation) {
		// 예약 목록 전체
		// 버스번호를 넣으면 해당 버스번호를 가진 예약리스트 반환
		// 멤버 아이디를 넣으면 해당 아이디로 넣은 예약 리스트 반환
		// list 반환 하게끔 만들어야~
		return dao.rsvAllList(reservation);
	}//

}// end of BusServiceDAO
