/**
 * 
 * Page class for simulating page blocks in memory. 
 *
 * @version     1.0 May 2018
 * @author      Amanda Camacho, 
 *               Benjamin Amos <benjamin.oxi@gmail.com>
 */
public class Page {
    private String id;
    private int size;
    private int usedSpace;
    private Process processInPage;

    /**
     * Constructor for memory page
     * 
     * @param block      Block name in memory table
     * @param size       Size in MB that memory page will have
     */
    public Page(String block, int size){
        this.id = "0x" + block;
        this.size = size;
        this.usedSpace = 0;
        this.processInPage = null;
    }

    /**
     * Retrieves memory block id
     * 
     * @return Memory block id
     */
    public String id(){
        return this.id;
    } 

    /**
     * Retrieves total block size in MB
     * 
     * @return Memory block size in MB
     */
    public int size(){
        return this.size;
    }

    /**
     * Retrieves block used space in MB
     * 
     * @return int Block used space in MB
     */
    public int usedSpace(){
        return this.usedSpace;
    }

    /**
     * Clears space used and removes process in block
     * 
     * @return Nothing
     */
    public void freeSpace(){
        this.usedSpace = 0;
        this.processInPage = null;
    }

    /**
     * Checks if there is a process running in this block
     * 
     * @return true if there's a process in block, otherwise false
     */
    public boolean hasProcess(){
        return (this.processInPage == null) ? false : true;
    }

    /**
     * Allows a process to use this block of memory
     * 
     * @return Nothing
     */
    public void assignProcess(Process process, int pSize){
        this.processInPage = process;
        this.usedSpace = this.size - pSize;
    }

    /**
     * Just a nice way to print memory page
     * 
     * @return String with page info
     */
    public String toString(){
        String pageName = "Page starting at: " + this.id + ".\n";
        String pageInfo = "Page size: " + this.size + ".\n";
        String pageInfo1 = "Page usedSpace: " + this.usedSpace + ".\n";
        String pageInfo2 = "Process using space: " + this.processInPage.PID() + ".\n";
        
        return pageName + pageInfo + pageInfo1 + pageInfo2;
    }
}