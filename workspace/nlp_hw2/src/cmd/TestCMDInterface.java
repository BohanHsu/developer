package cmd;

public class TestCMDInterface {
	public static void main(String[] args) {
		String path = "/Users/bohan/developer/nlp/assign2/grammar33.txt";
		String number = "30";
		String optional = "-al";
		CMDInterface.main(new String[]{path,number,"-al"});
	}
}