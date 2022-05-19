import java.util.*;


public class Q2 {
	
	public static int rings(Hashtable<Integer, ArrayList<Integer>> graph, int[]location) {
		
		//an array to store in-degree of every vertex
		int[] indegree = new int[graph.size()];
		int[] indegree2 = new int[graph.size()];
		
		//calculate in-degree of each vertex
		for(int i=0; i<graph.size(); i++) {
			ArrayList<Integer> neighbors = graph.get(Integer.valueOf(i));
			
			for(Integer j : neighbors) {
				indegree[j] = indegree[j] + 1;
				indegree2[j] = indegree2[j]+1;
			}
		}
		
		//perform topological sort start from different plant
		int from_earth = start_from_earth(graph, location, indegree);
		int from_asgard = start_from_asgard(graph, location, indegree2);
		
		//get the minimum value of start the two planets
		int minimum = Math.min(from_earth, from_asgard);
		
		return minimum;
	}
	
	
	
	public static int start_from_earth(Hashtable<Integer, ArrayList<Integer>>graph, int[]location, int[]indegree) {
		//a queue to store all in-degree 0 vertices
		LinkedList<Integer> queue = new LinkedList<Integer>();
		
		//a count to record number of transportation 
		int count = 0;
		int pre_location = 1;
		
		//enqueue vertices with in-degree 0
		for(int i=0; i<graph.size(); i++) {
			if(indegree[i] == 0) {
				queue.add(Integer.valueOf(i));
			}
		}
		
		//check whether can start from earth
		boolean can_start = false;
		for(int j=0; j<queue.size(); j++) {
			if(location[queue.get(j)] == 1) {
				can_start = true;
				break;
			}
		}
		if(!can_start) {
			return 10000;
		}
		
		//topological sort
		while(!queue.isEmpty()) {
			Integer vertex = Integer.valueOf(10000);
			
			//check whether can continue working on the same planet
			for(int k=0; k<queue.size(); k++) {
				if(location[queue.get(k)] == pre_location) {
					vertex = queue.get(k);
					break;
				}
			}
			//if not, move to another planet and increase transportation times
			if(vertex.equals(Integer.valueOf(10000))) {
				pre_location = location[queue.getFirst()];
				count++;
				vertex = queue.getFirst();
			}
			
			//remove a vertex, decrease in-degree by 1 for all its neighbors
			queue.remove(Integer.valueOf(vertex));
            ArrayList<Integer> neighbors = graph.get(Integer.valueOf(vertex));
			for(int m : neighbors) {
				indegree[m] = indegree[m] - 1;
				if(indegree[m] == 0) {
					queue.add(Integer.valueOf(m));
				}
			}
		}			
		return count;
	}
	
	
	
	public static int start_from_asgard(Hashtable<Integer, ArrayList<Integer>>graph, int[]location, int[]indegree) {
		//a queue to store all in-degree 0 vertices
		LinkedList<Integer> queue = new LinkedList<Integer>();
				
		//a count to record number of transportation 
		int count = 0;
		int pre_location = 2;
				
		//enqueue vertices with in-degree 0
		for(int i=0; i<graph.size(); i++) {
			if(indegree[i] == 0) {
				queue.add(Integer.valueOf(i));
			}
		}
				
		//check whether can start from earth
		boolean can_start = false;
		for(int j=0; j<queue.size(); j++) {
			if(location[queue.get(j)] == 2) {
				can_start = true;
				break;
			}
		}
		if(!can_start) {
			return 10000;
		}
				
		//topological sort
		while(!queue.isEmpty()) {
			Integer vertex = Integer.valueOf(10000);
			//check whether can continue working on the same planet
			for(int k=0; k<queue.size(); k++) {
				if(location[queue.get(k)] == pre_location) {
					vertex = queue.get(k);
					break;
				}
			}
			//if not, move to another planet and increase transportation times
			if(vertex.equals(Integer.valueOf(10000))) {
				pre_location = location[queue.getFirst()];
				count++;
				vertex = queue.getFirst();
			}
					
			//remove a vertex, decrease in-degree by 1 for all its neighbors
			queue.remove(Integer.valueOf(vertex));
            ArrayList<Integer> neighbors = graph.get(Integer.valueOf(vertex));
			for(int m : neighbors) {
				indegree[m] = indegree[m] - 1;
				if(indegree[m] == 0) {
					queue.add(Integer.valueOf(m));
				}
			}
		}			
			return count;
	}

	
	
	public static void main(String[] args) {
		/*int[] location = {1,2,1,2,1};
		Hashtable<Integer, ArrayList<Integer>> graph = new Hashtable<Integer, ArrayList<Integer>>();
		
		ArrayList<Integer> zero = new ArrayList<Integer>();
		zero.add(Integer.valueOf(1));
		zero.add(Integer.valueOf(2));
		graph.put(Integer.valueOf(0), zero);
		
		ArrayList<Integer> one = new ArrayList<Integer>();
		one.add(Integer.valueOf(3));
		one.add(Integer.valueOf(4));
		graph.put(Integer.valueOf(1), one);
		
		ArrayList<Integer> two = new ArrayList<Integer>();
		two.add(Integer.valueOf(3));
		two.add(Integer.valueOf(4));
		graph.put(Integer.valueOf(2), two);
		
		graph.put(Integer.valueOf(3), new ArrayList<Integer>());
		graph.put(Integer.valueOf(4), new ArrayList<Integer>());
		
		int w = rings(graph,location);
		System.out.println(w);*/
		
		
		

	}

}
