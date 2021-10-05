package testcasae;

import javax.security.auth.login.FailedLoginException;

import org.testng.Assert;
import org.testng.annotations.Test;
import base.TestBase;
import pages.LoginPage;
import utils.ExcelLibraries;


public class TC001_Login_Cred extends TestBase{
	
	LoginPage objLogin;
	
	@Test
	public void loginTest() throws Throwable   {
		try {
		
		objLogin = new LoginPage(driver);
		
		objLogin.loginActivity(ExcelLibraries.getTestColValue("UserName"), ExcelLibraries.getTestColValue("Password"));
		
		
		objLogin.setOTP();
		
		Assert.assertTrue(objLogin.verifyUsernameDispalyed());
		
		reporting("Login Validation", "Login Successfull", "Login Successfull", "Pass");
		}
		catch(Exception t) {
			 throw new FailedLoginException("FAILED TO LOGIN");
			
		}
	
	}

}
