package LeetCode;

import java.util.*;
import java.util.stream.Collectors;

public class Top_K_Frequent_Elements_347 {
	/*
	 * Given an integer array nums and an integer k, return the k most frequent elements.
	 * You may return the answer in any order. It is guaranteed k is valid and that the answer is unique.
	 * Input: nums = [1,1,1,2,2,3], k = 2	Output: [1,2]
	 *
	 * List to Array primitives: int[]arr=list.stream().mapToInt(i->i).toArray() or list.stream().mapToInt(Integer::intValue).toArray();
	 * map.put(k, map.getOrDefault(k, 0) + 1);*
	 */
	public int[] searchMethod(int[] arr,int k) {
		Map<Integer,Integer> map =new HashMap<>();
		for (int a: arr){
			map.put(a,map.getOrDefault(a,0)+1);
		}
		Map<Integer,Integer> sortedMap = map.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect
			(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,(oldValue,newValue)->oldValue, LinkedHashMap::new));//O(n.log n)
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

	public static void main(String[] args) {
		Top_K_Frequent_Elements_347 ob = new Top_K_Frequent_Elements_347();
		int [] arr={5,8,12,8,123,145,60,-45,4,8,5,12};
		int k=3;
		int[] finalNode = ob.searchMethod(arr,k);
		System.out.println("Result: k="+k+" Output"+ Arrays.toString(finalNode));
		int [] arr2={1,1,1,2,2,3};
		int k2=2;
		int[] finalNode2 = ob.searchMethod(arr2,k2);
		System.out.println("Result: k="+k2+" Output"+ Arrays.toString(finalNode2));
		System.out.println("Accepted: k="+k+" Output"+ Arrays.toString(ob.topKFrequentAccepted(arr,k)));
		System.out.println("Accepted: k="+k2+" Output"+ Arrays.toString(ob.topKFrequentAccepted(arr2,k2)));
    }

	/*
	private static Map<Integer, Integer> sortedHashMapByValues(Map<Integer, Integer> hashmap) {
		TreeMap<Integer, Integer>  treeMap = new TreeMap<>((o1, o2) -> {
			if (o1 != o2) return Integer.compare(o1,o2);
			else return o1.compareTo(o2);
		});
		treeMap.putAll(hashmap);
		return treeMap;
	}*/

	public int[] topKFrequentAccepted(int[] nums, int k) {
		Map<Integer, Integer> counterMap = new HashMap<>();
		for (int n : nums) {
			counterMap.put(n, counterMap.getOrDefault(n, 0) + 1);
		}
		//This could also be a TreeMap? - You can not sort TreeMap on values.
		//TreeMap<Integer, Integer> tm= sortedHashMapByValues(counterMap);
		PriorityQueue<Map.Entry<Integer, Integer>> heap = new PriorityQueue<>(
				(a, b) -> Integer.compare(b.getValue(), a.getValue()));
		for (
				Map.Entry<Integer, Integer> entry : counterMap.entrySet()) {
			heap.offer(entry);
		}
		int[] res = new int[k];
		for (int i = 0; i < k; i++) {
			res[i] = Objects.requireNonNull(heap.poll()).getKey();
		}
		return res;
	}

}
