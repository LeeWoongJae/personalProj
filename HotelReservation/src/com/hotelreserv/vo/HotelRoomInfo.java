package com.hotelreserv.vo;

public class HotelRoomInfo {

	private int roomNum; // 방번호
	private String roomState; // 방이 비어있는지 (반환되는 값을 문자로 받아서 CASE문으로 구분)
	private String roomMemInfo; // 묵고있는 회원이 누군지
	
	
	// Constructor
	public HotelRoomInfo() {}
	
	public HotelRoomInfo(int roomNum , String roomState, String roomMemInfo) {
		this.roomNum = roomNum;
		this.roomState = roomState;
		this.roomMemInfo = roomMemInfo;
		
	}
	
	
	
	// getter ,setter
	public int getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}
	public String getRoomState() {
		return roomState;
	}
	public void setRoomState(String roomState) {
		this.roomState = roomState;
	}
	public String getRoomMemInfo() {
		return roomMemInfo;
	}
	public void setRoomMemInfo(String roomMemInfo) {
		this.roomMemInfo = roomMemInfo;
	}
	
	// toString
	@Override
	public String toString() {
		return "HotelRoomInfo [roomNum=" + roomNum + ", roomState=" + roomState + ", roomMemInfo=" + roomMemInfo + "]";
	}
	
	
	
	
}
