import java.util.ArrayList;

public class Main {

	// VARS
	public final static int GRID_SIZE = 5;
	public static int[][] puzzle = new int[GRID_SIZE][GRID_SIZE];
	public static int numOfCages = 0;
	public static ArrayList<ArrayList<Integer>> cageParams = new ArrayList<ArrayList<Integer>>();
	public static Cage[] cages;
	public static int[][] incrmBoard = new int[Main.GRID_SIZE][Main.GRID_SIZE];
	///////

	public static void main(String[] args){

		//getInputs();
		setInputsDebug();

		initializePuzzle();
		initializeIncrementalBoard();
		createCages(numOfCages, cageParams);

		SolverFuncs.solvePuzzle(puzzle, cages, incrmBoard);

		displayPuzzle();

		System.out.println();
		if(SolverFuncs.solvePuzzle(puzzle, cages, incrmBoard)){
			System.out.println("Solved!");
		} else {
			System.out.println("Not Solved.");
		}

		SolverFuncs.getCages(cages);
		System.out.println("Checks: " + SolverFuncs.checks);
		System.out.println("Backtracks: " + SolverFuncs.backtracks);
	}

	public static void displayPuzzle(){
		System.out.println();
		System.out.println("== Solution ==");
		for(int i = 0; i < GRID_SIZE; i++){
			for(int j = 0; j < GRID_SIZE; j++){
				System.out.print(puzzle[i][j]+"  ");
			}
			System.out.println();
			System.out.println();
		}
	}

	public static void setInputsDebug(){
		numOfCages = 9;

		cageParams.add(new ArrayList<Integer>());
		cageParams.get(0).add(9);
		cageParams.get(0).add(3);
		cageParams.get(0).add(0);
		cageParams.get(0).add(5);
		cageParams.get(0).add(6);

		cageParams.add(new ArrayList<Integer>());
		cageParams.get(1).add(7);
		cageParams.get(1).add(2);
		cageParams.get(1).add(1);
		cageParams.get(1).add(2);
		
		cageParams.add(new ArrayList<Integer>());
		cageParams.get(2).add(10);
		cageParams.get(2).add(3);
		cageParams.get(2).add(3);
		cageParams.get(2).add(8);
		cageParams.get(2).add(13);

		cageParams.add(new ArrayList<Integer>());
		cageParams.get(3).add(14);
		cageParams.get(3).add(4);
		cageParams.get(3).add(4);
		cageParams.get(3).add(9);
		cageParams.get(3).add(14);
		cageParams.get(3).add(19);

		cageParams.add(new ArrayList<Integer>());
		cageParams.get(4).add(3);
		cageParams.get(4).add(1);
		cageParams.get(4).add(7);

		cageParams.add(new ArrayList<Integer>());
		cageParams.get(5).add(8);
		cageParams.get(5).add(3);
		cageParams.get(5).add(10);
		cageParams.get(5).add(11);
		cageParams.get(5).add(16);

		cageParams.add(new ArrayList<Integer>());
		cageParams.get(6).add(13);
		cageParams.get(6).add(4);
		cageParams.get(6).add(12);
		cageParams.get(6).add(17);
		cageParams.get(6).add(21);
		cageParams.get(6).add(22);

		cageParams.add(new ArrayList<Integer>());
		cageParams.get(7).add(5);
		cageParams.get(7).add(2);
		cageParams.get(7).add(15);
		cageParams.get(7).add(20);

		cageParams.add(new ArrayList<Integer>());
		cageParams.get(8).add(6);
		cageParams.get(8).add(3);
		cageParams.get(8).add(18);
		cageParams.get(8).add(23);
		cageParams.get(8).add(24);

		cages = new Cage[numOfCages];
	}

	public static void getInputs(){
		numOfCages = Integer.parseInt(SolverFuncs.myScanner("Input number of cages: "));
		System.out.println();
		for (int i = 0; i < numOfCages; i++){
			String inputLine;
			inputLine = SolverFuncs.myScanner("Input line " + (i+1) + ": ");
			String[] splitLine = inputLine.split(" ");
			cageParams.add(new ArrayList<Integer>());
			for (int j = 0; j < splitLine.length; j++){
				cageParams.get(i).add(Integer.parseInt(splitLine[j]));
			}
		}
		System.out.println();
		cages = new Cage[numOfCages];
	}

	public static void initializePuzzle(){
		for (int i = 0; i < GRID_SIZE; i++){
			for (int j = 0; j < GRID_SIZE; j++){
				puzzle[i][j] = 0;
			}
		}
	}

	public static void initializeIncrementalBoard(){
		for(int i = 0; i < Main.GRID_SIZE; i++){
			for (int j = 0; j < Main.GRID_SIZE; j++){
				if( (i == 0) && (j == 0)){
					incrmBoard[i][j] = 0;
				} else if (j == 0) {
					incrmBoard[i][j] = incrmBoard[i-1][j+4]+1;
				} else {
					incrmBoard[i][j] = incrmBoard[i][j-1]+1;
				}
			}
		}	
	}
	

	public static void createCages(int numOfCages, ArrayList<ArrayList<Integer>> cageParams){
		for (int i = 0; i < numOfCages; i++){
			int numOfCells = cageParams.get(i).get(1);
			int cellPos[] = new int[numOfCells];
			for (int j = 0; j < numOfCells; j++){
				cellPos[j] = cageParams.get(i).get(j+2);
			}
			cages[i] = new Cage(cageParams.get(i).get(0), cageParams.get(i).get(1), cellPos);
		}
	}
}