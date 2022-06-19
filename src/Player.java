
public class Player {
	
	String playerID;
	String playerName;
	private int totalScore;
	private int noFours;
	private int noSixes;
	private int numberBallsPlayed;
	private boolean isBatting;
	private boolean isOut;
	
	public Player(String playerName) {
		this.playerID = playerName;
		this.playerName = playerName;
		this.totalScore = 0;
		this.noFours = 0;
		this.noSixes = 0;
		this.isBatting = false;
		this.isOut = false;
	}
	
	public String getPlayerID() {
		return playerID;
	}

	public void setPlayerID(String playerID) {
		this.playerID = playerID;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getTotalScore() {
		return totalScore;
	}

	public void increaseScore(int currentRun) {
		this.totalScore += currentRun;
		if(currentRun == GlobalConstants.FOUR) {
			this.setNoFours(this.getNoFours() + 1);
		} else if(currentRun == GlobalConstants.SIX) {
			this.setNoSixes(this.getNoSixes() + 1);
		}
	}

	public int getNoFours() {
		return noFours;
	}

	public void setNoFours(int noFours) {
		this.noFours = noFours;
	}

	public int getNoSixes() {
		return noSixes;
	}

	public void setNoSixes(int noSixes) {
		this.noSixes = noSixes;
	}

	public int getNumberBallsPlayed() {
		return numberBallsPlayed;
	}

	public void setNumberBallsPlayed(int numberBallsPlayed) {
		this.numberBallsPlayed = numberBallsPlayed;
	}

	public boolean isBatting() {
		return isBatting;
	}

	public void setBatting(boolean isBatting) {
		this.isBatting = isBatting;
	}

	public boolean isOut() {
		return isOut;
	}

	public void setOut(boolean isOut) {
		this.isOut = isOut;
	}

}
