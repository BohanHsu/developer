package pascalsTriangleII;

import java.util.LinkedList;
import java.util.List;

public class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> last = new LinkedList<Integer>();

        if (rowIndex == 0){
            last.add(1);
            return last;
        } 
        last.add(1);
        last.add(1);
        if (rowIndex == 1){
            return last;
        }
        List<Integer> current = null;
        for (int i = 1; i < rowIndex; i++){
            current = new LinkedList<Integer>();
            int prev = 0;
            for (Integer j : last){
                current.add(prev + j);
                prev = j;
            }
            current.add(1);
            last = current;
        }
        return current;
    }
    
    public static void main(String[] args) {
		Solution s = new Solution();
		List<Integer> res = s.getRow(3);
		System.out.println(res);
	}
}