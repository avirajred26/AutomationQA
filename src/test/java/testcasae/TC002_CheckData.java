package testcasae;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.TestBase;
import pages.DataUploadpage;
import pages.LoginPage;
import pages.PortFolioPage;
import pages.loanApplicantPage;
import utils.ExcelLibraries;

public class TC002_CheckData  extends TestBase{
	

	LoginPage objLogin;
	PortFolioPage objPort;
	DataUploadpage objappli;
	
	@Test(priority = 1)
	public void loginTest() throws Throwable   {
	
	
		objLogin = new LoginPage(driver);
		
		objLogin.loginActivity(ExcelLibraries.getTestColValue("UserName"), ExcelLibraries.getTestColValue("Password"));
		
		Assert.assertNotEquals(objLogin.verifyforWrongPassword(), true);
		
		reporting("Login-OTP Validation", "User should be able to get OTP", "User Successfully navigate to OTP Page", "Pass");
		
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
			AssertJUnit.assertEquals(true, objPort.portfolioActivity());
			reporting("Portfolio Validation", "Table should be loaded", "Table loaded successfully", "Pass");
		}catch(Exception e) {
			reporting("Portfolio Validation", "Table should be loaded", "Table loaded unsuccessfully", "Fail");
		}
		
		try {
			AssertJUnit.assertEquals(true, objPort.searchLoan(ExcelLibraries.getTestColValue("loan")));
			reporting("Loan Search Validation", "Loan  should be show", "Loan shows successfully", "Pass");
		}catch(Exception e) {
			reporting("Loan Search Validation", "Loan should be show", "loan shows unsuccessfully", "Fail");
		}
		Thread.sleep(10000);
		
	
		objappli = new DataUploadpage(driver);
		
		objappli.validateData("Loan Details");
		objappli.validateData("Applicant Details");
		
		
	}


}
