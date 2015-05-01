package edu.neu.cs5200.university.dao;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.persistence.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.*;

import edu.neu.cs5200.university.model.*;


public class StudentDAO {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("University");
	EntityManager em = null;

	//Get student details for student from the studentID
	public Student getStudentDetailsForStudentLandingPage(Integer studentId){

		em = factory.createEntityManager();
		return em.find(Student.class, studentId);
	}	

	// get students who are waiting for approval from the admin
	@SuppressWarnings("unchecked")
	public List<Student> getStudentsWaiting() {

		em = factory.createEntityManager();

		String query = "SELECT s FROM Student s WHERE s.studentfunddetail.fundPetitionStatus = 1 ";

		return em.createQuery(query).getResultList();
	}

	// get all the admin approved students
	@SuppressWarnings("unchecked")
	public List<Student> getStudentsApproved() {
		em = factory.createEntityManager();

		String query = "SELECT s FROM Student s WHERE s.studentfunddetail.fundPetitionStatus = 2 ";

		return em.createQuery(query).getResultList();
	}

	//updates the student with particular studentId
	public void updateStudent(int studentId, Student student) {

		em = factory.createEntityManager();
		em.getTransaction().begin();

		student.setStudentId(studentId);
		em.merge(student);

		em.getTransaction().commit();
		em.close();
	}

	//Converts the Student record into a JSON and send it over the http post request 
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
}

