package wordLadderII;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
	private HashMap<String, ArrayList<String>> next = new HashMap<String, ArrayList<String>>();

	public ArrayList<ArrayList<String>> findLadders(String start, String end,
			HashSet<String> dict) {
		ArrayList<ArrayList<String>> allPath = new ArrayList<ArrayList<String>>();
		int shortestLength = Integer.MAX_VALUE;
		Queue<String> queue = new LinkedList<String>();
		HashMap<String, Integer> dist = new HashMap<String, Integer>();
		queue.add(start);
		dist.put(start, 0);
		String str = null;
		int d = 0;
		while (!queue.isEmpty()) {
			System.out.println(queue);
			System.out.println(dist);
			str = queue.poll();
			d = dist.get(str);
			if (d + 1 > shortestLength) {
				break;
			}
			if (str.equals(end)) {
				if (d + 1 < shortestLength){
					shortestLength = d;
				}
			} else {
				HashSet<String> successors = allConjunctedWords(str);
				for (String successor : successors) {
					if (dict.contains(successor) || end.equals(successor)) {
						if (!dist.containsKey(successor)) {
							queue.add(successor);
							dist.put(successor, d + 1);
							addToMap(str, successor);
						} else if (d+1 < dist.get(successor)){
							dist.put(successor, d + 1);
							addToMap(str, successor);
						}else if (successor.equals(end)){
							addToMap(str, successor);
						}
					}
				}
			}
		}

		if (shortestLength == Integer.MAX_VALUE) {
			return allPath;
		}

		System.out.println(next);
		System.out.println(shortestLength);

		ArrayList<ArrayList<String>> newPath = null;
		ArrayList<String> al1 = new ArrayList<String>();
		al1.add(start);
		allPath.add(al1);
		for (int i = 0; i < shortestLength; i++) {
			newPath = new ArrayList<ArrayList<String>>();
			for (ArrayList<String> path1 : allPath) {
				str = path1.get(i);
				ArrayList<String> successors = this.next.get(str);
				Iterator<String> itr = successors.iterator();
				str = itr.next();
				while (itr.hasNext()) {
					al1 = new ArrayList<String>(path1);
					al1.add(itr.next());
					newPath.add(al1);
				}
				path1.add(str);
				newPath.add(path1);
			}
			allPath = newPath;
		}

		return allPath;
	}

	private void addToMap(String key, String value) {
		if (!this.next.containsKey(key)) {
			this.next.put(key, new ArrayList<String>());
		}
		ArrayList<String> nexts = this.next.get(key);
		nexts.add(value);
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

		String start = "a";
		String end = "c";

		String[] dictArray = new String[] { "a", "b", "c"};
		HashSet<String> dict = new HashSet<String>();

		for (String string : dictArray) {
			dict.add(string);
		}

		ArrayList<ArrayList<String>> res = s.findLadders(start, end, dict);
		System.out.println(res);
	}
}