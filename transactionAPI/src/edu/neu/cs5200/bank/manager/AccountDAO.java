package edu.neu.cs5200.bank.manager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import edu.neu.cs5200.bank.models.Account;

@Path("/account")
public class AccountDAO 
{
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("transactionAPI");
	EntityManager em = null;

	// fetches account details from the given account number
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Account getAccount(@PathParam("id") int AccNo) 
	{
		em = factory.createEntityManager();
		em.getTransaction().begin();
		Account acc = em.find(Account.class, AccNo);
		em.getTransaction().commit();
		return acc;
	}
	
	//updates an account
	public void updateAccount(Integer AccNo, Account acc) 
	{
		em = factory.createEntityManager();
		em.getTransaction().begin();

		acc.setAccountNo(AccNo);
		em.merge(acc);
		
		em.getTransaction().commit();
		em.close();
	}
	
}
