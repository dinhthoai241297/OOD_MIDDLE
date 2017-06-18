package pdt.ood.models;

public class KickByMem implements kickBehavior{

	@Override
	public boolean kick(User creator, User kick, User beKick) {
		if (beKick.getAuthority() instanceof AAdmin) {
			return false;
		} // không được kick admin
		
		if (kick.getAuthority() instanceof AAdmin || kick.getId().equals(creator.getId())) {
			return true;
		} // admin có thể kick bất kì, chủ phòng cũng thế
		
		return false;
	}

}
