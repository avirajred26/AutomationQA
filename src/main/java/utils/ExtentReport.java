package utils;


import java.net.InetAddress;
import java.net.UnknownHostException;

import org.testng.annotations.AfterTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport {
	 
	static ExtentTest logger, childLogger, parentLogger;
	static ThreadLocal<ExtentTest> extentThread = new ThreadLocal<ExtentTest>();
	static ThreadLocal<ExtentReports> extentReporterThread = new ThreadLocal<ExtentReports>();
	static ExtentHtmlReporter htmlReporter;
	static ExtentReports extent;
	static String extentFolderPath = TestUtil.reportFolderPath+"/Report"+TestUtil.getTimeStamp(), reportName;
	static int iStepNumber = 1;
	static String methodn,oldmethodName;
	
	public static void createReportFile(String reportTitle)   {
		
		htmlReporter = new ExtentHtmlReporter(extentFolderPath + "\\testReport.html");
	    extent = new ExtentReports();
	    System.out.println(extent);
	    extentReporterThread.set(extent);
	    extent.attachReporter(htmlReporter);
	    extent.setSystemInfo("OS", "MAC");
	    htmlReporter.config().setDocumentTitle(reportTitle+" Extent Report");
	    htmlReporter.config().setReportName(reportTitle+" Test Report");
	    htmlReporter.config().setTheme(Theme.STANDARD);
	   
	}

	

	 public synchronized  ExtentReports getInstance() {	
		
		 return extentReporterThread.get();
	 }

	 
	 public synchronized static ExtentTest createReportName(String step_details) throws UnknownHostException{
		logger =extent.createTest(step_details+"</b>"+" System Name - "+ InetAddress.getLocalHost().getHostName());
		System.out.println(logger);
		extentThread.set(logger);
		iStepNumber =1;
		System.out.println(extentThread.get());
		return extentThread.get();	
		
	 }
	 public synchronized static  ExtentTest createParentNode(String methodName) {
		

		 
		 
		 
		 
		 parentLogger = extentThread.get().createNode(methodName);	
		 return parentLogger;
	 }
	  
	  
	  public synchronized  ExtentTest getTest() {
	     return extentThread.get();
	  }
	  
	  public static void Report(String status, String description, String actualStep, String expectedStep) throws Throwable{
			
	  	String ReportStatus = "<b>Step Number "+iStepNumber+"<br>Description :</b> "+description+"<br><b>Expected :</b> "+expectedStep+"<br><b>Actual :</b> "+actualStep;
		ExcelLibraries.fExcelReporter(description, actualStep, expectedStep, status, TestUtil.getCurrentDate());
		
		try{
			
				if(status.equalsIgnoreCase("Pass")){	
					
				parentLogger.createNode(description).log(Status.PASS, ReportStatus).addScreenCaptureFromPath(TestUtil.getScreenhot("PASS"));
				
					flush();
				}
				else  if(status.equalsIgnoreCase("Fail")){
					parentLogger.createNode(description).log(Status.FAIL, ReportStatus).addScreenCaptureFromPath(TestUtil.getScreenhot("FAIL"));
					flush();
					
				}
			
			
				oldmethodName = description;
		
		}catch(Exception e){
			e.printStackTrace();
		}
		iStepNumber = iStepNumber + 1;
	}
	  
	  public static void skipReport() throws Throwable{
			
		  	String ReportStatus = "<b>Step Number "+iStepNumber+"<br>Description :The Test has been Skipped ";
			ExcelLibraries.fExcelReporter("The test has been skipped", "", "", "Skipped", TestUtil.getCurrentDate());
			try{
					
					extentThread.get().log(Status.SKIP, ReportStatus);
					extentThread.get().addScreenCaptureFromPath(TestUtil.getScreenhot("Skip"));
					flush();
			
			}catch(Exception e){
				e.printStackTrace();
			}
			iStepNumber = iStepNumber + 1;
		}
	
	@AfterTest
	public static void flush(){
		extent.flush();
	}

}
