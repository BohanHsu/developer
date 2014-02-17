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
	public ListNode sortList(ListNode head) {
		return quickSort(head);
	}

	private ListNode quickSort(ListNode head) {
		if (head == null) {
			return null;
		}

		ListNode ln = head.next;
		ListNode lessEqual = null;
		ListNode greater = null;
		ListNode lessEqualTail = null;
		ListNode greaterTail = null;

		// ListNode = lessEqualTail = null;

		while (ln != null) {
			if (head.val >= ln.val) {
				// append to lessEqual
				if (lessEqual == null) {
					lessEqual = ln;
					lessEqualTail = ln;
				} else {
					lessEqualTail.next = ln;
					lessEqualTail = ln;
				}
			} else {
				// append to greater
				if (greater == null) {
					greater = ln;
					greaterTail = ln;
				} else {
					greaterTail.next = ln;
					greaterTail = ln;
				}
			}
		}

		// lessEqualTail.next = head;

		lessEqual = quickSort(lessEqual);
		greater = quickSort(greater);

		ln = lessEqual;
		if (ln == null) {
			// return from middle (head)
			lessEqual = head;
		} else {
			while (ln.next != null) {
				ln = ln.next;
			}
			ln.next = head;
		}
		head.next = greater;

		return lessEqual;
	}
}