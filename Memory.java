import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

/**
 * 
 * Memory class for simulating OS memory management.
 * 
 * @version 1.0 May 2018
 * @author Amanda Camacho, Benjamin Amos <benjamin.oxi@gmail.com>
 */
public class Memory extends Thread {
    private ArrayList<Page> pageList;
    private int pageNumber;
    private int pageSize;
    private int memorySize;
    private int usedSpace;
    private Page inMemory[];
    private int dispPages;
    private Queue<Process> waitingProcess;
    private MemoryInterface memoryInterface;
    /**
     * Constructor for memory
     * 
     * @param size       Size in MB that memory will have
     * @param pageNumber Total memory pages
     * @param blockSize  Size in MB that pages will have
     */

    public Memory(int size, int pageNumber, int blockSize, MemoryInterface memoryInterface) {
        this.memorySize = size;
        this.usedSpace = 0;
        this.pageNumber = pageNumber;
        this.pageSize = blockSize;
        this.pageList = new ArrayList<Page>();
        this.dispPages = pageNumber;
        this.waitingProcess = new LinkedList<Process>();
        int iBlock;
        for (int i = 0; i < pageNumber; i++) {
            iBlock = i;
            this.pageList.add(new Page(iBlock, blockSize));
        }
        this.memoryInterface = memoryInterface;
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

    /*
    */

    public synchronized void addProcess(Process process) {
        int pagesToUse = process.getNumberOfPages();
        int processSize = process.getSize();

        if (this.dispPages >= pagesToUse) {
            // Si requiere una sola pagina
            if (pagesToUse == 1) {
                Page freePage = this.getFreeBlock();
                freePage.assignProcess(process, processSize);
                this.auxUsedMemory(this.pageSize);
                this.dispPages--;
                // this.memoryInterface.addCountProcess();
                process.start();
                // Thread newThread = new Thread(this, process.name());
                // newThread.run();
                // Si requiere mas de una pagina
            } else if (pagesToUse > 1) {
                List<Page> freePages = getFreeBlocks(processSize);
                int processSizeLeft = processSize;
                for (Page freePage : freePages) {
                    freePage.assignProcess(process, processSizeLeft);
                    this.auxUsedMemory(this.pageSize);
                    processSizeLeft -= freePage.size();
                    this.dispPages--;
                }
                // this.memoryInterface.addCountProcess();
                process.start();
                // new Thread(this, process.name());

            }
        } else {
            // Coloco los procesos en una cola de espera
            this.waitingProcess.add(process);
        }

    }

    public void run() {
        System.out.println("Iniciando hilo de memoria..");
        System.out.println(this.memoryInterface);
        while (true) {
            for (Page page : this.pageList) {
                if (page.hasProcess() && page.processInPage().getStatus() == 0) {
                    this.removeProcessFromInterface(page.processInPage());
                }
            }

            if (!this.waitingProcess.isEmpty()) {
                Process head = this.waitingProcess.peek();
                if (head.getSize() <= this.usedSpace) {
                    this.addProcess(this.waitingProcess.poll());
                } else {
                    this.waitingProcess.add(this.waitingProcess.poll());
                }
                // this.memoryInterface.update();
            }
            
        }
    }

    public synchronized void removeProcessFromInterface(Process process) {
        System.out.format("El proceso %d termino su ejecucion. Eliminando..", process.getPid());
        this.memoryInterface.reduceCountProcess(process);
        this.killProcess(process.getPid());
        System.out.println("Eliminado.");
        this.memoryInterface.update();
    }

    /*
    */
    public int getUsedPages() {
        return this.pageNumber - this.dispPages;
    }

    // funcion auxiliar para hacer pruebas
    public void auxUsedMemory(int newUsedSpace) {
        this.usedSpace += newUsedSpace;
    }

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

    public ArrayList<Page> getInMemory() {
        return this.pageList;
    }

    public synchronized boolean killProcess(int pid) {
        Process toKill;
        boolean wasInMemory = false;
        System.out.format("Eliminando proceso con PID:%d%n", pid);
        for (Page page : this.pageList) {
            if (page.whichProcess() == pid) {
                System.out.format("Eliminando de la pagina con ID:%d%n", page.id());
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