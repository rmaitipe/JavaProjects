package LeetCode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Number_of_Islands_Copy_200 {
    /*
     * # for blocked and . for open. find shortest path
     */
    static int minSteps=Integer.MAX_VALUE;

    public static void main(String args[]) {
        Number_of_Islands_Copy_200 ob = new Number_of_Islands_Copy_200();
        char[][] seaSq = new char[4][5];
        seaSq[0][0] = '.';seaSq[0][1] = '#';seaSq[0][2] = '.';seaSq[0][3] = '#';seaSq[0][4] = '#';
        seaSq[1][0] = '.';seaSq[1][1] = '.';seaSq[1][2] = '.';seaSq[1][3] = '.';seaSq[1][4] = '.';
        seaSq[2][0] = '#';seaSq[2][1] = '.';seaSq[2][2] = '.';seaSq[2][3] = '#';seaSq[2][4] = '.';
        seaSq[3][0] = '#';seaSq[3][1] = '.';seaSq[3][2] = '.';seaSq[3][3] = '.';seaSq[3][4] = '.';
        Pair p=new Pair(0,0);
        boolean[][] visited= new boolean[4][5];
        List<List<String>> retList=new ArrayList<>();
        ob.calcIslands(retList,new ArrayList<>(), seaSq,visited,p);
        System.out.println(retList.getFirst());
        ob.backtrack(seaSq,visited,0,0,1);
        System.out.println("minSteps: "+minSteps);

    }

    private void backtrack(char[][] board, boolean[][] visited, int i, int j, int index) {
        if (i==board.length-1 && j==board[0].length-1){
            minSteps=Math.min(index,minSteps);
        }
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || visited[i][j] || board[i][j] == '#') {
        //end
        }else {
            visited[i][j] = true;
            // Recursively explore all four directions (up, down, left, right) return true for any callback returning true
            backtrack(board, visited, i + 1, j, index + 1);
            backtrack(board, visited, i - 1, j, index + 1);
            backtrack(board, visited, i, j + 1, index + 1);
            backtrack(board, visited, i, j - 1, index + 1);
            visited[i][j] = false;
        }

    }

    private void calcIslands(List<List<String>> retList, List<String> list,char[][] grid,boolean[][] visited,Pair current) {
        if (current.getX()==3 && current.getY()==4){
            retList.add(list);
        }
        else{
            list.add(current.getX()+","+current.getY());
            List<Pair> nextList= calcList(current,grid,visited);
            for (Pair p:nextList){
                visited[p.x][p.y]=true;
                calcIslands(retList,list, grid,visited,p);
                visited[p.x][p.y]=false;
            }
        }
    }

    private List<Pair> calcList(Pair p, char [][] grid, boolean[][] visited){
        List<Pair> nextList=new ArrayList<>();
        if (p.getX()+1<grid.length && grid[p.x+1][p.y]=='.' && !visited[p.x + 1][p.y]) {
            nextList.add(new Pair(p.getX()+1,p.getY()));
        }
        //if (p.getX()-1>=0 && grid[p.x-1][p.y]=='.' && !visited[p.x - 1][p.y]) {
        //    nextList.add(new Pair(p.getX()-1,p.getY()));
        //}
        if (p.getY()+1<grid[0].length && grid[p.x][p.y+1]=='.' && !visited[p.x][p.y + 1]) {
            nextList.add(new Pair(p.getX(),p.getY()+1));
        }
        //if (p.getY()-1>=0 && grid[p.x][p.y-1]=='.' && !visited[p.x][p.y - 1]) {
        //    nextList.add(new Pair(p.getX(),p.getY()-1));
        //}
        return nextList;
    }

}

class Pair implements Comparable<Number_of_Islands_200.Pair> {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Number_of_Islands_200.Pair pair = (Number_of_Islands_200.Pair) o;
        return x == pair.x && y == pair.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public int compareTo(Number_of_Islands_200.Pair o) {
        return 0;
    }
}