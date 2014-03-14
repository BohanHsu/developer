package surroundedRegions;

import java.util.LinkedList;

public class Solution {

	int weight = 0;
	int height = 0;

	public void solve(char[][] board) {
		LinkedList<Integer> xQueue = new LinkedList<Integer>();
		LinkedList<Integer> yQueue = new LinkedList<Integer>();
		this.height = board.length;

		if (height == 0) {
			return;
		}

		this.weight = board[0].length;

		if (weight == 0) {
			return;
		}

		boolean[][] mask = new boolean[height][weight];

		for (int i = 0; i < weight; i++) {
			if (board[0][i] == 'O') {
				yQueue.add(0);
				xQueue.add(i);
				mask[0][i] = true;
			}
			if (board[height - 1][i] == 'O') {
				yQueue.add(height - 1);
				xQueue.add(i);
				mask[height - 1][i] = true;
			}
		}

		for (int i = 1; i < height - 1; i++) {
			if (board[i][0] == 'O') {
				yQueue.add(i);
				xQueue.add(0);
				mask[i][0] = true;
			}
			if (board[i][weight - 1] == 'O') {
				yQueue.add(i);
				xQueue.add(weight - 1);
				mask[i][weight-1] = true;
			}
		}
		// start points are in Queue

		while (!xQueue.isEmpty()) {
			int x = xQueue.poll();
			int y = yQueue.poll();

			if (isLegal(x - 1, y)) {
				if ((!mask[y][x - 1]) && (board[y][x - 1] == 'O')) {
					mask[y][x - 1] = true;
					xQueue.add(x - 1);
					yQueue.add(y);
				}
			}

			if (isLegal(x + 1, y)) {
				if ((!mask[y][x + 1]) && (board[y][x + 1] == 'O')) {
					mask[y][x + 1] = true;
					xQueue.add(x + 1);
					yQueue.add(y);
				}
			}

			if (isLegal(x, y - 1)) {
				if ((!mask[y - 1][x]) && (board[y - 1][x] == 'O')) {
					mask[y - 1][x] = true;
					xQueue.add(x);
					yQueue.add(y - 1);
				}
			}

			if (isLegal(x, y + 1)) {
				if ((!mask[y + 1][x]) && (board[y + 1][x] == 'O')) {
					mask[y + 1][x] = true;
					xQueue.add(x);
					yQueue.add(y + 1);
				}
			}
		}

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < weight; j++) {
				if (mask[i][j]) {
					board[i][j] = 'O';
				} else {
					board[i][j] = 'X';
				}
			}
		}

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < weight; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}

	}

	/**
	 * check if a coordinate is legal
	 */
	private boolean isLegal(int x, int y) {
		return x < weight && x >= 0 && y < height && y >= 0;
	}

	public static void main(String[] args) {
		Solution s = new Solution();
		// char board[][] = new char[][] { { 'X', 'X', 'X', 'X' },
		// { 'X', 'O', 'O', 'X' }, { 'X', 'X', 'O', 'X' },
		// { 'X', 'O', 'X', 'X' } };
		char board[][] = new char[][] { { 'X', 'O', 'X' }, { 'O', 'X', 'O' },
				{ 'X', 'O', 'X' } };
		s.solve(board);

	}
}