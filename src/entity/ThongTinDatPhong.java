package entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import utils.Utils;

public class ThongTinDatPhong {
	private int guestId, numDays;
	private int[] roomIds;
	private Date checkIn;
	private Status status;
	
	public ThongTinDatPhong(int guestId, int numDays, int[] roomIds, Date checkIn) {
		this.guestId = guestId;
		this.numDays = numDays;
		this.roomIds = roomIds;
		this.checkIn = checkIn;
		this.status = Status.PRE_CHECK_IN;
	}
	

	public int getGuestId() {
		return guestId;
	}

	public void setGuestId(int guestId) {
		this.guestId = guestId;
	}

	public int getNumDays() {
		return numDays;
	}

	public void setNumDays(int numDays) {
		this.numDays = numDays;
	}

	public int[] getRoomIds() {
		return roomIds;
	}

	public void setRoomIds(int[] roomIds) {
		this.roomIds = roomIds;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}


	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
	}


	public static ThongTinDatPhong nhap(int id) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Chon loai phong: ");
		System.out.println("1. Phong don");
		System.out.println("2. Phong doi");
		System.out.println("3. Phong VIP");
		int type = sc.nextInt();
		String typeStr = "";
		switch(type) {
		case 1: 
			typeStr = "P-DON";
			break;
		case 2:
			typeStr = "P-DOI";
			break;
		case 3:
			typeStr = "P-VIP";
			break;
		default:
			typeStr = "P-DON";	
		}
		System.out.println("Nhap thoi gian check in: ");
		String checkIn = sc.nextLine();
		Date checkInDate = Utils.convertDate(checkIn);
		ArrayList<Phong> availableRooms = Phong.getAvailableRooms(checkInDate);
		
		return null;
	}
	
	
	
	
	
	
}
