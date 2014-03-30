package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import parser.SentenceParser;
import parser.State;
import grammar.ContextFreeLanguage;

public class TestParser {
	public static void main(String[] args) {
		String path = "/Users/bohan/developer/nlp/assign2/grammar1.txt";
		ContextFreeLanguage cfl = new ContextFreeLanguage(path, false);
		SentenceParser sp = new SentenceParser(cfl);

		String str = "another swallow has each servant . ";
//		String str = "another swallow each has servant . ";
//		String str = "i love you . ";
		
		ArrayList<String> words = sp.stringToArrayList(str);
		System.out.println(words);
		String startf = "S1";
		System.out.println(sp.earleyParser(words, startf));

		HashMap<Integer, ArrayList<State>> cha = sp.getChart();

		System.out.println(cha);

		for (Integer i : cha.keySet()) {
			System.out.println(i);
			ArrayList<State> los = cha.get(i);
			for (State state : los) {
				if (state.isComplete() && state.getPrev() == 0
						&& state.getFrom().equals(startf)) {
				System.out.println(i + ":" + state);
				}
			}
		}
	}
}