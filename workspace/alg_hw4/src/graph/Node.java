package graph;

public class Node {
	private int id;
	private int preVisit;
	private int postVisit;
		

	public Node(int id) {
		super();
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPreVisit() {
		return preVisit;
	}
	public void setPreVisit(int preVisit) {
		this.preVisit = preVisit;
	}
	public int getPostVisit() {
		return postVisit;
	}
	public void setPostVisit(int postVisit) {
		this.postVisit = postVisit;
	}

	@Override
	public String toString() {
		return "Node [id=" + id + ", preVisit=" + preVisit + ", postVisit="
				+ postVisit + "]";
	}
	
	
}
