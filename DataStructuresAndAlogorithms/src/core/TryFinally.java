package core;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class TryFinally {
    public static void main(String[] args) {
        tryCatchFinally();
        tryWith();
    }

    public static void tryCatchFinally(){
        int b = 0;
        try{
            b= 5/0;
        }
        catch(Exception e){
            b= 5/0;	System.out.println("e2");
        }
        finally {
            System.out.println("finally"+b);
        }
    }

    public static void tryWith(){//avoiding needing to close resources with a finally

        try(  FileInputStream     input         = new FileInputStream("file.txt");
              BufferedInputStream bufferedInput = new BufferedInputStream(input)
        ) {

            int data = bufferedInput.read();
            while(data != -1){
                System.out.print((char) data);
                data = bufferedInput.read();
            }
        }
        catch (Exception fnfe) {
            fnfe.printStackTrace();
        }
    }
}