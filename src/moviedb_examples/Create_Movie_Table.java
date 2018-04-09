package moviedb_examples;

import java.sql.*;

/**
 * Creates or recreates the Movie table in the target database
 * 
 * @author Kari
 * @version 17.3.2017
 */
public class Create_Movie_Table {
	
	public static void main(String[] args) {
		String username = ConnectionParameters.username;			 
		String password = ConnectionParameters.password;
		String databaseURL = ConnectionParameters.databaseURL;
		Connection dbConnection = null;	
		
		System.out.print("CREATING AND POPULATING THE MOVIE TABLE IN ");
				
		try {
			dbConnection = DriverManager.getConnection(databaseURL, username, password);
			System.out.println(dbConnection.getMetaData().getDatabaseProductName() + "\n");
			
			Statement statement = dbConnection.createStatement();	
			
			// 1. Drop the Movie table
			try {
				statement.executeUpdate("DROP TABLE Movie");
				System.out.println("The existing Movie table is dropped succesfully.");
			} catch(SQLException sqle) {}
					
			// 2. Create/recreate the Movie table
			String sqlCreateTable = 
					"CREATE TABLE Movie ("
					+ "id INTEGER PRIMARY KEY, " 
					+ "name VARCHAR(50) NOT NULL, " 
					+ "director VARCHAR(50) NOT NULL, "
					+ "year INTEGER NOT NULL)";
			
			PreparedStatement pstmtCreateTable = dbConnection.prepareStatement(sqlCreateTable);	
						
			pstmtCreateTable.execute();
			System.out.println("The Movie table is created succesfully.");
			
			// 3. Populate the Movie table
			String sqlInsertMovie =
				"INSERT INTO Movie (id, name, director, year) VALUES " +
				"(10, 'Casablanca','Michael Curtiz', 1942), " +
				"(20, 'Citizen Kane','Orson Wells', 1941), " +
				"(30, 'The Talk of the Town','George Stevens', 1942)";
							
			statement.executeUpdate(sqlInsertMovie);
			System.out.println("The Movie table is populated succesfully.");
			
		} catch (SQLException sqle) {
				System.out.println("===== Database error =====\n" + sqle.getMessage());
		} finally {
			if (dbConnection != null) {
				try { dbConnection.close(); } catch (SQLException sqle) {}
			}
		}
	}
}
// End