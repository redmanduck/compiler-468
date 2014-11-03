public class Register {
	public char type;
	public int number;
	public String dtype;
	public Register(char type, int number, String datatype){
		this.type = type;
		this.number = number;
		this.dtype = datatype;
	}
	public Register(char type, int number){
		this.type = type;
		this.number = number;
		this.dtype = "tiny";
	}
	public String toString(){
		return "$" + type + number;
	}

	public String toTiny(){
		return "r" + number;
	}
}