package test;

import java.util.ArrayList;

import sentenceBuilder.SentenceBuilder;
import grammar.ContextFreeLanguage;

public class TestForProb1 {
	public static void main(String[] args) {
		String path = "src/grammar1.txt";
		ContextFreeLanguage cfl = new ContextFreeLanguage(path, true);
		SentenceBuilder sb = new SentenceBuilder(cfl);
		
		int number = 5;
		
		ArrayList<String> generatedStrs = new ArrayList<String>();
		for (int i = 0;i < number; i++){
			generatedStrs.add(sb.buildSentence());
		}
		
		for (String string : generatedStrs) {
			System.out.println(string);
		}
		
		double totalLength = 0;
		for (String string : generatedStrs) {
			String[] sa = string.split(" ");
			totalLength += sa.length;
		}
		
		if (true){
			System.out.println("--average length: " + (totalLength/number));
		}
	}
}
