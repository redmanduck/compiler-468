public class Register {
	public char type;
	public int number;
	public String dtype;
	public boolean dirty;
	public boolean live;
	
	public Register(char type, int number, String datatype){
		this.type = type;
		this.number = number;
		this.dtype = datatype;
		this.dirty = false;
		this.live = false;
	}
	
	public Register(char type, int number){
		this.type = type;
		this.number = number;
		this.dtype = "tiny";
		this.dirty = false;
		this.live = false;
	}
	
	public Register(char type){
		this.type = type;
		this.number = -1;
		this.dtype = "return";
		this.dirty = false;
		this.live = false;
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