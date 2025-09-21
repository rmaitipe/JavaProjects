package LeetCode;

import java.util.*;

public class N_Queens_051 {
    /*
     * The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
     * Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.
     * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.
     */

    public static void main(String args[]) {
        N_Queens_051 ob = new N_Queens_051();
        int n=4;
        System.out.println(ob.solveNQueensPair(n));
    }

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> retList= new ArrayList<>();
        char[][] grid=new char[n][n];
        List<int[]> listCoordinate=new ArrayList<>();
        for (int k =0;k<n;k++){
            Arrays.fill(grid[k],'.');
        }
        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++)  {
                listCoordinate.add(new int []{i,j});
            }
        }
        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++)  {
                List<int[]> losList = placeLOS(new int[]{i,j},grid);
                listCoordinate.removeAll(losList);
                grid[i][j]='Q';
                backtrack(1,n, listCoordinate, grid,retList);
                grid[i][j]='.';
                listCoordinate.addAll(losList);
            }
        }
        return retList;
    }

    private List<int[]> placeLOS(int[] place,char [][] grid) {
        List<int[]> losList=new ArrayList<>();
        int n = grid.length;
        grid[place[0]][place[1]]='Q';
        //h
        for (int i=0;i<n;i++){
            losList.add(new int[]{place[0],i});
        }
        //v
        for (int i=0;i<n;i++){
            losList.add(new int[]{i,place[1]});
        }
        //d
        int countP=1;
        while ((place[0]+countP)<n && place[1]+countP<n){
            losList.add(new int[]{place[0]+countP,place[1]+countP});
            countP++;
        }
        int countN=1;
        while ((place[0]-countN)>0 && place[1]-countN>0){
            losList.add(new int[]{place[0]+countN,place[1]+countN});
            countN++;
        }
        return losList;
    }


    void backtrack(int count, int n, List<int[]> listCoordinate, char[][] grid, List<List<String>> finList){
        if (count==n){
            // uniqueness check
            List<String> currList= new ArrayList<>();
            for (int a=0;a<n;a++) {
                StringBuilder sb= new StringBuilder();
                for (int b=0;b<n;b++)  {
                    sb.append(grid[a][b]);
                }
                currList.add(sb.toString());
            }
            if (!finList.contains(currList)){
                finList.add(currList);
            }
        } else if (listCoordinate.isEmpty()){

        } else {
            for (int[] k: listCoordinate){
                List<int[]> losList = placeLOS(k,grid);
                listCoordinate.removeAll(losList);
                backtrack(count++,n,listCoordinate, grid, finList);
                grid[k[0]][k[1]]='.';
                listCoordinate.addAll(losList);
            }
        }
    }

    public List<List<String>> solveNQueensPair(int n) {
        List<List<String>> retList= new ArrayList<>();
        char[][] grid=new char[n][n];
        List<Pair> listCoordinate=new ArrayList<>();
        for (int k =0;k<n;k++){
            Arrays.fill(grid[k],'.');
        }
        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++)  {
                listCoordinate.add(new Pair(i,j));
            }
        }
        backtrackPair(0,n, listCoordinate, grid,retList);
        /*for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++)  {
                List<Pair> losList = placeLOSPair(new Pair(i,j),grid);
                List<Pair> temp = new ArrayList<>(listCoordinate);
                listCoordinate.removeAll(losList);
                grid[i][j]='Q';
                backtrackPair(1,n, new ArrayList<>(listCoordinate), grid,retList);
                grid[i][j]='.';
                listCoordinate =temp;
            }
        }*/
        return retList;
    }

    private List<Pair> placeLOSPair(Pair place,char [][] grid) {
        List<Pair> losList=new ArrayList<>();
        int n = grid.length;;
        //h
        for (int i=0;i<n;i++){
            losList.add(new Pair(place.x,i));
        }
        //v
        for (int i=0;i<n;i++){
            losList.add(new Pair(i,place.y));
        }
        //d
        int countP=1;
        while ((place.x+countP)<n && place.y+countP<n){
            losList.add(new Pair(place.x+countP,place.y+countP));
            countP++;
        }
        int countN=1;
        while ((place.x-countN)>=0 && place.y-countN>=0){
            losList.add(new Pair(place.x-countN,place.y-countN));
            countN++;
        }
        int countPX=1;
        while ((place.x+countPX)<n && place.y-countPX>=0){
            losList.add(new Pair(place.x+countPX,place.y-countPX));
            countPX++;
        }
        int countNX=1;
        while ((place.x-countNX)>=0 && place.y+countNX<n){
            losList.add(new Pair(place.x-countNX,place.y+countNX));
            countNX++;
        }
        return losList;
    }


    void backtrackPair(int count, int n, List<Pair> listCoordinate, char[][] grid, List<List<String>> finList){
        if (count==n){//exit
            // uniqueness check
            List<String> currList= new ArrayList<>();
            for (int a=0;a<n;a++) {
                StringBuilder sb= new StringBuilder();
                for (int b=0;b<n;b++)  {
                    sb.append(grid[a][b]);
                }
                currList.add(sb.toString());
            }
            if (!finList.contains(currList)){
                finList.add(currList);
            }
        } else if (listCoordinate.isEmpty()){
            //System.out.println("Exit");
        } else {
            for (int l=0;l<listCoordinate.size();l++){
                Pair k= listCoordinate.get(l);
                List<Pair> losList = placeLOSPair(k,grid);
                List<Pair> temp = new ArrayList<>(listCoordinate);
                listCoordinate.removeAll(losList);
                grid[k.x][k.y]='Q';
                backtrackPair(count+1,n,new ArrayList<>(listCoordinate), grid, finList);
                grid[k.x][k.y]='.';
                listCoordinate =temp;
            }
        }
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
    }

}
