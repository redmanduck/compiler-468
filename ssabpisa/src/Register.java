import java.util.Date;

public class Register {
	public char type;
	public int number;
	public String dtype;
	public boolean dirty;
	public String opr; //used by ensure() & allocate() 
	public boolean free;
	
	
	private long LastAllocated;
	
	public void updateTimestamp(){
		Date d = new Date();
		LastAllocated = d.getTime();
	}
	
	public long getTimestamp(){
		return LastAllocated;
	}
	
	public Register(char type, int number, String datatype){
		this.type = type;
		this.number = number;
		this.dtype = datatype;
		this.dirty = false;
		free = false;
		opr = "nothing";
	}
	
	public Register(char type, int number){
		this.type = type;
		this.number = number;
		this.dtype = "tiny";
		this.dirty = false;
		free = false;
		opr = "nothing";
	}
	
	public Register(char type){
		this.type = type;
		this.number = -1;
		this.dtype = "return";
		this.dirty = false;
		free = false;
		opr = "nothing";
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