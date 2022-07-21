package Heirachy;



import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.TestBase;
import pages.AccountCreationPage;
import pages.CreateCompnayPage;
import pages.LoginPage;
import pages.PortFolioPage;
import pages.Settingpage;
import utils.CSVLibrary;

//import utils.CSVLibrary;




public class HC_Create_User_2 extends TestBase {

	LoginPage objLogin;
	PortFolioPage objPort;
	AccountCreationPage objAcc;
	Settingpage objSet;
	
	
	@Test(priority = 1)
	public void loginTest() throws Throwable   {
	
	
		objLogin = new LoginPage(driver);
		
	
		
		objLogin.loginActivity(CSVLibrary.readColValue("UserName"), CSVLibrary.readColValue("Password"));
		
		
		
		objLogin.setOTP();
		
		Assert.assertTrue(objLogin.verifyUsernameDispalyed());
		
		reporting("Login Validation", "User should be able to login", "User Successfully Loggedin", "Pass");
		
	
	}
	
	@Test(priority = 2)
	public void createCompnay() throws Throwable {
		objPort = new PortFolioPage(driver);
		
		objPort.clickOnCompany("Click","");
		
		objPort.selectMenu("Settings");
		
		
		objSet = new Settingpage(driver);
		
		objSet.selectSettingTab("Users");
		
		
		
		objAcc = new AccountCreationPage(driver);
		objAcc.singleCreateAccount();
		
		checkBlnMethod=	objAcc.createAcountBulk();
		try {
			 AssertJUnit.assertEquals(true,checkBlnMethod );
			 reporting("Bulk User Creadtion Validation", "Bulk User Should be created Succssfully ", "Bulk User Created Successfully", "Pass");
		}
		catch (Exception E) {
			reporting("Bulk User Creadtion Validation", "Bulk User Should be created Succssfully ", "Bulk User Created Unsuccessfully", "Fail");
		}
		
       		
		
	}
	
	
	

}
