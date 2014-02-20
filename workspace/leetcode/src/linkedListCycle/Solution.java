package linkedListCycle;

// Definition for singly-linked list.
class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
		next = null;
	}
}

public class Solution {
	public boolean hasCycle(ListNode head) {
		if (head == null) {
			return false;
		}

		ListNode f = head;
		ListNode s = head;

		while (s.next != null) {

			if (f.next == null) {
				// fast reach the end
				return false;
			} else {
				f = f.next;
				if (f.next == null) {
					// fast reach the end
					return false;
				} else {
					f = f.next;
				}
			}
			s = s.next;

			// the fast and slow are point to same node
			if (s == f) {
				// there is a loop
				return true;
			}
		}
		return false;

	}
	
	public static void main(String[] args) {
		Solution s = new Solution();
		
		ListNode l0 = new ListNode(0);
		ListNode l1 = new ListNode(0);
		ListNode l2 = new ListNode(0);
		ListNode l3 = new ListNode(0);
		ListNode l4 = new ListNode(0);
		ListNode l5 = new ListNode(0);
		ListNode l6 = new ListNode(0);
		
		l0.next = l1;
		l1.next = l2;
		l2.next = l3;
		l3.next = l4;
		l4.next = l5;
		l5.next = l6;
		l6.next = l6;
		
		
		boolean has = s.hasCycle(l0);
		System.out.println(has);
	}
}