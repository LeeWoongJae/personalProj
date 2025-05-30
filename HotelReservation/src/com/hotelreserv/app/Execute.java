package com.hotelreserv.app;

import java.util.List;
import java.util.Scanner;
import com.hotelreserv.service.BusInfoService;
import com.hotelreserv.service.BusServiceDAO;
import com.hotelreserv.service.HotelService;
import com.hotelreserv.service.HotelServiceDAO;
import com.hotelreserv.service.MemberService;
import com.hotelreserv.service.MemberServiceDAO;
import com.hotelreserv.vo.BusInfo;
import com.hotelreserv.vo.BusReservation;
import com.hotelreserv.vo.HotelReservation;
import com.hotelreserv.vo.HotelRoomInfo;
import com.hotelreserv.vo.Member;

public class Execute {

	BusInfoService bSvc = new BusServiceDAO();
	HotelService hSvc = new HotelServiceDAO();

	public void roomStatChk() {
		List<HotelRoomInfo> roomList = hSvc.roomList();
		int space = 0;
		// List<HotelRoomInfo> hSlist = hSvc.roomList();
		// System.out.println(hSlist);
		System.out.println("----+----+----+----+----+----+----+----+----+----+");
		for (int i = 0; i < roomList.size(); i++) {
			space++;
			System.out.print(roomList.get(i).getRoomNum() + " " + roomList.get(i).getRoomState() + " ");
			if (space % 7 == 0) {
				if (roomList.get(i).getRoomNum() / 9000 == 1) {
					System.out.print("3F");
				}
				if (roomList.get(i).getRoomNum() / 2000 == 1) {
					System.out.print("2F");
				}
				if (roomList.get(i).getRoomNum() / 1000 == 1) {
					System.out.print("1F");
				}
				System.out.println("\n");

			}
			if (space % 14 == 0) {
				System.out.println("----+----+----+----+----+----+----+----+----+----+");
			}
		}
		System.out.println("----+----+----+----+----+----+----+----+----+----+");
	}

	public void operationInfo() {
		List<BusInfo> busList = bSvc.selectAllBusList();

		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("버스등급        버스번호      운행시작      운행종료  수용인원");
		for (int i = 0; i < busList.size(); i++) {

			System.out.print("[" + busList.get(i).getBusGrd() + "]     [" + busList.get(i).getBusNum() + "]" + //
					"     [" + busList.get(i).getBusOperStart() + "]  ~  [");

			if (busList.get(i).getBusGrd().equals("normal")) {
				System.out.print(busList.get(i).getBusOperNorEnd() + "]");
			} else {
				System.out.print(busList.get(i).getBusOperVipEnd() + "]");
			}

			System.out.println("    " + busList.get(i).getBusFlowChk());

		}
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	}

	public void hotelReserve() {

		System.out.println("😀 예약을 시작합니다.\n1.호텔 객실예약 2. 셔틀버스 예약\n원하는 메뉴를 입력해주세요.");
		String selectRsv = scn.nextLine();
		switch (selectRsv) {
		case "1": // 호텔 예약

			HotelReservation roomselect = new HotelReservation();

			System.out.println("😀 호텔 예약을 시작합니다.\n예약자 아이디를 입력해주세요.");
			String rsvUserId = scn.nextLine();

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
				System.out.println(" 😀 호텔 예약이 정상처리 되었습니다. 감사합니다! 😀");
			} else {
				System.out.println(" 😔 호텔 예약이 처리되지 못했습니다. 확인해주세요! 😔");
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
				System.out.println(" 😀 버스 예약이 정상처리 되었습니다. 감사합니다! 😀");
			} else {
				System.out.println(" 😔 버스 예약이 처리되지 못했습니다. 확인해주세요! 😔");
			}

			break;
		}
	}

	Scanner scn = new Scanner(System.in);

	// 메인메소드.
	public void execute() {

		// 실제로 실행될 메인 페이지
		// MemberDAO dao = new MemberDAO();
		// HotelDAO hDao = new HotelDAO();
		MemberControl mcontrol = new MemberControl();

		boolean run = true;

		while (run) {
			System.out.println("━━━━━━━━━━━━━━[ Welcome to YEDAM HOTEL ]━━━━━━━━━━━━━━━━");
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
				roomStatChk();
				break;

			case "3": // 운행정보
				operationInfo();
				break;

			case "4": // 호텔 및 셔틀버스 예약
				hotelReserve();
				break;

			case "5": // 종료
				run = false;
				System.out.println("😊 이용해 주셔서 감사합니다. 😊");
				break;
			}
		}
	} // end of execute.

}
