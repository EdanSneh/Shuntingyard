import java.util.Iterator;
import java.util.LinkedList;

public class Logicalclass implements LogicalExpression{

	public Logicalclass(){}
	
	public boolean valid(String str) {
		polishorder rearange = new polishorder(str);
		LinkedList<String> organized = rearange.returnlist();
		Object[] varlist = rearange.returnvar();
		LinkedList<String> temp;
		double iterations = Math.pow(2, varlist.length);
		
		for (int i = 0; i < iterations; i++) {
			temp = hardclone(organized);
			int size = organized.size();
			
			if(sentencesolver(size, temp, varlist, i) == "0"){
				return false;
			}	
		}
		//63 int max (unsigned int)
		
		return true;
	}

	/*
	 * Checks to see if in all the possibilities there is a statement that returns true
	 * @see LogicalExpression#satisfiable(java.lang.String)
	 */
	public boolean satisfiable(String str) {
		polishorder rearange = new polishorder(str);
		LinkedList<String> organized = rearange.returnlist();
		Object[] varlist = rearange.returnvar();
		LinkedList<String> temp;
		double iterations = Math.pow(2, varlist.length);
		
		for (int i = 0; i < iterations; i++) {
			temp = hardclone(organized);
			int size = organized.size();
			
			if(sentencesolver(size, temp, varlist, i) == "1"){
				return true;
			}	
		}
		//63 int max (unsigned int)
		
		return false;
	}

	private static String notproblem(String string) {
		int val = Integer.parseInt(string);
		if(val == 0){
			return "1";
		}
		return "0";
	}

	private static String biconditional(String second, String first) {
		int sec = Integer.parseInt(second);
		int fir = Integer.parseInt(first);
		if(fir == 1 && sec == 0){
			return "0";
		}
		return "1";
	}

	private static String conditional(String second, String first) {
		int sec = Integer.parseInt(second);
		int fir = Integer.parseInt(first);
		if(fir == sec){
			return "1";
		}
		return "0";
	}

	private static String andproblem(String second, String first) {
		int sec = Integer.parseInt(second);
		int fir = Integer.parseInt(first);
		if(fir == 1 && sec == fir){
			return "1";
		}
		return "0";
	}

	private static String orproblem(String second, String first) {
		int sec = Integer.parseInt(second);
		int fir = Integer.parseInt(first);
		if(fir == 1 || sec == 1){
			return "1";
		}
		return "0";
	}

	private static String binaryval(String string, Object[] varlist, int iteration) {
		for (int mask = 0; mask < varlist.length; mask++) {
			if(string.equals(varlist[mask])){
				int c = iteration & (1 << mask);
			
				if(c > 0){
					return "1";
					
				} 
				return "0";
			}
		}
		return "0";
	}
	private LinkedList<String> hardclone(LinkedList<String> list){
		LinkedList<String> temp = new LinkedList<String>();
		for (int i = 0; i < list.size(); i++) {
			temp.add(list.get(i));
		}
		return temp;
		
	}
	
	

	public boolean contingent(String str) {
		int truth = 0;
		int nottruth = 0;
		
		polishorder rearange = new polishorder(str);
		LinkedList<String> organized = rearange.returnlist();
		Object[] varlist = rearange.returnvar();
		LinkedList<String> temp;
		double iterations = Math.pow(2, varlist.length);
		
		for (int i = 0; i < iterations; i++) {
			temp = hardclone(organized);
			int size = organized.size();
			
			if(sentencesolver(size, temp, varlist, i) == "1"){
				truth = 1;
			}
			else{
				nottruth = 1;
			}
			if(nottruth == 1 && truth == 1){
				return true;
			}
		}
		//63 int max (unsigned int)
		
		return false;
	}

	public int equivalent(String str1, String str2) {
		polishorder rearange2 = new polishorder(str2);
		LinkedList<String> organized2 = rearange2.returnlist();
		Object[] varlist2 = rearange2.returnvar();
		LinkedList<String> temp2;
		
		polishorder rearange1 = new polishorder(str1);
		LinkedList<String> organized = rearange1.returnlist();
		Object[] varlist = rearange1.returnvar();
		LinkedList<String> temp;
		double iterations = Math.pow(2, varlist.length);
		
		//checks if both sentences have the same variables
		if(!(varlist.length == varlist2.length)){
			System.err.println("both sentences must contain the same variables");
			return 0;
		}
		if(compare(varlist, varlist2) == false){
			System.err.println("both sentences must contain the same variables");
			return 0;
		}
		
		String[] table1 = new String[(int) iterations];
		String[] table2 = new String[(int) iterations];
		for (int i = 0; i < iterations; i++) {
			temp = hardclone(organized);
			int size = organized.size();
			
			table1[i] = sentencesolver(size, temp, varlist, i);
				
		}
		
		for (int i = 0; i < iterations; i++) {
			temp = hardclone(organized2);
			int size = organized2.size();
			
			table2[i] = sentencesolver(size, temp, varlist, i);
				
		}
		if(compareequals(table1, table2) == true){
			return 1;
		}
		else{
			return -1;
		}
	}

	private boolean compareequals(String[] table1, String[] table2) {
		for (int i = 0; i < table1.length; i++) {
			if(!(table1[i] == table2[i])){
				return false;
			}
		}
		return true;
	}

	private boolean compare(Object[] varlist, Object[] varlist2) {
		int contains = 0;
		for (int i = 0; i < varlist2.length; i++) {
			for (int j = 0; j < varlist.length; j++) {
				if(varlist2[i].equals(varlist[j])){
					contains = 1;
				}
			}
			if(!(contains == 1)){
				return false;
			}
			contains = 0;
		}
		return true;
	}

	public int entails(String str1, String str2) {
		polishorder rearange2 = new polishorder(str2);
		LinkedList<String> organized2 = rearange2.returnlist();
		Object[] varlist2 = rearange2.returnvar();
		
		polishorder rearange1 = new polishorder(str1);
		LinkedList<String> organized = rearange1.returnlist();
		Object[] varlist = rearange1.returnvar();
		LinkedList<String> temp;
		double iterations = Math.pow(2, varlist.length);
		
		//checks if both sentences have the same variables
		if(!(varlist.length == varlist2.length)){
			System.err.println("both sentences must contain the same variables");
			return 0;
		}
		if(compare(varlist, varlist2) == false){
			System.err.println("both sentences must contain the same variables");
			return 0;
		}
		
		String[] table1 = new String[(int) iterations];
		String[] table2 = new String[(int) iterations];
		for (int i = 0; i < iterations; i++) {
			temp = hardclone(organized);
			int size = organized.size();
			
			table1[i] = sentencesolver(size, temp, varlist, i);
				
		}
		
		for (int i = 0; i < iterations; i++) {
			temp = hardclone(organized2);
			int size = organized2.size();
			
			table2[i] = sentencesolver(size, temp, varlist, i);
				
		}
		if(entailcompare(table1, table2) == true){
			return 1;
		}
		else{
			return -1;
		}
	}
	
	private boolean entailcompare(String[] table1, String[] table2) {
		for (int i = 0; i < table2.length; i++) {
			if(table1[i] != table2[i] && table1[i] == "1"){
				return false;
			}
		}
		return true;
	}

	public static String sentencesolver(int size, LinkedList<String> temp, Object[] varlist, int i){
		for (int j = 0; j < size; j++) {
			if(temp.get(j).equals("~")){
				temp.set(j, notproblem(temp.get(j-1)));
				temp.remove(j-1);
				j = j-1;
				size = size-1;
			}
			else if(temp.get(j).equals("&")){
				temp.set(j, andproblem(temp.get(j-1),temp.get(j-2)));
				temp.remove(j-1);
				temp.remove(j-2);
				j = j-2;
				size = size-2;
			}
			else if(temp.get(j).equals("|")){
				temp.set(j, orproblem(temp.get(j-1),temp.get(j-2)));
				temp.remove(j-1);
				temp.remove(j-2);
				j = j-2;
				size = size-2;
			}
			else if(temp.get(j).equals("=>")){
				temp.set(j, conditional(temp.get(j-1),temp.get(j-2)));
				temp.remove(j-1);
				temp.remove(j-2);
				j = j-2;
				size = size-2;
			}
			else if(temp.get(j).equals("<=>")){
				temp.set(j, biconditional(temp.get(j-1),temp.get(j-2)));
				temp.remove(j-1);
				temp.remove(j-2);
				j = j-2;
				size = size-2;
			}
			else{
				temp.set(j, binaryval(temp.get(j), varlist, i));
				//System.out.println(temp.get(j));
				
			}
		}
		return temp.get(0);
	}
	
	
	//public int()
	//"p&|~=>q"
	//"1&1"
	//"0
	
}
