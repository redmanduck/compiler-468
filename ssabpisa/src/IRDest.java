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
 public class IRDest {
	public Register _reg;
	public Id _id;

	public IRDest(Register r) {
		if (r == null)
			System.err.println("Something gone wrong");
		_reg = r;
	}

	public IRDest(Id id) {
		if (id == null)
			System.err.println("Something gone wrong");
		_id = id;
	}

	public String toString() {
		if (_id != null) {
			return "<Id>" + _id.toString();
		} else if (_reg != null) {
			return "<Reg>" + _reg.toString();
		}
		return "<Empty IRDest>";
	}

	public String getDataTypePrecedence() {
		if (_id != null) {
			return _id.getType();
		} else if (_reg != null) {
			return _reg.dtype;
		}
		System.err.println("Something gone wrong");
		return null;
	}

	public boolean isFloat() {
		if (this._id != null && this._id.getType().equals("FLOAT")) {
			return true;
		}

		if (this._reg != null && this._reg.dtype.equals("FLOAT")) {
			return true;
		}
		return false;
	}
}