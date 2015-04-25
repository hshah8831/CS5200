package edu.neu.cs5200.chaanda.dao;

import java.util.List;

import javax.persistence.*;

import edu.neu.cs5200.chaanda.models.*;


public class StudentDAO {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("Test-Chaanda");
	EntityManager em = null;

	//student login
	public Person universityLogin(String userName , String password){

		em = factory.createEntityManager();

		Person p = null;

		//String query = "SELECT p FROM Person p WHERE p.userName = " + userName;

		try{
			p = (Person) em.createQuery(
					"SELECT p FROM Person p WHERE p.userName = :name")
					.setParameter("name", userName)
					.getSingleResult();

			if(p.getPassword().equals(password)){
				return p;
			}
			else{
				return null;
			}
		}

		catch(NoResultException e){
			return null;
		}
	}


	//Fill and edit form
	public Student filForm(Student student){

		em = factory.createEntityManager();
		em.getTransaction().begin();
		em.merge(student);
		em.getTransaction().commit();

		return student;
	}

	//Get student details for student landing page
	public Student getStudentDetailsForStudentLandingPage(Integer studentId){

		em = factory.createEntityManager();
		return em.find(Student.class, studentId);
	}	

	//get tuition fee of student
	public Degree getTuitionFee(Integer degreeId){
		return em.find(Degree.class, degreeId);

	}

	// get students who are waiting for approval
	@SuppressWarnings("unchecked")
	public List<Student> getStudentsWaiting(Integer universityId) {

		em = factory.createEntityManager();

		String query = "SELECT s FROM Student s, University u "
				+ "WHERE s.fundPetitionStatus = 1 "
				+ "AND s.university.universityId = u.universityId "
				+ "AND u.universityId = " + universityId;

		return em.createQuery(query).getResultList();
	}

	// get students with fund request approved
	@SuppressWarnings("unchecked")
	public List<Student> getStudentsApproved(Integer universityId) {
		em = factory.createEntityManager();

		String query = "SELECT s FROM Student s, University u "
				+ "WHERE s.fundPetitionStatus = 2 "
				+ "AND s.university.universityId = u.universityId "
				+ "AND u.universityId = " + universityId;

		return em.createQuery(query).getResultList();
	}

	// get students who are waiting for approval
	public void updateStudent(int studentId, Student student) {

		em = factory.createEntityManager();
		em.getTransaction().begin();

		student.setStudentId(studentId);
		em.merge(student);

		em.getTransaction().commit();
		em.close();
	}

	//get student wanting funds, from a the selected university
	@SuppressWarnings("unchecked")
	public List<Student> getStudentFromUni(String universityName) {

		em = factory.createEntityManager();
		String query = "SELECT s FROM Student s, University u WHERE s.university.universityId = u.universityId AND  "
				+ "u.universityName LIKE '%"
				+ universityName
				+ "%' AND s.fundPetitionStatus = 2";
		return em.createQuery(query).getResultList();
	}

	// get students who need funding
	@SuppressWarnings("unchecked")
	public List<Student> getStudentsWantingFund() {

		em = factory.createEntityManager();
		String query = "SELECT s FROM Student s, University u WHERE s.university.universityId = u.universityId AND u.registered = 1 AND s.fundPetitionStatus = 2";
		return em.createQuery(query).getResultList();
	}
}

