package graph;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

import comparator.EdgeSTComp;
import comparator.IdCom;
import comparator.NodeIdComp;
import comparator.PostTimeCom;

public class Graph {
	private DecimalFormat df = new DecimalFormat("#.#");
	private HashMap<Integer, Node> v = null;
	private ArrayList<Edge> e = null;
	private int clock = 0;

	public Graph(String[] edge) {
		HashSet<Integer> ver = new HashSet<Integer>();
		this.v = new HashMap<Integer, Node>();
		this.e = new ArrayList<Edge>();

		for (String string : edge) {
			String[] a = string.split(",");
			int src = Integer.parseInt(a[0]);
			int tgt = Integer.parseInt(a[1]);
			double weight = Double.parseDouble(a[2]);
			ver.add(src);
			ver.add(tgt);
			e.add(new Edge(src, tgt, weight));
		}

		for (Integer integer : ver) {
			v.put(integer, new Node(integer));
		}
	}

	public HashMap<Integer, Node> getV() {
		return v;
	}

	public ArrayList<Edge> getE() {
		return e;
	}

	public ArrayList<Edge> edgeFromVertex(int id) {
		ArrayList<Edge> res = new ArrayList<Edge>();
		for (Edge edge : this.e) {
			if (edge.getSrc() == id) {
				res.add(edge);
			}
		}
		Collections.sort(res, new IdCom());
		return res;
	}

	public void dfs(int source) {
		this.clock = 1;
		explore(source);
	}

	private void explore(int vertex) {
		Node n = v.get(vertex);
		n.setPreVisit(this.clock);
		this.clock++;
		for (Edge e : edgeFromVertex(vertex)) {
			Node ver = v.get(e.getTgt());
			if (ver.getPreVisit() == 0) {
				explore(e.getTgt());
			}
		}

		n.setPostVisit(this.clock);
		this.clock++;

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

	public ArrayList<Node> topologicalSort() {
		ArrayList<Node> res = new ArrayList<Node>();
		for (Entry<Integer, Node> en : v.entrySet()) {
			res.add(en.getValue());
		}
		Collections.sort(res, new PostTimeCom());
		return res;
	}

	public void singleSourceShortestPath() {
		ArrayList<Node> topoList = topologicalSort();
		HashMap<Integer, Double> dist = new HashMap<Integer, Double>();
		HashMap<Integer, Integer> prev = new HashMap<Integer, Integer>();

		ArrayList<Integer> ids = new ArrayList<Integer>();
//		for (Integer id : v.keySet()) {
//			ids.add(id);
//		}
//		Collections.sort(ids);

		for (Node node : topoList) {
			dist.put(node.getId(), null);
			prev.put(node.getId(), null);
			ids.add(node.getId());
		}
		dist.put(topoList.get(0).getId(), 0.0);
		for (Node node : topoList) {
			ArrayList<Edge> edges = edgeFromVertex(node.getId());
			for (Edge edge : edges) {
				System.out.println();
				System.out.println("(" + edge.getSrc() + "," + edge.getTgt()
						+ ") in E");
				int src = edge.getSrc();
				int tgt = edge.getTgt();
				Double wei = edge.getWeight();
				Double srcwei = dist.get(src);
				Double tgtwei = dist.get(tgt);
				if (cmp(add(srcwei, wei), tgtwei) < 0) {
					System.out.println("\t" + tgt + ".dist > " + src
							+ ".dist + length(" + src + "," + tgt + ")");
					System.out.println("\t\t" + tgt + ".dist <- "
							+ df.format((srcwei + wei)));
					System.out.println("\t\t" + tgt + ".prev <- " + src);
					dist.put(tgt, srcwei + wei);
					prev.put(tgt, src);
				} else {
					System.out.println("\t" + tgt + ".dist <= " + src
							+ ".dist + length(" + src + "," + tgt + ")");

				}

				// show the vertex

				System.out.print("vert:");
				for (Integer id : ids) {
					System.out.print("\t" + id);
				}
				System.out.println();
				System.out.print("dist:");
				for (Integer id : ids) {
					if (dist.get(id) == null) {
						System.out.print("\tinf");
					} else {
						System.out.print("\t" + df.format(dist.get(id)));
					}
				}
				System.out.println();
				System.out.print("prev:");
				for (Integer id : ids) {
					System.out.print("\t" + prev.get(id));
				}
				System.out.println();

				// end show vertex
			}

		}
	}

	public void moore(int vertex) {
		ArrayList<Node> list = new ArrayList<Node>();
		for (Integer id : v.keySet()) {
			list.add(new Node(id));
		}
		Collections.sort(list, new NodeIdComp());

		HashMap<Integer, Double> dist = new HashMap<Integer, Double>();
		HashMap<Integer, Integer> prev = new HashMap<Integer, Integer>();
		HashSet<Integer> withDist = new HashSet<Integer>();
		HashSet<Integer> withoutDist = new HashSet<Integer>();

		for (Node node : list) {
			dist.put(node.getId(), (double) 100);
			prev.put(node.getId(), null);
		}
		dist.put(vertex, (double) 0);
		withDist.add(vertex);

		//
		System.out.println("at iteration: " + 0);
		System.out.print("id:");
		for (Node node : list) {
			System.out.print("\t" + node.getId());
		}
		System.out.println();
		System.out.print("dis:");
		for (Node node : list) {
			System.out.print("\t" + dist.get(node.getId()));
		}
		System.out.println();
		System.out.print("pre:");
		for (Node node : list) {
			System.out.print("\t" + prev.get(node.getId()));
		}
		System.out.println("\n");

		//

		for (int i = 1; i < list.size(); i++) {

			ArrayList<Edge> edges = new ArrayList<Edge>();
			for (Integer id : withDist) {
				edges.addAll(edgeFromVertex(id));
			}
			Collections.sort(edges, new IdCom());

			for (Edge edge : edges) {
				int src = edge.getSrc();
				int tgt = edge.getTgt();
				double wei = edge.getWeight();
				double srcwei = dist.get(src);
				double tgtwei = dist.get(tgt);
				if (srcwei + wei < tgtwei) {
					dist.put(tgt, srcwei + wei);
					prev.put(tgt, src);
					withDist.add(tgt);
				} else {
				}
			}

			// show the vertices
			System.out.println("at iteration: " + i);
			System.out.print("id:");
			for (Node node : list) {
				System.out.print("\t" + node.getId());
			}
			System.out.println();
			System.out.print("dis:");
			for (Node node : list) {
				System.out.print("\t" + dist.get(node.getId()));
			}
			System.out.println();
			System.out.print("pre:");
			for (Node node : list) {
				System.out.print("\t" + prev.get(node.getId()));
			}
			System.out.println("\n");
			// end
		}
	}

	public void moore2(int vertex) {
		ArrayList<Node> list = new ArrayList<Node>();
		for (Integer id : v.keySet()) {
			list.add(new Node(id));
		}
		Collections.sort(list, new NodeIdComp());

		HashMap<Integer, Double> dist = new HashMap<Integer, Double>();
		HashMap<Integer, Integer> prev = new HashMap<Integer, Integer>();

		for (Node node : list) {
			dist.put(node.getId(), null);
			prev.put(node.getId(), null);
		}
		dist.put(vertex, (double) 0);

		//
		System.out.println("at iteration: " + 0);
		System.out.print("id:");
		for (Node node : list) {
			System.out.print("\t" + node.getId());
		}
		System.out.println();
		System.out.print("dis:");
		for (Node node : list) {
			if (dist.get(node.getId()) == null) {
				System.out.print("\tinf");
			} else {
				System.out.print("\t" + dist.get(node.getId()));
			}
		}
		System.out.println();
		System.out.print("pre:");
		for (Node node : list) {
			System.out.print("\t" + prev.get(node.getId()));
		}
		System.out.println("\n");
		//

		ArrayList<Edge> edges = this.e;
		Collections.sort(edges, new EdgeSTComp());
		for (int i = 1; i < list.size(); i++) {

			for (Edge edge : edges) {

				int src = edge.getSrc();
				int tgt = edge.getTgt();
				System.out.println("----edge: (" + src + "," + tgt + ")");
				Double wei = edge.getWeight();
				Double srcwei = dist.get(src);
				Double tgtwei = dist.get(tgt);

				if (cmp(add(srcwei, wei), tgtwei) < 0) {
					dist.put(tgt, srcwei + wei);
					prev.put(tgt, src);
				} else {
				}

				System.out.print("-----id:");
				for (Node node : list) {
					System.out.print("\t" + node.getId());
				}
				System.out.println();
				System.out.print("----dis:");
				for (Node node : list) {
					if (dist.get(node.getId()) == null) {
						System.out.print("\tinf");
					} else {
						System.out.print("\t" + dist.get(node.getId()));
					}
				}
				System.out.println();
				System.out.print("----pre:");
				for (Node node : list) {
					System.out.print("\t" + prev.get(node.getId()));
				}
				System.out.println("\n");

			}

			// show the vertices
			System.out.println("at iteration: " + i);
			System.out.print("id:");
			for (Node node : list) {
				System.out.print("\t" + node.getId());
			}
			System.out.println();
			System.out.print("dis:");
			for (Node node : list) {
				if (dist.get(node.getId()) == null) {
					System.out.print("\tinf");
				} else {
					System.out.print("\t" + dist.get(node.getId()));
				}
			}
			System.out.println();
			System.out.print("pre:");
			for (Node node : list) {
				System.out.print("\t" + prev.get(node.getId()));
			}
			System.out.println("\n");
			// end
		}

	}
}
