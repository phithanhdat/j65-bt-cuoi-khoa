package entity;

import java.util.ArrayList;
import java.util.Date;

public class Phong {
	private int id;
	private String name, type;
	private int cost;
	
	private static int currentID = 100;
	
	public static ArrayList<Phong> listRoom = null;

	public Phong(int id, String name, String type, int cost) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.cost = cost;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
	
	@Override
	public String toString() {
		String out = String.format("[%-3d   %-10s %-10s %6d]", id, name, type, cost);
		return out;
	}

	public static ArrayList<Phong> getAvailableRooms(Date checkInDate, String type) {
		ArrayList<Phong> ds1 = filterByType(type);
		if (ds1 != null) {
			
		}
		return null;
	}
	
	public static ArrayList<Phong> filterByType(String type) {
		if (listRoom != null) {
			@SuppressWarnings("unchecked")
			ArrayList<Phong> listCopied = (ArrayList<Phong>) listRoom.clone();
			listCopied.removeIf(r ->  !r.getType().equals(type));
			return listCopied;
		}
		return null;
	}
	
	
}
