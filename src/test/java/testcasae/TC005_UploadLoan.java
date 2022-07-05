package testcasae;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.TestBase;
import pages.DataUploadpage;
import pages.LoginPage;
import pages.PortFolioPage;
import pages.loanApplicantPage;
import utils.ExcelLibraries;
import utils.TestUtil;

public class TC005_UploadLoan extends TestBase {
	
	
	LoginPage objLogin;
	PortFolioPage objPort;
	DataUploadpage objappli;
	
	@Test(priority = 1)
	public void loginTest() throws Throwable   {
	
	
		
		objLogin = new LoginPage(driver);
		
		objLogin.loginActivity(ExcelLibraries.getTestColValue("UserName"), ExcelLibraries.getTestColValue("Password"));
	
		
		//Assert.assertNotEquals(objLogin.verifyforWrongPassword(), true);
		
		reporting("Login-OTP Validation", "User should be able to get OTP", "User Successfully navigate to OTP Page", "Pass");
		
		objLogin.setOTP();
		
		
		Assert.assertTrue(objLogin.verifyUsernameDispalyed());
		
		
		reporting("Login Validation", "User should be able to login", "User Successfully Loggedin", "Pass");
		
	
	}
	@Test(priority = 2)
	public void porFolioPageTest() throws Throwable {
		objPort=new PortFolioPage(driver);
		
		//type = "Portfolio";
		objPort.clickOnCompany("Click", "");
		
		type = "Portfolio";
		Thread.sleep(2000);

	
		/*Thread.sleep(5000);
		try {
			Assert.assertEquals(true, objPort.portfolioActivity());
			reporting("Portfolio Validation", "Table should be loaded", "Table loaded successfully", "Pass");
		}catch(Exception e) {
			reporting("Portfolio Validation", "Table should be loaded", "Table loaded unsuccessfully", "Fail");
		}
		
		
		
		objPort.selectMenu("Data Upload");
		objappli = new DataUploadpage(driver);
		objappli.uploadFile();
		getNetworklog();
		
		objPort.selectMenu("Portfolio");
		
		Thread.sleep(4000);
		System.out.println(ExcelLibraries.getDataUploadColValue("loan_id"));
		objPort.searchLoan(ExcelLibraries.getDataUploadColValue("loan_id"));
		
		Thread.sleep(4000);
	
		
		objappli.validateData("Loan Details");
		*/
		
		
	
		
	}

}
