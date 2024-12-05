package LeetCode;

public class Non_Overlapping_Intervals_435 {
    /*
     * Given an array of intervals where intervals[i] = [start, end], return the minimum number of intervals
     * you need to remove to make the rest of the intervals non-overlapping.
     * Note that intervals which only touch at a point are non-overlapping. For example [1,2] & [2,3] are non-overlapping.
     *
     * Input: intervals = [[1,2],[2,3],[3,4],[1,3]]     Output: 1
     * Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
     * See Merge_Intervals_056
     */

    public static void main(String args[]) {
        Non_Overlapping_Intervals_435 ob = new Non_Overlapping_Intervals_435();
        int[][] scores = new int[4][2];
        scores[0] = new int[]{1,2};
        scores[1] = new int[]{2,3};
        scores[2] = new int[]{3,4};
        scores[3] = new int[]{1,3};
        System.out.println(ob.countInterval(scores));
    }

    public int countInterval(int[][] intervals) {
        int count=0;
        int left=0;
        int right=1;
        while (right<=intervals.length-1) {
            int[] currIdx = intervals[left];
            if (currIdx[1] < intervals[right][0]) {//overlap
                right++;
                count++;
            }
            else if (currIdx[1] > intervals[right][1]) {
                currIdx = intervals[right];//pick smaller
                left = right;
                right = left + 1;
                count++;
            }
            else if (currIdx[1] <= intervals[right][0]) {//no overlap
                left++;
                right = left + 1;
            }
        }
        return count;
    }

}
