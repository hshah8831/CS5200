package edu.neu.cs5200.chaanda.serviceclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;



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

	public String getAccountNumbers(String holderName){
		String output = "";
		
		try {
			String flag1 = "http://localhost:8080/transactionAPI/rest/trans/" + holderName; 
			URI uri = null;
			    try {
			        uri = new URI(flag1.replaceAll(" ", "%20"));
			    } catch (URISyntaxException e) {
			        // TODO Auto-generated catch block
			        e.printStackTrace();
			    }
			   // System.out.println(uri);
			URL url = new URL(uri.toString());
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");

			InputStreamReader isr = new InputStreamReader(conn.getInputStream());
			BufferedReader br= new BufferedReader(isr);


			//List<Integer> accNums = new ArrayList<Integer>();

			output = br.readLine();
			System.out.println(output);

			//List<Integer> accs = new ObjectMapper().readValue(output, ArrayList);

			/*while((output = br.readLine()) != null){

				System.out.println(output);
				System.out.println("kavya");
				//accNums.add(Integer.parseInt(output));
			}*/

			return output;

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return output;
	}
	
	public static void main(String[] args)
	{
//		TransactionClient tr = new TransactionClient();
//		tr.getAccountNumbers("Brad Pitt");
		String holderName = "Brad Pitt";
		String URLstr = "http://localhost:8080/transactionAPI/rest/trans/" + holderName;
		
		//String encodedUrl = URLEncoder.encode(holderName, "UTF-8");
		URLstr = "http://localhost:8080/transactionAPI/rest/trans/" + holderName;
		URLstr.replaceAll("\\+", "%20");
		//System.out.println(URLstr);
		
//		URI uri;
//		try {
//			uri = new URI(
//				    "http", 
//				    "127.0.0.1:8080", 
//				    "transactionAPI/rest/trans/" + holderName,
//				    null);
//			URL url = uri.toURL();
//			System.out.println(url.toString());
//		} catch (URISyntaxException | MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		String flag1 = "http://localhost:8080/transactionAPI/rest/trans/" + holderName; 
		URI uri = null;
		    try {
		        uri = new URI(flag1.replaceAll(" ", "%20"));
		    } catch (URISyntaxException e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
		    }
		    System.out.println(uri);
			
	}
}
