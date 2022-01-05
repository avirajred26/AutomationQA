package apiModules;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.JsonNode;

import utils.ExcelLibraries;

public class UploadAPI {
	
//https://api.staging.credgenics.com/recovery/loan/Test_Loan_11234?company_id=ad8b5a88-637f-49a3-b8af-f341dd9db5fd
	
	
	String loanid ;
	
	@Test
	public void getData() throws Throwable {
		
		  String sDate1="31-Dec-1998 23:37:50";  
		    Date date1=new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse(sDate1);  
		    System.out.println(sDate1+"\t"+date1);
		
		
		
		loanid = ExcelLibraries.getDataUploadColValue("loan_id");
		
		String apiURl = "https://api.staging.credgenics.com/recovery/loan/"+loanid+"?company_id=ad8b5a88-637f-49a3-b8af-f341dd9db5fd";
		
		
		URLConnection connection = new URL(apiURl).openConnection();
		connection.setRequestProperty("authenticationtoken", "f68789dc-0994-4273-8551-6831bfd88011");
		connection.setRequestProperty("role", "admin");
		connection.setRequestProperty("authority", "api.staging.credgenics.com");
		connection.setRequestProperty("content-type", "application/json");
		connection.setRequestProperty("accept", "application/json");
		connection.setRequestProperty("origin", "https://ui.staging.credgenics.com");
		
		
		HttpResponse <JsonNode> response = Unirest.get(apiURl)
			      .header("authenticationtoken", "f68789dc-0994-4273-8551-6831bfd88011")
			      .header("role", "admin")
			      .header("authority", "api.staging.credgenics.com")
			      .header("content-type", "application/json")
			      .header("accept", "application/json")
			      .header("origin", "https://ui.staging.credgenics.com")
			      .asJson();
			    System.out.println(response.getStatus());
			    System.out.println(response.getBody());
			      System.out.println(response.getHeaders().get("Content-Type"));
			      
			      
			    
		
		//Get Response  
		
		try {
			
			JSONObject myObject = new JSONObject(response.getBody());
			JSONArray jsonarr = myObject.toJSONArray(myObject.names());
			
			
			for(int i=0; i<jsonarr.length(); i++){   
				  JSONObject o = jsonarr.getJSONObject(i);  
				  System.out.println(o);  
				}
		      
		      for(int i = 0; i<myObject.names().length(); i++){
		    	
		    	  System.out.println( "key = " + myObject.names().getString(i) + " value = " + myObject.get(myObject.names().getString(i)));
		    	}
			
			InputStream is = connection.getInputStream();
			System.out.println(connection.getContentType());
			//System.out.println(connection.getre);
			System.out.println(is.toString());
		}catch(Exception e) {
			e.printStackTrace();
		}
	
		
		
		
		
	}

}
