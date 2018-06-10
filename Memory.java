import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import javax.swing.JTextArea;

/**
 * 
 * Memory class for simulating OS memory management.
 * 
 * @version 1.0 May 2018
 * @author Amanda Camacho <ajmandi94@gmail.com>, Benjamin Amos
 *         <benjamin.oxi@gmail.com>
 */
public class Memory extends Thread {
    private ArrayList<Page> pageList;
    private int pageNumber;
    private int pageSize;
    private int memorySize;
    private int usedSpace;
    private Page inMemory[];
    private int dispPages;
    private LinkedList<Process> waitingProcess;
    private MemoryInterface memoryInterface;
    private JTextArea alertArea;

    /**
     * Constructor for memory
     * 
     * @param size            Size in MB that memory will have
     * @param pageNumber      Total memory pages
     * @param pageSize        Size in MB that pages will have
     * @param memoryInterface Memory interface instance for updating
     * @param alertArea       Alert area instance at interface for logs
     */

    public Memory(int size, int pageNumber, int pageSize, MemoryInterface memoryInterface, JTextArea alertArea) {
        this.memorySize = size;
        this.usedSpace = 0;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.pageList = new ArrayList<Page>();
        this.dispPages = pageNumber;
        this.waitingProcess = new LinkedList<Process>();
        int iBlock;
        for (int i = 0; i < pageNumber; i++) {
            iBlock = i;
            this.pageList.add(new Page(iBlock, pageSize));
        }
        this.memoryInterface = memoryInterface;
        this.alertArea = alertArea;
        new Thread(this, "Memory");
    }

    /**
     * Retrieves total memory size in MB
     * 
     * @return int Memory size in MB
     */
    public int getMemorySize() {
        return this.memorySize;
    }

    /**
     * Retrieves memory pages number
     * 
     * @return int Memory pages number
     */
    public int getPageNumber() {
        return this.pageNumber;
    }

    /**
     * Retrieves memory space used in MB
     * 
     * @return int Memory space used in MB
     */
    public int getUsedSpace() {
        return this.usedSpace;
    }

    /**
     * Synchronized method for adding a new process in memory
     * 
     * @param process Process to add in memory
     */
    public synchronized void addProcess(Process process) {
        int pagesToUse = process.getNumberOfPages();
        int processSize = process.getSize();
        System.out.format("Process will be using %d page(s) from %d pages available.%n", pagesToUse, this.dispPages);
        if (this.dispPages >= pagesToUse) {
            // If page size satisfies process requeriments
            if (pagesToUse == 1) {
                Page freePage = this.getFreeBlock();
                freePage.assignProcess(process, processSize);
                this.auxUsedMemory(this.pageSize);
                this.dispPages--;
                process.start();
                // If a single page is not enough
            } else if (pagesToUse > 1) {
                List<Page> freePages = getFreeBlocks(processSize);
                int processSizeLeft = processSize;
                for (Page freePage : freePages) {
                    freePage.assignProcess(process, processSizeLeft);
                    this.auxUsedMemory(this.pageSize);
                    processSizeLeft -= freePage.size();
                    this.dispPages--;
                }
                process.start();
            }
        }
    }

    /**
     * Queue a process into ready list
     * 
     * @param process   Process to be queued
     */
    public void queueProcess(Process process) {
        System.out.format("Queueing process with PID %d now.%n", process.getPid());
        process.setStatus(1);
        this.waitingProcess.add(process);
    }

    /**
     * Ready queue check. If there are enough space and pages to satisfy
     *  head process at queue, it will be dequeued and executed. If not,
     *  it will check if there are any process in queue that can be satisfied
     *  with current availability. If not, returns null.
     * 
     * @return Process Process to be executed
     */
    public Process checkWaitingQueue() {
        if (!this.waitingProcess.isEmpty()) {

            Process pro = this.waitingProcess.peek();
            if (this.dispPages >= pro.getNumberOfPages() && pro.getSize() <= this.getfreeMemory()) {
                return this.waitingProcess.poll();
            } else {
                for (Process process : this.waitingProcess) {
                    if (this.dispPages >= process.getNumberOfPages() && process.getSize() <= this.getfreeMemory()) {
                        System.out.format("Dequeueing process %s needing %d pages from %d availables.%n",
                                process.name(), process.getNumberOfPages(), this.dispPages);
                        int index = this.waitingProcess.indexOf(process);
                        return this.waitingProcess.remove(index);
                    }
                }
            }
        }

        return null;
    }

    /**
     * Prepares a process to be executed from ready queue and
     *  changes its status to execution.
     * 
     */
    public synchronized void executeWaitingProcess() {
        Process process = this.checkWaitingQueue();
        if (process != null) {
            process.setStatus(3);
            this.addProcess(process);
            this.memoryInterface.addProcessFromQueue(process);
        }
    }

    /**
     * Overrided process for Thread run(). Simulates physical memory by removing
     * done process and freeing space, and adding process in queue.
     * 
     */
    public void run() {
        System.out.println("Starting memory thread..");
        while (true) {
            for (Page page : this.pageList) {
                if (page.hasProcess() && page.processInPage().getStatus() == 0) {
                    this.removeProcessFromInterface(page.processInPage());
                    this.executeWaitingProcess();
                }
            }

            // if (!this.waitingProcess.isEmpty()) {
            // Process head = this.waitingProcess.peek();
            // if (head.getSize() <= this.usedSpace) {
            // this.addProcess(this.waitingProcess.poll());
            // } else {
            // this.waitingProcess.add(this.waitingProcess.poll());
            // }
            // }
        }
    }

    /**
     * Synchronized method for removing a process from interface
     * 
     * @param process Process to remove from interface and get killed
     */
    public synchronized void removeProcessFromInterface(Process process) {
        this.alertArea.append("El proceso " + Integer.toString(process.getPid()) + " termino su ejecucion\n");
        System.out.format("Process with PID %d finished its execution. Removing from table..", process.getPid());
        this.memoryInterface.reduceCountProcess(process);
        this.killProcess(process.getPid());
        System.out.println("Process removed.");
        this.memoryInterface.update();
    }

    /**
     * Retrieves number of used pages
     * 
     * @return int Number of used pages
     */
    public int getUsedPages() {
        return this.pageNumber - this.dispPages;
    }

    /**
     * Sets new used space in memory
     * 
     * @param newUsedSpace Size of memory in MB that will be used
     */
    public void auxUsedMemory(int newUsedSpace) {
        this.usedSpace += newUsedSpace;
    }

    /**
     * Clear used space in memory
     * 
     * @param used Size of memory in MB that will be freed
     */
    public void freeSpace(int used) {
        this.usedSpace -= used;
    }

    /**
     * Retrieves memory space free in MB
     * 
     * @return int Memory space free in MB
     */
    public int getfreeMemory() {
        return this.memorySize - this.usedSpace;
    }

    /**
     * Retrieves memory page size in MB
     * 
     * @return int Memory page size in MB
     */
    public int getPageSize() {
        return this.pageSize;
    }

    /**
     * Retrieves first free memory block
     * 
     * @return Page Free memory block or null if there are not any free blocks
     */
    public Page getFreeBlock() {
        Page currentPage = null;
        for (int i = 0; i < this.pageNumber; i++) {
            currentPage = this.pageList.get(i);
            if (!currentPage.hasProcess()) {
                return currentPage;
            }
        }

        return null;
    }

    /**
     * Retrieves list of free blocks that fits process
     * 
     * size in MB requirements
     * 
     * @param pSize Process size
     * @return List<Page> Free memory blocks list
     */
    public List<Page> getFreeBlocks(int pSize) {
        ArrayList<Page> pages = new ArrayList<Page>();

        int i = 0; // Index
        int pSizeLeft = pSize; // Process size tracker
        List<Page> pageList = pages; // Memory pages list
        Page currentPage = null; // Page in use

        while (pSizeLeft > 0) {
            currentPage = this.pageList.get(i);
            if (!currentPage.hasProcess()) {
                pageList.add(currentPage);
                pSizeLeft -= currentPage.size();
            }
            i++;
        }
        return pageList;
    }

    /**
     * Retrieves all pages in memory
     * 
     * @return ArrayList<Page> Total pages in memory
     */
    public ArrayList<Page> getInMemory() {
        return this.pageList;
    }

    /**
     * Synchronized method for killing a process
     * 
     * @param pid PID from process to be killed
     */
    public synchronized boolean killProcess(int pid) {
        Process toKill;
        boolean wasInMemory = false;
        for (Page page : this.pageList) {
            if (page.whichProcess() == pid) {
                toKill = page.processInPage();
                toKill.killProccess();
                page.freeSpace();
                this.dispPages++;
                this.freeSpace(this.pageSize);
                wasInMemory = true;
            }
        }
        return wasInMemory;
    }
}