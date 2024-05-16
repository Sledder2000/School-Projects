import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

import gameBoards.SudokuGUI;
public class SudokuBoard {
	
	private SudokuGUI gui = new SudokuGUI();
	private int[][] board = new int[9][9];
	
	public static void main(String[] args) throws FileNotFoundException { 
		SudokuBoard sb = new SudokuBoard();
		sb.loadPuzzle("Sudoku1.txt");
		boolean hm = sb.solvePuzzle();
		if (hm == true) {
			System.out.println("success");
		} else {
			System.out.println("failure");
		}
		sb.drawPuzzle();
	}
	
	public void loadPuzzle(String str) throws FileNotFoundException {
		Scanner r = new Scanner(new File(str));
		int row = 0;
		while (r.hasNextLine()) {
			String line = r.nextLine();
			if (line != "") {
				int col = 0;
				for (char c : line.toCharArray()) {
					if (c >= '1' && c <= '9') {
						board[row][col] = Character.getNumericValue(c);
					}
					if (c != ' ') {
						col++;
					}
				}
				gui.draw(board);
				row++;
			}
		}
	}

	public boolean solvePuzzle() {
		boolean hmm = solvePuzzle(0, 0);
		return false;
	}
	public boolean solvePuzzle(int row, int col) {
		row = row + (col + 1) / 9;
		col = (col + 1) % 9;
		return false;
	}
	
	public void drawPuzzle() {
		gui.draw(board);
	}
	
}
