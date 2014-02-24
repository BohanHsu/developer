package candy;

import java.util.Stack;

public class Solution {
	int[] c = null;
	int[] ratings = null;
	int index = 0;
	Stack<Integer> stack = new Stack<Integer>();

	public int candy(int[] ratings) {
		int len = ratings.length;
		c = new int[len];
		this.ratings = ratings;

		for (int i = 0; i < len; i++) {
			c[i] = -1;
		}

		for (int i = 0; i < len; i++) {
			this.stack.push(i);
			while (!this.stack.empty()) {
				int index = this.stack.peek();
				if (compare(index)) {
					this.stack.pop();
				}

			}
		}

		int mc = 0;
		for (int i = 0; i < len; i++) {
			mc = mc + c[i];
		}

		return mc;
	}

	/**
	 * int * 3 -> void
	 */
	private boolean compare(int i) {
		if (this.c[i] != -1) {
			return true;
		}

		boolean seted = true;
		int l = getR(i - 1);
		int m = getR(i);
		int r = getR(i + 1);

		int min = Math.min(l, r);
		int max = Math.max(l, r);

		int c = 0;

		if (m < min) {
			// c[i] <- 1
			c = 1;
		} else if (m == min) {
			// c[i] <- 1
			c = 1;
		} else if (m < max && m > min) {
			// c[i] <- min(getC(i-1),getC(i+1)) + 1
			int small = l < r ? i - 1 : i + 1;

			// need to check
			if (getC(small) == -1) {
				// value uncalculated, push to stack
				this.stack.push(small);
				seted = false;
			} else {
				c = getC(small) + 1;
			}
		} else if (m == max) {
			// c[i] <- min(getC(i-1),getC(i+1)) + 1
			int small = l < r ? i - 1 : i + 1;

			// need to check
			if (getC(small) == -1) {
				// value uncalculated, push to stack
				this.stack.push(small);
				seted = false;
			} else {
				c = getC(small) + 1;
			}
		} else if (m > max) {
			// c[i] <- max(getC(i-1),getC(i+1)) + 1

			// need to check both
			if (getC(i - 1) == -1) {
				// push i-1
				this.stack.push(i - 1);
				seted = false;
			} else if (getC(i + 1) == -1) {
				// push i+1
				this.stack.push(i + 1);
				seted = false;
			} else {
				c = Math.max(getC(i - 1), getC(i + 1)) + 1;
			}
		}
		if (seted) {
			this.c[i] = c;
		}
		return seted;
	}

	/**
	 * int -> int
	 * 
	 * @param i
	 *            : the index of ratings return : max value if index out of
	 *            boundary, otherwise return ratings[i]
	 */
	private int getR(int i) {
		if (i == -1) {
			return Integer.MAX_VALUE;
		} else if (i == this.ratings.length) {
			return Integer.MAX_VALUE;
		} else {
			return this.ratings[i];
		}
	}

	/**
	 * int -> int
	 * 
	 * @param i
	 *            : the index of i iff c[i] haven't been compute, first compute
	 *            c[i]
	 */
	private int getC(int i) {
		if (i < 0 || i >= this.c.length) {
			return 0;
		}
		return this.c[i];

	}

	/*
	 * private int[] naiveCandy(int[] ratings){ int[] n = new
	 * int[ratings.length];
	 * 
	 * return n; }
	 */

	public static void main(String[] args) {
		Solution s = new Solution();
		/*
		 * int[] ratings = new int[12000]; for (int i = 0; i < 12000; i++) {
		 * ratings[i] = 12000 - i; }
		 */

		int[] ratings = new int[] { 1, 2, 3, 4, 5, 6, 8, 1, 0, 4, 5, 2, 1 };
		int mc = s.candy(ratings);

		System.out.println(mc);
	}
}