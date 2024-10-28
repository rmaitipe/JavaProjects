package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Insert_Intervals_057 {
    /*
     * You are given an array of non-overlapping intervals in an array where intervals[i] = [start, end] represent
     * the start and the end of the ith interval and intervals is sorted in ascending order by start.
     * You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
     * Insert newInterval into intervals such that intervals is still sorted in ascending order by start
     * and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).
     * Return intervals after the insertion.
     * Note that you don't need to modify intervals in-place. You can make a new array and return it.
     * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]    Output: [[1,5],[6,9]]
     *
     * See Merge_Intervals_056
     * //result.toArray(new int[result.size()][])
     */

    public static void main(String args[]) {
        Insert_Intervals_057 ob = new Insert_Intervals_057();
        int[][] scores = new int[5][2];
        scores[0] = new int[]{1,2};
        scores[1] = new int[]{3,5};
        scores[2] = new int[]{6,7};
        scores[3] = new int[]{8,10};
        scores[4] = new int[]{12,16};

        int[] key = new int[]{4,8};
        System.out.println(Arrays.deepToString(ob.insertInterval(scores,key)));
        System.out.println(Arrays.deepToString(ob.insertAccepted(scores,key)));
    }

    public int[][] insertInterval(int[][] intervals, int[] key) {
        int left = 0;
        int right = intervals.length - 1;
        List<int[]> retList = new ArrayList<>();
        while (left <= right) {
            //System.out.println(intervals[left][0]+" vs0 "+key[0]);
            //System.out.println(intervals[left][1]+" vs1 "+key[1]);
            if (intervals[left][1] < key[0] || intervals[left][0] > key[1]) {
                retList.add(new int[]{intervals[left][0],intervals[left][1]});//Non-overlap
            } else {//Right overlap or Left overlap
                if (intervals[left][0] > key[0] && intervals[left][1] <= key[1]) {
                    //intervals[left][0] = key[0];// key[0]=Math.min(key[0],intervals[left][0]);
                    key[0]=Math.min(key[0],intervals[left][0]);
                }
                if (intervals[left][0] <= key[0] && intervals[left][1] < key[1]) {
                    //intervals[left][1] = key[1];// key[1]=Math.max(key[1],intervals[left][1]);
                    key[1]=Math.max(key[1],intervals[left][1]);
                }
                //retList.add(new int[]{intervals[left][0],intervals[left][1]});
                retList.add(new int[]{key[0],key[1]});//Only once
            }
            left++;
        }
        return retList.toArray(new int[retList.size()][]);
    }

    public int[][] insertAccepted(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        // Iterate through intervals and add non-overlapping intervals before newInterval
        int i = 0;
        while (i < intervals.length && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }
        // Merge overlapping intervals
        while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        // Add merged newInterval
        result.add(newInterval);//Initialize this
        // Add non-overlapping intervals after newInterval
        while (i < intervals.length) {
            result.add(intervals[i]);
            i++;
        }
        return result.toArray(new int[result.size()][]);
    }
}
