package comparator;

import graph.Node;

import java.util.Comparator;

public class NodeIdComp implements Comparator<Node> {

	@Override
	public int compare(Node o1, Node o2) {
		// TODO Auto-generated method stub
		return o1.getId() - o2.getId();
	}

	
	
}
