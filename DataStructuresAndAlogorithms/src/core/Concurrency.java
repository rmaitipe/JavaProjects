package core;

import static java.lang.Thread.sleep;

public class Concurrency {
    /*  create a race condition:  increment a shared variable through 2 threads 10000 times. -> solution synchronized
        create a deadlock: use 2 objects as locks in 2 threads, with reverse order in acquiring & releasing locks.
        You can synchronize using an array as monitor object, because arrays (even arrays of primitives) are objects in Java.
        Collections.synchronizedList(new ArrayList<>()); Collections.synchronizedMap(new ArrayList<>()); vs SynchronizedHashMap

        Each thread caches the value of a variable and the synchronization among threads happen only when the synchronized method
        or block is completely executed. To overcome this problem we use volatile keyword.
        Volatile keyword in Java guarantees that value will always be read from main memory instead of local cache thus solving the visibility issue.
        2043. Simple Bank System
     */

    public static Object Lock1 = new Object();
    public static Object Lock2 = new Object();
    public static Object Lock3 = new Object();
    static int counter=0;
    private static boolean stopReq;

    public static void main(String[] args) throws InterruptedException {
        ThreadDemo1 T1 = new ThreadDemo1();
        ThreadDemo2 T2 = new ThreadDemo2();
        T1.start();
        T2.start();

        /* https://stackoverflow.com/questions/43107959/java-thread-count-doesnt-work - missing joins */
        Thread T3 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                synchronized (Lock3) {
                    counter++;
                }
            }
        });

        Thread T4 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                synchronized (Lock3) {
                    counter++;
                }
            }
        });
        T3.start();
        T4.start();
        T3.join(); T4.join();
        System.out.println("counter: "+counter);

        //https://stackoverflow.com/questions/21135870/why-does-java-not-see-the-updated-value-from-another-thread
        //why volatile keyword is needed to address infinite loop
        Thread bgw = new Thread(new Runnable() {
            public void run(){
                int i = 0;
                while(!stopReq){
                    i++;
                }
                System.out.println("exit loop");
            }
        });
        bgw.start();
        sleep(1000);
        stopReq = true;

    }

    private static class ThreadDemo1 extends Thread {
        public void run() {
            synchronized (Lock1) {
                System.out.println("Thread 1: Holding lock 1...");

                try { sleep(10); }
                catch (InterruptedException e) {}
                System.out.println("Thread 1: Waiting for lock 2...");

                synchronized (Lock2) {
                    System.out.println("Thread 1: Holding lock 1 & 2...");
                }
            }
        }
    }
    private static class ThreadDemo2 extends Thread {
        public void run() {
            synchronized (Lock2) {
                System.out.println("Thread 2: Holding lock 2...");

                try { sleep(10); }
                catch (InterruptedException e) {}
                System.out.println("Thread 2: Waiting for lock 1...");

                synchronized (Lock1) {
                    System.out.println("Thread 2: Holding lock 1 & 2...");
                }
            }
        }
    }

    private static class ThreadDemo3 extends Thread {
        public void run() {
            for (int i=0;i<1000;i++) {
                counter++;
            }
        }
    }
}

