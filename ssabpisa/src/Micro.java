/*
    This program is part of an assignment for ECE468 at Purdue University, IN.
    Copying, modifying or reusing this program may result in disciplinary actions.
    
    Copyright (C) 2014-2075 S. Sabpisal

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

*/
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Micro{
  public static int CONST_NUM_REG_USE = 4; 
  public static boolean DATAFLOW_VERBOSE = false;
  public static boolean TINYGEN_VERBOSE = true;
  
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

      DataflowBuilder ralloc = new DataflowBuilder(CONST_NUM_REG_USE);
      ralloc.setMode(ralloc.BOTTOM_UP);
      ralloc.setGlobalVars(extractor.root_scope);

//      for(String fn : extractor.getFullIR().keySet()){
//          System.out.println(";----" + fn);
//          Utils.printIR(extractor.getFullIR().get(fn));
//      }

//      System.out.println(";Register Allocated IR code");

      for(String fn : extractor.getFullIR().keySet()){
          //System.out.println(";--- enforce ---" + fn);
          Utils.printIR(ralloc.enforce(extractor.getFullIR().get(fn)));
      }

      System.out.println(";----------------- tiny ------------------------");

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
	

    //Generate the rest of the tiny
    for(String fn : extractor.getFullIR().keySet()){
	   	 //Utils.printIR(extractor.getFullIR().get(fn));

	   	 TinyGenerator asmgen = new TinyGenerator(extractor.getFullIR().get(fn), extractor.getSymbolTableMap());
	     tiny_buffer.append(asmgen.translate());
     }


     System.out.println(tiny_buffer);
    // Utils.printSymbolTable(extractor.root_scope);


  }
}


