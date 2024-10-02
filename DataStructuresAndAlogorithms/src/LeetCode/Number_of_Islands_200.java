package LeetCode;

import java.util.*;

public class Number_of_Islands_200 {
    /*
     * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
     * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
     * You may assume all four edges of the grid are all surrounded by water.
     *
     * List addition & removal.
     * comparable Interface for a Pair class?
     */

    public static void main(String args[]) {
        Number_of_Islands_200 ob = new Number_of_Islands_200();
        int[][] seaSq = new int[4][5];
        seaSq[0][0] = 1;seaSq[0][1] = 1;seaSq[0][2] = 0;seaSq[0][3] = 0;seaSq[0][4] = 0;
        seaSq[1][0] = 1;seaSq[1][1] = 1;seaSq[1][2] = 0;seaSq[1][3] = 0;seaSq[1][4] = 0;        
        seaSq[2][0] = 0;seaSq[2][1] = 0;seaSq[2][2] = 1;seaSq[2][3] = 0;seaSq[2][4] = 0;
        seaSq[3][0] = 0;seaSq[3][1] = 0;seaSq[3][2] = 0;seaSq[3][3] = 1;seaSq[3][4] = 1;
        System.out.println(ob.calcIslands(seaSq));
    }

    private int calcIslands(int[][] grid) {
        List<Pair> totalRemainingList= new ArrayList<>();
        int[][] visited=new int [grid.length][grid[0].length];
        int count=0;
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[0].length;j++){
                if (grid[i][j]==1){
                    Pair p =new Pair(i,j);
                    totalRemainingList.add(p);
                }
            }
        }
        while (!totalRemainingList.isEmpty()){
            Pair p= totalRemainingList.remove(0);
            List<Pair> listIsle=new ArrayList<>();
            listIsle.add(p);
            PriorityQueue<Pair> pq=new PriorityQueue<>();
            pq.add(p);
            visited[p.x][p.y]=1;
            while (!pq.isEmpty()){
                Pair pqElem=pq.poll();
                List<Pair> nextList = calcList(pqElem, grid,visited);
                pq.addAll(nextList);
                listIsle.addAll(nextList);
            }
            totalRemainingList.removeAll(listIsle);
            count++;
        }
        return count-1;
    }

    private List<Pair> calcList(Pair p, int [][] grid, int[][] visited){
        List<Pair> nextList=new ArrayList<>();
        if (p.getX()+1>grid.length && grid[p.x+1][p.y]==1 && visited[p.x+1][p.y]==0) {
            nextList.add(new Pair(p.getX()+1,p.getY())); visited[p.x+1][p.y]=1;
        }
        if (p.getX()-1>=0 && grid[p.x-1][p.y]==1 && visited[p.x-1][p.y]==0) {
            nextList.add(new Pair(p.getX()-1,p.getY())); visited[p.x-1][p.y]=1;
        }
        if (p.getY()+1<grid[0].length && grid[p.x][p.y+1]==1 && visited[p.x][p.y+1]==0) {
            nextList.add(new Pair(p.getX(),p.getY()+1)); visited[p.x][p.y+1]=1;
        }
        if (p.getY()-1>=0 && grid[p.x][p.y-1]==1 && visited[p.x][p.y-1]==0) {
            nextList.add(new Pair(p.getX(),p.getY()-1)); visited[p.x][p.y-1]=1;
        }
        return nextList;
    }

    class Pair implements Comparable<Pair> {
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

        @Override
        public int compareTo(Pair o) {
            return 0;
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

}
