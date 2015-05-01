package edu.neu.cs5200.chaanda.dao;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.neu.cs5200.chaanda.model.*;

@Path("/student")
public class StudentDAO {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("Chaanda");
	EntityManager em = null;

	//student login
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

	//Exports the student to an xml file.
	public void exportStudentDatabaseToXmlFile(Student student, String xmlFileName) 
	{

		try 
		{
			JAXBContext jaxb = JAXBContext.newInstance(Student.class);
			Marshaller marshaller = jaxb.createMarshaller();
			marshaller.marshal(student, new File(xmlFileName));


		} catch (JAXBException e) 
		{
			e.printStackTrace();
		}
	}

	//Exports the student to an xml file.
	public Student persistXmlFiletoStudent(File xmlFileName) 
	{
		JAXBContext context;
		try 
		{
			//			JAXBContext jaxb = JAXBContext.newInstance(Student.class);
			//			Unmarshaller unmarshaller = jaxb.createUnmarshaller();
			//			return (Student) unmarshaller.unmarshal(xmlFileName);

			context = JAXBContext.newInstance(Student.class);
			Unmarshaller m = context.createUnmarshaller();
			//StreamSource source = new StreamSource(new StringReader(input))
			return (Student) m.unmarshal(xmlFileName);
		} catch (JAXBException e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	@POST
	@Path("/insert")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String createStudent(String sjsn) 
	{
		//System.out.println(student);

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
		//		Person p = student.getPerson();
		//		p.setDateOfBirth(null);
		pdao.insertPerson(student.getPerson());

		UniversityDAO udao = new UniversityDAO();
		University u = udao.getUniversityfromCollegeID(student.getDegree().getCollege().getCollegeId());
		student.getDegree().getCollege().setUniversity(u);


		//student.set

		//insertStudent(student);

		insertQueryforStudnt(student);
		
		insertFundDetailsQuery(student);

		return("success");

	}

	public void insertQueryforStudnt(Student student)
	{
		em = factory.createEntityManager();
		em.getTransaction().begin();

		//		String query = "INSERT INTO Student s VALUES " + student.getStudentId() + "," + 
		//				student.getEnrolledYear() + "," +
		//				student.getDegree().getDegreeId() + "," +
		//				student.getGpa() + "," +
		//				student.getFamilyIncome();

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

	public void insertStudent(Student student)
	{
		em = factory.createEntityManager();
		em.getTransaction().begin();

		//		em.persist(student.getPerson().getAddress());
		//		//em.persist(student.getPerson());
		//		//em.persist(student.);
		em.persist(student.getStudentfunddetail());
		em.persist(student);

		em.getTransaction().commit();
		em.close();
	}


	public void deletePerson(Integer studentId){
		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Person p = em.find(Person.class, studentId);
		em.remove(p);
		
		em.getTransaction().commit();
		em.close();
	}
	
	public void updateStudentFundDetail(Studentfunddetail student) {

		em = factory.createEntityManager();
		em.getTransaction().begin();

		//student.setStudentId(studentId);
		em.merge(student);

		em.getTransaction().commit();
		em.close();
	}


	public static void main(String[] args) 
	{
		StudentDAO dao = new StudentDAO();
		Date date = new Date();
		BigDecimal big = new BigDecimal(44000);
		BigDecimal gpa = new BigDecimal(3.5);

		//		List<Site> sites = dao.findAllSites();
		/*Student student = dao.getStudentDetailsForStudentLandingPage(1);
		dao.createStudent(student);

		System.out.println(student.getStudentId());
		System.out.println(student.getPerson().getPersonName());
		System.out.println(student.getDegree().getCollege().getCollegeName());*/

		//dao.exportStudentDatabaseToXmlFile(student, "student.xml");

		//		String sjsn = {"studentId":103,"enrolledYear":1420434000000,"familyIncome":1000.00,"gpa":3.5,"degree":{"degreeId":1,"degreeName":"MSCS","duration":2,"tuition":44000,"college":{"collegeId":2,"collegeName":"CCIS","university":null}},"person":{"personId":103,"dateOfBirth":594277200000,"email":"shah.hard@husky.neu.edu","gender":"male","password":"abcusa123","personName":"Hardik Shah","phone":123456789,"roleName":"student","userName":"shah.hard","address":{"addressId":1,"city":"Boston","country":"USA","state":"MA","street":"12C Smith Street","zipCode":2120}},"studentfunddetail":{"studentId":103,"fundPetitionStatus":1,"fundRequired":10.00,"petitionDescription":"Please fund me"}}""

		Address a = new Address(10,"Boston","USA","MA","12C Smith Street",2120);
		Person p = new Person(103,date,"shah.hard@husky.neu.edu","male","abcusa123","Hardik Shah",123456789,"student","shah.hard",a);
		University u = null;
		College c = new College(2,"CCIS",null);
		Degree d = new Degree(1,"MSCS",2,big,c);
		//Studentfunddetail sfnd = new Studentfunddetail(p.getPersonId(), big, big, null, 0, null);
		//Student s = new Student(p.getPersonId(),date, big, gpa, d, sfnd, null, p);

		//dao.insertFundDetailsQuery(s);

		AddressDAO adao = new AddressDAO();
		//adao.insertAddress(a);
		//		
		//		PersonDAO pdao = new PersonDAO();
		//		pdao.insertPerson(p);
		//		
		//		//CollegeDAO cdao = new CollegeDAO();
		//		//cdao.insertCollege();
		//		
		//		DegreeDAO ddao = new DegreeDAO();
		//		//ddao.insertDegree();
		//		
		//dao.insertQueryforStudnt(s);

		//File file = new File("student-3.xml");

		//Student student = dao.persistXmlFiletoStudent(file);

		//		System.out.println(student.getStudentId());
		//		System.out.println(student.getPerson().getPersonName());

		//		Address a = new Address();
		//		a.setCity(student.getPerson().getAddress().getCity());
		//		a.setCountry(student.getPerson().getAddress().getCountry());
		//		a.setState(student.getPerson().getAddress().getState());
		//		a.setStreet(student.getPerson().getAddress().getStreet());
		//		a.setZipCode(student.getPerson().getAddress().getZipCode());
		//
		//		AddressDAO aDao = new AddressDAO();
		//		aDao.insertAddress(a);
		//
		//		Person p = new Person();
		//		p.setAddress(a);
		//		p.setEmail(student.getPerson().getEmail());
		//		p.setPassword(student.getPerson().getPassword());
		//		p.setRoleName(student.getPerson().getRoleName());
		//
		//		p.setPersonName(student.getPerson().getPersonName());
		//		//p.setRole(r);
		//		p.setUserName(student.getPerson().getUserName());
		//
		//		PersonDAO pDao = new PersonDAO();
		//		pDao.insertPerson(p);
		//		
		//		Degree d = new Degree();
		//		d.setCollegeName(student.getDegree().getCollegeName());
		//		d.setDegreeName(student.getDegree().getDegreeName());
		//		d.setDuration(student.getDegree().getDuration());
		//		d.setTuition(student.getDegree().getTuition());
		//		DegreeDAO dDao = new DegreeDAO();
		//		dDao.createStudent(d);


		//System.out.println(student.getDegree().getUniversity().getUniversityName());

		//		dao.convertXmlFileToOutputFile("sites.xml", "sites2equipment.html", "sites2equipment.xsl");

		//List<Student> studentswantingfund = dao.getStudentFromUni("wentworth");

		//		for(Student s : studentswantingfund){
		//			System.out.println(s.getStudentId());
		//		}


	}
}

