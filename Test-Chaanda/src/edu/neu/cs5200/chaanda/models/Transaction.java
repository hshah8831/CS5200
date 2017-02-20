package edu.neu.cs5200.chaanda.models;

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
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int transactionId;

	private BigDecimal amountTransferred;

	@Temporal(TemporalType.DATE)
	private Date transactionDate;
	
	private int studentId;
	
	
	private int donorId;

	//bi-directional many-to-one association to Donor
	/*@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="donorId")
	private Donor donor;

	//bi-directional many-to-one association to Student
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="studentId")
	private Student student;*/

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

	public int getStudentId() {
		return this.studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	
	public int getDonorId() {
		return this.donorId;
	}

	public void setDonorId(int donorId) {
		this.donorId = donorId;
	}
	
	public Date gettransactionDate() {
		return this.transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	

	/*public Donor getDonor() {
		return this.donor;
	}

	public void setDonor(Donor donor) {
		this.donor = donor;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}*/

}