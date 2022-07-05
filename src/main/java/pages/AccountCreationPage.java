package pages;
import java.lang.reflect.Array;
import java.util.Collection;
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
	

	

	@FindBy(xpath = "//table[@id = 'user-upload-logs']//tbody//tr")
	List <WebElement> bulkTable;
	
	@FindBy(xpath = "//button[normalize-space()='Bulk user upload']")
	WebElement bulkUserBtn;
	
	@FindBy(css = "input[name='file1']")
	WebElement fileSend;
	
	@FindBy(xpath = "//button[normalize-space()='Process File']")
	WebElement process;
	
	@FindBy(xpath = "//button[normalize-space()='Add User']")
	WebElement addUser;
	
	@FindBy(xpath = "//input[@id='first_name']")
	WebElement firstName;
	
	@FindBy(xpath = "//input[@id='last_name']")
	WebElement lastname;
	
	@FindBy(xpath = "//input[@id='email']")
	WebElement userEmail;
	
	@FindBy(xpath = "//input[@placeholder='Select Role']")
	WebElement role;
	

	
	@FindBy(xpath = "//div[@class='basic-single false select--is-disabled css-14jk2my-container'][1]")
	WebElement reportTo;
	
	@FindBy(css = "div[id='profile'] div[class='select__value-container css-1hwfws3']")
	WebElement permission;
	
	@FindBy(xpath = "div[id='profession'] div[class='select__value-container css-1hwfws3']")
	WebElement profession;
	
	
	@FindBy(xpath = "//button[normalize-space()='Add & Invite User']")
	WebElement createUser;
	
	@FindBy(xpath = "//div[@class='rst__rowWrapper']//div//div//child::div")
	List <WebElement> rolelist;
	
	
	
	
	
	//div[@class='hierarchy-tree-container']//div//div//div//div
	
	WebElement userName,status,roleEl;
	String strSatatus="",roleName="";
	

	
	//div[@class='rst__node']//div[@class='rst__nodeContent']//div//div[@class='rst__rowWrapper']
	
	
	public AccountCreationPage(WebDriver driver) {
		setWebDriver(driver);
	
		
	}
	
	
	
	public void singleCreateAccount() throws Throwable {
	roleName  = "polo1";
		
		jsExecutorClickOn(addUser);
		
		
		
		
		firstName.sendKeys("test");
		lastname.sendKeys("user");
		userEmail.sendKeys("2612Testuser@yopmail.com");
		
		
		jsExecutorClickOn(role);
		
		roleEl = pbDriver.findElement(By.xpath("//div[contains(text(),'"+roleName+"')]"));
		jsExecutorClickOn(roleEl);
		
		TestUtil.mouseHover(reportTo);
		
		reportTo.click();
		reportTo.sendKeys("Cheif1 Cheif1");
		
		
		
		TestUtil.mouseHover(permission);
		permission.click();
		permission.sendKeys("jogi");
		
		TestUtil.mouseHover(profession);
		
		profession.click();
		profession.sendKeys("telecalling");
		
		TestUtil.mouseHover(createUser);
		
		
		jsExecutorClickOn(createUser);
		
		
		
		
		
		
	}
	
	

	
	
	
	
	
	
	public Boolean createAcountBulk( ) {
		
		
		

		jsExecutorClickOn(bulkUserBtn);
		
		fileSend.sendKeys(System.getProperty("user.dir")+ "/src/main/java/"
				+ "config/BulkUser.xlsx");
		
		
		jsExecutorClickOn(process);
		
		int userSize = bulkTable.size();
		
		for(int i = 0;i<=userSize-1;i++) {
			int index = i+1;
			
			userName = pbDriver.findElement(By.xpath("//table[@id = 'user-upload-logs']//tbody//tr["+index+"]//td[1]"));
			status = pbDriver.findElement(By.xpath("//table[@id = 'user-upload-logs']//tbody//tr["+index+"]//td[2]"));
			
			System.out.println(status.getText());
			
			if(ExcelLibraries.getUserNames(userName.getText()) && status.getText()=="Success") {
			
				
			}else {
				
				strSatatus = status.getText();
				return false;
			}
			
		}
		return false;
		
		
	}
	
	
	
	
	
}
