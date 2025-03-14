package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Pascals_Triangle_118 {
    /*
     * Given an integer numRows, return the first numRows of Pascal's triangle.
     * In Pascal's triangle, each number is the sum of the two numbers directly above.
     * Input: numRows = 5  Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
     */

    public static void main(String args[]) {
        Pascals_Triangle_118 ob = new Pascals_Triangle_118();
        List<List<Integer>> list = ob.searchPascal(5);
        System.out.println(list);
    }

    private List<List<Integer>> searchPascal(int k) {
        List<List<Integer>> list=new ArrayList<>();
        for (int i=1;i<=k;i++){
            if (i==1){
                List<Integer> list1= List.of(1);
                list.add(list1);
                if (k==1) {
                    break;
                }
            }
            else if (i==2) {
                List<Integer> list2= Arrays.asList(1,1);
                list.add(list2);
                if (k==2) {
                    break;
                }
            }else {
                int left=0;
                List<Integer> list3= new ArrayList<>();
                list3.add(1);
                List<Integer> prior= list.get(i-2);//i-1 elems
                while(left<i-2){
                    list3.add(prior.get(left)+prior.get(left+1));
                    left++;
                }
                list3.add(1);
                list.add(list3);
            }
        }
        return list;
    }

}
