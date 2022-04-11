package pages;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import base.PageBase;

import subClasses.AnalyticDashBoardData;

public class DashBoardPage extends PageBase {
	

	
	
	 
		@FindBy(xpath = "//ul[@class = ' card-header-tabs  ml-0 mr-0 nav nav-tabs']//li")
		List <WebElement> tabs;

	AnalyticDashBoardData objAnay;
	
	public DashBoardPage(WebDriver driver) {
		setWebDriver(driver);
		objAnay = new AnalyticDashBoardData(driver) ;
		
	}
	
	
	
	
	public void switchTOTab(String tab) {
		
		for(int i= 0; i<=tabs.size();i++) {
			if(tabs.get(i).getText().contains(tab) || tabs.get(i).getText().equalsIgnoreCase(tab)) {
				jsExecutorClickOn(tabs.get(i));
			}
		}
			
			
		}
	
	public boolean checkLoanCount(int loanFromPortfolio) {
		
		if(loanFromPortfolio == Integer.valueOf(objAnay.getLoanCount())) {
			return true;
		}else {
			return false;
		}
		
	}
	
	public void getRecoveryStatus() {
		
		objAnay.getStatusDashboardValue();
		
	}
	


}
