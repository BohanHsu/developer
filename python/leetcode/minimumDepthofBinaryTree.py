import collections
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution:
  # @param root, a tree node
  # @return an integer
  def minDepth(self, root):
    queue =  collection.deque()
    depth = {}
    depth[root] = 1
    queue.append(root)
    while queue:
      n = queue.popleft()
      if not n.left and not n.right:
        return depth[n]

      if n.left:
        queue.append(n.left)
        depth[n.left] = depth[n] + 1

      if n.right:
        queue.append(n.right)
        depth[n.right] = depth[n] + 1

