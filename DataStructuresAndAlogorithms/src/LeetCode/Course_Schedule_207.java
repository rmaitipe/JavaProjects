package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Course_Schedule_207 {
	/*
	 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
	 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course
	 * bi first if you want to take course ai.
	 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
	 * Return true if you can finish all courses. Otherwise, return false.
	 * Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
	 * [1,0] [1,2] [1,3] [4,3] [2,4] [3,2]
	 * [1,0] [1,2] [1,3] [2,4] [4,3] [3,2]
	 * Incomplete Review Merge Interval question
	 */
	private boolean courseSearch(int matrix [][]) {
		boolean retVal = true;
		int row = matrix.length;
		Map<Integer, List<Integer>> map = new HashMap<>();
		for (int i = 0; i < row; i++) {
			int[] arr = matrix[i];
			if (map.containsKey(arr[1])) {//loop check
				List<Integer> negativeList = map.get(arr[1]);
				if (negativeList.contains(arr[0])) {
					return false;
				}
				else{
					//for each in negativeList add edge
					for (int j: negativeList){
						map.get(j).add(arr[1]);
					}
				}
			} else {
                List<Integer> positive;
				if (map.containsKey(arr[0])) {
                    positive=map.get(arr[0]);
                } else {
                    positive = new ArrayList<>();
                }
                positive.add(arr[1]);
                map.put(arr[0], positive);

            }
		}
		return retVal;
	}

	private void addNode(Map<Integer, List<Integer>> map, int key, int value){
		if (map.containsKey(value)) {
			//for each in positive add node
			for (int j: map.get(value)){
				//addNode(map, value);
			}
		} else {
			//positive = new ArrayList<>();
		}
		map.get(key).add(value);//add To list
	}

	public static void main(String args[]) {
		Course_Schedule_207 ob = new Course_Schedule_207();
		int[][] scores = new int[6][2];
		scores[0] = new int[]{1,0};
		scores[1] = new int[]{1,2};
		scores[2] = new int[]{1,3};
		scores[3] = new int[]{2,4};
		scores[4] = new int[]{4,3};
		scores[5] = new int[]{3,2};
		System.out.println(ob.courseSearch(scores));
	}
}
