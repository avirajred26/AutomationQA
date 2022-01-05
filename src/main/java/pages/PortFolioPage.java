package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.PageBase;
import utils.TestUtil;

public class PortFolioPage extends PageBase {

	@FindBy(xpath = "//table[@class='capitalize admin_portfolio_table table']//tbody//tr[1]//td[1]//a")
	public WebElement credgenicsCompany;
	
	@FindBy(css = "#searchKeyword")
	public WebElement searchEnter;
	
	
	@FindBy(xpath = "//table[@class='capitalize admin_portfolio_table table']//tbody//tr[1]//td[1]//a//span[1]")
	public WebElement searchClicked;
	
	@FindBy(xpath = "//body/div[@id='root']/div[1]/div[2]/main[1]/div[1]/div[1]/div[5]/div[1]/div[1]/div[1]/div[1]")
	public WebElement portTable;
	
	
	@FindBy(xpath = "//body/div[@id='root']/div[1]/div[2]/main[1]/div[1]/div[1]/div[4]/div[1]/div[1]/div[1]/div[1]/div[1]/span[1]/div[1]/div[3]/div[1]/select[1]")
	public WebElement selectOption;
	
	@FindBy(xpath = "//input[@id='searchKeyword']")
	public WebElement searchType;
	
	public static WebElement eloan;
	public static String loanNumber;
	
	@FindBy(xpath = "//input[@id='searchKeyword']")
	public WebElement sortingType;
	
	@FindBy(xpath = "//div[@id='allocation_dropdown']//button[@id='dropdown_toggle']")
	public WebElement dataSelection;
	
	@FindBy(xpath = "//div[@class='dropdown-menu show']//button[2]")
	public WebElement clickonMonth;
	
	@FindBy(xpath = "//div[@class='year-month-wrapper']//div[@class='year-month']//div")
	public List<WebElement> monthPicker;
	
	
	
	@FindBy(xpath = "//input[@id='all']")
	public WebElement selectAll;
	
	@FindBy(xpath = "//ul[@class='list-unstyled nav flex-column']//li")
	public List <WebElement> MunuBarList;
	
	
	@FindBy(xpath = "//div[@class='year-month-wrapper']//div[1]//div[2]")
	public WebElement yearMonth;
	
	@FindBy(xpath = "//div[@class='year-month-wrapper']//div[1]//div[1]")
	public WebElement yearPrev;
	
	
	@FindBy(xpath = "//div[@class='year-month-wrapper']//div[1]//div[3]")
	public WebElement yearNext;
	
	
	
	
	

	public PortFolioPage(WebDriver driver) {
		setWebDriver(pbDriver);
	}
	
	public void clickOnCompany(String method,String companyName) {
		waitForElementToAppear(credgenicsCompany);
		if(method.equalsIgnoreCase("Click")) {
			jsExecutorClickOn(credgenicsCompany);
	
			
		}else if(method.equalsIgnoreCase("Search")) {
			jsExecutorClickOn(searchEnter);
			searchEnter.sendKeys(companyName,Keys.ENTER);
			WebElement dynamicel = pbDriver.findElement(By.xpath("//td[1]//span[contains(text(),'"+companyName+"')]"));
		
			moveToElement(dynamicel);
			jsExecutorClickOn(dynamicel);
			
		
			
		
		}
		
	}
	
	public boolean searchLoan(String loan) {
		TestUtil.selectItem(selectOption, 1);
		searchType.sendKeys(loan,Keys.ENTER);
		 eloan = pbDriver.findElement(By.xpath("//span[contains(text(),'"+loan+"')]"));
		//span[contains(text(),'"+loan+"')]
		if(waitForElementToAppearBoolean(eloan)!=null) {
			loanNumber = eloan.getText().toString();
			jsExecutorClickOn(eloan);
			
			System.out.println(loanNumber);
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public boolean portfolioActivity() {
		if(waitForElementToAppearBoolean(portTable)!=null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	public void sortLoans(String value) {
		TestUtil.selectItemByVisibleText(sortingType, value);
		
	}
	
	public void selectMonth(String month,String YearValue) {
		jsExecutorClickOn(dataSelection);
		jsExecutorClickOn(clickonMonth);
		
		
		String monthValue = yearMonth.getText();
		
		monthValue = 	monthValue.substring(4);
		monthValue =	monthValue.trim();
		
		
		int iMonthValue = Integer.valueOf(monthValue); 
		
		if(iMonthValue>Integer.valueOf(YearValue)) {
			jsExecutorClickOn(yearPrev);
		}else {
			jsExecutorClickOn(yearNext);
		}
		
		int count = monthPicker.size();
		
		for(int i = 0;i<=count;i++) {
			if(monthPicker.get(i).getText().contains(month)) {
				System.out.println(monthPicker.get(i).getText());
				jsExecutorClickOn(monthPicker.get(i));
				
				break;
				
			}
		}
		
	}
	
	public void selectMultipleLoans(String loan) {
		
		
		searchType.sendKeys(loan,Keys.ENTER);
		selectAll.click();
		
	}
	
	
	public void selectMenu(String Menu ) {
	
		WebElement el = pbDriver.findElement(By.linkText(Menu));
		
	jsExecutorClickOn(el);
	
		}
	

	
	
	
}
