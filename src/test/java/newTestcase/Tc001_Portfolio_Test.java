package newTestcase;

import java.awt.AWTException;

import org.testng.annotations.Test;
import org.testng.annotations.Test;

import base.TestBase;
import pages.NoticeDraftPage;

public class Tc001_Portfolio_Test extends TestBase {

	NoticeDraftPage notice;
	
	
	
	@Test(priority = 2)
	public void portfolioTest() throws AWTException {
		objPort.clickOnCompany("SearchSearch", "load Testing");
		objPort.selectMenu("Notice Drafts");
		
		notice = new NoticeDraftPage(driver);
		
		notice.GetDownloadSampleExcel();
		notice.setNoticeType("rahul");
		notice.uploadDraft("random","english");
		
		
		
	
		
		
		
	}

}
