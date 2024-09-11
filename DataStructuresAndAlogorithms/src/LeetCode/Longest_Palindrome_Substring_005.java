package LeetCode;

public class Longest_Palindrome_Substring_005 {

    private String longestPalindromeBasic(String input) {
        String output="";
        for (int i=0;i<input.length();i++){
            for (int j=i;j<input.length();j++){
                String inner =input.substring(i, j);
                if (isPalindrome(inner)){
                    if (output.length()<inner.length()){
                        output=inner;
                    }
                }
            }
        }
        return output;
    }

    private boolean isPalindrome(String substring) {
        boolean retVal=true;
        for (int i=0;i<substring.length()/2;i++){
            if (substring.charAt(i)==substring.charAt(substring.length()-i-1)){
            }else{
                retVal=false;
                break;
            }
        }
        return retVal;
    }

    public static void main(String args[]) {
        Longest_Palindrome_Substring_005 ob = new Longest_Palindrome_Substring_005();
        String input = "babad";
        System.out.println(ob.longestPalindromeBasic(input));
        System.out.println(ob.longestPalindrome(input));
    }

    private String longestPalindrome(String input) {
        String output="";
        for (int i=1;i<input.length()-1;i++){
            String curr= isPalindromeCentric(i,input);
                if (output.length()<curr.length()){
                    output=curr;
                }
            }
        return output;
    }
    private String isPalindromeCentric(int i,String input){
        int leftMax=0;
        int rightMax=input.length()-1;
        int shift=1;
        StringBuilder sb= new StringBuilder();
        sb.append(input.charAt(i));
        while (i+shift<=rightMax && i-shift>=leftMax && input.charAt(i+shift)==input.charAt(i-shift)){
            sb.append(input.charAt(i+shift)) ;
            sb.insert(0,input.charAt(i-shift));
            shift++;
        }
        return sb.toString();
    }
}
