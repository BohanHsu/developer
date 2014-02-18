package binaryTreePostorderTraversal;

import java.util.ArrayList;

// to copy
import java.util.Stack;

// Definition for binary tree
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return val+"";
	}
}

public class Solution {
	private ArrayList<Integer> postOrder = new ArrayList<Integer>();
	private Stack<TreeNode> nodes = new Stack<TreeNode>();
	private Stack<Integer> status = new Stack<Integer>();

	// 0 in status means no child has been visited
	// 1 means left child has been visited
	// 2 means right child has been visited

	public ArrayList<Integer> postorderTraversal(TreeNode root) {
		if (root == null) {
			return postOrder;
		}

		TreeNode tn = null;

		nodes.push(root);
		status.push(0);

		while (!nodes.empty()) {			
			int sta = this.status.peek();
			tn = this.nodes.peek();
			switch (sta) {
			case 0:
				// no child has been visited
				changeStatue();
				if (tn.left != null) {
					nodes.push(tn.left);
					status.push(0);
				}
				break;
			case 1:
				// left child has been visited
				changeStatue();
				if (tn.right != null) {
					nodes.push(tn.right);
					status.push(0);
				}
				break;
			case 2:
				// right child has been visited
				tn = nodes.pop();
				status.pop();
				visitNode(tn);
				break;
			default:

			}
		}
		return this.postOrder;
	}

	/**
	 * TreeNode -> Void
	 * 
	 * @param tn
	 *            : a tree node effect : add the value in this node to the tail
	 *            of postOrder array list
	 */
	private void visitNode(TreeNode tn) {
		this.postOrder.add(tn.val);
	}

	/**
	 * Void -> Void effect : modified the status stack, add 1 to the head
	 * element iff valided.
	 */
	private void changeStatue() {
		if (this.status.empty()) {
			return;
		}
		int s = this.status.peek();
		if (s == 0 || s == 1) {
			this.status.pop();
			this.status.push(s + 1);
		}
	}
	
	/**
	 * test
	 * @param args
	 */
	public static void main(String[] args) {
		TreeNode tn1 = new TreeNode(1);
		TreeNode tn2 = new TreeNode(2);
		TreeNode tn3 = new TreeNode(3);
		TreeNode tn4 = new TreeNode(4);
		TreeNode tn5 = new TreeNode(5);
		TreeNode tn6 = new TreeNode(6);
		
		tn1.left = tn2;
		tn1.right = tn3;
		
		tn2.left = null;
		tn2.right = tn4;
		
		tn3.left = null;
		tn3.right = null;

		tn4.left = tn5;
		tn4.right = tn6;
		
		tn5.left = null;
		tn5.right = null;
		
		tn6.left = null;
		tn6.right = null;
		
		Solution s = new Solution();
		
		ArrayList<Integer> list = s.postorderTraversal(tn1);
		System.out.println(list);
	}
}