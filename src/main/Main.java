package main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;

import dataio.DataIO;
import entity.KhachHang;
import entity.Phong;
import entity.ThongTinDatPhong;
import interfaces.Saveable;

public class Main {
	private static String rootDir = "C:\\Users\\Admin\\Desktop\\J65";
	
	public static void main(String[] args) {
		DataIO.root = rootDir;
		
		ArrayList<Phong> listRoom = DataIO.loadPhong("phong.txt");
		ArrayList<KhachHang> listKH = DataIO.loadGuest("guest.txt");
		ArrayList<ThongTinDatPhong> listRoomOrder = DataIO.loadOrder("order.txt");
		KhachHang.listKH = listKH;
		Phong.listRoom = listRoom;
		ThongTinDatPhong.listRoomOrder = listRoomOrder;
		
		Scanner sc = new Scanner(System.in);
		int chon;
		do {
			printMenu();
			chon = sc.nextInt();
			switch (chon) {
			case 1: 
				inDanhSach(listRoom, "Danh sach phong");
			break;
			case 2:
				sortRooms(listRoom);
				inDanhSach(listRoom, "Danh sach phong theo gia tang dan");
			break;
			case 3:
				nhapThongTinDatPhong();
				break;
			
			}
		} while (chon != 0);
		
		// save data here
		DataIO.saveGuest("guest.txt", listKH);
		DataIO.saveOrder("order.txt", listRoomOrder);
	}

	private static void nhapThongTinDatPhong() {
		KhachHang kh = KhachHang.nhap();
		ThongTinDatPhong.nhap(kh.getId());
	}

	private static void sortRooms(ArrayList<Phong> listRoom) {
		Comparator<Phong> com = new Comparator<Phong>() {
			@Override
			public int compare(Phong o1, Phong o2) {
				return o1.getCost() > o2.getCost() ? 1 : -1;
			}
		};
		listRoom.sort(com);
	}

	private static void inDanhSach(ArrayList<Phong> listRoom, String title) {
		System.out.println("------------- "+ title + "---------------");
		for (Phong phong : listRoom) {
			System.out.println(phong);
		}
	}

	private static void printMenu() {
		System.out.println("\n1. In danh sach phong");
		System.out.println("2. In danh sach phong theo gia");
		System.out.println("3. Nhap thong tin dat phong");
		System.out.println("4. Tra cuu thong tin");
		System.out.println("5. Checkin - Checkout");
		System.out.println("6. Xuat hoa don");
		System.out.println("\n Moi chon:");
	}
}
