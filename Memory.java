import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Memory class for simulating OS memory management.  
 * 
 * @version     1.0 May 2018 
 * @author      Amanda Camacho,  
                Benjamin Amos   <benjamin.oxi@gmail.com>                
 */
public class Memory {
    private List<Page> pageList;
    private int pageNumber;
    private int pageSize;
    private int size;
    private int usedSpace;

    /**
     * Constructor for memory
     * 
     * @param size        Size in MB that memory will have
     * @param pageNumber  Total memory pages
     * @param blockSize   Size in MB that pages will have
     */
    public Memory(int size, int pageNumber, int blockSize){
        this.size = size;
        this.usedSpace = 0;
        this.pageNumber = pageNumber;
        this.pageSize = blockSize;
        this.pageList = new ArrayList<Page>();

        String iBlock;
        for (int i = 0; i < pageNumber; i++) {
            iBlock = "000" + i; 
            this.pageList.add(new Page(iBlock, blockSize));    
        }
    }

    /**
     * Retrieves total memory size in MB
     * 
     * @return int  Memory size in MB
     */
    public int size(){
        return this.size;
    }

    /**
     * Retrieves memory pages number
     * 
     * @return int  Memory pages number
     */
    public int pageNumber(){
        return this.pageNumber;
    }

    /**
     * Retrieves memory space used in MB
     * 
     * @return int  Memory space used in MB
     */
    public int usedSpace(){
        return this.size - this.usedSpace;
    }

    /**
     * Retrieves memory page size in MB
     * 
     * @return int Memory page size in MB
     */
    public int pageSize() {
        return this.pageSize;
    }

    /**
     * Retrieves first free memory block
     * 
     * @return Page Free memory block or null if there are not
                    any free blocks
     */
    public Page getFreeBlock(){
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
     * @param  pSize      Process size
     * @return List<Page> Free memory blocks list
     */
    public List<Page> getFreeBlocks(int pSize) {
        ArrayList<Page> pages = new ArrayList<Page>();
        
        int i = 0;                   // Index
        int pSizeLeft = pSize;       // Process size tracker 
		List<Page> pageList = pages; // Memory pages list
        Page currentPage = null;     // Page in use

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
    
}