package pages;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.PageBase;
import utils.ExcelLibraries;
import utils.TestUtil;

public class AccountCreationPage extends PageBase {
	String usernames[];
	
	//table[1]/tbody[1]/tr[1]/td[1]/input[@name='new_email']
	@FindBy(xpath = "//table[1]/tbody[1]/tr[1]/td[1]/input[@name='new_email']")
	WebElement email;
	
	@FindBy(xpath = "//table[1]/tbody[1]/tr[2]/td[1]/input[@name='new_name']")
	WebElement name;
	
	@FindBy(xpath = "//table[1]/tbody[1]/tr[4]/td[1]/input[@name='new_userPassword']")
	WebElement password;
	
	@FindBy(xpath = "//table[1]/tbody[1]/tr[6]/td[1]/div/select")
	WebElement campign;
	
	@FindBy(xpath = "//table[1]/tbody[1]/tr[7]/td[1]/select")
	WebElement profess;
	
	@FindBy(xpath = "//table[1]/tbody[1]/tr[5]/td[1]/select")
	WebElement role;
	
	
	@FindBy(xpath = "//body/div[@id='root']/div[1]/div[2]/main[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[6]/div[1]/div[1]/div[1]/button[1]")
	WebElement createBtn;
	
	
	@FindBy(xpath = "//button[contains(text(),'Upload Excel')]")
	WebElement bulkUpload;
	
	
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
	
	private By uuid = By.id("username");
By uuu = 	By.xpath("//a[contains(text(),'Create User /Agent')]");

	
	
	
	//a[contains(text(),'Create User /Agent')]
	//table[@class='table table-striped table-hover']/tbody/tr
	
	
	
	
	public AccountCreationPage(WebDriver driver) {
		setWebDriver(driver);
		System.out.println(uuid);
		
	}
	
	
	
	public void singleCreateAccount(String Type, String Email, String Password, String Name,String Campign, String Profession) {
		
		String em = TestUtil.getCurrentDate();
		email.sendKeys(Email + em.replace("/", ""));
		name.sendKeys(Name + em.replace("/", ""));
		password.sendKeys(Password);
		TestUtil.selectItemByVisibleText(role, Type);
		TestUtil.selectItem(campign,1);
		TestUtil.selectItem(profess, 1);
		
		
		createBtn.click();
		
		
	}
	
	
	public void bulkCreationAccount() {
		usernames = ExcelLibraries.getUserNames();
	
		jsExecutorClickOn(bulkUpload);
		
		chooseFile.sendKeys(System.getProperty("user.dir")+ "/src/main/java/config/new_agent.xlsx");
		
		clickOnElement(uploadFile);
		
		uploadFile.sendKeys(Keys.ENTER);
		
		
		int count = DetailTable.size();
		int j = 0;
		
		for(int i = 0; i<=count;i++) {
			j=i+1;
			WebElement userDetail = pbDriver.findElement(By.xpath("//table[@class='table table-striped table-hover']/tbody/tr["+j+"]//td[1]"));
			WebElement statusMsg = pbDriver.findElement(By.xpath("//table[@class='table table-striped table-hover']/tbody/tr["+j+"]//td[3]"));
		
			
			String UserName = usernames[i].replace("\"", "");
			UserName = UserName.replace("\"", "");
		
			if(UserName.equalsIgnoreCase(userDetail.getText())) {
				if(!statusMsg.getText().equalsIgnoreCase("success")) {
					System.out.println("Error in "+userDetail.getText().toString() +" Check Error -  "+statusMsg.getText() );
					
				}
			}
		}
		
		
	}

	
	
	
	
	
	
	public void createAcount(String Method ,String Type, String Email, String Password, String Name,String Campign, String Profession ) {
		
		jsExecutorClickOn(clickOnTab);
		
		
		if(Method.contains("Single")) {
			singleCreateAccount(Type, Email, Password, Name, Campign, Profession);
		}else
			bulkCreationAccount();
	}
}
