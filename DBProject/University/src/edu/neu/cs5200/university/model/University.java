package edu.neu.cs5200.university.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * The persistent class for the university database table.
 * 
 */
@XmlRootElement
@JsonIgnoreProperties(value = { "college" })
@Entity
@NamedQuery(name="University.findAll", query="SELECT u FROM University u")
public class University implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int universityId;

	private int bankAccountNo;

	private int universityAddressId;

	private String universityName;

	//bi-directional one-to-one association to College
	@OneToOne(mappedBy="university")
	private College college;

	public University() {
	}

	@XmlAttribute
	public int getUniversityId() {
		return this.universityId;
	}

	public void setUniversityId(int universityId) {
		this.universityId = universityId;
	}

	@XmlAttribute
	public int getBankAccountNo() {
		return this.bankAccountNo;
	}

	public void setBankAccountNo(int bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}

	@XmlAttribute
	public int getUniversityAddressId() {
		return this.universityAddressId;
	}

	public void setUniversityAddressId(int universityAddressId) {
		this.universityAddressId = universityAddressId;
	}

	@XmlAttribute
	public String getUniversityName() {
		return this.universityName;
	}

	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}

	@XmlTransient
	public College getCollege() {
		return this.college;
	}

	public void setCollege(College college) {
		this.college = college;
	}

}