package LeetCode;

import java.util.*;

public class Sliding_Window_Maximum_239 {
    /* You are given an array of integers nums, there is a sliding window of size k which is moving from the
     * very left of the array to the very right. You can only see the k numbers in the window.
     * Each time the sliding window moves right by one position. Return the max of sliding window.
     * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3     Output: [3,3,5,5,6,7]
     * Explain [1,3,-1]=3 [3,-1,-3]=3 [-1,-3,5]=5 etc...
     *
     * https://stackoverflow.com/questions/55694015/java-8-stream-max-function-argument-type-comparator-vs-comparable
     */
    public int[] findMaxWindow(int[] nums,int k) {
        int[] dpSum = new int [nums.length-k+1];
        List<Integer> list=new LinkedList<>();
        for (int i=0;i<k;i++){
            list.add(nums[i]);
        }
        dpSum[0]= list.stream().max(Integer::compareTo).get();//***//
        for (int i=0;i<nums.length-k;i++){
            list.removeFirst();
            list.add(nums[i+k]);
            dpSum[i+1]=list.stream().max(Integer::compareTo).get();
        }
        return dpSum;
    }

    public static void main(String args[])    {
        int[] prices = {1,3,-1,-3,5,3,6,7};
        Sliding_Window_Maximum_239 ob = new Sliding_Window_Maximum_239();
        int[] out=ob.findMaxWindow(prices,3);
        System.out.println(Arrays.toString(out));
        int[] out2=ob.maxSlidingWindowAccepted(prices,3);
        System.out.println(Arrays.toString(out2));
    }

    /*
     * TreeMap put, get
     */
    public int[] maxSlidingWindowAccepted(int[] nums, int k) {
        //Use TreeMap to get the largest element from sorted map.
        TreeMap<Integer, Integer> map = new TreeMap<>(Collections.reverseOrder());
        //first put k values in TreeMap
        for (int i = 0; i < k; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        // To return, create an ArrayList to store the largest element in window
        ArrayList<Integer> list = new ArrayList<>();
        //get the first value which is largest in window by iterator and next method and add it to the list
        Integer val = map.keySet().iterator().next();
        list.add(val);
        // similarly for all window do the same. That is remove the last element and add new element in TreeMap and add largest element to the list
        for (int i = k; i < nums.length; i++) {
            int temp = nums[i - k];
            //subtract or remove
            map.put(temp, map.get(temp) - 1);
            if (map.get(temp) <= 0) {
                map.remove(temp);
            }
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            val = map.keySet().iterator().next();//map.firstEntry().getKey();
            list.add(val);
        }
        // Convert list to int array and return it
        Integer[] ret= list.toArray(new Integer[0]);
        int[] ans = list.stream().mapToInt(i -> i).toArray();
        return ans;
    }

    public int[] maxSlidingWindowPQ(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < k; i++) {
            pq.add(nums[i]);
        }
        // To return, create an ArrayList to store the largest element in window
        ArrayList<Integer> list = new ArrayList<>();
        Integer val = pq.peek();
        list.add(val);
        for (int i = k; i < nums.length; i++) {
            int temp = nums[i - k];
            //subtract or remove
            pq.remove(nums[i-k]);
            pq.add(nums[i]);
            val = pq.peek();
            list.add(val);
        }
        // Convert list to int array and return it
        Integer[] ret= list.toArray(new Integer[0]);
        int[] ans = list.stream().mapToInt(i -> i).toArray();
        return ans;
    }
}
