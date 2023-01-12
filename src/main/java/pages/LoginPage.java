package pages;



import java.text.ParseException;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.FindBy;
import base.PageBase;
import utils.TestUtil;

public class LoginPage extends PageBase {

	
	@FindBy(xpath = "//input[@id='username']")
	public WebElement userName;
	
	
	@FindBy(xpath = "//input[@id='password']")
	public WebElement password;

	@FindBy(xpath = "//button[normalize-space()='Sign In']")
	public WebElement signBTN;

	
	
	@FindBy(xpath = "(//img[@alt='Riskipedia'])[2]]")
	public WebElement verifyUsername;
	
	
	
	
	
	public LoginPage(WebDriver driver) {
		
		setWebDriver(driver);
	
	}
	

	
	
	public void setUsername(String user) {
		userName.clear();
		userName.sendKeys(user);
	}
	
	public void setPasswrod(String pass) {
		password.clear();
		password.sendKeys(pass);
	}
	
	
		

	
	public void loginActivity(String username, String Password) throws Throwable {
	

		
		setUsername(username);
		
		setPasswrod(Password);
		
		jsExecutorClickOn(signBTN);
		
		
	}
	
	
	
	
	public void resertPassword() {
		
		/*pass1.sendKeys("Test@123");
		pass2.sendKeys("Test@123");
		
		changeButton.click();*/
	}
	
		
	
	
	public Boolean verifyUsernameDispalyed() {
		return verifyUsername.isDisplayed();
	}
	
	public Boolean verifyforWrongPassword() {
		
		return false;
		
	}
	
	
	
	
	
	
}
