package LeetCode;

import java.util.*;

public class Top_K_Frequent_Elements_347 {

	/*
	 * Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
	 */
	public int[] searchMethod(int[] arr,int k) {
		Map<Integer,Integer> map =new HashMap<>();
		/*for (int a: arr){
				map.put(a,map.getOrDefault(a,0)+1);
			}
		}
		LinkedHashMap<Integer,Integer> newMap = map.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,
				(oldValue, newValue) -> oldValue, LinkedHashMap::new));
		newMap.entrySet().stream()
		Iterator<Integer> it =newMap.keySet().stream().iterator();
		int j=1;
		while (it.hasNext()){
			if (j==k) {
				Integer ret= it.next();
			}else{
				j++;
			}
		}*/
		return null;
	}

	public static void main(String args[]) {
		Top_K_Frequent_Elements_347 ob = new Top_K_Frequent_Elements_347();
		int [] arr={5,8,12,78,123,145,60,-45,4,8,5};
		int k=3;
		int[] finalNode = ob.searchMethod(arr,k);
		System.out.println("Result: "+finalNode);
    }

}
