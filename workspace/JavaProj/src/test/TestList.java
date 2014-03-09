package test;

import java.util.ArrayList;

public class TestList {
	public static void main(String[] args) {
		ArrayList<String> array = new ArrayList<String>();
		array.add("1");
		array.add("2");
		array.add("3");
		array.add("4");
		array.add("5");
		array.add("6");

		ArrayList<String> newPart = new ArrayList<String>();
		newPart.add("a");
		newPart.add("b");
		newPart.add("c");

		// replace "3" in array with newPart

		int index = array.indexOf("3");
		array.remove("3");
		array.addAll(index, newPart);

		System.out.println(array);

		ArrayList<Rule> a1 = new ArrayList<Rule>();
		a1.add(new Rule(0));
		a1.add(new Rule(1));
		a1.add(new Rule(2));
		a1.add(new Rule(3));

		ArrayList<Rule> a2 = new ArrayList<Rule>(a1);

		for (Rule rule : a2) {
			rule.i += 1;
		}

		for (Rule rule : a2) {
			System.out.println(rule.i);
		}

		for (Rule rule : a1) {
			System.out.println(rule.i);
		}
	}
}

class Rule {
	int i;

	public Rule(int i) {
		super();
		this.i = i;
	}

}
