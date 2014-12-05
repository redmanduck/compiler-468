import java.util.HashSet;

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
     * Fill successors and predecssors for each IR
     */
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
            }else if( (i+1) < original.size()){
                //return node doesnt have any successor
                IRNode k = original.get(i + 1);
                m.successors.add(k);
                k.predecessors.add(m);
            }
            i++;
        }
    }

    /*
     * Data flow analysis
     * Iterate over each IR node updating IN and LIVE_OUT set
     * until "convergence"
     * Q: Is this across all basic blocks?
     */
    public void analyzeDataFlow(IRList original){
        System.out.println(";====ANALYZING DATAFLOW=====");
        //populate worklist
        HashSet<IRNode> worklist = new HashSet<IRNode>();
        IRNode p = null;
        for(IRNode n : original){
            worklist.add(n);
            n.prev = p;
            p = n;
        }
        //traverse CFG upward
        IRNode node = original.get(original.size() - 1);
        do{
            for(IRNode pred : node.predecessors){
                computeGenKillSet(pred);
            }
            worklist.remove(node);
            if(node.predecessors.size() == 0){
                break;
            }
            node = node.prev;
        }while(!worklist.isEmpty());

        System.out.println(";====END ANALYSIS OF DATAFLOW=====");

    }

    public void computeGenKillSet(IRNode node){
        System.out.println("Computing GEN KILL Set for " + node.toString());
    }

    /*
     * Do bottom up register allocation
     */
    public void bottomUp(){

    }

    /*
     * do register allocation
     * to limit to {reg_limit} number of register
     * returns a new optimized IRList
     */
    public IRList enforce(IRList original){
        buildCFG(original);
        analyzeDataFlow(original);
        return original;
    }

    public void setMode(int v){
        mode = v;
    }


}
