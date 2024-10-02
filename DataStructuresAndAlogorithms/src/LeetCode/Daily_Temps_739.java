package LeetCode;

import java.util.Arrays;
import java.util.Stack;

public class Daily_Temps_739 {
    /*
     * Given an array of integers temperatures represents the daily temperatures, return an array answer such that
     * answer[i] is the number of days you have to wait after the ith day to get a warmer temperature.
     * If there is no future day for which this is possible, keep answer[i] == 0 instead.
     */

    public static void main(String args[]) {
        Daily_Temps_739 ob = new Daily_Temps_739();
        int[] temps = {73,74,75,71,69,70,72,73};
        System.out.println(Arrays.toString(ob.dailyTempDiff(temps)));
        System.out.println(Arrays.toString(ob.dailyTemperaturesAcceptedDP(temps)));
        System.out.println(Arrays.toString(ob.dailyTemperaturesAcceptedStack(temps)));
    }

    private int[] dailyTempDiff(int[] num) {
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

    /* Stack order will have current min 75-71-69 on 70*/
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

    public int[] dailyTemperaturesAcceptedDP(int[] temperatures) {
        int n = temperatures.length;
        if(n == 1) return new int[]{0};
        int[] answer = new int[n];
        answer[n - 1] = 0;
        for(int i = n - 2; i >= 0; i--){
            int pointer = i + 1;
            int counter = 1;
            while(temperatures[i] >= temperatures[pointer]){
                if(pointer == n - 1 || answer[pointer] == 0) {
                    counter = 0;
                    break;
                }
                counter+= answer[pointer];
                pointer+= answer[pointer];
            }
            answer[i] = counter;
        }
        return answer;
    }
}
