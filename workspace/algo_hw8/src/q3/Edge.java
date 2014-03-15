package q3;

public class Edge {
	private int src;
	private int tgt;
	private int weight;
	
	public Edge(int src, int tgt, int weight) {
		super();
		this.src = src;
		this.tgt = tgt;
		this.weight = weight;
	}

	public int getSrc() {
		return src;
	}

	public void setSrc(int src) {
		this.src = src;
	}

	public int getTgt() {
		return tgt;
	}

	public void setTgt(int tgt) {
		this.tgt = tgt;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	
	
	
}
