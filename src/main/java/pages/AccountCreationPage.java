package pages;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import base.PageBase;
import utils.ExcelLibraries;
import utils.TestUtil;

public class AccountCreationPage extends PageBase {
	String usernames[];
	
	//table[1]/tbody[1]/tr[1]/td[1]/input[@name='new_email']
	@FindBy(xpath = "//input[@id='email']")
	WebElement email;
	
	@FindBy(xpath = "//input[@id='first-name']")
	WebElement name;
	
	@FindBy(xpath = "//input[@id='last-name']")
	WebElement lastname;
	
	@FindBy(xpath = "(//div[@class='select__menu-list css-11unzgr'])[1]")
	List <WebElement> parent_Company;
	
	@FindBy(xpath = "//table[1]/tbody[1]/tr[7]/td[1]/select")
	WebElement profess;
	
	@FindBy(xpath = "(//div[@class='select__menu-list css-11unzgr'])[1]")
	List <WebElement> role;
	
	
	
	@FindBy(xpath = "//button[normalize-space()='Create & Invite User']")
	WebElement createBtn;
	
	
	@FindBy(xpath = "//div[@class='select__menu-list select__menu-list--is-multi css-11unzgr'])[1]")
	List <WebElement> select_Companies;
	
	
	@FindBy(xpath = "//input[@id='file']")
	WebElement chooseFile;
	
	@FindBy(xpath = "//body/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/input[1]")
	WebElement uploadFile;
	
	@FindBy(xpath = "//span[contains(text(),'×')]")
	WebElement close;
	
	@FindBy(xpath = "//a[contains(text(),'Create User /Agent')]")
	WebElement clickOnTab;
	
	
	@FindBy(xpath = "//table[@class='table table-striped table-hover']/tbody/tr")
	List <WebElement> DetailTable;
	


	
	
	
	//a[contains(text(),'Create User /Agent')]
	//table[@class='table table-striped table-hover']/tbody/tr
	
	
	
	
	public AccountCreationPage(WebDriver driver) {
		setWebDriver(driver);
	
		
	}
	
	
	
	public void singleCreateAccount() {
		
		Actions actions = new Actions(pbDriver); 
		
	jsExecutorClickOn(pbDriver.findElement(By.xpath("//span[normalize-space()='User Management']")));
		email.sendKeys("testuserqa12@yopmail.com");
		name.sendKeys("Tester");
		lastname.sendKeys("QA");
		
		
		
	
		actions.moveToElement(pbDriver.findElement(By.xpath("(//div[@class='select__value-container select__value-container--has-value css-1hwfws3'])[1]"))); 
	    actions.clickAndHold().perform();
	    
	    TestUtil.selectBYList(role, "User");
		
	    actions.moveToElement(pbDriver.findElement(By.xpath("(//div[@class='select__value-container css-1hwfws3'])[1]"))); 
	    actions.clickAndHold().perform();
		

	    TestUtil.selectBYList(parent_Company, "Credgenics");
		//TestUtil.selectItem(parent_Company, 16);
	    
	    actions.moveToElement(pbDriver.findElement(By.xpath("(//div[@class='select__value-container select__value-container--is-multi css-1hwfws3'])[1]"))); 
	    actions.clickAndHold().perform();
	    TestUtil.selectBYList(select_Companies, "Credgenics");
		
		//TestUtil.selectItem(select_Companies, 10);
		
		createBtn.click();
		
		
	}
	
	

	
	
	
	
	
	
	public void createAcount(String Method ,String Type, String Email, String Password, String Name,String Campign, String Profession ) {
		
		
	}
}
