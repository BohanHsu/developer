package parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import grammar.ContextFreeLanguage;
import grammar.Rule;

public class SentenceParser {
	private ContextFreeLanguage cfl = null;
	private HashMap<Integer, LinkedList<State>> chart = null;

	public SentenceParser(ContextFreeLanguage cfl) {
		this.cfl = cfl;
	}

	public void earleyParse(ArrayList<String> words, String start) {
		this.chart = new HashMap<Integer, LinkedList<State>>();

		// initialize

		ArrayList<Rule> rules = this.cfl.getMapping().get(start);

		for (Rule rule : rules) {
			addToSet(new State(this.cfl, rule, 0), 0);
		}

		
		
		for (int i = 0; i < words.size(); i++) {
			LinkedList<State> states = this.chart.get(i);
			for (State state : states) {
				// test
				System.out.println(i + " " + state);
				if (!state.isComplete()) {
					// this state is incomplete
					if (state.isNextSymNonTerminal()) {
						// next is non-terminal
						// do perdictor
						perdictor(state, i);
					} else {
						// do scanner
						scanner(state, i);
					}
				} else {
					// this is complete
					// do complete
					complete(state, i);
				}
			}
		}
	}

	/**
	 * Prediction of earley parse algorithm
	 * 
	 * @param state
	 *            : the current state
	 * @param j
	 *            : the current of index in loop
	 */
	private void perdictor(State state, int j) {
		String nextB = state.peekNext();
		ArrayList<Rule> rules = this.cfl.getMapping().get(nextB);

		for (Rule rule : rules) {
			State newState = new State(cfl, rule, j);
			addToSet(newState, j);
		}

	}

	/**
	 * scanner a state
	 * 
	 * @param state
	 *            : the original state
	 * @param j
	 *            : the current set
	 */
	private void scanner(State state, int j) {
		addToSet(new State(state), j + 1);
	}

	/**
	 * complete in earley parser
	 * 
	 * @param state
	 *            : the original state
	 * @param k
	 *            : the current set
	 */
	private void complete(State state, int k) {
		String fromX = state.getFrom();
		int j = state.getPrev();
		LinkedList<State> states = this.chart.get(j);
		for (State sta : states) {
			if ((!sta.isComplete()) && sta.peekNext().equals(fromX)) {
				addToSet(new State(state), k);
			}
		}
	}

	/**
	 * add a state to set
	 * 
	 * @param state
	 *            : the adding state
	 * @param j
	 *            : the index of set
	 */
	private void addToSet(State state, int j) {
		if (!this.chart.containsKey(j)) {
			this.chart.put(j, new LinkedList<State>());
		}
		this.chart.get(j).add(state);
	}

	/**
	 * split a string by space and store into ArrayList
	 * 
	 * @param str
	 *            : a string
	 * @return : a arraylist
	 */
	public ArrayList<String> stringToArrayList(String str) {
		ArrayList<String> list = new ArrayList<String>();
		String[] sa = str.split(" ");
		for (String string : sa) {
			list.add(string);
		}
		return list;
	}

	public HashMap<Integer, LinkedList<State>> getChart() {
		return chart;
	}

}
