package comparator;

import graph.Node;

import java.util.Comparator;

public class PostTimeCom implements Comparator<Node> {

	@Override
	public int compare(Node o1, Node o2) {
		// TODO Auto-generated method stub
		return o2.getPostVisit() - o1.getPostVisit();
	}

}