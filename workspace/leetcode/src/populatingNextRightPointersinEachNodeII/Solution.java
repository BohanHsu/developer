package populatingNextRightPointersinEachNodeII;

import java.util.LinkedList;

class TreeLinkNode {
	int val;
	TreeLinkNode left, right, next;

	TreeLinkNode(int x) {
		val = x;
	}
}

public class Solution {
	public void connect(TreeLinkNode root) {
		if (root == null){
	        return;
	    }
		TreeLinkNode n = null; // n is the current node
		TreeLinkNode head = null; // is the head of n's father's line
		TreeLinkNode f = null; // f is n's father

		head = root;
		n = getFirstChildOfNode(head);
		f = root;

		TreeLinkNode u = null;
		TreeLinkNode m = null;
		while (n != null) {
			//System.out.println("==========");
			u = getNextFatherByFatherAndNode(f, n);
			//putsValue(n);
			//putsValue(u);
			if (f == u) {
				n.next = f.right;
				n = n.next;
			} else if (u == null) {
				n.next = null;
				f = getFirstNodeWithChild(head);
				if (f != null) {
					//f = getFirstChildOfNode(m);
					n = getFirstChildOfNode(f);
					head = n;
				} else {
					n = null;
				}
			} else {
				n.next = getFirstChildOfNode(u);
				f = u;
				n = n.next;
			}

		}
	}

	private TreeLinkNode getFirstChildOfNode(TreeLinkNode n) {
		if (n.left != null) {
			return n.left;
		} else {
			return n.right;
		}
	}

	private TreeLinkNode getNextFatherByFatherAndNode(TreeLinkNode fa,
			TreeLinkNode n) {
		if (n == fa.left && fa.right != null) {
			return fa;
		}
		TreeLinkNode uncle = getFirstNodeWithChild(fa.next);
		return uncle;
	}

	private TreeLinkNode getFirstNodeWithChild(TreeLinkNode head) {
		if (head == null) {
			return null;
		}
		while (true) {
			if (head.left != null || head.right != null) {
				return head;
			} else {
				if (head.next != null) {
					head = head.next;
				} else {
					return null;
				}
			}
		}
	}
	
	private void putsValue(TreeLinkNode n){
		if (n == null){
			System.out.println("null");
		}else{
			System.out.println(n.val);
		}
	}
	
	public static void traverse(TreeLinkNode n){
		LinkedList<TreeLinkNode> queue = new LinkedList<TreeLinkNode>();
		queue.add(n);
		while(!queue.isEmpty()){
			n = queue.poll();
			if (n.next == null){
				System.out.println(n.val + " -> null");
			} else {
				System.out.println(n.val + " -> " + n.next.val);
			}
			if (n.left != null){
				queue.add(n.left);
			} 
			if (n.right != null){
				queue.add(n.right);
			}
		}
	}

	public static void main(String[] args) {
		Solution s = new Solution();
		TreeLinkNode t1 = new TreeLinkNode(3);
		TreeLinkNode t2 = new TreeLinkNode(9);
		TreeLinkNode t3 = new TreeLinkNode(20);
		TreeLinkNode t4 = new TreeLinkNode(4);
		TreeLinkNode t5 = new TreeLinkNode(5);
		TreeLinkNode t6 = new TreeLinkNode(15);
		TreeLinkNode t7 = new TreeLinkNode(7);
		t1.left = t2;
		t1.right = t3;
		//t2.left = t4;
		//t2.right = t5;
		t3.left = t6;
		t3.right = t7;
		//t3.right = t5;
		s.connect(t1);
		
		System.out.println("++++++++++");
		traverse(t1);
	}

}