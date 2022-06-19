import java.util.Scanner;


public class Match {

	private Team team1;
	private Team team2;
	private int noOvers;
	
	private Player playerOnStrike;
	private Player playerOnNonStrike;
	private ConsoleDisplay consoleDisplay;
	private Scanner sc;
	public Match(int noPlayers, int noOvers) {
		sc = new Scanner(System.in);
		this.noOvers = noOvers;
		this.team1 = new Team(GlobalConstants.TEAM1, noPlayers);
		this.team2 = new Team(GlobalConstants.TEAM2, noPlayers);
		this.consoleDisplay = new ConsoleDisplay();
	}
	
	void setPlayerOnStrike(Player playerOnStrike) {
		this.playerOnStrike = playerOnStrike; 
	}
	
	void setPlayerOnNonStrike(Player playerOnNonStrike) {
		this.playerOnNonStrike = playerOnNonStrike;
	}
	
	Player getPlayerOnStrike() {
		return this.playerOnStrike;
	}
	
	Player getPlayerOnNonStrike() {
		return this.playerOnNonStrike;
	}
	
	void startMatch() {
		team1.setPlayerOrder();
		startBatting(team1);
		team2.setPlayerOrder();
		startBatting(team2);
		displayWinner();
	}
	
	void startBatting(Team team) {
		int noOvers = this.noOvers;
		this.setPlayerOnStrike(team.getNextBatsMan());
		this.setPlayerOnNonStrike(team.getNextBatsMan());
		int overNumber = 1;
		while(overNumber <= noOvers) {
			boolean isOverCompleted = playOver(team, overNumber);
			team.displayScore();
			changeStrike();
			if(!isOverCompleted)
				return;
			overNumber++;
		}
	}
	
	boolean playOver(Team team, int overNumber) {
		System.out.println();
		System.out.println("Over "+overNumber);
		
		int numberOfBalls = 0;
		int totalScore = 0;
		
		while(numberOfBalls < GlobalConstants.NO_BALL_IN_OVER) {
			
			String ballStatus = sc.next();
			int runs = 0;
			Player playerOnStrike = this.getPlayerOnStrike();
			
			if(GlobalConstants.WIDE_BALL.equals(ballStatus)) {
				runs += 1;
			} else if(GlobalConstants.WICKET.equals(ballStatus)) {
				numberOfBalls += 1;
				playerOnStrike.setNumberBallsPlayed(playerOnStrike.getNumberBallsPlayed() + 1);
				team.markPlayerOut(playerOnStrike);
				Player nextBatsMan = team.getNextBatsMan();
				if(nextBatsMan == null)
					break;
				this.setPlayerOnStrike(nextBatsMan);
				
			} else {
				numberOfBalls += 1; 
				runs += Integer.parseInt(ballStatus);
				playerOnStrike.increaseScore(runs);
				playerOnStrike.setNumberBallsPlayed(playerOnStrike.getNumberBallsPlayed() + 1);
				if((runs%2) != 0) {
					changeStrike(); 
				}
			}
			
			totalScore += runs;
		}
		
		team.setBallsPlayed(numberOfBalls);
		team.setTotalScore(team.getTotalScore() + totalScore);
		
		if(numberOfBalls < GlobalConstants.NO_BALL_IN_OVER) {
			return false;
		}
		return true;
	}
	
	void displayWinner() {
		consoleDisplay.displayWinner(team1, team2);
	}
	
	void changeStrike() {
		Player playerOnStrike = getPlayerOnStrike();
		Player playerOnNonStrike = getPlayerOnNonStrike();
		
		setPlayerOnStrike(playerOnNonStrike);
		setPlayerOnNonStrike(playerOnStrike);
	}

}
