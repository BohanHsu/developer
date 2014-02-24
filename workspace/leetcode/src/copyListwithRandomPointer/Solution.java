package copyListwithRandomPointer;

import java.util.HashMap;

//  Definition for singly-linked list with a random pointer.
class RandomListNode {
	int label;
	RandomListNode next, random;

	RandomListNode(int x) {
		this.label = x;
	}
}

public class Solution {
	public RandomListNode copyRandomList(RandomListNode head) {
		if (head == null) {
			return null;
		}

		HashMap<RandomListNode, RandomListNode> mapf = new HashMap<RandomListNode, RandomListNode>();
		RandomListNode copy = null;
		RandomListNode oldrln = head;
		RandomListNode newrln = null;
		RandomListNode copyTail = null;
		while (oldrln != null) {
			newrln = new RandomListNode(oldrln.label);
			if (copy == null) {
				copy = newrln;
				copyTail = newrln;
			} else {
				copyTail.next = newrln;
				copyTail = newrln;
			}
			mapf.put(oldrln, newrln);
			oldrln = oldrln.next;
		}

		oldrln = head;
		while (oldrln != null) {
			newrln = mapf.get(oldrln);
			newrln.random = mapf.get(oldrln.random);
			oldrln = oldrln.next;
		}

		return copy;
	}
	
	
	private void showRandomListNode(RandomListNode head){
		while(head != null){
			System.out.print(head.label + " -(r)-> " );
			if (head.random == null){
				System.out.println("null");
			}else{
				System.out.println(head.random.label);
			}
			head = head.next;
			System.out.println("|");
			System.out.println("v");
		}
		System.out.println("null");
	}
	
	public static void main(String[] args) {
		RandomListNode rln0 = new RandomListNode(0);
		RandomListNode rln1 = new RandomListNode(1);
		RandomListNode rln2 = new RandomListNode(2);
		RandomListNode rln3 = new RandomListNode(3);
		RandomListNode rln4 = new RandomListNode(4);
		RandomListNode rln5 = new RandomListNode(5);
		RandomListNode rln6 = new RandomListNode(6);
		RandomListNode rln7 = new RandomListNode(7);
		RandomListNode rln8 = new RandomListNode(8);
		RandomListNode rln9 = new RandomListNode(9);
		
		rln0.next = rln1;
		rln0.random = rln2;
		
		rln1.next = rln2;
		rln1.random = rln3;

		rln2.next = rln3;
		rln2.random = rln4;
		
		rln3.next = rln4;
		rln3.random = rln5;
		
		rln4.next = rln5;
		rln4.random = rln6;
	
		rln5.next = rln6;
		rln5.random = rln7;
		
		rln6.next = rln7;
		rln6.random = rln8;
		
		rln7.next = rln8;
		rln7.random = rln9;
		
		rln8.next = rln9;
		rln8.random = rln0;
		
		rln9.next = null;
		rln9.random = rln1;
		
		Solution s = new Solution();
//		s.showRandomListNode(rln0);
		RandomListNode result = s.copyRandomList(rln0);
		
		s.showRandomListNode(result);
	}
}