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
	
	
	/**
	 * 
	 * @param team
	 */
	void startBatting(Team team) {
		/*
		 * loop till either all overs are completed or all wickets are down
		 * at end of each over 
		 *  a) displayScore
		 *  b) changeStrike
		 *  c) update overNumber
		 * 
		 */
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
	
	/**
	 * 
	 * @param team
	 * @param overNumber
	 * @return boolean (if the complete Over was completed or all wickets were down before over completion)
	 */
	boolean playOver(Team team, int overNumber) {
		
		/*
		 * This method is responsible for processing each ball in a Over
		 * if ball was wide then simply add 1 run to team score
		 * if ball took wicket then 
		 * 	a) increase number of balls by 1,
		 * 	b) mark strike batsMan as out
		 *  c) get Next batsman from the team
		 *  d) if nextBatsMan == null stop the game
		 *  e) if nextBatsMan 1= null set that batsMan on strike
		 *  
		 * if batsMan scored runs on the ball
		 * 	a) increase ball by one
		 *  b) increase strike batsman score by one
		 *  
		 * At last update team score by total runs in over
		 * and update number of balls played by team.
		 */
		
		
		System.out.println();
		System.out.println("Over "+overNumber);
		
		int numberOfBalls = 0;
		int totalScoreInOver = 0;
		
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
			
			totalScoreInOver += runs;
		}
		
		team.setBallsPlayed(numberOfBalls);
		team.setTotalScore(team.getTotalScore() + totalScoreInOver);
		
		if(numberOfBalls < GlobalConstants.NO_BALL_IN_OVER) {
			return false;
		}
		return true;
	}
	
	void displayWinner() {
		consoleDisplay.displayWinner(team1, team2);
	}
	
	void changeStrike() {
		/*
		 * Swap the position of Strike and non Strike batsman
		 */
		Player playerOnStrike = getPlayerOnStrike();
		Player playerOnNonStrike = getPlayerOnNonStrike();
		
		setPlayerOnStrike(playerOnNonStrike);
		setPlayerOnNonStrike(playerOnStrike);
	}

}
