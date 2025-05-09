package operations;

public class StringOperations {
    //length, indexOf, valueOf (char c), toCharArray, charAt, subString, split
    //copyValueOf(char[] data)
    public static void main(String [] args){
        String str1 = String.valueOf(5);
        System.out.println(str1);
        String str2 = String.valueOf(true);
        char[] data = str2.toCharArray();
        str1= String.copyValueOf(data);//same as str1=new String(data);

        String str4= "qwertyuiop";
        String[] str =str4.split("yu"); System.out.println(str.length);
        int pos = str4.indexOf("yu");   System.out.println(pos);
        if (str4.substring(5, 10).equals("yuiop")){
            System.out.println("Pass 1");
        }
        char myChar = 'A';	int asciiValue = (int) myChar;
        System.out.println(asciiValue);
        System.out.println(Character.getNumericValue('A'));
        System.out.println(Character.getNumericValue('2'));
    }
}

