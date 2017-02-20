package edu.neu.cs5200.chaanda.models;

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

	private int registered;

	private int universityAddressId;

	private String universityName;

	//bi-directional many-to-one association to College
	@OneToMany(mappedBy="university")
	private List<College> colleges;
	
	//bi-directional many-to-one association to Bankaccount
		@ManyToOne(fetch=FetchType.EAGER)
		@JoinColumn(name="bankAccountId")
		private Bankaccount bankaccount;

	public University() {
	}

	public int getUniversityId() {
		return this.universityId;
	}

	public void setUniversityId(int universityId) {
		this.universityId = universityId;
	}

	public Bankaccount getBankaccount() {
		return this.bankaccount;
	}

	public void setBankaccount(Bankaccount bankaccount) {
		this.bankaccount = bankaccount;
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

}