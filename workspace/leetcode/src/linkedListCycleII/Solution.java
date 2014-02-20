package linkedListCycleII;

//Definition for singly-linked list.
class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
		next = null;
	}
}

public class Solution {
	
	public ListNode detectCycle(ListNode head) {
        if (head == null) {
			return null;
		}

        // ListNode start = null;
		ListNode f = head;
		ListNode s = head;

		while (s.next != null) {
			System.out.println("while");
			if (f.next == null) {
				// fast reach the end
				return null;
			} else {
				f = f.next;
				if (f.next == null) {
					// fast reach the end
					return null;
				} else {
					f = f.next;
				}
			}
			s = s.next;

			// the fast and slow are point to same node
			if (s == f) {
				// there is a loop
				System.out.println("loop");
				break;
			}
		}
		
        f = head;
        while (s != f){
        	System.out.println("!=");
            s = s.next;
            f = f.next;
        }
        
        return s;
    }
	
	public static void main(String[] args) {
		Solution s = new Solution();
		
		ListNode l0 = new ListNode(0);
		ListNode l1 = new ListNode(1);
		ListNode l2 = new ListNode(2);
		ListNode l3 = new ListNode(3);
		ListNode l4 = new ListNode(4);
		ListNode l5 = new ListNode(5);
		ListNode l6 = new ListNode(6);
		
		l0.next = l1;
		l1.next = l2;
		l2.next = l3;
		l3.next = l4;
		l4.next = l5;
		l5.next = l6;
		l6.next = null;
		
		
		ListNode ln = s.detectCycle(l0);
		System.out.println(ln);
	}
}