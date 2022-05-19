import java.util.*;


public class Q1 {

	public static int find_exit(String[][][] jail) {
		
		//store each cell's visit state
		boolean[][][] visit_state = new boolean[jail.length][jail[0].length][jail[0][0].length];
		
		//store each cell's distance from source
		int[][][] distance = new int[jail.length][jail[0].length][jail[0][0].length];
		
		//create a queue to do BFS
		LinkedList<int[]> queue = new LinkedList<int[]>();
		
		//find the source of the input matrix 
		boolean found = false;
		for(int i = 0; i < jail.length; i++) {
			for(int j = 0; j < jail[0].length; j++) {
				for(int k = 0; k < jail[0][0].length; k++) {
					if(jail[i][j][k].equals("S")){
						int[] source = {i, j, k};
						visit_state[i][j][k] = true;
						distance[i][j][k] = 0;
						queue.add(source);
						found = true;
						break;
					}
				}
				if(found) {
					break;
				}
			}
			if(found) {
				break;
			}
		}
		
		//traversal loop
		while(!queue.isEmpty()) {
			int[] current_vertex = queue.removeFirst();
			int level = current_vertex[0];
			int row = current_vertex[1];
			int column = current_vertex[2];
			
			//check if can move up
			if(can_move(level+1, row,column, jail, visit_state)) {
				int[] next_vertex = {level+1, row, column};
				queue.add(next_vertex);
				visit_state[level+1][row][column] = true;
				distance[level+1][row][column] = distance[level][row][column] + 1;
				
				if(jail[level+1][row][column].equals("E")) {
					return distance[level+1][row][column];
				}
			}
			
			//check if can move down
			if(can_move(level-1, row, column, jail, visit_state)) {
				int[] next_vertex = {level-1, row, column};
				queue.add(next_vertex);
				visit_state[level-1][row][column] = true;
				distance[level-1][row][column] = distance[level][row][column] + 1;
				
				if(jail[level-1][row][column].equals("E")) {
					return distance[level-1][row][column];
				}
			}
			
			//check if can move north
			if(can_move(level, row+1, column, jail, visit_state)) {
				int[] next_vertex = {level, row+1, column};
				queue.add(next_vertex);
				visit_state[level][row+1][column] = true;
				distance[level][row+1][column] = distance[level][row][column] + 1;
				
				if(jail[level][row+1][column].equals("E")) {
					return distance[level][row+1][column];
				}
			}
			
			//check if can move south
			if(can_move(level, row-1, column, jail, visit_state)) {
				int[] next_vertex = {level, row-1, column};
				queue.add(next_vertex);
				visit_state[level][row-1][column] = true;
				distance[level][row-1][column] = distance[level][row][column] + 1;
				
				if(jail[level][row-1][column].equals("E")) {
					return distance[level][row-1][column];
				}
			}
				
			//check if can move east
			if(can_move(level, row, column+1, jail, visit_state)) {
				int[] next_vertex = {level, row, column+1};
				queue.add(next_vertex);
				visit_state[level][row][column+1] = true;
				distance[level][row][column+1] = distance[level][row][column] + 1;
				
				if(jail[level][row][column+1].equals("E")) {
					return distance[level][row][column+1];
				}
			}
			
			//check if can move west
			if(can_move(level, row, column-1, jail, visit_state)) {
				int[] next_vertex = {level, row, column-1};
				queue.add(next_vertex);
				visit_state[level][row][column-1] = true;
				distance[level][row][column-1] = distance[level][row][column] + 1;
				
				if(jail[level][row][column-1].equals("E")) {
					return distance[level][row][column-1];
				}
			}
			
		}
		
		return -1;
	}
	
	
	
	//check can move to the cell or not 
	public static boolean can_move(int level_num, int row_num, int column_num, String[][][] jail, boolean[][][] visit_state){
		if(level_num < 0 || level_num >= jail.length) {
			return false;
		}else if(row_num < 0 || row_num >= jail[0].length){
			return false;
		}else if(column_num < 0 || column_num >= jail[0][0].length) {
			return false;
		}else if(!(jail[level_num][row_num][column_num].equals(".")||jail[level_num][row_num][column_num].equals("E"))) {
			return false;
		}else if(visit_state[level_num][row_num][column_num]) {
			return false;
		}else {
			return true;
		}
	}
	

	
	public static void main(String[] args) {

	}

}