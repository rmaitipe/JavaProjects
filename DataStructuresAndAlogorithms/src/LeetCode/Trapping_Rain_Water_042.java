package LeetCode;


public class Trapping_Rain_Water_042 {
    /*
     * Given n non-negative integers representing an elevation map where the width of each bar is 1,
     * compute how much water it can trap after raining.
     * Input: height = [4,2,0,3,2,5]    Output: 9
     * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]    Output: 6
     */

    /*
     * set pointers to initial location
     * if next greater than left
     *      move left, i.e higher left boundary (if not in a collection)
     *      collection end-> add to total
     * if next smaller than left it is a possible collection
     *      initialize right  as left+1
     *
     */
    public int trap(int[] input) {
        int leftIdx=0;int rightIdx=0;//2 pointers
        int leftBound=0;
        int total=0;
        boolean isCollection=false;
        for (int i=1;i<input.length;i++){
            if (input[i]>leftBound){
                if (!isCollection) {
                    leftIdx++;
                    rightIdx++;
                    leftBound=input[i];
                } else{//close boundary
                    total+=(rightIdx-leftIdx)*Math.min(leftBound,input[i]);
                    for (int j=leftIdx+1;j<rightIdx;j++){
                        total-=input[leftIdx];
                    }
                    System.out.println(total);
                    leftBound=input[i];
                    leftIdx++;
                    rightIdx=leftIdx;
                }
            } else{
                rightIdx++;
                isCollection=true;
            }
        }
        return total;
    }

    public static void main(String args[]) {
        Trapping_Rain_Water_042 ob = new Trapping_Rain_Water_042();
        //int[] heights = {4,2,0,3,2,5};
        //System.out.println(ob.trap(heights));
        int[] heights2 = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(ob.trap(heights2));
    }

}
