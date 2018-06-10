/**
 * 
 * Page class for simulating page blocks in memory.
 *
 * @version 1.0 May 2018
 * @author Amanda Camacho <ajmandi94@gmail.com>, Benjamin Amos
 *         <benjamin.oxi@gmail.com>
 */
public class Page {
    private Process processInPage;
    private Integer pageId;
    private int size;
    private int usedSpace;

    /**
     * Constructor for memory page
     * 
     * @param block Block name in memory table
     * @param size  Size in MB that memory page will have
     */
    public Page(int block, int size) {
        this.pageId = block;
        this.size = size;
        this.usedSpace = 0;
        this.processInPage = null;
    }

    /**
     * Retrieves memory block id
     * 
     * @return Memory block id
     */
    public int id() {
        return this.pageId;
    }

    /**
     * Retrieves total block size in MB
     * 
     * @return Memory block size in MB
     */
    public int size() {
        return this.size;
    }

    /**
     * Retrieves block used space in MB
     * 
     * @return int Block used space in MB
     */
    public int usedSpace() {
        return this.usedSpace;
    }

    /**
     * Clears space used and removes process in block
     * 
     * @return Nothing
     */
    public void freeSpace() {
        this.usedSpace = 0;
        this.processInPage = null;
    }

    /**
     * Checks if there is a process running in this block
     * 
     * @return true if there's a process in block, otherwise false
     */
    public boolean hasProcess() {
        return (this.processInPage == null) ? false : true;
    }

    /**
     * Retrieves a PID from process in page
     * 
     * @return Process PID if there's a process running in this page, otherwise -1
     */
    public int whichProcess() {
        return this.processInPage != null ? this.processInPage.getPid() : -1;
    }

    /**
     * Retrieves process in page
     * 
     * @return Process  Process running in this page
     */
    public Process processInPage() {
        return this.processInPage;
    }

    /**
     * Allows a process to use this block of memory
     * 
     * @return Nothing
     */
    public void assignProcess(Process process, int pSize) {
        this.processInPage = process;
        this.usedSpace = pSize;
        process.addPage(this);
    }

    /**
     * Just a nice way to print memory page
     * 
     * @return String with page info
     */
    public String toString() {
        String pageName = "Page starting at: " + Integer.toString(this.pageId) + ".\n";
        String pageInfo = "Page size: " + this.size + ".\n";
        String pageInfo1 = "Page usedSpace: " + this.usedSpace + ".\n";
        String pageInfo2 = "";
        if (this.processInPage != null) {
            pageInfo2 = "Process using space: " + this.processInPage.getPid() + ".\n";
        } else {
            pageInfo2 = "Process using space: Ninguno.\n";
        }

        return pageName + pageInfo + pageInfo1 + pageInfo2;
    }
}