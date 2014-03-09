package cmd;

import grammar.ContextFreeLanguage;

import java.util.ArrayList;
import java.util.HashSet;

import sentenceBuilder.SentenceBuilder;

public class CMDInterface {
	/**
	 * command line interface
	 * 
	 * @param args
	 *            args[0] : is the grammar configuration file path args[1] :
	 *            indicate how much sentence should generate args[2] : is
	 *            optional arguments example : -al a : show average length of
	 *            generated sentence l : show detail of generating sentence
	 */
	public static void main(String[] args) {
		HashSet<Character> optionalArgument = new HashSet<Character>();
		String filepath = args[0];
		int number = Integer.parseInt(args[1]);

		if (args.length >= 3) {
			char[] optionalArgumentArray = args[2].toCharArray();
			if (optionalArgumentArray.length > 0
					&& optionalArgumentArray[0] == '-'
					&& optionalArgumentArray.length > 1) {
				for (int i = 1; i < optionalArgumentArray.length; i++) {
					optionalArgument.add(optionalArgumentArray[i]);
				}
			}
		}

		ContextFreeLanguage cfl = new ContextFreeLanguage(filepath,
				optionalArgument.contains('l'));
		SentenceBuilder sb = new SentenceBuilder(cfl);

		double totalLength = 0;
		ArrayList<String> generatedStr = new ArrayList<String>(); 
		for (int i = 0; i < number; i++) {
			String s = sb.buildSentence();
			generatedStr.add(s);
			totalLength += s.split(" ").length;
		}
		for (String string : generatedStr) {
			System.out.println(string);
		}
		if (optionalArgument.contains('a')) {
			System.out.println("--average length: " + (totalLength / number));
		}
	}

}
