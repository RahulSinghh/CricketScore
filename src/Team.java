import java.util.Scanner;

public class Team {
	private Player players[];
	private String teamName;
	private int totalScore;
	private int noPlayers;
	private int falledWickets;
	private int oversPlayed; 
	private int ballsPlayed;
	private ConsoleDisplay consoleDisplay;
	
	public int getBallsPlayed() {
		return ballsPlayed;
	}

	public void setBallsPlayed(int ballsPlayed) {
		this.ballsPlayed = ballsPlayed;
		if(this.ballsPlayed == 6) {
			this.ballsPlayed = 0;
			this.setOversPlayed(this.getOversPlayed() + 1);
		}
	}

	public Team(String teamName, int noPlayers) {
		this.teamName = teamName;
		this.noPlayers = noPlayers;
		players = new Player[noPlayers];
		this.oversPlayed = 0;
		this.consoleDisplay = new ConsoleDisplay();
	}
	
	public String getTeamName() {
		return this.teamName;
	}

	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	public int getFalledWickets() {
		return falledWickets;
	}
	
	public int getOversPlayed() {
		return this.oversPlayed;
	}
	
	public void setOversPlayed(int oversPlayed) {
		this.oversPlayed = oversPlayed;
	}
	
	public void displayScore() {
		this.consoleDisplay.displayScore(players, this);
	}
	
	void setPlayerOrder() {
		Scanner sc = new Scanner(System.in);
		System.out.println();
		System.out.println(String.format("Batting Order for %s: ", this.teamName));
		for(int playerIndex = 0; playerIndex < this.noPlayers; playerIndex++) {
			String playerName = sc.next();
			players[playerIndex] = new Player(playerName);
		}
		//comment
	}

	public int getNoPlayers() {
		return noPlayers;
	}

	public void setNoPlayers(int noPlayers) {
		this.noPlayers = noPlayers;
	}
	
	public Player getNextBatsMan() {
		for(int playerIndex = 0; playerIndex < this.noPlayers; playerIndex++) {
			if(!players[playerIndex].isBatting() && !players[playerIndex].isOut()) {
				Player nextBatsMan = players[playerIndex];
				nextBatsMan.setBatting(true);
				return nextBatsMan;
			}
		}
		
		return null;
	}
	
	void markPlayerOut(Player player) {
		player.setBatting(false);
		player.setOut(true);
		falledWickets += 1;
	}
}
