package edu.neu.cs5200.chaanda.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the address database table.
 * 
 */
@Entity
@NamedQuery(name="Address.findAll", query="SELECT a FROM Address a")
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int addressId;

	private String city;

	private String country;

	private String state;

	private String street;

	public Address(int addressId, String city, String country, String state,
			String street, int zipCode) {
		super();
		this.addressId = addressId;
		this.city = city;
		this.country = country;
		this.state = state;
		this.street = street;
		this.zipCode = zipCode;
	}

	private int zipCode;

	//bi-directional many-to-one association to Person
	@OneToMany(mappedBy="address")
	private List<Person> persons;

	public Address() {
	}

	public int getAddressId() {
		return this.addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public List<Person> getPersons() {
		return this.persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public Person addPerson(Person person) {
		getPersons().add(person);
		person.setAddress(this);

		return person;
	}

	public Person removePerson(Person person) {
		getPersons().remove(person);
		person.setAddress(null);

		return person;
	}

}