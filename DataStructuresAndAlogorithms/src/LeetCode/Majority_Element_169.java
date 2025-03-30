package LeetCode;

import java.util.Arrays;
import java.util.HashMap;

public class Majority_Element_169 {
    /*
     * Given an array nums of size n, return the majority element. The majority element is the element that appears
     * more than [n / 2] times. You may assume that the majority element always exists in the array.
     *
     * Follow-up: Could you solve the problem in linear time and in O(1) space?
     */
    public static void main(String args[]) {
        Majority_Element_169 ob = new Majority_Element_169();
        int [] arr={2,2,1,1,1,2,2};
        Integer major = ob.searchMethod(arr);
        System.out.println(major);
        Integer linear = ob.searchLinearMethod(arr);
        System.out.println(linear);
    }

    private Integer searchMethod(int[] arr) {
        Arrays.sort(arr);//n(log(n)
        return arr[arr.length/2];
    }

    private Integer searchLinearMethod(int[] arr) { //O(n) space complexity
        HashMap<Integer,Integer> map =new HashMap<>();
        int maxOccur=arr.length/2;
        int retVal = 0;
        for (Integer i:arr){
            int v= map.getOrDefault(i,0)+1;
            map.put(i,v);
            if (v>maxOccur){
                retVal=i;
                break;
            }
        }
        return retVal;
    }


    //Boyer-Moore Voting Algorithm
    public int majorityElement(int[] nums) {
        int count=0, candidate=0;
        for(int arr:nums){
            if(count==0){
                candidate=arr;
            }
            count+=(arr==candidate)?1:-1;
        }
        return candidate;
    }

}
