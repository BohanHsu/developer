package cloneGraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

//  Definition for undirected graph.
class UndirectedGraphNode {
	int label;
	ArrayList<UndirectedGraphNode> neighbors;

	UndirectedGraphNode(int x) {
		label = x;
		neighbors = new ArrayList<UndirectedGraphNode>();
	}
};

public class Solution {
	HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
	Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();

	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
		if (node == null) {
			return null;
		}

		UndirectedGraphNode ugn = null;
		UndirectedGraphNode img = null;

		queue.add(node);

		while (!queue.isEmpty()) {
			ugn = queue.poll();
			// add a img of ugn
			copy(ugn);
			for (UndirectedGraphNode neighbor : ugn.neighbors){
				if (map.get(neighbor) == null){
					// add a img of neighbor
					copy(neighbor);
					queue.add(neighbor);
				}
			}
		}

		queue.add(node);

		HashSet<UndirectedGraphNode> copied = new HashSet<UndirectedGraphNode>();
		
		while (!queue.isEmpty()) {
			ugn = queue.poll();
			if (copied.contains(ugn)){
				continue;
			}
			img = map.get(ugn);
			
			for (UndirectedGraphNode neighbor : ugn.neighbors){
				img.neighbors.add(map.get(neighbor));
				if (!copied.contains(neighbor)){
					queue.add(neighbor);
				}
			}
			
			copied.add(ugn);
			
		}
		return this.map.get(node);
	}
	
	private void copy(UndirectedGraphNode node){
		map.put(node, new UndirectedGraphNode(node.label));
	}

	private void showGraph(UndirectedGraphNode node){
		UndirectedGraphNode ugn = node;
		Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
		HashSet<UndirectedGraphNode> shown = new HashSet<UndirectedGraphNode>();
		queue.add(ugn);
		while (!queue.isEmpty()){
			ugn = queue.poll();
			if (shown.contains(ugn)){
				continue;
			}
			System.out.print(ugn.label + ",");
			shown.add(ugn);
			for (UndirectedGraphNode neighbor : ugn.neighbors){
				System.out.print(neighbor.label + ",");
				if (!shown.contains(neighbor)){
					queue.add(neighbor);
				}
			}
			System.out.print("#");
			
		}
		System.out.println();
	}
	

	public static void main(String[] args) {
		// {0,1,5#1,2,5#2,3#3,4,4#4,5,5#5}
		UndirectedGraphNode n0 = new UndirectedGraphNode(0);
		UndirectedGraphNode n1 = new UndirectedGraphNode(1);
		UndirectedGraphNode n2 = new UndirectedGraphNode(2);
		UndirectedGraphNode n3 = new UndirectedGraphNode(3);
		UndirectedGraphNode n4 = new UndirectedGraphNode(4);
		UndirectedGraphNode n5 = new UndirectedGraphNode(5);
		n0.neighbors.add(n1);
		n0.neighbors.add(n5);
		n1.neighbors.add(n2);
		n1.neighbors.add(n5);
		n2.neighbors.add(n3);
		n3.neighbors.add(n4);
		n3.neighbors.add(n4);
		n4.neighbors.add(n5);
		n4.neighbors.add(n5);
		Solution s = new Solution();
		s.showGraph(n0);
		n0 = s.cloneGraph(n0);
		s.showGraph(n0);
	}
}