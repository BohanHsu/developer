package q3;

import java.util.HashMap;

public class Question {
	public static void main(String[] args) {
		
	String[] edge = new String[] { "1,3,19", "1,9,2", "2,1,20", "2,3,3",
			"3,7,4", "3,8,4", "4,2,7", "4,5,19", "4,7,19", "5,3,18", "5,6,21",
			"5,8,11", "6,4,12", "7,2,6", "7,6,21", "8,6,9", "9,2,22", "9,4,10" };
	Graph g = new Graph(edge);
	
	
	
	HashMap<Integer, Integer> xc = new HashMap<Integer,Integer>();
	HashMap<Integer, Integer> yc = new HashMap<Integer,Integer>();
	
	xc.put(1, 10);
	yc.put(1, 0);
	
	xc.put(2, 14);
	yc.put(2, 0);
	
	xc.put(3, 16);
	yc.put(3, 2);
	
	xc.put(4, 18);
	yc.put(4, 6);
	
	xc.put(5, 16);
	yc.put(5, 10);
	
	xc.put(6, 12);
	yc.put(6, 12);
	
	xc.put(7, 8);
	yc.put(7, 10);
	
	xc.put(8, 6);
	yc.put(8, 6);
	
	xc.put(9, 8);
	yc.put(9, 2);
	
	GraphLatex gl = new GraphLatex(g, xc, yc, null);
	GraphLatex gl2 = new GraphLatex(xc, yc, null);
	
//	int tc = g.dinic(5, 2,gl, gl2);
	int tc = g.dinic(5, 2,null, null);
	System.out.println(tc);
	}
}
