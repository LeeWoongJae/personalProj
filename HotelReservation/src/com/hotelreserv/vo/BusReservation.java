package com.hotelreserv.vo;

public class BusReservation {

	private int busReservNo;
	private int busNo;
	private String busReservMem;
	private String busReservTime;
	
	//Constructor
	public BusReservation() {}
	public BusReservation(int busReservNo,int busNo,String busReservMem,
			             String busReservTime
			) 
	{
		this.busReservNo = busReservNo;
		this.busNo = busNo;
		this.busReservMem = busReservMem;
		this.busReservTime = busReservTime;
	}
	
	//getter , setter
	public int getBusReservNo() {
		return busReservNo;
	}
	public void setBusReservNo(int busReservNo) {
		this.busReservNo = busReservNo;
	}
	public int getBusNo() {
		return busNo;
	}
	public void setBusNo(int busNo) {
		this.busNo = busNo;
	}
	public String getBusReservMem() {
		return busReservMem;
	}
	public void setBusReservMem(String busReservMem) {
		this.busReservMem = busReservMem;
	}
	public String getBusReservTime() {
		return busReservTime;
	}
	public void setBusReservTime(String busReservTime) {
		this.busReservTime = busReservTime;
	}
	
	// toString
	@Override
	public String toString() {
		return "BusReservation [busReservNo=" + busReservNo + ", busNo=" + busNo + ", busReservMem=" + busReservMem
				+ ", busReservTime=" + busReservTime + "]";
	}
	
	
	
	
	
	
}
