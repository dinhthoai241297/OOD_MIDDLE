package pdt.ood.models;

import java.util.ArrayList;
import java.util.List;

public abstract class Room {
	protected String id;
	protected String name;
	protected String password;
	protected User creator;
	protected kickBehavior kickBe;
	protected List<User> listUser;
	protected String type;
	
	public Room(User creator, String name, String password) {
		this.creator = creator;
		this.name = name;
		this.password = password;
		listUser = new ArrayList<>();
		type = this.getClass().getSimpleName();
	}
	
	// cus
	
	public boolean kickUser(User kick, User beKick) {
		if (kickBe.kick(creator, kick, beKick)) {
			listUser.remove(beKick);
			return true;
		} else {
			return false;
		}
	}
	
	public abstract boolean checkWrite(User u);
	
	public abstract boolean checkFun(User u);
	
	public abstract boolean addUser(User u);

	public void leave(User user) {
		listUser.remove(user);
	}

	// geter - seter
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public kickBehavior getKickBe() {
		return kickBe;
	}

	public void setKickBe(kickBehavior kickBe) {
		this.kickBe = kickBe;
	}

	public List<User> getListUser() {
		return listUser;
	}

	public void setListUser(List<User> listUser) {
		this.listUser = listUser;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}
	
}
