package LeetCode;

public class Reverse_Integer_007 {

	/*
	 * Given a signed 32-bit integer x, return x with its digits reversed. If reversing x causes the value to go outside
	 * the signed 32-bit integer range [-2^31 to 2^31 - 1], then return 0.
	 * Assume the environment does not allow you to store 64-bit integers (signed or unsigned).
	 */
	public int reverseMethod(int k){
        int nextDigit =0;
		boolean isNeg = false;
		StringBuffer sb= new StringBuffer("");
		if (k<0){
			isNeg=true;
			k=k*-1;
		}
		int prev_rev_num = 0, rev_num = 0;
		while (k!=0){
			nextDigit=k%10;
			rev_num = (rev_num*10) + nextDigit;
			sb.append(nextDigit);
			/*
			Logic for checking if the reverse overflowed or not.
            The values of (rev_num - nextDigit)/10 and prev_rev_num must be same if there was no problem.
            if ((rev_num - nextDigit)/10 != prev_rev_num) {
                System.out.println("WARNING OVERFLOWED!!!");
                return 0;
            }
			 */
			prev_rev_num = rev_num;
			k=k/10;
		}
		if (isNeg) {
			return Integer.valueOf(sb.toString())  * -1;
		}
		else
			return Integer.valueOf(sb.toString());
	}

	
	public static void main(String args[]) {
        int input =-34525;
		Reverse_Integer_007 ob = new Reverse_Integer_007();
        System.out.println(ob.reverseMethod(input));
    }

}
