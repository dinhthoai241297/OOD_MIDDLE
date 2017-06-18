package pdt.ood.models;

public class BoardRoom extends Room {
	
	private static int countBoardRoom = 0;

	public BoardRoom(User creator, String name, String password) {
		super(creator, name, password);
		this.id = "BoardRoom" + countBoardRoom++;
		kickBe = new KickByMem();
	}

	@Override
	public boolean checkWrite(User u) {
		return true;
	}

	@Override
	public boolean checkFun(User u) {
		return (u.getAuthority() instanceof AAdmin) ? true : false;
	}

	@Override
	public boolean addUser(User u) {
		if (u.getAuthority() instanceof AAdmin || u .getAuthority() instanceof ALecturer) {
			listUser.add(u);
			return true;
		} else {
			return false;
		}
	}

}
