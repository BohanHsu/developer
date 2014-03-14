package utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GrammarReader {
	private ArrayList<String> grammar = null;
	public GrammarReader(String path) {
		this.grammar = eliminator(readLinesFromFile(path));
	}

	@SuppressWarnings("finally")
	private ArrayList<String> readLinesFromFile(String path) {
		ArrayList<String> lines = new ArrayList<String>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(path));
			String line = null;
			while ((line = br.readLine()) != null) {
				lines.add(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return lines;
		}
	}

	private ArrayList<String> eliminator(ArrayList<String> lines) {
		ArrayList<String> result = new ArrayList<String>();
		for (String string : lines) {
			if ("".equals(string) || string.startsWith("#")) {

			}else {
				result.add(string);
			}
		}
		return result;
	}

	public ArrayList<String> getGrammar() {
		return grammar;
	}

	public static void main(String[] args) {
		String path = "src/grammar1.txt";
		/*
		GrammarReader gr = new GrammarReader();
		ArrayList<String> lines = gr.readLinesFromFile("src/grammar1.txt");
		lines = gr.eliminator(lines);
		for (String string : lines) {
			System.out.println(string);
		}
		*/
		
		ArrayList<String> lines = new GrammarReader(path).getGrammar();
		for (String string : lines) {
			System.out.println(string);
		}
	}
}