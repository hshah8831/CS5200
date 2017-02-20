package edu.neu.cs5200.university.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the student database table.
 * 
 */
@XmlRootElement
@Entity
@NamedQuery(name="Student.findAll", query="SELECT s FROM Student s")
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int studentId;

	@Temporal(TemporalType.DATE)
	private Date enrolledYear;

	private BigDecimal familyIncome;

	private BigDecimal gpa;

	//bi-directional many-to-one association to Degree
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="degreeId")
	private Degree degree;

	//bi-directional one-to-one association to Person
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="studentId")
	@PrimaryKeyJoinColumn
	private Person person;

	//bi-directional one-to-one association to Studentfunddetail
	@OneToOne(mappedBy="student", fetch=FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private Studentfunddetail studentfunddetail;

	public Student() {
	}

	@XmlAttribute
	public int getStudentId() {
		return this.studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	@XmlAttribute
	public Date getEnrolledYear() {
		return this.enrolledYear;
	}

	public void setEnrolledYear(Date enrolledYear) {
		this.enrolledYear = enrolledYear;
	}

	@XmlAttribute
	public BigDecimal getFamilyIncome() {
		return this.familyIncome;
	}

	public void setFamilyIncome(BigDecimal familyIncome) {
		this.familyIncome = familyIncome;
	}

	@XmlAttribute
	public BigDecimal getGpa() {
		return this.gpa;
	}

	public void setGpa(BigDecimal gpa) {
		this.gpa = gpa;
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

	public Studentfunddetail getStudentfunddetail() {
		return this.studentfunddetail;
	}

	public void setStudentfunddetail(Studentfunddetail studentfunddetail) {
		this.studentfunddetail = studentfunddetail;
	}

}