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

	public static boolean checkValid(ArrayList<ArrayList<Integer>> puzzle, Cage[] cageObj){
		boolean isValid = false;
		if ( cagesValid(puzzle, cageObj) && rowsValid(puzzle) && columnsValid(puzzle) ){
			isValid = true;
		}
		return isValid;
	}
	
	public static boolean cagesValid(ArrayList<ArrayList<Integer>> puzzle, Cage[] cageObj){
		boolean cageValid = false;
		int[][] cageIndex = new int[Main.GRID_SIZE][Main.GRID_SIZE];

		// Create incremental array for comparison to.
		for(int i = 0; i < Main.GRID_SIZE; i++){
			for (int j = 0; j < Main.GRID_SIZE; j++){
				if( (i == 0) && (j == 0)){
					cageIndex[i][j] = 1;
				} else if (j == 0) {
					cageIndex[i][j] = cageIndex[i-1][j+4]+1;
				} else {
					cageIndex[i][j] = cageIndex[i][j-1]+1;
				}
			}
		}
		
		for (int i = 0; i < cageObj.length; i++){
			int cellPosLength = cageObj[i].cellPos.length;
			int[] cellX = new int[cellPosLength];
			int[] cellY = new int[cellPosLength];
			int cellSum = 0;
			for (int j = 0; j < cellPosLength; j++){
				// Get puzzle index from incremental indentifier.
				for (int x = 0; x < Main.GRID_SIZE; x++){
					for (int y = 0; y < Main.GRID_SIZE; y++){
						if (cageObj[i].cellPos[j] == cageIndex[x][y]){
							cellX[j] = x;
							cellY[j] = y;
							break;
						}
					}		
				}
				cellSum += puzzle.get(cellX[j]).get(cellY[j]);
				int count = 0;
				for (int x = 0; x < cellPosLength; x++){
					if (puzzle.get(cellX[j]).get(cellY[j]) > 0){
						count += 1;
					}
				}
				if((count == cellPosLength) && (cellSum == cageObj[i].cageSum)){
					cageValid = true;
				} else if (cellSum < cageObj[i].cageSum) {
					cageValid = true;
				}
			}
		}

		return cageValid;
	}	

	public static boolean rowsValid(ArrayList<ArrayList<Integer>> puzzle){
		boolean rowsValid = false;
		for (int x = 0; x < Main.GRID_SIZE; x++){
			HashSet<Integer> rowSet = new HashSet<Integer>();
			ArrayList<Integer> rowAList = new ArrayList<Integer>();
			// Build Row
			for (int y = 0; y < Main.GRID_SIZE; y++){
				if(puzzle.get(x).get(y) != 0){
					rowAList.add(puzzle.get(x).get(y));
					rowSet.add(puzzle.get(x).get(y));
				}
			}
			//System.out.println("rowSet: " + rowSet);
			//System.out.println("rowAList: " + rowAList);
			if(rowSet.size() == rowAList.size()){
				rowsValid = true;
			} else {
				rowsValid = false;
			}
			if(!rowsValid) break;
		}
		return rowsValid;
	}

	public static boolean columnsValid(ArrayList<ArrayList<Integer>> puzzle){
		boolean columnsValid = false;
		for (int i = 0; i < Main.GRID_SIZE; i++){
			ArrayList<Integer> columnAList = new ArrayList<Integer>();
			HashSet<Integer> columnSet = new HashSet<Integer>();
			// Build Column
			for (int j = 0; j < Main.GRID_SIZE; j++){
				if(puzzle.get(j).get(i) != 0){
					columnAList.add(puzzle.get(j).get(i));
					columnSet.add(puzzle.get(j).get(i));
				}
				//System.out.println("columnSet: " + columnSet);
				//System.out.println("columnAList: " + columnAList);
			}
			if(columnSet.size() == columnAList.size()){
					columnsValid = true;
				} else {
					columnsValid = false;
			}
			if(!columnsValid) break;
		}
		return columnsValid;
	}

	public static boolean isSolved(ArrayList<ArrayList<Integer>> puzzle, Cage[] cageObj){
		boolean isSolved = false;
		int sumPuzzle = 0;
		int sumCages = 0;

		for (int x = 0; x < Main.GRID_SIZE; x++){
			for (int y = 0; y < Main.GRID_SIZE; y++){
				sumPuzzle += puzzle.get(x).get(y);
			}
		}

		for (int x = 0; x < cageObj.length; x++){
			sumCages += cageObj[x].cageSum;
		}

		if (sumPuzzle == sumCages){
			isSolved = true;
			System.out.println("PUZZLE SOLVED :)!");
			System.out.println("sumCages: " + sumCages);
			System.out.println("sumPuzzle: " + sumPuzzle);
		}
		return isSolved;
	}

}