import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Micro{
  public static int CONST_NUM_REG_USE = 15;
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
    StringBuffer tiny_buffer = new StringBuffer();
    //Do global scope declarations
	for(Id symbol: extractor.root_scope){
			
			String s =  String.format("var %s\n", symbol.getReferenceName());
			if(symbol.getType() == "STRING"){
				s = String.format("str %s %s\n", symbol.getReferenceName(), symbol.getStrValue());
			}
			tiny_buffer.append(s);
	}
	//Do push registers and JSR main and halt
        tiny_buffer.append(ISA.push.getName() + "\n");
	for(int i = 0; i< Micro.CONST_NUM_REG_USE; i++){
		tiny_buffer.append(String.format("%s r%d\n", ISA.push.getName(), i));
	}
	tiny_buffer.append(ISA.jsr.getName() + " main\n");
	tiny_buffer.append("sys halt\n");

    for(String fn : extractor.getFullIR().keySet()){
	   	 Utils.printIR(extractor.getFullIR().get(fn));
	   	 
	   	 TinyGenerator asmgen = new TinyGenerator(extractor.getFullIR().get(fn), extractor.getSymbolTableMap());
	     tiny_buffer.insert(tiny_buffer.length(), asmgen.translate());
     }
     
     
     System.out.println(tiny_buffer);
    // Utils.printSymbolTable(extractor.root_scope);
  }
}


