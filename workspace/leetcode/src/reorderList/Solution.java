package reorderList;

//  Definition for singly-linked list.
class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
		next = null;
	}
}

public class Solution {
	
	public void reorderList(ListNode head) {
		int length = lengthOfList(head);
		
		if (length <= 2){
			return;
		}
		
		int p1Length = length / 2;
		ListNode p1 = head;
		ListNode p2 = null;

		ListNode n = head;

		int count = 0;
		while (count < p1Length) {
			n = n.next;
			count++;
		}

		ListNode pn = n;

		n = n.next;

		pn.next = null;

		while (n != null) {
			pn = n;
			n = n.next;
			p2 = insertToHead(p2, pn);
		}
		
		n = p1;
		while (p2 != null) {
			pn = n.next;
			n.next = p2;
			p2 = p2.next;
			n.next.next = pn;
			n = pn;
		}
	}

	/**
	 * ListNode * 2 -> ListNode
	 * 
	 * @param head
	 *            : the head of the old list
	 * @param node
	 *            : the new node to be insert return : the new head of the list
	 */
	private ListNode insertToHead(ListNode head, ListNode node) {
		if (node == null) {
			return head;
		} else {
			node.next = head;
			return node;
		}
	}

	/**
	 * ListNode -> int
	 * 
	 * @param ln
	 *            : the head of a list return : the length of the list
	 */
	private int lengthOfList(ListNode head) {
		int length = 0;
		while (head != null) {
			length++;
			head = head.next;
		}
		return length;
	}
	
	/**
	 * for test
	 */
	private void showList(ListNode head){
		while (head != null){
			System.out.print(head.val);
			head = head.next;
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		ListNode n0 = new ListNode(0);
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(4);
		ListNode n5 = new ListNode(5);
		ListNode n6 = new ListNode(6);
		ListNode n7 = new ListNode(7);
		ListNode n8 = new ListNode(8);
		ListNode n9 = new ListNode(9);
		ListNode n10 = new ListNode(10);
		
		ListNode nnull = null;
		n0.next = n1;
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n5.next = n6;
		n6.next = n7;
		n7.next = n8;
		n8.next = n9;
		n9.next = n10;
	
		Solution s = new Solution();
		s.reorderList(n0);
		s.showList(n0);
	}
}