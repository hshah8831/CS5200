package edu.neu.cs5200.chaanda.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the bankaccount database table.
 * 
 */
@Entity
@NamedQuery(name="Bankaccount.findAll", query="SELECT b FROM Bankaccount b")
public class Bankaccount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idBankAccount;

	private String accountHolderEmail;

	private String accountHolderName;

	private int accountNumber;

	private int cvv;

	@Temporal(TemporalType.DATE)
	private Date expireDate;

	private int pin;

	//bi-directional many-to-one association to Bankmaster
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="bankId")
	private Bankmaster bankmaster;

	/*//bi-directional many-to-one association to Donor
	@OneToMany(mappedBy="bankaccount")
	private List<Donor> donors;*/

	//bi-directional many-to-one association to University
	@OneToMany(mappedBy="bankaccount")
	private List<University> universities;

	public Bankaccount() {
	}

	public int getIdBankAccount() {
		return this.idBankAccount;
	}

	public void setIdBankAccount(int idBankAccount) {
		this.idBankAccount = idBankAccount;
	}

	public String getAccountHolderEmail() {
		return this.accountHolderEmail;
	}

	public void setAccountHolderEmail(String accountHolderEmail) {
		this.accountHolderEmail = accountHolderEmail;
	}

	public String getAccountHolderName() {
		return this.accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public int getAccountNumber() {
		return this.accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getCvv() {
		return this.cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	public Date getExpireDate() {
		return this.expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public int getPin() {
		return this.pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public Bankmaster getBankmaster() {
		return this.bankmaster;
	}

	public void setBankmaster(Bankmaster bankmaster) {
		this.bankmaster = bankmaster;
	}

	/*public List<Donor> getDonors() {
		return this.donors;
	}

	public void setDonors(List<Donor> donors) {
		this.donors = donors;
	}

	public Donor addDonor(Donor donor) {
		getDonors().add(donor);
		donor.setBankaccount(this);

		return donor;
	}

	public Donor removeDonor(Donor donor) {
		getDonors().remove(donor);
		donor.setBankaccount(null);

		return donor;
	}*/

	public List<University> getUniversities() {
		return this.universities;
	}

	public void setUniversities(List<University> universities) {
		this.universities = universities;
	}

	public University addUniversity(University university) {
		getUniversities().add(university);
		university.setBankaccount(this);

		return university;
	}

	public University removeUniversity(University university) {
		getUniversities().remove(university);
		university.setBankaccount(null);

		return university;
	}

}