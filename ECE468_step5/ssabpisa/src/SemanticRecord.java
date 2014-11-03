public abstract class SemanticRecord {
	   protected String name;
	   protected String type;
	   protected String value;
	   
	   public SemanticRecord(String name, String type){
		     this.name = name;
		     this.type = type;
	  }
		   
	   public SemanticRecord(String name, String type, String value){
			   this.name = name;
			   this.type = type;
			   this.value = value;
	   }
}
