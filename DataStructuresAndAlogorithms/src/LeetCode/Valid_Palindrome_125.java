package LeetCode;

public class Valid_Palindrome_125 {
    /*
     * A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all
     * non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include
     * letters and numbers.
     * Given a string s, return true if it is a palindrome, or false otherwise.
     */

    private boolean validatePalindrome(String input) {
        boolean retVal=true;
        char[] charArr= input.toCharArray();
        StringBuilder sb= new StringBuilder();
        for (char c:charArr) {
            if (Character.isLetterOrDigit(c)) {
                char d = Character.toLowerCase(c);
                sb.append(d);
            }
        }
        String s1=sb.toString();
        String s2=sb.reverse().toString();
        if (!s1.equals(s2)){
            retVal=false;
        }
        return retVal;
    }

    public static void main(String args[]) {
        Valid_Palindrome_125 ob = new Valid_Palindrome_125();
        String input ="A man, a plan, a canal: Panama";
        System.out.println(ob.validatePalindrome(input));
        System.out.println(ob.validatePalindromeAccepted(input));
    }

    private boolean validatePalindromeAccepted(String input) {
        //Solve without using extra space. Two pointers left/right shift accordingly while left<right - if valid compare
        int left=0;
        int right=input.length()-1;
        boolean retVal=true;
        while (left<=right){
            left=validateLeft(input,left);//edge cases
            right=validateRight(input,right);
            if (left<=right) {
                if(Character.toLowerCase(input.charAt(left))!=Character.toLowerCase(input.charAt(right))){
                    retVal=false;
                    break;
                }
                left++;
                right--;
            }
        }
        return retVal;
    }

    private int validateLeft(String input,int index) {
        while( index<input.length()){
            if (Character.isLetterOrDigit(input.charAt(index))){
                break;
            } else{
                index++;
            }
        }
        return index;
    }

    private int validateRight(String input,int index) {
        while( index>0){
            if (Character.isLetterOrDigit(input.charAt(index))){
                break;
            } else{
                index--;
            }
        }
        return index;
    }
}
