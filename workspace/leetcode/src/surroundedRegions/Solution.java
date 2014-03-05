package surroundedRegions;

public class Solution {
	public void solve(char[][] board) {
		int len = board.length;
		for (int i = 1; i < len / 2; i++) {
			for (int j = i; j < len - i; j++) {
				if (board[j][i] == 'O') {
					// the upper line
					if (board[j][i - 1] != 'O') {
						board[j][i] = 'X';
					}
				}
				if (board[j][len - i] == 'O') {
					// the lower line
					if (board[j][len - i + 1] != 'O') {
						board[j][len - i] = 'X';
					}
				}
				if (board[i][j] == 'O') {
					// the left line
					if (board[i - 1][j] != 'O') {
						board[i][j] = 'X';
					}
				}
				if (board[len - i][j] == 'O') {
					// the right line
					if (board[len - i + 1][j] != 'O') {
						board[len - i][j] = 'X';
					}
				}
			}
		}

		if (len % 2 == 1) {
			int i = len / 2 + 1;
			if (board[i - 1][i] == 'X' && board[i + 1][i] == 'X'
					&& board[i][i - 1] == 'X' && board[i][i + 1] == 'X') {
				board[i][i] = 'X';
			}
		}
		
		for (int i = 0 ; i < board.length; i++){
			for (int j = 0; j < board.length; j++)
		}
	}
}