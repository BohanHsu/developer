package graph;

public class Edge {
	private int src;
	private int tgt;
	private double weight;
	
	public Edge(int src, int tgt, double weight) {
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

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "Edge [src=" + src + ", tgt=" + tgt + ", weight=" + weight + "]";
	}
	
	
	
}
