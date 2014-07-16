# Definition for a  binary tree node
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    # @param root, a tree node
    # @return nothing, do it in place
    def flatten(self, root):
      check_root_right = False
      stack = []
      n = root
      stack.append(n)
      while not stack:
        n = stack[-1]
        if not n.left:
          stack.append(n.left)
        elif not n.right:
          stack.append(n.right)
        else
          n = stack.pop()
          # call helper function


      def
        if n == stack[-1].left
          m = stack[-1]
          n.right = m.right
          m.left = m.right
          m.right = n

        if n == stack[-1].right
          stack
          #if stack[-1] == root:
            
          
