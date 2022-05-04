package dataio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import entity.DataTemplate;
import entity.KhachHang;
import entity.Phong;

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
			e.printStackTrace();
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
}
