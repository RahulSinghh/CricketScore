
import java.util.Scanner;

public class RunnerClass {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println(GlobalConstants.NO_PLAYERS_INPUT);
		int noPlayers = sc.nextInt();
		System.out.println(GlobalConstants.NO_OVERS_INPUT);
		int noOvers = sc.nextInt();
		Match match = new Match(noPlayers, noOvers);
		match.startMatch();
	}

}
