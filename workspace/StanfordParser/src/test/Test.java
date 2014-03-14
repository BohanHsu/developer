package test;

import java.io.StringReader;
import java.util.Collection;
import java.util.List;

import com.chaoticity.dependensee.Main;

import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.TokenizerFactory;
import edu.stanford.nlp.trees.GrammaticalStructure;
import edu.stanford.nlp.trees.GrammaticalStructureFactory;
import edu.stanford.nlp.trees.PennTreebankLanguagePack;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreebankLanguagePack;

public class Test {
	public static void main(String[] args) throws Exception {
		String text = "how are you.";
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
		System.out.println(tree);
//		Main.writeImage(tree, tdl, "image.png", 3);
	}
}