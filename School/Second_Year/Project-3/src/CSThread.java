/**
 * Threads that have an execution time and are Identified by their thread ID
 *
 * @author Paul Pollard
 * @version 4-17-2022
 */

public class CSThread {
    private int execTime;
    private int threadId;

    /**
     * Constructor
     * @param execuTime
     * @param id
     */
    CSThread(int execuTime, int id){
        execTime = execuTime;
        threadId = id;
    }

    /**
     * 1) prints a message saying the CSThread identified by the thread id has executed,
     * 2) subtracts a given time slice value from the execution time, and
     * 3) prints the remaining execution time for the process
     * @param timeSlice
     */
    public void doWork(int timeSlice){
        execTime = execTime - timeSlice;
        if (execTime > 0) {
            System.out.println("Executing: T" + threadId + " Time Remaining after Exexution: " + execTime + "ms");
        }
        else {
            System.out.println("Executing: T" + threadId + " Time Remaining after Exexution: " + execTime + "ms (completed)");
        }
    }
    //Returns the thread's execution time.
    public int getExecTime(){
        return this.execTime;
    }

}

