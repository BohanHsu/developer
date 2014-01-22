package chap3;

import java.util.ArrayList;

public class MyStack{
	ArrayList<Object> stack = null;

	public MyStack(){
		this.stack = new ArrayList<Object>();
	}

	public void push(Object obj){
		this.stack.add(obj);
	}

	public Object peek(){
		return this.stack.get(this.stack.size()-1);
	}

	public Object pop(){
		if(this.stack.isEmpty()){
			return null;
		}
		int len = this.stack.size();
		Object o = this.stack.get(len - 1);
		this.stack.remove((len - 1));
		return o;
	}

	public boolean isEmpty(){
		return this.stack.isEmpty();
	}

	private void toConsole(){
		for(Object obj:this.stack){
			System.out.println(obj);
		}
	}

	public static void main(String[] args){
		MyStack ms = new MyStack();
		ms.push(new Integer(1));
		ms.push(new Integer(2));
		ms.push(new Integer(3));
		ms.push(new Integer(4));

		ms.toConsole();

		ms.pop();
		ms.pop();
		ms.pop();
		ms.pop();
		ms.pop();
		ms.toConsole();
	}
}
