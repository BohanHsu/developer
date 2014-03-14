package q3;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class Q2 {
	DecimalFormat df = new DecimalFormat("#");
	HashMap<Integer, Double>[][] dist = null;

	private ArrayList<Integer> v = null;
	private ArrayList<Edge> e = null;

	public Q2(String[] edge) {
		HashSet<Integer> ver = new HashSet<Integer>();
		this.v = new ArrayList<Integer>();
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
			this.v.add(integer);
		}
		// sort the list
		Collections.sort(this.v);
		this.dist = new HashMap[this.v.size()][this.v.size()];

		for (int i = 0; i < this.v.size(); i++) {
			for (int j = 0; j < this.v.size(); j++) {
				dist[i][j] = new HashMap<Integer, Double>();
				// dist[i][j].put(0, null);
				if (i == j){
					dist[i][j].put(0, (double) 0);
				} else{
				dist[i][j].put(0, weigthOfEdge(i, j));
				}
			}
		}
	}

	private Double weigthOfEdge(int i, int j) {
		int src = this.v.get(i);
		int tgt = this.v.get(j);

		for (Edge edge : this.e) {
			if (edge.getSrc() == src && edge.getTgt() == tgt) {
				return edge.getWeight();
			}
			// if (edge.getSrc() == tgt && edge.getTgt() == src){
			// return edge.getWeight();
			// }
		}

		return null;
	}

	public void allPairShortestPath() {
		showMatrix(0);
		for (int k = 1; k <= this.v.size(); k++) {
			for (int i = 0; i < this.v.size(); i++) {
				for (int j = 0; j < this.v.size(); j++) {
					Double distijk_1 = this.dist[i][j].get(k - 1);
					Double distikk_1 = this.dist[i][k-1].get(k - 1);
					Double distkjk_1 = this.dist[k-1][j].get(k - 1);
					Double distijk = Operator.add(distikk_1, distkjk_1);
					if (Operator.cmp(distijk, distijk_1) < 0) {
						this.dist[i][j].put(k, distijk);
					} else {
						this.dist[i][j].put(k, distijk_1);
					}
				}
			}
			showMatrix(k);
		}
	}

	private void showMatrix(Integer k) {
		System.out.println("k = " + k);
		System.out.print("\t");
		for (int i = 0; i < this.v.size(); i++) {
			System.out.print(this.v.get(i) + "\t");
		}
		System.out.println();

		
		for (int i = 0; i < this.v.size(); i++) {
			System.out.print(this.v.get(i) + "\t");
			for (int j = 0; j < this.v.size(); j++) {
				if (this.dist[i][j].get(k) == null) {
					System.out.print("inf\t");
				} else {
					System.out.print(df.format(this.dist[i][j].get(k)) + "\t");
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void main(String[] args) {
		String[] edge = new String[] { "1,3,-3", "1,4,1", "2,4,7", "2,5,8",
				"3,4,8", "3,5,9", "4,2,2", "4,3,10", "4,5,9", "5,3,-1" };
		Q2 q = new Q2(edge);
		q.allPairShortestPath();
	}

}

/*
 * 
 * "1,3,-3", "1,4,1", "2,4,7", "2,5,8", "3,4,8", "3,5,9", "4,2,2", "4,3,10",
 * "4,5,9", "5,3,-1"
 */