package edu.neu.cs5200.jdbc.entity;

import java.util.Date;

public class Movie {
	private String ID;
	private String title;
	private String posterImage;
	private Date releaseDate;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPosterImage() {
		return posterImage;
	}
	public void setPosterImage(String posterImage) {
		this.posterImage = posterImage;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public Movie(){
		
	};
	public Movie(String ID, String title, String posterImage,Date releaseDate){
		this.ID = ID;
		this.title = title;
		this.posterImage = posterImage;
		this.releaseDate = releaseDate;
	};
}
