package edu.neu.cs5200.jdbc.entity;

public class Cast {
	private String characterName;
	private String movieID;
	private String actorID;

	public String getMovieID() {
		return movieID;
	}

	public void setMovieID(String movieID) {
		this.movieID = movieID;
	}

	public String getActorID() {
		return actorID;
	}

	public void setActorID(String actorID) {
		this.actorID = actorID;
	}

	public String getCharacterName() {
		return characterName;
	}

	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}
	public Cast(){
		
	}
	public Cast(String characterName, String movieID, String actorID){
		this.characterName = characterName;
		this.movieID = movieID;
		this.actorID = actorID;
	}
}
