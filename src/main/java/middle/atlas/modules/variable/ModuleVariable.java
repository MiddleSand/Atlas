package middle.atlas.modules.variable;

import middle.atlas.Atlas;
import middle.atlas.modules.Module;

public class ModuleVariable extends Module {
	protected static String specifyValidOperator = "§cSpecify a valid operator. §7Valid operators: §dget §7, §dset §7, §dchange §7, §ddelete §7, §dadd";
	/** Initiate the module */
	@Override
	public void init() {
		Atlas.get().getCommand("market").setExecutor(new CommandVariable());
		Atlas.getDatabaseHelper().setupTable("variables", "variable VARCHAR(255), uuid VARCHAR(255), value DOUBLE");
	}
	
	/** Shut down the module */
	@Override
	public void shutdown() {
		//Do something...
	}
	
}
