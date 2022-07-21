package testcasae;



import org.testng.Assert;
import org.testng.annotations.Test;
import base.TestBase;
import pages.LoginPage;
import pages.PortFolioPage;
import pages.loanApplicantPage;
import utils.ExcelLibraries;


public class TC001_Login_Cred extends TestBase{
	
	loanApplicantPage objappli;
	
	@Test(priority = 1)
	public void loginTest() throws Throwable   {
	
	
		
		
	
	}
	@Test(priority = 2)
	public void porFolioPageTest() throws Throwable {
		

		
		objappli = new loanApplicantPage(driver);
		Assert.assertEquals(true, objappli.validationONLoan());
		
		
		try{
			checkFun = objappli.editLoan(ExcelLibraries.getTestColValue("applicant_email"), ExcelLibraries.getTestColValue("applicant_contact"), ExcelLibraries.getTestColValue("tabName"));
			
			if(checkFun) {
				reporting("Loan edit Validation", "Loan Should be edit Successfully", "Loan Edit Successfully", "Pass");
			}else {
				reporting("Loan edit Validation", "Loan Should be edit Successfully", "Loan Edit Un-Successfully", "Fail");
			}
			
			
		}catch(Exception e) {
			reporting("Loan edit Validation", "Loan Should be edit Successfully", "ERROR While Editing", "Fail");
		}
		
		
	}
	
	
	
	
	
	
	
	public void etoe() {
		//login
		//potyfolio
		//notive
	}
	
	
	

}
