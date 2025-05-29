package com.hotelreserv.vo;



public class BusInfo {

	private int busNum;
	private String busGrd;
	private String busOperStart;
	private String busOperNorEnd;
	private String busOperVipEnd;
	private int busFlowChk;
	
	//Constructor
	public BusInfo() {}
	public BusInfo(int busNum, String busGrd, String busOperStart,//
			String busOperNorEnd, String busOperVipEnd, int busFlowChk) {
		
		this.busNum = busNum;
		this.busGrd = busGrd;
		this.busOperStart = busOperStart;
		this.busOperNorEnd = busOperNorEnd;
		this.busOperVipEnd = busOperVipEnd;
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
	public String getBusOperNorEnd() {
		return busOperNorEnd;
	}
	public void setBusOperNorEnd(String busOperNorEnd) {
		this.busOperNorEnd = busOperNorEnd;
	}
	public String getBusOperVipEnd() {
		return busOperVipEnd;
	}
	public void setBusOperVipEnd(String busOperVipEnd) {
		this.busOperVipEnd = busOperVipEnd;
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
				+ ", busOperNorEnd=" + busOperNorEnd + ", busOperVipEnd=" + busOperVipEnd + ", busFlowChk=" + busFlowChk
				+ "]";
	}
	
	
	
	
	
	
	
	
	
	
}
