import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CalcudokuSolver {

	private final static int GRID_SIZE = 5;

	public static void main(String[] args){
		int inputChoice = 1; // 0 Default Input, 1 Manual Input
		
		ArrayList<ArrayList<Integer>> cageParams = setCageParameters(inputChoice);
		int[][] puzzle = initializePuzzle();
		int[][] incrmBoard = initializeIncrementalBoard();
		Cage[] cages = createCages(cageParams);
		SolverFuncs.solvePuzzle(puzzle, cages, incrmBoard, 0, 0);
		displayPuzzle(puzzle);

		//getCages(cages);
		System.out.print("\nchecks: " + SolverFuncs.checks);
		System.out.println(" backtracks: " + SolverFuncs.backtracks);
	}

	private static void displayPuzzle(int[][] puzzle){
		System.out.println("\n---Solution---\n");
		for(int i = 0; i < puzzle.length; i++){
			for(int j = 0; j < puzzle.length; j++){
				System.out.print(puzzle[i][j]+" ");
			}
			System.out.println();
		}
	}

	private static ArrayList<ArrayList<Integer>> setCageParameters(int inputChoice){
		ArrayList<ArrayList<Integer>> cageParams = new ArrayList<ArrayList<Integer>>();
		if(inputChoice == 1){
			Scanner myScanner = new Scanner(System.in);	
			System.out.print("Number of cages: ");
			int numOfCages = Integer.parseInt(myScanner.nextLine());
			for (int i = 0; i < numOfCages; i++){
				System.out.print("Cage number " + (i) + ": ");
				String inputLine = myScanner.nextLine();
				String[] splitLine = inputLine.split(" ");
				cageParams.add(new ArrayList<Integer>());
				for (int j = 0; j < splitLine.length; j++){
					cageParams.get(i).add(Integer.parseInt(splitLine[j]));
				}
			}
			myScanner.close();
		} else {
			ArrayList<Integer> c0 = new ArrayList<Integer>(Arrays.asList(9,3,0,5,6));
			cageParams.add(new ArrayList<Integer>());
			cageParams.get(0).addAll(c0);

			ArrayList<Integer> c1 = new ArrayList<Integer>(Arrays.asList(7,2,1,2));
			cageParams.add(new ArrayList<Integer>());
			cageParams.get(1).addAll(c1);
			
			ArrayList<Integer> c2 = new ArrayList<Integer>(Arrays.asList(10,3,3,8,13));
			cageParams.add(new ArrayList<Integer>());
			cageParams.get(2).addAll(c2);

			ArrayList<Integer> c3 = new ArrayList<Integer>(Arrays.asList(14,4,4,9,14,19));
			cageParams.add(new ArrayList<Integer>());
			cageParams.get(3).addAll(c3);
			
			ArrayList<Integer> c4 = new ArrayList<Integer>(Arrays.asList(3,1,7));
			cageParams.add(new ArrayList<Integer>());
			cageParams.get(4).addAll(c4);
			
			ArrayList<Integer> c5 = new ArrayList<Integer>(Arrays.asList(8,3,10,11,16));
			cageParams.add(new ArrayList<Integer>());
			cageParams.get(5).addAll(c5);
			
			ArrayList<Integer> c6 = new ArrayList<Integer>(Arrays.asList(13,4,12,17,21,22));
			cageParams.add(new ArrayList<Integer>());
			cageParams.get(6).addAll(c6);
			
			ArrayList<Integer> c7 = new ArrayList<Integer>(Arrays.asList(5,2,15,20));
			cageParams.add(new ArrayList<Integer>());
			cageParams.get(7).addAll(c7);
			
			ArrayList<Integer> c8 = new ArrayList<Integer>(Arrays.asList(6,3,18,23,24));
			cageParams.add(new ArrayList<Integer>());
			cageParams.get(8).addAll(c8);
		}
		return cageParams;
	}

	private static int[][] initializePuzzle(){
		int[][] puzzle = new int[GRID_SIZE][GRID_SIZE];
		for (int i = 0; i < GRID_SIZE; i++){
			for (int j = 0; j < GRID_SIZE; j++){
				puzzle[i][j] = 0;
			}
		}
		return puzzle;
	}

	private static int[][] initializeIncrementalBoard(){
		int[][] incrmBoard = new int[GRID_SIZE][GRID_SIZE];
		for(int i = 0; i < GRID_SIZE; i++){
			for (int j = 0; j < GRID_SIZE; j++){
				if( (i == 0) && (j == 0)){
					incrmBoard[i][j] = 0;
				} else if (j == 0) {
					incrmBoard[i][j] = incrmBoard[i-1][j+4]+1;
				} else {
					incrmBoard[i][j] = incrmBoard[i][j-1]+1;
				}
			}
		}
		return incrmBoard;	
	}
	
	/*private static void getCages(Cage[] cages){
		System.out.println();
		for ( int i = 0; i < cages.length; i++){
			cages[i].displayCageData(i);
		}
	}*/

	private static Cage[] createCages(ArrayList<ArrayList<Integer>> cageParams){
		Cage[] cages = new Cage[cageParams.size()];
		int numOfCages = cageParams.size();
		for (int i = 0; i < numOfCages; i++){
			int numOfCells = cageParams.get(i).get(1);
			int cellPos[] = new int[numOfCells];
			for (int j = 0; j < numOfCells; j++){
				cellPos[j] = cageParams.get(i).get(j+2);
			}
			cages[i] = new Cage(cageParams.get(i).get(0), cageParams.get(i).get(1), cellPos);
		}
		return cages;
	}
}