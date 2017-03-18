import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegxPractice
{
	public static void main(String[] args)
	{
		String wrong = "bobblabla";
		
		String systemCommandString = "###SYSTEM::";
		String systemCommandPatternString = "(#{3})(SYSTEM::)";
		
		Pattern r = Pattern.compile(systemCommandPattern);
		Matcher m1 = r.matcher(systemCommandString);
		Matcher m2 = r.matcher(wrong);
		System.out.println(m1.find());
		System.out.println(m2.find());
	}
}
