package com;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* Each time the method String.matches(String regex) is invoked, the specified regular expression is compiled again
 * 
 * Common matching symbols for reference
.			Matches any character
^regex		Finds regex that must match at the beginning of the line.
regex$		Finds regex that must match at the end of the line.
[abc]		Set definition, can match the letter a or b or c.
[abc][vz]	Set definition, can match a or b or c followed by either v or z.
[^abc]		When a caret appears as the first character inside square brackets, it negates the pattern. This pattern matches any character except a or b or c.
[a-d1-7]	Ranges: matches a letter between a and d and figures from 1 to 7, but not d1.
X|Z			Finds X or Z.
XZ			Finds X directly followed by Z.
$			Checks if a line end follows.
*/
public class RegExTester {

		public static void main(String args []){
			String datePattern = "^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$";
			System.out.println("datePattern :"+testString(datePattern,"02/28/2018"));
			String emailPattern = ".+@.+\\.[a-z]+";
			System.out.println("emailPattern :"+testString(emailPattern,"brmaitipe@gmail.com"));
			String phonePattern = "^\\d{3}[-]\\d{3}[-]\\d{4}$|^\\d{10}$|^[(]\\d{3}[)]\\d{3}[-]\\d{4}$";
			System.out.println("phonePattern :"+testString(phonePattern,"609-678-6677"));
		}
		
		public static boolean testString(String regEx, String input){
			
			Pattern pattern = Pattern.compile(regEx);
			Matcher matcher = pattern.matcher((CharSequence)input);
			return matcher.matches();
		}
}
