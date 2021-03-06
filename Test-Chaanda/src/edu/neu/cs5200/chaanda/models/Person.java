package edu.neu.cs5200.chaanda.models;

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
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int personId;

	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;

	private String email;

	private String gender;

	private String password;

	private String personName;

	private int phone;

	private String userName;

	/*//bi-directional one-to-one association to Donor
	@OneToOne(mappedBy="person", fetch=FetchType.LAZY)
	//@PrimaryKeyJoinColumn(name="personId", referencedColumnName="donorId")
	private Donor donor;*/

	//bi-directional many-to-one association to Address
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="addressId")
	private Address address;

	//bi-directional many-to-one association to Role
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="roleId")
	private Role role;

	//bi-directional one-to-one association to Student
	@OneToOne(mappedBy="person", fetch=FetchType.LAZY)
	//@PrimaryKeyJoinColumn(name="personId", referencedColumnName="studentId")
	private Student student;

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

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/*public Donor getDonor() {
		return this.donor;
	}

	public void setDonor(Donor donor) {
		this.donor = donor;
	}*/

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
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