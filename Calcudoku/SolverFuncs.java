import java.util.Scanner;

public class SolverFuncs {

	public static String myScanner(String question){
		Scanner myScanner = new Scanner(System.in);
		System.out.print(question);
		String x = myScanner.nextLine();
		return x;
	}

	public static void getCages(Cage[] cageObj, int numOfCages){
		for ( int i = 0; i < numOfCages; i++){
			cageObj[i].displayCageData(i);
		}
	}

	public static boolean checkValid(){
		// TODO
	}

	public static boolean cagesValid(){
		// TODO
	}

	public static boolean columnsValid(){
		// TODO
	}

	public static boolean rowsValid(){
		// TODO
	}
}