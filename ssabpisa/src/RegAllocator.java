import java.util.ArrayList;

public class RegAllocator {
    private int reg_limit;
    private int mode;
    public static int BOTTOM_UP = 5;
    public static int GRAPH_COLORING = 6;

    private SymbolTable symtb;

    public RegAllocator(int max_use){
        reg_limit = max_use;
        mode = BOTTOM_UP;
    }


    public void setGlobalVars(SymbolTable itable){
        symtb = itable;
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


    public void DFS(IRNode vtx){
        vtx.discovered = true;
        System.out.println(vtx.toString());
        for(IRNode w : vtx.predecessors){
            if(!w.discovered){
                DFS(w);
            }
        }
    }

    /*
     * initialize live out set to
     * all global variables
     */
    private void initLiveOutSet(IRNode nd){
        for(Id n : symtb){
            nd.LIVE_OUT.add(n.toString());
        }
    }

    /*
     * Data flow analysis
     * Iterate over each IR node updating IN and LIVE_OUT set
     * until "convergence"
     * Q: Is this across all basic blocks?
     */
    public void analyzeDataFlow(IRList original){
        //populate worklist
        ArrayList<IRNode> worklist = new ArrayList<IRNode>();
        IRNode p = null;
        for(IRNode n : original){
            worklist.add(n);
            p = n;
        }

        IRNode lnode = original.get(original.size() - 1);
        initLiveOutSet(lnode);
        //work the worklist

        do{
            IRNode n = worklist.get(worklist.size() - 1);
            boolean fixed = computeLiveness(n);
            if(!fixed){
                //keep going
                worklist.addAll(n.predecessors);
                System.out.println("Added all preds of " + n.toString() + " to work list");
            }
            worklist.remove(n);
        }while(!worklist.isEmpty());

    }

    /*
     * Compute Liveness for one IR Node (CFG)
     *
     * The set of variables that are live out of a node is the union of all the variables that are live in to the node's successors.
     * The set of variables that are live in to a node is the set of variables that are live out for the node, minus any variables
     * that are killed by the node, plus any variables that are gen-ed by the node.
     *
     * Return true if fix point (no set changed)
     */
    public boolean computeLiveness(IRNode n){

//        if(n.GEN.size() == 0 && n.KILL.size() == 0){
//            return true;
//        }

        System.out.println("Computing Liveness for " + n.toString());
        String digestLiveOut = n.LIVE_OUT.toString();
        String digestLiveIn = n.LIVE_IN.toString();
        String digestLiveOut_new = "";
        String digestLiveIn_new = "";


        //compute live out
        //union of all the variables that are live in to the node's successors.
        System.out.println("has " + n.successors.size() + " successors");
        for(IRNode suc: n.successors){
            System.out.println("successor " + suc.toString() +  " has LIVEIN = " + suc.LIVE_IN.toString());
            n.LIVE_OUT.addAll(suc.LIVE_IN);
        }
        //compute live in
        //LIVE_IN = (LIVE_OUT - KILL) U GEN
        n.LIVE_IN.addAll(n.LIVE_OUT);
        n.LIVE_IN.removeAll(n.KILL);
        n.LIVE_IN.addAll(n.GEN);

        System.out.println("KILL: " + n.KILL.toString());
        System.out.println("GEN: " + n.GEN.toString());
        digestLiveOut_new = n.LIVE_OUT.toString();
        digestLiveIn_new = n.LIVE_IN.toString();

        System.out.println("LIVE In" + digestLiveIn + " --> " + digestLiveIn_new);
        System.out.println("LIVE Out " + digestLiveOut + " --> " + digestLiveOut_new);

        if(digestLiveIn != digestLiveIn_new) return false; //something changed
        if(digestLiveOut != digestLiveOut_new) return false; //something changed

        return true; //is fixed
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
