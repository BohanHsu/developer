package test;

public class PlainTest {
	public static void main(String[] args) {
		String s = "1,2,3,4,5,6,7,8,9,0";
		String[] s1 = s.split(",");
		System.out.println(s1.length);
		String[] s2 = s.split(",",5);
		System.out.println(s2.length);
	}
}
