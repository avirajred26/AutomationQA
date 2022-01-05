package testcasae;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.TestBase;
import pages.LoginPage;
import pages.NoticePage;
import pages.*;
import utils.ExcelLibraries;
import utils.TestUtil;

public class TC004_CreateAccount extends TestBase {
	
	LoginPage objLogin;
	PortFolioPage objPort;
	AccountCreationPage objAcc;
	
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
		objPort.clickOnCompany("Click", "");
		Thread.sleep(5000);
		try {
			Assert.assertEquals(true, objPort.portfolioActivity());
			reporting("Portfolio Validation", "Table should be loaded", "Table loaded successfully", "Pass");
		}catch(Exception e) {
			reporting("Portfolio Validation", "Table should be loaded", "Table loaded unsuccessfully", "Fail");
		}
		
		objPort.selectMenu("Settings");
		
	
		
		objAcc = new AccountCreationPage(driver);
		objAcc.createAcount("Multi", "", "", "", "", "", "");
	
		
		
		Thread.sleep(10000);
		
	}


}
