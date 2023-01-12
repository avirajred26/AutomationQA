package base;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;

import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Connection;
import org.apache.commons.collections4.bag.SynchronizedSortedBag;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.poi.openxml4j.exceptions.InvalidOperationException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.xmlbeans.impl.common.ReaderInputStream;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.devtools.Command;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v96.network.Network;
import org.openqa.selenium.devtools.v96.network.Network.GetResponseBodyResponse;
import org.openqa.selenium.devtools.v96.network.model.Request;
import org.openqa.selenium.devtools.v96.network.model.RequestId;
import org.openqa.selenium.devtools.v96.network.model.Response;
import org.openqa.selenium.devtools.v96.network.model.ResponseReceived;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.IInvokedMethod;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.openqa.selenium.logging.LogEntry;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.opencsv.exceptions.CsvException;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.LoginPage;
import pages.HomePage;

import java.util.Optional;
import utils.ExcelLibraries;
import utils.ExtentReport;
import utils.TestUtil;
import utils.WebEventListener;
import utils.csvLibrary;



public class TestBase {
	static URLConnection connection;
	public  static BufferedReader br;
	public static WebDriver driver;
	protected static PageBase basePage;
	public static Properties prop;
	public boolean checkBlnMethod;
	public boolean testStatus;
	static Logger LOGGER =    Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	boolean append = true;
    static FileHandler handler;
    public static String logFolderLocation = System.getProperty("user.dir") + "/target/TestReports/";
    public static boolean checkFun;
    public static ChromeOptions option;
    public static  String  responseBody,type="";
    public  static  Map<Integer, String> numberMapping;
    
    
    public LoginPage objLogin;
    
    public HomePage objHome;
    
    public  String [][] arraystr;
	public List<String> data;
	  static List<List<String>> records;

    
  public static DevTools tool;
	
	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/"
					+ "config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@BeforeSuite
	public void extentFileCreation() throws Throwable {
		ExtentReport.createReportFile(prop.getProperty("reportTitle"));
	//	TestUtil.renameFile();
	}
	
	@Parameters("browser")
	@BeforeTest
    public void launchApplication(String brow) throws Throwable {
		
	csvLibrary.TestCaseName = getClass().getSimpleName();
	     
	System.out.println(getClass().getSimpleName());
		
	//	ExcelLibraries.createExcel(getClass().getSimpleName());
		
		ExtentReport.createReportName(getClass().getSimpleName());
		
	
		
		testStatus = Boolean.valueOf(csvLibrary.readFromParentCSVFile("Status"));
		
		
		if(!testStatus) {
			ExtentReport.skipReport();
			throw new SkipException(getClass().getSimpleName()+" has been skipped");
		}
		
		LoggingPreferences preferences = new LoggingPreferences();
		preferences.enable(LogType.CLIENT, Level.ALL);

		 
		
		
		
		handler = new FileHandler(logFolderLocation+TestUtil.createLogFile(), append);
		
		setDriverProperty(brow);
		
		driver.get(prop.getProperty("AppUrl"));

		
		
		
		e_driver = new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		csvLibrary.readTheCsvPath();
	}
	
	@BeforeMethod
	public static void getFunctionName(Method  arg0) {
		System.out.println(arg0.toString());
		ExtentReport.createParentNode(arg0.getName());
		
	}
	
	
	public static void log(String data) {
		
		LOGGER.info(data);
		Reporter.log(data);
		LOGGER.addHandler(handler);
	}
	
   @AfterTest
    public static void tearDown() throws Throwable {
    
    	//ExcelLibraries.setEndTime();
    	//ExcelLibraries.resetCount();
    	
        driver.quit();
    }
    
    private  void setDriverProperty(String Brow) throws Throwable{
    
    	
    	if(Brow.contains("Chrome") || Brow.equalsIgnoreCase("chrome")) {
    		WebDriverManager.chromedriver().setup();
    		
    		 option = new ChromeOptions();   
    		
    		 option.addArguments("use-fake-device-for-media-stream");
    		 option.addArguments("use-fake-ui-for-media-stream"); 
    	    
    	    Map<String, Object> prefs = new HashMap<String, Object>();
    	    prefs.put("profile.default_content_setting_values.media_stream_mic", 1);
    	    prefs.put("profile.default_content_setting_values.media_stream_camera", 1);
    	    prefs.put("profile.default_content_setting_values.geolocation", 1);
    	    prefs.put("profile.default_content_setting_values.notifications", 1);
    	    prefs.put("profile.default_content_setting_values.automatic_downloads'", 1);
    	    prefs.put( "profile.content_settings.pattern_pairs.*.multiple-automatic-downloads", 1 );
    	    
    	    option.setExperimentalOption("prefs", prefs);
    		

    		driver = new ChromeDriver(option);
    	} 
    	
    	else if (Brow.contains("Firefox") || Brow.equalsIgnoreCase("Firefox")) 
    		{
    		WebDriverManager.firefoxdriver().setup();
    		
    		driver = new FirefoxDriver(); 
    	}
    	
    	driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	
    	
    }
    
    
public void reporting(String desc,String exp,String actual,String status) throws Throwable {
		
		if(status.equalsIgnoreCase("PASS")) {
			ExtentReport.Report("Pass", desc, actual, exp);
		}else {
			ExtentReport.Report("Fail", desc, actual, exp);
		}
	}
	
    
    
  
    public void getResponse() {
    	
    	tool = 	((HasDevTools) driver).getDevTools();
    	tool.createSession();
    	tool.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
    
   
    	try {
    
   	 tool.addListener(Network.responseReceived(), requestSent -> {
   		
   
   	
   	     
   		 
   		 if(requestSent.getResponse().getUrl().contains(prop.getProperty("APiURL")) && requestSent.getType().toJson().contains("Fetch")  ) {
   			 //&& (type.contains("Portfolio") || type.equalsIgnoreCase("Portfolio"))
   		
   			if(requestSent.getResponse().getStatus() !=200 || requestSent.getResponse().getStatus() !=201 )   {
   				
   			
   			 Response re = requestSent.getResponse();
   			
   			RequestId[] requestIds = new RequestId[1];
   			
   			 
              System.out.println("Request URL => " + re.getUrl());
              
       

              System.out.println("Request Status => " + re.getStatus());

              System.out.println("Request Headers => " + re.getHeaders().toString());
             
              System.out.println("Type: " + requestSent.getType().toJson());
              requestSent.getResponse().getHeaders().toJson().forEach((k, v) -> System.out.println((k + ":" + v)));
              
              
              
              requestIds[0] = requestSent.getRequestId();
              System.out.println(requestIds[0]);
              System.out.println(requestSent.getRequestId());
              System.out.println("-----------------------------------------------------------------------------------------");

              System.out.println("Response Body: \n" + tool.send(Network.getResponseBody(requestSent.getRequestId())).getBody() + "\n");
              System.out.println("-----------------------------------------------------------------------------------------");

                responseBody = tool.send(Network.getResponseBody(requestIds[0])).getBody();
             
   			}  
   		 } 
   		 
   	 });
   	 
   	InputStream targetStream = new ByteArrayInputStream(responseBody.getBytes());
	br = new BufferedReader(new InputStreamReader(targetStream));
	
		
		String strCurrentLine;
		int i=0;
		System.out.println("Printing in Buffer");
		try {
		strCurrentLine = br.readLine();
		numberMapping.put(i,strCurrentLine);
            i++;
		
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
    	 
    	
    		
    		
    	}catch(Exception e) {
    		
    		
    		
    	}
    	
    }
    
    
    @DataProvider(name = "test")
    public Iterator<Object []> provider( ) throws InterruptedException, IOException, CsvException
    {
        List<Object []> testCases = new ArrayList<>();
       
        csvLibrary.TestCaseName =  csvLibrary.TestCaseName.replace("newTestcase.", "");
		  System.out.println(csvLibrary.TestCaseName);
		  csvLibrary.readTheCsvPath();
		
		  String scenario =csvLibrary.readFromParentCSVFile("Scenario");
		  String arrylist [] = scenario.split(",");
	
		  Object[] data1 = null;
		  

	
		  
	for(String x :arrylist ) {
	
		data1 =  csvLibrary.readAllValues(x).toArray();

		testCases.add(data1);
	}
		  
		
		  

		

        return testCases.iterator();
    }



    
    @BeforeClass
    public  void RequireLogin() throws Throwable {
    	
    	
    	
    	objLogin = new LoginPage(driver);
		
		objLogin.loginActivity(csvLibrary.readFromParentCSVFile("UserName"), csvLibrary.readFromParentCSVFile("Password"));
		
		Assert.assertNotEquals(objLogin.verifyforWrongPassword(), true);
		
		//reporting("Login-OTP Validation", "User should be able to get OTP", "User Successfully navigate to OTP Page", "Pass");
		
		objHome = new HomePage(driver);
		
	//	Assert.assertTrue(objLogin.verifyUsernameDispalyed());
		
		//reporting("Login Validation", "User should be able to login", "User Successfully Loggedin", "Pass");
		
		
    	
    	
    	
    }
  
    
    
    /*public PackagePart put(final PackagePartName partName, final PackagePart part) {
    	  final String ppName = partName.getName();
    	  final StringBuilder concatSeg = new StringBuilder();
    	  // split at slash, but keep leading slash
    	  final String delim = "(?=["+PackagingURIHelper.FORWARD_SLASH_STRING+".])";
    	  for (String seg : ppName.split(delim)) {
    	    concatSeg.append(seg);
    	    if (registerPartNameStr.contains(concatSeg.toString())) {
    	      throw new InvalidOperationException(
    	        "You can't add a part with a part name derived from another part ! [M1.11]");
    	    }
    	  }
    	  registerPartNameStr.add(ppName);
    	  return packagePartLookup.put(ppName, part);
    	}*/
    
}  
