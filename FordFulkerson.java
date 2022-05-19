import java.util.*;
import java.io.File;

public class FordFulkerson {

	public static ArrayList<Integer> pathDFS(Integer source, Integer destination, WGraph graph){
		ArrayList<Integer> path = new ArrayList<Integer>();
		
		//record visited vertices
		ArrayList<Integer> visited = new ArrayList<Integer>();
		visited.add(source);
		
		//find the path recursively
		helper(source, destination, graph, visited, path);
		
		//check if we get the path connecting source and destination
		if(path.get(path.size()-1).equals(destination)) {
			return path;
		}else {
			return new ArrayList<Integer>();
		}

	}
	


	public static String fordfulkerson( WGraph graph){
		String answer="";
		int maxFlow = 0;
		
		int source = graph.getSource();
		int destination = graph.getDestination();
		
		WGraph residual = new WGraph(graph);
		
		//add backward edge
		for(Edge e : graph.getEdges()) {
			residual.addEdge(new Edge(e.nodes[1], e.nodes[0], 0));
		}
		
		//while there is an s-t path, augment the flow
		while(pathDFS(source, destination, residual).size() != 0) {
			ArrayList<Integer> path = pathDFS(source, destination, residual);
			Integer bottleneck = Integer.MAX_VALUE;
			
			//get the smallest residual capacity
			for(int j=0; j<path.size()-1; j++) {
				Edge edgeRSD = residual.getEdge(j, j+1);
				Integer weightRSD = edgeRSD.weight;
				if(weightRSD < bottleneck) {
					bottleneck = weightRSD;
				}
			}
			
			maxFlow += bottleneck;
			
			//update residual graph, forward and backward is the same
			for(int i=0; i<path.size()-1; i++) { 
				Edge forward = residual.getEdge(path.get(i), path.get(i+1));
				Integer forward_weight = forward.weight;
				Integer new_forward_weight = forward_weight - bottleneck;
				residual.setEdge(path.get(i), path.get(i+1), new_forward_weight);
				
				Edge backward = residual.getEdge(path.get(i+1), path.get(i));
				Integer backward_weight = backward.weight;
				Integer new_backward_weight = backward_weight + bottleneck;
				residual.setEdge(path.get(i+1), path.get(i), new_backward_weight);
				
			}
		}
		
		//update original graph
		for(Edge edge2 : graph.getEdges()) {
			Integer new_weight = edge2.weight - residual.getEdge(edge2.nodes[0], edge2.nodes[1]).weight;
			graph.setEdge(edge2.nodes[0], edge2.nodes[1], new_weight);
		}
		
		

		answer += maxFlow + "\n" + graph.toString();	
		return answer;
	}
	

	
	 public static void helper(Integer current_vertex, Integer destination, WGraph graph, ArrayList<Integer> visited, ArrayList<Integer> path) {
		 //add current vertex to the path
		 path.add(current_vertex);
		 
		 //if reach destination then return, if not, recursively find the path
		 if(current_vertex.equals(destination)) {
			 return;
		 }else {
			 for(int i=0; i<graph.getNbNodes(); i++) {
				 if(!visited.contains(i)) {
					 if((graph.getEdge(current_vertex, i) != null) && (graph.getEdge(current_vertex, i).weight > 0)) {
						 visited.add(i);
						 helper(i, destination, graph, visited, path);
						 //if reach destination than break, else remove current vertex
						 if(path.get(path.size() - 1).equals(destination)) {
							 break;
						 }else {
							 path.remove(path.size() - 1);
						 }
					 }
				 }
			 }
		 }
		 
	 }
	
	
	 public static void main(String[] args){
		String file = args[0];
		File f = new File(file);
		WGraph g = new WGraph(file);
	    System.out.println(fordfulkerson(g));
	 }
}