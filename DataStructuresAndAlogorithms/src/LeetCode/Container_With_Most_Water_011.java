package LeetCode;


public class Container_With_Most_Water_011 {
    /*
     * You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints
     * of the ith line are (i, 0) and (i, height[i]).
     * Find two lines that together with the x-axis form a container, such that the container contains the most water.
     * Return the maximum amount of water a container can store.
     * Input: height = [1,8,6,2,5,4,8,3,7]  Output: 49
     */
    private int maxArea(int[] input) {
       int[] leftIndexArr=new int [input.length];
       leftIndexArr[0]=0;
       int areaMax=-1;
       for (int i=1;i< input.length;i++){
           if (input[i]>input[leftIndexArr[i-1]]){
               leftIndexArr[i]=i;//new left boundary index
           }else{
               leftIndexArr[i]=leftIndexArr[i-1];
           }
           int area;
           if (input[leftIndexArr[i-1]]<input[i]){
               area = (i - leftIndexArr[i - 1] -1) * (input[leftIndexArr[i-1]]);
           }else{//if (input[leftIndexArr[i-1]]>input[i])
               area = (i - leftIndexArr[i - 1] -1) * (input[i]);
           }
           if (input[leftIndexArr[i-1]]>input[i]) {
               area = (i - leftIndexArr[i - 1]) * (input[i]);
               if (area>areaMax){
                   areaMax=area;
               }
           }
       }
       return areaMax;
    }

    public static void main(String args[]) {
        Container_With_Most_Water_011 ob = new Container_With_Most_Water_011();
        int[] heights = {1,8,6,2,5,4,8,3,7};
        System.out.println(ob.maxArea(heights));
    }

}
