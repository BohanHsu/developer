package comparator;

import graph.Edge;

import java.util.Comparator;

public class EdgeSTComp implements Comparator<Edge>{

	@Override
	public int compare(Edge o1, Edge o2) {
		return (o1.getSrc() * 100 + o1.getTgt()) - (o2.getSrc() * 100 + o2.getTgt());
	}
	
}
