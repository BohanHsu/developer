package q3;

public class Question {
	public static void main(String[] args) {
		
	String[] edge = new String[] { "1,3,19", "1,9,2", "2,1,20", "2,3,3",
			"3,7,4", "3,8,4", "4,2,7", "4,5,19", "4,7,19", "5,3,18", "5,6,21",
			"5,8,11", "6,4,12", "7,2,6", "7,6,21", "8,6,9", "9,2,22", "9,4,10" };
	Graph g = new Graph(edge);
//	Path p = g.findShortestGreatestCapacityPath(5, 2);
//	System.out.println(p);
//	System.out.println(p.getCapacity());
	int tc = g.dinic(5, 2);
	System.out.println(tc);
	}
}
