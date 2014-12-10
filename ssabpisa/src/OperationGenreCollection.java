import java.util.ArrayList;

public class OperationGenreCollection extends ArrayList<Instruction>{
		/**
	 * 
	 */
	private static final long serialVersionUID = 9086106002762528980L;

		public OperationGenreCollection(Instruction...args){
			super();
			for(Instruction a: args){
				this.add(a);
			}
		}
	}