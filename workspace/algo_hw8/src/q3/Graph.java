package q3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {

	private HashSet<Integer> v = null;
	private ArrayList<Edge> e = null;

	public Graph(String[] edge) {
		HashSet<Integer> ver = new HashSet<Integer>();
		this.v = new HashSet<Integer>();
		this.e = new ArrayList<Edge>();

		for (String string : edge) {
			String[] a = string.split(",");
			int src = Integer.parseInt(a[0]);
			int tgt = Integer.parseInt(a[1]);
			int weight = Integer.parseInt(a[2]);
			ver.add(src);
			ver.add(tgt);
			e.add(new Edge(src, tgt, weight));
		}

		this.v = ver;

		// add reserve graph
		HashSet<Edge> reserve = new HashSet<Edge>();
		for (Edge ed : this.e) {
			reserve.add(new Edge(ed.getTgt(), ed.getSrc(), 0));
		}

		for (Edge ed : reserve) {
			this.e.add(ed);
		}
	}

	public int dinic(int src, int tgt) {
		Path path = null;
		int totalCapacity = 0;
		while ((path = findShortestGreatestCapacityPath(src, tgt)) != null) {
			System.out.println(path);
			System.out.println(path.getCapacity());
			totalCapacity += path.getCapacity();
			modifyGraph(path);
		}
		return totalCapacity;
	}

	public Path findShortestGreatestCapacityPath(int src, int tgt) {
		HashMap<Integer, Integer> capa = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> prev = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> length = new HashMap<Integer, Integer>();
		Queue<Integer> queue = new LinkedList<Integer>();

		queue.add(src);
		capa.put(src, Integer.MAX_VALUE);
		length.put(src, 0);

		while (!queue.isEmpty()) {
			int n = queue.poll();

			/*
			System.out.println(n);
			System.out.println("cap " + capa.get(n));
			System.out.println("len " + length.get(n));
			System.out.println("prev " + prev.get(n));
			*/

			if (length.get(tgt) != null && length.get(tgt) < length.get(n)) {
				break;
			}
			LinkedList<Edge> edges = getEdgeFromSrc(n);
			for (Edge edge : edges) {
				int m = edge.getTgt();
				int c = edge.getWeight();

				if (prev.containsKey(m)) {
					// this vertex has been visited
					if (length.get(n) + 1 < length.get(m)) {
						// this new path is shorter
						// set capacity and prev
						prev.put(m, n);
						capa.put(m, Math.min(capa.get(n), c));
					} else {
						// this path is longer, nothing to do
					}
				} else {
					// this vertex has not been visited
					length.put(m, length.get(n) + 1);
					prev.put(m, n);
					capa.put(m, Math.min(c, capa.get(n)));
					queue.add(m);
				}
			}
		}

//		System.out.println(capa.get(tgt));

		if (capa.get(tgt) == null) {
			// no path
			return null;
		}

		Path p = new Path();
		p.setCapacity(capa.get(tgt));
		int n = tgt;
		p.addToHead(n);
		do {
			n = prev.get(n);
			p.addToHead(n);
		} while (n != src);

		return p;
	}

	private void modifyGraph(Path path) {
		LinkedList<Edge> es = path.modifyEdge();
		
		/*
		for (Edge ed : es) {
			System.out.println(ed.getSrc() +"," + ed.getTgt() + ","+ ed.getWeight());
		}
		*/
		for (Edge ed : es) {
			for (Edge ted : this.e) {
				if (ted.getSrc() == ed.getSrc() && ted.getTgt() == ed.getTgt()) {
					ted.setWeight(ted.getWeight() + ed.getWeight());
				}
			}
		}
	}

	private LinkedList<Edge> getEdgeFromSrc(int src) {
		LinkedList<Edge> edges = new LinkedList<Edge>();
		for (Edge edge : this.e) {
			if (edge.getSrc() == src && edge.getWeight() != 0) {
				edges.add(edge);
			}
		}
		return edges;
	}
}