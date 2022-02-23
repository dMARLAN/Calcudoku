import java.util.Scanner;
import java.io.IOException;

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

	// TODO
	public static boolean checkValid(){
		boolean isValid = false;

		cagesValid();
		columnsValid();
		rowsValid();

		return isValid;
	}

	// TODO
	public static boolean cagesValid(){
		boolean cageValid = false;

		return cageValid;
	}

	// TODO
	public static boolean columnsValid(){
		boolean columnValid = false;

		return columnValid;
	}

	// TODO
	public static boolean rowsValid(){
		boolean rowValid = false;

		return rowValid;
	}

}