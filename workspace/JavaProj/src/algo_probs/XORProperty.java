package algo_probs;

public class XORProperty {
	public static void main(String[] args) {
		int a = singleNumber(new int[]{1,1,2,2,3,3,4,4,7,7,6,9,8,6,6,6,6,6,8,9});
		System.out.println(a);
	}
	
	public static int singleNumber(int[] array){
		if (array.length == 0){
			return 0;
		}
		
		int single = array[0];
		for (int i = 1; i<array.length; i++){
			single = single^array[i];
		}
		return single;
	}
}
