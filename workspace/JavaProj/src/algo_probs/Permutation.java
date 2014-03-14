package algo_probs;

public class Permutation {
	public void permutation(String s){
		permutation("",s);
	}
	
	private void permutation(String prefix, String s){
		int len = s.length();
		System.out.println(prefix);
		for (int i = 0; i < len; i++){
			permutation(prefix + s.substring(i, i + 1), s.substring(0, i ) + s.substring(i+1,len));
		}
	}
	
	public static void main(String[] args) {
		Permutation p = new Permutation();
		p.permutation("0123");
	}
}