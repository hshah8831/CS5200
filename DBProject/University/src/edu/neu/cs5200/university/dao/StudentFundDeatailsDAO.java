package edu.neu.cs5200.university.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import edu.neu.cs5200.university.model.Studentfunddetail;

public class StudentFundDeatailsDAO {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("University");
	EntityManager em = null;

	// updates the student fund detail record for  particular student with id = studentId
	public Studentfunddetail updateStudentFundDetails(int studentId, Studentfunddetail sfnd) {

		em = factory.createEntityManager();
		em.getTransaction().begin();

		sfnd.setStudentId(studentId);
		em.merge(sfnd);

		em.getTransaction().commit();
		em.close();

		return sfnd;
	}

}
