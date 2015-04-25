package edu.neu.cs5200.chaanda.models;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the degree database table.
 * 
 */
@Entity
@NamedQuery(name="Degree.findAll", query="SELECT d FROM Degree d")
public class Degree implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int degreeId;

	private String degreeName;

	private int duration;

	private BigDecimal tuition;

	//bi-directional many-to-one association to College
	@ManyToOne
	@JoinColumn(name="collegeId")
	private College college;

	public Degree() {
	}

	public int getDegreeId() {
		return this.degreeId;
	}

	public void setDegreeId(int degreeId) {
		this.degreeId = degreeId;
	}

	public String getDegreeName() {
		return this.degreeName;
	}

	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}

	public int getDuration() {
		return this.duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public BigDecimal getTuition() {
		return this.tuition;
	}

	public void setTuition(BigDecimal tuition) {
		this.tuition = tuition;
	}

	public College getCollege() {
		return this.college;
	}

	public void setCollege(College college) {
		this.college = college;
	}

}