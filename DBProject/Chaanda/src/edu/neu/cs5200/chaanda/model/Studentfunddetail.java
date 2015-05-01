package edu.neu.cs5200.chaanda.model;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;


/**
 * The persistent class for the studentfunddetails database table.
 * 
 */
@Entity
@Table(name="studentfunddetails")
@NamedQuery(name="Studentfunddetail.findAll", query="SELECT s FROM Studentfunddetail s")
public class Studentfunddetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int studentId;

	private BigDecimal fundCollected;

	private BigDecimal fundRequired;

	private String petitionDescription;
	
//	@Column(name="fundPetitionStatus")
//	private int fundPetitionStatus;

	public Studentfunddetail(int studentId, BigDecimal fundCollected,
			BigDecimal fundRequired, String petitionDescription, Student student) {
		super();
		this.studentId = studentId;
		this.fundCollected = fundCollected;
		this.fundRequired = fundRequired;
		this.petitionDescription = petitionDescription;
		//this.fundPetitionStatus = fundPetitionStatus;
		this.student = student;
	}

	//bi-directional one-to-one association to Student
	/*@OneToOne
	@PrimaryKeyJoinColumn
	@JoinColumn(name="studentId")*/
	@OneToOne(fetch=FetchType.EAGER)
	@PrimaryKeyJoinColumn(name="studentId", referencedColumnName="studentId")
	private Student student;

	public Studentfunddetail() {
	}

	public int getStudentId() {
		return this.studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	
//	public int getFundPetitionStatus() {
//		return this.fundPetitionStatus;
//	}
//
//	public void setFundPetitionStatus(int fundPetitionStatus) {
//		this.fundPetitionStatus = fundPetitionStatus;
//	}

	public BigDecimal getFundCollected() {
		return this.fundCollected;
	}

	public void setFundCollected(BigDecimal fundCollected) {
		this.fundCollected = fundCollected;
	}

	public BigDecimal getFundRequired() {
		return this.fundRequired;
	}

	public void setFundRequired(BigDecimal fundRequired) {
		this.fundRequired = fundRequired;
	}

	public String getPetitionDescription() {
		return this.petitionDescription;
	}

	public void setPetitionDescription(String petitionDescription) {
		this.petitionDescription = petitionDescription;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}