import java.util.ArrayList;

public class TinyOutputBuffer extends ArrayList<String>{

		private static final long serialVersionUID = 2648572864328128569L;
		public TinyOutputBuffer(){
			super();
		}
		
		@Override public boolean add(String k){
			super.add(k + "\n");
			if(Micro.TINYGEN_VERBOSE) System.out.println("; tiny generated = " + k.replaceAll("\n", "; "));
			return true;
		}
	}
