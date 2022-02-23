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
		System.out.print("Cage " +cageNum+ " cageSum = " +cageSum+ " numberOfCells = " +numberOfCells+ " cellPos = " + Arrays.toString(cellPos));
		System.out.println();
	}

}