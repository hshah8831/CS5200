package edu.neu.cs5200.chaanda.dao;

import javax.persistence.*;

import edu.neu.cs5200.chaanda.model.Person;

public class PersonDAO {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("Chaanda");
	EntityManager em = null;

	//gets a person from the given personID
	public Person getPersonDetailsForStudentLandingPage(Integer personId){
		em = factory.createEntityManager();
		return em.find(Person.class, personId);
	}

	//inserts a person into the database
	public Person insertPerson(Person person)
	{
		em = factory.createEntityManager();
		em.getTransaction().begin();

		em.persist(person); 

		em.getTransaction().commit();
		em.close();
		Person p = getPerson(person.getUserName());

		return p;
	}

	//gets a person with given person name
	public Person getPerson(String userName){

		em = factory.createEntityManager();
		Person p =  (Person) em.createQuery(
				"SELECT p FROM Person p WHERE p.userName = :name")
				.setParameter("name", userName)
				.getSingleResult();

		em.close();

		return p;
	}

}

