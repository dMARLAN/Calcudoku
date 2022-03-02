
public class SolverFuncs {

	public static int checks = 0;
	public static int backtracks = 0;

	private static boolean checkValid(int[][] puzzle, Cage[] cages, int row, int column, int checkNumber, int[][] incrmBoard){
		return checkRowsValid(puzzle, row, checkNumber) &&
        checkColumnsValid(puzzle, column, checkNumber) &&
		checkCagesValid(puzzle, cages, row, column, checkNumber, incrmBoard);
	}
	
	private static boolean checkCagesValid(int[][] puzzle, Cage[] cages, int row, int column, int checkNumber, int[][] incrmBoard){
		int cageToValidate = 0;
		int cageSum = 0;
		int cellsOccupied = 0;
		
		for(int i = 0; i < cages.length; i++) {
			for(int j = 0; j < cages[i].numberOfCells; j++) {
				if(cages[i].cellPos[j] == incrmBoard[row][column]) {
					cageToValidate = i;
				}
			}
		}
		
		for(int i = 0; i < cages[cageToValidate].numberOfCells; i++) {
			for(int j = 0; j < puzzle.length; j++) {
				for(int k = 0; k < puzzle.length; k++) {
					if(incrmBoard[j][k] == cages[cageToValidate].cellPos[i]) {
						if(j==row && k==column){
							cageSum += checkNumber;
							cellsOccupied++;
						} else if(puzzle[j][k] > 0) {
							cageSum += puzzle[j][k];
							cellsOccupied++;
						}
					}
				}
			}
		}

		if(cellsOccupied == cages[cageToValidate].numberOfCells){
			if(cageSum != cages[cageToValidate].cageSum){
				return false;
			}
		}

		if(cellsOccupied < cages[cageToValidate].numberOfCells){
			if(cageSum >= cages[cageToValidate].cageSum){
				return false;
			}
		}
		
		return true;
	}

	private static boolean checkRowsValid(int[][] puzzle, int row, int checkNumber){
		for (int i = 0; i < puzzle.length; i++){
			if(puzzle[row][i] == checkNumber){
				return false;
			}
		}
		return true;
	}

	private static boolean checkColumnsValid(int[][] puzzle, int column, int checkNumber){
		for (int i = 0; i < puzzle.length; i++){
			if(puzzle[i][column] == checkNumber){
				return false;
			}
		}
		return true;
	}
	
	public static void solvePuzzle(int[][] puzzle, Cage[] cages, int[][] incrmBoard, int nextRow, int nextColumn){
		for (int row = nextRow; row < puzzle.length; row++){
			for (int column = nextColumn; column < puzzle.length; column++){
				for (int checkNumber = puzzle[row][column]+1; checkNumber <= puzzle.length+1; checkNumber++) {
					if(checkNumber!=6) checks++;
					if(checkValid(puzzle, cages, row, column, checkNumber, incrmBoard) && checkNumber != 6) {
						puzzle[row][column] = checkNumber;
						if(column == 4) {
							nextColumn = 0;
						} else {
							nextColumn = column;
							nextRow = row;
						}
						break;
					} else if(checkNumber >= puzzle.length) {
						backtracks++;
						puzzle[row][column] = 0;
						if(column == 0) {
							nextColumn = 4;
							nextRow = row-1;
						} else {
							nextColumn = column-1;
						}
						solvePuzzle(puzzle, cages, incrmBoard, nextRow, nextColumn);
						return;
					}
				}
			}
		}
	}
}