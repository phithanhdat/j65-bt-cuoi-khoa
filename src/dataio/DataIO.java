package dataio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import entity.DataTemplate;
import entity.KhachHang;
import entity.Phong;
import entity.Status;
import entity.ThongTinDatPhong;
import interfaces.Saveable;
import utils.Utils;

public class DataIO {
	public static String root = "D:\\data"; 
	
	public static <T> ArrayList<T> loadData(String fileName, DataTemplate<T> dataTemplate) {
		File file = new File(root + File.separator + fileName);
		
		FileReader frd = null;
		BufferedReader bufR = null;
		ArrayList<T> list = new ArrayList<>(); 
		
		try {
			frd = new FileReader(file);
			bufR = new  BufferedReader(frd);
			String line;
			while((line = bufR.readLine()) != null) {
				if (line.startsWith("#")) continue;
				T room = dataTemplate.retrieve(line);
				list.add(room);
			}
		} catch (IOException e) {
			// e.printStackTrace();
			System.out.println("--> no file: "+fileName);
		} finally {
			try {
				if (bufR != null) bufR.close();
			}catch (Exception e) {}
		}
		return list;
	}

	public static ArrayList<Phong> loadPhong(String fileName) {
		ArrayList<Phong> listRoom = loadData(fileName, s -> {
			String[] parts = s.split(";");
			int id = Integer.parseInt(parts[0]);
			String name = parts[1];
			String type = parts[2];
			int cost = Integer.parseInt(parts[3]);
			return new Phong(id, name, type, cost);
		});
		return listRoom;
	}
	


	public static ArrayList<KhachHang> loadGuest(String fileName) {
		ArrayList<KhachHang> listCustomer = loadData(fileName, s -> {
			String[] parts = s.split(";");
			int id = Integer.parseInt(parts[0]);
			String name = parts[1];
			String idCard = parts[2];
			String phone = parts[3];
			return new KhachHang(id, name, idCard, phone);
		});
		return listCustomer;
	}

	public static ArrayList<ThongTinDatPhong> loadOrder(String fileName) {
		ArrayList<ThongTinDatPhong> listOrder = loadData(fileName, s -> {
			String[] parts = s.split(";");
			int id = Integer.parseInt(parts[0]);
			int customerId = Integer.parseInt(parts[1]);
			int roomId = Integer.parseInt(parts[2]);
			int numDay = Integer.parseInt(parts[3]);
			Date checkIn = Utils.convertDate(parts[4]);
			return new ThongTinDatPhong(id, customerId, numDay, roomId, checkIn, parts[5]);
		});
		return listOrder;
	}

	public static void saveGuest(String fileName, ArrayList<KhachHang> listCustomer) {
		ArrayList<Saveable> converted = new ArrayList<>();
		for (Saveable item : listCustomer) {
			converted.add(item);
		}
		saveData(fileName, converted);
	}
	
	public static void saveOrder(String fileName, ArrayList<ThongTinDatPhong> listOrder) {
		ArrayList<Saveable> converted = new ArrayList<>();
		for (Saveable item : listOrder) {
			converted.add(item);
		}
		saveData(fileName, converted);
	}
	
	private static void saveData(String fileName, ArrayList<Saveable> list) {
		File file = new File(root + File.separator + fileName);
		FileWriter fwt = null;
		BufferedWriter bufW = null;
		try {
			fwt = new FileWriter(file);
			bufW = new BufferedWriter(fwt);
			for (Saveable t : list) {
				bufW.write(t.getLine());
				bufW.newLine();
			}
		}catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufW!=null) bufW.close();
			} catch (Exception e) {}
		}
	}
}
