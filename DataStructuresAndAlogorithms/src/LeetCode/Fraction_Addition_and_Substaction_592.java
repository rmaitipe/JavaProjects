package LeetCode;

public class Fraction_Addition_and_Substaction_592 {
    /*
     * Given a string expression representing an expression of fraction addition and subtraction, return the calculation
     * result in string format. The final result should be an irreducible fraction. If your final result is an integer,
     * change it to the format of a fraction that has a denominator 1. So in this case, 2 should be converted to 2/1.
     * Input: expression = "-1/2+1/2+1/3"   Output: "1/3"
     */

    public static void main(String args[]) {
        Fraction_Addition_and_Substaction_592 ob = new Fraction_Addition_and_Substaction_592();
        String input= "1/3-1/2";
        System.out.println(ob.fractionAddition(input));
    }

    public String fractionAddition(String expression) {
        String regex = "[+-]";
        String[] operands= expression.split(regex);//?
        int length= operands.length;
        Integer [] nums=new Integer [length];
        Integer[] denom= new Integer[length];
        int idx=0;
        for (String number: operands){
            String[] s =number.split("/");
            nums[idx]=Integer.valueOf(s[0]);
            denom[idx]=Integer.valueOf(s[1]);
            idx++;
        }
        Character [] operators= new Character[length];
        String[] operands2= expression.split("/");
        int index=0;
        for (String str: operands2){
            if (str.length()>1){
                operators[index]=str.charAt(1);
            }
            else if (str.length()==1 && index==0){
                operators[index]='+';
            }
            index++;
        }
        Integer commonDenom= denom[0];
        for (int i=1; i<denom.length; i++){
            if (commonDenom%denom[i]!=0){
                commonDenom*=denom[i];
            }
        }
        for (int i=0; i<nums.length; i++){
            int factor= commonDenom/denom[i];
            if (factor!=1){
                nums[i]*=nums[i]*factor;
            }
        }
        int sum=0;
        for (int i=0; i<operators.length; i++){
            switch (operators[i]) {
                case '+':
                    sum += nums[i];
                    break;
                case '-':
                    sum -= nums[i];
                    break;
                default:
                    break;
            }
        }

        for (int i=2; i< commonDenom/2; i++){
            if (sum%i==0 && commonDenom %i==0){
                sum=sum/i;
                commonDenom=commonDenom/i;
            } else{
                i++;
            }
        }
        return String.valueOf(sum)+"/"+ String.valueOf(commonDenom);
    }

}
