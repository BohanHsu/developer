package heap;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * 
 * @author bohan
 * a N-nary heap
 */
public class NaryTree {
	private int n;
	private boolean min = true;
	private ArrayList<Entry<Key>> array = null;
	private int count = 0;

	/**
	 * constrcutor method
	 * @param n: N-nary heap, implicity a min-heap
	 */
	public NaryTree(int n) {
		super();
		this.n = n;
	}

	/**
	 * constructor method
	 * @param n : N-nary heap
	 * @param minOrMax : iff minOrMax is true then this is a minimum heap, otherwise this is a maximum heap
	 */
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

	private int cmp(Entry<Key> e1, Entry<Key> e2) {
		this.count ++;
		System.out.println("comparing: "+e1.getKey().getId()+" v.s. "+e2.getKey().getId());
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
		Entry<Key> pe = this.array.get(parentIndex);
		Entry<Key> ce = this.array.get(index);
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
			Entry<Key> e = this.array.get(leftMost);
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
		Entry<Key> pe = this.array.get(index);
		Entry<Key> ce = this.array.get(child);
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

	public void makeQueue(ArrayList<Entry<Key>> list) {
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

	public Entry<Key> deleteMostPriority() {
		Entry<Key> e = null;
		int last = getLastIndexInArray();
		if (last >= 0) {
			e = this.array.get(0);
		}
		this.array.set(0, this.array.get(last));
		this.array.remove(last);
		heapify(0, false);
		return e;
	}

	public void insert(Entry<Key> e) {
		this.array.add(e);
		int last = getLastIndexInArray();
		heapify(last, true);
	}

	public void update(Entry<Key> key, double newWeight) {
		for (int i = 0; i < this.array.size(); i++) {
			Entry<Key> e = this.array.get(i);
			if (e.getKey().equals(key)) {
				e.setValue(newWeight);
				int j = i;
				while (needShilftUp(j) == 1) {
					j = shiftUp(j);
				}
			}

		}

	}

	public boolean isEmpty(){
		return this.array.isEmpty();
	}
	
	public void printTree() {
		DecimalFormat df = new DecimalFormat("#.#");
		System.out.print("//\tH = ");
		for (Entry<Key> e : this.array) {
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
			
	}
}