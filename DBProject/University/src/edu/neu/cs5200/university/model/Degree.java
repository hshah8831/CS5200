package edu.neu.cs5200.university.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the degree database table.
 * 
 */
@XmlRootElement
@JsonIgnoreProperties(value = { "students" })
@Entity
@NamedQuery(name="Degree.findAll", query="SELECT d FROM Degree d")
public class Degree implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int degreeId;

	private String degreeName;

	private int duration;

	private BigDecimal tuition;

	//bi-directional many-to-one association to Student
	@OneToMany(mappedBy="degree")
	private List<Student> students;

	//bi-directional many-to-one association to College
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="collegeId")
	private College college;

	public Degree() {
	}

	@XmlAttribute
	public int getDegreeId() {
		return this.degreeId;
	}

	public void setDegreeId(int degreeId) {
		this.degreeId = degreeId;
	}

	@XmlAttribute
	public String getDegreeName() {
		return this.degreeName;
	}

	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}

	@XmlAttribute
	public int getDuration() {
		return this.duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	@XmlAttribute
	public BigDecimal getTuition() {
		return this.tuition;
	}

	public void setTuition(BigDecimal tuition) {
		this.tuition = tuition;
	}

	@XmlTransient
	public List<Student> getStudents() {
		return this.students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public Student addStudent(Student student) {
		getStudents().add(student);
		student.setDegree(this);

		return student;
	}

	public Student removeStudent(Student student) {
		getStudents().remove(student);
		student.setDegree(null);

		return student;
	}

	public College getCollege() {
		return this.college;
	}

	public void setCollege(College college) {
		this.college = college;
	}

}