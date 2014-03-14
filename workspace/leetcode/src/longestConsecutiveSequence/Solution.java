package longestConsecutiveSequence;

import java.util.HashMap;

public class Solution {
    HashMap<Integer,Boolean> hs = new HashMap<Integer,Boolean>();
    public int longestConsecutive(int[] num) {
        for (int i = 0; i < num.length; i++){
            hs.put(num[i],false);
        }
        int max = 0;
        int cur = 0;
        
        for (Integer x : hs.keySet()){
            cur = check(x) + checkLess(x) + checkGreater(x);
            if (cur > max){
            	max = cur;
            }
        }
        return max;
    }
    
    private int check(int x){
        if (this.hs.containsKey(x)){
            if (this.hs.get(x)){
                return 0;
            }else{
                this.hs.put(x,true);
                return 1;
            }
        } else{
            return 0;
        }
    }
    
    private int checkLess(int x){
        int c = check(x-1);
        if (c == 1){
            return c + checkLess(x-1);
        } else{
            return c;
        }
    }
    
    private int checkGreater(int x){
        int c = check(x+1);
        if (c == 1){
            return c + checkGreater(x+1);
        } else{
            return c;
        }
    }
    
    public static void main(String[] args) {
		Solution s = new Solution();
		int[] num = new int[]{100,4,200,3,2,1};
		System.out.println(s.longestConsecutive(num));
	}
}