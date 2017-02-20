package edu.neu.cs5200.chaanda.model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the degree database table.
 * 
 */
@Entity
@NamedQuery(name="Degree.findAll", query="SELECT d FROM Degree d")
public class Degree implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int degreeId;

	private String degreeName;

	public Degree(int degreeId, String degreeName, int duration,
			BigDecimal tuition, College college) {
		super();
		this.degreeId = degreeId;
		this.degreeName = degreeName;
		this.duration = duration;
		this.tuition = tuition;
		this.college = college;
	}

	private int duration;

	private BigDecimal tuition;

	//bi-directional many-to-one association to College
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="collegeId")
	private College college;

	//bi-directional many-to-one association to Student
	@OneToMany(mappedBy="degree")
	private List<Student> students;

	public Degree() {
	}

	public int getDegreeId() {
		return this.degreeId;
	}

	public void setDegreeId(int degreeId) {
		this.degreeId = degreeId;
	}

	public String getDegreeName() {
		return this.degreeName;
	}

	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}

	public int getDuration() {
		return this.duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public BigDecimal getTuition() {
		return this.tuition;
	}

	public void setTuition(BigDecimal tuition) {
		this.tuition = tuition;
	}

	public College getCollege() {
		return this.college;
	}

	public void setCollege(College college) {
		this.college = college;
	}

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

}