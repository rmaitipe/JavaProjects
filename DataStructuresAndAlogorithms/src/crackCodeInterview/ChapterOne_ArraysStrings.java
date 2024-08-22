package crackCodeInterview;

import java.util.Arrays;
import java.util.HashMap;

public class ChapterOne_ArraysStrings {
    /*
     * 1. Is Unique: Implement an algorithm to determine if a string has all unique characters. What if you
     * cannot use additional data structures?
     *
     * 2. Check Permutation: Given two strings, write a method to decide if one is a permutation of the other.
     *
     * 3. URLify: Write a method to replace all spaces in a string with '%20'. You may assume that the string
     * has sufficient space at the end to hold the additional characters, and that you are given the "true"length of the string.
     * (Note: If implementing in Java, please use a character array so that you can perform this operation in place.)
     *
     * 4. Palindrome Permutation: Given a string, write a function to check if it is a permutation of a palindrome.
     *
     * 5. One Away: There are three types of edits that can be performed on strings: insert a character, remove a character,
     * or replace a character. Given two strings, write a function to check if they are one edit (or zero edits) away.
     *
     * 6. String Compression: Implement a method to perform basic string compression using the counts of repeated characters.
     * For example, the string aabcccccaaa would become a2blc5a3. If the "compressed" string would not become smaller than the original string,
     * return the original string.
     *
     * 7. Rotate Matrix: Given an image represented by an NxN matrix, where each pixel in the image is 4
     * bytes, write a method to rotate the image by 90 degrees. Can you do this in place?
     *
     * 8. Zero Matrix: Write an algorithm such that if an element in an MxN matrix is 0 (Array), its entire row and column are set to 0.
     *
     * 9. String Rotation:Assume you have a method isSubstring which checks if one word is a substring of another. Given two strings,
     * sl and s2, write code to check if s2 is a rotation of sl using only one call to isSubstring (e.g., "waterbottle" is a rotation of"erbottlewat").
     */

    public static void main(String args[]){
        ChapterOne_ArraysStrings cc = new ChapterOne_ArraysStrings();
        String test1="wqasdserww";
        String test2="asdserwwwq";
        String test3="tacoatc";
        String test4="taco cat";
        cc.isUnique(test1); cc.isUnique2(test1);
        cc.isPermutation(test1,test2);
        cc.isOneAway(test1,test2);
        cc.urlify(test4);
        cc.isPermutePalindrome(test3);
        cc.compressString(test1);
        cc.StringRotate(test1, test2);
    }

    private boolean isUnique(String test) {//HashSet add() method
        boolean ret=false;
        char[] arr1 =test.toCharArray();
        HashMap<Character,Integer> map=new HashMap<>();
        for (Character c:arr1){
            if (map.containsKey(c)){
                map.put(c, map.get(c)+1);
				/*put Returns: the previous value associated with key, or null if there was no mapping for key.
			    (A null return can also indicate that the map previously associated null with key.)*/
            }
            else{
                map.put(c, 1);
            }
        }
        Integer aval =map.entrySet().stream().reduce((i, j) -> i.getValue() > j.getValue() ? i : j).get().getValue();
        if (aval>2){
            System.out.println("isUnique test failed: " +test);
        }
        else{
            ret=true;
            System.out.println("isUnique test passed: " +test);
        }
        return ret;
    }
    private boolean isUnique2(String test) {
        boolean ret=true;
        char[] arr1 =test.toCharArray();
        nest_loop:
        for (int i=0;i<arr1.length;i++){
            for (int j=i+1;j<arr1.length;j++){
                if (arr1[i]==arr1[j]){
                    System.out.println("isUnique test failed: " +test);
                    ret=false;
                    break nest_loop;
                }
            }
        }
        return ret;
    }
    private boolean isPermutation(String a, String b) {
        boolean ret=false;
        if (a.length()!=b.length()){
            System.out.println("isPermutation test failed: " +a +" "+ b);
        }
        else{
            char[] arr1 =a.toCharArray();
            char[] arr2 =a.toCharArray();
            Arrays.sort(arr1);
            Arrays.sort(arr2);
            if (Arrays.equals(arr1, arr2)){
                System.out.println("isPermutation test passed: " +a +" "+ b);
                ret=true;
            }
            else{
                System.out.println("isPermutation test failed: " +a +" "+ b);
            }
        }
        return ret;
    }

    private void urlify(String test4) {
        String arr[]= test4.split(" ");
        int length =arr.length;
        StringBuilder sb=new StringBuilder();
        for (int i=0;i<length-1;i++){
            sb.append(arr[i]+"%20");
        }
        sb.append(arr[length-1]);
        System.out.println("urlify: "+sb.toString());
    }

    private boolean isOneAway(String test1, String test2) {//abc abb ab abcd
        boolean ret=false;
        if(Math.abs(test1.length()-test2.length())>1){
            System.out.println("isOneAway test failed: " +test1 +" "+ test2);
        }
        else{
            if (test1.length()==test2.length()){
                int count=0;
                for (int i=0;i<test2.length();i++){
                    if (test1.charAt(i)!=test2.charAt(i)){
                        count++;
                    }
                }
                if (count==1) ret=true;
            }
            else{
                if (test1.contains(test2)|| test2.contains(test1)){
                    ret=true;
                }
            }
        }
        return ret;
    }

    private boolean isPermutePalindrome(String test1) { //tacocat -> aaccott
        boolean ret= false;
        char[] arr1 =test1.toCharArray();
        Arrays.sort(arr1);
        int odd=0;
        char oddchar = 0;
        //if length 1 return true
        for (int i =1;i<arr1.length;i++){
            if (arr1[i-1]==arr1[i]){
                if (arr1[i]==oddchar){
                    odd--;
                    oddchar=0;//reset
                }
            }
            else{
                odd++;
                oddchar=arr1[i];
            }
        }
        if (odd>1){	System.out.println("isPermPalindrome test failed: " +test1); ret=false;	}
        else{ System.out.println("isPermPalindrome test passed: " +test1); ret=true; }
        return ret;
    }

    public String compressString(String str){//aabcccccaaa -> a2blc5a3
        if (str.length()==1){
            return str;
        }
        else{
            char[] arr= str.toCharArray();
            int seq=1;
            char temp=arr[0];
            StringBuilder sb =new StringBuilder();
            for (int i=1;i<str.length();i++){
                if (arr[i-1]==arr[i]){
                    seq++;
                }
                else{
                    sb.append(arr[i-1]);
                    sb.append(seq);
                    temp=arr[i];
                    seq=1;
                }
            }
            sb.append(temp);
            sb.append(seq);
            str = sb.toString();
        }
        System.out.println("compressString: "+str);
        return str;
    }

    public static void modifyMatrix(int mat[ ][ ], int R, int C)
    {
        int row[ ]= new int [R];
        int col[ ]= new int [C];
        int i, j;
        for (i = 0; i < R; i++) /* Initialize all values of row[] as 0 */
            row[i] = 0;
        for (i = 0; i < C; i++) /* Initialize all values of col[] as 0 */
            col[i] = 0;
        /* Store the rows and columns to be marked as 1 in row[] and col[] arrays respectively */
        for (i = 0; i < R; i++) {
            for (j = 0; j < C; j++) {
                if (mat[i][j] == 1) {
                    row[i] = 1;
                    col[j] = 1;
                }
            }
        }
        /* Modify the input matrix mat[] using the above constructed row[] and col[] arrays */
        for (i = 0; i < R; i++) {
            for (j = 0; j < C; j++) {
                if ( row[i] == 1 || col[j] == 1 ) {
                    mat[i][j] = 1;
                }
            }
        }
    }

    public boolean StringRotate(String a, String b){
        boolean ret=false;
        int length1=1;
        int val=0;
        while (true){
            if (a.indexOf(b.substring(0,length1)) !=-1){
                val=a.indexOf(b.substring(0,length1));
                length1++;
            }
            else{
                break;
            }
        }
        String temp =a.substring(val, a.length());
        temp= temp+a.substring(0, val);
        if (b.equals(temp)){
            System.out.println("StringRotate passed: "+a +" " +b);
            ret= true;
        }
        else {
            System.out.println("StringRotate failed: "+a +" " +b);
        }
        return ret;
    }
}

