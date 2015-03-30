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

import edu.neu.cs5200.jdbc.entity.Cast;

public class CastManager {
	static java.util.Date today = new java.util.Date();
	static java.sql.Date date = new java.sql.Date(today.getTime());
	DataSource ds;
	public CastManager(){
		try {

			Context ctx = new InitialContext();

			ds = (DataSource)ctx.lookup("jdbc:mysql://localhost/movieschema");

			System.out.println(ds);

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	public void createCast(Cast newCast) throws SQLException{
		Connection connection = null;
		String sql = "insert into cast values (?,?,?)";
		try{
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, newCast.getCharacterName());
			statement.setString(2, newCast.getMovieID());
			statement.setString(3, newCast.getActorID());
			statement.execute();
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
	};
	public List<Cast> readAllCast() throws SQLException{
		List<Cast> casts = new ArrayList<Cast>();
		Connection connection = null;
		String sql = "select * from cast";
		try{
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
		    ResultSet rs = statement.executeQuery(sql);
		    
		    while (rs.next()){
		    	Cast cast = new Cast();
		    	cast.setCharacterName(rs.getString("charactername")); 
		    	cast.setMovieID(rs.getString("movieid"));
		    	cast.setActorID(rs.getString("actorid"));
		    	casts.add(cast);
		    }
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return casts;
	};
	public List<Cast> readAllCastForActor(String actorId) throws SQLException{
		List<Cast> casts = new ArrayList<Cast>();
		Connection connection = null;
		String sql = "select * from cast where actorid = ?";
		try{
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, actorId);
		    ResultSet rs = statement.executeQuery(sql);
		    
		    while (rs.next()){
		    	Cast cast = new Cast();
		    	cast.setCharacterName(rs.getString("comment")); 
		    	cast.setMovieID(rs.getString("movieid"));
		    	cast.setActorID(rs.getString("actorid"));
		    	casts.add(cast);
		    }
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return casts;
	};
	public List<Cast> readAllCastForMovie(String movieId) throws SQLException{
		List<Cast> casts = new ArrayList<Cast>();
		Connection connection = null;
		String sql = "select * from cast where movieid = ?";
		try{
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, movieId);
		    ResultSet rs = statement.executeQuery(sql);
		    
		    while (rs.next()){
		    	Cast cast = new Cast();
		    	cast.setCharacterName(rs.getString("comment")); 
		    	cast.setMovieID(rs.getString("movieid"));
		    	cast.setActorID(rs.getString("actorid"));
		    	casts.add(cast);
		    }
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return casts;
	};
	public Cast readCastForId(String castId) throws SQLException{
		Connection connection = null;
    	Cast cast = new Cast();
		String sql = "select * from cast where charactername = ?";
		try{
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, castId);
		    ResultSet rs = statement.executeQuery(sql);
		    
		    while (rs.next()){
		    	cast.setCharacterName(rs.getString("comment")); 
		    	cast.setMovieID(rs.getString("movieid"));
		    	cast.setActorID(rs.getString("actorid"));
		    	
		    }
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return cast;
	};
	public void updateCast(String castId, Cast newCast) throws SQLException{
		Connection connection = null;
		String sql = "update cast set charactername = ?, movieid = ?, actorid = ? where charactername = ?";
		try{
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, newCast.getCharacterName());
			statement.setString(2, newCast.getMovieID());
			statement.setString(3, newCast.getActorID());
			statement.setString(4, castId);
			statement.execute();
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
	};
	public void deleteCast(String castId) throws SQLException{ 
		Connection connection = null;
		String sql = "delete from Cast where charactername = ?";
		try{
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, castId);
			statement.execute();
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
	};
}
