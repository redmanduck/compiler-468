/*
    This program is part of an assignment for ECE468 at Purdue University, IN.
    Copying, modifying or reusing this program may result in disciplinary actions.
    
    Copyright (C) 2014-2075 S. Sabpisal

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

*/
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
