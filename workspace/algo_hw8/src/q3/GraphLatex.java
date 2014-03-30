package q3;

import java.awt.PageAttributes.OriginType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GraphLatex {
	Graph g = null;
	private final String BEGINNODE = "\\begin{tikzpicture}[->,>=stealth',shorten >=1pt,auto,node distance=3cm,thick,main node/.style={circle,fill=blue!20,draw,font=\\sffamily\\Large\\bfseries}]";
	private final String BEGINEDGE = "\\path[every node/.style={font=\\sffamily\\small}]";
	private final String END = "\\end{tikzpicture}";
	private HashMap<Integer, Integer> xc = null;
	private HashMap<Integer, Integer> yc = null;
	private HashMap<Integer, String> label = null;
	private boolean uesPrintedSet = false;

	public GraphLatex(Graph g, HashMap<Integer, Integer> xc,
			HashMap<Integer, Integer> yc, HashMap<Integer, String> label) {
		super();
		this.g = g;
		this.xc = xc;
		this.yc = yc;
		this.label = label;
	}

	public GraphLatex(HashMap<Integer, Integer> xc,
			HashMap<Integer, Integer> yc, HashMap<Integer, String> label) {
		super();
		this.xc = xc;
		this.yc = yc;
		this.label = label;
		uesPrintedSet = true;
	}

	public void setG(Graph g) {
		this.g = g;
	}

	public String toLatex() {
		String str = "";
		str += BEGINNODE + "\n";
		HashSet<Integer> v = this.g.getV();
		ArrayList<Edge> e = this.g.getE();
		HashSet<Edge> ori = this.g.getOriginal();
		for (Integer i : v) {
			if (label != null) {
				str += "  \\node[main node] (" + i + ") at (" + xc.get(i) + ","
						+ yc.get(i) + ") {" + label.get(i) + "};\n";
			} else {
				str += "  \\node[main node] (" + i + ") at (" + xc.get(i) + ","
						+ yc.get(i) + ") {" + i + "};\n";
			}
		}
		str += "\n";
		str += BEGINEDGE + "\n";

		HashSet<Edge> printed = new HashSet<Edge>();

		for (Edge edge : e) {
			if (edge.getWeight() > 0) {
				if (ori.contains(edge)) {
					// forward edge
					if (uesPrintedSet) {
						if (!reservePrinted(printed, edge)) {
							str += "  (" + edge.getSrc() + ") edge node {"
									+ edge.getWeight() + "} (" + edge.getTgt()
									+ ")\n";
						} else {
							str += "  (" + edge.getSrc()
									+ ") edge [bend right] node  {"
									+ edge.getWeight() + "} (" + edge.getTgt()
									+ ")\n";
						}
					} else {
						str += "  (" + edge.getSrc() + ") edge node {"
								+ edge.getWeight() + "} (" + edge.getTgt()
								+ ")\n";
					}
				} else {
					// back edge
					str += "  (" + edge.getSrc()
							+ ") edge [bend right] node  {" + edge.getWeight()
							+ "} (" + edge.getTgt() + ")\n";
				}
			}
			printed.add(edge);
		}
		str += ";\n";
		str += END;
		return str;
	}

	private boolean reservePrinted(HashSet<Edge> printed, Edge e) {
		for (Edge edge : printed) {
			if (edge.getSrc() == e.getTgt() && edge.getTgt() == e.getSrc()) {
				return true;
			}
		}

		return false;
	}
}

/*
 * \begin{tikzpicture}[->,>=stealth',shorten >=1pt,auto,node distance=3cm,
 * thick,main
 * node/.style={circle,fill=blue!20,draw,font=\sffamily\Large\bfseries}]
 * 
 * \node[main node] (1) at (5,5) {S}; \node[main node] (2) at (8, 7) {X};
 * \node[main node] (3) at (8, 5) {Y}; \node[main node] (4) at (8, 3) {Z};
 * \node[main node] (5) at (12, 8) {A}; \node[main node] (6) at (12, 6) {B};
 * \node[main node] (7) at (12, 4) {C}; \node[main node] (8) at (12, 2) {D};
 * \node[main node] (9) at (15, 5) {T};
 * 
 * \path[every node/.style={font=\sffamily\small}] (1) edge node {2} (2) (1)
 * edge node {2} (3) (1) edge node {3} (4) (2) edge node {2} (5) (2) edge node
 * {2} (6) (3) edge node {2} (7) (3) edge node {2} (8) (4) edge node {3} (6) (4)
 * edge node {3} (7) (5) edge node {2} (9) (6) edge node {1} (9) (7) edge node
 * {1} (9) (8) edge node {2} (9);
 * 
 * \end{tikzpicture}
 */