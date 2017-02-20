package edu.neu.cs5200.chaanda.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the person database table.
 * 
 */
@Entity
@NamedQuery(name="Person.findAll", query="SELECT p FROM Person p")
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int personId;

	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;

	public Person(int personId, Date dateOfBirth, String email, String gender,
			String password, String personName, int phone, String roleName,
			String userName, Address address) {
		super();
		this.personId = personId;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.gender = gender;
		this.password = password;
		this.personName = personName;
		this.phone = phone;
		this.roleName = roleName;
		this.userName = userName;
		this.address = address;
		this.transactions = transactions;
		this.universityadminmappings = universityadminmappings;
	}

	private String email;

	private String gender;

	private String password;

	private String personName;

	private int phone;

	private String roleName;

	private String userName;

	//bi-directional many-to-one association to Address
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="addressId")
	private Address address;

	//bi-directional many-to-one association to Transaction
	@OneToMany(mappedBy="person")
	private List<Transaction> transactions;

	//bi-directional many-to-one association to Universityadminmapping
	@OneToMany(mappedBy="person")
	private List<Universityadminmapping> universityadminmappings;

	public Person() {
	}

	public int getPersonId() {
		return this.personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPersonName() {
		return this.personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public int getPhone() {
		return this.phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Transaction> getTransactions() {
		return this.transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Transaction addTransaction(Transaction transaction) {
		getTransactions().add(transaction);
		transaction.setPerson(this);

		return transaction;
	}

	public Transaction removeTransaction(Transaction transaction) {
		getTransactions().remove(transaction);
		transaction.setPerson(null);

		return transaction;
	}

	public List<Universityadminmapping> getUniversityadminmappings() {
		return this.universityadminmappings;
	}

	public void setUniversityadminmappings(List<Universityadminmapping> universityadminmappings) {
		this.universityadminmappings = universityadminmappings;
	}

	public Universityadminmapping addUniversityadminmapping(Universityadminmapping universityadminmapping) {
		getUniversityadminmappings().add(universityadminmapping);
		universityadminmapping.setPerson(this);

		return universityadminmapping;
	}

	public Universityadminmapping removeUniversityadminmapping(Universityadminmapping universityadminmapping) {
		getUniversityadminmappings().remove(universityadminmapping);
		universityadminmapping.setPerson(null);

		return universityadminmapping;
	}

}