/**
 * Control Flow Graph Data Structure
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
public class ControlFlow {

    HashMap<String, BasicBlock> bbmap;
    /*
     * @constructor
     */
    public ControlFlow(){
        bbmap = new HashMap<String, BasicBlock>();
    }


    /*
     * create new basic block and add to graph
     * this assumes first node of IRList is a LABEL (?)
     */
    public void addNode(IRNode root) throws Exception{
        if(root.getLabel() == null){
            throw new Exception("Graph Failure: First IR Node is not a label");
        }
        BasicBlock new_block = new BasicBlock(root);
        bbmap.put(root.getLabel(), new_block);
    }

    /*
     * Connects BB 'A' ----> 'B'
     */
    public void linkA_B(String A, String B){
        BasicBlock nodeA = bbmap.get(A);
        BasicBlock nodeB = bbmap.get(B);

        nodeA.addOutgoing(nodeB);
    }

    public void printGraph(){

    }

    /*
     * Basic Block Class
     * contains lists of IRs
     */
    private class BasicBlock{
        private IRNode root; //first IR node in this BB
        private ArrayList<BasicBlock> outgoing;  //all outgoing edges

        public BasicBlock(IRNode r){
            outgoing = new ArrayList<BasicBlock>();
            root = r;
        }

        public IRNode getIR(){
            return root;
        }

        public Iterator<BasicBlock> getOutgoing(){
            return outgoing.iterator();
        }

        public void addOutgoing(BasicBlock outg){
            outgoing.add(outg);
        }
    }


}
