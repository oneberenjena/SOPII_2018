import static java.lang.Math.ceil;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * 
 * Process class for simulating process in memory. not real process are being
 * used.
 *
 * @version 1.0 May 2018
 * @author Amanda Camacho <ajmandi94@gmail.com>, Benjamin Amos
 *         <benjamin.oxi@gmail.com>
 */
public class Process extends Thread {
    private int pid;
    private int executionTime;
    private int size;
    private String name;
    private int status;
    private int memoryTime; // Total memory time
    private int totalTime; // Total memory it needs to finish.
    private int runningTime; // Running time.
    private List<Page> pageTable;
    private int numberOfPages;

    /**
     * Constructor for process
     * 
     * @param name     Name that process will have
     * @param size     Size in MB that process will use
     * @param pageSize Size in MB that of pages
     */
    public Process(String name, int size, int pageSize) {
        this.pid = (int) (Math.random() * 10000);
        this.name = name;
        this.size = size;
        this.status = 3;
        this.memoryTime = 0;
        this.totalTime = (int) (Math.random() * 100);
        this.runningTime = 0;
        this.numberOfPages = (size % pageSize != 0) ? (int) (ceil(size / pageSize) + 1) : size / pageSize;
        this.pageTable = new ArrayList<Page>();
        new Thread(this, this.name);
    }

    /**
     * Overrided process for Thread run(). Runs the process in memory and checks for
     * update in status
     * 
     */
    public void run() {
        Boolean endTime = false;

        while (true) {
            try {
                TimeUnit.SECONDS.sleep(1);
                if (this.status == 3) {
                    totalTime--;
                    runningTime++;
                    memoryTime++;
                    if (totalTime <= 0) {
                        totalTime = 0;
                        endTime = true;
                    }
                } else if (this.status == 2 || this.status == 1) {
                    memoryTime++;
                }

                if (endTime && totalTime == 0) {
                    this.status = 0;
                    break;
                } // Same as Thread.sleep(5000);

            } catch (InterruptedException e) {
                System.out.println("interrupted.");
            }

        }
    }

    /**
     * Retrieves total memory time
     * 
     * @return int Process memory time
     */
    public int getTotalTime() {
        return this.totalTime;
    }

    /**
     * Sets process memory time
     * 
     * @param time Time that process will be in memory
     */
    public void setTotalTime(int time) {
        this.totalTime = time;
    }

    /**
     * Retrieves process time in memory so far
     * 
     * @return int Process time in memory at calling time
     */
    public int getRunningTime() {
        return this.runningTime;
    }

    /**
     * Adds to process running time
     * 
     */
    public void updateRunningTime() {
        this.runningTime++;
    }

    /**
     * Reduces process time left in memory
     * 
     */
    public void updateTotalTime() {
        this.totalTime--;
    }

    /**
     * Retrieves process id
     * 
     * @return int process id
     */

    public int getPid() {
        return this.pid;
    }

    /**
     * Retrieves process lifetime in memory
     * 
     * @return int Time that process will use memory
     */
    public int executionTime() {
        return this.executionTime;
    }

    /**
     * Retrieves process size
     * 
     * @return int Memory size in MB
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Retrieves process name
     * 
     * @return String Process name
     */
    public String name() {
        return this.name;
    }

    /**
     * Retrieves process status translated to text
     * 
     * @return String process status
     */
    public String status() {
        if (this.status == 0) {
            return "Finalizado";
        } else if (this.status == 1) {
            return "Listo";
        } else if (this.status == 2) {
            return "Bloqueado";
        } else if (this.status == 3) {
            return "Ejecucion";
        }
        return "";
    }

    /**
     * Retrieves process status integer
     * 
     * @return int Process status
     */
    public int statusInt() {
        return this.status;
    }

    /**
     * Changes process status
     * 
     * @param status Process status to be set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Synchronized method for killing process
     * 
     */
    public synchronized void killProccess() {
        if (this.status != 0) {
            this.status = 0;
            this.pageTable.clear();
        }
        return;
    }

    /**
     * Retrieves process time in memory
     * 
     * @return int Process time in memory
     */
    public int memoryTime() {
        return this.memoryTime;
    }

    /**
     * Checks if process should be in memory
     * 
     * @return true if process needs memory time, otherwise false
     */
    public boolean isInMemory() {
        return this.memoryTime > 0;
    }

    /**
     * Retrieves number of pages holding the process
     * 
     * @return int Number of pages where process is in
     */
    public int getNumberOfPages() {
        return this.numberOfPages;
    }

    /**
     * Retrieves process status integer identifier
     * 
     * @return int Process status integer
     */
    public int getStatus() {
        return status;
    }

    /**
     * Retrieves a list containing all pages that holds the process
     * 
     * @return List<Page> List of pages holding the process
     */
    public List<Page> getPageTable() {
        return this.pageTable;
    }

    /**
     * Sets process page table
     * 
     */
    public void setPageTable(List<Page> pageTable) {
        this.pageTable = pageTable;
    }

    /**
     * Adds page to process page table
     * 
     * @param page New page that will hold some size of the process
     */
    public void addPage(Page page) {
        this.pageTable.add(page);
    }

    /**
     * Sets process status integer identifier
     * 
     * @param status
     */
    public void setProcessStatus(int status) {
        this.status = status;
    }

    /**
     * Just for printing process info in a nice way
     * 
     * @return String with process info
     */
    public String toString() {
        String stringName = this.pid + " Process " + this.name + ".\n";
        String stringInfo = "In Memory: " + this.isInMemory() + ".\n";
        String stringInfo2 = "Process Status: " + this.status + "\n";
        String stringInfo3 = "Process Execution Time: " + this.executionTime + ".\n";
        String stringInfo4 = "Process Memory Time Elapsed: " + this.memoryTime + ".\n";
        String stringInfo5 = "Proccess Size: " + this.size + ".\n";

        return stringName + stringInfo + stringInfo2 + stringInfo3 + stringInfo4 + stringInfo5;
    }

    /**
     * Prints all pages that process is using
     * 
     * @return String Pages being used by process
     */
    public String printPages() {
        String pages = "";
        for (Page page : this.pageTable) {
            pages += String.valueOf(page.id()) + " ";
        }
        return pages;
    }
}