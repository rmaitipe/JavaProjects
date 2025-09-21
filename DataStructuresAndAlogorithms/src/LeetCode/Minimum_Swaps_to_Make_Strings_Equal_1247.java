package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Minimum_Swaps_to_Make_Strings_Equal_1247 {
	/*
	 * Find maximum sum, move by maxSteps
	 */
	public static void main(String args[]) {
		Minimum_Swaps_to_Make_Strings_Equal_1247 ob = new Minimum_Swaps_to_Make_Strings_Equal_1247();
		String s1="xxyyxyxyxx";
		String s2="xyyxyxxxyx";
		int count=ob.minimumSwap(s1,s2);
		System.out.println(count);//recursion will have duplicated method calls
    }

	public int minimumSwap(String s1, String s2) {
		int xmis = 0;  // mismatch where s1[i] has 'x'
		int ymis = 0;  // mismatch where s1[i] has 'y'
		int count=-1;
		int temp=0;
		// count mismatches
		int n =s1.length();
		for (int i = n-1; i >= 0; i--) {
			if (s1.charAt(i) == 'x' && s2.charAt(i) == 'y') {
				xmis++;
			} else if (s1.charAt(i) == 'y' && s2.charAt(i) == 'x') {
				ymis++;
			}
		}
		int remX= 0;
		int remY= 0;
		if (xmis>0){
			if (xmis%2==0) {
				temp+=xmis/2;
			}
			else{
				temp+=(xmis-1)/2;
				remX=1;
			}
		}
		if (ymis>0){
			if (ymis%2==0){
				temp+=ymis/2;
			}
			else{
				temp+=(ymis-1)/2;
				remY=1;
			}
		}
		if (remX==1 && remY==1){
			count=0;
			count+=temp;
			count+=2;
		} else if (remX==0 && remY==1 || remX==1 && remY==0){
			//mismatch
			count=-1;
		} else{
			count=0;
			count+=temp;//0
		}
		return count;
	}

}
