package validPalindrome;

public class Solution {
	public boolean isPalindrome(String s) {
		int len = s.length();
		int j = s.length() - 1;
		if (j == 0) {
			return true;
		}
		char[] array = s.toCharArray();
		int i = 0;
		while (i < j) {
			while (!isAlphanumeric(array[i]) && i < len - 1) {
				i++;
			}
			while (!isAlphanumeric(array[j]) && j > 0) {
				j--;
			}
			if (i >= j){
				return true;
			}
			if (caseInSensitiveCompare(array[i], array[j])) {
				i++;
				j--;
			} else {
				return false;
			}

		}
		return true;
	}

	private boolean caseInSensitiveCompare(char c1, char c2) {
		c1 = toLowerCase(c1);
		c2 = toLowerCase(c2);
		return c1 == c2;
	}

	private char toLowerCase(char c) {
		if ((int) c >= (int) 'A' && (int) c <= (int) 'Z') {
			c = (char) ((int) c + (int) 'a' - (int) 'A');
		}
		return c;
	}

	private boolean isAlphanumeric(char c) {
		return (int) c >= (int) 'a' && (int) c <= (int) 'z'
				|| (int) c >= (int) 'A' && (int) c <= (int) 'Z'
				|| (int) c >= (int) '0' && (int) c <= (int) '9';
	}

	public static void main(String[] args) {
		Solution s = new Solution();
		String str = "ab,.ba";
		System.out.println(s.isPalindrome(str));
	}
}