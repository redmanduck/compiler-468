
public class Instruction {
	private String name;
	private int fields_count;
	
	public Instruction(String name){
		this.name = name;
		fields_count = 0;
	}
	public Instruction(String name, int f){
		fields_count = f;
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	
	public int getFieldsCount(){
		return this.fields_count;
	}
}
