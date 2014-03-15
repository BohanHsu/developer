package tree;

import java.io.StringReader;
import java.util.Collection;
import java.util.List;

import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.TokenizerFactory;
import edu.stanford.nlp.trees.GrammaticalStructure;
import edu.stanford.nlp.trees.GrammaticalStructureFactory;
import edu.stanford.nlp.trees.PennTreebankLanguagePack;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreebankLanguagePack;

public class ParseTree {
	public static Tree parseTree(String sentence) {
		String text = sentence;
		TreebankLanguagePack tlp = new PennTreebankLanguagePack();
		GrammaticalStructureFactory gsf = tlp.grammaticalStructureFactory();
		LexicalizedParser lp = LexicalizedParser
				.loadModel("edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz");
		lp.setOptionFlags(new String[] { "-maxLength", "500",
				"-retainTmpSubcategories" });
		TokenizerFactory tokenizerFactory = PTBTokenizer.factory(
				new CoreLabelTokenFactory(), "");
		List wordList = tokenizerFactory.getTokenizer(new StringReader(text))
				.tokenize();
		Tree tree = lp.apply(wordList);
		GrammaticalStructure gs = gsf.newGrammaticalStructure(tree);
		Collection tdl = gs.typedDependenciesCCprocessed(true);
		return tree;
	}

	public static void main(String[] args) {
		/*
		 * String[] stringArray = new String[] { "do coconuts speak ?",
		 * "who does Arthur suggest she carry ?",
		 * "are they suggesting Arthur ride to Camelot ?",
		 * "the Holy Grail was covered by a yellow fruit .", "do not speak !",
		 * "Arthur will have been riding for eight nights .",
		 * "Arthur and Guinevere migrate frequently .",
		 * "he knows what they are covering with that story .",
		 * "the king drank to the castle that was his home ." };
		 */
		String[] stringArray = new String[] {
				"roman rulers are named after july and august",
				"At the corner of Park Street is the bus stop" };
		for (String string : stringArray) {
			System.out.println(string);
			System.out.println(parseTree(string));
		}
	}
}
