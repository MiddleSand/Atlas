package middle.atlas;

import java.io.File;
import java.util.logging.Level;

import org.bukkit.plugin.java.JavaPlugin;

import middle.atlas.database.AbstractDatabaseCore;
import middle.atlas.database.DatabaseHelper;
import middle.atlas.database.DatabaseManager;
import middle.atlas.database.MySQLCore;
import middle.atlas.database.SQLiteCore;
import middle.atlas.database.ServiceInjector;
import middle.atlas.modules.Module;

public class Atlas extends JavaPlugin {
	
	private static Atlas self;
	private static Module[] modules;
	
	public String dbPrefix = "dot";
	public static MySQLCore database;
	private static DatabaseManager databaseManager;
	private static DatabaseHelper databaseHelper;
	private static String host = "put-host-here"; // Host to connect for MySQL
	private static String user = "put-user-here"; // User to try and connect with for MySQL
	private static String pass = "put-pass-here"; // Password for above
	private static String databat = "put-database-name-here"; // Database name to use
	private static short port = 1234; // Port for the host for MySQL
	private boolean useSSL = false; // Use ssl for MySQL?
	
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
	
	/** - Gets the database prefix for usage in DatabaseHelper */
	public String getDbPrefix() {
		return dbPrefix;
	}
	
	/** - Getter for the database manager */
	public DatabaseManager getDatabaseManager() {
		return databaseManager;
	}
	
	/** - Getter for the database helper */
	public static DatabaseHelper getDatabaseHelper() {
		return databaseHelper;
	}
	
	/** - Sets up the database on init; Run on enable / plugin reload *if you want* */
	private boolean setupDatabase() {
        try {
            AbstractDatabaseCore dbCore;
                dbCore = new SQLiteCore(this, new File(this.getDataFolder(), "dataDot.db"));
                
                // TODO: setup config to handle database type switching and information
                
                //dbCore = new MySQLCore(this, host, user, pass, databat, Short.toString(port), useSSL);
            this.databaseManager = new DatabaseManager(this, ServiceInjector.getDatabaseCore(dbCore));
            // Make the database up to date
            this.databaseHelper = new DatabaseHelper(this, this.databaseManager);
        } catch (DatabaseManager.ConnectionException e) {
            getLogger().log(Level.SEVERE, "Error when connecting to the database", e);
            getServer().getPluginManager().disablePlugin(this);
            return false;
        } catch (Exception e) {
            getLogger().log(Level.SEVERE, "Error when setup database", e);
            getServer().getPluginManager().disablePlugin(this);
            return false;
        }
        return true;
    }
	
}
