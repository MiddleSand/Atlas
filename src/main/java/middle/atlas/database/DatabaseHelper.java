
package middle.atlas.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;

import middle.atlas.Atlas;

/**
 * A Util to execute all SQLs.
 */
public class DatabaseHelper {

	private final DatabaseManager manager;

	private final Atlas plugin;

	public DatabaseHelper(Atlas plugin, DatabaseManager manager) throws SQLException {
		this.plugin = plugin;
		this.manager = manager;
		createBotInputTable();
	}

	/** - Creates a table in the database for name and appends the SQL parameters specified in fields. */

	private void createBotInputTable() {
		String sqlString = "CREATE TABLE botinput (input TEXT NOT NULL PRIMARY KEY, created INTEGER NOT NULL);";
		manager.runInstantTask(new DatabaseTask(sqlString));
	} 
	
	/** - Selects everything in a table by name of table. */
	
	public ResultSet selectAll(String table) throws SQLException {
		DatabaseConnection databaseConnection = manager.getDatabase().getConnection();
		Statement st = databaseConnection.get().createStatement();
		String selectAllTaxes = "SELECT * FROM " + plugin.getDbPrefix() + table;
		ResultSet resultSet = st.executeQuery(selectAllTaxes);
		return resultSet;
	}

	/** - Runs an SQL operation raw. You'll need to supply the exact SQL. */
	public void executeSQLWithoutReturn(String sqlString) {
		manager.addDelayTask(new DatabaseTask(sqlString, (ps) -> {}));
	}
		
	/** - Runs an SQL operation raw with a return. You'll need to supply the exact SQL. */
	
	public ResultSet executeSQLWithReturn(String sqlString) {
		ResultSet returnable = null;
		ResultSet resultSet = null;
		DatabaseConnection databaseConnection = manager.getDatabase().getConnection();
		Statement st = null;
		try {
			st = databaseConnection.get().createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			databaseConnection.release();
			return null;
		}
		try {
			returnable = st.executeQuery(sqlString);
		} catch (SQLException e) {
			e.printStackTrace();
			databaseConnection.release();
			return null;
		}

		databaseConnection.release();
		return returnable;
	}

	public void setupTable(String table, String parameters) {
		try {
			if (!manager.hasTable(table)) {
				String sqlString = "CREATE TABLE " + plugin.dbPrefix + " (" + parameters + ");";
				manager.runInstantTask(new DatabaseTask(sqlString));
			}
		} catch (SQLException e) {
			//This is a problem.
			e.printStackTrace();
		}	
	}
}
