package heap;

import graph.Node;

import java.util.ArrayList;

public class NaryTree {
	private int n;
	private boolean min = true;
	private ArrayList<Entry> array = null;
	
	public NaryTree(int n) {
		super();
		this.n = n;
	}

	public NaryTree(int n, boolean minOrMax) {
		super();
		this.n = n;
		this.min = minOrMax;
	}

	
	private int getParentIndex(int index){
		return (index + 1) / n - 1;
	}
	
	private int getLeftChildIndex(int index){
		return (index + 1) * n - 1;
	}
	
	private int getRightChildIndex(int index){
		return (index + 1) * n;
	}
	
	private int cmp(Entry e1, Entry e2){
		boolean e1Inf = e1.isInfinity();
		boolean e2Inf = e2.isInfinity();
		double e1Val = e1.getValue();
		double e2Val = e2.getValue();
		
		if (e1Inf && !e2Inf){
			return 1;
		}
		if (!e1Inf && e2Inf){
			return -1;
		}
		if (e1Inf && e2Inf){
			return 0;
		}
		if (e1Val > e2Val){
			return 1;
		}
		if (e1Val < e2Val){
			return -1;
		}
		if (e1Val == e2Val){
			return 0;
		}
	} 
	
	private boolean needShilftUp(int index){
		int parentIndex = getParentIndex(index);
		int cmp = cmp(this.array.get(index), this.array.get(parentIndex));
		if (cmp < 0 && this.min){
			return true;
		}
		return false;
	} 
	
	private int shiftUp(int index){
		int parentIndex = getParentIndex(index);
		Entry pe = this.array.get(parentIndex);
		Entry ce = this.array.get(index);
		this.array.set(index, pe);
		this.array.set(parentIndex, ce);
	}
	
	public void makeQueue(ArrayList<Entry> list){
		this.array = list;
		
	}
}
