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

	public static void getCages(Cage[] cages){
		System.out.println();
		for ( int c = 0; c < cages.length; c++){
			cages[c].displayCageData(c);
		}
	}

	public static boolean checkValid(int[][] puzzle, Cage[] cages, int row, int column, int checkNumber){
		return !rowsValid(puzzle, row, checkNumber) &&
        !columnsValid(puzzle, column, checkNumber) &&
		!cagesValid(puzzle, cages);
	}
	
	public static boolean cagesValid(int[][] puzzle, Cage[] cages){

		for(int c = 0; c < Main.GRID_SIZE; c++){
			int numberOfCells = cages[c].numberOfCells;
			int cellSum = cages[c].cageSum;
			int[] cellPos = cages[c].cellPos;
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

	public static boolean solvePuzzle(int[][] puzzle, Cage[] cages){
		for (int row = 0; row < Main.GRID_SIZE; row++){
			for (int column = 0; column < Main.GRID_SIZE; column++){
				if(puzzle[row][column] == 0){
					for (int checkNumber = 1; checkNumber <= Main.GRID_SIZE; checkNumber++){
						if(checkValid(puzzle, cages, row, column, checkNumber)){
							puzzle[row][column] = checkNumber;

							/*System.out.println();
							System.out.println("=== Puzzle ===");
							for(int i = 0; i < Main.GRID_SIZE; i++){
								for(int j = 0; j < Main.GRID_SIZE; j++){
									System.out.print(puzzle[i][j]);
								}
								System.out.println();
							}*/

							if(solvePuzzle(puzzle, cages)){
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