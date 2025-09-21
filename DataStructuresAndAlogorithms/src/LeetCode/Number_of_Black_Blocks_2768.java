package LeetCode;

import java.util.Arrays;
import java.util.HashMap;

public class Number_of_Black_Blocks_2768 {
    /*
     * You are given two integers m and n representing the dimensions of a 0-indexed m x n grid.
*You are also given a 0-indexed 2D integer matrix coordinates, where coordinates[i] = [x, y] indicates that the cell with
* coordinates [x, y] is colored black. All cells in the grid that do not appear in coordinates are white.
* A block is defined as a 2 x 2 submatrix of the grid. More formally, a block with cell [x, y] as its top-left corner
* where 0 <= x < m - 1 and 0 <= y < n - 1 contains the coordinates [x, y], [x + 1, y], [x, y + 1], and [x + 1, y + 1].
* Return a 0-indexed integer array arr of size 5 such that arr[i] is the number of blocks that contains exactly i black cells.
     */

    public static void main(String args[]) {
        Number_of_Black_Blocks_2768 ob = new Number_of_Black_Blocks_2768();
        int [][] nums = new int[][]{{0,0}};//new int[][]{{0,0},{4,1},{3,5},{5,3}};
        System.out.println(Arrays.toString(ob.countBlackBlocks(3, 3, nums)));
    }

  //O(mxn)
    public long[] countBlackBlocks(int m, int n, int[][] coordinates) {
        //grid is 2x2
        int [][] grid= new int [m][n];
        for (int [] coor: coordinates){
            grid[coor[0]][coor[1]]=1;
        }
        long [] arr=new long [5];
        //phase 2
        int postInit=grid[0][0] + grid[0][1];
        int count=0;
        for (int i=0;i<m -1;i++){
            for (int j=0; j<n -1;j++){
                //int a= grid[i][j] + grid[i][j+1];
                int b= grid[i+1][j] +grid[i+1][j+1];
                count=postInit+b;
                arr[count]++;
                postInit=b;
            }
        }
        return arr;
    }

    /*
Instead of checking every 2×2 block to see how many black cells it contains, we can flip the perspective:
For each black cell, update all the 2×2 blocks it could be part of.
     */

    public long[] countBlackBlocksAccepted(int m, int n, int[][] coordinates) {
        HashMap<String, Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        for (int[] xy : coordinates) {
            int x = xy[0];
            int y = xy[1];
            if (x + 1 < m && y + 1 < n) addToMap(sb, x, y, map);       // Bottom-right
            if (x + 1 < m && y - 1 >= 0) addToMap(sb, x, y - 1, map);  // Bottom-left
            if (x - 1 >= 0 && y + 1 < n) addToMap(sb, x - 1, y, map);  // Top-right
            if (x - 1 >= 0 && y - 1 >= 0) addToMap(sb, x - 1, y - 1, map); // Top-left
        }
        long[] res = new long[5];
        for (int count : map.values()) {
            res[count]++;
        }
        res[0] = (long)(m - 1) * (n - 1) - map.size(); // Count of blocks with 0 black cells
        return res;
    }

    public void addToMap(StringBuilder sb, int x, int y, HashMap<String, Integer> map) {
        sb.setLength(0);
        sb.append(x).append(',').append(y);
        String key = sb.toString();
        map.put(key, map.getOrDefault(key, 0) + 1);
    }
}
