import java.util.*;


public class Board_game_recursion {
	
	
	public static int[] game(String[][] board){
		/* add all balls' coordinate to an ArrayList*/
		ArrayList<int[]> balls = new ArrayList<int[]>();
		for (int i=0; i<=4; i++) {
			for (int j=0; j<=8; j++) {
				if (board[i][j].equals("o")) {
					int[] coordinate = new int[]{i, j};
					balls.add(coordinate);
				}
			}
		}
		
		final int num_balls_initial = balls.size();
		
		int[] output = recursiveGame(board, num_balls_initial);
			
		return output;
	}
	
	
	public static int[] recursiveGame(String[][] board, int num_balls_initial) {
		int[] output = new int[2];
		output[0] = 10000;
		
		
		/* add all balls' coordinate to an ArrayList*/
		ArrayList<int[]> balls = new ArrayList<int[]>();
		for (int i=0; i<=4; i++) {
			for (int j=0; j<=8; j++) {
				if (board[i][j].equals("o")) {
					int[] coordinate = new int[]{i, j};
					balls.add(coordinate);
				}
			}
		}
		
		
		boolean isBasecase = true;
		
		for (int[] coordinate : balls) {
			if (canJumpUp(coordinate, board) || canJumpDown(coordinate, board) || canJumpRight(coordinate, board) || canJumpLeft(coordinate, board)) {
				isBasecase = false;
			}
		}
		
		if (isBasecase == true) {
			output[0] = balls.size();
			output[1] = num_balls_initial - balls.size();
			return output;
		}
		
		
		/* loop through each ball to check if it can jump */
		for (int[] coordinate : balls) {
			
			if (canJumpUp(coordinate, board)) {
				int row = coordinate[0];
				int col = coordinate[1];
				String[][] newBoard = deepcopy(board);
				newBoard[row][col] = ".";
				newBoard[row-1][col] = ".";
				newBoard[row-2][col] = "o";
				
				int[] newOutput = recursiveGame(newBoard, num_balls_initial);;
				
				if (newOutput[0] < output[0]) {
					output = newOutput;
				}
			}
			
			if (canJumpDown(coordinate, board)) {
				int row = coordinate[0];
				int col = coordinate[1];
				String[][] newBoard = deepcopy(board);
				newBoard[row][col] = ".";
				newBoard[row+1][col] = ".";
				newBoard[row+2][col] = "o";
				
				int[] newOutput = recursiveGame(newBoard, num_balls_initial);;
				
				if (newOutput[0] < output[0]) {
					output = newOutput;
				}
			}
			
			if (canJumpRight(coordinate, board)) {
				int row = coordinate[0];
				int col = coordinate[1];
				String[][] newBoard = deepcopy(board);
				newBoard[row][col] = ".";
				newBoard[row][col+1] = ".";
				newBoard[row][col+2] = "o";
				
				int[] newOutput = recursiveGame(newBoard, num_balls_initial);;
				
				if (newOutput[0] < output[0]) {
					output = newOutput;
				}
			}
			
			if (canJumpLeft(coordinate, board)) {
				int row = coordinate[0];
				int col = coordinate[1];
				String[][] newBoard = deepcopy(board);
				newBoard[row][col] = ".";
				newBoard[row][col-1] = ".";
				newBoard[row][col-2] = "o";
				
				int[] newOutput = recursiveGame(newBoard, num_balls_initial);;
				
				if (newOutput[0] < output[0]) {
					output = newOutput;
				}
			}
			
		}
		return output;
		
	}
	
	
	/* define a helper function to check can jump or not*/
	public static boolean canJumpUp(int[] coordinate, String[][] board) {
		int row = coordinate[0];
		int col = coordinate[1];
		if (row >= 2) {
			if (board[row - 1][col].equals("o")) {
				if (board[row - 2][col].equals(".")) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean canJumpDown(int[] coordinate, String[][] board) {
		int row = coordinate[0];
		int col = coordinate[1];
		if(row <= 2) {
			if (board[row + 1][col].equals("o")) {
				if (board[row + 2][col].equals(".")) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean canJumpRight(int[] coordinate, String[][] board) {
		int row = coordinate[0];
		int col = coordinate[1];
		if (col <= 6) {
			if(board[row][col + 1].equals("o")) {
				if(board[row][col + 2].equals(".")){
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean canJumpLeft(int[] coordinate, String[][] board) {
		int row = coordinate[0];
		int col = coordinate[1];
		if (col >= 2) {
			if (board[row][col - 1].equals("o")) {
				if(board[row][col - 2].equals(".")){
						return true;
				}
			}
		}
		return false;
	}
	
	public static String[][] deepcopy(String[][] board){
		String[][] newBoard = new String[5][9];
		for (int i=0; i<=4; i++) {
			for (int j=0; j<=8; j++) {
				newBoard[i][j] = board[i][j];
			}
		}
		return newBoard;
	}

}
