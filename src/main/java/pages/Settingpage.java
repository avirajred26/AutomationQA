package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.PageBase;

public class Settingpage extends PageBase {
	
	@FindBy(xpath = "//ul[@class = 'sidebar-category-option-container']//child::li")
	public List <WebElement> MunuBarList;
	
	
	

	public Settingpage(WebDriver driver) {
		
		setWebDriver(driver);
		// TODO Auto-generated constructor stub
	}
	
	
	
	public void selectSettingTab(String tabName) {
		

		
		WebElement el = pbDriver.findElement(By.linkText(tabName));
		
		jsExecutorClickOn(el);
		
	}

}
