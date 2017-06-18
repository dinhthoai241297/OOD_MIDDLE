package pdt.ood.models;

public class KickByVote implements kickBehavior{

	@Override
	public boolean kick(User creator, User kick, User beKick) {
		return false;
	}

}
