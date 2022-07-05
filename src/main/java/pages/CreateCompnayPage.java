package pages;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import base.PageBase;
import utils.ExcelLibraries;
import utils.TestUtil;

public class CreateCompnayPage extends PageBase {

	
	@FindBy(xpath = "//input[@name='new_companyName']")
	WebElement company_Name;
	
	
	@FindBy(xpath = "(//div)[202]")
	WebElement error;
	
	
	@FindBy(xpath = "//input[@name='new_companyEmail']")
	WebElement company_Email;
	
	
	@FindBy(xpath = "//input[@name='new_trademark']")
	WebElement company_Trademark;
	
	
	
	
	@FindBy(xpath = "//table[@class='text-center table table-borderless']//tbody")
	List <WebElement> permission_table;
	
	@FindBy(xpath = "//input[@id='first_name']")
	WebElement admin_name;
	
	//input[@id='first_name']
	
	@FindBy(xpath = "//input[@id='email']")
	WebElement admin_email;
	
	@FindBy(xpath = "//div[@class='gms card-body']//div//div//button[@class= 'btn btn-primary btn-sm float-right mr-2 gms-primary-btn cursor']")
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

	
	
	
	public  void applyCompanyDetails(String c ,String c1,String c2,String c3, String c4) {
		
		jsExecutorClickOn(pbDriver.findElement(By.xpath("//span[normalize-space()='Create Company']")));
		
		waitForElementToAppear(company_Name);
		String cName = c+TestUtil.randomNumnber();
		
		company_Name.sendKeys(cName);
		System.out.println(cName);
		company_Email.sendKeys("companyEmail"+TestUtil.randomNumnber()+"@yopmail.com");
		company_Trademark.sendKeys(c2+TestUtil.randomNumnber());
		
		
		authorised_Name.sendKeys(c3);
		authorised_Email.sendKeys("authorPerson"+TestUtil.randomNumnber()+"@yopmail.com");
		
		jsExecutorClickOn(nextBtn);
	}
	
	
	public void applyPermission() throws Throwable {
		
		Thread.sleep(5000);
		
		
		int table_size = permission_table.size(),tablecount,cloumnCount;
		
		for(int i =0; i <=table_size-1;i++) {
			
			tablecount = i+1;
			System.out.println(tablecount);
			int c2 = i+1;
			
			 WebElement permission_Names1 = pbDriver.findElement(By.xpath("//table[@class='text-center table table-borderless']//tbody["+c2+"]"));
			
			
		System.out.println(permission_Names1.getAttribute("class").toString());	
			if(permission_Names1.getAttribute("class").toString().equals("service-permission-container opacity-50")) {
				
				continue;
			}
				
			List <WebElement> permission_Names = pbDriver.findElements(By.xpath("//table[@class='text-center table table-borderless']//tbody["+c2+"]//tr"));
			
			int c1 = 0;
			
			cloumnCount = 0;
			
			System.out.println(permission_Names.size());
			for(int j =0;j<permission_Names.size()-1;j++) {
				System.out.println(i);
					
				
				cloumnCount = j+2;
				c1 = j+1;
				String permission_Name =permission_Names.get(c1).getText() ;
				System.out.println(permission_Name);
				
				String readCheckBox, writeCheckbox ;
				
				System.out.println(ExcelLibraries.permisson_set.get(permission_Name).get(0));
				readCheckBox = ExcelLibraries.permisson_set.get(permission_Name).get(0);
				writeCheckbox = ExcelLibraries.permisson_set.get(permission_Name).get(1);
				
				if(checkForElementToClickable(permission_Names.get(c1))) {
					try {
						
						if(readCheckBox.contains("Yes")) {
							WebElement readTrue = pbDriver.findElement(By.xpath("//table[@class='text-center table table-borderless']//tbody["+tablecount+"]//tr["+cloumnCount+"]//td[2]//div//input"));
							jsExecutorClickOn(readTrue);
						}
							
	
					}catch(Exception e) {
						
						
					}
					
					try {
						
						 if(writeCheckbox.contains("Yes")) {
							 WebElement writeTrue = pbDriver.findElement(By.xpath("//table[@class='text-center table table-borderless']//tbody["+tablecount+"]//tr["+cloumnCount+"]//td[3]//div//input[@class='custom-control-input']"));
							jsExecutorClickOn(writeTrue);
							
							
						}
						
					}catch(Exception e) {
						
					}
		
				}
			
				
			}
			
			
		
		
			
		}
		jsExecutorClickOn(nextBtn);
		
	}
	
	public String addAdmin(String a, String a1, String a2) {
		admin_name.sendKeys(a);
		admin_last.sendKeys(a1);
		String name  = "adminTest"+TestUtil.randomNumnber()+"@yopmail.com";
		System.out.println(name);
		admin_email.sendKeys(name);
		jsExecutorClickOn(admin_create);
		return name;
		
		
		
	}
	
	
}
