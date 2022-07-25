package testcasae;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.TestBase;
import pages.DashBoardPage;
import pages.LoginPage;
import pages.PortFolioPage;

import utils.CSVLibrary;

public class TC006_Dashboard extends TestBase {

	public TC006_Dashboard() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	LoginPage objLogin;
	PortFolioPage objPort;
	DashBoardPage objappli;
	
	@Test(priority = 1)
	public void loginTest() throws Throwable   {
	
	
		objLogin = new LoginPage(driver);
		
		objLogin.loginActivity(CSVLibrary.readColValue("UserName"), CSVLibrary.readColValue("Password"));
		
		
		
		objLogin.setOTP();
		
		Assert.assertTrue(objLogin.verifyUsernameDispalyed());
		
		reporting("Login Validation", "User should be able to login", "User Successfully Loggedin", "Pass");
		
	
	}
	@Test(priority = 2)
	public void porFolioPageTest() throws Throwable {
		objPort=new PortFolioPage(driver);
		objPort.clickOnCompany("Click", "");
		Thread.sleep(5000);
		try {
			Assert.assertEquals(true, objPort.portfolioActivity());
			reporting("Portfolio Validation", "Table should be loaded", "Table loaded successfully", "Pass");
		}catch(Exception e) {
			reporting("Portfolio Validation", "Table should be loaded", "Table loaded unsuccessfully", "Fail");
		}
		
		try {
			Assert.assertEquals(true, objPort.searchLoan(CSVLibrary.readColValue("loan")));
			reporting("Loan Search Validation", "Loan  should be show", "Loan shows successfully", "Pass");
		}catch(Exception e) {
			reporting("Loan Search Validation", "Loan should be show", "loan shows unsuccessfully", "Fail");
		}
		Thread.sleep(10000);
		
		objPort.selectMonth("Dec","2021");
		
		objPort.selectMenu("Dashboard");
		
		objappli = new DashBoardPage(driver);
		Thread.sleep(5000);
		
		

		
		
	}
	
	
	

}
