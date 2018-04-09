package moviedb_examples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

/**
 * DAO class for accessing movies. 
 * NB! There is no user input/output in this class!
 * 
 * @author Kari
 * @version 17.3.2017 | 7.4.2017 Class.forName added (Tomcat 8 requires this)
 */
public class MovieDAO 
{
	private String username;			 
	private String password; 
	private String databaseURL;

	public MovieDAO() {
		username = ConnectionParameters.username;			 
		password = ConnectionParameters.password;
		databaseURL = ConnectionParameters.databaseURL;
		
		// We have load the JDBC driver explicitly in Tomcat 8 environment
		try {
			Class.forName(ConnectionParameters.jdbcDriver);
		} catch (Exception ex) {
			System.out.println("\nJDBC driver not found: " + ex.getMessage());
		}
	}
	
	private Connection openConnection() throws SQLException	{
		Connection dbConnection = DriverManager.getConnection(databaseURL, username, password);
		return dbConnection;
	}
	
	private void closeConnection(Connection dbConnection) throws SQLException {
		if (dbConnection != null) {
			dbConnection.close();
		}
	}
		
	/**
	 * Retrieves all movies from the database. 
	 * @return List<Movie> - a List of Movies
	 * @throws SQLException
	 */
	public List<Movie> getAllMovies() throws SQLException {
		List<Movie> movieList = new ArrayList<Movie>();
		Connection dbConnection = null;
		
		try {
			dbConnection = openConnection();

			String sqlText = "SELECT id, name, director, year FROM Movie ORDER BY year, name";
			PreparedStatement preparedStatement = dbConnection.prepareStatement(sqlText);
			
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String director = resultSet.getString("director");
				int year = resultSet.getInt("year");

				movieList.add(new Movie(id, name, director, year));
			}
			
			return movieList;
			
		} catch (SQLException sqle)	{
			throw sqle;	// Let the caller decide what to do with the exception
			
		} finally {
			closeConnection(dbConnection);
		}
	}
	
	/**
	 * Retrieves movies from the database for the given year 
	 * @return List<Movie> - a List of Movies
	 * @throws SQLException
	 */
	public List<Movie> getMoviesForGivenYear(int givenYear) throws SQLException {
		List<Movie> movieList = new ArrayList<Movie>();
		Connection dbConnection = null;
		
		try {
			dbConnection = openConnection();

			String sqlText = "SELECT id, name, director, year FROM Movie WHERE year = ? ORDER BY name";
			PreparedStatement preparedStatement = dbConnection.prepareStatement(sqlText);
			preparedStatement.setInt(1, givenYear);
			
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String director = resultSet.getString("director");
				int year = resultSet.getInt("year");

				movieList.add(new Movie(id, name, director, year));
			}
			
			return movieList;
			
		} catch (SQLException sqle)	{
			throw sqle; 	// Let the caller decide what to do with the exception
			
		} finally {
			closeConnection(dbConnection);
		}
	}
}
// End