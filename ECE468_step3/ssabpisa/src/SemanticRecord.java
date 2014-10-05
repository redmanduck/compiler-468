public abstract class SemanticRecord {
	   private String name;
	   private String type;
	   private String value;
	   
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
