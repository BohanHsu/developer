package grammar;

import java.util.ArrayList;

public class Rule {
	int weight = 0;
	String fromSymbol = null;
	ArrayList<String> toSymbols = new ArrayList<String>();

	/**
	 * construct function
	 * 
	 * @param weight : weight of this grammar
	 * @param fromSymbol : from symbol
	 * @param toSymbolsArray : to symbol, can be null
	 */
	public Rule(int weight, String fromSymbol, String[] toSymbolsArray) {
		super();
		this.weight = weight;
		this.fromSymbol = fromSymbol;
		this.toSymbols = new ArrayList<String>();
		if (toSymbolsArray != null) {
			for (String symbol : toSymbolsArray) {
				this.toSymbols.add(symbol);
			}
		}
	}
	
	@Override
	public String toString() {
		String str = "[ ";
		str += fromSymbol;
		str += " -> ";
		for (String toSmb : toSymbols) {
			str += toSmb;
			str += " ";
		}
		str += "]";
		return str;
	}

	public double getWeight() {
		return weight;
	}

	public String getFromSymbol() {
		return fromSymbol;
	}

	public ArrayList<String> getToSymbols() {
		return toSymbols;
	}
	
	
}
