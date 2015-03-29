package edu.neu.cs5200.jdbc.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.neu.cs5200.jdbc.entity.Comment;

public class CommentManager {
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
	public void createComment(Comment newComment) throws SQLException{
		Connection connection = null;
		String sql = "insert into comment values (?,?,?,?)";
		try{
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, newComment.getComment());
			statement.setString(2, newComment.getMovieID());
			statement.setString(3, newComment.getUserID());
			statement.setDate(4, date);
			statement.execute();
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
	};
	public List<Comment> readAllComments() throws SQLException{
		List<Comment> comments = new ArrayList<Comment>();
		Connection connection = null;
		String sql = "select * from comment";
		try{
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
		    ResultSet rs = statement.executeQuery(sql);
		    
		    while (rs.next()){
		    	Comment comment = new Comment();
		    	comment.setComment(rs.getString("comment")); 
		    	comment.setMovieID(rs.getString("movieid"));
		    	comment.setUserID(rs.getString("userid"));
		    	comment.setDate(rs.getDate("date"));
		    	comments.add(comment);
		    }
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return comments;
	};
	public List<Comment> readAllCommentsForUsername(String username) throws SQLException{
		List<Comment> comments = new ArrayList<Comment>();
		Connection connection = null;
		String sql = "select * from comment where userid = ?";
		try{
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, username);
		    ResultSet rs = statement.executeQuery(sql);
		    
		    while (rs.next()){
		    	Comment comment = new Comment();
		    	comment.setComment(rs.getString("comment")); 
		    	comment.setMovieID(rs.getString("movieid"));
		    	comment.setUserID(rs.getString("userid"));
		    	comment.setDate(rs.getDate("date"));
		    	comments.add(comment);
		    }
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return comments;
	};
	public List<Comment> readAllCommentsForMovie(String movieId) throws SQLException{
		List<Comment> comments = new ArrayList<Comment>();
		Connection connection = null;
		String sql = "select * from comment where movieid = ?";
		try{
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, movieId);
		    ResultSet rs = statement.executeQuery(sql);
		    
		    while (rs.next()){
		    	Comment comment = new Comment();
		    	comment.setComment(rs.getString("comment")); 
		    	comment.setMovieID(rs.getString("movieid"));
		    	comment.setUserID(rs.getString("userid"));
		    	comment.setDate(rs.getDate("date"));
		    	comments.add(comment);
		    }
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return comments;
	};
	public Comment readCommentForId(String commentId) throws SQLException{
		Comment comment = new Comment();
		Connection connection = null;
		String sql = "select * from comment where comment = ?";
		try{
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, commentId);
			ResultSet rs = statement.executeQuery();
			 while (rs.next()){
				 comment.setComment(rs.getString("comment")); 
				 comment.setMovieID(rs.getString("movieid"));
				 comment.setUserID(rs.getString("userid"));
				 comment.setDate(rs.getDate("date"));
			    }   
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return comment;
	};
	public void updateComment(String commentId, String newComment) throws SQLException{
		Connection connection = null;
		String sql = "update Comment set comment = ?, date = ? where comment = ?";
		try{
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, newComment);
			statement.setDate(2, date);
			statement.setString(3, commentId);
			statement.execute();
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
	};
	public void deleteComment(String commentId) throws SQLException{
		Connection connection = null;
		String sql = "delete from Comment where comment = ?";
		try{
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, commentId);
			statement.execute();
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
	};
}
