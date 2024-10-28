package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Merge_Intervals_056 {
    /*
     * Given an array of intervals where intervals[i] = [start, end], merge all overlapping intervals, and return
     * an array of the non-overlapping intervals that cover all the intervals in the input.
     * Input: intervals = [[1,3],[2,6],[8,10],[15,18]] Output: [[1,6],[8,10],[15,18]]
     *
     * See Insert_Intervals_057
     * //result.toArray(new int[result.size()][])
     */
    public static void main(String args[]) {
        Merge_Intervals_056 ob = new Merge_Intervals_056();
        int[][] scores = new int[4][2];
        scores[0] = new int[]{1,3};
        scores[1] = new int[]{2,6};
        scores[2] = new int[]{8,10};
        scores[3] = new int[]{15,18};
        System.out.println(Arrays.deepToString(ob.merge(scores)));
        System.out.println(Arrays.deepToString(ob.mergeAccepted(scores)));
    }

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));//***
        int lIdx=0; int rIdx=1;
        List<int[]> mergedList =new ArrayList<>();
        while (rIdx<intervals.length) {
            if (intervals[lIdx][1] > intervals[rIdx][0]) {
                int[] kv = new int[]{intervals[lIdx][0], intervals[rIdx][1]};
                rIdx++;
                while (rIdx < intervals.length && kv[1] > intervals[rIdx][0]) {
                    kv = new int[]{intervals[lIdx][0], intervals[rIdx][1]};
                    rIdx++;
                }
                mergedList.add(kv);
                lIdx = rIdx;
                rIdx = rIdx + 1;
            } else {
                mergedList.add(intervals[lIdx]);
                lIdx++;
                rIdx++;
            }
        }
        mergedList.add(Arrays.copyOfRange(intervals,lIdx,rIdx)[0]);
        return mergedList.toArray(new int[mergedList.size()][]);//***
    }

    public int[][] mergeAccepted(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> mergedList = new ArrayList<>();
        int[] prev = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            int[] interval = intervals[i];
            if (interval[0] <= prev[1]) {
                prev[1] = Math.max(prev[1], interval[1]);
            } else {
                mergedList.add(prev);
                prev = interval;
            }
        }
        mergedList.add(prev);
        return mergedList.toArray(new int[mergedList.size()][]);
    }
}
