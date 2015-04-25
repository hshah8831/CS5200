package edu.neu.cs5200.chaanda.models;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the student database table.
 * 
 */
@Entity
@NamedQuery(name="Student.findAll", query="SELECT s FROM Student s")
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int studentId;

	@Temporal(TemporalType.DATE)
	private Date enrolledYear;

	private BigDecimal fundCollected;
	
	private BigDecimal fundRequired;

	private BigDecimal familyIncome;

	private BigDecimal gpa;
	
	private int fundPetitionStatus;

	private String petitionDescription;
	
	@Temporal(TemporalType.DATE)
	private Date yearOfStudy;

	//bi-directional many-to-one association to Degree
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="degreeId")
	private Degree degree;

	//bi-directional one-to-one association to Person
	@OneToOne(fetch=FetchType.EAGER)
	@PrimaryKeyJoinColumn(name="studentId", referencedColumnName="personId")
	private Person person;

	//bi-directional many-to-one association to University
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="universityId")
	private University university;
/*
	//bi-directional many-to-one association to Transaction
	@OneToMany(mappedBy="student")
	private List<Transaction> transactions;
*/
	public Student() {
	}
	
	public int getStudentId() {
		return this.studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public Date getEnrolledYear() {
		return this.enrolledYear;
	}

	public void setEnrolledYear(Date enrolledYear) {
		this.enrolledYear = enrolledYear;
	}

	public BigDecimal getFundCollected() {
		return this.fundCollected;
	}

	public void setFundCollected(BigDecimal fundCollected) {
		this.fundCollected = fundCollected;
	}
	
	public BigDecimal getGpa() {
		return this.gpa;
	}

	public void setGpa(BigDecimal gpa) {
		this.gpa = gpa;
	}
	
	public BigDecimal getFamilyIncome() {
		return this.familyIncome;
	}

	public void setFamilyIncome(BigDecimal familyIncome) {
		this.familyIncome = familyIncome;
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

	public int getFundPetitionStatus() {
		return this.fundPetitionStatus;
	}

	public void setFundPetitionStatus(int fundPetitionStatus) {
		this.fundPetitionStatus = fundPetitionStatus;
	}

	public Date getYearOfStudy() {
		return this.yearOfStudy;
	}

	public void setYearOfStudy(Date yearOfStudy) {
		this.yearOfStudy = yearOfStudy;
	}

	public Degree getDegree() {
		return this.degree;
	}

	public void setDegree(Degree degree) {
		this.degree = degree;
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

	/*public List<Transaction> getTransactions() {
		return this.transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Transaction addTransaction(Transaction transaction) {
		getTransactions().add(transaction);
		transaction.setStudent(this);

		return transaction;
	}

	public Transaction removeTransaction(Transaction transaction) {
		getTransactions().remove(transaction);
		transaction.setStudent(null);*/

		/*return transaction;
	}*/

}