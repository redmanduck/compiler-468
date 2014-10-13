import java.util.ArrayList;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Micro{
  public static void main(String[] args) throws Exception
  {
	 ArrayList<IRNode> irlist = new ArrayList<IRNode>();
	  
     CharStream in = new ANTLRFileStream(args[0]);
     MicroLexer lexer = new MicroLexer(in);
     CommonTokenStream tks = new CommonTokenStream(lexer);
     MicroParser psr = new MicroParser(tks);
     ParseTreeWalker walker = new ParseTreeWalker();
     ExtractionListener extractor = new ExtractionListener(psr);
     psr.setErrorHandler(new BailErrorStrategy());
     ParseTree t;

     try{
        t = psr.program();
     } catch (Exception fpe) {
	      System.out.println("Not accepted");
        return;
     }

     walker.walk(extractor, t);
   	 //Utils.printSymbolTable(extractor.getRootSymbolTable());
   	 Utils.printIR(extractor.getIRList());
  }
}

