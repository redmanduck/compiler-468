import java.util.ArrayList;

public class DataflowBuilder {
	private int reg_limit;
	private int mode;
	public static int BOTTOM_UP = 5;
	public static int GRAPH_COLORING = 6;

	public int count_max_temporaries;
	
	private SymbolTable symtb;

	public DataflowBuilder(int max_use) {
		reg_limit = max_use;
		mode = BOTTOM_UP;
		count_max_temporaries = 0;
	}

	public void setGlobalVars(SymbolTable itable) {
		symtb = itable;
	}

	/*
	 * Fill successors and predecssors for each IR
	 */
	private void buildCFG(IRList original) {
		int i = 0;
		for (IRNode m : original) {
			if (m.getInstruction().equals(ISA.JUMP)) {
				// there can only be one outgoing edge
				IRNode suc = original.getLabelMap().get(m.getJumpTarget());
				m.successors.add(suc);
				suc.predecessors.add(m);
			} else if (m.getInstruction().isConditionalJump()) {
				// there are two edges out
				IRNode fallthru = original.get(i + 1);
				IRNode taken = original.getLabelMap().get(m.getJumpTarget());
				m.successors.add(fallthru);
				m.successors.add(taken);
				taken.predecessors.add(m);
				fallthru.predecessors.add(m);
			} else if ((i + 1) < original.size()) {
				// return node doesnt have any successor
				IRNode k = original.get(i + 1);
				m.successors.add(k);
				k.predecessors.add(m);
			}
			i++;
		}
	}

	public void DFS(IRNode vtx) {
		vtx.discovered = true;
		System.out.println(vtx.toString());
		for (IRNode w : vtx.predecessors) {
			if (!w.discovered) {
				DFS(w);
			}
		}
	}

	/*
	 * initialize live out set to all global variables
	 */
	private void initLiveOutSet(IRNode nd) {
		for (Id n : symtb) {
			nd.LIVE_OUT.add(n.toString());
		}
	}

	/*
	 * Data flow analysis Iterate over each IR node updating IN and LIVE_OUT set
	 * until "convergence" Q: Is this across all basic blocks?
	 */
	public void analyzeDataFlow(IRList original) {
		// populate worklist
		ArrayList<IRNode> worklist = new ArrayList<IRNode>();
		IRNode p = null;
		for (IRNode n : original) {
			worklist.add(n);
			p = n;
		}

		IRNode lnode = original.get(original.size() - 1);
		initLiveOutSet(lnode);
		// work the worklist

		do {
			IRNode n = worklist.get(worklist.size() - 1);
			boolean fixed = computeLiveness(n);
			if (!fixed) {
				// keep going
				worklist.addAll(n.predecessors);
				if(Micro.DATAFLOW_VERBOSE) System.out.println(";Added all preds of " + n.toString()
						+ " to work list");
			}
			worklist.remove(n);
		} while (!worklist.isEmpty());

	}

	/*
	 * Compute Liveness for one IR Node (CFG)
	 * 
	 * The set of variables that are live out of a node is the union of all the
	 * variables that are live in to the node's successors. The set of variables
	 * that are live in to a node is the set of variables that are live out for
	 * the node, minus any variables that are killed by the node, plus any
	 * variables that are gen-ed by the node.
	 * 
	 * Return true if fix point (no set changed)
	 */
	public boolean computeLiveness(IRNode n) {

		// if(n.GEN.size() == 0 && n.KILL.size() == 0){
		// return true;
		// }

		if (Micro.DATAFLOW_VERBOSE)
			System.out.println(";=======Computing Liveness for " + n.toString()
					+ " ============");
		String digestLiveOut = n.LIVE_OUT.toString();
		String digestLiveIn = n.LIVE_IN.toString();
		String digestLiveOut_new = "";
		String digestLiveIn_new = "";

		// compute live out
		// union of all the variables that are live in to the node's successors.
		if (Micro.DATAFLOW_VERBOSE)
			System.out.println(";Has " + n.successors.size() + " successors");
		for (IRNode suc : n.successors) {
			if(Micro.DATAFLOW_VERBOSE)  System.out.println(";successor " + suc.toString()
					+ " has LIVEIN = " + suc.LIVE_IN.toString());
			n.LIVE_OUT.addAll(suc.LIVE_IN);
		}
		// compute live in
		// LIVE_IN = (LIVE_OUT - KILL) U GEN
		n.LIVE_IN.addAll(n.LIVE_OUT);
		n.LIVE_IN.removeAll(n.KILL);
		n.LIVE_IN.addAll(n.GEN);

		if (Micro.DATAFLOW_VERBOSE)
			System.out.println(";KILL set: " + n.KILL.toString());
		if (Micro.DATAFLOW_VERBOSE)
			System.out.println(";GEN set: " + n.GEN.toString());
		
		digestLiveOut_new = n.LIVE_OUT.toString();
		digestLiveIn_new = n.LIVE_IN.toString();

		if (Micro.DATAFLOW_VERBOSE)
			System.out.println(";LIVE In set " + digestLiveIn + " --> "
					+ digestLiveIn_new);
		if (Micro.DATAFLOW_VERBOSE)
			System.out.println(";LIVE Out set" + digestLiveOut + " --> "
					+ digestLiveOut_new);

		if (!digestLiveIn.equals(digestLiveIn_new)
				|| !digestLiveOut.equals(digestLiveOut_new)) {
			return false; // something changed
		}

		return true; // is fixed
	}

	/*
	 * do register allocation to limit to {reg_limit} number of register returns
	 * a new optimized IRList
	 */
	public IRList enforce(IRList original) {
		buildCFG(original);
		analyzeDataFlow(original);
		analyzeBB(original);
		return original;
	}



	public void analyzeBB(IRList original){
		IRNode p = null;
		for(IRNode n : original){
			if(Utils.nodeIsLeader(n)){
				p.markEndBB();
			}
			p = n;
		}
	}

	public void setMode(int v) {
		mode = v;
	}

}
