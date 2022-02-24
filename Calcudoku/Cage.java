import java.util.Arrays;

public class Cage {
	int cageSum;
	int numberOfCells;
	int[] cellPos;

	public Cage(int cageSum, int numberOfCells, int[] cellPos){
		this.cageSum = cageSum;
		this.numberOfCells = numberOfCells;
		this.cellPos = cellPos;
	}

	public void displayCageData(int cageNum){
		System.out.println("   Cage " +cageNum+ ": Sum = " +cageSum+ ", Cells = " +numberOfCells+ ", Positions = " + Arrays.toString(cellPos));
	}

}