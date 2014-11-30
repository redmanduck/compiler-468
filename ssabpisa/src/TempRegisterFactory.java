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

public class TempRegisterFactory {
	private static int tempcount = 1;
	private static int tiny_tempcount = 0;
	private static Hashtable<Integer, Register> regs = new Hashtable<Integer, Register>();
	private static Register previous;
	
	
	public static void reset(){
		tempcount = 1;
		tiny_tempcount = 0;
		regs.clear();
	}
	/*
	 * determine the next usable temp register
	 */
	public static Register allocate(String type){
		int v = tempcount++;
		Register r = new Register('T', v, type);
		regs.put(v, r);
		previous = r;
		return r;
	}
	
	public static Register allocate_tiny(){
		int v = tiny_tempcount++;
		Register r = new Register('r', v);
		regs.put(v, r);
		previous = r;
		return r;
	}

	public static Register previous() {
		return previous;
	}
	
	public static Hashtable<Integer, Register> getRegMap(){
		return regs;
	}
}
