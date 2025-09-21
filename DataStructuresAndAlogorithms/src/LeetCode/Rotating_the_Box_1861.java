package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* See 048
 * You are given an m x n matrix of characters boxGrid representing a side-view of a box. Each cell of the box is one of the following:
    A stone '#'
    A stationary obstacle '*'
    Empty '.'
The box is rotated 90 degrees clockwise, causing some of the stones to fall due to gravity. Each stone falls down until
* it lands on an obstacle, another stone, or the bottom of the box. Gravity does not affect the obstacles' positions,
* and the inertia from the box's rotation does not affect the stones' horizontal positions.
It is guaranteed that each stone in boxGrid rests on an obstacle, another stone, or the bottom of the box.
Return an n x m matrix representing the box after the rotation described above.
 *
Input: boxGrid = [["#","#","*",".","*","."],
                  ["#","#","#","*",".","."],
                  ["#","#","#",".","#","."]]

*
* [[#, #, #],
* [#, #, #],
* [*, #, #],
*  [., *, .],
*  [*, ., #],
* [., ., .]]
*
Output: [[".","#","#"],
         [".","#","#"],
         ["#","#","*"],
         ["#","*","."],
         ["#",".","*"],
         ["#",".","."]]
 */
public class Rotating_the_Box_1861 {


    public static void main(String args[]) {
        Rotating_the_Box_1861 box = new Rotating_the_Box_1861();
        char[][] grid2 = new char[3][6];
        grid2[0] = new char[]{'#', '#', '*', '.', '*','.'};
        grid2[1] = new char[]{'#', '#', '#', '*', '.','.'};
        grid2[2] = new char[]{'#', '#', '#', '.', '#','.'};
        System.out.println(Arrays.deepToString(grid2));
        char[][] out2=box.rotateTheBoxAccepted(grid2);
        System.out.println(Arrays.deepToString(out2));

        char[][] grid = new char[3][6];
        grid[0] = new char[]{'#', '#', '*', '.', '*','.'};
        grid[1] = new char[]{'#', '#', '#', '*', '.','.'};
        grid[2] = new char[]{'#', '#', '#', '.', '#','.'};
        System.out.println(Arrays.deepToString(grid));
        char[][] out=box.rotate(grid);
        System.out.println("Rotated Dynamic");
        System.out.println(Arrays.deepToString(out));

    }
    private char[][] rotate(char[][] grid) {
        int row = grid.length;
        int column = grid[0].length;
        char[][] out=new char[column][row];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                out[j][i]=grid[i][j];
            }
        }
        /*swap around middle see 048
        int mid=grid.length/2;
        for (int i = 0; i < row; i++) {
            for (int j = 1; j <= mid; j++) {
                char temp= out[i][mid-j];
                out[i][mid-j]=out[i][j+mid];
                out[i][j+mid]=temp;
           }
        }*/
        /*for (int i = 0; i < out.length; i++) {   // ....step2
            for (int j = 0; j < out[0].length / 2; j++) {
                char temp  = out[i][j];
                out[i][j] = out[i][out[0].length - 1 - j];
                out[i][out.length - 1 - j] = temp;
            }
        }*/
        System.out.println("Rotated Static");
        System.out.println(Arrays.deepToString(out));
        moveZeroesAccepted(out);
        return out;
    }


    public void moveZeroesAccepted(char[][] nums) {
        int rowx= nums.length;
        int colx= nums[0].length;
        for (int i =0;i<colx;i++) {
            int j=rowx-1;
            int k=rowx-1;
            List<int[]> obstList= new ArrayList<>();
            int floor=rowx-1;
            while (k>=0) {
                if (nums[k][i] == '*') {
                    int [] point= new int[]{floor, k};
                    // if [floor][i]
                    obstList.add(point);//rowNum decreasing
                    floor=k-1;
                }
                k--;
            }
            if (obstList.isEmpty()) {
                moveZerosRange(nums, i,j,0);
            } else{
                for (int[] idxList: obstList){
                    moveZerosRange(nums, i,idxList[0],idxList[1]);
                }
            }
        }
    }

    public void moveZerosRange(char[][] nums, int column,int floor, int ceiling){
        int idx=floor;
        while (floor > ceiling) {
            if (nums[floor][column] != '.') {
                nums[idx][column] = nums[floor][column];
                idx--;
            }
            floor--;
        }
        for (int g = ceiling; g < idx; g++) {
            nums[g][column] = '.';
        }
    }
/*
    For each row:
    empty will point to the last cell that is empty. Initially empty = columns - 1.
    Start from last column in the row, for each column c:
    if current cell contains a stone, we will move it from current cell to the empty cell(which is represented by empty variable).
    if current cell contains an obstacle, we will change value of empty to c-1.
    After customising the array box, we will create another array box2 which will be rotated version of box.
 */
    public char[][] rotateTheBoxAccepted(char[][] box) {
        int r = box.length, c = box[0].length;
        char[][] box2 = new char[c][r];

        for(int i = 0; i<r; ++i){
            int empty = c-1;
            for(int j = c-1; j>=0; --j){
                if(box[i][j] == '*'){
                    empty = j-1;
                }
                if(box[i][j] == '#'){
                    box[i][j] = '.';
                    box[i][empty] = '#';
                    --empty;
                }
            }
        }

        for(int i = 0; i<r; ++i){
            for(int j = c-1; j>=0; --j)
                box2[j][r-i-1] = box[i][j];
        }
        return box2;
    }

}