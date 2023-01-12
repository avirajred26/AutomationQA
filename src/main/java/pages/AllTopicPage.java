package pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.opencsv.exceptions.CsvException;

import base.PageBase;
import utils.csvLibrary;

public class AllTopicPage extends PageBase {
	
	@FindBy(xpath = "//select[@aria-labelledby='sort-by']")
	public WebElement sortBy;
	
	@FindBy(xpath = "//div[@class='css-1k5s00q']//div/child::p[1]")
	public List<WebElement> subTopicNames;
	
	
	@FindBy(xpath = "//button[normalize-space()='Done']")
	public WebElement doneBtn;
	
	@FindBy(xpath = "//input[@aria-label='filter']")
	public WebElement searchField;
	
	
	
	
	public AllTopicPage(WebDriver driver) {
		setWebDriver(driver);
		
	}
	
	public void selectTopic(String topicName) throws InterruptedException {
		

		Thread.sleep(2000);

		searchField.sendKeys(topicName,Keys.ENTER);
		
		Thread.sleep(2000);
		WebElement el = pbDriver.findElement(By.xpath("//a[normalize-space()='"+topicName+"']"));
		
		waitForElementToAppear(el);
		
		jsExecutorClickOn(el);
		
	}
	
	//div[6]//div[1]//button[1]
	public void search_for_subTopic_click_to_suscribe(String subTopicName) {
		
		int subTopicSize = 0;
		
		for (WebElement webElement : subTopicNames) {
			subTopicSize++;
			System.out.println(webElement.getText());
			if(webElement.getText().equalsIgnoreCase(subTopicName)) {
				
				WebElement elToSuccribe = pbDriver.findElement(By.xpath("//div[@class='css-79elbk']["+subTopicSize+"]//div[1]//button[1]"));
				jsExecutorClickOn(elToSuccribe);
				break;
			}
			
		}
		
		jsExecutorClickOn(doneBtn);
		
		
	}
	

	
	public void want_to_unsuscribe(String colName) throws IOException, CsvException, InterruptedException {
		
		Thread.sleep(2000);
			int subTopicSize = 0;
			List<WebElement> elList = pbDriver.findElements(By.xpath("//div[@class='chakra-stack css-dk347']/child::p[1]"));
			for (WebElement webElement : elList) {
				subTopicSize++;
				System.out.println(webElement.getText());
				if(webElement.getText().equalsIgnoreCase(colName)) {
					
					WebElement elToUNSuccribe = pbDriver.findElement(By.xpath("(//button[@title='Unsubscribe'])["+subTopicSize+"]"));
					jsExecutorClickOn(elToUNSuccribe);
					break;
				}
				
			}
			jsExecutorClickOn(doneBtn);
			
		
		
	}

}
