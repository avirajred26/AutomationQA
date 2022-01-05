package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.PageBase;

public class FiltersPage extends PageBase {
	
	@FindBy(className    = "//div[@class='filter-body modal-body']//h5")
	List <WebElement> filtersHeaderValue;
	
	@FindBy(className = "//button[@class='filter-by false']")
	WebElement filterBtn;
	
	
	@FindBy(className = "//button[contains(text(),'Apply Filters')]")
	WebElement filterApplied;
	
	
	public FiltersPage(WebDriver driver) {
		setWebDriver(driver);
		
		
	}
	
	
	
	public void selectFilters(String arr1 ,String arr2) {
		filterBtn.click();
		
		String[ ]filterName = arr1.split(",");
		String [] filterValue = arr2.split(",");
		
		for(int i = 0 ; i< filterName.length;i++) {
			
			if(filtersHeaderValue.get(i).getText().contains(filterName[i])) {
				filtersHeaderValue.get(i).click();
				
				for(int j=1;j<=filterValue.length;j++) {
					
					WebElement filteValues = pbDriver.findElement(By.xpath("//div[@class='filter-body modal-body']//div["+j+"]//div"));
					
					if(filteValues.getText().contains(filterValue[j])) {
						filteValues.click();
					}
					
				}
			}
			
			
			
		}
		
		
		
		
	}

}
