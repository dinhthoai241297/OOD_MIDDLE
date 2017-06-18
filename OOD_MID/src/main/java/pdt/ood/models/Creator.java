package pdt.ood.models;

import pdt.ood.views.MyScene;

public class Creator {
	
	public static User createUser(String name, String username, String password, String au) {
		if ("Admin".equalsIgnoreCase(au)) {
			return new User(name, username, password, new AAdmin());
		} else if ("Lecturer".equalsIgnoreCase(au)) {
			return new User(name, username, password, new ALecturer());
		} else {
			return new User(name, username, password, new AStudent());
		}
	}

	public static Room createRoom(User user, String name, String passRoom, String type) {
		if (user.getAuthority() instanceof AAdmin) {
			return createRoomAll(user, name, passRoom, type);
		} else if (user.getAuthority() instanceof ALecturer) {
			return (type.equalsIgnoreCase(MYCONS.TYPE_BOARDROOM)) ? null : createRoomAll(user, name, passRoom, type);
		} else {
			return (type.equalsIgnoreCase(MYCONS.TYPE_CHATROOM)) ? createRoomAll(user, name, passRoom, type) : null;
		}
	}
	
	private static Room createRoomAll(User user, String name, String passRoom, String type) {
		switch (type) {
		case MYCONS.TYPE_CLASSROOM:
			return new ClassRoom(user, name, passRoom);
		case MYCONS.TYPE_CHATROOM:
			return new ChatRoom(user, name, passRoom);
		case MYCONS.TYPE_BOARDROOM:
			return new BoardRoom(user, name, passRoom);
		default:
			return null;
		}
	}
	
	public static void loadUI(User u) {
		if (u.getAuthority() instanceof AAdmin) {
			u.setUI(new MyScene(u, MYCONS.SCENE_ADMIN));
		} else {
			u.setUI(new MyScene(u, MYCONS.SCENE_NORMAL));
		}
	}

}
