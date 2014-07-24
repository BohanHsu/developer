import collections
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution:
  # @param root, a tree node
  # @return a tree node
  def recoverTree(self, root):
    odd = []
    i = 0
    p = None
    stack = []
    stack.append(root)
    while stack:
      n = stack[-1]
      print n.val
      if n.left:
        stack.append(n.left)
      else:
        if p:
          if p.val > n.val:
            odd.extend([i-1,i])

        p = n
        #visit(n)
        stack.pop()
        if n.right:
          stack.append(n.right)

      i = i+1

    i = 0
    stack.append(root)
    while stack:
      n = stack[-1]
      if n.left:
        stack.append(n.left)
      else:
        if i == odd[1]:
          l = n
        if i == odd[3]:
          s = n

      stack.pop()
      if n.right:
        stack.append(n.right)

    tmp = l.val
    l.val = s.val
    s.val = tmp
    return root

# test code 
t1 = TreeNode(10)
t2 = TreeNode(3)
t3 = TreeNode(7)
t4 = TreeNode(1)
t5 = TreeNode(15)
t6 = TreeNode(12)
t7 = TreeNode(16)

t1.left = t2
t1.right = t3
t2.left = t4
t2.right = t5
t3.left = t6
t3.right = t7

s = Solution()
root = s.recoverTree(t1)

q = collections.deque()
q.append(root)
while q:
  n = q
  print n.val
  if n.left:
    print n,'.left', n.left.val
    q.append(n.left)

  if n.right:
    print n, '.right', n.right.val
    q.append(n.right)
