package binaryTreePreorderTraversal;

import java.util.ArrayList;

//to copy
import java.util.Stack;


//Definition for binary tree
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
    private Stack<TreeNode> nodes = new Stack<TreeNode>();
    private ArrayList<Integer> list = new ArrayList<Integer>();
    
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
        if (root == null){
            return this.list;
        }
        
        TreeNode tn = root;
        
        while (!nodes.empty() || tn != null){
            
            while (tn != null){
                visitNode(tn);
                nodes.push(tn);
                tn = tn.left;
            }
        
            tn = nodes.pop();
            tn = tn.right;
            
        }
        return this.list;
    }
    
    /**
     * TreeNode -> Void 
     * @param tn : a TreeNode to visit
     * effect : add the tree node's value to the tail of the list
     */ 
    private void visitNode(TreeNode tn){
        this.list.add(tn.val);
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
		
		ArrayList<Integer> list = s.preorderTraversal(tn1);
		System.out.println(list);
	}
}