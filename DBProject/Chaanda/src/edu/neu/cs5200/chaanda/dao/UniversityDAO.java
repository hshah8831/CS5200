package edu.neu.cs5200.chaanda.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import edu.neu.cs5200.chaanda.model.University;

public class UniversityDAO {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("Chaanda");
	EntityManager em = null;

	//Getting all the registered university
	public List<University> getRegisteredUniversity()
	{
		em = factory.createEntityManager();

		@SuppressWarnings("unchecked")
		List<University> lou = em.createQuery( "SELECT u FROM University u WHERE u.registered = 1").getResultList();

		return lou;
	}
	// Getting university from name
	@SuppressWarnings("unchecked")
	public List<University> getUniversity(String universityName)
	{
		em = factory.createEntityManager();
		return em.createQuery(
				"SELECT u FROM University u WHERE u.universityName = :name")
				.setParameter("name", universityName)
				.setMaxResults(10)
				.getResultList();

	}

	// Getting university from name
	@SuppressWarnings("unchecked")
	public List<University> getUniversityFromName(String universityName)
	{
		em = factory.createEntityManager();

		List<University> unis = 
				em.createQuery(
						"SELECT u FROM University u WHERE u.universityName = :name")
						.setParameter("name", universityName)
						.setMaxResults(10)
						.getResultList();

		return unis;						
	}

	// get university details of logged in admin
	@SuppressWarnings("unchecked")
	public University getUniversityforadmin(int personId)
	{
		em = factory.createEntityManager();
		String query = "SELECT u FROM Universityadminmapping um , University u WHERE um.university.universityId = u.universityId AND  "
				+ "um.person.personId LIKE '%"
				+ personId
				+ "%'";
		List<University> unis = em.createQuery(query).getResultList();
		return unis.get(0);

	}

	//updates the university record in the database
	public void registerUniversity(Integer uId , University uni){
		em = factory.createEntityManager();
		em.getTransaction().begin();

		uni.setUniversityId(uId);

		em.merge(uni);
		em.getTransaction().commit();
		em.close();

	}

	//fetches the university associated with the given college
	@SuppressWarnings("unchecked")
	public University getUniversityfromCollegeID(Integer collegeId)
	{
		em = factory.createEntityManager();
		String query = "SELECT u FROM College c , University u WHERE c.university.universityId = u.universityId AND  "
				+ "c.collegeId ="
				+ collegeId;
		List<University> unis = em.createQuery(query).getResultList();
		return unis.get(0);
	}
}
