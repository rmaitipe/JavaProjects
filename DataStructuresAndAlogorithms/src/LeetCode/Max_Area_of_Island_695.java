package LeetCode;

import java.util.*;

public class Max_Area_of_Island_695 {
    /* Similar to Flood_Fill_733/200
     * You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected
     * 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
     * The area of an island is the number of cells with a value 1 in the island.
     * Return the maximum area of an island in grid. If there is no island, return 0.
     *
     * Input: grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],
     * [0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
     * Output: 6
     */

    public static void main(String args[]) {
        Max_Area_of_Island_695 ob = new Max_Area_of_Island_695();
        int[][] seaSq = new int[8][12];
        seaSq[0] = new int[]{0,0,1,0,0,0,0,1,0,0,0,0,0};
        seaSq[1] = new int[]{0,0,0,0,0,0,0,1,1,1,0,0,0};
        seaSq[2] = new int[]{0,1,1,0,1,0,0,0,0,0,0,0,0};
        seaSq[3]= new int[]{0,1,0,0,1,1,0,0,1,0,1,0,0};
        seaSq[4]= new int[]{0,1,0,0,1,1,0,0,1,1,1,0,0};
        seaSq[5] = new int[]{0,0,0,0,0,0,0,0,0,0,1,0,0};
        seaSq[6] = new int[]{0,0,0,0,0,0,0,1,1,1,0,0,0};
        seaSq[7]= new int[]{0,0,0,0,0,0,0,1,1,0,0,0,0};
        System.out.println(ob.calcIslands(seaSq));
    }

    private int calcIslands(int[][] grid) {
        int maxArea=0;
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[0].length;j++){
                if (grid[i][j]==1){
                    int[] co=new int[2];
                    co[0]=i;
                    co[1]=j;
                    int localArea=calcArea(grid,co);
                    maxArea =Math.max(localArea,maxArea);
                }
            }
        }
        return maxArea;
    }

    private int calcArea(int[][] grid, int[] co) {
        List <int[]> list= new ArrayList<>();
        list.add(co);
        int count=0;
        while (!list.isEmpty()){
            int[] p= list.remove(0);
            int x=p[0];
            int y=p[1];
            count++;
            grid[x][y]=0;
            if (x+1<grid.length && grid[x+1][y]==1) {
                int[] inner=new int[2];
                inner[0]=x+1;
                inner[1]=y;
                list.add(inner);
            }
            if (x-1>=0 && grid[x-1][y]==1) {
                int[] inner=new int[2];
                inner[0]=x-1;
                inner[1]=y;
                list.add(inner);
            }
            if (y+1<grid[0].length && grid[x][y+1]==1) {
                int[] inner=new int[2];
                inner[0]=x;
                inner[1]=y+1;
                list.add(inner);
            }
            if (y-1>=0 && grid[x][y-1]==1) {
                int[] inner=new int[2];
                inner[0]=x;
                inner[1]=y-1;
                list.add(inner);
            }
        }
        return count;
    }

}
