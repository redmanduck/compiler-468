public class Register {
	public char type;
	public int number;
	public String dtype;
	private boolean dirty;
	private boolean free;
	
	public String opr; //$Lx $Tx varname

	public void clear(){
		free = true;
		opr = "none";
	}
	
	public boolean isDirty(){
		return dirty;
	}
	
	public String getMemory(){
		if(opr.contains("$L") || opr.contains("$T")){
			return TinyActivationRecord.getLocalVariable(opr);
		}
		return opr;
	}
	
	public Register(char type, int number, String datatype){
		this.type = type;
		this.number = number;
		this.dtype = datatype;
		this.dirty = false;
	}
	public Register(char type, int number){
		this.type = type;
		this.number = number;
		this.dtype = "tiny";
		this.dirty = false;
	}
	
	public Register(char type){
		this.type = type;
		this.number = -1;
		this.dtype = "return";
		this.dirty = false;
	}
	
	public String toString(){
		if(number == -1){
			return "$" + type;
		}
		return "$" + type + number;
	}

	public String toTiny(){
		return "r" + number;
	}
}