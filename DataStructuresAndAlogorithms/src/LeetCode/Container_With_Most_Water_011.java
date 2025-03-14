package LeetCode;

public class Container_With_Most_Water_011 {
    /*
     * You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints
     * of the ith line are (i, 0) and (i, height[i]).
     * Find two lines that together with the x-axis form a container, such that the container contains the most water.
     * Return the maximum amount of water a container can store.
     * Input: height = [1,8,6,2,5,4,8,3,7]  Output: 49 (7*7)
     */

    private int maxAreaIncorrect(int[] input) {
       int[] leftIndexArr=new int [input.length];
       leftIndexArr[0]=0;
       int areaMax=-1;
       for (int i=1;i< input.length;i++){
           if (input[i]>input[leftIndexArr[i-1]]){
               leftIndexArr[i]=i;//new left boundary index
           }else{
               leftIndexArr[i]=leftIndexArr[i-1];
           }
           int area= (i - leftIndexArr[i - 1] ) * (Math.min(input[leftIndexArr[i-1]],input[i]));
           areaMax=Math.max(area,areaMax);
       }
       return areaMax;
    }

    public static void main(String args[]) {
        Container_With_Most_Water_011 ob = new Container_With_Most_Water_011();
        int[] heights = {1,8,6,2,5,4,8,3,7};
        System.out.println(ob.maxAreaIncorrect(heights));
        System.out.println(ob.maxAreaAccepted(heights));
        int[] heights2 = {1,8,6,2,5,4,10,3,7};
        System.out.println(ob.maxAreaIncorrect(heights2));
        System.out.println(ob.maxAreaAccepted(heights2));
    }

    /*
     * left and right pointers are initialized to the start and end of the height array, while left < right.
     * Calculate Area:minHeight = Math.min(height[left], height[right]): Determine the shorter of the two lines.
     * width = right - left: Calculate the width between the two pointers.
     * maxArea = Math.max(maxArea, area): Update maxArea if the current area is larger.
     * If height[left] < height[right], move the left pointer to the right to try and find a taller line on the left.
     * Otherwise, move the right pointer to the left to try and find a taller line on the right.
     * [Move the pointer pointing to the shorter line inward, as this might increase the height of the next container.]
     */
    public int maxAreaAccepted(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int max = 0;
        while(left < right){
            int w = right - left;
            int h = Math.min(height[left], height[right]);
            int area = h * w;
            max = Math.max(max, area);
            if(height[left] < height[right]) left++;
            else if(height[left] > height[right]) right--;
            else {
                left++;
                right--;
            }
        }
        return max;
    }
}
