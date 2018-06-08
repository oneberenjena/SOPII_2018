import static java.lang.Math.ceil;
import java.util.List;
import java.util.ArrayList;

/**
 * 
 * Process class for simulating process in memory. not real process are being
 * used.
 *
 * @version 1.0 May 2018
 * @author Amanda Camacho, Benjamin Amos <benjamin.oxi@gmail.com>
 */
public class Process {
    private int pid;
    private int executionTime;
    private int size;
    private String name;
    private int status;
    private int memoryTime; // Tiempo que lleva en la memoria
    private int totalTime; // Tiempo total que debe estar en la memoria
    private int runningTime; // Tiempo que lleva corriendo.
    // private Page pageTable[];
    private List<Page> pageTable;
    private int numberOfPages;
    private int realSize;

    /**
     * Constructor for process
     * 
     * @param name Name that process will have
     * @param size Size in MB that process will use
     */
    public Process(String name, int size, int pageSize) {
        // this.pid = (int) (Math.random() * 100);
        this.pid = (int) (Math.random() * 10000);
        this.name = name;
        this.size = size;
        this.status = 3;
        this.memoryTime = 0;
        // this.totalTime = 100000;
        this.totalTime = (int) (Math.random() * 1000000000);
        this.runningTime = 0;
        this.numberOfPages = (size % pageSize != 0) ? (int) (ceil(size / pageSize) + 1) : size / pageSize; // this.numberPages(size,pageSize);
        this.realSize = numberOfPages * pageSize;
        // this.pageTable = new Page[this.numberOfPages];
        this.pageTable = new ArrayList<Page>();
    }




    public void runningProcess(){
        while (true) {
            totalTime -- ;
            runningTime ++;
            if (totalTime <= 0){
                totalTime = 0;
            }
        }
    }

    public int getTotalTime() {
        return this.totalTime;
    }

    /**
     * cantidad de paginas usadas por el proceso
     */

    private int numberPages(int size, int pageSize) {
        if (size % pageSize != 0)
            return (int) (ceil(size / pageSize) + 1);
        return size / pageSize;
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
    public String getName() {
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
        }  else if (this.status == 3) {
            return "Ejecucion";
        }
        return "";
    }

    public int statusInt(){
        return this.status;
    }

    public void setStatus(int status){
        this.status = status;
    }

    public void killProccess(){
        if (this.status != 0) {
            this.status =  0;
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
     */
    public int getNumberOfPages() {
        return this.numberOfPages;
    }

    /**
     */
    public int getStatus() {
        return status;
    }

    // public Page[] getPageTable() {
    public List<Page> getPageTable() {
        return this.pageTable;
    }

    public void setPageTable(List<Page> pageTable) {
        this.pageTable = pageTable;
    }

    public void addPage(Page page) {
        this.pageTable.add(page);
    }

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

    public String printPages() {
        String pages = "";
        for (Page page : this.pageTable) {
            pages += String.valueOf(page.id()) + " ";
        }
        return pages;
    }
}