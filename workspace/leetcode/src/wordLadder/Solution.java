package wordLadder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
	public int ladderLength(String start, String end, HashSet<String> dict) {
		HashMap<String, ArrayList<String>> successor = new HashMap<String, ArrayList<String>>();
		dict.add(start);
		dict.add(end);
		for (String str : dict) {
			ArrayList<String> scs = new ArrayList<String>();
			for (String str2 : dict) {
				if (isAdjacent(str, str2)) {
					scs.add(str2);
				}
			}
			successor.put(str, scs);
		}

		Queue<String> queue = new LinkedList<String>();
		HashMap<String, Integer> dist = new HashMap<String, Integer>();

		queue.add(start);
		dist.put(start, 1);
		String s = null;
		int d = 0;
		ArrayList<String> scs = null;
		while (!queue.isEmpty()) {
			s = queue.poll();

			d = dist.get(s);
			if (s.equals(end)) {
				return d;
			}
			scs = successor.get(s);

			for (String nextStr : scs) {
				if (!dist.containsKey(nextStr)) {
					dist.put(nextStr, d + 1);
					queue.add(nextStr);
				}
			}
		}

		return 0;
	}

	private boolean isAdjacent(String str1, String str2) {
		char[] sa1 = str1.toCharArray();
		char[] sa2 = str2.toCharArray();

		boolean ns = false; // indicate there are already one letter not same
		for (int i = 0; i < sa1.length; i++) {
			if (sa1[i] != sa2[i]) {
				if (ns) {
					return false;
				} else {
					ns = true;
				}
			}
		}

		return ns; // if there only one letter not same, return true, otherwise
					// all letters are same, so return false
	}

	public static void main(String[] args) {
		Solution s = new Solution();
		String[] sa = new String[] { "hot", "dot", "dog", "lot", "log" };
		HashSet<String> dict = new HashSet<String>();
		for (String str : sa) {
			dict.add(str);
		}

		System.out.println(s.ladderLength("hit", "cog", dict));
	}

}