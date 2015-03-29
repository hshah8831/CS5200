package edu.neu.cs5200.jdbc.manager;

	
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import edu.neu.cs5200.jdbc.entity.Movie;

public class MovieManager {
	static java.util.Date today = new java.util.Date();
	static java.sql.Date date = new java.sql.Date(today.getTime());
	public Connection getConnection(){
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/movieschema" , "root" , "abcusa123");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	public void createMovie(Movie newMovie) throws SQLException{
		Connection connection = null;
		String sql = "insert into movie values (?,?,?,?)";
		try{
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, newMovie.getID());
			statement.setString(3, newMovie.getPosterImage());
			statement.setDate(4, date);
			statement.setString(2, newMovie.getTitle());
			statement.execute();
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
	};
	public List<Movie> readAllMovies() throws SQLException{
		List<Movie> movies = new ArrayList<Movie>();
		Connection connection = null;
		String sql = "select * from movie";
		try{
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
		    ResultSet rs = statement.executeQuery(sql);
		    
		    while (rs.next()){
		    	Movie movie = new Movie();
		    	movie.setID(rs.getString("id"));
		    	movie.setPosterImage(rs.getString("posterImage"));
		    	movie.setReleaseDate(rs.getDate("releaseDate"));
		    	movie.setTitle(rs.getString("title"));
		    	movies.add(movie);
		    }
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return movies;
	};
	public Movie readMovie(String movieId) throws SQLException{
		Movie movie = new Movie();
		Connection connection = null;
		String sql = "select * from movie where id = ?";
		try{
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, movieId);
			ResultSet rs = statement.executeQuery();
			 while (rs.next()){
				 	movie.setID(rs.getString("id"));
				    movie.setPosterImage(rs.getString("posterImage"));
				    movie.setReleaseDate(rs.getDate("releaseDate"));
				    movie.setTitle(rs.getString("title"));
			    }
		   
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return movie;
		
	};
	public void updateMovie(String movieId, Movie movie) throws SQLException{
		Connection connection = null;
		String sql = "update Movie set title = ?, posterImage = ?, releaseDate = ? where id = ?";
		try{
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(4, movie.getID());
			statement.setString(2, movie.getPosterImage());
			statement.setDate (3, date);
			statement.setString(1, movie.getTitle());
			statement.execute();
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
	};
	public void deleteMovie(String movieId) throws SQLException{
		Connection connection = null;
		String sql = "delete from Movie where id = ?";
		try{
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, movieId);
			statement.execute();
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
	};
	public static void main(String[] args) {
		MovieManager manager = new MovieManager();
		Movie movie = new Movie();
		List<Movie> movies = new ArrayList<Movie>();
		movie.setID("2");
		movie.setTitle("Avatar2");
		movie.setReleaseDate(new Date());
		movie.setPosterImage("ActionThrillerMovie");
		try {
			movies = manager.readAllMovies();
			for(int i = 0; i < movies.size(); i++){
				System.out.println(movies.get(i).getID());
				System.out.println(movies.get(i).getTitle());
				System.out.println(movies.get(i).getPosterImage());
				System.out.println(movies.get(i).getReleaseDate());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			manager.updateMovie("1", movie);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			manager.deleteMovie("1");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			movies = manager.readAllMovies();
			for(int i = 0; i < movies.size(); i++){
				System.out.println(movies.get(i).getID());
				System.out.println(movies.get(i).getTitle());
				System.out.println(movies.get(i).getPosterImage());
				System.out.println(movies.get(i).getReleaseDate());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
