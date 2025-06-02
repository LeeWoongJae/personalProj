package com.hotelreserv.app;


import java.util.List;
import java.util.Scanner;

import com.hotelreserv.service.BusInfoService;
import com.hotelreserv.service.BusServiceDAO;
import com.hotelreserv.service.HotelService;
import com.hotelreserv.service.HotelServiceDAO;
import com.hotelreserv.vo.BusInfo;
import com.hotelreserv.vo.BusReservation;
import com.hotelreserv.vo.HotelReservation;
import com.hotelreserv.vo.HotelRoomInfo;


public class Execute {
	
	BusInfoService bSvc = new BusServiceDAO();
	HotelService hSvc = new HotelServiceDAO();

	public void roomStatChk() {
		List<HotelRoomInfo> roomList = null;
		roomList = hSvc.roomList();
		
		// List<HotelRoomInfo> hSlist = hSvc.roomList();
		// System.out.println(hSlist);
		System.out.println("----+----+----+----+----+----+----+----+----+----+\n");
		for (int i = 0; i < roomList.size(); i++) {
			
			System.out.print(roomList.get(i).getRoomNum() + " " + roomList.get(i).getRoomState() + " ");
			
			if ((i+1) % 7 == 0) {
				if (roomList.get(i).getRoomNum() / 9000 == 1) {
					System.out.print("[ VIP ]");
				}
				if (roomList.get(i).getRoomNum() / 3000 == 1 && roomList.get(i).getRoomNum() >= 3000) {
					System.out.print("[ 3F ]");
				}
				if (roomList.get(i).getRoomNum() / 2000 == 1 && roomList.get(i).getRoomNum() < 3000) {
					System.out.print("[ 2F ]");
				}
				if (roomList.get(i).getRoomNum() / 1000 == 1) {
					System.out.print("[ 1F ]");
				}
				System.out.println("\n");

			}
			if ((i+1) % 14 == 0) {
				System.out.println("----+----+----+----+----+----+----+----+----+----+\n");
			}
		}
		roomList.clear(); // DAO에서 add 로 리스트가 작성되면서 적재되는 타입이라 결과를 뿌리고 마지막에 이전 결과값을 초기화
		System.out.println("----+----+----+----+----+----+----+----+----+----+");
		
	}//

	public void operationInfo() {
		List<BusInfo> busList = bSvc.selectAllBusList();

		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("버스등급        버스번호      운행시작      운행종료  수용인원");
		for (int i = 0; i < busList.size(); i++) {

			System.out.print("[" + busList.get(i).getBusGrd() + "]     [" + busList.get(i).getBusNum() + "]" + //
					"     [" + busList.get(i).getBusOperStart() + "]  ~  [");

			if (busList.get(i).getBusGrd().equals("classic")) {
				System.out.print(busList.get(i).getBusOperEnd() + "]");
			} else {
				System.out.print(busList.get(i).getBusOperEnd() + "]");
			}

			System.out.println("    " + busList.get(i).getBusFlowChk());
		}
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	}//
	public void hotelRsvCancle() {
		
		System.out.println("😀 예약을 취소합니다.\n취소하실 메뉴를 입력해주세요.\n1. 호텔 2. 셔틀버스");
		String selectRsvCancle = scn.nextLine();
		switch (selectRsvCancle) {
		case "1": // 호텔 예약 취소
			roomStatChk();
			HotelReservation roomselect = new HotelReservation();

			System.out.println("😀 호텔 취소를 시작합니다.\n예약자 아이디를 입력해주세요.");
			String rsvUserId = scn.nextLine();
			
			if(rsvUserId =="") 
			{
				System.err.println("취소하실 아이디를 입력하셔야 예약이 가능합니다.\n확인해 주시고 다시 시도해주세요.😉");
				return;
			}
			System.out.println("취소하실 방 번호를 입력해주세요.");
			int rsvRoomNo = Integer.parseInt(scn.nextLine());

			System.out.println("취소하실 예약 날짜를 입력해주세요 (예 : 2026-01-01)");
			String rsvStart = scn.nextLine();

			roomselect.setReservMem(rsvUserId);
			roomselect.setReservRoomNo(rsvRoomNo);
			roomselect.setReservRoomStart(rsvStart);
			
			if (hSvc.hotelRoomReservCancle(roomselect) == 1) {
				System.err.println(" 😀 예약 취소가 정상처리 되었습니다. 감사합니다! 😀");
			} else {
				System.err.println(" 😔 예약 취소가 처리되지 못했습니다. 확인해주세요! 😔");
			}
			break;

		case "2": // 셔틀버스 예약 취소
			operationInfo(); // 버스 운행 정보 출력
			BusReservation busResrvSelect = new BusReservation();
			System.out.println("😀 버스 예약 취소를 시작합니다.\n취소하실 버스 번호를 입력해주세요.");
			int busRsvNo = Integer.parseInt(scn.nextLine());

			System.out.println("취소하실 예약자 아이디를 입력해주세요.");
			String busRsvMemId = scn.nextLine();

			busResrvSelect.setBusNo(busRsvNo);
			busResrvSelect.setBusReservMem(busRsvMemId);

			if (bSvc.deleteReserve(busResrvSelect)) {
				System.err.println(" 😀 버스 예약취소가 정상처리 되었습니다. 감사합니다! 😀");
			} else {
				System.err.println(" 😔 버스 예약취소가 처리되지 못했습니다. 확인해주세요! 😔");
			}

			break;
		}// end of switch
		
	}// end of hotelRsvCancle
	
	public void hotelReserve() {

		System.out.println("😀 예약을 시작합니다.\n1. 호텔 객실예약 2. 셔틀버스 예약\n원하는 메뉴를 입력해주세요.");
		String selectRsv = scn.nextLine();
		switch (selectRsv) {
		case "1": // 호텔 예약
			
			roomStatChk();
			HotelReservation roomselect = new HotelReservation();

			System.out.println("😀 호텔 예약을 시작합니다.\n예약자 아이디를 입력해주세요.");
			String rsvUserId = scn.nextLine();
			
			if(rsvUserId =="") 
			{
				System.err.println("예약하실 아이디를 입력하셔야 예약이 가능합니다.\n확인해 주시고 다시 시도해주세요.😉");
				return;
			}
			System.out.println("예약하실 방 번호를 입력해주세요.");
			int rsvRoomNo = Integer.parseInt(scn.nextLine());

			System.out.println("예약하실 날짜를 입력해주세요 (예 : 2026-01-01)");
			String rsvStart = scn.nextLine();

			System.out.println("숙박 기간을 입력해주세요 (예 : 3일 > 3)");
			int rsvEnd = Integer.parseInt(scn.nextLine());

			roomselect.setReservMem(rsvUserId);
			roomselect.setReservRoomNo(rsvRoomNo);
			roomselect.setReservRoomStart(rsvStart);
			roomselect.setReservRoomEnd(rsvEnd);

			if (hSvc.hotelRoomReserv(roomselect) == 1) {
				System.err.println(" 😀 호텔 예약이 정상처리 되었습니다. 감사합니다! 😀");
			} else {
				System.err.println(" 😔 호텔 예약이 처리되지 못했습니다. 확인해주세요! 😔");
			}
			break;

		case "2":
			operationInfo(); // 버스 운행 정보 출력
			BusReservation busResrvSelect = new BusReservation();
			System.out.println("😀 버스 예약을 시작합니다.\n예약하실 버스 번호를 입력해주세요.");
			int busRsvNo = Integer.parseInt(scn.nextLine());

			System.out.println("예약자 아이디를 입력해주세요.");
			String busRsvMemId = scn.nextLine();

			busResrvSelect.setBusNo(busRsvNo);
			busResrvSelect.setBusReservMem(busRsvMemId);

			if (bSvc.insertReserve(busResrvSelect)) {
				System.err.println(" 😀 버스 예약이 정상처리 되었습니다. 감사합니다! 😀");
			} else {
				System.err.println(" 😔 버스 예약이 처리되지 못했습니다. 확인해주세요! 😔");
			}
			break;
		}// end of switch
	}// end of hotelReserve

	Scanner scn = new Scanner(System.in);
	// 메인메소드.
	public void execute() {
		// 실제로 실행될 메인 페이지
		// MemberDAO dao = new MemberDAO();
		// HotelDAO hDao = new HotelDAO();
		MemberControl mcontrol = new MemberControl();
		boolean run = true;
		while (run) {
			System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("｜ 1. 회원  ┃ 2. 객실현황  ┃ 3. 운행버스정보  ┃ 4. 예약  ┃ 5.종료 ｜");
			System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			System.out.println("실행하실 메뉴를 입력 >");
			String menu = scn.nextLine();
			switch (menu) {
			case "1": // 회원관리
				mcontrol.execute();
				break;

			case "2": // 객실현황
				HotelService roomDetailInfo = new HotelServiceDAO();
				
				roomStatChk();
				System.out.println("자세히 알고 싶은 객실 번호를 입력해주세요.");
				int selectRoomNum = Integer.parseInt(scn.nextLine());
				
				HotelRoomInfo detail = roomDetailInfo.roomDetailInfo(selectRoomNum);
				System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
				System.out.println("｜  번호 ┃ 상태 ┃  아이디  ┃        시작일         ┃       종료일      ｜");
				System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
				System.out.println("  "+detail.getRoomNum()+" ┃ "+detail.getRoomState()+" ┃ "+ //
						detail.getRoomMemInfo()+" ┃ "+detail.getRoomRsvStart()+" ┃ "+ //
						detail.getRoomRsvEnd()); 
				break;

			case "3": // 운행정보
				operationInfo();
				break;

			case "4": // 호텔 및 셔틀버스 예약
				System.out.println("┏━━━━━━━━━━━━━━━━━━━━━┓");
				System.out.println("｜ 1. 예약  ┃ 2. 예약취소  ｜");
				System.out.println("┗━━━━━━━━━━━━━━━━━━━━━┛");
				System.out.println("실행하실 메뉴를 입력 >");
				String inrmenu = scn.nextLine();
				switch(inrmenu) {
				case "1": // 호텔 , 버스 예약
					hotelReserve();
					break;
				case "2": // 호텔 , 버스 예약취소
					hotelRsvCancle();
					break;
				}
				break;
			case "5": // 종료
				
				String endMsg = "3.. 2.. 1.. Goodbye";
				 for (char c : endMsg.toCharArray()) {
				try {
					System.out.print(c);
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}// end of for
				run = false;
				System.err.println("\n😊 이용해 주셔서 감사합니다. 😊\n😊 프로그램을 종료합니다.   😊");
				break;
			}
		}
	} // end of execute.

}
