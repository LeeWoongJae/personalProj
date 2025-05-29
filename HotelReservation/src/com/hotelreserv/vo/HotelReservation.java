package com.hotelreserv.vo;

public class HotelReservation {
	private String reservNo;
	private String reservMem;
	private int reservRoomNo;
	private String reservRoomStart;
	private int reservRoomEnd;
	
	
	//Constructor
	public HotelReservation() {}
	public HotelReservation(String reservNo,String reservMem,
	int reservRoomNo,String reservRoomStart,int reservRoomEnd
) 
	{
		this.reservNo = reservNo;
		this.reservMem = reservMem;
		this.reservRoomNo = reservRoomNo;
		this.reservRoomStart = reservRoomStart;
		this.reservRoomEnd = reservRoomEnd;
		
	}
	
	
	// getter , setter
	public String getReservNo() {
		return reservNo;
	}
	public void setReservNo(String reservNo) {
		this.reservNo = reservNo;
	}
	public String getReservMem() {
		return reservMem;
	}
	public void setReservMem(String reservMem) {
		this.reservMem = reservMem;
	}
	public int getReservRoomNo() {
		return reservRoomNo;
	}
	public void setReservRoomNo(int reservRoomNo) {
		this.reservRoomNo = reservRoomNo;
	}
	public String getReservRoomStart() {
		return reservRoomStart;
	}
	public void setReservRoomStart(String reservRoomStart) {
		this.reservRoomStart = reservRoomStart;
	}
	public int getReservRoomEnd() {
		return reservRoomEnd;
	}
	public void setReservRoomEnd(int rsvEnd) {
		this.reservRoomEnd = rsvEnd;
	}
	
	
	// toString
	@Override
	public String toString() {
		return "HotelReservation [reservNo=" + reservNo + ", reservMem=" + reservMem + ", reservRoomNo=" + reservRoomNo
				+ ", reservRoomStart=" + reservRoomStart + ", reservRoomEnd=" + reservRoomEnd + "]";
	}
	
	
	
	
	
	
	
}
