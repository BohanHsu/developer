package comparator;

import graph.Edge;

import java.util.Comparator;

public class EdgeWeightComparator implements Comparator<Edge> {

	@Override
	public int compare(Edge o1, Edge o2) {
		return (int)(o1.getWeight() - o2.getWeight()) * 10000 + (o1.getSrc() - o2.getSrc()) * 10 + (o1.getTgt() - o2.getTgt());
	}
	
}