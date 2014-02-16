package questions;

import graph.Graph;

public class Q3 {
	public static void main(String[] args) {
		String[] sa = new String[]{"1,2,-2",
				"1,5,-2",
				"1,6,-3",
				"1,8,-1",
				"2,6,7",
				"2,8,4",
				"3,2,2",
				"3,4,5",
				"3,7,9",
				"4,7,4",
				"5,7,5",
				"7,8,-1",
				"8,2,2",
				"8,5,8",
				"8,6,3",
				"8,7,7"};
		
		Graph g = new Graph(sa);
//		g.moore(3);
		g.moore2(3);
	}
}