import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashSet;

public class SolverFuncs {

	public static int checks = 0;
	public static int backtracks = 0;

	public static String myScanner(String input){
		Scanner myScanner = new Scanner(System.in);
		System.out.print(input);
		String x = myScanner.nextLine();
		return x;
	}

	public static void getCages(Cage[] cages){
		System.out.println();
		for ( int c = 0; c < cages.length; c++){
			cages[c].displayCageData(c);
		}
	}

	public static boolean checkValid(int[][] puzzle, Cage[] cages, int row, int column, int checkNumber, int[][] incrmBoard){
		return !rowsValid(puzzle, row, checkNumber) &&
        !columnsValid(puzzle, column, checkNumber) &&
		!cagesValid(puzzle, cages, incrmBoard);
	}
	
	public static boolean cagesValid(int[][] puzzle, Cage[] cages, int[][] incrmBoard){

		for(int c = 0; c < cages.length; c++){
			int numberOfCells = cages[c].numberOfCells;
			int cageSum = cages[c].cageSum;
			int[] cellPos = cages[c].cellPos;
			int cageActualSum = 0;
			int cellOccupied = 0;

			for(int cell = 0; cell < numberOfCells; cell++){
				int cellIndexX = 0;
				int cellIndexY = 0;
				

				// Assign Index to Incremental Key
				for(int x = 0; x < Main.GRID_SIZE; x++){
					for (int y = 0; y < Main.GRID_SIZE; y++){
						if(incrmBoard[x][y] == cellPos[cell]){
							cellIndexX = x;
							cellIndexY = y;
						}
					}
				}

				// Check if cells are occupied.
				if(puzzle[cellIndexX][cellIndexY] > 0){
					cellOccupied++;
					cageActualSum += puzzle[cellIndexX][cellIndexY];
				}
			}

			if(cellOccupied == numberOfCells){
				if(cageActualSum != cageSum){
					return true;
				}
			}

			if(cellOccupied < numberOfCells){
				if(cageActualSum >= cageSum){
					return true;
				}
			}
		}
		
		return false;
	}

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

	public static boolean solvePuzzle(int[][] puzzle, Cage[] cages, int[][] incrmBoard){
		for (int row = 0; row < Main.GRID_SIZE; row++){
			for (int column = 0; column < Main.GRID_SIZE; column++){
				if(puzzle[row][column] == 0){
					for (int checkNumber = 1; checkNumber <= Main.GRID_SIZE; checkNumber++){
						checks++;
						if(checkValid(puzzle, cages, row, column, checkNumber, incrmBoard)){
							puzzle[row][column] = checkNumber;
							if(solvePuzzle(puzzle, cages, incrmBoard)){
								return true;
							} else {
								puzzle[row][column] = 0;
								backtracks++;
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