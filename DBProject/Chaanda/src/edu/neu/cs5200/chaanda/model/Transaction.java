package edu.neu.cs5200.chaanda.model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the transaction database table.
 * 
 */
@Entity
@NamedQuery(name="Transaction.findAll", query="SELECT t FROM Transaction t")
public class Transaction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int transactionId;

	private BigDecimal amountTransferred;

	@Temporal(TemporalType.DATE)
	private Date transactionDate;

	public Transaction(int transactionId, BigDecimal amountTransferred,
			Date transactionDate, Person person, Student student) {
		super();
		this.transactionId = transactionId;
		this.amountTransferred = amountTransferred;
		this.transactionDate = transactionDate;
		this.person = person;
		this.student = student;
	}

	//bi-directional many-to-one association to Person
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="donorId")
	private Person person;

	//bi-directional many-to-one association to Student
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="studentId")
	private Student student;

	public Transaction() {
	}

	public int getTransactionId() {
		return this.transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public BigDecimal getAmountTransferred() {
		return this.amountTransferred;
	}

	public void setAmountTransferred(BigDecimal amountTransferred) {
		this.amountTransferred = amountTransferred;
	}

	public Date getTransactionDate() {
		return this.transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}