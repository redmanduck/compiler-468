import java.util.HashSet;

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
public class Utils {
	public static void printSymbolTable(SymbolTable s){
		// Post order walk of Symbol Table
		if(s == null){
			return;
		}
		if(s.error){
			 return;
		}
		System.out.format("Symbol table %s\n", s.scopename);
		for(String k : s.getKeys()){
			if(s.get(k).getStrValue() != null){
				 System.out.format("name %s type %s value %s\n", s.get(k).toString(), s.get(k).getType(), s.get(k).getStrValue());
			}else{
				 System.out.format("name %s type %s\n", s.get(k).toString(), s.get(k).getType());
			}
		}
		for(SymbolTable j : s.children){
			System.out.format("\n");
			printSymbolTable(j);
		}
	}
	
	public static void printIR(IRList irl){
		for(IRNode a : irl){
			System.out.print(a.toString());
			System.out.println("; --- LIVEIN: " + a.LIVE_IN.toString() + ", LIVEOUT: " + a.LIVE_OUT.toString());
		}
	}
	

	public static String printRegisters(){
		String longtext = "{";
		for(int i = 0; i < TinyGenerator.RegisterFile.length; i++){
			longtext += "r" + i + "=" + TinyGenerator.RegisterFile[i].opr + ( TinyGenerator.RegisterFile[i].isDirty() ? "*" : "") + ", ";
		}
		longtext += "}";
		return longtext;
	}

	
	/*
	 * @param var - $Lx or $Tx or global variable
	 */
	public static boolean varIsLive(String var, HashSet<String> liveness){
		
		System.out.println("; Checking Liveness " + var);
		System.out.println("; Live out: " + liveness.toString());
		
		if(liveness.contains(var)){
			System.out.println("; " + var + " is live");
			return true;
		}
		if(liveness.contains(var)){
			System.out.println("; " + var + " is live");
			return true;
		}
		
		System.out.println("; " + var + " is dead");
		return false;
	}
	
	/*
	 * @param opr - $Lx or $Tx or global variable
	 */
	public static Register varInRegister(String opr){
		for(int i =0; i<TinyGenerator.RegisterFile.length; i++){
			if(TinyGenerator.RegisterFile[i].opr.equals(opr)){
					System.out.println("; found " + opr + " in r" + i);
					return TinyGenerator.RegisterFile[i];
				}
			}
		
			System.out.println("; " + opr + ": not loaded in any register..");
			return null;
	}
	
	
	public static Register getFreeReg(){
		for(int i =0; i<TinyGenerator.RegisterFile.length; i++){
			if(TinyGenerator.RegisterFile[i].isFree()){
				return TinyGenerator.RegisterFile[i];
			}
		}
		return null; //no free Register
	}
	
	public static Register getMostDistantUsedReg(){
		Register min = TinyGenerator.RegisterFile[0];
		for(int r = 0 ; r < TinyGenerator.RegisterFile.length; r++){
			if(TinyGenerator.RegisterFile[r].getTimestamp() < min.getTimestamp()){
				min = TinyGenerator.RegisterFile[r];
			}
		}
		return min;
	}
	
	
}
