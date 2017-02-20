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

import edu.neu.cs5200.jdbc.entity.User;

public class UserManager {
	static java.util.Date today = new java.util.Date();
	static java.sql.Date date = new java.sql.Date(today.getTime());
	DataSource ds;
	public UserManager(){
		try {

			Context ctx = new InitialContext();

			ds = (DataSource)ctx.lookup("jdbc:mysql://localhost/movieschema");

			System.out.println(ds);

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	public void createUser(User newUser) throws SQLException{
		Connection connection = null;
		String sql = "insert into user values (?,?,?,?,?,?)";
		try{
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, newUser.getUserName());
			statement.setString(2, newUser.getPassword());
			statement.setString(3, newUser.getFirstName());
			statement.setString(4, newUser.getLastName());
			statement.setString(5, newUser.getEmail());
			statement.setDate(6, date);
			statement.execute();
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
	}
	public List<User> readAllUsers() throws SQLException{
		List<User> users = new ArrayList<User>();
		Connection connection = null;
		String sql = "select * from user";
		try{
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
		    ResultSet rs = statement.executeQuery(sql);
		    
		    while (rs.next()){
		    	User user = new User();
		    	user.setUserName(rs.getString("username"));
		    	user.setPassword(rs.getString("password"));
		    	user.setFirstName(rs.getString("firstname"));
		    	user.setLastName(rs.getString("lastname"));
		    	user.setEmail(rs.getString("email"));
		    	user.setDateOfBirth(rs.getDate("dateofbirth"));
		    	users.add(user);
		    }
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return users;
	}
	public User readUser(String username) throws SQLException{
		User user = new User();
		Connection connection = null;
		String sql = "select * from user where username = ?";
		try{
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet rs = statement.executeQuery();
			 while (rs.next()){
				 	user.setUserName(rs.getString("username"));
			    	user.setPassword(rs.getString("password"));
			    	user.setFirstName(rs.getString("firstname"));
			    	user.setLastName(rs.getString("lastname"));
			    	user.setEmail(rs.getString("email"));
			    	user.setDateOfBirth(rs.getDate("dateofbirth"));
			    }
		   
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return user;
	}
	public void updateUser(String username, User user) throws SQLException{
		Connection connection = null;
		String sql = "update User set password = ?, firstname = ?, lastname = ?, email = ? where username = ?";
		try{
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, user.getUserName());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getFirstName());
			statement.setString(4, user.getLastName());
			statement.setString(5, user.getEmail());
			statement.setDate(6, date);
			statement.execute();
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
	}
	public void deleteUser(String username) throws SQLException{
		Connection connection = null;
		String sql = "delete from User where username = ?";
		try{
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, username);
			statement.execute();
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
	}
}
