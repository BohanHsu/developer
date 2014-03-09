package sentenceBuilder;

import grammar.ContextFreeLanguage;
import grammar.Rule;

import java.util.ArrayList;
import java.util.LinkedList;

import utility.RandomNumberGenerator;

public class IntermediateSentence {
	private LinkedList<String> sentence = null;
	private LinkedList<Rule> rules = null;
	private ContextFreeLanguage cfl = null;

	/**
	 * constructor, randomly select a start symbol from cfl
	 * 
	 * @param cfl
	 *            : a context free language
	 */
	public IntermediateSentence(ContextFreeLanguage cfl) {
		this.cfl = cfl;
		ArrayList<Rule> startSymbolRules = cfl.getMapping().get("START");
		this.rules = new LinkedList<Rule>(startSymbolRules);
		Rule selectedStartRule = randomSelectRule();
		String startSymbol = selectedStartRule.getToSymbols().get(0);
		this.sentence = new LinkedList<String>();
		sentence.add(startSymbol);
		ArrayList<Rule> allRules = this.cfl.getMapping().get(startSymbol);
		this.rules = new LinkedList<>(allRules);
	}

	/**
	 * a successor constructor take a IntermediateSentence, this argument
	 * sentence must have rule remain to check,
	 * 
	 * @param is
	 *            : a IntermediateSentence
	 */
	public IntermediateSentence(IntermediateSentence is) {
		// copy attributes from previous IS
		this.cfl = is.cfl;
		this.sentence = new LinkedList<String>(is.sentence);
		// randomly select a rule from previous IS
		Rule selectedRule = is.randomSelectRule();
		String fromSymbol = selectedRule.getFromSymbol();
		ArrayList<String> toSymbols = selectedRule.getToSymbols();
		// remove the selected rule from previous IS
		is.rules.remove(selectedRule);
		// modify sentence in this IS
		int index = this.sentence.indexOf(fromSymbol);
		this.sentence.remove(fromSymbol);
		this.sentence.addAll(index, toSymbols);
		// find first non terminal symbols in new sentence
		String firstNonTerminal = null;
		for (String symbol : this.sentence) {
			if (isNonTerminal(symbol)) {
				firstNonTerminal = symbol;
				break;
			}
		}

		if (firstNonTerminal == null) {
			// this sentence only contain Terminal Symbols
			this.rules = new LinkedList<Rule>();
		} else {
			// get rules from this symbol
			ArrayList<Rule> allRules = this.cfl.getMapping().get(
					firstNonTerminal);
			this.rules = new LinkedList<Rule>(allRules);
		}
	}

	/**
	 * given a string, determine iff this symbols is a non-terminal symbol
	 * 
	 * @param symbol
	 *            : a given symbols
	 * @return
	 */
	private boolean isNonTerminal(String symbol) {
		return this.cfl.getNonTerminalSymbols().contains(symbol);
	}

	/**
	 * randomly select a rule form this structure, according to the rule's
	 * weight
	 * 
	 * @return : a rule if possible, if no rules return null
	 */
	public Rule randomSelectRule() {
		int totalRuleWeight = 0;
		for (Rule rule : this.rules) {
			totalRuleWeight += rule.getWeight();
		}
		int randomInt = RandomNumberGenerator.randomIntTo(totalRuleWeight);
		int accmulatedWeight = 0;
		for (Rule rule : this.rules) {
			if (accmulatedWeight <= randomInt
					&& randomInt < (accmulatedWeight + rule.getWeight())) {
				// select this rule
				return rule;
			} else {
				accmulatedWeight += rule.getWeight();
			}
		}
		return null;
	}

	/**
	 * determine iff this sentence need to be pop out of stack show check after
	 * qualified function
	 * 
	 * @return true iff this sentence not possible to make a qualified sentence
	 */
	public boolean needBackOffForSentenceOfLength(int sentenceLength) {
		if (this.sentence.size() > sentenceLength) {
			// when length of sentence is longer than given
			return true;
		} else if (this.rules.size() == 0) {
			// no remain rule to try
			return true;
		}
		return false;
	}

	/**
	 * determine if this sentence is qualified of a specific length
	 * 
	 * @param sentenceLength
	 *            : length of the given sentence
	 * @return : true iff qualified, otherwise false
	 */
	public boolean qualifiedForSentenceOfLength(int sentenceLength) {
		if (this.sentence.size() == sentenceLength) {
			// length of this sentence is qualified
			boolean allSymbolsAreTerminal = true;
			for (String symbols : this.sentence) {
				allSymbolsAreTerminal = allSymbolsAreTerminal
						&& this.cfl.getTerminalSymbols().contains(symbols);
			}
			return allSymbolsAreTerminal;
		} else {
			return false;
		}
	}

	/**
	 * determine a sentence can possible form sentence
	 * 
	 * @return : return true iff this sentence need back off (can not possible
	 *         form sentence), otherwise return false
	 */
	public boolean needBackOff() {
		if (this.rules.size() == 0) {
			// no remain rule to try
			return true;
		}
		return false;
	}

	/**
	 * determine this sentence structure is a sentence or not
	 * @return : true iff this structure is a sentence
	 */
	public boolean qualified() {
		boolean allSymbolsAreTerminal = true;
		for (String symbols : this.sentence) {
			allSymbolsAreTerminal = allSymbolsAreTerminal
					&& this.cfl.getTerminalSymbols().contains(symbols);
		}
		return allSymbolsAreTerminal;
	}

	/**
	 * concatenate strings in sentence list to a string, split by space
	 * 
	 * @return : the concatenated string
	 */
	public String getBuiltSentence() {
		String concatenated = "";
		for (String str : this.sentence) {
			concatenated = concatenated + str + " ";
		}
		return concatenated;
	}

	/**
	 * print detail of this sentence structure
	 */
	public void printLog() {
		System.out.println("==========");
		String concatenated = "[ ";
		for (String str : this.sentence) {
			concatenated = concatenated + str + " ";
		}
		concatenated += "]";
		System.out.println("--sentence: ");
		System.out.println(concatenated);
		System.out.println("--reamin rules: ");
		for (Rule rule : this.rules) {
			System.out.println("\t" + rule.toString());
		}
		System.out.println("==========");
		System.out.println();
	}
}
