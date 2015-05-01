package edu.neu.cs5200.bank.manager;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import edu.neu.cs5200.bank.models.Account;
import edu.neu.cs5200.bank.models.Transaction;


@Path("/trans")
public class TransactionDAO 
{
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("transactionAPI");
	EntityManager em = null;

	//takes payers and receivers account number and payers passkey and transaction amount
	//after passkey validation is cleared makes a transaction of amount from payer to reciever.
	@GET
	@Path("/{payerID}/{passkey}/{recieverID}/{amount}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public String createTransaction(@PathParam("payerID") String payerAccNo,
			@PathParam("passkey") String passKey,
			@PathParam("recieverID") String recieverAccNo,
			@PathParam("amount") String amount) 
	{

		Integer payerAccountNo = Integer.parseInt(payerAccNo);
		Integer recAccountNo = Integer.parseInt(recieverAccNo);
		BigDecimal zero = new BigDecimal(0);

		BigDecimal amt = new BigDecimal(amount);

		Transaction t = new Transaction();
		AccountDAO accdao = new AccountDAO();
		Account payeracc = new Account();
		Account recacc = new Account();

		t.setAmount(amt);
		payeracc = accdao.getAccount(payerAccountNo);

		if(passKey.equals(payeracc.getPassKey())){

			if((payeracc.getBalance().subtract(amt)).compareTo(zero) == -1)
				return "insufficient funds";
			else
				payeracc.setBalance((payeracc.getBalance().subtract(amt)));

			accdao.updateAccount(payerAccountNo, payeracc);
			t.setAccount1(payeracc);

			recacc = accdao.getAccount(recAccountNo);
			recacc.setBalance((recacc.getBalance().add(amt)));
			accdao.updateAccount(recAccountNo, recacc);
			t.setAccount2(accdao.getAccount(recAccountNo));

			em = factory.createEntityManager();
			em.getTransaction().begin();

			em.persist(t);

			em.getTransaction().commit();
			em.close();

			return "Success";

		}

		else{

			return "wrong passkey";
		}
	}

	//returns all the account numbers that a holder will have with the bank
	@SuppressWarnings("unused")
	@GET
	@Path("/{name}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public String getAccountsByName(@PathParam("name") String accountHolderName){

		accountHolderName.replace("+"," ");	
		em = factory.createEntityManager();        

		@SuppressWarnings("unchecked")
		List<Account> accounts = em.createQuery(
				"SELECT a FROM Account a WHERE a.holderName = :name")
				.setParameter("name", accountHolderName).getResultList();

		em.close();

		String output = ""; 
		StringBuffer buffer = new StringBuffer();

		for(Account a : accounts){
			output = output + a.getAccountNo() + "," ;
		}
		return output;
	}

}
