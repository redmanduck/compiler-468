public class Id {
   public Integer parameter_code;
   public Integer non_global_code;
   
   private String name;
   private String type;
   private String value;
   
   public Id(String name, String type){
	   this.name = name;
	   this.type = type;
	   parameter_code = null;
	   non_global_code = null;
   }
   
   public Id(String name, String type, String value){
	   this.name = name;
	   this.type = type;
	   this.value = value;
	   parameter_code = null;
	   non_global_code = null;
   }
   
   public boolean isLocalVariable(){
	   if(non_global_code != null) return true;
	   return false;
   }
   
   public String toString(){
	   if(parameter_code != null) return "$P" + parameter_code;
	   if(non_global_code != null) return "$L" + non_global_code;
	   
	   return name;
   }
   
   public String getReferenceName(){
	   return name;
   }
   
   public String getTiny(){
	   if(parameter_code != null){
		   //parameter
		   return "$" + TinyActivationRecord.getParameter(name); 
	   }
	   if(non_global_code != null){
		   //local variable
		   return "$" + TinyActivationRecord.getLocalVariable(name);
	   }
	   return name;
   }
   
   public String getType(){
	   return type;
   }
   
   public String getStrValue(){
	   return value;
   }
}
