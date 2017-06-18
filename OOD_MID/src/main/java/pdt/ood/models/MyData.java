package pdt.ood.models;

import java.util.ArrayList;
import java.util.List;

public class MyData {
	private List<User> listUser;
	private List<Room> listRoom;
	
	public MyData() {
		this.listUser = new ArrayList<>();
		User u = new User("Phạm Đình Thoại", "admin", "admin", new AAdmin());
		this.listUser.add(u);
		this.listRoom = new ArrayList<>();
		this.listRoom.add(new ClassRoom(u, "Phòng học", "123"));
	}

	// cus
	
	public User checkValid(String username, String password) {
		for (User u : listUser) {
			if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
				return u;
			}
		}
		return null;
	}

	public void addUser(User u) {
		listUser.add(u);
	}
	
	public void addRoom(Room r) {
		listRoom.add(r);
	}

	public boolean checkExited(String username) {
		for (User u : listUser) {
			if (u.getUsername().equals(username)) {
				return true;
			}
		}
		return false;
	}
	
	// geter - seter
	
	public List<User> getListUser() {
		return this.listUser;
	}

	public void setListUser(List<User> listUser) {
		this.listUser = listUser;
	}

	public List<Room> getListRoom() {
		return this.listRoom;
	}

	public void setListRoom(List<Room> listRoom) {
		this.listRoom = listRoom;
	}
	
}
