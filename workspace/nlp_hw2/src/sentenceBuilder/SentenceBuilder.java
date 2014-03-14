package sentenceBuilder;

import java.util.ArrayList;
import java.util.Stack;

import grammar.ContextFreeLanguage;

public class SentenceBuilder {
	private ContextFreeLanguage cfl = null;
	private boolean printingLog = false;

	public SentenceBuilder(ContextFreeLanguage cfl) {
		super();
		this.cfl = cfl;
		this.printingLog = cfl.isPrintingLog();
	}

	
//	/**
//	 * build a sentence with specific length, automatically ignore the ending period when count length
//	 * 
//	 * @param sentenceLength
//	 *            : length of this sentence
//	 * @return : a sentence iff this grammar can build this sentence, or null
//	 */
//	public String buildSentence(int sentenceLength){
//		return buildSentence(sentenceLength, true);
//		
//	}
	

	/**
	 * build a sentence
	 * @return : a string, null iff no sentence can be built
	 */
	public String buildSentence() {
		
		Stack<IntermediateSentence> stack = new Stack<IntermediateSentence>();
		IntermediateSentence startSymbol = new IntermediateSentence(cfl);

		// printing log
		if (this.printingLog) {
			System.out.println("--starting function build sentence:");
			System.out.println("--choosed start symbol");
			startSymbol.printLog();
		}
		// end

		stack.push(startSymbol);

		IntermediateSentence is = null;
		while (!stack.empty()) {
			is = stack.peek();

			// printing log
			if (this.printingLog) {
				System.out.println("--sentence now:");
				is.printLog();
			}
			// end

			if (is.qualified()) {
				// return the found sentence
				return is.getBuiltSentence();
			}
			if (is.needBackOff()) {
				// need back off
				stack.pop();
			} else {
				is = new IntermediateSentence(is);
				stack.push(is);
			}
		}
		return null;
	}
}