package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.PageBase;
import utils.TestUtil;

public class HomePage extends PageBase {
	@FindBy(xpath = "//nav/child::a")
	public List<WebElement> subTopicNames;
	
	
	@FindBy(xpath = "(//*[name()='svg'][@class='svg-inline--fa fa-angle-down '])[1]")
	public WebElement userDropDown;
	
	
	@FindBy(xpath = "//div[@class='chakra-menu__group']/child::a")
	public List<WebElement> menuList;
	
	

	public HomePage(WebDriver driver) {
		setWebDriver(pbDriver);
	}

	public void selectMainTabs(String tabName) throws InterruptedException {
		
		Thread.sleep(2000);
		
		waitForElementsToAppearBoolean(subTopicNames);
		
		for (WebElement webElement : subTopicNames) {
			
			System.out.println(webElement.getText());
			if(webElement.getText().equalsIgnoreCase(tabName)) {
				
				jsExecutorClickOn(webElement);
				break;
			}
		}
		
		
		
		
	}
	
	public void selectUserMono(String OptionValue) throws InterruptedException {
		Thread.sleep(3000);
		
		clickonsvgElement(userDropDown);
		Thread.sleep(1000);
		for (WebElement webElement : menuList) {
		
			if(webElement.getText().contains(OptionValue)) {
				jsExecutorClickOn(webElement);
				break;
			}
			
		}
		
		
		
	}
	
	
}
