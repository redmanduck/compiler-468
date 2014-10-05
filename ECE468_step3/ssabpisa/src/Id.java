public class Id extends SemanticRecord{
   public String name;
   public String type;
   public String value;

   public Id(String name, String type){
	   super(name, type);
   }
   
   public Id(String name, String type, String value){
	   super(name, type, value);
   }
}
