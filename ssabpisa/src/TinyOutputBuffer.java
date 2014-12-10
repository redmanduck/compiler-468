import java.util.ArrayList;

public class TinyOutputBuffer extends ArrayList<String>{

		private static final long serialVersionUID = 2648572864328128569L;
		public TinyOutputBuffer(){
			super();
		}
		
		@Override public boolean add(String k){
			String eol = (k.indexOf(k.length() - 1) == '\n') ? "" : "\n";
			super.add(k + eol);
//			System.out.println("; >  " + k.replaceAll("\n",";"));
			return true;
		}
	}
