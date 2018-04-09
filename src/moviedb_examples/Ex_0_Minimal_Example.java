package moviedb_examples;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 * A minimal database access example (executing a SELECT statement without parameters). 
 * Notice: First, you have to create the Movie table in the target database 
 * by running the 'Create_Movie_Table' program.
 * 
 * @author Kari
 * @version 17.3.2017
 */
public class Ex_0_Minimal_Example {
	
	public static void main(String[] args) {
		String username = ConnectionParameters.username;			 
		String password = ConnectionParameters.password;
		String databaseURL = ConnectionParameters.databaseURL;
		Connection dbConnection = null;

		System.out.println("=== LISTING ALL MOVIES === \n");

		try {
			// 1. Open a database connection
			//    NB! MariaDB in HH: This does not work, if you have not opened an SSH tunnel to the server.
			dbConnection = DriverManager.getConnection(databaseURL, username, password);

			// 2. Define the SQL query text
			String sqlText = "SELECT year, name, director FROM Movie ORDER BY year, name";

			// 3. Create a statement object
			Statement statement = dbConnection.createStatement();

			// 4. Execute the SQL query with the statement object
			ResultSet resultSet = statement.executeQuery(sqlText);

			// 5. Iterate through the query result
			// NB! resultSet.next() moves the cursor to the next available row
			// in the result. It returns false if there are no more rows.
			while (resultSet.next()) {
				
				// 6. Each column value has to be retrieved separately as below
				int year = resultSet.getInt("year");
				String name = resultSet.getString("name");
				String director = resultSet.getString("director");

				System.out.println(year + " " + name + " (" + director + ")");
			}
			
		} catch (SQLException sqle) {
			// If any JDBC operation fails, we display an error message here
			System.out.println("===== Database error =====\n" + sqle.getMessage());	
			
		} finally {
			// 7. Finally, the database connection should be closed
			try { dbConnection.close(); } catch(SQLException sqle) {}
		}
		
	}
}
// End