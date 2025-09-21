package crackCodeInterview;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ChapterOne_ArraysStrings {
    /*
     * 1. Is Unique: Implement an algorithm to determine if a string has all unique characters. What if you
     * cannot use additional data structures?
     *
     * 2. Check Permutation: Given two strings, write a method to decide if one is a permutation of the other.
     *
     * 3. URLify: Write a method to replace all spaces in a string with '%20'. You may assume that the string
     * has sufficient space at the end to hold the additional characters, and that you are given the true length of the string.
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
     * (Set Matrix Zeroes 073)
     *
     * 9. String Rotation:Assume you have a method isSubstring which checks if one word is a substring of another. Given two strings,
     * s1 and s2, write code to check if s2 is a rotation of s1 using only one call to isSubstring (e.g., "waterbottle" is a rotation of"erbottlewat").
     */

    public static void main(String args[]){
        ChapterOne_ArraysStrings cc = new ChapterOne_ArraysStrings();
        String test1="wqasdserww";
        String test2="asdverwxyq";
        String test3="tacoatc";
        String test4="taco cat  ";
        System.out.println(cc.isUnique(test1));
        System.out.println(cc.isUniqueNoStructures(test1));
        System.out.println(cc.isUniqueOptimal(test2));
        System.out.println(cc.isPermutation(test1,test2));
        cc.urlify(test4);
        cc.urlifyInSpace("Mr John Smith    ",13);
        System.out.println(cc.isOneAway(test1,test2));
        System.out.println(cc.isOneAway("test1","test2"));
        System.out.println(cc.isOneAway("test1","test"));
        System.out.println(cc.isPermutePalindrome(test3));
        System.out.println(cc.compressString(test1));
        int[][] scores = new int[3][3];
        scores[0][0] = 1;
        scores[0][1] = 1;
        scores[1][0] = 1;
        scores[1][1] = 1;
        scores[2][2] = 1;
        scores[1][2] = 1;
        scores[2][1] = 1;
        cc.modifyMatrix(scores);
        System.out.println(cc.stringRotate(test1, test2));
    }

    private boolean isUnique(String test) {//HashSet add() method
        boolean ret=true;
        char[] arr1 =test.toCharArray();
        Set<Character> set=new HashSet<>();
        for (Character c:arr1){
            if (!set.add(c)){
                System.out.println("isUnique test failed: " +test);
                ret=false;
                break;
            }
        }
        return ret;
    }

    private boolean isUniqueOptimal(String test){
        boolean ret=true;
        boolean[] uniqueArr=new boolean[128];//ASCII char set
        char[] arr1 =test.toCharArray();
        for (Character c:arr1){
            if (uniqueArr[c]==false){
                uniqueArr[c]=true;
            }
            else{
                System.out.println("isUnique test failed: " +test);
                ret=false;
                break;
            }
        }
        return ret;
    }

    private boolean isUniqueNoStructures(String test) {
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
        //Another solution is to use the leverage unique test, when iterating over 2nd string char count should match,
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

    private void urlifyInSpace(String test4,int length) {
        char[] arr= test4.toCharArray();
        int rightIndex=arr.length-1   ;
        for (int i=length-1;i>0;i--){
            //if actual char shift right
            //if space move next spot by 3 to left
            if (arr[i]==' ') {
                arr[rightIndex]='0';
                rightIndex--;
                arr[rightIndex]='2';
                rightIndex--;
                arr[rightIndex]='%';
                rightIndex--;
            }else{
                arr[rightIndex]=arr[i];
                rightIndex--;
            }
        }
        System.out.println(String.valueOf(arr));
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
                //switch
                if (test2.length()>test1.length()){
                    String temp= test2;
                    test2=test1;
                    test1=temp;
                }
                //if (test1.length()>test2.length()){
                    int j=0;
                    int jump=0;
                    for (int i=0;i<test2.length();i++){
                        if (test1.charAt(j+jump)!=test2.charAt(i)){
                            jump++;
                        } else{
                            j++;
                        }
                    }
                    if (j==test2.length()){//1 away [abcd abcde]  [abd abcd]
                        jump++;
                    }
                    if (jump==1) ret=true;
                //}
            }
        }
        System.out.println("isOneAway:"+ret);
        return ret;
    }

    private boolean isPermutePalindrome(String test1) { //tacocat -> aaccott
        boolean ret= false;
        char[] charArray =test1.toCharArray();
        int[] arr =new int [128];
        int oddCount=0;
        for (char c: charArray){
            arr[c]=arr[c]+1;
            if (arr[c]%2==0){
                oddCount++;
            } else{
                oddCount--;
            }
        }
        if (oddCount>1){
            System.out.println("isPermPalindrome test failed: " +test1);
        }
        else{
            System.out.println("isPermPalindrome test passed: " +test1);
            ret=true;
        }
        return ret;
        /*
        Arrays.sort(charArray);
        int odd=0;
        char oddchar = null;
        //if length 1 return true
        for (int i =1;i<charArray.length;i++){
            if (charArray[i-1]==charArray[i]){
                if (charArray[i]==oddchar){
                    odd--;
                    oddchar=null;//reset
                }
            }
            else{
                odd++;
                oddchar=arr1[i];
            }
        }
        */
    }

    public String compressString(String str){//aabcccccaaa -> a2blc5a3
        String out="";
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
            out = sb.toString();
        }
        System.out.println("compressString: "+out);
        return str.length()<out.length()? str: out;
    }

    private void modifyMatrix(int[][] mat){
        int[] row = new int [mat.length];
        int[] col = new int [mat[0].length];
        Arrays.fill(row,1);
        Arrays.fill(col,1);
        /*
        int i, j;
        for (i = 0; i < row.length; i++)
            row[i] = 1;
        for (i = 0; i < col.length; i++)
            col[i] = 1;
         */
        /* Store the rows and columns to be marked as 0 in row[] and col[] arrays respectively */
        for (int i = 0; i < row.length; i++) {
            for (int j = 0; j < col.length; j++) {
                if (mat[i][j] == 0) {
                    row[i] = 0;
                    col[j] = 0;
                }
            }
        }
        /* Modify the input matrix mat[] using the above constructed row[] and col[] arrays */
        for (int i = 0; i < row.length; i++) {
            if ( row[i] == 0 ) {
                nullifyRow(mat,i);
            }
        }
        for (int j = 0; j < col.length; j++) {
            if ( col[j] == 0 ) {
                nullifyCol(mat,j);
            }
        }
        System.out.println("modifyMatrix complete");
    }

    private void nullifyCol(int[][] mat, int j) {
        for (int i= 0; i < mat.length; i++) {
            mat[i][j] = 0;
        }
    }

    private void nullifyRow(int[][] mat, int i) {
        for (int j = 0; j < mat[0].length; j++) {
            mat[i][j] = 0;
        }
    }

    public boolean stringRotate(String a, String b){
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
    /* Above solution does not use the given method isSubString.
     The accepted solution:
     Concatenate the s1 string with itself
     Check if the string s2 is substring of the concatenated s1 string.
     If the substring is a part of the concatenated string then the strings are rotation of each other
     ex. if you concatenate the same string twice     "terbottlewaterbottle"
     Then we see that the string “erbottlewat” is a substring of the concatenated s1 string.
     */
}

