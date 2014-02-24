package gasStation;

import java.util.Stack;

public class Solution {
	Stack<Integer> from = new Stack<Integer>();
	Stack<Integer> to = new Stack<Integer>();

	int[][] potential = null;
	boolean[][] hasPotential = null;
	boolean[][] reachable = null;
	int len = 0;

	int[] gas = null;
	int[] cost = null;

	public int canCompleteCircuit(int[] gas, int[] cost) {
		this.gas = gas;
		this.cost = cost;
		this.len = gas.length;
		this.potential = new int[len][len];
		this.hasPotential = new boolean[len][len];
		this.reachable = new boolean[len][len];

		for (int i = 0; i < len; i++) {
			from.push(i);
			to.push(i);
			potential[i][(i + 1) % len] = gas[i] - cost[i];
			hasPotential[i][(i + 1) % len] = true;
			if (potential[i][(i + 1) % len] >= 0) {
				reachable[i][(i + 1) % len] = true;
			}
		}

		while (!this.from.empty()) {
			int f = from.peek();
			int t = to.peek();
			

			if (potential(f, t)) {
				// p(i,j) has calculated
				from.pop();
				to.pop();
				System.out.println("pop: from = " + f+ ", to = " + t);
				if (f == t && reachable[f][t] == true) {
//					System.out.println("reachable");
//					showReachable();
					return f;
				}
			}
			
//			System.out.println("reachable");
//			showReachable();
		}

		return -1;
	}

	/**
	 * int * 2 -> boolean given index of two station return true if potential
	 * between is computer, otherwise return false
	 */
	private boolean potential(int i, int j) {
		if (hasPotential[i][j] == true) {
			return true;
		}
		if ((i + 1 == j) || ((i == len - 1) && j == 0)) {
			// step == 1
			potential[i][j] = gas[i] - cost[i];
			hasPotential[i][j] = true;
			if (potential[i][j] >= 0) {
				reachable[i][j] = true;
			}
			return true;
		} else if (i == this.len - 1) {
			// p(i,j) = p(i,0) + p(0,j)
			if (checkPotential(i, 0) && checkPotential(0, j)) {
				potential[i][j] = potential[i][0] + potential[0][j];
				hasPotential[i][j] = true;
				/*
				if (potential[i][0] >= 0 && reachable[0][j] == true) {
					reachable[i][0] = true;
				}
				*/
				if (potential[i][0] >=0 && potential[i][j] >= 0 && reachable[i][(len+j-1)%len]){
					reachable[i][j] = true;
				}
				return true;
			} else {
				return false;
			}
		} else {
			// p(i,j) = p(i,i+1) + p(i+1,j)
			if (checkPotential(i, i + 1) && checkPotential(i + 1, j)) {
				potential[i][j] = potential[i][i + 1] + potential[i + 1][j];
				hasPotential[i][j] = true;
				/*
				if (potential[i][i + 1] >= 0 && reachable[i + 1][j] == true) {
					reachable[i][i + 1] = true;
				}
				*/
				if (potential[i][i + 1] >=0 && potential[i][j] >= 0 && reachable[i][(len+j-1)%len]){
					reachable[i][j] = true;
				}
				return true;
			} else {
				return false;
			}
		}

	}

	/**
	 * int * 2 -> boolean check iff the potential between two station has been
	 * calculated return true iff calculated, otherwise return false and push it
	 * into stack
	 */
	private boolean checkPotential(int i, int j) {
		if (hasPotential[i][j]) {
			return true;
		} else {
			from.push(i);
			to.push(j);
			return false;
		}
	}

	private void showReachable() {
//		System.out.println(reachable.length);
//		System.out.println(reachable[0].length);
		System.out.println(len);
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				System.out.print(i+","+j+": ");
				if (reachable[i][j]){
					System.out.print("t\t");
				}else{
					System.out.print("_\t");
				}
			}
			System.out.println("");
		}
	}

	public static void main(String[] args) {
		//                       0, 1, 2, 3, 4, 5, 6,  7
		int[] gas = new int[] {  0, 1, 2, 3, 4, 5, 15, 6 };
		int[] cost = new int[] { 1, 2, 3, 4, 5, 6, 7, 3 };
		Solution s = new Solution();
		System.out.println(s.canCompleteCircuit(gas, cost));
	}
}