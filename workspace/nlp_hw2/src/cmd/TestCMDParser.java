package cmd;

public class TestCMDParser {
	public static void main(String[] args) {
		CMDParser.main(new String[]{"/Users/bohan/developer/nlp/assign2/grammar1.txt","S1","another swallow has each servant . "});
		
		CMDParser.main(new String[]{"/Users/bohan/developer/nlp/assign2/grammar1.txt","S1","swallow has each servant . "});		
//		CMDParser.main(new String[]{"/Users/bohan/developer/nlp/assign2/grammar1.txt","S1","another swallow each has servant . "});
//		CMDParser.main(new String[]{"/Users/bohan/developer/nlp/assign2/grammar1.txt","S1","i love you . "});

	}
}