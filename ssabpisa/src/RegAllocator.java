public class RegAllocator {
    private int reg_limit;
    private int mode;
    public static int BOTTOM_UP = 5;
    public static int GRAPH_COLORING = 6;

    public RegAllocator(int max_use){
        reg_limit = max_use;
        mode = BOTTOM_UP;
    }
    /*
     * do register allocation
     * to limit to {reg_limit} number of register
     * returns a new optimized IRList
     */
    public IRList enforce(IRList original){
        return original;
    }

    public void setMode(int v){
        mode = v;
    }


}
