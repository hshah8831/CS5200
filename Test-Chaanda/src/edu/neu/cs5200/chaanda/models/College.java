package edu.neu.cs5200.chaanda.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the college database table.
 * 
 */
@Entity
@NamedQuery(name="College.findAll", query="SELECT c FROM College c")
public class College implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int collegeId;

	private String collegename;

	//bi-directional many-to-one association to University
	@ManyToOne
	@JoinColumn(name="universityId")
	private University university;

	//bi-directional many-to-one association to Degree
	@OneToMany(mappedBy="college")
	private List<Degree> degrees;

	public College() {
	}

	public int getCollegeId() {
		return this.collegeId;
	}

	public void setCollegeId(int collegeId) {
		this.collegeId = collegeId;
	}

	public String getCollegename() {
		return this.collegename;
	}

	public void setCollegename(String collegename) {
		this.collegename = collegename;
	}

	public University getUniversity() {
		return this.university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}

	public List<Degree> getDegrees() {
		return this.degrees;
	}

	public void setDegrees(List<Degree> degrees) {
		this.degrees = degrees;
	}

	public Degree addDegree(Degree degree) {
		getDegrees().add(degree);
		degree.setCollege(this);

		return degree;
	}

	public Degree removeDegree(Degree degree) {
		getDegrees().remove(degree);
		degree.setCollege(null);

		return degree;
	}

}