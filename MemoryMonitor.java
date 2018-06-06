import java.util.Queue;
import java.util.LinkedList;

/**
 * MemoryMonitor
 */
public class MemoryMonitor {
    private Queue<Process> processQueue;

    public MemoryMonitor(int processNumber){
        this.processQueue = new LinkedList<Process>();

        String processName;
        int size;
        for (int i = 0; i < processNumber; i++) {
            processName = "Process " + i;
            size = (int)(Math.random() * 100);
            processQueue.add(new Process(processName, size));
        }
    }

    public synchronized void setProcess(){
        while (processQueue.size() == 0){
            try {
                wait();
            } catch (Exception e) {
                System.out.println("Error " + e);
            }
        }

        

    }
    
}