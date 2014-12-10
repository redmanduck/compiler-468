import java.util.Date;
public class Register {
	public char type;
	public int number;
	public String dtype;
	private boolean dirty;
	private boolean free;
	
	public String opr; //$Lx $Tx varname
	
	public boolean isFree(){
		return free;
	}
	
	private long LastAllocated;
	public void updateTimestamp(){
		Date d = new Date();
		LastAllocated = d.getTime();
	}
	
	public long getTimestamp(){
		return LastAllocated;
	}
	
	public void clear(){
		free = true;
		dirty = false;
		opr = "none";
	}

	public void markClean(){
		dirty = true;
	}


	/*
	 * @param fullname - Px Lx or global variable
	 */
	public void occupy(String fullname){
		free = false;
		dirty = false;
		opr = fullname;
		updateTimestamp();
	}
	
	public boolean isDirty(){
		return dirty;
	}
	
	public void markDirty(){
		dirty = true;
		updateTimestamp();
	}
	
	public String getMemory(){
		if(opr.contains("$L") || opr.contains("$T")){
			return TinyActivationRecord.getLocalVariable(opr);
		}
		return opr;
	}
	
	public String getWritebackStatement(){
		return ISA.move.getName() + " " + this.toTiny() + " " + this.getMemory();
	}
	
	public Register(char type, int number, String datatype){
		this.type = type;
		this.number = number;
		this.dtype = datatype;
		this.dirty = false;
		free = false;
		opr = "none";
	}
	public Register(char type, int number){
		this.type = type;
		this.number = number;
		this.dtype = "tiny";
		this.dirty = false;
		free = false;
		opr = "none";
	}
	
	public Register(char type){
		this.type = type;
		this.number = -1;
		this.dtype = "return";
		this.dirty = false;
		free = false;
		opr = "none";
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