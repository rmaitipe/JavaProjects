package LeetCode;

import java.util.*;
import java.util.stream.Collectors;

public class Top_K_Frequent_Elements_347 {
	/*
	 * Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
	 * Input: nums = [1,1,1,2,2,3], k = 2	Output: [1,2]
	 *
	 * List to Array primitives
	 * int[] example1 = list.stream().mapToInt(i->i).toArray();
	 * // OR
	 * int[] example2 = list.stream().mapToInt(Integer::intValue).toArray();
	 *
	 * 	map.put(n, counter.getOrDefault(n, 0) + 1);*
	 */
	public int[] searchMethod(int[] arr,int k) {
		Map<Integer,Integer> map =new HashMap<>();
		for (int a: arr){
			map.put(a,map.getOrDefault(a,0)+1);
		}
		Map<Integer,Integer> sortedMap = map.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
				(oldValue, newValue) -> oldValue, LinkedHashMap::new));
		List<Integer> value = new ArrayList<>();
		int i=1;
		for( Map.Entry<Integer,Integer> entry : sortedMap.entrySet()){
			if (i>sortedMap.size()-k){
				value.add(entry.getKey());
			}
			i++;
		}
		return value.stream().mapToInt(j->j).toArray();
	}

	public static void main(String args[]) {
		Top_K_Frequent_Elements_347 ob = new Top_K_Frequent_Elements_347();
		int [] arr={5,8,12,78,123,145,60,-45,4,8,5};
		int k=3;
		int[] finalNode = ob.searchMethod(arr,k);
		System.out.println("Result: "+ Arrays.toString(finalNode));
		int [] arr2={1,1,1,2,2,3};
		int k2=2;
		int[] finalNode2 = ob.searchMethod(arr2,k2);
		System.out.println("Result: "+ Arrays.toString(finalNode2));
    }

	public int[] topKFrequentAccepted(int[] nums, int k) {
		Map<Integer, Integer> counterMap = new HashMap<>();
		for (int n : nums) {
			counterMap.put(n, counterMap.getOrDefault(n, 0) + 1);
		}
		PriorityQueue<Map.Entry<Integer, Integer>> heap = new PriorityQueue<>(
				(a, b) -> Integer.compare(b.getValue(), a.getValue()));
		for (Map.Entry<Integer, Integer> entry : counterMap.entrySet()) {
			heap.offer(entry);
		}
		int[] res = new int[k];
		for (int i = 0; i < k; i++) {
			res[i] = Objects.requireNonNull(heap.poll()).getKey();
		}
		return res;
	}
}
