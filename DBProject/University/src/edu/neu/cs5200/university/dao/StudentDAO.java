package edu.neu.cs5200.university.dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.net.*;

import edu.neu.cs5200.university.model.*;

import java.io.FileInputStream;


public class StudentDAO {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("University");
	EntityManager em = null;

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

	// get students who are waiting for approval
	@SuppressWarnings("unchecked")
	public List<Student> getStudentsWaiting() {

		em = factory.createEntityManager();

		String query = "SELECT s FROM Student s WHERE s.studentfunddetail.fundPetitionStatus = 1 ";

		return em.createQuery(query).getResultList();
	}

	// get students with fund request approved
	@SuppressWarnings("unchecked")
	public List<Student> getStudentsApproved() {
		em = factory.createEntityManager();

		String query = "SELECT s FROM Student s WHERE s.studentfunddetail.fundPetitionStatus = 2 ";

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

	@SuppressWarnings("resource")
	public void sendxmlfileoverhttp(String strXMLFilename)
	{

		try {

			String url = "https://http://localhost:8080/";
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();


			//add request header
			try {
				con.setRequestMethod("POST");
			} catch (ProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			con.setRequestProperty("Content-Type", "application/octet-stream");

			String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";

			// Send post request
			con.setDoOutput(true);
			con.connect();
			int BUFFER_SIZE = 100;
			BufferedInputStream fileIn = 
					new BufferedInputStream(new FileInputStream(strXMLFilename), BUFFER_SIZE );
			BufferedOutputStream out = 
					new BufferedOutputStream(con.getOutputStream(), BUFFER_SIZE);
			byte[] bytes = new byte[BUFFER_SIZE];
			int bytesRead;
			while((bytesRead = fileIn.read(bytes)) != -1){
				out.write(bytes, 0, bytesRead);
			}
			//			DataOutputStream wr;
			//			wr = new DataOutputStream(con.getOutputStream());
			//			wr.writeBytes(urlParameters);
			//			wr.flush();
			//			wr.close();
			//			int responseCode = con.getResponseCode();
			//			System.out.println("\nSending 'POST' request to URL : " + url);
			//			System.out.println("Post parameters : " + urlParameters);
			//			System.out.println("Response Code : " + responseCode);
			//	 
			//			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			//			String inputLine;
			//			StringBuffer response = new StringBuffer();
			//	 
			//			while ((inputLine = in.readLine()) != null) {
			//				response.append(inputLine);
			//			}
			//			in.close();
			//			System.out.println(response.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String convertStudenttoJSON(Student student)
	{
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json;
		try {
			json = ow.writeValueAsString(student);
			return json;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Integer post(Student student){

		try{

			// 1. URL
			URL url = new URL("http://localhost:8080/Chaanda/rest/student/insert");

			// 2. Open connection
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			// 3. Specify POST method
			conn.setRequestMethod("POST");

			// 4. Set the headers
			conn.setRequestProperty("Content-Type", "application/json");
			//conn.setRequestProperty("Authorization", "key="+apiKey);

			conn.setDoOutput(true);

			// 5. Add JSON data into POST request body 

			//`5.1 Use Jackson object mapper to convert Contnet object into JSON
			ObjectMapper mapper = new ObjectMapper();

			// 5.2 Get connection output stream
			DataOutputStream wr = new DataOutputStream(conn.getOutputStream());

			// 5.3 Copy Content "JSON" into 
			mapper.writeValue(wr, student);

			// 5.4 Send the request
			wr.flush();

			// 5.5 close
			wr.close();

			// 6. Get the response
			int responseCode = conn.getResponseCode();
			System.out.println("\nSending 'POST' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// 7. Print result
			System.out.println(response.toString());
			return responseCode;

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	public static void main(String[] args) 
	{
		StudentDAO dao = new StudentDAO();
		//		List<Site> sites = dao.findAllSites();
		Student student = dao.getStudentDetailsForStudentLandingPage(107);
		//		System.out.println(student.getPerson().getPersonName());
		//		System.out.println(student.getEnrolledYear());
		//dao.exportStudentDatabaseToXmlFile(student, "student-2.xml");
		//dao.sendxmlfileoverhttp("student-2.xml");

		//System.out.println(student.getDegree().getCollege().getUniversity().getUniversityName());
		System.out.println(dao.convertStudenttoJSON(student));

		//dao.exportStudentDatabaseToXmlFile(student, "student-3.xml");

		//dao.post(student);

		//		dao.convertXmlFileToOutputFile("sites.xml", "sites2equipment.html", "sites2equipment.xsl");

	}
}

