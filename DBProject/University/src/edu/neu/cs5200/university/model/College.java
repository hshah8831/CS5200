package edu.neu.cs5200.university.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


/**
 * The persistent class for the college database table.
 * 
 */
@XmlRootElement
@JsonIgnoreProperties(value = { "degrees" })
@Entity
@NamedQuery(name="College.findAll", query="SELECT c FROM College c")
public class College implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int collegeId;

	private String collegeName;

	//bi-directional one-to-one association to University
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="universityId")
	@PrimaryKeyJoinColumn
	private University university;

	//bi-directional many-to-one association to Degree
	@OneToMany(mappedBy="college")
	private List<Degree> degrees;

	public College() {
	}

	@XmlAttribute
	public int getCollegeId() {
		return this.collegeId;
	}

	public void setCollegeId(int collegeId) {
		this.collegeId = collegeId;
	}

	@XmlAttribute
	public String getCollegeName() {
		return this.collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public University getUniversity() {
		return this.university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}

	@XmlTransient
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