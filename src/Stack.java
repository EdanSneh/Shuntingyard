
public class Stack {
	static int index = -1;
	static String[] nstack;
	
	public Stack(int stacklen){
		nstack = new String[stacklen];
	}
	
	public void push(String operator){
		index++;
		nstack[index] = operator;
	}
	
	public String pop(){
		index--;
		return nstack[index+1];
	}
	
	public String check_value(){
		if(index != -1){
		return nstack[index];
		}
		return "empty";
	}
	
	public int index(){
		return index;
	}
}