package questions;

import graph.Dijkstra;
import graph.Graph;
import heap.NaryTree;

public class Q2 {
	public static void main(String[] args) {
		String[] sa = new String[]{"1,2,1.0",	
				"3,1,2.2",	
				"3,2,4.7",	
				"5,1,9.9",
				"5,2,1.3",	
				"5,3,2.6",	
				"5,4,4.1",	
				"5,8,1.1",
				"6,3,2.7",	
				"6,5,4.3",	
				"6,7,5.2",	
				"6,8,3.8",
				"7,1,8.4",	
				"7,2,1.7",	
				"7,4,1.3",	
				"7,5,3.5",
				"7,8,4.3",	
				"8,2,4.7",	
				"8,3,3.9",	
				"8,4,2.4"};
		
		Graph g = new Graph(sa);
		
		NaryTree bt = new NaryTree(3);
		
		Dijkstra dijk = new Dijkstra(g, bt);
		
//		dijk.dijkstra(6);
		dijk.dijkstraNoOutput(6);
	}
}
