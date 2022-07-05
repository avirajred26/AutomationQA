package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.PageBase;

public class AIPage extends PageBase{
	
	@FindBy(className  = "pt-3 pb-0 heading col-md-12")
	WebElement accessDenied;
	
	
	@FindBy(xpath  = "//button[normalize-space()='Add Rule']")
	WebElement addRule;
	
	@FindBy(xpath  = "//input[@placeholder='Enter name']")
	WebElement enterName;
	
	
	
	

	public AIPage(WebDriver driver) {
		setWebDriver(driver);
		
	}
	
	
	
	public void createRule() {
		
		if(!waitForElementToAppearBoolean(accessDenied)) {
			
			
			
		}
		
		
	}

}
