package middle.atlas.modules.variable;

import java.util.UUID;

import middle.atlas.database.DatabaseHelper;

public class VariableSQLWrapper {
	private DatabaseHelper helper;
	private ModuleVariable mVariable;
	
	public VariableSQLWrapper(DatabaseHelper d, ModuleVariable v) {
		helper = d;
		mVariable = v;
	}
	
	public double getVariable(String variableName, UUID user) {
		//TODO: hook up database shit
		return -1;
	}
}
