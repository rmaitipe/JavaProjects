package LeetCode;

import java.util.Collections;
import java.util.PriorityQueue;

public class Find_Median_From_Data_Stream_295 {
    /*
     * The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value,
     * and the median is the mean of the two middle values.
     * For example, for arr = [2,3,4], the median is 3.
     * For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
     * Implement the MedianFinder class:
     * MedianFinder() initializes the MedianFinder object.
     * oid addNum(int num) adds the integer num from the data stream to the data structure.
     * double findMedian() returns the median of all elements so far. Answers within 10^-5 of the actual answer will be accepted.
     */
    PriorityQueue<Integer> minHeap;
    PriorityQueue<Integer> maxHeap;
    int idx;

    Find_Median_From_Data_Stream_295(){
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        idx=0;
    }

    private void addNum(int arr) {
        idx++;
        // If maxHeap is empty or num is less than or equal to the top of maxHeap, add to maxHeap
        if (maxHeap.isEmpty() || arr <= maxHeap.peek()) {
            maxHeap.add(arr);
        } else {
            minHeap.add(arr);
        }
        // Balance the heaps if their sizes differ by more than one
        if (minHeap.size() - maxHeap.size() == 2) {
            maxHeap.add(minHeap.remove());
        }
        if (maxHeap.size() - minHeap.size() == 2) {
            minHeap.add(maxHeap.remove());
        }
    }

    private Double findMedian() {
       if (idx%2==0){
           Double a= Double.valueOf(maxHeap.peek());
           Double b = Double.valueOf(minHeap.peek());
           return (b-a)/2+a;
       } else{
           if (maxHeap.size()>minHeap.size()) {
               return Double.valueOf(maxHeap.peek());
           }else {
               return Double.valueOf(minHeap.peek());
           }
       }
    }

    public static void main(String args[]) {
        Find_Median_From_Data_Stream_295 ob = new Find_Median_From_Data_Stream_295();
        ob.addNum(1);ob.addNum(2); ob.addNum(5);
        Double out = ob.findMedian();
        System.out.println(out);
        ob.addNum(8);
        ob.addNum(6);
        ob.addNum(3);
        out = ob.findMedian();
        System.out.println(out);
    }

}
