package sumRoottoLeafNumbers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

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
	public int sumNumbers(TreeNode root) {
        if (root == null){
            return 0;   
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        HashMap<TreeNode, Integer> value = new HashMap<TreeNode, Integer>();
        HashSet<TreeNode> leaf = new HashSet<TreeNode>();
        HashMap<TreeNode,TreeNode> father = new HashMap<TreeNode,TreeNode>();
        TreeNode tn = null;
        queue.add(root);
        while(!queue.isEmpty()){
            tn = queue.poll();
            if (tn == root){
                // value equals to val
                value.put(tn, tn.val);
            } else{
                // value = father's value * 10 + this node's val
                value.put(tn,(value.get(father.get(tn)) * 10 + tn.val));
            }
            if (tn.left != null){
                queue.add(tn.left);
                father.put(tn.left,tn);
            }
            if (tn.right != null){
                queue.add(tn.right);
                father.put(tn.right,tn);
            }
            if (tn.left == null && tn.right == null){
                // this is leaf
                leaf.add(tn);
            }
        }
        
        int total = 0;
        for(TreeNode n : leaf){
            total += value.get(n);
        }
        return total;
    }
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		TreeNode tn1 = new TreeNode(2);
		TreeNode tn2 = new TreeNode(3);
		TreeNode tn3 = new TreeNode(4);
		TreeNode tn4 = new TreeNode(5);
		
		root.left = tn1;
		root.right = tn2;
		tn1.left = tn3;
		tn1.right = tn4;
		
		Solution s = new Solution();
		System.out.println(s.sumNumbers(root));
	}
}