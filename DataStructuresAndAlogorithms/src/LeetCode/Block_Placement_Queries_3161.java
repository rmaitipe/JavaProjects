package LeetCode;

import org.antlr.v4.runtime.tree.Tree;

import java.util.*;

public class Block_Placement_Queries_3161 {
    /*
     * There exists an infinite number line, with its origin at 0 and extending towards the positive x-axis.
     * You are given a 2D array queries, which contains two types of queries:
     * For a query of type 1, queries[i] = [1, x]. Build an obstacle at distance x from the origin.
     * It is guaranteed that there is no obstacle at distance x when the query is asked.
     * For a query of type 2, queries[i] = [2, x, sz]. Check if it is possible to place a block of size sz anywhere
     * in the range [0, x] on the line, such that the block entirely lies in the range [0, x].
     * A block cannot be placed if it intersects with any obstacle, but it may touch it.
     * Note that you do not actually place the block. Queries are separate.
     * Return a boolean array results, where results[i] is true if you can place the block specified in the ith query of type 2, and false otherwise.
     * Input: queries = [[1,2],[2,3,3],[2,3,1],[2,2,2]] Output: [false,true,true]
     */

    public static void main(String args[]) {
        Block_Placement_Queries_3161 ob = new Block_Placement_Queries_3161();
        int[][] nums = new int [4][3];
        nums[0] = new int[]{1,2,0};
        nums[1] = new int[]{2,3,3};
        nums[2] = new int[]{2,3,1};
        nums[3] = new int[]{2,2,2};
        System.out.println(ob.getResultsTLE(nums));
        int[][] nums2 = new int [5][3];
        nums2[0] = new int[]{1,7,0};
        nums2[1] = new int[]{2,7,6};
        nums2[2] = new int[]{1,2,0};
        nums2[3] = new int[]{2,7,5};
        nums2[4] = new int[]{2,7,6};
        System.out.println(ob.getResultsTLE2(nums2));
        System.out.println(ob.getResultsAccepted(nums2));
    }

    public List<Boolean> getResultsTLE(int[][] queries) {
        List<Boolean> retList= new ArrayList<>();
        Set<Integer> permSet= new HashSet<>();
        for (int[] q: queries){
            if (q[0]==1){
                permSet.add(q[1]);
            }
            else{
                int range=q[1];
                int width=q[2];
                int count=0;
                boolean addVal=false;
                for (int i=1;i<=range;i++){
                    count++;
                    if (permSet.contains(i-1)){
                        count=1;
                    }
                    if (count==width){
                        addVal=true;
                        break;
                    }
                }
                retList.add(addVal);
            }
        }
        return retList;
    }

    public List<Boolean> getResultsTLE2(int[][] queries) {
        List<Boolean> retList = new ArrayList<>();
        Set<Integer> permSet = new TreeSet<>();
        for (int[] q : queries) {
            if (q[0] == 1) {
                permSet.add(q[1]);//sortedList
            } else {
                int range = q[1];
                int width = q[2];
                int qPrev = 0;
                boolean addVal = false;
                for (int qCurr : permSet) {
                    if (qCurr < range) {
                        if (qCurr - qPrev >= width) {
                            addVal = true;
                            break;
                        }
                    } else {
                        break;
                    }
                    qPrev = qCurr;
                }
                if (range - qPrev >= width) {
                    addVal = true;
                }
                retList.add(addVal);
            }
        }
        return retList;
    }

    public List<Boolean> getResultsAccepted(int[][] queries) {
        TreeMap<Integer, Integer> obstacles = new TreeMap<>(); // max up until block at key
        List<Boolean> ans = new ArrayList<>();
        for (int[] q : queries) {
            if (q[0] == 1) { // build obstacle
                int index = q[1];
                Integer prev = obstacles.lowerKey(index);
                Integer next = obstacles.higherKey(index);
                if (prev == null) {
                    obstacles.put(index, index);
                } else {
                    obstacles.put(index, Math.max(index - prev, obstacles.get(prev)));
                }
                prev = index;
                while (next != null && obstacles.get(next) > obstacles.get(prev)) {
                    obstacles.put(next, Math.max(next - prev, obstacles.get(prev)));
                    prev = next;
                    next = obstacles.higherKey(next);
                }
            } else { // ans query
                int index = q[1], sz = q[2];
                if (obstacles.containsKey(index)) { // there's an obstacle at queried index
                    ans.add(obstacles.get(index) >= sz);
                } else { // no obstacle
                    Integer prev = obstacles.lowerKey(index);
                    if (prev == null) {
                        ans.add(index >= sz);
                    } else {
                        ans.add(Math.max(index - prev, obstacles.get(prev)) >= sz);
                    }
                }
            }
        }
        return ans;
    }
}
