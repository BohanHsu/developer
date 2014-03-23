package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import parser.SentenceParser;
import parser.State;
import grammar.ContextFreeLanguage;

public class TestParser {
	public static void main(String[] args) {
		String path = "src/grammar1.txt";
		ContextFreeLanguage cfl = new ContextFreeLanguage(path, true);
		SentenceParser sp = new SentenceParser(cfl);
		
		String str = "this sun covers any winter . ";
		ArrayList<String> words = sp.stringToArrayList(str);
		System.out.println(words);
		
		sp.earleyParse(words, "S1");
		
		HashMap<Integer, LinkedList<State>> cha = sp.getChart();
		
		System.out.println(cha);
	}
}
