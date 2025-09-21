package LeetCode;


import java.util.*;

public class Sort_Characters_By_Frequency_451 {
	/*

 */

	public static void main(String args[]) {
		Sort_Characters_By_Frequency_451 ob = new Sort_Characters_By_Frequency_451();
		String out=ob.frequencySort("tree");
		System.out.println(out);
		String out1=ob.frequencySort("priti");
		System.out.println(out1);

    }

	public String frequencySort(String s) {
		Map<Character, Integer> map = new HashMap();
		for (char c : s.toCharArray()) {
			map.put(c, map.getOrDefault(c, 0) + 1);
		}

		PriorityQueue<Map.Entry<Character,Integer>> pq = new PriorityQueue<>((a, b) -> Integer.compare(b.getValue(), a.getValue()));
		for (Map.Entry<Character,Integer> c : map.entrySet()) {
			pq.offer(c);
		}

		StringBuilder sb = new StringBuilder();
		while (!pq.isEmpty()) {
			Map.Entry<Character,Integer> c = pq.poll();
			for (int i = 0; i < c.getValue(); i++) {
				sb.append(c.getKey());
			}
		}
		return sb.toString();
	}

}
