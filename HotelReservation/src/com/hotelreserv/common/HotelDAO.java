package com.hotelreserv.common;

import java.util.ArrayList;
import java.util.List;

import com.hotelreserv.vo.HotelReservation;
import com.hotelreserv.vo.HotelRoomInfo;


public class HotelDAO extends DAO {

	List<HotelRoomInfo> list = new ArrayList<>();
	
	// 객실 전체 정보
	public List<HotelRoomInfo> selectAllRoom() {
		String sql = "SELECT room_num, "
				+ "(CASE WHEN TO_CHAR(room_state) LIKE '1' THEN 'O'"
				+ " ELSE 'X' END) room_state, "
				+ "room_mem_info"
				+ " FROM hotel_room_info";
		getConnect();
		HotelRoomInfo searchAllRoom = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int roomNum = rs.getInt("room_num");
				String roomState = rs.getString("room_state");
				String roomMemInfo = rs.getString("room_mem_info");
				
				searchAllRoom = new HotelRoomInfo(roomNum , roomState , roomMemInfo);
				list.add(searchAllRoom);
			}
		} catch (Exception e) {}
		finally {
			disConnect();
		}
		
		return list;
		
	}
	
	// 예약
	public int roomRsv(HotelReservation roomselect) {
		String sql = "SELECT * FROM hotel_room_info";
			
		getConnect();
		int r=0;
		try {
			conn.setAutoCommit(false); 
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				if(rs.getString("room_state").equals("1")) {
					
					
					// 예약 작성
					sql = "INSERT INTO hotel_reservation "+
							"VALUES (hotel_rsv_seq.NEXTVAL, ? , ? , TO_DATE(?,'YYYY-MM-DD') ,TO_DATE(?,'YYYY-MM-DD')+?)";
					
					pstmt = conn.prepareStatement(sql);
					
					pstmt.setString(1, roomselect.getReservMem());
					pstmt.setInt(2, roomselect.getReservRoomNo());
					pstmt.setString(3, roomselect.getReservRoomStart());
					pstmt.setString(4, roomselect.getReservRoomStart());
					pstmt.setInt(5, roomselect.getReservRoomEnd());
					
					r = pstmt.executeUpdate();
					conn.commit();
					
					
					// 예약 완료시 객실 상태 변경 
					sql = "UPDATE hotel_room_info "
							+"SET room_state = ? "
							+"WHERE room_num = ?";
					
					pstmt = conn.prepareStatement(sql);
					
					pstmt.setInt(1, 0);
					pstmt.setInt(2, roomselect.getReservRoomNo());
					
					
					r = pstmt.executeUpdate();
					conn.commit();
					
					
				}// end of inner if
				else {
					pstmt.close();
					System.out.println("죄송합니다 이용중인 객실입니다\n확인해주시고 선택해주세요.");
				}
			}
			return r;
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
		
		
		return 0;
				
	}
	
	
	
}
