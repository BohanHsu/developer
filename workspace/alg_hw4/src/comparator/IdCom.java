package comparator;

import graph.Edge;

import java.util.Comparator;

public class IdCom implements Comparator<Edge>{

	@Override
	public int compare(Edge o1, Edge o2) {
		// TODO Auto-generated method stub
		return o1.getTgt() - o2.getTgt();
	}
	
}
