# Round Robin Scheduling
## *Programming Assignment 3*

### Discussion
Programming assignment three is taken from an interview question that 
sometimes shows up in job interviews.  The assignment is to write a Java program 
that uses a queue to simulate the Round-Robin Scheduling of threads.  

Traditionally programs ran as a single stream of executing instructions.  As computer 
processor hardware has developed over the years it has become possible for a single 
program to run as separately executing streams of instructions.  Each of these 
streams is called a thread and we can illustrate the advantage of using multiple 
threads with a simple example.  Consider an application that implements a graphical 
user interface, which performs some computational intensive calculation requiring 
several seconds to complete.  If our hypothetical application executes using a single 
thread, the menus and window operations such as resize are not available while the 
intensive calculation is running.   So, if we try and open a menu, the menu will 
not open until the calculation completes.   On the other hand, if we use one thread 
for the GUI operations and a second thread for the calculation, we can interact 
with the elements of the GUI while the calculation is running.

Threads do not, however, run currently.  Rather, their execution is interwoven.   
Each thread executes for a small unit of time called a time slice.  Threads 
are chosen to execute for a time slice by a component of an operating system called 
the scheduler.  How the next time slice is selected is determined by a scheduling 
algorithm.  One of the simplest scheduling algorithms is called round-robin 
scheduling and amounts to nothing more than selecting the next process to execute 
for a time slice as the next process in line.   Process are repeatedly allowed to 
execute for a time slice and then returned to the end of the line of waiting 
processes.  When a process finishes execution, it is not returned to the line 
waiting processes.  

Time slices are short.  Windows 10 uses a time slice of approximately 20 ms 
(20 milliseconds or 20/1000 th of a second).   With time slicing a program will 
look like it is executing the threads concurrently, when in fact only one thread is 
executing at a time. 

### Assignment Details

We will represent a thread as an object instantiated from a class named CSThread.  
A CS Thread object has two instance variables: one representing the execution time 
for the thread and one the thread id.  Additionally, each CSThread object has a 
constructor, and operations named doWork, and getExecTime.  The doWork operation 
does three things: 1) prints a message saying the CSThread identified by the 
thread id has executed, 2) subtracts a given time slice value from the execution 
time, and 3) prints the remaining execution time for the process.  The getExecTime 
operation returns the CSThread’s current execution time.

Create an application program that simulates round-robin scheduling of program 
threads using CSThread instances to model threads.   Your application program should 
randomly create from 10 to 20 CSThread objects.  Each CSThread object should be 
randomly assigned an execution time of between 10 and 5000 milliseconds.  It should 
use a queue to schedule each of these threads for execution following a Round-Robin 
scheduling algorithm.  Use a time slice of 20ms but code your program so the time 
slice can be easily changed.  Simulate the execution of the thread by calling the 
doWork method of a thread.  CSThread objects should be executed until their execution 
time becomes zero or less.

The program should also print summary information for the simulation.  Example 
output is given below.  Use the example to determine the details of the summary 
information you need to print. Note: Total execution time should include only the 
time a thread actually executes.   For example, if the execution time of a thread 
that completed is -6, the thread only executed 14 ms in the final time slice and 
not 20 ms.

Method signatures for the CSThread Class are:
```
    public CSThread(int tid)
    public void doWork(int timeSlice)
    public int getExecTime()
```

### Example Output
```
Simulation of Round-Robin Thread Scheduling

Number of Threads: 12
Initial Execution Times (ms)
   Maximum: 4,302  Average: 876.245  Minimum: 15

Execution Log

Executing:  T0  Time remaining after execution: 1234 ms
Executing:  T1  Time remaining after execution: -5 ms (completed)
Executing:  T2  Time remaining after execution: 531 ms
Executing:  T3  Time remaining after execution: 4282 ms
...

Total Execution Time: 17,520 ms
```

#### Notes.
1.  The statistics for the initial execution times should be calculated from the
initial set of execution times that are randomly generated for the processes.
2.  The average initial execution time is a double.
3.  The total execution time should be accumulated by adding the time a process 
executes in each time slice, which will be 20 ms unless the process ends in that 
time slice.

### Assignment Submission
Push your completed project to your GitHub repo for this assignment.  Hint:  Push your work often.  That way you have a running log of the work you have done and protecting your work from accidental loss. 
