package pages;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.PageBase;
import utils.ExcelLibraries;

public class CreateCompnayPage extends PageBase {

	
	@FindBy(xpath = "//input[@name='new_companyName']")
	WebElement company_Name;
	
	
	
	@FindBy(xpath = "//input[@name='new_companyEmail']")
	WebElement company_Email;
	
	
	@FindBy(xpath = "//input[@name='new_trademark']")
	WebElement company_Trademark;
	
	
	
	@FindBy(xpath = "//input[@name='email_from']")
	WebElement company_EmailFrom;
	
	
	@FindBy(xpath = "//input[@name='email_replyTo']")
	WebElement company_EmailTo;
	
	
	@FindBy(xpath = "//table[@class='text-center table table-borderless']//tbody")
	List <WebElement> permission_table;
	
	@FindBy(xpath = "//input[@id='first_name']")
	WebElement admin_name;
	
	//input[@id='first_name']
	
	@FindBy(xpath = "//input[@id='email']")
	WebElement admin_email;
	
	@FindBy(xpath = "//button[normalize-space()='Create'])[1]")
	WebElement admin_create;
	
	
	@FindBy(xpath = "//input[@id='last_name']")
	WebElement admin_last;
	
	//input[@id='last_name']
	
	
	@FindBy(xpath = "//input[@name='new_authorisedRepresentativeName']")
	WebElement authorised_Name;
	
	
	@FindBy(xpath = "//input[@name='new_authorisedRepresentativeEmail']")
	WebElement authorised_Email;
	
	
	@FindBy(xpath = "(//button[normalize-space()='Next'])[1]")
	WebElement nextBtn;

	
	public CreateCompnayPage(WebDriver driver) {
		setWebDriver(pbDriver);
	}

	
	
	
	public  void applyCompanyDetails() {
		
		jsExecutorClickOn(pbDriver.findElement(By.xpath("//span[normalize-space()='Create Company']")));
		
		waitForElementToAppear(company_Name);
		
		company_Name.sendKeys("Test Hierachy Test Automation");
		company_Email.sendKeys("automationtest@yopmail.com");
		company_Trademark.sendKeys("Hierachy Test Automation");
		company_EmailFrom.sendKeys("automationtest@yopmail.com");
		company_EmailTo.sendKeys("automationtest@yopmail.com");
		
		authorised_Name.sendKeys("Test Hierachy Test Automation");
		authorised_Email.sendKeys("automationtest@yopmail.com");
		
		jsExecutorClickOn(nextBtn);
	}
	
	
	public void applyPermission() throws Throwable {
		
		
		int table_size = permission_table.size(),tablecount,cloumnCount;
		
		for(int i =0; i <=table_size;i++) {
			
			tablecount = i+1;
			System.out.println(tablecount);
			List <WebElement> permission_Names = pbDriver.findElements(By.xpath("//table[@class='text-center table table-borderless']//tbody["+i+1+"]//tr"));
			
			cloumnCount = 0;
			for(int j =1;j<=permission_Names.size();j++) {
				cloumnCount = j+1;
				String permission_Name =permission_Names.get(j).getText() ;
				
				
				String readCheckBox, writeCheckbox ;
				
				System.out.println(ExcelLibraries.permisson_set.get(permission_Name).get(0));
				readCheckBox = ExcelLibraries.permisson_set.get(permission_Name).get(0);
				writeCheckbox = ExcelLibraries.permisson_set.get(permission_Name).get(1);
				
				if(checkForElementToClickable(permission_Names.get(j))) {
					
					
					WebElement readTrue = pbDriver.findElement(By.xpath("//table[@class='text-center table table-borderless']//tbody["+tablecount+"]//tr["+cloumnCount+"]//td[2]//div//input[@class='custom-control-input']"));
					
					WebElement writeTrue = pbDriver.findElement(By.xpath("//table[@class='text-center table table-borderless']//tbody["+tablecount+"]//tr["+cloumnCount+"]//td[3]//div//input[@class='custom-control-input']"));
					
					if(readCheckBox.contains("Yes")) {
						jsExecutorClickOn(readTrue);
						
						
					} if(writeCheckbox.contains("Yes")) {
						jsExecutorClickOn(writeTrue);
						
						
					}
					
				}
				
				
			}
			
			
		
			
		}
		jsExecutorClickOn(nextBtn);
		
	}
	
	public void addAdmin() {
		admin_name.sendKeys("Admin ");
		admin_last.sendKeys("Test Hierachy");
		admin_email.sendKeys("AdminTest123@yopmail.com");
		jsExecutorClickOn(admin_create);
		
		
		
	}
	
	
}
