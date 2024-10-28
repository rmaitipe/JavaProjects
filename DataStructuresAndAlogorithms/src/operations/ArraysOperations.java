package operations;

import java.util.Arrays;
import java.util.List;

public class ArraysOperations {
    //asList/copyOf/equals/sort (ACES)
    public static void main(String [] args){
        int[] strArr = {5,6,7,8,1,2,3,4,9};
        List<String> list= Arrays.asList("dog", "over", "good");//initialize a List
        List<Integer> list2= Arrays.stream(strArr).boxed().toList();//primitives to object
        int[] strArr2 = Arrays.copyOf(strArr,strArr.length);
        int[] strArr3 = Arrays.copyOfRange(strArr,0,strArr.length);
        Arrays.equals(strArr2, strArr3);
        Arrays.sort(strArr);
        //Arrays.sort(someArr, (a, b) -> a.length() - b.length());
        Arrays.stream(strArr).forEach(x->System.out.print(x));
        Arrays.fill(strArr,1);
        Arrays.stream(strArr).forEach(x->System.out.print(x));
    }
}

