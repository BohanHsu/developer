package chap4;

import java.util.ArrayList;
import java.util.List;

public class Node{
	private int data = 0;
	private Node left = null;
	private Node right = null;	

	private ArrayList<Node> nodes = new ArrayList<Node>();
	
	public Node(int data){
		super();
		this.data = data;
	}

	public void setLeft(Node left){
		this.left = left;
	}

	public void setRight(Node right){
		this.right = right;
	}

	public Node getLeft(){
		return this.left;
	}

	public Node getRight(){

		return this.right;
	}

	public int getData(){
		return this.data;
	}

	public List<Node> getNodes(){
		return this.nodes;
	}

	public void addNode(Node n){
		this.nodes.add(n);
	}
}
