package cmd;

import grammar.ContextFreeLanguage;
import parser.SentenceParser;

public class CMDParser {
	public static void main(String[] args) {
		String filepath = args[0];
		String start = args[1];
//		String sentence = args[3];
		String sentence = "";
		for (int i = 2; i < args.length; i++) {
			sentence += args[i] + " ";
		}
		
		ContextFreeLanguage cfl = new ContextFreeLanguage(filepath, false);
		SentenceParser sp = new SentenceParser(cfl);
		System.out.println(sp.earleyParser(sp.stringToArrayList(sentence), start));
		
		
	}
}
