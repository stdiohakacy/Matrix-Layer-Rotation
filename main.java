import java.util.ArrayList;
import java.util.Scanner;

public class Solution {

    public static class Cell {
		public int row;
		public int column;

		public Cell(int row, int column) {
			super();
			this.row = row;
			this.column = column;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Input the number of rotate: ");
		int r = sc.nextInt();
		int [][]matrix = { 
			{1, 2, 3, 4}, 
            {5, 6, 7, 8}, 
            {9, 10, 11, 12}, 
            {13, 14, 15, 16} 
        };
		int[][] rotatedMatrix = new int[4][4];
		
		sc.close();
		
		int numberOfLayers = Math.min(4, 4) / 2;
		
		for (int i = 0; i < numberOfLayers; i++) {
			// We compute the row and column boundaries of the current iteration
			int rowLowerLimit = i;
			int columnLowerLimit = i;
			int rowUpperLimit = 4 - 1 - i;
			int columnUpperLimit = 4 - 1 - i;
//			0 3 0 3
			
			// We compute the path length of the current iteration, i.e. the number
			// of cells of the current layer
			int pathLength = ((4 - (i * 2)) * 2) + ((4 - (i * 2)) * 2) - 4;
//			12
			ArrayList<Cell> path = new ArrayList<Cell>();
			
			int currentRowIndex = i;
			int currentColumnIndex = i;
			
//			0 0
			boolean down = true;
			boolean right = false;
			boolean up = false;
			boolean left = false;
			
			for (int j = 0; j < pathLength; j++) {
				path.add(new Cell(currentRowIndex, currentColumnIndex));
				
				if (currentRowIndex == rowUpperLimit && currentColumnIndex == columnLowerLimit) {
					down = false;
					right = true;
				}
				
				if (currentRowIndex == rowUpperLimit && currentColumnIndex == columnUpperLimit) {
					right = false;
					up = true;
				}
				
				if (currentRowIndex == rowLowerLimit && currentColumnIndex == columnUpperLimit) {
					up = false;
					left = true;
				}
				
				if(down){
					currentRowIndex = currentRowIndex + 1;
				}
				
				if(right){
					currentColumnIndex = currentColumnIndex + 1;
				}
				
				if(up){
					currentRowIndex = currentRowIndex - 1;
				}
				
				if(left){
					currentColumnIndex = currentColumnIndex - 1;
				}
			}
			
			for (int j = 0; j < pathLength; j++) {
				Cell currentCell = path.get(j);
				int numberToBeMoved = matrix[currentCell.row][currentCell.column];
				Cell newCell = path.get((j + r) % pathLength);
				rotatedMatrix[newCell.row][newCell.column] = numberToBeMoved;
			}
		}
		
//		for (int i = 0; i < 4; i++) {
//			for (int j = 0; j < 4; j++) {
//				System.out.print(rotatedMatrix[i][j] + " ");
//			}
//			System.out.println();
//		}

	}
}
