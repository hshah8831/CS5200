package edu.neu.cs5200.jdbc.entity;

import java.util.Date;

public class Actor {
	private String ID;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Actor(){
		
	};
	public Actor(String ID, String firstName, String lastName, Date dateOfBirth){
		this.ID = ID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
	}
}
