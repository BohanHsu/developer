package test;

import java.util.HashSet;
import java.util.Iterator;

public class PlainTest {
	public static void main(String[] args) {
		HashSet<Integer> hs = new HashSet<Integer>();
		hs.add(1);
		hs.add(2);
		hs.add(3);
		hs.add(4);
		
		
		Iterator<Integer> itr = hs.iterator();
		
		while(itr.hasNext()){
			hs.add(itr.next() * 2);
		}
		
		System.out.println(hs);
	}
}
