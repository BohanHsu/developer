package parser;

import grammar.ContextFreeLanguage;
import grammar.Rule;

import java.util.ArrayList;
import java.util.LinkedList;

public class State {
	private static final Object State = null;
	private ContextFreeLanguage cfl = null;
	private String from = null;
	private LinkedList<String> beforeDot = null;
	private LinkedList<String> afterDot = null;
	private int prev = 0;

	/**
	 * construct for start symbols and prediction
	 * 
	 * @param cfl
	 *            : a context free language
	 * @param rule
	 *            : a rule contain in this state
	 * @param k
	 *            : the previous dot position
	 */
	public State(ContextFreeLanguage cfl, Rule rule, int k) {
		this.cfl = cfl;
		this.beforeDot = new LinkedList<String>();
		this.afterDot = new LinkedList<String>(rule.getToSymbols());
		this.from = rule.getFromSymbol();
		this.prev = k;
	}

	/**
	 * construct a state by another state use for scanner
	 * 
	 * @param state
	 *            : the previous state
	 */
	public State(State state) {
		this.cfl = state.getCfl();
		this.beforeDot = new LinkedList<String>(state.getBeforeDot());
		this.afterDot = new LinkedList<String>(state.getAfterDot());
		
		if (this.afterDot.size() > 0){
			this.beforeDot.add(this.afterDot.get(0));
			this.afterDot.remove(0);
		}

		this.from = state.getFrom();
		this.prev = state.getPrev();
	}

	/**
	 * if there are no symbol after the dot, this rule is terminated
	 * 
	 * @return : true if this state is complete
	 */
	public boolean isComplete() {
		return this.afterDot.isEmpty();
	}

	/**
	 * check if next symbol is non-terminal this state must be incomplete
	 * 
	 * @return : true if next symbol is non-terminal
	 */
	public boolean isNextSymNonTerminal() {
		return this.cfl.getNonTerminalSymbols().contains(this.afterDot.get(0));
	}

	/**
	 * peek the next symbol of this state this state must be incomplete
	 * 
	 * @return : the first symbol after dot
	 */
	public String peekNext() {
		return this.afterDot.get(0);
	}

	public ContextFreeLanguage getCfl() {
		return cfl;
	}

	public String getFrom() {
		return from;
	}

	public LinkedList<String> getBeforeDot() {
		return beforeDot;
	}

	public LinkedList<String> getAfterDot() {
		return afterDot;
	}

	public int getPrev() {
		return prev;
	}

	@Override
	public String toString() {
		String str = this.from + " -> ";
		for (String s : this.beforeDot) {
			str += s + " ";
		}
		str += "~ ";
		for (String s : this.afterDot) {
			str += s + " ";
		}
		return str;
	}
	
	

}
