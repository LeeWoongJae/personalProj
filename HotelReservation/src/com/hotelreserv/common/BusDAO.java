package com.hotelreserv.common;


import java.util.ArrayList;
import java.util.List;
import com.hotelreserv.vo.BusInfo;
import com.hotelreserv.vo.BusReservation;


public class BusDAO extends DAO{
	
	public List<BusInfo> busList = new ArrayList<>();
	public List<BusInfo> operBusAllList(){
		
		String sql = "SELECT * FROM bus_info";
		BusInfo bus = null;
		getConnect();
		BusInfo searchBusInfo = null;
		try {
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
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
		}finally {
			disConnect();
		}
		
		
		return busList;
	} // end of operBusAllList
	
	public int busReserv(BusReservation bus) { 
		System.out.println(bus);
		String sql = "";
		int r = 0;
		sql = "SELECT * FROM bus_info WHERE bus_no="+bus.getBusNo();
		getConnect();
		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				if(rs.getInt("bus_flow_chk")<30) {
					sql = "INSERT INTO bus_reservation (bus_reserv_no , bus_no , bus_reserv_member , bus_reserv_time) "
							+ "VALUES (bus_rsv_seq.NEXTVAL,?,?,SYSDATE)";
					pstmt = conn.prepareStatement(sql);
					
					// 정원이 남아있을 경우 예약 실행
					pstmt.setInt(1, bus.getBusNo());
					pstmt.setString(2, bus.getBusReservMem());
					
					r = pstmt.executeUpdate();
					conn.commit();
					
					// 예약이 완료되면 버스 정원 카운트업
					sql = "UPDATE bus_info "
						  + "SET bus_flow_chk = ? "
						  + "WHERE bus_no = ?";
					
					// 정원이 남아있을 경우 예약 실행
					pstmt.setInt(1, rs.getInt("bus_flow_chk")+1);
					pstmt.setInt(2, bus.getBusNo());
					
					r = pstmt.executeUpdate();
					conn.commit();
					
				}else {
					System.out.println(" 😔 셔틀 버스의 정원이 초과되었습니다. 😔");
				}
				
			}
			
			return r;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {disConnect();}
		
		return 0;
	}// end of busReserv
	
	
}
