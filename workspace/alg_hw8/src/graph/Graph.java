package graph;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

public class Graph {
	private boolean isDirected = false;

	private HashSet<Integer> v = null;
	private ArrayList<Edge> e = null;
	private HashMap<Integer, Integer> root = new HashMap<Integer, Integer>();

	DecimalFormat udf = new DecimalFormat("#.#");

	public Graph(String[] edge) {
		HashSet<Integer> ver = new HashSet<Integer>();
		this.v = new HashSet<Integer>();
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

		this.v = ver;
	}

	public ArrayList<Edge> kruskal() {
		//
		System.out.println("begin kruskal");
		//
		ArrayList<Edge> mst = new ArrayList<Edge>();
		ArrayList<Edge> sortedEdges = new ArrayList<Edge>(this.e);
		Collections.sort(sortedEdges, new EdgeWeightComparator());
		
		ArrayList<Integer> vertices = new ArrayList<Integer>(this.v);
		Collections.sort(vertices);

		resetUnionFindRoot();

		// show sorted edges
		// System.out.println(sortedEdges);

		System.out.println("// after sort edges by weight:");
		
		for (Edge edge : sortedEdges) {
			System.out.println("{" + edge.getSrc() + "," + edge.getTgt()
					+ "}  " + udf.format(edge.getWeight()));
		}
		//
		
		//
		System.out.println("for all v in V: ");
		System.out.println("\tmakeset(v)");
		
		System.out.print("//\tX = {");
		for (Edge edge : mst) {
			System.out.print("{"+edge.getSrc()+","+edge.getTgt()+"} ");
		}
		System.out.println("}");
		
		System.out.print("//");
		for (Integer i : vertices) {
			System.out.print("\t"+i+".root = "+this.root.get(i));
		}
		System.out.println();
		//

		for (Edge e : sortedEdges) {
			int u = e.getSrc();
			int v = e.getTgt();

			System.out.println("For edge {" + u + "," + v + "} in E");

			if (find(u) != find(v)) {
				//
				System.out.println("AS (find(" + u + ") != find(" + v + "))");
				//
				mst.add(e);
				
				
				//
				System.out.println("add edge {" + u + "," + v + "} to X");
				System.out.println("union(" + u + "," + v + ")");
				//
				union(u, v);

			} else {
				System.out.println("AS (find(" + u + ") == find(" + v + "))");
				System.out.println("// Union not possible both has same subset ");
			}
			//
			System.out.print("//\tX = {");
			for (Edge edge : mst) {
				System.out.print("{"+edge.getSrc()+","+edge.getTgt()+"} ");
			}
			System.out.println("}");
			//
			//
			System.out.print("//");
			for (Integer i : vertices) {
				System.out.print("\t"+i+".root = "+this.root.get(i));
			}
			System.out.println();
			//
		}
		
		//
		System.out.println("end kruskal");
		//
		return mst;
	}

	/**
	 * union two vertex
	 * 
	 * @param x
	 *            : the id of first vertex
	 * @param y
	 *            : the id of second vertex
	 */
	private void union(int x, int y) {
		//
		System.out.println("begin union(" + x + "," + y + ")");
		//
		//
		System.out.println("xRoot = find("+x+")");
		//
		int xRoot = find(x);
		//
		System.out.println("yRoot = find("+y+")");
		//
		int yRoot = find(y);
		// the new root is from the tree specified by the first parameter to
		// union.
		// y.parent <- x
		this.root.put(yRoot, xRoot);
		System.out.println(yRoot+".parent = "+xRoot);
		//
		System.out.println("end union(" + x + "," + y + ")");
		//
		
	}

	/**
	 * find parent of a vertex
	 * 
	 * @param x
	 *            : id of the vertex need to find root
	 * @return : id of the root vertex
	 */
	private int find(int x) {
		//
		System.out.println("begin find(" + x + ")");
		//
		int xRoot = this.root.get(x);
		if (xRoot == x) {
			//
			System.out.println(x + ".Root == " + x + "\nreturn " + x
					+ "\nend find(" + x + ")");
			//
			return x;
		} else {
			//
			System.out.println(x + ".Root != " + x + " \nreturn find(" + xRoot
					+ ")");
			//
			int result = find(xRoot);
			//
			System.out.println("end find(" + x + ")");
			//
			return result;
		}

	}

	/**
	 * reset the union find root of each vertex in the graph, set the root to
	 * the vertex itself
	 */
	private void resetUnionFindRoot() {
		this.root = new HashMap<Integer, Integer>();
		for (Integer id : this.v) {
			this.root.put(id, id);
		}
	}

	HashMap<Integer, Double> pq = null;
	HashMap<Integer, Integer> prev = null;
	HashMap<Integer, Double> cost = null;

	/**
	 * delete the minimum value entry in the cost hash map
	 * 
	 * @return the key of the minimum entry before remove
	 */
	private int deleteMin() {
		int index = 0;
		Double min = null;
		for (Integer i : this.pq.keySet()) {
			if (operator.Operator.cmp(this.pq.get(i), min) < 0) {
				index = i;
				min = this.pq.get(i);
			}
		}

		this.pq.remove(index);
		return index;
	}

	public void jarnik(int source) {
		ArrayList<Integer> visited = new ArrayList<Integer>();

		ArrayList<Integer> vertexList = new ArrayList<>(this.v);
		Collections.sort(vertexList);

		// make queue
		this.pq = new HashMap<Integer, Double>();
		this.prev = new HashMap<Integer, Integer>();
		this.cost = new HashMap<Integer, Double>();
		for (Integer vertex : this.v) {
			this.pq.put(vertex, null);
			this.prev.put(vertex, null);
			this.cost.put(vertex, null);
		}
		this.pq.put(source, (double) 0);
		this.cost.put(source, (double) 0);
		
		// show set
		System.out.print(visited + "\t");
		// end

		// show vertices
		DecimalFormat df = new DecimalFormat("#");
		for (Integer integer : vertexList) {
			Double ct = this.cost.get(integer);
			if (ct == null) {
				System.out.print("nil");
			} else {
				System.out.print(df.format(ct));
			}
			System.out.print("|" + this.prev.get(integer) + "\t");
		}
		System.out.println();
		// end

		// loop
		while (!this.pq.isEmpty()) {
			int v = deleteMin();

			visited.add(v);

			for (Edge edge : edgeOfVertex(v, visited)) {
				int z = 0;
				if (edge.getSrc() == v) {
					z = edge.getTgt();
				} else {
					z = edge.getSrc();
				}

				Double zCost = this.cost.get(z);
				Double w = edge.getWeight();
				if (operator.Operator.cmp(zCost, w) > 0) {

					this.cost.put(z, w);
					this.prev.put(z, v);
					// decrease-key
					this.pq.put(z, w);
				}
			}
			
			// show set
			System.out.print(visited + "\t");
			// end

			// show vertices
			for (Integer integer : vertexList) {
				Double ct = this.cost.get(integer);
				if (ct == null) {
					System.out.print("nil");
				} else {
					System.out.print(df.format(ct));
				}
				System.out.print("|" + this.prev.get(integer) + "\t");
			}
			System.out.println();
			// end


		}

	}

	/**
	 * find all the edges in the graph include with one particular vertex
	 * 
	 * @param vertex
	 *            : the particular vertex
	 * @return a ArrayList contain all the edges contain those edges
	 */
	private ArrayList<Edge> edgeOfVertex(int vertex, ArrayList<Integer> visited) {
		ArrayList<Edge> edges = new ArrayList<Edge>();
		for (Edge edge : this.e) {
			if (edge.getSrc() == vertex) {
				if (!visited.contains(edge.getTgt())) {
					edges.add(edge);
				}
			}

			if (edge.getTgt() == vertex) {
				if (!visited.contains(edge.getSrc())) {
					edges.add(edge);
				}
			}
		}
		return edges;
	}

}
