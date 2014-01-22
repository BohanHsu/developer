package chap3;

import java.util.ArrayList;

public class MyQueue{
	ArrayList<Object> a = null;

	public MyQueue(){
		this.a = new ArrayList<Object>();
	}
	
	public void inQueue(Object obj){
		a.add(obj);
	}
	
	public Object eQueue(){
		if(a.isEmpty()){
			return null;
		}
		Object o = a.get(0);
		a.remove(0);
		return o;
	}

	public boolean isEmpty(){
		return a.isEmpty();
	}

}
