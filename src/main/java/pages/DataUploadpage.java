package pages;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.PageBase;
import utils.ExcelLibraries;

public class DataUploadpage extends PageBase {
	@FindBy(xpath = "//div[@class='tab-pane active']//div//div//div//div//table//tbody//tr")
	public List <WebElement> DetailsTable;
	
	int tableSize;
	
	@FindBy(xpath = "//a[contains(text(),'Loan Details')]")
	public WebElement loanDetailsTab;

	@FindBy(xpath = "//div[@class='tab-pane active']//div//div//div//div//table//tbody//tr//th")
	public List <WebElement> leftDetailsTable;
	
	@FindBy(xpath = "//div[@class='tab-pane active']//div//div//div//div//table//tbody//tr//td")
	public List <WebElement> rightDetailsTable;
	

	//div[@class='tab-pane active']//div//div//div//div//table//tbody//tr//th
	
	@FindBy(xpath = "//a[contains(text(),'Applicant Details')]")
	public WebElement applicantDetailsTap;
	
	@FindBy(xpath = "//body/div[@id='root']/div[1]/div[2]/main[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/button[1]")
	public WebElement clickOnUpload;
	
	@FindBy(xpath = "//input[@id='file']")
	public WebElement uploadFile;
	
	
	
	@FindBy(xpath = "//body/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/input[1]")
	public WebElement submit;
	
	
	@FindBy(xpath = "//button//span[contains(text(),'×')]")
	public WebElement close;
	
	

	
	
	
	
	public DataUploadpage(WebDriver driver) {
		setWebDriver(driver);
	}
	
	
	public void switchToTab(String tabName) {
		
		switch(tabName) {
		  case "Applicant Details":
			  jsExecutorClickOn(applicantDetailsTap);
		  
		    break;
		  case "Loan Details":
			  jsExecutorClickOn(loanDetailsTab);
		   
		    break;

		  default:
		    
		}
		
	}
	
	
	
	public void uploadFile() throws InterruptedException {
		
		jsExecutorClickOn(clickOnUpload);
		uploadFile.sendKeys(System.getProperty("user.dir")+ "/src/main/java/config/sample_excel_loan (16).xlsx");
		jsExecutorClickOn(submit);
		Thread.sleep(5000);
	
		//close.click();
	}
	
	
	public void validateData(String Tab1) throws Throwable {
		try {
		
		if(Tab1.contains("Loan Details")) {
			
			tableSize = DetailsTable.size();
			
			
			
			for(int i =0;i<=tableSize;i++) {
			String col=	ExcelLibraries.getDataUploadCol(leftDetailsTable.get(i).getText().toString().replace(" ", "_").toLowerCase());
			
			System.out.println(col);
				if(leftDetailsTable.get(i).getText().toString().replace(" ", "_").toLowerCase().contains(col)) {
					System.out.println(leftDetailsTable.get(i).getText().toString().replace(" ", "_").toLowerCase());
					String Value=	ExcelLibraries.getDataUploadValue(leftDetailsTable.get(i).getText().toString().replace(" ", "_").toLowerCase());
					
					if(!rightDetailsTable.get(i).getText().isBlank() || !rightDetailsTable.get(i).getText().isEmpty()) {
						
						
						if(rightDetailsTable.get(i).getText().contains(Value)) {
							System.out.println("There is Data");
						
					}
					else {
						System.out.println(leftDetailsTable.get(i).getText().toString() + "Is Empty");
					}
					}
					
				
					
			}
			
			
		}
			
			
			
		}else if(Tab1.contains("Applicant Details")) {
			switchToTab("Applicant Details");
			
			tableSize = DetailsTable.size();
			
			
			
			for(int i =0;i<=tableSize;i++) {
			String col=	ExcelLibraries.getDataUploadCol(leftDetailsTable.get(i).getText().toString().replace(" ", "_").toLowerCase());
			
			System.out.println(col);
			
				if(leftDetailsTable.get(i).getText().toString().replace(" ", "_").toLowerCase().contains(col)) {
					String Value=	ExcelLibraries.getDataUploadValue(leftDetailsTable.get(i).getText().toString().replace(" ", "_").toLowerCase());
					if(!rightDetailsTable.get(i).getText().isBlank() || !rightDetailsTable.get(i).getText().isEmpty()) {
						
						
						if(rightDetailsTable.get(i).getText().contains(Value)) {
							System.out.println("There is Data");
						
					}
					else {
						System.out.println(leftDetailsTable.get(i).getText().toString() + "Is Empty");
					}
					}
				
				
			}
			
			
		}
		
	}
	}
		catch(Exception e) {
			e.printStackTrace();
		}
	
	}

}
