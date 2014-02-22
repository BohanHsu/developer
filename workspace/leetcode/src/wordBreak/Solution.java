package wordBreak;

import java.util.HashSet;
import java.util.Set;

public class Solution {
	public boolean wordBreak(String s, Set<String> dict) {
	    int len = s.length();
	    boolean[] inDict = new boolean[len + 1];
	    inDict[0] = true;
	    for (int i = 1; i< len+1; i++){
	        inDict[i] = false;
	    }
	    
	    boolean in = false;
	    String testPart = null;
	    for (int i = 1; i < len + 1; i++){
	        in = false;
	        for (int j = 0; j < i; j++){
	            if (inDict[j]){
	                // inDict[j] is true, test the last part
	                testPart = s.substring(j,i);
	                if (dict.contains(testPart)){
	                    in = true;
	                    break;
	                }
	            }else{
	                // inDict[j] is false, don't need to test
	            }
	        }
	        inDict[i] = in;
	    }
	    return inDict[len];
	}
	
	public static void main(String[] args) {
		Solution s = new Solution();
		HashSet<String> dict = new HashSet<String>();
		dict.add("leet");
		dict.add("code");
		dict.add("1");
		String str = "leetcode1";
		boolean in = s.wordBreak(str, dict);
		System.out.println(in);
	}
}

