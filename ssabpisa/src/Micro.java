import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Micro{
  public static void main(String[] args) throws Exception
  {
	  
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
    	 fpe.printStackTrace();
	     System.out.println("Not accepted");
        return;
     }

     walker.walk(extractor, t);
 
	 System.out.println(";IR code");
	 
	 
	 //Generate Tiny Code
	int vardec_offsets = 0;
    StringBuffer tiny_buffer = new StringBuffer();

	for(Id symbol: extractor.root_scope){
			
			String s =  String.format("var %s\n", symbol.getReferenceName());
			if(symbol.getType() == "STRING"){
				s = String.format("str %s %s\n", symbol.getReferenceName(), symbol.getStrValue());
			}
			tiny_buffer.insert(vardec_offsets, s);
			vardec_offsets += s.length();
	}
		
     for(String fn : extractor.getFullIR().keySet()){
	   	 Utils.printIR(extractor.getFullIR().get(fn));
	   	 
	   	 TinyGenerator asmgen = new TinyGenerator(extractor.getFullIR().get(fn), extractor.getSymbolTableMap());
	     tiny_buffer.insert(tiny_buffer.length(), asmgen.translate());
     }
     
     
     System.out.println(tiny_buffer);
    // Utils.printSymbolTable(extractor.root_scope);
  }
}


