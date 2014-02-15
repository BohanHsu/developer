package addBinary;

public class Solution {
    public String addBinary(String a, String b) {
        int la = a.length();
        int lb = b.length();
        int len = la;
        
        if (la < lb){
            for(int i = 0; i < (lb-la);i++){
                a = "0" + a;
                len = lb;
            }
        }
        if (lb < la){
            for(int i = 0; i < (la-lb);i++){
                b = "0" + b;
            }
            len = la;
        }
        
        char[] bin1 = a.toCharArray();
        char[] bin2 = b.toCharArray();
        
        int carrier = 0;
        int res = 0;
        
        int[] result = new int[len];
        
        System.out.println(len);
        System.out.println(bin1);
        System.out.println(bin2);
        
        for (int i = len-1; i >= 0; i--){
            int b1 = Integer.parseInt(bin1[i]+"");
            int b2 = Integer.parseInt(bin2[i]+"");
            
            switch(b1 + b2 + carrier){
                case 3:
                    result[i] = 1;
                    carrier = 1;
                    break;
                case 2:
                    result[i] = 0;
                    carrier = 1;
                    break;
                case 1:
                    result[i] = 1;
                    carrier = 0;
                    break;
                case 0:
                    result[i] = 0;
                    carrier = 0;
                    break;
            }
            
            for (int j : result){
            	System.out.println(j);
            }
        }
        
        String s = "";
        
        if (carrier == 1){
            s = "1";
        }
        
        for (int i : result){
            s = s + i;
        }
        
        return s;
    }
    
    
    public static void main(String[] args) {
		Solution s = new Solution();
		String str = s.addBinary("11", "1");
		System.out.println(str);
	}
}