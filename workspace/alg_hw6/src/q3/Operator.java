package q3;

public class Operator {
	public static int cmp(Double d1, Double d2) {
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
	
	public static Double add(Double a, Double b){
		if (a == null || b == null){
			return null;
		}
		return a + b;
	}

}
