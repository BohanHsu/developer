package q2;

import java.util.HashMap;

import q3.Graph;
import q3.GraphLatex;

public class Q2 {
	public static void main(String[] args) {

		String[] edge = new String[] { "1,2,2", "1,3,2", "1,4,3", "2,5,2",
				"2,6,2", "3,7,2", "3,8,2", "4,6,3", "4,7,3", "5,9,2", "6,9,1",
				"7,9,1", "8,9,2" };
		Graph g = new Graph(edge);
		
		HashMap<Integer, Integer> xc = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> yc = new HashMap<Integer, Integer>();
		HashMap<Integer, String> label = new HashMap<Integer, String>();
		
		xc.put(1, 5);
		yc.put(1, 5);
		
		xc.put(2, 8);
		yc.put(2, 12);
		
		xc.put(3, 8);
		yc.put(3, 8);
		
		xc.put(4, 8);
		yc.put(4, 4);
		
		xc.put(5, 12);
		yc.put(5, 14);
	
		xc.put(6, 12); // 12
		yc.put(6, 10); // 6
		
		xc.put(7, 12);
		yc.put(7, 6);
		
		xc.put(8, 12); // 12
		yc.put(8, 2); // 2
		
		xc.put(9, 15);
		yc.put(9, 5);
		
		label.put(1, "S");
		label.put(2, "X");
		label.put(3, "Y");
		label.put(4, "Z");
		label.put(5, "A");
		label.put(6, "B");
		label.put(7, "C");
		label.put(8, "D");
		label.put(9, "T");
		
		GraphLatex gl1 = new GraphLatex(g, xc, yc, label);
		GraphLatex gl2 = new GraphLatex(xc, yc, label);
		
		
//		int tc = g.dinic(1, 9,gl1,gl2);
		int tc = g.dinic(1, 9,null,null);
		System.out.println(tc);
	}
}