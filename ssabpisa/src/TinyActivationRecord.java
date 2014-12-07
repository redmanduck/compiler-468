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
import java.util.Hashtable;


public class TinyActivationRecord {
	private static int stack_growth_count = 1; 
	private static int local_var_count = -1;
	public static Hashtable<String, Integer> ParameterStackMap = new Hashtable<String, Integer>(); 
	public static Hashtable<String, Integer> LocalStackMap = new Hashtable<String, Integer>(); //local variable to stack
	
	public static void assignLocalVariable(String name) {
		LocalStackMap.put(name, local_var_count);
		local_var_count--;
	}

	public static String getReverseName(String var){
//		System.err.println("Reverse lookup " + var);
//		System.err.println(ParameterStackMap.toString());
//		System.err.println(LocalStackMap.toString());
		
		//Search Parameter Map
		for(String k : ParameterStackMap.keySet()){
			Integer v = ParameterStackMap.get(k);
			if(v.toString().equals(var)){
				return k;
			}
		}
		//Seacrh Local Map
		for(String k : LocalStackMap.keySet()){
			Integer v = LocalStackMap.get(k);
			if(v.toString().equals(var)){
				return k;
			}
		}
		return null;
	}
	
	public static void assignParameter(String name){
		ParameterStackMap.put(name, ++stack_growth_count);
	}
	
	public static Integer getParameter(String name){
		if(!ParameterStackMap.containsKey(name)){
			assignParameter(name);
		}
		return ParameterStackMap.get(name);
	}
	
	public static Integer getLocalVariable(String name){
		if(!LocalStackMap.containsKey(name)){
			   //if its not already in the Stack Map
			   //we put it there 
			   assignLocalVariable(name);
		}
		return LocalStackMap.get(name);
	}
	public static void saveRegisters(int increm){
		stack_growth_count += increm;
	}
	
	public static int getReturnStackAddress(){
		return stack_growth_count + 1;
	}
	
	public static void reset(){
		LocalStackMap.clear();
		ParameterStackMap.clear();
		stack_growth_count = 1;
		local_var_count = -1;
	}
}
