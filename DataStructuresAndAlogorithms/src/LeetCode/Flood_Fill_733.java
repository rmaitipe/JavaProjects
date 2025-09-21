package LeetCode;

import java.util.*;

public class Flood_Fill_733 {
    /* Similar to Number_of_Islands_200
     * You are given an image represented by an m x n grid of integers image, where image[i][j] represents the pixel
     * value of the image. You are also given three integers sr, sc, and color. Your task is to perform a flood fill on
     * the image starting from the pixel image[sr][sc].
     * To perform a flood fill:
     * Begin with the starting pixel and change its color to color.
     * Perform the same process for each pixel that is directly adjacent (pixels that share a side with the original
     * pixel, either horizontally or vertically) and shares the same color as the starting pixel.
     * Keep repeating this process by checking neighboring pixels of the updated pixels and modifying their color if it
     * matches the original color of the starting pixel.
     * The process stops when there are no more adjacent pixels of the original color to update.
     * Return the modified image after performing the flood fill.
     *
     * Input: image = [[1,1,1],[1,1,0],[1,0,1]], sr = 1, sc = 1, color = 2   Output: [[2,2,2],[2,2,0],[2,0,1]]
     *
     * List addition & removal.
     * BFS approach is Time complexity:O(m*n)  Space complexity:O(m*n)-> can be reduced to O(1) by updating grid
     */

    public static void main(String args[]) {
        Flood_Fill_733 ob = new Flood_Fill_733();
        int[][] seaSq = new int[3][3];
        seaSq[0][0] = 1;seaSq[0][1] = 1;seaSq[0][2] = 1;
        seaSq[1][0] = 1;seaSq[1][1] = 1;seaSq[1][2] = 0;
        seaSq[2][0] = 1;seaSq[2][1] = 0;seaSq[2][2] = 1;
        System.out.println(Arrays.deepToString(ob.numIslandsAccepted(seaSq)));
    }

    class Pair {
        int x;
        int y;
        Pair(int x,int y){
            this.x=x;
            this.y=y;
        }
        public int getX(){
            return x;
        }
        public int getY(){
            return y;
        }
    }

    //Modifies original array to keep track of remaining tiles
    public int[][] numIslandsAccepted(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        if (grid[0][0] == 1) {
            grid[0][0] = 2;
            bfs(grid, rows, cols);
        }
        return grid;
    }

    private void bfs(int[][] grid, int rows, int cols) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0});
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!q.isEmpty()) {
            int[] point = q.poll();
            int row = point[0], col = point[1];
            for (int[] direction : directions) {
                int nr = row + direction[0];
                int nc = col + direction[1];
                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc] == 1) {
                    q.add(new int[]{nr, nc});
                    grid[nr][nc] = 2;
                }
            }
        }
    }

}
