package edu.neu.cs5200.chaanda.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the university database table.
 * 
 */
@Entity
@NamedQuery(name="University.findAll", query="SELECT u FROM University u")
public class University implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int universityId;

	private int bankAccountNo;

	private int registered;

	public University(int universityId, int bankAccountNo, int registered,
			int universityAddressId, String universityName,
			List<College> colleges,
			List<Universityadminmapping> universityadminmappings) {
		super();
		this.universityId = universityId;
		this.bankAccountNo = bankAccountNo;
		this.registered = registered;
		this.universityAddressId = universityAddressId;
		this.universityName = universityName;
		this.colleges = colleges;
		this.universityadminmappings = universityadminmappings;
	}

	private int universityAddressId;

	private String universityName;

	//bi-directional many-to-one association to College
	@OneToMany(mappedBy="university")
	private List<College> colleges;

	//bi-directional many-to-one association to Universityadminmapping
	@OneToMany(mappedBy="university")
	private List<Universityadminmapping> universityadminmappings;

	public University() {
	}

	public int getUniversityId() {
		return this.universityId;
	}

	public void setUniversityId(int universityId) {
		this.universityId = universityId;
	}

	public int getBankAccountNo() {
		return this.bankAccountNo;
	}

	public void setBankAccountNo(int bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}

	public int getRegistered() {
		return this.registered;
	}

	public void setRegistered(int registered) {
		this.registered = registered;
	}

	public int getUniversityAddressId() {
		return this.universityAddressId;
	}

	public void setUniversityAddressId(int universityAddressId) {
		this.universityAddressId = universityAddressId;
	}

	public String getUniversityName() {
		return this.universityName;
	}

	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}

	public List<College> getColleges() {
		return this.colleges;
	}

	public void setColleges(List<College> colleges) {
		this.colleges = colleges;
	}

	public College addCollege(College college) {
		getColleges().add(college);
		college.setUniversity(this);

		return college;
	}

	public College removeCollege(College college) {
		getColleges().remove(college);
		college.setUniversity(null);

		return college;
	}

	public List<Universityadminmapping> getUniversityadminmappings() {
		return this.universityadminmappings;
	}

	public void setUniversityadminmappings(List<Universityadminmapping> universityadminmappings) {
		this.universityadminmappings = universityadminmappings;
	}

	public Universityadminmapping addUniversityadminmapping(Universityadminmapping universityadminmapping) {
		getUniversityadminmappings().add(universityadminmapping);
		universityadminmapping.setUniversity(this);

		return universityadminmapping;
	}

	public Universityadminmapping removeUniversityadminmapping(Universityadminmapping universityadminmapping) {
		getUniversityadminmappings().remove(universityadminmapping);
		universityadminmapping.setUniversity(null);

		return universityadminmapping;
	}

}