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
			return "<Id>" + _id.name;
		} else if (_reg != null) {
			return "<Reg>" + _reg.toString();
		}
		return "<Empty IRDest>";
	}

	public String getDataTypePrecedence() {
		if (_id != null) {
			return _id.type;
		} else if (_reg != null) {
			return _reg.dtype;
		}
		System.err.println("Something gone wrong");
		return null;
	}

	public boolean isFloat() {
		if (this._id != null && this._id.type.equals("FLOAT")) {
			return true;
		}

		if (this._reg != null && this._reg.dtype.equals("FLOAT")) {
			return true;
		}
		return false;
	}
}