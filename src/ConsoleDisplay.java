
public class ConsoleDisplay {
	
	public final int strLength = 15;
	
	void displayWinner(Team team1, Team team2) {
		System.out.println();
		System.out.print("Result: ");
		
		if(team1.getTotalScore() == team2.getTotalScore()) {
			System.out.println(GlobalConstants.MATCH_TIED);
			return;
		}
		
		String winningTeam = team1.getTotalScore() > team2.getTotalScore() ?  team1.getTeamName() : team2.getTeamName();
		int scoreWonBy = Math.abs(team1.getTotalScore() - team2.getTotalScore());
		System.out.println(String.format(GlobalConstants.WINNER_MESSAGE, winningTeam, scoreWonBy));
	}
	
	public void displayScore(Player[] players, Team team) {
		System.out.println();
		System.out.println(String.format("Scorecard for %s", team.getTeamName()));
		System.out.println(String.format("%s%s%s%s%s", getFormattedString("Player Name"), getFormattedString("Score"), getFormattedString("4s"), getFormattedString("6s"), getFormattedString("Balls")));
		for(int playerIndex = 0; playerIndex < players.length; playerIndex++) {
			Player player = players[playerIndex];
			String playerName = player.playerName;
			
			if(player.isBatting()) {
				playerName += "*";
			}
			
			System.out.print(getFormattedString(playerName));
			
			System.out.print(String.format("%s %s %s %s", getFormattedInt(player.getTotalScore()), getFormattedInt(player.getNoFours()), getFormattedInt(player.getNoSixes()), getFormattedInt(player.getNumberBallsPlayed())));
			System.out.println();
		}
		
		System.out.println(String.format("Total: %s/%s", team.getTotalScore() , team.getFalledWickets()));
		System.out.println(String.format("Overs: %s.%s", team.getOversPlayed(), team.getBallsPlayed()));
	}
	
	String getFormattedString(String playerName) {
		return prettyFormat(playerName);
	}
	
	String getFormattedInt(int number) {
		return prettyFormat(String.valueOf(number));
	}
	
	
	String prettyFormat(String str) {
		int length = strLength - str.length();
		while(length > 0) {
			str += " ";
			length--;
		}
		return str;
	}
}
