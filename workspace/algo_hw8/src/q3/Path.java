package q3;

import java.util.LinkedList;

public class Path {
	LinkedList<Integer> path = null;
	int capacity = 0;
	
	public Path() {
		this.path = new LinkedList<Integer>();
	}
	
	public void addToHead(int i){
		this.path.addFirst(i);
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	@Override
	public String toString() {
		String s = "";
		for (int i : path) {
			s += i + " ";
		}
		return s;
	}
	
	public LinkedList<Edge> modifyEdge(){
		Edge e = null;
		int size = this.path.size();
		LinkedList<Edge> es = new LinkedList<Edge>();
		for (int i = 0; i < size - 1; i++){
			e = new Edge(path.get(i), path.get(i+1), -capacity);
			es.add(e);
			e = new Edge(path.get(i+1), path.get(i), capacity);
			es.add(e);
		}
		return es;
	}
}
