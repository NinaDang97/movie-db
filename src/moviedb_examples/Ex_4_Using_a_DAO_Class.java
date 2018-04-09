package moviedb_examples;

import java.util.List;

/**
 * Database access example using the DAO design pattern.
 * NB! There is no JDBC-related code in this class!
 * 
 * @author Kari
 * @version 17.3.2017
 */
public class Ex_4_Using_a_DAO_Class {
	
	public static void main(String[] args) {
		
		// 1. Create a DAO object for accessing the data
		MovieDAO movieDAO = new MovieDAO();
		List<Movie> movieList;
		
		try {
			System.out.println("=== LISTING ALL MOVIES === \n");
			
			// 2. Get a list of movies by calling the getAllMovies method of the DAO object
			movieList = movieDAO.getAllMovies();
		
			for(Movie movie : movieList) {
				System.out.println(movie.getYear() + " " + movie.getName() + " (" + movie.getDirector() + ")");		
			}	
			

			int givenYear = 1942;
			System.out.println("\n\n=== LISTING MOVIES FOR A GIVEN YEAR (" + givenYear + ") === \n");
			
			// 3. Get a list of movies for the given year by calling the getMoviesForGivenYear method of the DAO object
			movieList = movieDAO.getMoviesForGivenYear(givenYear);
			
			for(Movie movie : movieList) {
				System.out.println(movie.getYear() + " " + movie.getName() + " (" + movie.getDirector() + ")");		
			}
		
		} catch(Exception ex) {
			System.out.println("The database is temporarily unavailable. Please try again later. \n");
			System.out.println("=== System error message (for the developer's eyes only) === \n" + ex.getMessage());
		}
	}
}
// End