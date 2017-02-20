package edu.neu.cs5200.chaanda.serviceclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



public class TransactionClient {
	
	/*public static void main(String[] args){
		
		//TransactionClient client = new TransactionClient();
		//String msg  = client.insertTransaction("2", "donor1", "1", "100");
		
		//System.out.println(msg);
	}*/
	
	public String insertTransaction(String donorAccountNumber , String passKey , String recieverAccountNumber , String amount){
	
		try {
			
			//URL url = new URL("http://localhost:8080/transAPI/rest/trans/2/donor1/1/16");
			URL url = new URL("http://localhost:8080/transactionAPI/rest/trans/" + donorAccountNumber + "/" + passKey + "/" + recieverAccountNumber + "/" + amount);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			
			InputStreamReader isr = new InputStreamReader(conn.getInputStream());
			BufferedReader br= new BufferedReader(isr);
			
			String output ;
			StringBuffer buffer = new StringBuffer();
			
			while((output = br.readLine()) != null){
				
				buffer.append(output);
			}
			
			return buffer.toString();
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Transaction failed";
}
}
