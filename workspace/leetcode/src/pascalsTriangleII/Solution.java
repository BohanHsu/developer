package pascalsTriangleII;

public class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> last = new LinkedList<Integer>();
        if (rowIndex == 1){
            return last.add(1);
        } 
        last.add(1);
        last.add(1);
        if (rowIndex == 2){
            return last;
        }
        List<Integer> current = null;
        for (int i = 2; i < rowIndex; i++){
            current = new LinkedList<Integer>();
            int prev = 0;
            for (Integer i : last){
                current.add(prev + i);
                prev = i;
            }
            current.add(1);
            last = current;
        }
        return current;
    }
}
