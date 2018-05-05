/**
 * 
 * Process class for simulating process in memory.
 *  not real process are being used.
 *
 * @version     1.0 May 2018 
 * @author      Amanda Camacho,
                Benjamin Amos <benjamin.oxi@gmail.com>
 */
public class Process {
    private int pid;
    private int executionTime;
    private int size;
    private String name;
    private int status;
    private int memoryTime;
    
    /**
     * Constructor for process
     * 
     * @param name       Name that process will have
     * @param size       Size in MB that process will use
     */
    public Process(String name, int size){
        this.pid = (int) (Math.random() * 100);
        this.pid = (int) (Math.random() * 10000);
        this.name = name;
        this.size = size;
        this.status = 0; 
        this.memoryTime = 0;
    }

    /**
     * Retrieves process id
     * 
     * @return int process id
     */
    public int PID(){
        return this.pid;
    }

    /**
     * Retrieves process lifetime in memory
     * 
     * @return int Time that process will use memory
     */
    public int executionTime(){
        return this.executionTime;
    }

    /**
     * Retrieves process size
     * 
     * @return int Memory size in MB
     */
    public int size(){
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
        if (this.status == 1){
            return "Listo";
        } else if (this.status == 2){
            return "Bloqueado";
        } else if (this.status == 3){
            return "Bloqueado/Listo";
        } else if (this.status == 0){
            return "Finalizado";
        }
        return "";
    }

    /**
     * Retrieves process time in memory
     * 
     * @return int Process time in memory
     */
    public int memoryTime(){
        return this.memoryTime;
    }

    /**
     * Checks if process should be in memory
     * 
     * @return true if process needs memory time, otherwise false
     */
    public boolean isInMemory(){
        return this.memoryTime > 0; 
    }

    /**
     * Just for printing process info in a nice way
     * 
     * @return String with process info
     */
    public String toString(){
        String stringName = this.pid + " Process " + this.name + ".\n";
        String stringInfo = "In Memory: " + this.isInMemory() + ".\n";
        String stringInfo2 = "Process Status: " + this.status + "\n";
        String stringInfo3 = "Process Execution Time: " + this.executionTime + ".\n";
        String stringInfo4 = "Process Memory Time Elapsed: " + this.memoryTime + ".\n";
        String stringInfo5 = "Proccess Size: " + this.size + ".\n";

        return stringName + stringInfo + stringInfo2 + stringInfo3 + stringInfo4 + stringInfo5;
    }
}