package cmd;

public class TestCMDInterface {
	public static void main(String[] args) {
		String path = "/Users/bohan/developer/nlp/assign2/grammar3.txt";
		String number = "10";
		String optional = "-al";
		CMDInterface.main(new String[]{path,number,"-l"});
	}
}
