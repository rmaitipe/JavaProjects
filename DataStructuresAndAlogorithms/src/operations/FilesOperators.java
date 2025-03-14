package operations;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FilesOperators {

    final static String FILE_PATH ="res/nashorn1.js";

    public static void main(String arg[]){
        try{
            List<String> list= Files.readAllLines(Paths.get(FILE_PATH));//read operation
            if (list!=null){
                list.stream().forEach(x->System.out.print(x));}
            Files.walk(Paths.get(FILE_PATH)).sorted();//Java 8  Stream containing list all file names in current directory and sub-directories
            Files.list(Paths.get(FILE_PATH));// Stream containing list all file names and sub-directories in current directory
            //usecase
            List<String> result = Files.walk(Paths.get(FILE_PATH)).filter(Files::isRegularFile)
                    .map(x -> x.toString()).collect(Collectors.toList());
            result.forEach(System.out::println);
            //Files.newDirectoryStream(Paths.get("res/nashorn1.js"));//Similar to Files.list

            //pre java8 algorithm focus
            readfileDir(new File(FILE_PATH));
        }catch (Exception e){}
    }

    public static void readfileDir(File dir){
        for (File f :dir.listFiles()){
            if (f.isDirectory()){
                readfileDir(f);//recurse
            } else{
                System.out.println(f.getName());
            }
        }
    }

}
