package LeetCode;


public class Trapping_Rain_Water_042 {
    /*
     * Given n non-negative integers representing an elevation map where the width of each bar is 1,
     * compute how much water it can trap after raining.
     * Input: height = [4,2,0,3,2,5]    Output: 9
     *
     * Expanded on 011
     */
    public int trap(int[] input) {
        int[] leftIndexArr=new int [input.length];
        leftIndexArr[0]=0;
        int areaMax=-1;
        for (int i=1;i< input.length;i++){
            if (input[i]>input[leftIndexArr[i-1]]){
                leftIndexArr[i]=i;//new left boundary index
            }else{
                leftIndexArr[i]=leftIndexArr[i-1];
            }
            int possibleArea;
            if (input[leftIndexArr[i-1]]<input[i]){
                possibleArea = (i - leftIndexArr[i - 1] -1) * (input[leftIndexArr[i-1]]);
            }else{//if (input[leftIndexArr[i-1]]>input[i])
                possibleArea = (i - leftIndexArr[i - 1] -1) * (input[i]);
            }
            int minusArea=0;
            for (int j=leftIndexArr[i - 1]+1;j<i;j++){
                minusArea+=input[j];
            }
            int area=possibleArea-minusArea;
            if (area>areaMax){
                areaMax=area;
            }
        }
        return areaMax;
    }

    public static void main(String args[]) {
        Trapping_Rain_Water_042 ob = new Trapping_Rain_Water_042();
        int[] heights = {4,2,0,3,2,5};
        System.out.println(ob.trap(heights));
    }

}
