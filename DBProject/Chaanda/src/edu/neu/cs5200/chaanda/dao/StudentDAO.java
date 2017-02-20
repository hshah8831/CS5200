package edu.neu.cs5200.chaanda.dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.neu.cs5200.chaanda.model.*;

@Path("/student")
public class StudentDAO {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("Chaanda");
	EntityManager em = null;

	//returns the person with valid username and associated password
	public Person universityLogin(String userName , String password){

		em = factory.createEntityManager();

		Person p = null;	

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
		finally{
			em.close();

		}
	}

	//Fill and edit form
	public Student filForm(Student student){

		em = factory.createEntityManager();
		em.getTransaction().begin();
		em.merge(student);
		em.getTransaction().commit();
		em.close();

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
				+ "WHERE s.degree.college.university.universityId = u.universityId "
				+ "AND u.universityId = " + universityId;

		return em.createQuery(query).getResultList();
	}

	// get students with fund request approved
	@SuppressWarnings("unchecked")
	public List<Student> getStudentsApproved(Integer universityId) {
		em = factory.createEntityManager();

		String query = "SELECT s FROM Student s, University u "
				+ "WHERE s.degree.college.university.universityId = u.universityId "
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
		String query = "SELECT s FROM Student s, University u WHERE s.degree.college.university.universityId = u.universityId AND  "
				+ "u.universityName LIKE '%"
				+ universityName
				+ "%'";
		return em.createQuery(query).getResultList();
	}

	// get students who need funding
	@SuppressWarnings("unchecked")
	public List<Student> getStudentsWantingFund() {

		em = factory.createEntityManager();
		String query = "SELECT s FROM Student s, University u  WHERE s.degree.college.university.universityId = u.universityId AND u.registered = 1";
		return em.createQuery(query).getResultList();
	}

	//exposed as web service, takes a JSON string and persists the data in database
	@POST
	@Path("/insert")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String createStudent(String sjsn) 
	{

		ObjectMapper mapper = new ObjectMapper();
		Student student = null;
		try {
			student = mapper.readValue(sjsn, Student.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		AddressDAO adao = new AddressDAO();
		adao.insertAddress(student.getPerson().getAddress());

		PersonDAO pdao = new PersonDAO();
		pdao.insertPerson(student.getPerson());

		UniversityDAO udao = new UniversityDAO();
		University u = udao.getUniversityfromCollegeID(student.getDegree().getCollege().getCollegeId());
		student.getDegree().getCollege().setUniversity(u);

		insertQueryforStudnt(student);

		insertFundDetailsQuery(student);

		return("success");

	}

	// Inserts student record to database
	public void insertQueryforStudnt(Student student)
	{
		em = factory.createEntityManager();
		em.getTransaction().begin();

		Query query = em.createNativeQuery("INSERT INTO student (studentId, enrolledYear,degreeId,gpa,familyIncome) " +
				" VALUES(?,?,?,?,?)");
		query.setParameter(1, student.getStudentId());
		query.setParameter(2, student.getEnrolledYear());
		query.setParameter(3, student.getDegree().getDegreeId());
		query.setParameter(4, student.getGpa());
		query.setParameter(5, student.getFamilyIncome());
		query.executeUpdate(); 

		em.getTransaction().commit();
		em.close();



		System.out.println("student inserted");
	}

	// Inserts student fund details of a student to database	
	public void insertFundDetailsQuery(Student student)
	{
		BigDecimal zero = new BigDecimal(0);
		em = factory.createEntityManager();
		em.getTransaction().begin();

		Query query = em.createNativeQuery("INSERT INTO studentfunddetails (studentId, fundRequired,petitionDescription,fundCollected) " +
				" VALUES(?,?,?,?)");
		query.setParameter(1, student.getStudentId());
		query.setParameter(2, student.getStudentfunddetail().getFundRequired());
		query.setParameter(3, student.getStudentfunddetail().getPetitionDescription());
		query.setParameter(4, zero);
		//query.setParameter(4, student.getStudentfunddetail().getFundCollected());
		query.executeUpdate(); 

		em.getTransaction().commit();
		em.close();

		System.out.println("student inserted");

	}

	//deletes person from database
	public void deletePerson(Integer studentId){
		em = factory.createEntityManager();
		em.getTransaction().begin();

		Person p = em.find(Person.class, studentId);
		em.remove(p);

		em.getTransaction().commit();
		em.close();
	}

	// updates student's fund details to database
	public void updateStudentFundDetail(Studentfunddetail student) {

		em = factory.createEntityManager();
		em.getTransaction().begin();

		//student.setStudentId(studentId);
		em.merge(student);

		em.getTransaction().commit();
		em.close();
	}


}


