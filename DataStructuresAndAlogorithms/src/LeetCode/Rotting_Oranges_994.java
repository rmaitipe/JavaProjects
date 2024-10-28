package LeetCode;

import java.util.*;

public class Rotting_Oranges_994 {
    /*
     * You are given an m x n grid where each cell can have one of three values:
     * 0 representing an empty cell, 1 representing a fresh orange, or 2 representing a rotten orange.
     * Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
     * Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.
     */
    public static void main(String args[]) {
        Rotting_Oranges_994 ob = new Rotting_Oranges_994();
        int[][] oranges = new int[3][3];
        oranges[0][0] = 2;
        oranges[0][1] = 1;
        oranges[0][2] = 1;
        oranges[1][0] = 1;
        oranges[1][1] = 1;
        oranges[1][2] = 0;
        oranges[2][0] = 0;
        oranges[2][1] = 1;
        oranges[2][2] = 1;
        System.out.println(ob.calcMinTime(oranges));
    }

    private int calcMinTime(int[][] grid) {
        List<Pair> list=new ArrayList<>();
        //Set<Pair> set=new HashSet<>();
        int[][] visited= new int[grid.length][grid[0].length];
        int count=0;
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[0].length;j++){
                if (grid[i][j]==2){
                    Pair p =new Pair(i,j);
                    list.add(p);
                    visited[i][j]=1;
                    //set.add(p);
                }
            }
        }
        while (!list.isEmpty()){
            List<Pair> listIn=new ArrayList<>();
            for (Pair p : list) {
                List<Pair> nextList = calcList(p, grid.length, grid[0].length);
                //4d set.add if true i.e new entries add to list
                for (Pair n : nextList) {
                    //boolean isAdd= set.add(n);
                    if (visited[n.x][n.y] != 1 && grid[n.x][n.y]==1) {
                        listIn.add(n);
                        visited[n.x][n.y] = 1;
                    } else {
                        System.out.println("Already included");
                    }
                }
            }
            list=listIn;
            count++;
        }
        return count-1;
    }

    private List<Pair> calcList(Pair p, int row,int col){
        List<Pair> nextList=new ArrayList<>();
        if (p.getX()+1<row) { nextList.add(new Pair(p.getX()+1,p.getY()));}
        if (p.getX()-1>=0) { nextList.add(new Pair(p.getX()-1,p.getY()));}
        if (p.getY()+1<col) { nextList.add(new Pair(p.getX(),p.getY()+1));}
        if (p.getY()-1>=0) { nextList.add(new Pair(p.getX(),p.getY()-1));}
        return nextList;
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return x == pair.x && y == pair.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
        /*
        @Override
        public int compareTo(Pair other) {
            int c = this.getX().compareTo(other.x);
            if (c != 0) return c;
            return this.getY().compareTo(other.y);
        }
        */
    }

    /*
     * The problem requires can be modeled as a breadth-first search (BFS) problem. The BFS is appropriate here because
     * it allows us to explore the grid level by level (minute by minute)
     */
    public int orangesRottingAccepted(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Queue<int[]> qRotten = new LinkedList<>();
        int countFreshOrange = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    qRotten.offer(new int[] {i, j});
                }
                if (grid[i][j] == 1) {
                    countFreshOrange++;
                }
            }
        }
        if (countFreshOrange == 0)
            return 0;
        if (qRotten.isEmpty())
            return -1;

        int minutes = -1;
        int[][] dirs = {{1, 0},{-1, 0},{0, -1},{0, 1}};
        while (!qRotten.isEmpty()) {
            int size = qRotten.size();
            while (size-- > 0) {
                int[] cell = qRotten.poll();
                int x = cell[0];
                int y = cell[1];
                for (int[] dir : dirs) {
                    int i = x + dir[0];
                    int j = y + dir[1];
                    if (i >= 0 && i < m && j >= 0 && j < n && grid[i][j] == 1) {
                        grid[i][j] = 2;
                        countFreshOrange--;
                        qRotten.offer(new int[] {i, j});
                    }
                }
            }
            minutes++;
        }
        if (countFreshOrange == 0)
            return minutes;
        return -1;
    }

}
