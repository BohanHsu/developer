package algo;

import java.util.ArrayList;

public class Graph {
	private boolean isDirected = false;

	private ArrayList<Integer> vertices;
	private ArrayList<Edge> edges;
	
	public Graph(boolean isDirected) {
		this.isDirected = isDirected;
		this.vertices = new ArrayList<Integer>();
		this.edges = new ArrayList<Edge>();
	}

	
}
