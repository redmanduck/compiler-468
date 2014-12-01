public class RegAllocator {
    private int reg_limit;
    private int mode;
    public static int BOTTOM_UP = 5;
    public static int GRAPH_COLORING = 6;

    public RegAllocator(int max_use){
        reg_limit = max_use;
        mode = BOTTOM_UP;
    }

    private void buildCFG(IRList original){
        int i = 0;
        for(IRNode m : original){
            if(m.getInstruction().equals(ISA.JUMP)){
                //there can only be one outgoing edge
                IRNode suc = original.getLabelMap().get(m.getJumpTarget());
                m.successors.add(suc);
                suc.predecessors.add(m);
            }else if(m.getInstruction().isConditionalJump()){
                //there are two edges out
                IRNode fallthru = original.get(i+1);
                IRNode taken = original.getLabelMap().get(m.getJumpTarget());
                m.successors.add(fallthru);
                m.successors.add(taken);
                taken.predecessors.add(m);
                fallthru.predecessors.add(m);
            }
            i++;
        }
    }

    /*
     * do register allocation
     * to limit to {reg_limit} number of register
     * returns a new optimized IRList
     */
    public IRList enforce(IRList original){
        buildCFG(original);
        return original;
    }

    public void setMode(int v){
        mode = v;
    }


}
