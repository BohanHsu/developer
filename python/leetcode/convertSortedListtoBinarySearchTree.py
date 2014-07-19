import collections
class TreeNode:
  def __init__(self, x):
      self.val = x
      self.left = None
      self.right = None

class ListNode:
  def __init__(self, x):
      self.val = x
      self.next = None

class Solution:
  def __init__(self):
    self.index = {}

  # @param head, a list node
  # @return a tree node
  def sortedListToBST(self, head):
    h_root = {}
    h_position = {}
    if not head:
      return None

    i = 0
    n = head
    while n:
      self.index[i] = n
      i = i + 1
      n = n.next

    queue = collections.deque()
    rootIndex = getRootIndexByInterval(0,i+1)
    root = TreeNode(index[rootIn].val)
    queue.append((0,rootIndex))
    h_root[(0,rootIndex)] = root
    h_position[(0,rootIndex)] = 'l'
    queue.append((rootIndex+1,i+1))
    h_root[(rootIndex+1,i+1)] = root
    h_position[(rootIndex+1,i+1)] = 'r'
    while queue:
      interval = queue.popleft()
      ind = getRootIndexByInterval(interval)
      r = TreeNode(self.index[ind].val)
      if 
      queue.append((interval[0],ind))
      h_root[(interval[0],ind)] = r
      h_position[(interval[0],ind)] = 'l'
      queue.append((ind+1,interval[1]))
      h_root[(ind+1,interval[1])] = r
      h_position[(ind+1,interval[1])] = 'r'


  # @interval: part of the list represent by index
  # example (a,b) a is index of first element, b-1 is index of last element
  # @return root node index in this interval
  # if this is empty interval return None
  def getRootIndexByInterval(self,interval):
    if interval[1] - interval[0] == 0:
      return None
    else:
      return (interval[1] - interval[0] - 1)/2 + interval[0]
