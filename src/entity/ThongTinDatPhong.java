package entity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import interfaces.Saveable;
import utils.Utils;

public class ThongTinDatPhong implements Saveable{
	private int guestId, numDays;
	private int roomId;
	private Date checkIn;
	private Status status;
	private int id;
	
	private static int currentID = 100;
	public static ArrayList<ThongTinDatPhong> listRoomOrder = null;
	
	public ThongTinDatPhong(int guestId, int numDays, int roomId, Date checkIn) {
		this.guestId = guestId;
		this.numDays = numDays;
		this.roomId = roomId;
		this.checkIn = checkIn;
		this.status = Status.PRE_CHECK_IN;
		this.id = ++currentID;
	}
	
	public ThongTinDatPhong(int id, int guestId, int numDays, int roomId, Date checkIn, String status) {
		this.id = id;
		this.guestId = guestId;
		this.numDays = numDays;
		this.roomId = roomId;
		this.checkIn = checkIn;
		this.status = Utils.convertStatus(status);
		currentID = id >= currentID ? id : currentID;
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

	public int getRoomId() {
		return roomId;
	}

	public void setRoomIds(int roomId) {
		this.roomId = roomId;
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
	
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getLine() {
		String checkInStr = Utils.convertDate(checkIn);
		return id+";"+guestId+";"+roomId+";"+numDays+";"+checkInStr+";"+status;
	}
	
	public Date getCheckoutDate() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(checkIn);
		cal.add(Calendar.DAY_OF_MONTH, numDays);
		return cal.getTime();
	}


	public static void nhap(int id) {
		System.out.println("cus: "+id);
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
		sc.nextLine();
		String checkIn = sc.nextLine();
		Date checkInDate = Utils.convertDate(checkIn);
		System.out.println("Nhap so ngay thue:");
		int numDay = sc.nextInt();
		
		ArrayList<Phong> availableRooms = Phong.getAvailableRooms(checkInDate, typeStr, numDay);
		Utils.inDanhSach(availableRooms , "Danh sach phong trong");
		System.out.println("Nhap so luong phong muon thue:");
		int numRoom = sc.nextInt();
		for (int i = 0; i < numRoom; i++) {
			System.out.println("Nhap ma phong:");
			int roomId = sc.nextInt();
			ThongTinDatPhong order = new ThongTinDatPhong(id, numDay, roomId, checkInDate);
			if(listRoomOrder==null) {
				listRoomOrder = new ArrayList<>();
			}
			listRoomOrder.add(order);
		};
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<ThongTinDatPhong> filterByDate(Date checkInDate, int numDay) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(checkInDate);
		Date checkOut = cal.getTime();
		ArrayList<ThongTinDatPhong> filtered = new ArrayList<>();
		if (listRoomOrder != null) {
			filtered = (ArrayList<ThongTinDatPhong>) listRoomOrder.clone();
			filtered.removeIf(o -> {
				boolean busy = o.getCheckoutDate().after(checkInDate) && o.checkIn.before(checkOut);
				return !busy;
			});
		}
		return filtered;
	}

	public static boolean contains(ArrayList<ThongTinDatPhong> ds2, Phong p) {
		for (ThongTinDatPhong record : ds2) {
			if (record.roomId == p.getId()) {
				return true;
			}
		}
		return false;
	}
	
	
	
	
	
	
}
