package questions;

import java.util.ArrayList;

import graph.Graph;
import graph.Node;

public class Q4 {
	public static void main(String[] args) {
		String[] sa = new String[]{"1,3,2.4",
				"1,6,-0.3",
				"1,7,-6.9",
				"1,10,7.7",
				"2,5,1.5",
				"2,6,-6.0",
				"3,4,8.3",
				"3,8,7.1",
				"3,9,-3.8",
				"4,2,-7.0",
				"4,6,-5.1",
				"4,9,-4.7",
				"5,9,-9.8",
				"8,5,5.0",
				"8,7,8.2",
				"8,9,-3.1",
				"9,7,1.4",
				"10,2,6.1",
				"10,4,-8.5",
				"10,9,5.2"};
		
		Graph g = new Graph(sa);
		g.dfs(1);
		ArrayList<Node> topSort = g.topologicalSort();
		for (Node node : topSort) {
			System.out.println(node.getId()+"\t"+node.getPreVisit()+"\t"+node.getPostVisit());
		}
		
		g.singleSourceShortestPath();
	}
}