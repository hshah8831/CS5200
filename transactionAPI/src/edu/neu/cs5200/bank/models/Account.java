package edu.neu.cs5200.bank.models;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the account database table.
 * 
 */
@Entity
@NamedQuery(name="Account.findAll", query="SELECT a FROM Account a")
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int accountNo;

	private BigDecimal balance;

	private String holderName;

	private String passKey;

	//bi-directional many-to-one association to Transaction
	@OneToMany(mappedBy="account1")
	private List<Transaction> transactions1;

	//bi-directional many-to-one association to Transaction
	@OneToMany(mappedBy="account2")
	private List<Transaction> transactions2;

	public Account() {
	}

	public int getAccountNo() {
		return this.accountNo;
	}

	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}

	public BigDecimal getBalance() {
		return this.balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getHolderName() {
		return this.holderName;
	}

	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	public String getPassKey() {
		return this.passKey;
	}

	public void setPassKey(String passKey) {
		this.passKey = passKey;
	}

	public List<Transaction> getTransactions1() {
		return this.transactions1;
	}

	public void setTransactions1(List<Transaction> transactions1) {
		this.transactions1 = transactions1;
	}

	public Transaction addTransactions1(Transaction transactions1) {
		getTransactions1().add(transactions1);
		transactions1.setAccount1(this);

		return transactions1;
	}

	public Transaction removeTransactions1(Transaction transactions1) {
		getTransactions1().remove(transactions1);
		transactions1.setAccount1(null);

		return transactions1;
	}

	public List<Transaction> getTransactions2() {
		return this.transactions2;
	}

	public void setTransactions2(List<Transaction> transactions2) {
		this.transactions2 = transactions2;
	}

	public Transaction addTransactions2(Transaction transactions2) {
		getTransactions2().add(transactions2);
		transactions2.setAccount2(this);

		return transactions2;
	}

	public Transaction removeTransactions2(Transaction transactions2) {
		getTransactions2().remove(transactions2);
		transactions2.setAccount2(null);

		return transactions2;
	}

}