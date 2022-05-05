package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import entity.Phong;
import entity.Status;

public class Utils {
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	public static Date convertDate(String dateStr){
		Date date;
		try {
			date = simpleDateFormat.parse(dateStr);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String convertDate(Date date) {
		String dateS = null;
		try {
			dateS = simpleDateFormat.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateS;
	}

	public static Status convertStatus(String status) {
		switch (status) {
		case "PRE_CHECK_IN":
			return Status.PRE_CHECK_IN;
		case "CHECKED_IN":
			return Status.CHECKED_IN;
		case "CHECKED_OUT":
			return Status.CHECKED_OUT;
		default:
			return Status.PRE_CHECK_IN;
		}
	}
	
	public static void inDanhSach(ArrayList<Phong> listRoom, String title) {
		System.out.println("------------- "+ title + "---------------");
		for (Phong phong : listRoom) {
			System.out.println(phong);
		}
	}
}
