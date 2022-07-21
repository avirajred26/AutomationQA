package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.testng.annotations.Test;


import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

public class CSVLibrary {
	
	
	public static CSVReader objCsv;
	public  static String TestCaseName;
	public  static String[] csvCell;
	public static int rowNo=0;
	public static CSVParser csvParser;
	public static Reader reader;
	public static String CSVPath = System.getProperty("user.dir")+ "/src/main/java/config/dataFile1.csv";
	
	
	
	static {
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
		
	}
	
	
    
	public static String readColValue(String ColName) throws IOException, CsvException {
		
         for (CSVRecord csvRecord : csvParser) {
        	 
        	 if(csvRecord.get("TestCase").toString().equalsIgnoreCase(TestCaseName)) {
        		 String name = csvRecord.get(ColName);
                 return name;
        	 }  
         }
         
         return null;
         	
	}

}
