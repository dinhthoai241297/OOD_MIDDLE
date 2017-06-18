package pdt.ood.models;

public class ChatRoom extends Room {
	
	private static int countChatRoom = 0;

	public ChatRoom(User creator, String name, String password) {
		super(creator, name, password);
		this.id = "ChatRoom" + countChatRoom++;
		kickBe = new KickByMem();
	}

	@Override
	public boolean checkWrite(User u) {
		return true;
	}

	@Override
	public boolean checkFun(User u) {
		return true;
	}

	@Override
	public boolean addUser(User u) {
		listUser.add(u);
		return true;
	}

}
