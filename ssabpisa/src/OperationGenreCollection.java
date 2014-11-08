import java.util.ArrayList;

public class OperationGenreCollection extends ArrayList<Instruction>{
		/**
	 * 
	 */
	private static final long serialVersionUID = 9086106002762528980L;

		public OperationGenreCollection(Instruction a, Instruction b, Instruction c){
			super();
			this.add(a);
			this.add(b);
			this.add(c);
		}

		public OperationGenreCollection(Instruction s, Instruction s2) {
			// TODO Auto-generated constructor stub
			super();
			this.add(s);
			this.add(s2);
		}
	}