package model.UserAction;

public class UserActionPrediction {

	private final UserActionProcessed action;
	private final double score;

	public UserActionPrediction(UserActionProcessed action, double score) {
		this.action = action;
		this.score = score;
	}

	public UserActionProcessed getAction() {
		return action;
	}

	public double getScore() {
		return score;
	}

}
