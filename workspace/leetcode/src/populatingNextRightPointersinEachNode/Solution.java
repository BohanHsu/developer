package populatingNextRightPointersinEachNode;

//* Definition for binary tree with next pointer.
class TreeLinkNode {
	int val;
	TreeLinkNode left, right, next;

	TreeLinkNode(int x) {
		val = x;
	}
}

public class Solution {
	public void connect(TreeLinkNode root) {
		int last = 2;
		int cnt = 1;
		TreeLinkNode n = root;
		TreeLinkNode fa = null;
		TreeLinkNode first = root;
		TreeLinkNode ffa = null;
		while (n != null) {
			System.out.println("n=" + n.val);
			if (fa != null) {
				System.out.println("fa=" + fa.val);
			}
			if (n == root) {
				n.next = null;
				fa = n;
				n = n.left;
				first = n.left;
				ffa = n;
			} else if (n == fa.left) {
				n.next = fa.right;
				n = n.next;
			} else {
				// right child
				if (fa.next != null) {
					n.next = fa.next.left;
					fa = fa.next;
					n = n.next;
				} else {
					// this is the last
					n.next = null;
					n = first;
					fa = ffa;
					if (n != null) {
						first = n.left;
						ffa = n;
					}
				}
			}

		}
	}

	public static void main(String[] args) {
		Solution s = new Solution();
		TreeLinkNode t1 = new TreeLinkNode(1);
		TreeLinkNode t2 = new TreeLinkNode(2);
		TreeLinkNode t3 = new TreeLinkNode(3);
		TreeLinkNode t4 = new TreeLinkNode(4);
		TreeLinkNode t5 = new TreeLinkNode(5);
		TreeLinkNode t6 = new TreeLinkNode(6);
		TreeLinkNode t7 = new TreeLinkNode(7);
//		t1.left = t2;
//		t1.right = t3;
//		t2.left = t4;
//		t2.right = t5;
//		t3.left = t6;
//		t3.right = t7;
		s.connect(t1);
		System.out.println(t1.next == null);
//		System.out.println(t2.next == null);
//		System.out.println(t2.next.val);
//		System.out.println(t3.next == null);
//		System.out.println(t4.next == null);
//		System.out.println(t4.next.val);
//		System.out.println(t5.next == null);
//		System.out.println(t5.next.val);
//		System.out.println(t6.next == null);
//		System.out.println(t6.next.val);
//		System.out.println(t7.next == null);
	}
}