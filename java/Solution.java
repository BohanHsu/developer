/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
  public void connect(TreeLinkNode root) {
    TreeLinkNode n = null;
    TreeLinkNode head = null;
    TreeLinkNode f = null;

    n = root;
    head = n;
    f = root;
    //while(n != null){
    //  if (n == root){
    //    n.next= null;
    //    n = head;
    //    n = getFirstChildOfNode(n);
    //    fa = root;
    //  } else {
    //    n = getFirstNodeWithChild(head);
    //    if (n.left != null){
    //      TreeLinkNode uncle = getNextByFatherAndNode(n, n.left);
    //      if (uncle == fa){
    //        n.left.next = n.right;
    //      } else {
    //        n.left.next = getFirstChildOfNode(uncle);
    //      }
    //    }  
    //    if (n.right != null){
    //      TreeLinkNode uncle = getNextByFatherAndNode(n, n.right);
    //      n.right.next = getFirstChildOfNode(uncle);
    //    } else if (n.left == null && n.right == null) {
    //      // no next child
    //    }
    //  }         


    //}
    TreeLinkNode u = null;
    TreeLinkNode m = null;
    while(n != null){
      n = getFirstChildOfNode(f);
      if (n == null){
      }
      u = getFirstNodeWithChild(f);
      if (u == null){
        
      }
      if (f == u){
        n.next = f.right;
        // father node not change
      } else {
        n.next = getFirstChildOfNode(u);
        f = u;
      }
      n = n.next;
      if (f == null){
        f = getFirstNodeWithChild(head);
        n = getFirstChildOfNode(f);
        head = f;
      }
    }

  }
  
  private TreeLinkNode getFirstChildOfNode(TreeLinkNode n){
    if (n.left != null){
      return n.left;
    } else {
      return n.right;
    }
  }
  private TreeLinkNode getNextByFatherAndNode(TreeLinkNode fa, TreeLinkNode n){
    if (n == fa.left && fa.right != null){
      return fa;
    }
    TreeLinkNode uncle = getFirstNodeWithChild(fa.next);
    return uncle;
  }

  private TreeLinkNode getFirstNodeWithChild(TreeLinkNode head){
    if (head == null){
      return null;
    }
    while(true){
      if (head.left != null || head.right != null){
        return head;
      } else {
        if (head.next != null){
          head = head.next;
        } else{
          return null;
        }
      }
    }
  }
}

