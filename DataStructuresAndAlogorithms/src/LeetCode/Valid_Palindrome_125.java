package LeetCode;

public class Valid_Palindrome_125 {
    /*
     * A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all
     * non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include
     * letters and numbers.
     * Given a string s, return true if it is a palindrome, or false otherwise.
     */

    public static void main(String args[]) {
        Valid_Palindrome_125 ob = new Valid_Palindrome_125();
        String input ="A man, a plan, a canal: Panama";
        System.out.print(ob.validatePalindrome(input));
    }

    private boolean validatePalindrome(String input) {
        //two pointers left /right while left<right - if valid compare, if not shift accordingly
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
