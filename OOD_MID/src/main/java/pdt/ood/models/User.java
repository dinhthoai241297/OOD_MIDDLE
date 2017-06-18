package pdt.ood.models;

import javafx.stage.Stage;
import pdt.ood.views.MyScene;

public class User {
	private String name;
	private String username;
	private String password;
	private String id;
	private Authority authority;
	private int state;
	private Stage stage;
	private MyScene ui;
	
	private static int countUser = 0;
	
	public User(String name, String username, String password, Authority au) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.authority = au;
		this.id = "user" + countUser++;
	}
	
	// cus
	public Room createRoom(User owner, String name, String password, String type) {
		return Creator.createRoom(this, name, password, type);
	}
	
	public boolean kick(User bekick, Room room) {
		return room.kickUser(this, bekick);
	}

	public void leaveRoom(Room room) {
		room.leave(this);
	}
	
	public boolean joinRoom(Room r) {
		return authority.joinRoom(this, r);
	}
	
	// geter - seter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Authority getAuthority() {
		return authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public MyScene getUI() {
		return ui;
	}

	public void setUI(MyScene ui) {
		this.ui = ui;
	}

	// override
	public String toString() {
		return this.name;
	}
	
	public Stage getStage() {
		return stage;
	}

}
