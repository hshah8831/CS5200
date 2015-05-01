package edu.neu.cs5200.university.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import edu.neu.cs5200.university.model.Degree;

public class DegreeDAO {
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("University");
	EntityManager em = null;

	//get tuition fee of student
	public Degree getTuitionFee(Integer degreeId){
		return em.find(Degree.class, degreeId);

	}

}
