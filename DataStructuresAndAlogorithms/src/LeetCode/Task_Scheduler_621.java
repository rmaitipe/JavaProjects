package LeetCode;

import java.util.*;

public class Task_Scheduler_621 {
    /*
     * You are given an array of CPU tasks, each labeled with a letter from A to Z, and a number n. Each CPU interval
     * can be idle or allow the completion of one task. Tasks can be completed in any order, but there's a constraint:
     * there has to be a gap of at least n intervals between two tasks with the same label.

Return the minimum number of CPU intervals required to complete all tasks.
     */
    public int leastInterval(char[] tasks, int n) {
        StringBuilder sb = new StringBuilder("");
        Node[] map = new Node [26];
        List<Node> inUse=new ArrayList<>();
        int count=tasks.length;
        for (char c:tasks){
            if (map[c-'A']==null){
                map[c-'A']=new Node();
            }
            map[c-'A'].val=c;
            map[c-'A'].frequency++;

        }
        //only valid entries
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b)-> Integer.compare(b.frequency,a.frequency));
        //PriorityQueue<Node> pq = new PriorityQueue<>((a, b)-> a.frequency!=b.frequency?Integer.compare(b.frequency,a.frequency):a.cooldown-b.cooldown);
        for (Node node: map){
            if (node !=null) {
                pq.offer(node);
            }
        }
        int curr=0;
        while (curr<count){
            if (!pq.isEmpty()){
                Node node=pq.poll();
                sb.append(node.val);
                curr++;
                node.frequency--;
                node.cooldown=n+1;
                if (node.frequency==0){
                    pq.remove(node);
                } else {
                    inUse.add(node);
                }
            } else{
                //use Interval
                sb.append(" ");
            }
            // next any non zero counter gets decremented by one
            for (int i=inUse.size()-1;i>=0;i--){
                Node nodeIn= inUse.get(i);
                nodeIn.cooldown--;
                if (nodeIn.cooldown==0){
                    inUse.remove(i);
                    if (nodeIn.frequency>0) {
                        pq.add(nodeIn);
                    }
                }
            }
            //pq.update();// update pq or does it get updated above
        }
        return sb.length();
    }
    /*
        HashMap<Character,int []> map2 = new HashMap<>();
        if (map2.containsKey(c)){
            int val[]=  map2.get(c);
            val[0]++;
            map2.put(c,val);
        }
        else{
            int [] val= new int [] {1,0};
            map2.put(c,val);
        PriorityQueue<Map.Entry<Character,int[] >> pq = new PriorityQueue<>((a, b)-> Integer.compare(b.value()[0],a.value()[0]));
     */

    class Node {
        char val;
        int cooldown;
        int frequency;

        public int getCooldown() {
            return cooldown;
        }

        public void setCooldown(int cooldown) {
            this.cooldown = cooldown;
        }

        public char getVal() {
            return val;
        }

        public void setVal(char val) {
            this.val = val;
        }

        public int getFrequency() {
            return frequency;
        }

        public void setFrequency(int frequency) {
            this.frequency = frequency;
        }
    }


    public static void main(String args[]) {
        Task_Scheduler_621 ob = new Task_Scheduler_621();
        int n=2;
        char[] tasks0 = new char []{'A','B','A'};
        System.out.println(ob.leastInterval(tasks0,n));
        char[] tasks = new char []{'A','A','A','B','B','B'};
        System.out.println(ob.leastInterval(tasks,n));
    }

    class Task {
        int freq, lastUsed = -1;
        public Task(int f) { freq = f; }
    }

    // Overall we always want to schedule the most repeated task that is available to schedule every time.
    // This greedy approach works because picking any other task will result in non optimal solution.
    public int leastIntervalAccepted(char[] tasks, int n) {
        // if n == 0 there will be no idle periods, so return length of tasks
        if(n == 0) return tasks.length;

        Map<Character, Task> map = new HashMap<>();
        for(char c: tasks) {
            map.putIfAbsent(c, new Task(0));
            map.get(c).freq++;
        }

        PriorityQueue<Task> pq = new PriorityQueue<>((x, y) -> y.freq - x.freq);

        // Use a queue to add tasks that were scheduled at the end
        // Which means tasks that are at the top of the cooling are the ones first to go out of cooling and become available for scheduling.
        Queue<Task> cooling = new LinkedList<>();

        pq.addAll(map.values());
        int count = 0;

        while(!pq.isEmpty() || !cooling.isEmpty()) {
            // if no tasks are available to schedule at current time, go idle until the first cooling task becomes available
            if(pq.isEmpty()) count = cooling.peek().lastUsed + n + 1;

            // Add any tasks in cooling that just became available for scheduling
            while(!cooling.isEmpty() && count > cooling.peek().lastUsed + n) {
                pq.add(cooling.poll());
            }

            // Schedule the most frequent occurring task by polling priority queue.
            Task t = pq.poll();
            t.lastUsed = count++;
            t.freq--;

            // Add the task back into cooling if there are more instances of it to schedule.
            if(t.freq != 0) cooling.add(t);
        }

        return count;
    }
}
