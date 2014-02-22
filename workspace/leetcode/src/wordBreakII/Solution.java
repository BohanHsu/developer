package wordBreakII;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Solution {
	/*
	 * public ArrayList<String> wordBreak(String s, Set<String> dict) {
	 * ArrayList<String> strings = new ArrayList<String>(); int len =
	 * s.length(); boolean[] inDict = new boolean[len + 1];
	 * LinkedList<LinkedList<Integer>>[] path = new LinkedList[len + 1];
	 * 
	 * inDict[0] = true; for (int i = 1; i < len + 1; i++) { inDict[i] = false;
	 * }
	 * 
	 * boolean in = false; String testPart = null; for (int i = 1; i < len + 1;
	 * i++) { in = false; for (int j = 0; j < i; j++) { if (inDict[j]) { //
	 * inDict[j] is true, test the last part testPart = s.substring(j, i); if
	 * (dict.contains(testPart)) { in = true; if (path[i] == null) { path[i] =
	 * new LinkedList<LinkedList<Integer>>(); } if (j == 0) {
	 * LinkedList<Integer> ll = new LinkedList<Integer>(); ll.add(i);
	 * path[i].add(ll); } else if (i == len) { for (LinkedList<Integer> oll :
	 * path[j]) { LinkedList<Integer> ll = new LinkedList<Integer>();
	 * ll.addAll(oll); path[i].add(ll); } } else { for (LinkedList<Integer> oll
	 * : path[j]) { LinkedList<Integer> ll = new LinkedList<Integer>();
	 * ll.add(i); ll.addAll(oll); path[i].add(ll); } } } } else { // inDict[j]
	 * is false, don't need to test } } inDict[i] = in; }
	 * 
	 * if (inDict[len]) { // there are at least one combination of words for
	 * (LinkedList<Integer> ll : path[len]) { StringBuilder sb = new
	 * StringBuilder(s); for (Integer i : ll) { sb.insert((int) i, ' '); }
	 * String str = new String(sb); strings.add(str); } } return strings; }
	 */

	public ArrayList<String> wordBreak(String s, Set<String> dict) {
		int len = s.length();
		boolean[] inDict = new boolean[len + 1];
		inDict[0] = true;
		for (int i = 1; i < len + 1; i++) {
			inDict[i] = false;
		}

		boolean in = false;
		String testPart = null;
		for (int i = 1; i < len + 1; i++) {
			in = false;
			for (int j = 0; j < i; j++) {
				if (inDict[j]) {
					// inDict[j] is true, test the last part
					testPart = s.substring(j, i);
					if (dict.contains(testPart)) {
						in = true;
						break;
					}
				} else {
					// inDict[j] is false, don't need to test
				}
			}
			inDict[i] = in;
		}

		ArrayList<String> strings = new ArrayList<String>();

		LinkedList<LinkedList<Integer>>[] path = new LinkedList[len + 1];

		if (inDict[len]) {
			// if there is a path
			path[len] = new LinkedList<LinkedList<Integer>>();
			LinkedList<Integer> lle = new LinkedList<Integer>();
			lle.add(len);
			path[len].add(lle);
			for (int i = len; i > 0; i--) {
				for (int j = i - 1; j >= 0; j--) {
					if (inDict[j]) {
						// j is a possible punctation
						testPart = s.substring(j, i);
						if (dict.contains(testPart)) {
							// j is a punction
							if (path[j] == null) {
								path[j] = new LinkedList<LinkedList<Integer>>();
							}

							for (LinkedList<Integer> l : path[i]) {
								LinkedList<Integer> ll = new LinkedList<Integer>(
										l);
								ll.add(j);
								path[j].add(ll);
							}
						} else {
							// j is not a punctation
						}
					}
				}
			}

			for (LinkedList<Integer> ll : path[0]) {
				StringBuilder sb = new StringBuilder(s);
				int index = 0;
				int upper = ll.size() - 1;
				for (Integer i : ll) {
					if (index > 0 && index < upper) {
						sb.insert((int) i, ' ');
					}
					index++;
				}
				String str = new String(sb);
				strings.add(str);
			}
		} else {
			// there are no path
		}
		return strings;
	}

	public static void main(String[] args) {
		Solution s = new Solution();
		HashSet<String> dict = new HashSet<String>();
		dict.add("leet");
		dict.add("code");
		dict.add("tcode");
		dict.add("l");
		dict.add("e");
		dict.add("t");
		dict.add("1");
		dict.add("leetcode");
		String str = "leetcode1";
		ArrayList<String> list = s.wordBreak(str, dict);

		for (String string : list) {
			System.out.println(string);
		}
	}
}