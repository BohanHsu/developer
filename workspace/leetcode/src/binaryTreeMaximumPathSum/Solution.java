package binaryTreeMaximumPathSum;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// * Definition for binary tree
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

public class Solution {
	private HashMap<TreeNode, Integer> a = new HashMap<TreeNode, Integer>();
	private HashMap<TreeNode, Integer> b = new HashMap<TreeNode, Integer>();
	private HashMap<TreeNode, Integer> c = new HashMap<TreeNode, Integer>();
	private HashMap<TreeNode, TreeNode> father = new HashMap<TreeNode, TreeNode>();

	public int maxPathSum(TreeNode root) {
		if (root == null){
			return 0;
		}
		Stack<TreeNode> stack1 = new Stack<TreeNode>();
		Stack<Integer> stack2 = new Stack<Integer>();

		stack1.push(root);
		stack2.push(0);

		int curState = 0;
		TreeNode tn = null;

		while (!stack1.empty()) {
			tn = stack1.peek();
			curState = stack2.pop();
			if (curState == 0) {
				// non-child has been visited
				stack2.push(1);
				if (tn.left != null) {
					stack1.push(tn.left);
					stack2.push(0);
				}
			} else if (curState == 1) {
				// left has visited
				stack2.push(2);
				if (tn.right != null) {
					stack1.push(tn.right);
					stack2.push(0);
				}
			} else if (curState == 2) {
				// all children have been visited
				stack1.pop();
				// visit this
				if (tn.left == null) {
					// this node is a leaf
					this.b.put(tn, 0);
				} else {
					this.b.put(tn,
							Math.max(this.b.get(tn.left), this.c.get(tn.left))
									+ tn.left.val);
				}
				if (tn.right == null) {
					// this node is a leaf
					this.c.put(tn, 0);
				} else {
					this.c.put(
							tn,
							Math.max(this.b.get(tn.right), this.c.get(tn.right))
									+ tn.right.val);
				}
			}
		}

		this.a.put(root, 0);
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		if (root.left != null) {
			queue.add(root.left);
			this.father.put(root.left, root);
		}
		if (root.right != null) {
			queue.add(root.right);
			this.father.put(root.right, root);
		}
		int max = Integer.MIN_VALUE;
		while (!queue.isEmpty()) {
			tn = queue.poll();
			TreeNode f = this.father.get(tn);
			if (tn == f.left) {
				this.a.put(tn, f.val + Math.max(this.a.get(f), this.c.get(f)));
			} else {
				this.a.put(tn, f.val + Math.max(this.a.get(f), this.b.get(f)));
			}
			if (tn.left != null) {
				queue.add(tn.left);
				this.father.put(tn.left, tn);
			}
			if (tn.right != null) {
				queue.add(tn.right);
				this.father.put(tn.right, tn);
			}
			// at this time, tn get the value of a,b,c
			int tnMax = maxPath(Math.max(0, this.a.get(tn)), Math.max(0, this.b.get(tn)), Math.max(0, this.c.get(tn))) + tn.val;
			if (tnMax > max) {
				max = tnMax;
			}
		}
		int tnMax = maxPath(Math.max(0, this.a.get(root)), Math.max(0, this.b.get(root)), Math.max(0, this.c.get(root))) + root.val;
		if (tnMax > max) {
			max = tnMax;
		}
		
		return max;
	}

	private int maxPath(int a, int b, int c) {
		if (a >= c && b >= c) {
			// ab
			return a + b;
		} else if (a >= b && c >= b) {
			// ac
			return a + c;
		} else if (b >= a && c >= a) {
			// bc
			return b + c;
		} else {
			return 0;
		}
	}
	
	
	public static void main(String[] args) {
		TreeNode t0 = new TreeNode(2);
		TreeNode t1 = new TreeNode(-1);
		TreeNode t2 = new TreeNode(-2);
		t0.left = t1;
		t0.right = t2;
		
//		TreeNode t3 = new TreeNode(5);
//		TreeNode t4 = new TreeNode(6);
//		t1.left = t3;
//		t1.right = t4;
		
		Solution s = new Solution();
		System.out.println(s.maxPathSum(t0));
	}
}