package com.hotelreserv.common;

import java.util.ArrayList;
import java.util.List;

import com.hotelreserv.vo.BusInfo;
import com.hotelreserv.vo.BusReservation;

public class BusDAO extends DAO {

	public List<BusInfo> operBusAllList() {
		List<BusInfo> busList = new ArrayList<>();

		String sql = "SELECT * FROM bus_info";
		getConnect();
		BusInfo searchBusInfo = null;

		try {

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int busNo = rs.getInt("bus_no");
				String busGrd = rs.getString("bus_grade");
				String busOperStart = rs.getString("bus_oper_start");
				String busOperNorEnd = rs.getString("bus_oper_nor_end");
				String busOperVipEnd = rs.getString("bus_oper_vip_end");
				int busFlowChk = rs.getInt("bus_flow_chk");

				searchBusInfo = new BusInfo(busNo, busGrd, busOperStart, busOperNorEnd, busOperVipEnd, busFlowChk);
				busList.add(searchBusInfo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}

		return busList;
	} // end of operBusAllList
	
	public int busNumFlowChk(int busNo){
		// 버스 번호를 입력하면 예약리스트에 존재하는 지정버스의 예약 건수를 반환 
		
		String sql = "SELECT COUNT(*) "
				+ "FROM bus_reservation "
				+ "WHERE bus_no = ?";
		getConnect();
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, busNo);
			rs = pstmt.executeQuery();
			int cnt = rs.getInt(busNo);
			return cnt;
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
		
		return 0;
	}// end of busNumFlowChk
	

	public int insert(BusReservation reservation) {
		// 버스 예약 리스트 추가 메소드.
		String sql = "INSERT INTO bus_reservation(bus_reserv_no , bus_no , bus_reserv_member , bus_reserv_time)"//
				+ " VALUES(bus_rsv_seq.NEXTVAL,?,?,SYSTIMESTAMP)";

		getConnect();

		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reservation.getBusNo());
			pstmt.setString(2, reservation.getBusReservMem());

			int r = pstmt.executeUpdate();
			conn.commit();
			return r;

		} catch (Exception e) {
		} finally {
			disConnect();
		}
		return 0;

	}//
	
	public boolean busRsvDel(BusReservation reservation) {
		// 삭제할 버스번호 , 사용자이름 , 예약한 날짜(년도/달/일) >> 599 , user02 , 25/05/30
		String sql = "DELETE FROM bus_reservation "
				+ "WHERE bus_no = ? "
				+ "AND bus_reserv_member LIKE ? "
				+ "AND SUBSTR(bus_reserv_time,0,8) LIKE ?";
		getConnect();
		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reservation.getBusNo());
			pstmt.setString(2, reservation.getBusReservMem());
			pstmt.setString(3, reservation.getBusReservTime());
			pstmt.executeUpdate();
			return true;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
		return false;
	}// end of busReserveUpdate
	
	
	public List<BusReservation> selectRsvList(BusReservation reservation) {
		// 버스 예약 테이블에서 해당 버스를 예약한 리스트를 반환(1건이상)
		List<BusReservation> inst = new ArrayList<>();
		BusReservation busRsv=null;
		String sql = "SELECT * FROM bus_reservation "
				     + "WEHRE bus_no = ?";
		
		getConnect();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reservation.getBusNo());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int busNo = rs.getInt("bus_no");
				String reservMem = rs.getString("bus_reserv_member");
				String reservTime = rs.getString("bus_reserv_time");
				
				busRsv.setBusNo(busNo);
				busRsv.setBusReservMem(reservMem);
				busRsv.setBusReservTime(reservTime);
				busRsv = new BusReservation(busNo ,reservMem ,  reservTime);
				inst.add(busRsv);
			
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
		
		return inst;
	}
	public BusReservation selectOne(BusReservation reservation) {
		String sql = "SELECT * FROM bus_reservation "
				+ "WHERE bus_no = ?";
		BusReservation rsvInst = null;
		getConnect();
		try {
			// 쿼리한 처음 값을 가져옴
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reservation.getBusNo());
			rs = pstmt.executeQuery();
			int busNo = rs.getInt("bus_no");
			String busRsvMem = rs.getString("bus_reserv_member");
			String busRsvTime = rs.getString("bus_reserv_time");

			rsvInst = new BusReservation(busNo , busRsvMem , busRsvTime);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
		
		return rsvInst;
		
	}
	// NVL , CASE문을 사용해서 한번의 쿼리로 다양한 형태의 결과값을 리턴
	public List<BusReservation> rsvAllList(BusReservation reservation) {
		List<BusReservation> list = new ArrayList<>();
		String sql = "SELECT * FROM bus_reservation"
				+ "   where bus_no = nvl(?, bus_no)";
		getConnect();
		BusReservation rsvAllList = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(0, sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				int busNo = rs.getInt("bus_no");
				String busRsvMem = rs.getString("bus_reserv_member");
				String busRsvTime = rs.getString("bus_reserv_time");
				
				rsvAllList = new BusReservation (busNo, busRsvMem,  busRsvTime);
				list.add(rsvAllList);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
		return list;
	}
	

	

}
