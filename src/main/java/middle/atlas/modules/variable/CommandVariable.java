package middle.atlas.modules.variable;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandVariable implements CommandExecutor{
	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		// /variable get-set-change-delete-add <name> <value or default value>
		if(args.length == 3) {
			switch (args[0]) {
			case "get": {
				
				break; 
				}
			case "set": { break; }
			case "change": { break; }
			case "delete": { break; }
			case "add": { break; }
			default: { 
				sender.sendMessage(ModuleVariable.specifyValidOperator);
				break; 
				}
			}
			return true;
		}
		
		return false;
    }
}
