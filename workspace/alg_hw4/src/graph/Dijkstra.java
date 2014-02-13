package graph;

import heap.Entry;
import heap.NaryTree;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Dijkstra {
	DecimalFormat df = new DecimalFormat("#.#");
	private Graph g = null;
	HashMap<Integer, Node> v = null;
	ArrayList<Edge> e = null;
	NaryTree nt = null;

	public Dijkstra(Graph g, NaryTree nt) {
		super();
		this.g = g;
		this.e = g.getE();
		this.v = g.getV();
		this.nt = nt;
	}

	private Double add(Double d1, Double d2) {
		if (d1 == null || d2 == null) {
			return null;
		} else {
			return d1 + d2;
		}

	}

	private int cmp(Double d1, Double d2) {
		boolean e1Inf = d1 == null;
		boolean e2Inf = d2 == null;

		if (e1Inf && !e2Inf) {
			return 1;
		}
		if (!e1Inf && e2Inf) {
			return -1;
		}
		if (e1Inf && e2Inf) {
			return 0;
		}
		double e1Val = d1;
		double e2Val = d2;
		if (e1Val > e2Val) {
			return 1;
		}
		if (e1Val < e2Val) {
			return -1;
		}
		if (e1Val == e2Val) {
			return 0;
		}
		return 0;
	}

	public void dijkstra(int source) {
		HashMap<Integer, Double> dist = new HashMap<Integer, Double>();
		HashMap<Integer, Integer> prev = new HashMap<Integer, Integer>();
		ArrayList<Integer> vs = new ArrayList<Integer>();
		for (Integer i : v.keySet()) {
			vs.add(i);
		}
		Collections.sort(vs);
		ArrayList<Entry> eArray = new ArrayList<Entry>();
		for (Integer i : vs) {
			Entry e = null;
			Node n = null;
			n = this.v.get(i);
			if (i == source) {
				e = new Entry(n, (double) 0);
				dist.put(i, (double) 0);
			} else {
				e = new Entry(n, null);
				dist.put(i, null);
			}
			eArray.add(e);
			prev.put(i, null);
		}
		this.nt.makeQueue(eArray);
		nt.printTree();

		int count = 0;
		while (!nt.isEmpty()) {
			System.out.println("E is not empty");
			int u = nt.deleteMostPriority().getKey().getId();
			System.out.println("\t" + u + " <- deletemin()");
			nt.printTree();
			ArrayList<Edge> edges = g.edgeFromVertex(u);
			for (Edge edge : edges) {
				int v = edge.getTgt();
				System.out.println("\t(" + u + "," + v + ") is in E");
				Double newLength = add(dist.get(u), edge.getWeight());
				if (cmp(dist.get(v), newLength) > 0) {
					dist.put(v, newLength);
					prev.put(v, u);
					nt.update(this.v.get(v), newLength);
					System.out.println("\t"+ v + ".dist > " + u + ".dist + length("
							+ u + "," + v + ")");
					System.out.println("\t\t" + v + ".dist <- " + df.format(newLength));
					System.out.println("\t\t" + v + ".prev <- " + u);
					System.out.println("\t\tQ.decrease-key("+v+")");
					nt.printTree();
				}else{
					System.out.println("\t"+ v + ".dist < " + u + ".dist + length("
							+ u + "," + v + ")");
				}
			}
		}
	}

}
