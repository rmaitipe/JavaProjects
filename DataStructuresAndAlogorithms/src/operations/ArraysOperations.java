package operations;

import java.util.Arrays;
import java.util.List;

public class ArraysOperations {
    //asList/copyOf/equals/sort (ACES)
    public static void main(String [] args){
        int[] strArr = {1,2,3,4,5,6,7,8,9};
        Arrays.sort(strArr);
        int[] strArr2 = Arrays.copyOf(strArr,strArr.length);
        Arrays.equals(strArr, strArr2);
        List<String> list= Arrays.asList("dog", "over", "good");//initialize a List
        list.stream().forEach(x->System.out.print(x));
    }
}

