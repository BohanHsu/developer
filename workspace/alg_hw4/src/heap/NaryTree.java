package heap;

import graph.Node;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class NaryTree {
	private int n;
	private boolean min = true;
	private ArrayList<Entry> array = null;
	private int count = 0;

	public NaryTree(int n) {
		super();
		this.n = n;
	}

	public NaryTree(int n, boolean minOrMax) {
		super();
		this.n = n;
		this.min = minOrMax;
	}

	private int getParentIndex(int index) {
		int p = ((index + 1 + n - 2) / n - 1);
		if (p >= 0) {
			return p;
		} else {
			return 0;
		}
	}

	private int getLeftMostChildIndex(int index) {
		return (index + 1) * n - (n - 2) - 1;
	}

	private int getRightMostChildIndex(int index) {
		return (index + 1) * n;
	}

	private int cmp(Entry e1, Entry e2) {
		this.count ++;
//		System.out.println("comparing: "+e1.getKey().getId()+" v.s. "+e2.getKey().getId());
		boolean e1Inf = e1.getValue() == null;
		boolean e2Inf = e2.getValue() == null;

		if (e1Inf && !e2Inf) {
			return 1;
		}
		if (!e1Inf && e2Inf) {
			return -1;
		}
		if (e1Inf && e2Inf) {
			return 0;
		}
		double e1Val = e1.getValue();
		double e2Val = e2.getValue();
		if (e1Val > e2Val) {
			return 1;
		}
		if (e1Val < e2Val) {
			return -1;
		}
		if (e1Val == e2Val) {
			return 0;
		}
		return 0;
	}

	private int needShilftUp(int index) {
		int parentIndex = getParentIndex(index);
		if (parentIndex == index){
			return 0;
		}
		int cmp = cmp(this.array.get(index), this.array.get(parentIndex));
		if (cmp < 0 && this.min) {
			return 1;
		}
		return 0;
	}

	private int shiftUp(int index) {
		int parentIndex = getParentIndex(index);
		Entry pe = this.array.get(parentIndex);
		Entry ce = this.array.get(index);
		this.array.set(index, pe);
		this.array.set(parentIndex, ce);
		return parentIndex;
	}

	private int needShiftDwon(int index) {
		int leftMost = getLeftMostChildIndex(index);
		int rightMost = getRightMostChildIndex(index);
		rightMost = Math.min(rightMost, getLastIndexInArray());
		boolean needed = false;
		for (int i = leftMost; i <= rightMost; i++) {
			int cmp = cmp(this.array.get(index), this.array.get(i));
			if (cmp > 0 && this.min) {
				needed = true;
				break;
			}
		}

		if (needed) {
			Entry e = this.array.get(leftMost);
			int t = leftMost;
			for (int i = leftMost + 1; i <= rightMost; i++) {
				int cmp = cmp(e, this.array.get(i));
				if (cmp > 0 && this.min) {
					e = this.array.get(i);
					t = i;
				}
			}
			return t;
		} else {
			return -1;
		}
	}

	private int shiftDown(int index, int child) {
		Entry pe = this.array.get(index);
		Entry ce = this.array.get(child);
		this.array.set(index, ce);
		this.array.set(child, pe);
		return child;
	}

	private void heapify(int index, boolean lookUp) {
		int i = index;
		if (lookUp) {
			while (needShilftUp(i) == 1) {
				i = shiftUp(i);
			}
		} else {
			int ci = needShiftDwon(i);
			while (ci != -1) {
				i = shiftDown(i, ci);
				ci = needShiftDwon(i);
			}
		}
	}

	public void makeQueue(ArrayList<Entry> list) {
		this.array = list;
		boolean anotherIteration = true;
		while (anotherIteration) {
			anotherIteration = false;
			for (int i = 0; i < this.array.size(); i++) {
				int needed = needShilftUp(i);
				if (needed == 1) {
					heapify(i, true);
					anotherIteration = true;
				}
			}
		}
		this.count = 0;
	}

	private int getLastIndexInArray() {
		return this.array.size() - 1;
	}

	public Entry deleteMostPriority() {
		Entry e = null;
		int last = getLastIndexInArray();
		if (last >= 0) {
			e = this.array.get(0);
		}
		this.array.set(0, this.array.get(last));
		this.array.remove(last);
		heapify(0, false);
		return e;
	}

	public void insert(Entry e) {
		this.array.add(e);
		int last = getLastIndexInArray();
		heapify(last, true);
	}

	public void update(Node key, double newWeight) {
		for (int i = 0; i < this.array.size(); i++) {
			Entry e = this.array.get(i);
			if (e.getKey().equals(key)) {
				e.setValue(newWeight);
//				e.setInfinity(false);
				int j = i;
				while (needShilftUp(j) == 1) {
					j = shiftUp(j);
				}

//				int cj = needShiftDwon(j);
//				while (cj != -1) {
//					j = shiftDown(j, cj);
//					cj = needShiftDwon(j);
//				}
			}

		}

	}

	public boolean isEmpty(){
		return this.array.isEmpty();
	}
	
	public void printTree() {
		DecimalFormat df = new DecimalFormat("#.#");
		System.out.print("//\t");
		for (Entry e : this.array) {
			if (e.getValue()==null) {
				System.out
				.print(e.getKey().getId() + "(inf) ");
			} else {
				System.out
						.print(e.getKey().getId() + "(" + df.format(e.getValue()) + ") ");
			}
		}
		System.out.print("\n");
		System.out.println("//\tcount: " + this.count);
	}

	public static void main(String[] args) {
		NaryTree t2 = new NaryTree(2);
		ArrayList<Entry> eArray = new ArrayList<Entry>();
		HashMap<Integer, Node> vertice = new HashMap<Integer, Node>();
		for (int i = 1; i < 9; i++) {
			Entry e = null;
			Node n = null;
			if (i == 6) {
				n = new Node(i);
				e = new Entry(n, (double)0);
			} else {
				n = new Node(i);
				e = new Entry(n, null);
			}
			vertice.put(i, n);
			eArray.add(e);
		}

		t2.makeQueue(eArray);
		t2.printTree();
		t2.deleteMostPriority();
		t2.printTree();
		t2.update(vertice.get(3), 2.7);
		t2.printTree();
		t2.update(vertice.get(5), 4.3);
		t2.printTree();
		
	}
}
