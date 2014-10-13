public class Register {
	public char type;
	public int number;
	public Register(char type, int number){
		this.type = type;
		this.number = number;
	}
	public String toString(){
		return "$" + type + number;
	}
}
