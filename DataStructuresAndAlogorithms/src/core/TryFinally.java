package core;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
 * The finally block in Java is designed to always execute, even when exceptions occur.
 * However, there are scenarios where the finally block might be skipped: ex System.exit() or Runtime.getRuntime().halt()
 * called within the try or catch block, JVM Crash, Thread Interruption or Termination.
 *
 * https://www.baeldung.com/java-18-deprecate-finalization [tryWithResources Java7Solution, Java9 Solution: Use Cleaner API]
 */
public class TryFinally {
    public static void main(String[] args) {
        tryCatchFinally();
        tryWithResources();
    }

    public static void tryCatchFinally(){
        int b = 0;
        try{
            b= 5/0;
        }
        catch(Exception e){
            System.out.println("e1");
            System.exit(0);
            System.out.println("e2");
        }
        finally {
            System.out.println("finally"+b);
        }
    }

    public static void tryWithResources(){//avoiding needing to close resources with a finally
        try( FileInputStream input = new FileInputStream("file.txt");
            BufferedInputStream bufferedInput = new BufferedInputStream(input)) {
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

    public static void tryWithResources2(){//Has a readLine(), read() methods
        Path filePath = Paths.get("path/to/your/file.txt");
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}