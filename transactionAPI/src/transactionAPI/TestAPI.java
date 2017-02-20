package transactionAPI;

import java.math.BigDecimal;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/hello")
public class TestAPI {
	
	@GET
	@Path("/world")
	public String message()
	{
		return "hello world" ;
	}
	
	@GET
	@Path("/world/{msg}")
	public String dynamicmessage(@PathParam("msg") String msg)
	{
		return msg ;
	}
	
	public static void main(String[] args)
	{
		BigDecimal hundred = new BigDecimal(100);
		BigDecimal ten = new BigDecimal(10);
		BigDecimal zero = new BigDecimal(0);
		
		if((ten.subtract(hundred)).compareTo(zero) == -1)
			System.out.println("insufficient funds");
		else
			System.out.println(ten.subtract(hundred).doubleValue());
	}
}
