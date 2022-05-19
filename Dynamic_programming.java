import java.util.*;

public class Dynamic_programming {


	public static int weight(int[] plates) {
		
		if (plates.length == 0) {
			return 0;
		}
		
		int closest_sum = 0;
		int difference = 0;
		
		int num_items = plates.length + 1;
		int total_sum = 2000;
		/*for (int k=0; k<plates.length; k++) {
			total_sum += plates[k];
		}*/
		
		boolean[][] arr = new boolean[num_items][total_sum + 1];
		
		
		for (int b=0; b<num_items; b++) {
			arr[b][0] = true;
		}
		
		
		for (int i=0; i<num_items-1; i++) {
			for (int j=0; j<total_sum+1; j++) {
				if (j-plates[i] >= 0){
					if ( arr[i][j] == true || arr[i][j-plates[i]] == true ) {
						arr[i+1][j] = true;
					}else {
						arr[i+1][j] = false;
					}
				}else {
					if ( arr[i][j] == true) {
						arr[i+1][j] = true;
					}else {
						arr[i+1][j] = false;
					}
				}
			}
		}
		
		
		for (int m=0; m<total_sum+1; m++) {
			if(m <= 1000) {
				if (arr[num_items-1][m] == true) {
					closest_sum = m;
					
					if(m == 1000) {
						return closest_sum;
					}
					
					difference = Math.abs(closest_sum - 1000);
				}
				
			}else if(m>1000) {
				if (arr[num_items-1][m] == true) {
					if (Math.abs(m - 1000) <= difference) {
						closest_sum = m;
					}else {
						break;
					}
				}
			}
		}
		
		return closest_sum;
	}
	
	
	
}
