package test;

import graph.Node;

import java.text.DecimalFormat;
import java.util.HashMap;

public class Test {
	public static void main(String[] args) {
		HashMap<Integer, Node> hm = new HashMap<Integer, Node>();
		Node n = new Node(1);
		hm.put(1, n);
		n = hm.get(1);
		n.setId(2);
		n = hm.get(1);
		System.out.println(n.getId());
		
		DecimalFormat df = new DecimalFormat("#.#");
		System.out.println(df.format(21312.324234));
		
		
//		Double a = 1.0;
//		Double b = null;
//		System.out.println(a+b);
		
		HashMap<Integer, Integer> h = new HashMap<Integer,Integer>();
		h.put(0, 0);
		int i = h.get(1);
		System.out.println(i);
	}
}
