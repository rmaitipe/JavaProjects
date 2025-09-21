package operations;

import java.util.Arrays;

public class StringCharOperations {
    //length, indexOf, valueOf (char c), toCharArray, charAt, subString, split
    //copyValueOf(char[] data)
    public static void main(String [] args){
        String str1 = String.valueOf(5);
        System.out.println(str1);
        String str2 = String.valueOf(true);

        char[] data = str2.toCharArray();
        str1= String.copyValueOf(data);//same as str1=new String(data);

        String str4= "qwertyuiop";
        String[] str =str4.split("yu");
        System.out.println(Arrays.toString(str));
        String test="/home///foo//frev/";
        String [] strArr= test.split("/");
        System.out.println(Arrays.toString(strArr));
        String str5= "qwer+yui-puodmp";
        String[] operands= str5.split("[+-]");//?
        System.out.println(Arrays.toString(operands));
        int pos = str4.indexOf("yu");
        System.out.println(pos);
        if (str4.substring(5, 10).equals("yuiop")){
            System.out.println("Pass 1");
        }
        int asciiValue = (int) 'A';
        System.out.println(asciiValue);
        //String str5="ACE";
        //System.out.println((int)str5.charAt(0));
        System.out.println(Character.isLetterOrDigit('@'));
        System.out.println(Character.getNumericValue('a'));
        System.out.println(Character.getNumericValue('A'));
        System.out.println(Character.getNumericValue('2'));
        System.out.println((int) 'a');//ASCII value
        System.out.println((int) 'A');//ASCII value
        System.out.println((char) 65);
    }
}

