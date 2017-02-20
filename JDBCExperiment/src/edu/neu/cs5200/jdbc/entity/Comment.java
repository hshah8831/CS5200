package edu.neu.cs5200.jdbc.entity;

import java.util.Date;

public class Comment {
	private String comment;
	private Date date;
	private String userID;
	private String movieID;
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getMovieID() {
		return movieID;
	}
	public void setMovieID(String movieID) {
		this.movieID = movieID;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Comment(){
		
	};
	public Comment(String comment, String userID, String movieID, Date date){
		this.comment = comment;
		this.date = date;
		this.userID = userID;
		this.movieID = movieID;
	}
}
