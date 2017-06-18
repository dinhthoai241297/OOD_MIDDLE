package pdt.ood.models;

public abstract class Authority {
	public boolean joinRoom(User u, Room r) {
		return r.addUser(u);
	}
	
	public boolean delRoom(User u, Room r) {
		return true;
	}
	
	public boolean createRoom(User u, String roomName, String password, String roomType) {
		return true;
	}
}
