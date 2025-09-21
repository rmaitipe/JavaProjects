package LeetCode;

import java.util.*;

/*
 * This question is about implementing a basic elimination algorithm for Candy Crush.
 * Given a 2D integer array board representing the grid of candy, different positive integers board[i][j] represent different
 * types of candies. A value of board[i][j] = 0 represents that the cell at position (i, j) is empty. The given board
 * represents the state of the game following the player's move. Now, you need to restore the board to a stable state by
 * crushing candies according to the following rules:
 * If three or more candies of the same type are adjacent vertically or horizontally, "crush" them all at the same time - these positions become empty.
 * After crushing all candies simultaneously, if an empty space on the board has candies on top of itself, then these
 * candies will drop until they hit a candy or bottom at the same time. (No new candies will drop outside the top boundary.)
 * After the above steps, there may exist more candies that can be crushed. If so, you need to repeat the above steps.
 * If there does not exist more candies that can be crushed (ie. the board is stable), then return the current board.
 * You need to perform the above rules until the board becomes stable, then return the current board.
 *
 * Input:board =[[110,5,112,113,114],[210,211,5,213,214],[310,311,3,313,314],[410,411,412,5,414],[5,1,512,3,3],[610,4,1,613,614],[710,1,2,713,714],[810,1,2,1,1],[1,1,2,2,2],[4,1,4,4,1014]]
 * Output:[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[110,0,0,0,114],[210,0,0,0,214],[310,0,0,113,314],[410,0,0,213,414],[610,211,112,313,614],[710,311,412,613,714],[810,411,512,713,1014]]
 */
public class Candy_Crush_723 {


    public static void main(String args[]) {
        Candy_Crush_723 candy = new Candy_Crush_723();
        int[][] grid = new int[10][5];
        grid[0] = new int[]{110, 5, 112, 113, 114};
        grid[1] = new int[]{210, 211, 5, 213, 214};
        grid[2] = new int[]{310, 311, 3, 313, 314};
        grid[3] = new int[]{410, 411, 412, 5, 414};
        grid[4] = new int[]{5, 1, 512, 3,3};
        grid[5] = new int[]{610, 4, 1, 613, 614};
        grid[6] = new int[]{710, 1, 2, 713, 714};
        grid[7] = new int[]{810, 1, 2, 1, 1};
        grid[8] = new int[]{1, 1, 2, 2, 2};
        grid[9] = new int[]{4, 1, 4, 4, 1014};
        candy.crush(grid);
        System.out.println(Arrays.deepToString(grid));
        int[][] grid2 = new int[10][5];
        grid2[0] = new int[]{110, 5, 112, 113, 114};
        grid2[1] = new int[]{210, 211, 5, 213, 214};
        grid2[2] = new int[]{310, 311, 3, 313, 314};
        grid2[3] = new int[]{410, 411, 412, 5, 414};
        grid2[4] = new int[]{5, 1, 512, 3,3};
        grid2[5] = new int[]{610, 4, 1, 613, 614};
        grid2[6] = new int[]{710, 1, 2, 713, 714};
        grid2[7] = new int[]{810, 1, 2, 1, 1};
        grid2[8] = new int[]{1, 1, 2, 2, 2};
        grid2[9] = new int[]{4, 1, 4, 4, 1014};
        candy.candyCrushAccepted(grid2);
        System.out.println(Arrays.deepToString(grid2));
    }

    private void crush(int[][] grid) {
        int row = grid.length;
        int column = grid[0].length;
        List<List<int[]>> list =new ArrayList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                detectLogic(new int[]{i, j}, grid,row,column,list);
            }
        }
        while (!list.isEmpty()) {
            //resolveList adjust grid
            for (List<int[]> entryList: list) {
                for (int[] entry: entryList) {
                    grid[entry[0]][entry[1]]=0;
                }
            }
            //move zeros algo 268
            moveZeroesAccepted(grid,row,column);
            // addtoList
            List<List<int[]>> innerList =new ArrayList<>();
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    detectLogic(new int[]{i, j},grid,row,column,innerList);
                }
            }
            list=innerList;
        }
    }

    private void detectLogic(int[] inst, int[][] grid,int row,int column,List<List<int[]>>  retList) {
        //4 directions
        int x=inst[0];
        int y= inst[1];
        int ixL=1;
        int ixR=1;
        int countRowR=0;
        int countRowL=0;
        int countColU=0;
        int countColD=0;
        if (grid[x][y]!=0) {
            while (x + ixL < row) {
                if (grid[x + ixL][y] == grid[x][y]) {
                    countColU++;
                    ixL++;
                } else {
                    break;
                }
            }
            while (x - ixR > 0) {
                if (grid[x - ixR][y] == grid[x][y]) {
                    countColD++;
                    ixR++;
                } else {
                    break;
                }
            }
            int iyU = 1;
            int iyD = 1;
            List<int[]> rowList = new ArrayList<>();
            if (countColU + countColD + 1 >= 3) {
                for (int j = x - countColD; j <= x + countColU; j++) {
                    rowList.add(new int[]{j, y});
                }
            }
            // update List
            while (y + iyU < column) {
                if (grid[x][y + iyU] == grid[x][y]) {
                    countRowR++;
                    iyU++;
                } else {
                    break;
                }
            }
            while (y - iyD > 0) {
                if (grid[x][y - iyD] == grid[x][y]) {
                    countRowL++;
                    iyD++;
                } else {
                    break;
                }
            }
            List<int[]> colList = new ArrayList<>();
            if (countRowR + countRowL + 1 >= 3) {
                for (int j = y - countRowL; j <= y + countRowR; j++) {
                    colList.add(new int[]{x, j});
                }
            }
            if (!rowList.isEmpty()) {
                retList.add(rowList);
            }
            if (!colList.isEmpty()) {
                retList.add(colList);
            }
        }
    }

    public void moveZeroesAccepted(int[][] nums, int row, int column) {
        for (int i =0;i<column;i++) {
            int idx=row-1;
            int j=row-1;
            while (j>=0) {
                if (nums[j][i] != 0) {
                    nums[idx][i] = nums[j][i];
                    idx--;
                    j--;
                } else{ j--;}
            }
            for (int k=0;k<=idx;k++){
                nums[k][i] = 0;
            }
        }
    }

    public int[][] candyCrushAccepted(int[][] board) {
        int r = board.length, c = board[0].length;
        boolean crush = false;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                // check horizontal line
                int v = Math.abs(board[i][j]);
                if (v == 0) continue;
                if (j + 2 < c && v == Math.abs(board[i][j + 1]) && v == Math.abs(board[i][j + 2])) {
                    crush = true;
                    board[i][j] = board[i][j + 1] = board[i][j + 2] = -v;
                }
                // check vertical line
                if (i + 2 < r && v == Math.abs(board[i + 1][j]) && v == Math.abs(board[i + 2][j])) {
                    crush = true;
                    board[i][j] = board[i + 1][j] = board[i + 2][j] = -v;
                }
            }
        }
        // crush candy
        for (int j = 0; j < c; j++) {
            int id = r - 1;
            for (int i = r - 1; i >= 0; i--) {
                if (board[i][j] > 0) {
                    board[id--][j] = board[i][j];
                }
            }
            while (id >= 0) board[id--][j] = 0;
        }
        return crush ? candyCrushAccepted(board) : board;
    }
}