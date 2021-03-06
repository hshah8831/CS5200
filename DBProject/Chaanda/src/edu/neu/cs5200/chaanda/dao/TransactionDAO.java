package edu.neu.cs5200.chaanda.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import edu.neu.cs5200.chaanda.model.Transaction;


public class TransactionDAO 
{
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("Chaanda");
	EntityManager em = factory.createEntityManager();

	// Inserts a mapping between a donor and a student(transaction)
	public void insertTransaction(Transaction txn)
	{
		em = factory.createEntityManager();
		em.getTransaction().begin();

		em.persist(txn);

		em.getTransaction().commit();
		em.close();
	}
}
