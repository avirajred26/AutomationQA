package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.PageBase;

public class loanApplicantPage extends PageBase {
	
	public loanApplicantPage(WebDriver driver) {
		setWebDriver(pbDriver);
		
		
	}
	@FindBy(xpath = "//a[contains(text(),'Loan Details')]")
	public WebElement loanDetails;
	
	@FindBy(xpath = "//a[contains(text(),'Applicant Details')]")
	public WebElement applicantDetails;
	
	@FindBy(xpath = "//a[contains(text(),'Additional Details')]")
	public WebElement additionalVariables;
	
	@FindBy(xpath = "//a[contains(text(),'Recovery Details')]")
	public WebElement recovery;
	
	@FindBy(xpath = "//a[contains(text(),'Documents')]")
	public WebElement documents;
	
	@FindBy(xpath = "//body/div[@id='root']/div[1]/div[2]/main[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[4]/div[1]/div[1]/div[1]/div[1]/button[1]")
	public WebElement editBtn;
	
	
	
	
	@FindBy(xpath = "//table[@class='cd-table table table-striped table-hover']//tbody//tr[4]//td//input[@name='applicant_email']")
	public WebElement applicantEmail;
	
	
	@FindBy(xpath = "//table[@class='cd-table table table-striped table-hover']//tbody//tr[5]//td//input[@name='applicant_contact_number']")
	public WebElement applicantContact;
	
	
	@FindBy(xpath = "//body[1]/div[1]/div[1]/div[2]/main[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[4]/div[1]/div[1]/div[1]/div[1]/button[1]")
	public WebElement saveBtn;
	
	
	//td[contains(text(),'DB_Test_1')]
	
	
	public boolean validationONLoan() {
		System.out.println(PortFolioPage.loanNumber);
		
		WebElement el = pbDriver.findElement(By.xpath("//td[contains(text(),'"+PortFolioPage.loanNumber+"')]"));
		
		
		if(waitForElementToAppearBoolean(el)) {
			return true;
		}
		return false;
		
	}
	
	public boolean editLoan(String email, String contact,String tabName) {
		if(validationONLoan()) {
			switchToTab(tabName);
			
			jsExecutorClickOn(editBtn);
			
			applicantEmail.clear();
			applicantEmail.sendKeys(email);
			
			applicantContact.clear();
			int number = Integer.parseInt(contact);
			sendKeysINTByJS(applicantContact,number);
			
			
			saveBtn.click();
			
			
			if(applicantEmail.toString().contains(email) && applicantContact.toString().contains(contact) ) {
				return true;
				
			}
		}
		return false;
		
	}
	
	
	

	public void switchToTab(String tabName) {
		
		switch(tabName) {
		  case "Applicant Details":
			  jsExecutorClickOn(applicantDetails);
		  
		    break;
		  case "Loan Details":
			  jsExecutorClickOn(loanDetails);
		   
		    break;
		    
		  case "Additional Details":
			   jsExecutorClickOn(additionalVariables);
			    break;
			    
		  case "Recovery Details":
			  jsExecutorClickOn(recovery);
			   
			    break;
			    
		  case "Documents":
			  jsExecutorClickOn(documents);
			   
			    break;
		  default:
		    
		}
		
	}

}
