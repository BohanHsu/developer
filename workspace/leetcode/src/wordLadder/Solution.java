package wordLadder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
	public int ladderLength(String start, String end, HashSet<String> dict) {
		Queue<String> queue = new LinkedList<String>();
		HashMap<String, Integer> dist = new HashMap<String, Integer>();
		queue.add(start);
		dist.put(start, 0);
		String str = null;
		int d = 0;
		while (!queue.isEmpty()) {
			str = queue.poll();
			d = dist.get(str);
			if (str.equals(end)) {
				return d + 1;
			} else {
				HashSet<String> successors = allConjunctedWords(str);
				for (String successor : successors) {
					if (dict.contains(successor) || end.equals(successor)) {
						if (!dist.containsKey(successor)) {
							queue.add(successor);
							dist.put(successor, d + 1);
						}
					}
				}
			}
		}
		return 0;
	}

	private HashSet<String> allConjunctedWords(String str) {
		char[] strArr = str.toCharArray();
		HashSet<String> conjuncted = new HashSet<String>();
		for (int i = 0; i < strArr.length; i++) {
			char cur = strArr[i];
			for (int j = (int) 'a'; j <= (int) 'z'; j++) {
				strArr[i] = (char) j;
				conjuncted.add(new String(strArr));
			}
			strArr[i] = cur;
		}

		conjuncted.remove(str);
		return conjuncted;
	}

	public static void main(String[] args) {
		Solution s = new Solution();

		String start = "hit";
		String end = "cog";

		String[] dictArray = new String[] { "hot", "dot", "dog", "lot", "log" };
		HashSet<String> dict = new HashSet<String>();

		for (String string : dictArray) {
			dict.add(string);
		}

		System.out.println(s.ladderLength(start, end, dict));
	}
}
