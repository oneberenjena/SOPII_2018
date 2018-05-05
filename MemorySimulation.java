import java.util.ArrayList;
import java.util.List;

/**
 * MemorySimulation
 */
public class MemorySimulation {
    private static final int _PROCESS = 10;

    public static void main(String[] args) {
        List<Process> pl = new ArrayList<Process>();

        for (int i = 0; i < _PROCESS; i++) {
            pl.add(new Process("pro" + i, 1024));
        }

        System.out.println(pl);
    }
    
}