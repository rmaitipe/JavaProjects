package operations;

import java.util.Arrays;
import java.util.List;

public class ArraysOperations {
/*
 * (ACES) asList/copyOf/equals/sort/fill/stream
 * Essential questions: Two Sum, Best Time to Buy and Sell Stock, Product of Array Except Self, Maximum Subarray
 */
    public static void main(String [] args){
        int[] intArr = {5,6,7,8,1,2,3,4,9};
        String[] strArr= {"dog", "over", "good"};
        List<Integer> intList= Arrays.asList(5,6,7,8,1,2,3,4,9);//initialize a List Directly
        List<String> strList= Arrays.asList("dog", "over", "good");//initialize a List Directly
        List<String> list= Arrays.asList(strArr);//convert to a List
        List<Integer> list2= Arrays.stream(intArr).boxed().toList();//primitives to object

        int[] strArr2 = Arrays.copyOf(intArr,intArr.length);
        int[] strArr3 = Arrays.copyOfRange(intArr,0,intArr.length);

        System.out.println("Equals check: " +Arrays.equals(strArr2, strArr3));
        Arrays.sort(intArr);
        //Arrays.sort(someArr, (a, b) -> a.length() - b.length());
        Arrays.stream(intArr).forEach(x->System.out.print(x));
        System.out.println("Fill Function");
        Arrays.fill(intArr,1);
        Arrays.stream(intArr).forEach(x->System.out.print(x));
    }
}

