
public class Instruction {
	private String name;
	private int fields_count;
	public String supported_type; //float int etc
	
	public Instruction(String name){
		this.name = name;
		fields_count = 0;
		supported_type = "NONE";
	}
	public Instruction(String name, int f, String supported_type){
		fields_count = f;
		this.name = name;
		this.supported_type = supported_type;
	}
	
	public Instruction(String name, int f){
		fields_count = f;
		this.name = name;
		this.supported_type = "NONE";
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getFieldsCount(){
		return this.fields_count;
	}
}
