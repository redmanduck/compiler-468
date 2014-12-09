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

public class TinyActivationRecord {
	private static int param_count = 0; 
	private static int local_var_count = 0;
	private static int register_count = 0;

	
	public static String getParameter(String name){
		//GIVEN $P1 returns $1
		String j = name.replace("$P", "");
		int num = Integer.parseInt(j);
		if(num > param_count) param_count = num;
		
		return "$" + (num + register_count + 1);
	}
	
	public static String getLocalVariable(String name){
		//GIVEN $L1 returns $-1
		String j = name.replace("$L", "");
		int num = Integer.parseInt(j);
		if(num > local_var_count) local_var_count = num;
		return "$-" + num;
	}
	public static void saveRegisters(int increm){
		register_count += increm;
		
	}
	
	public static int getReturnStackAddress(){
		return param_count + register_count + 2;
	}
	
	public static void reset(){
		param_count =0;
		local_var_count = 0;
		register_count = 0;
	}
}
