package heap;

import graph.Node;

public class Entry  {
	Node key = null;
	Double value = null;
	public Entry(Node key, Double value) {
		super();
		this.key = key;
		this.value = value;
	}
	public Node getKey() {
		return key;
	}
	public void setKey(Node key) {
		this.key = key;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	
	
		
}
