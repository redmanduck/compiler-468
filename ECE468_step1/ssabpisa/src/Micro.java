import org.antlr.v4.runtime.*;
//import java.io.InputStream;
//import java.io.FileInputStream;

public class Micro{
  public static void main(String[] args) throws Exception
  {  
     CharStream in = new ANTLRFileStream(args[0]);
     MicroLexer lexer = new MicroLexer(in);
     String[] token_names = lexer.getTokenNames();
     //System.out.println(token_names.length);
     while(!lexer._hitEOF){
	 Token tk = lexer.nextToken();
         if(tk.getType() == -1){
           break;
	 }		
	if(token_names[tk.getType()].equals("COMMENT")){
	  continue;
	}
	System.out.format("Token Type: %s\n",token_names[tk.getType()]);
	System.out.format("Value: %s\n", tk.getText());
	 //System.out.println(tk.getType());
     }    
  }
}
