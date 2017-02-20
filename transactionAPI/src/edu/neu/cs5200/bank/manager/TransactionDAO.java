package edu.neu.cs5200.bank.manager;

import java.math.BigDecimal;

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
	
	//removes a row, with siteId, and fetches the updated database
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
		
}
