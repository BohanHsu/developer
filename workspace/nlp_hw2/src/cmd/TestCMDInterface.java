package cmd;

public class TestCMDInterface {
	public static void main(String[] args) {
		String path = "/Users/bohan/developer/nlp/assign2/grammar1.txt";
		String number = "1";
//		String optional = "-al";
		CMDInterface.main(new String[]{path,number,"-al"});
	}
}
