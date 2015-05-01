package edu.neu.cs5200.university.dao;

import javax.persistence.*;

import edu.neu.cs5200.university.model.*;

public class PersonDAO {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("University");
	EntityManager em = null;

	public Person getPersonDetailsForStudentLandingPage(Integer personId){
		em = factory.createEntityManager();
		return em.find(Person.class, personId);
	}

	//person login
	public Person universityLogin(String userName , String password){

		em = factory.createEntityManager();

		Person p = null;

		//String query = "SELECT p FROM Person p WHERE p.userName = " + userName;

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

