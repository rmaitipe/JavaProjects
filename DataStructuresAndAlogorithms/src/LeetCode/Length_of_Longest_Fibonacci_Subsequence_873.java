package LeetCode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Length_of_Longest_Fibonacci_Subsequence_873 {
	/*
	 * A sequence x1, x2, ..., xn is Fibonacci-like if:n >= 3 & xi + xi+1 == xi+2 for all i + 2 <= n
	 * Given a strictly increasing array arr of positive integers forming a sequence, return the length of the longest
	 * Fibonacci-like subsequence of arr. If one does not exist, return 0.
	 * subsequence is derived from another sequence arr by deleting any number of elements (including none) from arr,
	 * without changing the order of the remaining elements. For example, [3, 5, 8] is a subsequence of [3, 4, 5, 6, 7, 8].
	 * Example 1:Input: arr = [1,2,3,4,5,6,7,8] Output: 5 : [1,2,3,5,8].
	 * Example 2:Input: arr = [1,3,7,11,12,14,18] Output: 3 : [1,11,12], [3,11,14] or [7,11,18].
	 */
	public int lenLongestFibSubseqUsingPair(int[] arr) {
		int n=arr.length;
		HashMap<Pair,Integer> dpMap=new HashMap<>();
		int max=0;
		HashSet<Integer> hashSet=new HashSet<>();
		for (int i=0;i<n;i++){
			for (int j=i-1;j>0;j--){
				if(arr[i]-arr[j]>=arr[j]){
					break;
				}
				if (hashSet.contains(arr[i]-arr[j])){
					Pair key =new Pair(arr[j],arr[i]-arr[j]);//2,1
					int temp=dpMap.getOrDefault(key,0);
					Pair key2 =new Pair(arr[i], arr[j]);//3,2
					dpMap.put(key2,temp+1);
					max =Math.max(max,temp+1);
				}
			}
			hashSet.add(arr[i]);
		}
		return max!=0?max+2:max;
	}

	class Pair{
		int x;
		int y;

		Pair (int x,int y){
			this.x=x;
			this.y=y;
		}
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Pair pair = (Pair) o;
			return x == pair.x && y == pair.y;
		}

		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}
	}

	public static void main(String[] args) {
		Length_of_Longest_Fibonacci_Subsequence_873 ob = new Length_of_Longest_Fibonacci_Subsequence_873();
		int[] s = {1,2,3,4,5,6,7,8};
		int[] s1 = {1,3,7,11,12,14,18};
		int[] s2 = {2,4,7,8,9,10,14,15,18,23,32,50};  //4,14,18,32,50
		System.out.println(ob.lenLongestFibSubseqUsingPair(s));
		System.out.println(ob.lenLongestFibSubseqUsingPair(s1));
		System.out.println(ob.lenLongestFibSubseqUsingPair(s2));
	}

	public int lenLongestFibSubseq(int[] arr) {
		int n=arr.length;
		HashMap<Integer[],Integer> dpMap=new HashMap<>();
		int max=0;
		HashSet<Integer> hashSet=new HashSet<>();
		for (int i=0;i<n;i++){
			for (int j=i-1;j>0;j--){
				if (hashSet.contains(arr[i]-arr[j])){
					Integer[] key ={arr[j],arr[i]-arr[j]};//2,1
					int temp=dpMap.getOrDefault(key,0);
					Integer[] key2 ={arr[i], arr[j]};//3,2
					dpMap.put(key2,temp+1);
					max =Math.max(max,temp+1);
				}
			}
			hashSet.add(arr[i]);
		}
		return max+2;
	}

}
