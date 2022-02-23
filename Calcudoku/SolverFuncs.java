import java.util.Scanner;

public class SolverFuncs {

	public static String myScanner(String question){
		Scanner myScanner = new Scanner(System.in);
		System.out.print(question);
		String x = myScanner.nextLine();
		return x;
	}

	public static void getCages(Cage[] cageObj){
		for ( int i = 0; i < Main.numOfCages; i++){
			cageObj[i].displayCageData(i);
		}

	}
}