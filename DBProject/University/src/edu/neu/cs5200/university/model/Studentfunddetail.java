package edu.neu.cs5200.university.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;


/**
 * The persistent class for the studentfunddetails database table.
 * 
 */
@XmlRootElement
@JsonIgnoreProperties(value = { "student", "fundPetitionStatus" })
@Entity
@Table(name="studentfunddetails")
@NamedQuery(name="Studentfunddetail.findAll", query="SELECT s FROM Studentfunddetail s")
public class Studentfunddetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int studentId;

	private int fundPetitionStatus;

	private BigDecimal fundRequired;

	private String petitionDescription;

	//bi-directional one-to-one association to Student
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="studentId")
	@PrimaryKeyJoinColumn
	private Student student;

	public Studentfunddetail() {
	}

	@XmlAttribute
	public int getStudentId() {
		return this.studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	@XmlAttribute
	public int getFundPetitionStatus() {
		return this.fundPetitionStatus;
	}

	public void setFundPetitionStatus(int fundPetitionStatus) {
		this.fundPetitionStatus = fundPetitionStatus;
	}

	@XmlAttribute
	public BigDecimal getFundRequired() {
		return this.fundRequired;
	}

	public void setFundRequired(BigDecimal fundRequired) {
		this.fundRequired = fundRequired;
	}

	@XmlAttribute
	public String getPetitionDescription() {
		return this.petitionDescription;
	}

	public void setPetitionDescription(String petitionDescription) {
		this.petitionDescription = petitionDescription;
	}

	@XmlTransient
	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}