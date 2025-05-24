import java.util.ArrayDeque;
import java.util.Random;

/**
 * Simulation for a Round Robbin Scheduling where threads are pulled from a queue to do work for a specific amount of time
 * and then are re-added to the queue
 *
 * @author Paul Pollard
 * @version 4-17-2022
 */
public class RoundRobinApp {
    private static int timeSlice = 20;
    private ArrayDeque<CSThread> threadDeque;
    private int numberOfThreads;
    private int totalExecutionTime;
    private Random rand;

    /**
     * Constructor
     */
    public RoundRobinApp() {
        rand = new Random();
        //Thread amounts will vary from 10-20 threads.
        numberOfThreads = rand.nextInt(10) + 10;
        threadDeque = new ArrayDeque(numberOfThreads);
    }

    public double run() {
        totalExecutionTime = 0;
        int max = 0;
        int min = 5000;
        System.out.println("Number of Threads: " + numberOfThreads);
        createThreads();
        for (int i=0; i != numberOfThreads && i <= numberOfThreads; i++) {
            CSThread thread = threadDeque.removeFirst();
            int threadTime = thread.getExecTime();
            totalExecutionTime = totalExecutionTime + threadTime;
            threadDeque.addLast(thread);
            if (threadTime > max) {
                max = threadTime;
            }
            else if (threadTime < min) {
                min = threadTime;
            }
        }
        int avg = totalExecutionTime / numberOfThreads;
        System.out.println("Maximum: " + max + " Average: " + avg + " Minimum: " + min + "\n");
        printStats();
        // the queue until the queue is empty.
        while (!threadDeque.isEmpty()) {
            threadDeque.removeFirst().doWork(timeSlice);
        }
        // Then report the results;
        System.out.println("\nTotal Execution Time: " + totalExecutionTime + "ms");
        return totalExecutionTime;
    }



    public void createThreads() {
        System.out.println("Initial Execution Times (ms)");
        //For every thread created, it will assign an execution value
        //from 10-5000 ms. It will also assign a thread id.
        for (int i = 0; i < numberOfThreads; i++) {
            int execTime = rand.nextInt(4990) + 10;
            int threadID = i;
            //Adds every thread to the deque
            threadDeque.addLast(new CSThread(execTime, threadID));
        }
    }

    /**
     * Prints the statistics for the threads.
     * Note:  These should be calculated before the threads are
     *        executed. See run method for where this mehtod is
     *        called.
     */
    public void printStats() {
        System.out.println("Execution Log\n");
    }


    public static void main(String[] args) {
        System.out.println("Simulation of Round-Robin Thread Scheduling\n");
        RoundRobinApp roundRobin = new RoundRobinApp();
        double execTime = roundRobin.run();
    }
}
