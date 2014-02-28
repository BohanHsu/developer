package algo_probs;

import java.util.HashSet;

public class CommonLeastAncestor {
	// the top down approach
	public Node claTD(Node root, Node a, Node b){
		if (root == null){
			return null;
		}
		
		if (root == a || root == b){
			return root;
		}
		
		Node l = root.left;
		Node r = root.right;
		
		l = claTD(l,a,b);
		r = claTD(r,a,b);
		
		if (l != null && r != null){
			return root;
		}else{
			if (l == null){
				return r;
			}else{
				return l;
			}
		}
	}
	
	// a buttom up approach
	public Node claBU(Node root, Node a, Node b){
		HashSet<Node> aAse = new HashSet<Node>();
		HashSet<Node> bAse = new HashSet<Node>();
		
		while(!(a == null && b.parent == null)){
			if (a != null){
				aAse.add(a);
				a = a.parent;
			}
			if (aAse.contains(b)){
				return b;
			}
			if(b != null){
				bAse.add(b);
				b = b.parent;
			}
			if (bAse.contains(a)){
				return a;
			}
		}
		return null;
	}
	
}

class Node{
	int val;
	public Node(int val) {
		this.val = val;
	}
	Node parent;
	Node left;
	Node right;
}
