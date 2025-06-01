package com.hotelreserv.vo;



public class BusInfo {

	private int busNum;
	private String busGrd;
	private String busOperStart;
	private String busOperEnd;
	private int busFlowChk;
	
	//Constructor
	public BusInfo() {}
	public BusInfo(int busNum, String busGrd, String busOperStart,//
			String busOperEnd, int busFlowChk) {
		
		this.busNum = busNum;
		this.busGrd = busGrd;
		this.busOperStart = busOperStart;
		this.busOperEnd = busOperEnd;
		this.busFlowChk = busFlowChk;
	}
	
	// getter , setter
	public int getBusNum() {
		return busNum;
	}
	public void setBusNum(int busNum) {
		this.busNum = busNum;
	}
	public String getBusGrd() {
		return busGrd;
	}
	public void setBusGrd(String busGrd) {
		this.busGrd = busGrd;
	}
	public String getBusOperStart() {
		return busOperStart;
	}
	public void setBusOperStart(String busOperStart) {
		this.busOperStart = busOperStart;
	}
	public String getBusOperEnd() {
		return busOperEnd;
	}
	public void setBusOperNorEnd(String busOperEnd) {
		this.busOperEnd = busOperEnd;
	}
	public int getBusFlowChk() {
		return busFlowChk;
	}
	public void setBusFlowChk(int busFlowChk) {
		this.busFlowChk = busFlowChk;
	}
	
	// toString
	@Override
	public String toString() {
		return "BusInfo [busNum=" + busNum + ", busGrd=" + busGrd + ", busOperStart=" + busOperStart
				+ ", busOperEnd="+busOperEnd + ", busFlowChk=" + busFlowChk + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
