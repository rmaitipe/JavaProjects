package LeetCode;

import java.util.*;

public class Valid_Square_593 {
    /*
     * Given the coordinates of four points in 2D space p1, p2, p3 and p4, return true if the four points construct a square.
     * The coordinate of a point pi is represented as [xi, yi]. The input is not given in any order.
     * A valid square has four equal sides with positive length and four equal angles (90-degree angles).
     */
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        List<int[]> list =new ArrayList<>();
        list.add(p2);
        list.add(p3);
        list.add(p4);
        return eval1stPhase(p1, list);
    }

    boolean eval1stPhase(int[] p1, List<int[]> list){
        boolean retVal = false;
        double[] arr = new double [3];
        Double k=0.0;
        int diagIdx=-1;
        boolean firstPhase = false;
        int j=0;
        for (int [] ent:list){
            arr[j]=calc (p1,list.get(j));
            j++;
        }
        if (arr[0] == arr[1]){
            if (arr[2]== Math.sqrt(2)*arr[0]){
                k=arr[0];
                diagIdx=2;
                firstPhase=true;
            }
        }
        if (arr[0] == arr[2]){
            if (arr[1]== Math.sqrt(2)*arr[0]){
                k=arr[0];
                diagIdx=1;
                firstPhase=true;
            }
        }
        if (arr[1] == arr[2]){
            if (arr[0]== Math.sqrt(2)*arr[1]){
                k=arr[1];
                diagIdx=0;
                firstPhase=true;
            }
        }
        if (firstPhase) {
            double[] arr2 = new double[2];
            int[] p4=list.get(diagIdx);
            list.remove(p4);
            int l=0;
            for (int [] ent:list){
                arr2[l]=calc (p4,list.get(l));
                l++;
            }
            if (arr2[0] == k && arr2[1] == k) {
                retVal = true;
            }
        }
        return retVal;
    }

    double calc (int[] p1, int [] p2){
        return Math.sqrt((Math.pow(p1[0]-p2[0],2)+Math.pow(p1[1]-p2[1],2)));
    }

    public static void main(String args[]) {
        Valid_Square_593 ob = new Valid_Square_593();
        int[] p1 ={0,0};
        int[] p2 = {0,1};
        int[] p3 = {1,0};
        int[] p4 = {1,1};
        System.out.print(ob.validSquare(p1,p2,p3,p4));
        System.out.print(ob.validSquareAccepted(p1,p2,p3,p4));
    }


    // This method returns true if the given 4 points form a square, false otherwise
    public boolean validSquareAccepted(int[] p1, int[] p2, int[] p3, int[] p4) {
        // We use a set to store the distances between the points
        Set<Integer> set = new HashSet();
        // Calculate the distances between all pairs of points and add them to the set
        set.add(distanceSquare(p1,p2));
        set.add(distanceSquare(p1,p3));
        set.add(distanceSquare(p1,p4));
        set.add(distanceSquare(p2,p3));
        set.add(distanceSquare(p2,p4));
        set.add(distanceSquare(p3,p4));
        // A square must have 4 equal sides, so the set must contain 2 different values (the lengths of the sides and the diagonals)
        // The set should not contain 0, as that would mean that two points have the same coordinates
        return !set.contains(0) && set.size() == 2;
    }
    // This method calculates the distance between two points and returns its square
    private int distanceSquare(int[] a, int[] b){
        // We use the Pythagorean theorem to calculate the distance between the points
        return (a[0]-b[0])*(a[0]-b[0]) + (a[1]-b[1])*(a[1]-b[1]);
    }

}
