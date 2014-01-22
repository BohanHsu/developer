package chap4;

import chap3.MyStack;

public class BSTree extends Tree{
	
	public BSTree(){
		super();
	}

	public void insert(int data){
		if(this.root == null){
			this.root = new Node(data);
		}else{
			Node n = root;
			Node p = null;
			while(n != null){
				p = n;
				if(data <= n.getData()){
					n = n.getLeft();
				}else{
					n = n.getRight();
				}
			}
			if(data <= p.getData()){
				p.setLeft(new Node(data));
			}else{
				p.setRight(new Node(data));
			}
		}
	}


	public void inOrderTraverse(){
		System.out.println("in-order traverse");
		boolean isLeftChecked = false;
		Node n = null;
		MyStack stack = new MyStack();		
		stack.push(root);
		while(!stack.isEmpty()){
			n = (Node)stack.peek();
			if(n.getLeft() == null || isLeftChecked){
				System.out.println(n.getData());
				stack.pop();
				isLeftChecked = true;
				n = n.getRight();
				if(n != null){
					stack.push(n);
					isLeftChecked = false;
				}
			}else{
				stack.push(n.getLeft());
				isLeftChecked = false;
			}
		}
	}

	public void preOrderTraverse(){
		System.out.println("pre order traverse");
		MyStack stack = new MyStack();
		stack.push(root);
		Node n = null;
		while(!stack.isEmpty()){
			n = (Node)stack.pop();
			System.out.println(n.getData());
			if(n.getRight() != null){
				stack.push(n.getRight());
			}
			if(n.getLeft() != null){
				stack.push(n.getLeft());
			}	
		}
	}

	public void postOrderTraverse(){
		System.out.println("post order traverse");
		MyStack stack = new MyStack();
		MyStack stk2 = new MyStack();
		stack.push(root);
		stk2.push(new Integer(0));
		Node n = null;
		while(!stack.isEmpty()){
			n = (Node)stack.peek();
			int i = (Integer)stk2.pop();
			switch(i){
				case 0:
					n = n.getLeft();
					stk2.push(new Integer(i+1));
					if(n != null){
						stack.push(n);
						stk2.push(new Integer(0));
					}
					break;
				case 1:
					n = n.getRight();
					stk2.push(new Integer(i+1));
					if(n != null){
						stack.push(n);
						stk2.push(new Integer(0));
					}
					break;
				case 2:
					System.out.println(n.getData());
					stack.pop();
					break;
			}	
		}
	}

	public static void main(String[] args){
		BSTree bst = new BSTree();
		bst.insert(6);
		bst.insert(3);
		bst.insert(8);
		bst.insert(1);
		bst.insert(5);
		bst.insert(4);
		bst.inOrderTraverse();
		bst.preOrderTraverse();
		bst.postOrderTraverse();
	}	
	
	private static void print(Node n){
		if(n == null){
			System.out.println("null");
		}else{
			System.out.println(n.getData());
		}
	}
}
