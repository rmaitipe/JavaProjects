package LeetCode;

public class Climbing_Stairs_070 {

    /*
     * You are climbing a staircase. It takes n steps to reach the top.
     * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
     */

    public static void main(String args[]) {
        Climbing_Stairs_070 ob = new Climbing_Stairs_070();
        int count= ob.uniqueStairPath(10);
        System.out.println(count);
    }

    private int uniqueStairPath(int length) {
        int[] arr=new int [length];
        if (length==1){
            return 1;
        } else if (length==2){
            return 2;
        }
        arr[0]=1;
        arr[1]=2;
        for (int j=2;j<length-1;j++){
             arr[j]=arr[j-1]+arr[j-2];
        }
        return arr[length-1];
    }
}
