import org.antlr.v4.runtime.*;
//import java.util.Arrays;

//import java.io.InputStream;
//import java.io.FileInputStream;
import org.antlr.v4.runtime.tree.*;
public class Micro{
  public static void main(String[] args) throws Exception
  {  
     System.setErr(null);
     CharStream in = new ANTLRFileStream(args[0]);
     MicroLexer lexer = new MicroLexer(in);
     CommonTokenStream tks = new CommonTokenStream(lexer);
     MicroParser psr = new MicroParser(tks);    
     psr.setErrorHandler(new BailErrorStrategy());
     try{
       ParseTree t = psr.program();
     } catch (Exception fpe) {
//	System.out.println(Arrays.toString(fpe.getStackTrace()));
	System.out.println("Not accepted");
        return;
     }
    
     System.out.println("Accepted");
  }
}
