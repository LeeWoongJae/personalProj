package com.hotelreserv.common;

import java.util.ArrayList;
import java.util.List;
import com.hotelreserv.vo.HotelRoomInfo;


public class HotelDAO extends DAO {

	List<HotelRoomInfo> list = new ArrayList<>();
	
	public List<HotelRoomInfo> selectAllRoom() {
		String sql = "SELECT room_num, "
				+ "CASE WHEN room_state=1 THEN 'O'"
				+ " ELSE 'x' END AS room_state, "
				+ "room_mem_info"
				+ " FROM hotel_room_info";
		getConnect();
		HotelRoomInfo searchAllRoom = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int roomNum = rs.getInt("room_num");
				int roomState = rs.getInt("room_state");
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
	
	
}
