package entity;

import java.util.ArrayList;
import java.util.Scanner;

public class KhachHang {
	private int id;
	private String name, idCard, phone;
	
	private static int currentID = 100;
	public static ArrayList<KhachHang> listKH = new ArrayList<>();

	public KhachHang(String name, String idCard, String phone) {
		this.name = name;
		this.idCard = idCard;
		this.phone = phone;
		this.id = currentID++;
	}
	
	public KhachHang(int id, String name, String idCard, String phone) {
		this.name = name;
		this.idCard = idCard;
		this.phone = phone;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public static KhachHang nhap() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Nhap so can cuoc: ");
		String idCard = sc.nextLine();
		KhachHang found = findByIdCard(idCard);
		if (found != null) {
			System.out.println("Tim thay khach hang:");
			found.showInfo();
			return found;
		} else {
			System.out.println("Nhap so dien thoai: ");
			String phone = sc.nextLine();
			System.out.println("Nhap ten: ");
			String name = sc.nextLine();
			return new KhachHang(name, idCard, phone);
		}
	}
	
	private static KhachHang findByIdCard(String idCard) {
		if (listKH != null) {
			for (KhachHang kh : listKH) {
				if (kh.getIdCard().equals(idCard)) {
					return kh;
				}
			}
		}
		return null;
	}

	public void showInfo() {
		System.out.println("Ten: "+name);
		System.out.println("CCCD: "+idCard);
		System.out.println("SDT: "+phone);
	}
	
	
	
	
	
}
