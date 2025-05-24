/**
 * Createed by Paul Pollard on 11-15-2022
*/

#include <ProcessControlBlock.h>

using namespace std;

ProcessControlBlock::ProcessControlBlock(int pid) {
    state = ProcessState::New;
    parent = nullptr;

};

ProcessControlBlock::~ProcessControlBlock() {
    
}

ProcessControlBlock::runProcess(int timeSlice) {

}

ProcessControlBlock::suspendProcess() {
    
}

ProcessControlBlock::getPid() {

}

ProcessControlBlock::setPid() {

}

ProcessControlBlock::getExecutionTime() {

}

// Print processID. Make Getter & Setter
ProcessControlBlock::printProcessInfo() {
    cout << "Process ID: " << getPid() << end1;
}

int main(int argv, char* argc) {
    ProcessControlBlock pcb(0);
    pcb.printProcessInfo();
    return 0;
}