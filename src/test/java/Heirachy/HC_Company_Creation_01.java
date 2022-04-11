package Heirachy;


import org.testng.Assert;
import org.testng.annotations.Test;

import base.TestBase;
import pages.AccountCreationPage;
import pages.CreateCompnayPage;
import pages.LoginPage;
import pages.PortFolioPage;
import utils.ExcelLibraries;




public class HC_Company_Creation_01 extends TestBase {

	LoginPage objLogin;
	PortFolioPage objPort;
	AccountCreationPage objAcc;
	CreateCompnayPage objCreate;
	
	
	@Test(priority = 1)
	public void loginTest() throws Throwable   {
	
	
		objLogin = new LoginPage(driver);
		
		objLogin.loginActivity(ExcelLibraries.getTestColValue("UserName"), ExcelLibraries.getTestColValue("Password"));
		
		
		
		objLogin.setOTP();
		
		Assert.assertTrue(objLogin.verifyUsernameDispalyed());
		
		reporting("Login Validation", "User should be able to login", "User Successfully Loggedin", "Pass");
		
	
	}
	
	@Test(priority = 2)
	public void createCompnay() throws Throwable {
		objPort=new PortFolioPage(driver);
		//objPort.selectMenu("Create Company");
		objCreate = new CreateCompnayPage(driver);
		objAcc = new AccountCreationPage(driver);
		
		//objAcc.singleCreateAccount();
		
		objCreate.applyCompanyDetails();
		objCreate.applyPermission();
		objCreate.addAdmin();
		
		
	}

}
