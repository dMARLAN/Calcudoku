import java.util.ArrayList;

public class Main {

	final static int GRID_SIZE = 5;
	public static int numOfCages;

	public static void main(String[] args){

		// Get/Set Input
		numOfCages = Integer.parseInt(SolverFuncs.myScanner("Input number of cages: "));
		ArrayList<ArrayList<Integer>> cageParams = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < numOfCages; i++){
			String inputLine;
			inputLine = SolverFuncs.myScanner("Input line " + (i+1) + ": ");
			String[] splitLine = inputLine.split(" ");
			cageParams.add(new ArrayList<Integer>());
			for (int j = 0; j < splitLine.length; j++){
				cageParams.get(i).add(Integer.parseInt(splitLine[j]));
			}
		}

		// Initialize Puzzle Cells
		ArrayList<ArrayList<Integer>> puzzle = new ArrayList<ArrayList<Integer>>(GRID_SIZE);
		for (int i = 0; i < GRID_SIZE; i++){
			puzzle.add(new ArrayList<Integer>());
			for (int j = 0; j < GRID_SIZE; j++){
				puzzle.get(i).add(0);
			}

		}

		// Create Cage Objects
		Cage[] cageObjects = new Cage[numOfCages];
		for ( int i = 0; i < numOfCages; i++){
			int numOfCells = cageParams.get(i).get(1);
			int cellPos[] = new int[numOfCells];
			for ( int j = 0; j < numOfCells; j++){
				cellPos[j] = cageParams.get(i).get(j+2);
			}
			cageObjects[i] = new Cage(cageParams.get(i).get(0), cageParams.get(i).get(1), cellPos);
		}

		//SolverFuncs.getCages(cageObjects);

	}

}


