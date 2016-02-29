
public interface LogicalExpression {
	//public LogicalExpression(String str)
	
	boolean valid(String str);
	boolean satisfiable(String str);
	boolean contingent(String str);
	
	int equivalent(String str1, String str2);
	int entails(String str1, String st2);
}
