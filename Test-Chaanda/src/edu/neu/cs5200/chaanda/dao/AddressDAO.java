package edu.neu.cs5200.chaanda.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import edu.neu.cs5200.chaanda.models.*;


public class AddressDAO 
{
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("Test-Chaanda");
	EntityManager em = null;
	
	public void insertAddress(Address address)
	{
		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		em.persist(address);
		
		em.getTransaction().commit();
		em.close();
	}
}
