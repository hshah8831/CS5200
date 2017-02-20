package edu.neu.cs5200.chaanda.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the bankmaster database table.
 * 
 */
@Entity
@NamedQuery(name="Bankmaster.findAll", query="SELECT b FROM Bankmaster b")
public class Bankmaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int bankId;

	private String bankName;

	private String bankRouting;

	private String bankSwiftCode;

	//bi-directional many-to-one association to Bankaccount
	@OneToMany(mappedBy="bankmaster")
	private List<Bankaccount> bankaccounts;

	//bi-directional many-to-one association to Address
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="bankAddressId")
	private Address address;

	public Bankmaster() {
	}

	public int getBankId() {
		return this.bankId;
	}

	public void setBankId(int bankId) {
		this.bankId = bankId;
	}

	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankRouting() {
		return this.bankRouting;
	}

	public void setBankRouting(String bankRouting) {
		this.bankRouting = bankRouting;
	}

	public String getBankSwiftCode() {
		return this.bankSwiftCode;
	}

	public void setBankSwiftCode(String bankSwiftCode) {
		this.bankSwiftCode = bankSwiftCode;
	}

	public List<Bankaccount> getBankaccounts() {
		return this.bankaccounts;
	}

	public void setBankaccounts(List<Bankaccount> bankaccounts) {
		this.bankaccounts = bankaccounts;
	}

	public Bankaccount addBankaccount(Bankaccount bankaccount) {
		getBankaccounts().add(bankaccount);
		bankaccount.setBankmaster(this);

		return bankaccount;
	}

	public Bankaccount removeBankaccount(Bankaccount bankaccount) {
		getBankaccounts().remove(bankaccount);
		bankaccount.setBankmaster(null);

		return bankaccount;
	}

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}