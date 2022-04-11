package subClasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.PageBase;

public class AnalyticDashBoardData extends PageBase {
	@FindBy(xpath = "//div[@class='justify-content-between align-items-center card-body']//div[@class='mb-0 mt-3 card-title fw-700']//a")
	public WebElement totalLoan;
	@FindBy(xpath = "//*[local-name() = 'svg']/*[name()='g' and @class = 'highcharts-axis-labels highcharts-xaxis-labels']/*[name() = 'text'][@text-anchor='end']")
	public List<WebElement> statusXAxis;
	@FindBy(xpath = "//*[local-name() = 'svg']//*[name()='g' and @class = 'highcharts-series highcharts-series-0 highcharts-column-series highcharts-tracker']/*[name() = 'rect']")
	public List<WebElement> statusXAxisBar;

	public AnalyticDashBoardData(WebDriver driver) {
		
		setWebDriver(driver);
		
	}
	
	
	public String getLoanCount() {
		 return totalLoan.getText();
	}
	
	public void getStatusDashboardValue() {
		waitForElementToAppear(statusXAxis.get(2));
		
		int count = statusXAxisBar.size();
		System.out.println(count);
		
		for(int i = 0;i<count;i++) {
			System.out.println();
			int j = i +1;
			
			WebElement statusXAxisValue = pbDriver.findElement(By.xpath("//*[local-name() = 'svg']/*[name()='g' and @class = 'highcharts-data-labels highcharts-series-0 highcharts-column-series highcharts-tracker']/*[name() = 'g']["+j+"]/*[name()='text']/*[name()='tspan']"));
			System.out.println("Status Name "+statusXAxis.get(i).getText()+" Value is "+ statusXAxisValue.getText());
		}
		
		
	}
}