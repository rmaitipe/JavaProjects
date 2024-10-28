package LeetCode;

public class Word_Search_079 {
	/*
	 * Given an m x n grid of characters board and a string word, return true if word exists in the grid.
	 * The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are
	 * horizontally or vertically neighboring. The same letter cell may not be used more than once.
	 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"		Output: true
	 */
	public static void main(String args[]) {
		Word_Search_079 ob = new Word_Search_079();
		char[][] scores = new char[3][4];
		scores[0] = new char[]{'a','b','c','e'};
		scores[1] = new char[]{'s','f','c','s'};
		scores[2] = new char[]{'a','d','e','e'};
		String wordStr = "abcced";
		System.out.println(ob.exist(scores,wordStr));
	}

	public boolean exist(char[][] board, String word) {
		int m = board.length;
		int n = board[0].length;
		boolean[][] visited = new boolean[m][n];
		boolean result = false;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] == word.charAt(0)) {
					result = backtrack(board, word, visited, i, j, 0);
					if (result) return true;
				}
			}
		}
		return false;
	}

	private boolean backtrack(char[][] board, String word, boolean[][] visited, int i, int j, int index) {
		if (index == word.length()) {
			return true;
		}
		if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || visited[i][j] || board[i][j] != word.charAt(index)) {
			return false;
		}
		visited[i][j] = true;
		// Recursively explore all four directions (up, down, left, right) return true for any callback returning true
		if (backtrack(board, word, visited, i + 1, j, index + 1) ||
			backtrack(board, word, visited, i - 1, j, index + 1) ||
			backtrack(board, word, visited, i, j + 1, index + 1) ||
			backtrack(board, word, visited, i, j - 1, index + 1)) {
			return true;
		}
		visited[i][j] = false;
		return false;
	}
}
