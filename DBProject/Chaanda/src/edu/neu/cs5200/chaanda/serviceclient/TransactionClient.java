package edu.neu.cs5200.chaanda.serviceclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;



public class TransactionClient {

	//calls the bank servce that carries a transaction between payer and the receiver
	public String insertTransaction(String donorAccountNumber , String passKey , String recieverAccountNumber , String amount){

		try {

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
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "Transaction failed";
	}

	//calls the bank account service that fetches all the account numbers of the holder name
	public String getAccountNumbers(String holderName){
		String output = "";

		try {
			String flag1 = "http://localhost:8080/transactionAPI/rest/trans/" + holderName; 
			URI uri = null;
			try {
				uri = new URI(flag1.replaceAll(" ", "%20"));
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}

			URL url = new URL(uri.toString());
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");

			InputStreamReader isr = new InputStreamReader(conn.getInputStream());
			BufferedReader br= new BufferedReader(isr);

			output = br.readLine();
			System.out.println(output);

			return output;

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return output;
	}
}
