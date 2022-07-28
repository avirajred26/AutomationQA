package pages;

import java.awt.AWTException;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.PageBase;
import utils.TestUtil;

public class NoticeDraftPage extends PageBase {
	

	String noticeTypevalue;
	
	@FindBy(xpath = "//button[normalize-space()='Add Notice Type']")
	WebElement addNoticeType;
	
	@FindBy(xpath = "//input[@name='case_type_new']")
	WebElement addNoticeValue;
	
	@FindBy(xpath = "//button[normalize-space()='Submit']")
	WebElement submitNoticeType;
	
	@FindBy(xpath = "//button[normalize-space()='Upload Draft']")
	WebElement uploadDraftButton;

	
	
	@FindBy(xpath = "//div[@class=' css-1hwfws3'][1]")
	WebElement clickInputNoticeType;

	@FindBy(xpath = "//label[normalize-space()='Choose File']")
	WebElement chooseDraft;
	////label[normalize-space()='Choose File']
	
	@FindBy(xpath = "//input[@value='Upload File']")
	WebElement submitNoticeDraft;
	
	// new code 
	
	@FindBy(xpath = "//input[@value='Upload File']")
	public List <WebElement> DAU;
	
	@FindBy(xpath = "//button[contains(text(),'Download Sample Excel')]")
	WebElement DownloadSampleExcel ;
	
	@FindBy(xpath = "//input[@id='is-vernacular-checkbox']")
	WebElement clickVernacular;
	
	
	@FindBy(xpath = "//div[contains(text(),'Select')]")
	WebElement selectLanguage;
	
	
	//button[normalize-space()='Upload File']
	@FindBy(xpath = "//button[normalize-space()='Upload File']")
	WebElement uploadFile;
	
	
	
	

	
	//Constructor
	public NoticeDraftPage(WebDriver driver) {
		setWebDriver(driver);
	}
	
	
	// download sample excel
	
	
		public void GetDownloadSampleExcel() {
		
			jsExecutorClickOn(DownloadSampleExcel);
			
				
		}
		
	
	
	

	
	//Create Notice Type
	public void setNoticeType(String  noticeType) {
		noticeTypevalue=noticeType;
		
		jsExecutorClickOn(addNoticeType);
		//addNoticeType.click();
		
		addNoticeValue.sendKeys(noticeType);
		
		jsExecutorClickOn(submitNoticeType);
		//submitNoticeType.click();
	}
	
	
	
	
	
	
	//Upload New Draft
	public void uploadDraft(String draftName,String vernacularLang) throws AWTException {
		jsExecutorClickOn(uploadDraftButton);
		
		// draftName = draftName.toUpperCase();
	
		//TestUtil.selectItemByVisibleText(selectNoticeType, draftName);
		
		
		clickInputNoticeType.sendKeys(noticeTypevalue, Keys.RETURN);
		
		jsExecutorClickOn(clickVernacular);
		
		
		selectLanguage.sendKeys(vernacularLang, Keys.RETURN);
		
		chooseDraft.sendKeys(TestUtil.getFile);
		
		jsExecutorClickOn(uploadFile);
		
		
		
		
		
	
		
	
		// clickOnElement(submitNoticeDraft);
		
		//submitNoticeDraft.sendKeys(Keys.ENTER);
		
	}
	
	
	
	
	/*public void noticeDraftCreation(String type, String noticeType) throws AWTException, InterruptedException {
		
		if(type.equalsIgnoreCase("Existing")) {
			uploadDraft(noticeType);
			
		}else {
			setNoticeType(noticeType);
			Thread.sleep(4000);
			uploadDraft(noticeType);
			
		} 
		
	}*/
	
	
}
	
	

