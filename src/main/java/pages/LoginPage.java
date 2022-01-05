package pages;



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
	
	@FindBy(xpath = "//button[@id='login-btn-lp']")
	public WebElement loginBtn;
	
	@FindBy(xpath = "//input[@name='username']")
	public WebElement userName;
	
	
	@FindBy(xpath = "//input[@id='pass-field']")
	public WebElement password;

	@FindBy(xpath = "//button[normalize-space()='Login']")
	public WebElement signBTN;

	
	@FindBy(xpath = "//input[@id='pass-field']")
	public WebElement otpTextBox;

	
	@FindBy(css = "#login-btn-lp[type='submit']")
	public WebElement otpSignin;

	
	@FindBy(xpath = "//span[@class='capitalize mv-short']")
	public WebElement verifyUsername;
	
	@FindBy(xpath = "	//input[@id='login']")
	public WebElement otpUSer;
	
	@FindBy(xpath = "//tbody//tr[2]/td[1]//p")
	//tbody tr td div p
	public WebElement otpValue;
	
	//input[@id='login']
	@FindBy(xpath = "//body//div[@class='mctn']//div")
	public List <WebElement> mailList;
	
	
	@FindBy(xpath = "	//div[@class='alert alert-danger']")
	public WebElement wrongPassword;
	
	//*[contains(text(),'ABC')]
	@FindBy(css = "span[class='ellipsis']")
	WebElement time;
	

	
	String pWindow,otp,user,timeNow;
	List<WebElement> dynamicTime;
	
	
	
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
	
	public String getOTP(String user) throws Throwable {
		pWindow =  pbDriver.getWindowHandle();
		openTab();
		
	 	Set<String> setWindows = pbDriver.getWindowHandles();
		for(String x:setWindows) {
			if(!pWindow.equals(x)) {
				pbDriver.switchTo().window(x);
				
			}
			
		}
		
		
		pbDriver.navigate().to("https://yopmail.com");
		
		user = user.replace("@yopmail.com", "");
		otpUSer.sendKeys(user,Keys.ENTER);
		waitDriver();
		pbDriver.switchTo().frame("ifinbox");
		for (int i = 0; i<=mailList.size();i++) {
			

			
			
		
			pbDriver.switchTo().defaultContent();
			pbDriver.switchTo().frame("ifmail");
			System.out.println(time.getText());
			timeNow  =	TestUtil.removeDayandDate(time.getText());
			
			if (TestUtil.getTime(30).contains(timeNow)) {
		        otp = otpValue.getText();
		        pbDriver.switchTo().defaultContent();
		        
		        break;
		       
			}	
			else {
				pbDriver.switchTo().defaultContent();
				pbDriver.switchTo().frame("ifinbox");
				jsExecutorClickOn(mailList.get(i+3));
				//mailList.get(i+3).click();
				pbDriver.switchTo().defaultContent();
				pbDriver.switchTo().frame("ifmail");
				otp = otpValue.getText();
			    pbDriver.switchTo().defaultContent();
			    break;
				
				
			}
		}
	    
        pbDriver.switchTo().defaultContent();
        pbDriver.switchTo().window(pWindow);
	    //pbDriver.close();
		return otp;
		
	}
	
	public void loginActivity(String username, String Password) throws Throwable {
	

		jsExecutorClickOn(loginBtn);
		
		setUsername(username);
		
		setPasswrod(Password);
		
		jsExecutorClickOn(signBTN);
		
		user = username;
		
	}
	
		public void setOTP() throws Throwable {
		waitDriver();
		
		otp =	getOTP(user);
		
		pbDriver.switchTo().window(pWindow);
		
		otpTextBox.sendKeys(otp);
		
		
		jsExecutorClickOn(otpSignin);
			
		
	}
	
	public Boolean verifyUsernameDispalyed() {
		return verifyUsername.isDisplayed();
	}
	
	public Boolean verifyforWrongPassword() {
		
		try {
			if(waitForElementToAppearBoolean(wrongPassword)==null) {
				
				return true;
			}
			
		}catch(Exception e) {
			
		}
		return false;
		
	}
	
	
	
	
	
	
}
