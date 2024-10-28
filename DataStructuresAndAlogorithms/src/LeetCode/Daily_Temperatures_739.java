package LeetCode;

import java.util.Arrays;
import java.util.Stack;

public class Daily_Temperatures_739 {
    /*
     * Given an array of integers temperatures represents the daily temperatures, return an array answer such that
     * answer[i] is the number of days you have to wait after the ith day to get a warmer temperature.
     * If there is no future day for which this is possible, keep answer[i] == 0 instead.
     *
     * Input: temperatures = [73,74,75,71,69,72,76,73]  Output: [1,1,4,2,1,1,0,0]
     */
    private int[] dailyTempDiff(int[] num) {//O(n2)
        int[] out=new int[num.length];
        for (int i=0;i<=num.length-1;i++){
            for (int j=i+1;j<=num.length-1;j++){
                if (num[j]>num[i]){
                    out[i]=j-i;
                    break;
                }
            }
        }
        return out;
    }

    /*
    1.Initialize an array result to store the number of days until the next warmer temperature for each temperature.
    2.Iterate through the array of temperatures.
    3.For each temperature, if the stack is not empty and the current temperature is greater than the temperature at
      the index at the top of the stack, pop the stack and calculate the number of days until the next warmer temperature.
    4.Push the current index onto the stack.
    5.Continue this process until all temperatures are processed.
    */
    public int[] dailyTemperaturesAcceptedStack(int[] temps) {
        int[] results = new int[temps.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < temps.length; i++) {
            while (!stack.isEmpty() && temps[stack.peek()] < temps[i]) {
                results[stack.peek()] = i - stack.pop();
            }
            stack.push(i);
        }
        return results;
    }

    /*
    Input: temperatures = [73,74,75,71,69,72,76,73]  Output: [1,1,4,2,1,1,0,0]
    Allocate memory for result array. The result does not count towards space complexity.
    There is no solution for the last day, so set it's value in the return array to be 0
    For every day from the second to last to the first day :
        Initially offset is initialized to 1 - difference between this day and the day we are currently checking.
        As long as the temperature at day + offset isn't more than the temperature at day, and there is a hotter day after
        day + offset : Add the result at day + offset to next to skip all days which were not hotter than day+offset.
        Because day + offset isn't hotter than day, this is guaranteed not to skip the solution for day.
        If day + offset is hotter than day : Set the result of day to be offset.
        Else : Set the result of day to be 0 to signify that there are no hotter days.
    Return the results array.

    Time complexity: O(n), amortized time, the inner loops skips days efficiently.
    In the worst case scenario, a day will need to check every single day that comes after it to find the next hottest day.
    However, if a future day is checked, and it is not the solution, it will be removed from consideration when other
    days down the road need to be solved. So the amortized time complexity for solving any given day is o(1).
    Solving n days takes o(n) time.
    Space complexity: O(n)
     */
    public int[] dailyTemperaturesAcceptedDP(int[] temperatures) {
        int n = temperatures.length;
        if(n == 1)
            return new int[]{0};
        int[] answer = new int[n];
        for(int i = n - 2; i >= 0; i--){
            /*
            int pointer = i + 1;
            int counter = 1;
            while(temperatures[i] >= temperatures[pointer]){
                if(pointer == n - 1 || answer[pointer] == 0) {
                    counter = 0;
                    break;
                }
                counter += answer[pointer];
                pointer += answer[pointer];
            }
            answer[i] = counter;*/
            int j = i + 1;
            while (j < temperatures.length && temperatures[i] >= temperatures[j]) {
                if (answer[j] > 0) {
                    j += answer[j];
                } else {
                    j = temperatures.length;    // when there isn't any warmer future, exit keeping answer[i]=0
                }
            }
            if (j < temperatures.length) {
                answer[i] = j - i;
            }
        }
        return answer;
    }

    public static void main(String args[]) {
        Daily_Temperatures_739 ob = new Daily_Temperatures_739();
        int[] temps = {73,74,75,71,69,70,72,73};
        System.out.println(Arrays.toString(ob.dailyTempDiff(temps)));
        System.out.println(Arrays.toString(ob.dailyTemperaturesAcceptedDP(temps)));
        System.out.println(Arrays.toString(ob.dailyTemperaturesAcceptedStack(temps)));
    }
}
