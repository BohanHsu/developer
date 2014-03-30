package parser;

import grammar.ContextFreeLanguage;
import grammar.Rule;

import java.util.ArrayList;
import java.util.LinkedList;

public class State {
	private ContextFreeLanguage cfl = null;
	private String from  = null;
	private ArrayList<String> beforeDot = null;
	private ArrayList<String> afterDot = null;
	private int prev = 0;
	
	public State(ContextFreeLanguage cfl, Rule rule, int prev) {
		this.cfl = cfl;
		this.from = rule.getFromSymbol();
		this.beforeDot = new ArrayList<String>();
		this.afterDot = new ArrayList<String>(rule.getToSymbols());
		this.prev = prev;
	}
	
	public State(State state) {
		this.cfl = state.cfl;
		this.from = state.from;
		this.beforeDot = new ArrayList<String>(state.beforeDot);
		this.afterDot = new ArrayList<String>(state.afterDot);
		this.beforeDot.add(this.afterDot.get(0));
		this.afterDot.remove(0);
		this.prev = state.prev;
	}
	
	public State(ContextFreeLanguage cfl, String pos, String word, int i){
		this.cfl = cfl;
		this.from = pos;
		this.beforeDot = new ArrayList<String>();
		this.beforeDot.add(word);
		this.afterDot = new ArrayList<String>();
		this.prev = i;
	}
	
	public String next(){
		return this.afterDot.get(0);
	}
	
	public boolean isNextNonterminal(){
		return this.cfl.getNonTerminalSymbols().contains(this.afterDot.get(0));
	}
	
	public boolean isComplete(){
		return this.afterDot.isEmpty();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof State){
			State s = (State)obj;
			if (s.from.equals(this.from)){
				if (this.beforeDot.size() == s.beforeDot.size() && this.afterDot.size() == s.afterDot.size()){
					for (int i = 0 ; i < this.beforeDot.size(); i++){
						if (!this.beforeDot.get(i).equals(s.beforeDot.get(i))){
							return false;
						}
					}
					
					for (int i = 0 ; i < this.afterDot.size(); i++){
						if (! this.afterDot.get(i).equals(s.afterDot.get(i))){
							return false;
						}
					}
					return true;
				}
			}
		}
		return false;
	}

	public String getFrom() {
		return from;
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
		
		str += "* ";
		
		for (String s : this.afterDot) {
			str += s + " ";
		}
		
		return str + "(" + this.prev + ")";
		
	}

	public ContextFreeLanguage getCfl() {
		return cfl;
	}

	public ArrayList<String> getBeforeDot() {
		return beforeDot;
	}
	
	
}