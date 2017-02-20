package edu.neu.cs5200.chaanda.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the universityadminmapping database table.
 * 
 */
@Entity
@NamedQuery(name="Universityadminmapping.findAll", query="SELECT u FROM Universityadminmapping u")
public class Universityadminmapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	//bi-directional many-to-one association to Person
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="adminId")
	private Person person;

	//bi-directional many-to-one association to University
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="universityId")
	private University university;

	public Universityadminmapping(int id, Person person, University university) {
		super();
		this.id = id;
		this.person = person;
		this.university = university;
	}

	public Universityadminmapping() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public University getUniversity() {
		return this.university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}

}