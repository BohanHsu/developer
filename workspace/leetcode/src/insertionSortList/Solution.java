package insertionSortList;

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
	public ListNode insertionSortList(ListNode head) {
		ListNode sortedList = null;

		if (head == null) {
			return sortedList;
		}

		ListNode cur = head;
		ListNode next = null;
		while (cur != null) {
			next = cur.next;
			sortedList = insert(sortedList, cur);
			cur = next;
		}

		return sortedList;
	}

	/**
	 * ListNode * 2 -> Void head : the head of a linked list node : the new node
	 * to be insert effect : insert the new node into the linked list in sorted
	 * order
	 */
	private ListNode insert(ListNode head, ListNode node) {
		ListNode sortedHead = null;
		ListNode cur = head;
		ListNode prev = null;

		while (cur != null) {
			if (cur.val < node.val) {
				prev = cur;
				cur = cur.next;
			} else {
				break;
			}
		}

		if (prev == null) {
			sortedHead = node;
			node.next = head;
		} else {
			sortedHead = head;
			prev.next = node;
			node.next = cur;
		}

		return sortedHead;
	}
	
	
	private ListNode createList(int[] list){
		ListNode head = null;
		ListNode tail = null;
		
		for (int i : list){
			ListNode ln = new ListNode(i);
			if (tail == null){
				head = ln;
			}else{
				tail.next = ln;
			}
			tail = ln;
		}
		return head;
	}
	
	private void showList(ListNode head){
		System.out.println("+++++");
		while (head != null){
			System.out.println(head.val);
			head = head.next;
		}
	}
	
	public static void main(String[] args) {
		Solution s = new Solution();
		int[] list = new int[]{1,6,3,4,8,5};
		ListNode head = s.createList(list);
		s.showList(head);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);		
		head = s.insert(head, node2);
		s.showList(head);
		head = s.insertionSortList(head);
		s.showList(head);
		
		
	}
}