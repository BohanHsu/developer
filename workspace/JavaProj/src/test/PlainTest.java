package test;

public class PlainTest {
	public static void main(String[] args) {
		String s = "123456789";
		StringBuilder sb = new StringBuilder(s);
		sb.insert(3, ' ');
		String str = new String(sb);
		System.out.println(str);
	}
}