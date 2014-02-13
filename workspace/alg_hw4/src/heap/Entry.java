package heap;

import graph.Node;

public class Entry  {
	Node key = null;
	double value = 0;
	boolean infinity = false;
	public Entry(Node key, double value, boolean infinity) {
		super();
		this.key = key;
		this.value = value;
		this.infinity = infinity;
	}
	public Node getKey() {
		return key;
	}
	public void setKey(Node key) {
		this.key = key;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public boolean isInfinity() {
		return infinity;
	}
	public void setInfinity(boolean infinity) {
		this.infinity = infinity;
	}
	
	
		
}
