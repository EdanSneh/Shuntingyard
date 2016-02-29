import java.util.*;

public class polishorder {
	static LinkedList<String> list;
	static LinkedList<String> variableamount;
	static Stack stack;
	
	public polishorder(String str){
		String[] sentence = str.split(" ");
		list = new LinkedList<String>();
		variableamount = new LinkedList<String>();
		stack = new Stack(sentence.length);
		translate(sentence);
	}
//	public static void main(String[] args) {
//		String str = "a & b | b => d ";
//		String[] sentence = str.split(" ");
//		list = new LinkedList<String>();
//		stack = new Stack(sentence.length);
//		translate(sentence);
//	}
	private static void translate(String[] original) {
		for (int i = 0; i < original.length; i++) {
			//printlist(list);
			if(operatorcheck(original[i]) == true){
				
				while(stackimportant(original[i], stack.check_value()) == true && !(stack.index() == -1)){
					//pop stack
					list.add(stack.pop());
				}	
				
					//push val from stack
					stack.push(original[i]);
			}
			else if(original[i].equals("(")){
				stack.push(original[i]);
			}
			else if(original[i] .equals(")")){
				
				while(!((stack.check_value()).equals("("))){
					//System.out.println(stack.check_value());
					list.add(stack.pop());
				}
				stack.pop();
			}
			else{
				if(!(list.contains(original[i]))){
					variableamount.add(original[i]);
				}
				//push variable to list
				list.add(original[i]);
				
				
			}			
		}
		
		while(stack.index() != -1){
			list.add(stack.pop());
		}
			//printlist(list);
		
	}
	
	public static boolean operatorcheck(String operator){
		if(operator.equals("~") || operator.equals("&") || operator.equals("|") || operator.equals("=>") || operator.equals("<=>")){

			return true;
		}
		return false;
	}
	
	/*
	 * checks if two operators have the same value
	 */
	public static boolean stackimportant(String newitem, String stacktop){
		if(operatorval(newitem) < operatorval(stacktop)){
			//push to stack
			return false;
		}
		//pop the stack
		return true;
	}
	//Assigns each operator a value
	public static int operatorval(String operator){
		switch(operator){
			case "(": return 5;
			case "~": return 1;
			case "&": return 2;
			case "|": return 3;
			case "=>": return 4;
			case "<=>": return 4;
		}
		return -1;
	}
	
	public static void printlist(LinkedList<String> list){
		Object[] array = list.toArray();
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] +" ");
		}
		System.out.println();
	}
	
	public LinkedList<String> returnlist(){
		return list;
	}
	public Object[] returnvar(){
		Object[] array = variableamount.toArray();
		return array;
	}
}
