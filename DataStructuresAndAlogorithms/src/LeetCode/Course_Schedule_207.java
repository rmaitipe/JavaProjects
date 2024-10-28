package LeetCode;

import java.util.*;

public class Course_Schedule_207 {
	/*
	 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses-1.
	 * You are given an array prerequisites where prerequisites[i] = [a, b] indicates that you must take course b first
	 * if you want to take course a.
	 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
	 * Return true if you can finish all courses. Otherwise, return false.
	 * Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
	 * [1,0] [1,2] [1,3] [4,3] [2,4] [3,2]
	 * [1,0] [1,2] [1,3] [2,4] [4,3] [3,2]
	 */
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		//iterate and store x-y in a map if y-x exists break loop
		//logic is x-y and y-z then x-z
		// before inserting check for loop
		boolean retVal;
		Map<Integer,List<Integer>> map =new HashMap<>();
		int row= prerequisites.length;
		for (int i=0;i<row;i++){
			int[] arr=prerequisites[i];
			List<Integer> valueList=map.get(arr[1]);
			if (valueList!=null) {
				if (valueList.contains(arr[0])) {
					return false;//loop
				} else {
					for (int rev : valueList) {
						retVal = addNode(map, arr[0], rev);
						if (!retVal) return retVal;
					}
				}
			}
			List<Integer> positive;
			if (map.get(arr[0])==null){
				positive=new ArrayList<>();
			} else {
				positive=map.get(arr[0]);
			}
			positive.add(arr[1]);
			map.put(arr[0], positive);
		}
		return true;
	}

	private boolean addNode(Map<Integer, List<Integer>> map, int key, int value){
		boolean retVal = false;
		List<Integer> negative=map.get(value);
		if (negative!=null) {
			if (negative.contains(key)) {
				return false;
			} else {
				for (int rev : negative) {
					retVal = addNode(map, key, rev);
					if (!retVal) return retVal;
				}
			}
		}
		List<Integer> positive;
		if (map.get(key)==null){
			positive=new ArrayList<>();
		} else {
			positive=map.get(key);
		}
		positive.add(value);
		map.put(key, positive);
		return  retVal;
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
		int[][] score2=Arrays.copyOf(scores,scores.length);
		System.out.println(ob.canFinish(2,scores));
		System.out.println(ob.canFinishAccepted(5,score2));
	}

	/*
	 * The code begins by creating a count array to store the in-degree of each course and an adjacency list to store
	 * the courses that depend on each course. The code then fills the count array and adjacency list using the prerequisites array.
	 * Check for cycles:Next, the code uses a depth-first search (DFS) to traverse the graph and detect any cycles.
	 * The DFS starts from courses with in-degree 0, meaning courses without any prerequisites. If a course has already
	 * been visited, the DFS returns without visiting its neighbors to prevent an infinite loop.
	 * If a course has no neighbors, the number of courses that need to be taken is decremented by one.
	 */
	Set<Integer> set = new HashSet();
	public boolean canFinishAccepted(int n, int[][] prerequisites) {
		int [] count = new int[n];
		ArrayList<Integer> [] prereqs = new ArrayList[n];
		for(int k = 0; k < n; k++)		{
			prereqs[k] = new ArrayList();
		}
		for(int [] temp : prerequisites){
			int a = temp[0];
			int b = temp[1];
			count[a]++;
			prereqs[b].add(a);
		}
		for(int k = 0; k < count.length; k++){
			if(!set.contains(k)){
				if(count[k] == 0){
					n = DFS(count, prereqs, k, n);
				}
			}
		}
		return n == 0 ?  true : false;
	}

	public int DFS(int [] count,ArrayList<Integer> [] prereqs, int currentClass, int n)	{
		set.add(currentClass);
		n--;
		if(prereqs[currentClass].size() == 0){
			return n;
		}
		for(int neighbor : prereqs[currentClass]){
			count[neighbor]--;
			if(count[neighbor] == 0){
				if(!set.contains(neighbor))	{
					n = DFS(count, prereqs, neighbor, n);
				}
			}
		}
		return n;
	}
}
