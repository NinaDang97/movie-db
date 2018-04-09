package moviedb_examples;

/**
 * Here you set the Database URL, username, and password to connect the desired database.
 * In addition, the vendor-specific error code for primary key violation is defined here.
 * 
 * All the example programs can access MariaDB, SQL Server, Sqlite etc. without any changes.
 * - You change the target database by toggling the comments below.
 * - You have comment out / uncomment all 6 lines per each set of parameters!
 * - Initially, Sqlite is the target DBMS. The database file is included in this Java project.
 * 
 * @author Kari
 * @version 1.1 | 7.4.2017 | Support for explicit driver loading with Class.forName added
 */

public class ConnectionParameters {
	
	// === (1) MariaDB ===
//	public static final String username = "*** CHANGE THIS TO YOUR OWN HAAGA-HELIA USERNAME ***"; 
//	public static final String password = "*** CHANGE THIS TO YOUR OWN MariaDB PASSWORD ***";
//	private static final String databaseName = username;
//	public static final String databaseURL = "jdbc:mysql://localhost:3306/" + databaseName;
//	public static final String jdbcDriver = "org.mariadb.jdbc.Driver";
//	public static final int PK_VIOLATION_ERROR = 1062; // PK violation: error code in MariaDB is 1062
	
	// === (2) SQL Server ===
//	public static final String username = "*** CHANGE THIS TO YOUR OWN SQL-EDU-01 USERNAME ***"; 
//	public static final String password = "*** CHANGE THIS TO YOUR OWN SQL-EDU-01 PASSWORD ***";
//	private static final String databaseName = "BIT_SWD03";
//	public static final String databaseURL = "jdbc:sqlserver://SQL-EDU-01:1433;databaseName=" + databaseName;
//	public static final String jdbcDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//	public static final int PK_VIOLATION_ERROR = 2627; // PK or UNIQUE violation: error code in SQL Server is 2627
	
	// === (3) Sqlite ===
	public static final String username = "No username required here!"; 
	public static final String password = "No password required here!";
	private static final String databaseName = "./SqliteDatabases/MovieDB.sqlite";
	public static final String databaseURL = "jdbc:sqlite:" + databaseName;
	public static final String jdbcDriver = "org.sqlite.JDBC";
	public static final int PK_VIOLATION_ERROR = 19; // PK violation: error code in Sqlite is 19
}
// End
