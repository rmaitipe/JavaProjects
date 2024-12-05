package LeetCode;

import java.util.*;

public class K_Closest_Points_to_Origin_973 {
    /*
     * Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k,
     * return the k closest points to the origin (0, 0). The distance between two points on the X-Y plane is the
     * Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).You may return the answer in any order. The answer is
     * guaranteed to be unique (except for the order that it is in).
     * Input: points = [[1,3],[-2,2]], k = 1    Output: [[-2,2]]
     */

    public static void main(String args[]) {
        K_Closest_Points_to_Origin_973 ob = new K_Closest_Points_to_Origin_973();
        int[][] scores = new int[3][2];
        scores[0] = new int[]{3,3};
        scores[1] = new int[]{5,-1};
        scores[2] = new int[]{-2,4};
        System.out.println(Arrays.deepToString(ob.kClosest(scores, 2)));
    }

    public int[][] kClosest(int[][] points, int k) {
        // Min-heap to store the smallest elements from each list
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return (b[0]*b[0] + b[1]*b[1]) - (a[0]*a[0] + a[1]*a[1]);
            }
        });
        //((a,b)-> (b[0]*b[0] + b[1]*b[1]) - (a[0]*a[0] + a[1]*a[1]));
        for (int i=0;i<points.length;i++) {
            if (pq.size() < k) {
                pq.add(points[i]);
            } else{
                if (Math.sqrt(Math.pow((pq.peek()[0]),2)+Math.pow((pq.peek()[1]),2))> Math.sqrt(Math.pow((points[i][0]),2)+Math.pow((points[i][1]),2))){
                    pq.poll();
                    pq.add(points[i]);
                }
            }
        }
        List<int[]> list=new ArrayList<>();
        list.addAll(pq);
        return list.toArray(new int[list.size()][]);
    }

}
