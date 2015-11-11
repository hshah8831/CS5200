package edu.neu.cs5200.jdbc.manager;

	
import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import edu.neu.cs5200.jdbc.entity.Movie;

public class MovieManager {
	static java.util.Date today = new java.util.Date();
	static java.sql.Date date = new java.sql.Date(today.getTime());
	DataSource ds;
	public MovieManager(){
		try {

			Context ctx = new InitialContext();

			ds = (DataSource)ctx.lookup("jdbc:mysql://localhost/movieschema");

			System.out.println(ds);

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public void createMovie(Movie newMovie) throws SQLException{
		Connection connection = null;
		String sql = "insert into movie values (?,?,?,?)";
		try{
			connection = ds.getConnection();
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
			connection = ds.getConnection();
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
			connection = ds.getConnection();
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
			connection = ds.getConnection();
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
			connection = ds.getConnection();
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
}
