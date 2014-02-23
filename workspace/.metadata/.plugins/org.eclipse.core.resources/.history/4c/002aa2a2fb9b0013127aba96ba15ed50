package questions;

import java.util.ArrayList;

import graph.Edge;
import graph.Graph;

public class Q1 {
	public static void main(String[] args) {
		String[] edges = new String[]{"1,3,4",
				"1,5,8",
				"1,6,2",
				"1,8,8",
				"2,3,3",
				"2,4,10",	
				"2,8,10",
				"2,9,6",
				"3,4,9",
				"3,5,11",	
				"3,6,6",	
				"3,7,5",
				"4,5,6",
				"4,6,5",
				"4,7,6",
				"4,8,8",
				"5,6,9",
				"5,7,7",
				"5,9,7",
				"6,7,4",
				"7,8,4",
				"7,9,9",
				"8,9,10"};
		Graph g = new Graph(edges);
		
		 ArrayList<Edge> selectedEdges = g.kruskal();
		 
		 for (Edge edge : selectedEdges) {
			System.out.println(edge);
		}
	}
}

/*

"1,3,4",
"1,5,8",
"1,6,2",
"1,8,8",
"2,3,3",
"2,4,10",	
"2,8,10",
"2,9,6",
"3,4,9",
"3,5,11",	
"3,6,6",	
"3,7,5",
"4,5,6",
"4,6,5",
"4,7,6",
"4,8,8",
"5,6,9",
"5,7,7",
"5,9,7",
"6,7,4",
"7,8,4",
"7,9,9",
"8,9,10"

*/