package grammar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import utility.GrammarReader;

public class ContextFreeLanguage {

	private HashSet<String> startSymbols = null;
	private HashSet<String> nonTerminalSymbols = null;
	private HashSet<String> terminalSymbols = null;
	private HashMap<String, ArrayList<Rule>> mapping = null;
	private HashMap<String, ArrayList<String>> terminalPOS = null;
	private boolean printingLog = false;

	public ContextFreeLanguage() {
	}

	public ContextFreeLanguage(String grammarPath) {
		this(grammarPath, false);
	}

	public ContextFreeLanguage(String grammarPath, boolean printingLog) {

		this.printingLog = printingLog;
		ArrayList<String> lines = new GrammarReader(grammarPath).getGrammar();
		this.startSymbols = new HashSet<String>();
		this.nonTerminalSymbols = new HashSet<String>();
		this.terminalSymbols = new HashSet<String>();
		this.mapping = new HashMap<String, ArrayList<Rule>>();
		
		for (String line : lines) {
			String[] parts = line.split("\t");

			if ("START".equals(parts[1])) {
				// this is a START symbol
				this.startSymbols.add(parts[2]);
			} else {
				// this is a rewrite rule
				nonTerminalSymbols.add(parts[1]);
				if (parts.length > 2) {
					String[] toParts = parts[2].split(" ");
					for (String symbol : toParts) {
						terminalSymbols.add(symbol);
					}
				}
			}
		}
		terminalSymbols.removeAll(nonTerminalSymbols);

		if (printingLog) {
			System.out.println("--strat symbols:");
			System.out.println(startSymbols);
			System.out.println("--non terminal symbols:");
			System.out.println(nonTerminalSymbols);
			System.out.println("--terminal symbols:");
			System.out.println(terminalSymbols);
		}

		for (String line : lines) {
			String[] parts = line.split("\t");
			int weight = Integer.parseInt(parts[0]);
			String[] toParts = null;
			if (parts.length > 2) {
				toParts = parts[2].split(" ");
			}
			Rule rule = new Rule(weight, parts[1], toParts);
			if (!this.mapping.containsKey(parts[1])) {
				this.mapping.put(parts[1], new ArrayList<Rule>());
			}
			this.mapping.get(parts[1]).add(rule);// finished here, need to be
													// test
		}

		if (printingLog) {
			System.out.println("--rules:");
			System.out.println(mapping);
		}
		
		this.terminalPOS = new HashMap<String, ArrayList<String>>();
		
		for (String nonter : this.mapping.keySet()) {
			ArrayList<Rule> rules = this.mapping.get(nonter);
			for (Rule rule : rules) {
				ArrayList<String> toSymbs = rule.getToSymbols();
				if (toSymbs.size() == 1 && this.terminalSymbols.contains(toSymbs.get(0))){
					if (!this.terminalPOS.containsKey(toSymbs.get(0))){
						this.terminalPOS.put(toSymbs.get(0), new ArrayList<String>());
					}
					this.terminalPOS.get(toSymbs.get(0)).add(nonter);
				}
			}
		}
		
		if (printingLog) {
			System.out.println("--POS:");
			System.out.println(terminalPOS);
		}
	}
	
	

	public HashSet<String> getStartSymbols() {
		return startSymbols;
	}

	public HashSet<String> getNonTerminalSymbols() {
		return nonTerminalSymbols;
	}

	public HashSet<String> getTerminalSymbols() {
		return terminalSymbols;
	}

	public HashMap<String, ArrayList<Rule>> getMapping() {
		return mapping;
	}
	
	

	public boolean isPrintingLog() {
		return printingLog;
	}

	
	
	public HashMap<String, ArrayList<String>> getTerminalPOS() {
		return terminalPOS;
	}

	public static void main(String[] args) {
//		String path = "src/grammar1.txt";
//		ContextFreeLanguage cfl = new ContextFreeLanguage(path,true);
	}
}
