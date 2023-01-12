package utils;


	
	
	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.FileReader;
	import java.io.IOException;
	import java.io.Reader;
	import java.nio.file.Files;
	import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

	import org.apache.commons.csv.CSVFormat;
	import org.apache.commons.csv.CSVParser;
	import org.apache.commons.csv.CSVRecord;
	import org.testng.annotations.Test;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
	import com.opencsv.exceptions.CsvValidationException;



	public class csvLibrary  {
		
		
		
		public static CSVReader objCsv;
		public  static String TestCaseName,Scenario;
		public  static String[] csvCell;
	 	public static int rowNo=0;
		public static CSVParser csvParser;
		public static Reader reader;
		public static String CSVPath = System.getProperty("user.dir")+ "/src/main/java/config/ParentCSV.csv";
		
		public static String CSVPathReader ;
		
		

		public static void readTheCsvPath() {
			
			try {
				reader = Files.newBufferedReader(Paths.get(CSVPath));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			try {
				csvParser = new CSVParser(reader, CSVFormat.DEFAULT
				         .withFirstRecordAsHeader()
				         .withIgnoreHeaderCase()
				         .withTrim());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			try {
		        
	        	for (CSVRecord csvRecord : csvParser) {
	        
	           	 
	           	 if(csvRecord.get("TestCase").equalsIgnoreCase(TestCaseName)) {
	           		  CSVPathReader = System.getProperty("user.dir")+returnCSVType(csvRecord.get("Service_Type"));
	           		  System.out.println(CSVPathReader);
	                  
	           	 }  
	            }
	        }
	        catch(Exception e) {
	        	System.out.println(e);
	        }
				
		}
			
		public static String readFromParentCSVFile(String ColName) throws IOException, CsvException {
			
			
			
			
			try {
				reader = Files.newBufferedReader(Paths.get(CSVPath));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			try {
				csvParser = new CSVParser(reader, CSVFormat.DEFAULT
				         .withFirstRecordAsHeader()
				         .withIgnoreHeaderCase()
				         .withTrim());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
	        try {
	        
	        	for (CSVRecord csvRecord : csvParser) {
	        	
	           	 
	           	 if(csvRecord.get("TestCase").equalsIgnoreCase(TestCaseName)) {
	           		 String name = csvRecord.get(ColName);
	                    return name;
	           	 }  
	            }
	        }
	        catch(Exception e) {
	        	System.out.println(e);
	        }
			
	         
	         return null;
	         	
		}
			
	    
		public static String readColValue(String Scenario, String ColName) throws IOException, CsvException {
		
			
		
		
			try {
				reader = Files.newBufferedReader(Paths.get(CSVPathReader));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			try {
				csvParser = new CSVParser(reader, CSVFormat.DEFAULT
				         .withFirstRecordAsHeader()
				         .withIgnoreHeaderCase()
				         .withTrim());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
	        try {
	        
	        	for (CSVRecord csvRecord : csvParser) {
	        		System.out.println(csvRecord.get(ColName).trim());
	           	 
	           	 if(csvRecord.get("Scenario").equalsIgnoreCase(Scenario)) {
	           		 
	           		 String name = csvRecord.get(ColName);
	                    return name;
	           	 }  
	            }
	        }
	        catch(Exception e) {
	        	System.out.println(e);
	        }
			
	         
	         return null;
	         	
		}
		
		
		
		public static String returnCSVType(String value) {
			
			
			
			switch (value) {
			case "Recovery": 
				
				
				return "/src/main/java/config/RecoveryCSV.csv";
				
				case "Notice": 
							
					return "/src/main/java/config/NoticeCSV.csv";
							
				case "Communication": 
					
					return "/src/main/java/config/CommunicationCSV.csv";
					
				case "Calling": 
					
					return "/src/main/java/config/CallingCSV.csv";
					
				case "Payment": 
					
					return "/src/main/java/config/PaymentCSV.csv";
		
				case "User": 
					
					return "/src/main/java/config/UserCSV.csv";
					
					
					
					
			default:
				break;
			}
			
			return null;
			
			
		}
		
		
		public static List<String>  readAllValues(String value) throws FileNotFoundException, IOException, CsvException {


			
			List<String>  records = new ArrayList<String>();
				try {
					reader = Files.newBufferedReader(Paths.get(CSVPathReader));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				try {
					csvParser = new CSVParser(reader, CSVFormat.DEFAULT
					         .withFirstRecordAsHeader()
					         .withIgnoreHeaderCase()
					         .withTrim());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
		        
			  
				try {
					
			        
		        	for (CSVRecord csvRecord : csvParser) {
		        		
		           	 if(csvRecord.get("Scenario").equalsIgnoreCase(value)) {
		           		 records.add(value);
		           		 
		           		return records;
		           	
		           	 }  
		           	 
		           	 System.out.println(records);
		            }
		        }
		        catch(Exception e) {
		        	System.out.println(e);
		        }
			  
			  
			
			  
			return records;
		}
		

	}
	
	
	
	
	
	
	
	
	


