
public class Testcode {
	public static void main(String[] args) {
		String Sentence = "~ a | a";
		String Sentence2 = "~ a & ~ b";
		boolean s = true;
		Logicalclass logic = new Logicalclass();
		s = logic.satisfiable(Sentence);
		System.out.println("Sentence satisfiablitiy = " + s);
		s = logic.contingent(Sentence);
		System.out.println("Sentence contingency = " + s);
		s = logic.valid(Sentence);
		System.out.println("Sentence validity = " + s);
		int w = logic.equivalent(Sentence, Sentence2);
		System.out.println("Sentence equivelency = " + w);
		w = logic.entails(Sentence, Sentence2);
		System.out.println("Sentence entailment = " + w);
	}
}
