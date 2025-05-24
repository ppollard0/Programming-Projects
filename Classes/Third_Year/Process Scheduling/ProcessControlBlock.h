/**
// Created by branton on 10/23/2020.
// Loosely based on the Linux Task_struct
 References:
    file:///D:/Downloads/idoc.pub_process-control-block-in-linux.pdf
*/

#ifndef CPP_PROCESSCONTROLBLOCK_H
#define CPP_PROCESSCONTROLBLOCK_H

#include <string>
#include "other.h"
// We will supply the Process class in the next assignment
#include "Process.h"

// Implement stuff
//class Process {};

// Enumerated type
enum class ProcessState {
    New, Ready, Running, Waiting, Terminated
};

class ProcessControlBlock {

private:
    /* The volatile keyword tells the compiler that this 
       variable value may change at any time. It prohibits
       some caching optimizations that could lead to problems. */
    volatile ProcessState state;    // Current state of the process
    int processId;
    // We are taking extreme liberties with the way this actually works
    Process processInstance;

    // The previous Process reference replaces most of the below.
    long counter;   // reference count (sort of)
    long priority;
    ProcessControlBlock* parent;
    long startTime, clockTime, cpuTime;
    int processor;
    int exitCode;
    // Placeholders for information the OS would need;
    // Defined in other structures -- we will not use them.
    unsigned long long pc;
    std::string comm;
    void* registerFile;
    void* stack;
    unsigned int flags;

    task_group* scheduleTaskGroup;
    sched_info schedulingInfo;
    // Normally keep a pointer to list of children and siblings
    void* children;
    void* siblings;
    void* fileSystem;
    void* files;

public:
    // no default constructor
    ProcessControlBlock(int pid);

    // getters and setters
    int getPid() {return processId;}
    void setPid(int pid) {processId = pid;}
    void setCommandLine(std::string cmd) { comm = cmd; }
    std::string getCommandLine() { return comm; }
    // Uncomment if needed
    //void setState(ProcessState newState) {state = newState;}

    int runProcess(int timeSlice);
    void suspendProcess();
    long getExecutionTime();
    void printProcessInfo();
};


#endif //CPP_PROCESSCONTROLBLOCK_H
