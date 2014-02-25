package gasStation;

import java.util.Stack;

public class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int i = 0;
        int len = gas.length;
        int count = 0;
        int tank = 0;
        
        while (count < len){
            tank = gas[(i + count) % len] - cost[(i + count) % len] + tank;
            System.out.println("tank :" + tank);
            if (tank < 0){
                if (i + count > len){
                    return -1;
                }
                i = i + count + 1;
                tank = 0;
                count = 0;
            } else{
            	count++;
            }

        }
        
        return i;
        
    }
	
	public static void main(String[] args) {
		//                       0, 1, 2, 3, 4, 5, 6,  7
		int[] gas = new int[] {  0, 1, 2, 3, 4, 5, 9, 6 };
		int[] cost = new int[] { 1, 2, 3, 4, 5, 6, 7, 3 };
		Solution s = new Solution();
		System.out.println(s.canCompleteCircuit(gas, cost));
	}
}