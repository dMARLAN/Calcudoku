import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashSet;

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

	public static boolean checkValid(ArrayList<ArrayList<Integer>> puzzle, int x, int y){
		boolean isValid = false;
		//if ( cagesValid() && && rowValid(puzzle, x) && columnValid(puzzle, y) ){
		//	isValid = true;
		//}
		return isValid;
	}

	
	public static boolean cagesValid(){
		boolean cageValid = false;
		// TODO
		return cageValid;
	}

	public static boolean rowValid(ArrayList<ArrayList<Integer>> puzzle, int x){
		boolean rowValid = false;

		// If ArrayList is the same size as the HashSet, there are no duplicates.
		HashSet<Integer> rowSet = new HashSet<Integer>(puzzle.get(x));
		if(rowSet.size() == puzzle.get(x).size()){
			rowValid = true;
		}

		return rowValid;
	}

	public static boolean columnValid(ArrayList<ArrayList<Integer>> puzzle, int y){
		boolean columnValid = false;
		
		ArrayList<Integer> columnAList = new ArrayList<Integer>();
		HashSet<Integer> columnSet = new HashSet<Integer>();

		// Build Column
		for (int i = 0; i < Main.GRID_SIZE; i++){
			columnAList.add(puzzle.get(i).get(y));
		}
		// Build Column in a HashSet
		for (int i = 0; i < Main.GRID_SIZE; i++){
			columnSet.add(puzzle.get(i).get(y));
		}
		// If ArrayList is the same size as the HashSet, there are no duplicates.
		if(columnSet.size() == columnAList.size()){
			columnValid = true;
		}

		return columnValid;
	}

	public static boolean isSolved(){
		boolean isSolved = false;
		// TODO
		return isSolved;
	}

}