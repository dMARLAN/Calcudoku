import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashSet;

public class SolverFuncs {

	public static String myScanner(String input){
		Scanner myScanner = new Scanner(System.in);
		System.out.print(input);
		String x = myScanner.nextLine();
		return x;
	}

	public static void getCages(Cage[] cageObj, int numOfCages){
		for ( int i = 0; i < numOfCages; i++){
			cageObj[i].displayCageData(i);
		}
	}

	public static boolean checkValid(int[][] puzzle, Cage[] cageObj, int row, int column, int checkNumber){
		return !rowsValid(puzzle, row, checkNumber) &&
        !columnsValid(puzzle, column, checkNumber);

        //!cagesValid(puzzle, cageObjects);
	}
	
	/*public static boolean cagesValid(int[][] puzzle, Cage[] cageObjects){
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
		
		for (int i = 0; i < cageObjects.length; i++){
			int cellPosLength = cageObjects[i].cellPos.length;
			int[] cellX = new int[cellPosLength];
			int[] cellY = new int[cellPosLength];
			int cellSum = 0;
			for (int j = 0; j < cellPosLength; j++){
				// Get puzzle index from incremental indentifier.
				for (int x = 0; x < Main.GRID_SIZE; x++){
					for (int y = 0; y < Main.GRID_SIZE; y++){
						if (cageObjects[i].cellPos[j] == cageIndex[x][y]){
							cellX[j] = x;
							cellY[j] = y;
						}
					}		
				}
				cellSum = cellSum + puzzle.get(cellX[j]).get(cellY[j]);
				int count = 0;
				for (int x = 0; x < cellPosLength; x++){
					if (puzzle.get(cellX[j]).get(cellY[j]) > 0){
						count += 1;
					}
				}
				if((count == cellPosLength) & (cellSum == cageObjects[i].cageSum)){
					return true;
				} else if ((count < cellPosLength) & (cellSum < cageObjects[i].cageSum)) {
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}*/

	public static boolean rowsValid(int[][] puzzle, int row, int checkNumber){
		for (int i = 0; i < Main.GRID_SIZE; i++){
			if(puzzle[row][i] == checkNumber){
				return true;
			}
		}
		return false;
	}

	public static boolean columnsValid(int[][] puzzle, int column, int checkNumber){
		for (int i = 0; i < Main.GRID_SIZE; i++){
			if(puzzle[i][column] == checkNumber){
				return true;
			}
		}
		return false;
	}

	public static boolean solvePuzzle(int[][] puzzle, Cage[] cageObjects){
		for (int row = 0; row < Main.GRID_SIZE; row++){
			for (int column = 0; column < Main.GRID_SIZE; column++){
				if(puzzle[row][column] == 0){
					for (int checkNumber = 1; checkNumber <= Main.GRID_SIZE; checkNumber++){
						if(checkValid(puzzle, cageObjects, row, column, checkNumber)){
							puzzle[row][column] = checkNumber;

							System.out.println();
							System.out.println("=== Puzzle ===");
							for(int i = 0; i < Main.GRID_SIZE; i++){
								for(int j = 0; j < Main.GRID_SIZE; j++){
									System.out.print(puzzle[i][j]);
								}
								System.out.println();
							}

							if(solvePuzzle(puzzle, cageObjects)){
								return true;
							} else {
								puzzle[row][column] = 0;
							}
						}
					}
					return false;
				}
			}
		}
		return true;
	}
}