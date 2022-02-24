import java.util.ArrayList;

public class Main {

	public final static int GRID_SIZE = 5;
	public static ArrayList<ArrayList<Integer>> puzzle = new ArrayList<ArrayList<Integer>>(GRID_SIZE);

	public static void main(String[] args){

		// Get/Set Input
		int numOfCages = 0;
		try {
			numOfCages = Integer.parseInt(SolverFuncs.myScanner("Input number of cages: "));
		} catch (Exception e) {
			System.out.println("ERR: Invalid Input");
			System.exit(0);
		}
		System.out.println();
		ArrayList<ArrayList<Integer>> cageParams = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < numOfCages; i++){
			String inputLine;
			inputLine = SolverFuncs.myScanner("Input line " + (i+1) + ": ");
			String[] splitLine = inputLine.split(" ");
			cageParams.add(new ArrayList<Integer>());
			for (int j = 0; j < splitLine.length; j++){
				cageParams.get(i).add(Integer.parseInt(splitLine[j]));
			}
			if (cageParams.get(i).get(1) != cageParams.get(i).size()-2){
				System.out.println("ERR: Invalid Input");
				System.exit(0);
			}
		}
		System.out.println();

		// Initialize Puzzle Cells
		for (int i = 0; i < GRID_SIZE; i++){
			puzzle.add(new ArrayList<Integer>());
			for (int j = 0; j < GRID_SIZE; j++){
				puzzle.get(i).add(0);
			}
		}

		// Create Cage Objects
		Cage[] cageObjects = new Cage[numOfCages];
		for (int i = 0; i < numOfCages; i++){
			int numOfCells = cageParams.get(i).get(1);
			int cellPos[] = new int[numOfCells];
			for (int j = 0; j < numOfCells; j++){
				cellPos[j] = cageParams.get(i).get(j+2);
			}
			cageObjects[i] = new Cage(cageParams.get(i).get(0), cageParams.get(i).get(1), cellPos);
		}

		// Solve Puzzle
		int x = 0;
		int y = 0;
		while(!SolverFuncs.isSolved(puzzle, cageObjects)){
			for (x = 0; x < GRID_SIZE; x++){
				for (y = 0; y < GRID_SIZE; y++){
					incrementCell(x, y);
					while(!SolverFuncs.checkValid(puzzle, cageObjects)){
						displayPuzzle();
						//System.out.println("Puzzle Valid: " + SolverFuncs.checkValid(puzzle, cageObjects));
						//System.out.println("Rows Valid: " + SolverFuncs.rowsValid(puzzle));
						//System.out.println("Columns Valid: " + SolverFuncs.columnsValid(puzzle));
						//System.out.println("Cages Valid: " + SolverFuncs.cagesValid(puzzle, cageObjects));
						incrementCell(x, y);
						//System.out.print(" x: " + x);
						//System.out.print(" y: " + y);
						// Backtrack, Set Cell to 0
						if(puzzle.get(x).get(y) > GRID_SIZE){
							puzzle.get(x).set(y,0);
							if( y == 0 ){
								x = x-1;
								y = 4;
							} else {
								y = y-2;
							}
						}
					}
				}
			}
		}
	}

	public static void incrementCell(int x, int y){
		int a = puzzle.get(x).get(y)+1;
		puzzle.get(x).set(y,a);
	}

	public static void displayPuzzle(){
		System.out.println(" displayPuzzle: ");
		for ( int x = 0; x < GRID_SIZE; x++){
			for ( int y = 0; y < GRID_SIZE; y++){
				System.out.print(puzzle.get(x).get(y));
			}
			System.out.println();
		}
	}
}