package palindromePartitioningII;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Solution {
    public int minCut(String s) {
        int len = s.length();
        int min = Integer.MAX_VALUE;
        HashMap<Integer,LinkedList<Integer>> adjancent = new HashMap<Integer,LinkedList<Integer>>();
        LinkedList<Integer> list = null;
        for (int i = 0; i < len; i++){
            list = new LinkedList<Integer>();
            list.add(i+1);
            for (int j = i + 2; j<=len; j++){
                if (isPalindrome(s.substring(i,j))){
                    list.add(j);
                }
            }
            adjancent.put(i,list);
        }
        
        // got all the palindrome substrings in s
        // then find all the path from 0 to len
        
        LinkedList<LinkedList<Integer>> paths = new LinkedList<LinkedList<Integer>>();
        for (Integer next : adjancent.get(0)) {
        	LinkedList<Integer> p = new LinkedList<Integer>();
        	p.add(0);
        	p.add(next);
        	paths.add(p);
		}  
         
            
        LinkedList<Integer> path = null;
        while(!paths.isEmpty()){
        	path = paths.poll();
            int end = path.get(path.size()-1);
            if (end != len){
                // this path is unfinished
                LinkedList<Integer> successor = adjancent.get(end);
                
                for(int scr : successor){
                    LinkedList<Integer> p = new LinkedList<Integer>(path);
                    p.add(scr);
                    paths.add(p);
                }
            } else{
                // this path is finished
                // we can separate the string
                if (path.size() -2 < min){
                    min = path.size() - 2;
                }
            }
        }
        return min;
    }
    
    /**
     * 
     * return true if the given string is a palindrome
     */ 
    private boolean isPalindrome(String str){
        int len = str.length();
        String subStr1 = str.substring(0, len / 2);
        String subStr2 = str.substring(len / 2 + len % 2, len);
        subStr2 = new StringBuilder(subStr2).reverse().toString();
        return subStr1.equals(subStr2);
    }
    
    
    public static void main(String[] args) {
		Solution s = new Solution();
		String str = "ababababababababababababcbabababababababababababa";
		System.out.println(s.minCut(str));
	}
}