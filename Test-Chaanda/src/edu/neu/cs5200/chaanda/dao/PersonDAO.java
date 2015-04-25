package edu.neu.cs5200.chaanda.dao;

import javax.persistence.*;

import edu.neu.cs5200.chaanda.models.*;

public class PersonDAO {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("Test-Chaanda");
	EntityManager em = null;
	
	public Person getPersonDetailsForStudentLandingPage(Integer personId){
		em = factory.createEntityManager();
		return em.find(Person.class, personId);
	}
	
	public void insertPerson(Person person)
	{
		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		em.persist(person);
		
		em.getTransaction().commit();
		em.close();
	}
	
	public static void main(String[] args){
		PersonDAO dao = new PersonDAO();
		
		Person p = dao.getPersonDetailsForStudentLandingPage(1);
		
		System.out.println(p.getPersonName());
}
}

