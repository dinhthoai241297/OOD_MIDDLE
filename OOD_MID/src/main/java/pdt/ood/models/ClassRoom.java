package pdt.ood.models;

public class ClassRoom extends Room {
	
	private static int countClassRoom = 0;

	public ClassRoom(User creator, String name, String password) {
		super(creator, name, password);
		this.id = "ClassRoom" + countClassRoom++;
		kickBe = new KickByMem();
	}

	@Override
	public boolean checkWrite(User u) {
		return (u.getAuthority() instanceof AAdmin || creator.getId().equals(u.getId())) ? true : false;
	}

	@Override
	public boolean checkFun(User u) {
		return (u.getAuthority() instanceof AAdmin || creator.getId().equals(u.getId())) ? true : false;
	}

	@Override
	public boolean addUser(User u) {
		listUser.add(u);
		return true;
	}

}
