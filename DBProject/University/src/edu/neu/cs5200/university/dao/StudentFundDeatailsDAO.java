package edu.neu.cs5200.university.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import edu.neu.cs5200.university.model.Studentfunddetail;

public class StudentFundDeatailsDAO {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("University");
	EntityManager em = null;
	
	// get students who are waiting for approval
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
