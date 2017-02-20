package edu.neu.cs5200.chaanda.model;

import java.io.IOException;
import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the student database table.
 * 
 */
@Entity
@NamedQuery(name="Student.findAll", query="SELECT s FROM Student s")
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int studentId;

	@Temporal(TemporalType.DATE)
	private Date enrolledYear;

	private BigDecimal familyIncome;

	private BigDecimal gpa;

	//bi-directional many-to-one association to Degree
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="degreeId")
	private Degree degree;

	//bi-directional one-to-one association to Studentfunddetail
	@OneToOne(mappedBy="student")
	private Studentfunddetail studentfunddetail;

	//bi-directional many-to-one association to Transaction
	@OneToMany(mappedBy="student")
	private List<Transaction> transactions;
	
	//bi-directional one-to-one association to Person
	@OneToOne(fetch=FetchType.EAGER)
	@PrimaryKeyJoinColumn(name="studentId", referencedColumnName="personId")
	private Person person;

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

	public BigDecimal getFamilyIncome() {
		return this.familyIncome;
	}

	public void setFamilyIncome(BigDecimal familyIncome) {
		this.familyIncome = familyIncome;
	}

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

	public Studentfunddetail getStudentfunddetail() {
		return this.studentfunddetail;
	}

	public void setStudentfunddetail(Studentfunddetail studentfunddetail) {
		this.studentfunddetail = studentfunddetail;
	}

	public List<Transaction> getTransactions() {
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
		transaction.setStudent(null);

		return transaction;
	}
	
	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	public Student(String jsonString)
	{
		ObjectMapper mapper = new ObjectMapper();
		//Map<String,Object> map = mapper.readValue(json, Map.class);
		try {
			this.degree = mapper.readValue(jsonString, Student.class).degree;
			this.enrolledYear = mapper.readValue(jsonString, Student.class).enrolledYear;
			this.familyIncome = mapper.readValue(jsonString, Student.class).familyIncome;
			this.transactions = mapper.readValue(jsonString, Student.class).transactions;
			this.studentfunddetail = mapper.readValue(jsonString, Student.class).studentfunddetail;
			this.person = mapper.readValue(jsonString, Student.class).person;
			this.degree.setCollege(mapper.readValue(jsonString, Student.class).degree.getCollege());
			this.degree.getCollege().setUniversity(mapper.readValue(jsonString, Student.class).degree.getCollege().getUniversity());
			
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Student(int studentId, Date enrolledYear, BigDecimal familyIncome,
			BigDecimal gpa, Degree degree, Studentfunddetail studentfunddetail,
			List<Transaction> transactions, Person person) {
		super();
		this.studentId = studentId;
		this.enrolledYear = enrolledYear;
		this.familyIncome = familyIncome;
		this.gpa = gpa;
		this.degree = degree;
		this.studentfunddetail = studentfunddetail;
		this.transactions = transactions;
		this.person = person;
	}

}