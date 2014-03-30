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
	private HashMap<Integer, ArrayList<State>> chart = null;
	private String startf = null;
	private Queue<State> queue = new LinkedList<State>();
	private int q = 0;

	public SentenceParser(ContextFreeLanguage cfl) {
		this.cfl = cfl;
	}

	public boolean earleyParser(ArrayList<String> words, String start){
		this.startf = start;
		this.chart = new HashMap<Integer, ArrayList<State>>();
		ArrayList<Rule> rules = this.cfl.getMapping().get(start);
		for (Rule rule : rules) {
			addToSet(new State(this.cfl, rule, 0), 0);
		}
		
		for (int i = 0; i < words.size(); i++) {
			
			ArrayList<State> nextSet = this.chart.get(i);
			
			if (nextSet == null){
				return false;
			}
			
			
			this.queue = new LinkedList<State>(nextSet);
			this.q = i;
			while(!this.queue.isEmpty()){
				State state = this.queue.poll();
				if (!state.isComplete()){
					// not complete
					if (state.isNextNonterminal()){
						// non terminal
						predictor(state,i);
					}else{
						// terminal
						scanner(state,i,words.get(i));
					}
				} else{
					// complete
					complete(state,i);
					
				}
			}
		}
		return isAccepted();
	}
	
	private void predictor(State state, int j){
		String nextB = state.next();
		ArrayList<Rule> rules = this.cfl.getMapping().get(nextB);
		for (Rule rule : rules) {
			addToSet(new State(this.cfl, rule, j), j);
		}
	}
	
	private void scanner(State state, int j, String word){		
		if (state.next().equals(word)){
			addToSet(new State(state), j+1);
		}
	}

	
	private void complete(State state, int k){
		
		int j = state.getPrev();
		ArrayList<State> sj = this.chart.get(j);
		
		for (State state2 : sj) {
			if (!state2.isComplete() && state2.next().equals(state.getFrom())){
				
				addToSet(new State(state2), k);
			}
		}
	}

	private void addToSet(State state, int j) {
		if (!this.chart.containsKey(j)) {
			this.chart.put(j, new ArrayList<State>());
		}
		if (!inSet(j, state)) {
			this.chart.get(j).add(state);
			if (j == this.q) {
				this.queue.add(state);
			}
		}
	}

	private boolean inSet(int j, State state) {
		ArrayList<State> states = this.chart.get(j);
		for (State state2 : states) {
			if (state2.equals(state)) {
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<String> stringToArrayList(String str){
		String[] sa = str.split(" ");
		ArrayList<String> res = new ArrayList<String>();
		for (String string : sa) {
			res.add(string);
		}
		return res;
	}

	public HashMap<Integer, ArrayList<State>> getChart() {
		return chart;
	}
	
	public boolean isAccepted(){
		for (Integer i : this.chart.keySet()) {
			ArrayList<State> los = this.chart.get(i);
			for (State state : los) {
				if (state.isComplete() && state.getPrev() == 0 && state.getFrom().equals(startf)){
					
					ArrayList<Rule> rules = this.cfl.getMapping().get(startf);
					
					for (Rule rule : rules) {
						ArrayList<String> tosym = rule.getToSymbols();
						if (tosym.size() == state.getBeforeDot().size()){
							for (int j = 0 ; j < tosym.size(); j++){
								if (!tosym.get(j).equals(state.getBeforeDot().get(j))){
									return false;
								}
							}
						}
					}
					
				}
			}
		}
		return true;
	}

}