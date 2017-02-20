package edu.neu.cs5200.university.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


/**
 * The persistent class for the address database table.
 * 
 */
@XmlRootElement
@JsonIgnoreProperties(value = { "persons" })
@Entity
@NamedQuery(name="Address.findAll", query="SELECT a FROM Address a")
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int addressId;

	private String city;

	private String country;

	private String state;

	private String street;

	private int zipCode;

	//bi-directional many-to-one association to Person
	@OneToMany(mappedBy="address")
	private List<Person> persons;

	public Address() {
	}

	@XmlAttribute
	public int getAddressId() {
		return this.addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	@XmlAttribute
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@XmlAttribute
	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	@XmlAttribute
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@XmlAttribute
	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	@XmlAttribute
	public int getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	@XmlTransient
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