package middle.atlas;

import org.bukkit.plugin.java.JavaPlugin;
import middle.atlas.modules.Module;

public class Atlas extends JavaPlugin {
	
	private static Atlas self;
	private static Module[] modules;
	
	@Override
	public void onEnable() {
		getLogger().info("-Atlas loading-");
		getLogger().info("===============");
		getLogger().info("Loading Modules");
		modules = new Module[] 
				
				{};  //To register a new module, simply create an instance of it in these brackets.
		
		for (int i = 0; i < modules.length; i++) {
			modules[i].init();
		}
		self=this;
	}

	@Override
	public void onDisable() {
		getLogger().info("-Atlas shutting down-");
		getLogger().info("=====================");
		getLogger().info("Shutting down Modules");
		for (int i = 0; i < modules.length; i++) {
			modules[i].shutdown();
		}
	}
	
	public static Atlas get() {
		return self;
	}
	
}
