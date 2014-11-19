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
