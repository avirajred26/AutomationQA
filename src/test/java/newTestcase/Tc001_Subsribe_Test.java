package newTestcase;

import java.io.IOException;

import org.testng.annotations.Test;

import com.opencsv.exceptions.CsvException;

import org.testng.annotations.Test;

import base.TestBase;
import pages.AllTopicPage;
import utils.TestUtil;
import utils.csvLibrary;

public class Tc001_Subsribe_Test extends TestBase {

	AllTopicPage objtop;
	@Test(priority = 2,dataProvider = "test")
	public void portfolioTest(String Scenario) throws InterruptedException, IOException, CsvException {
		
		objHome.selectMainTabs("All Topics");
		objtop = new AllTopicPage(driver);
		
		TestUtil.selectItemByVisibleText(objtop.sortBy, "Date of topic update");
		
		String topicName = csvLibrary.readColValue(Scenario, "topicName");
		
		objtop.selectTopic(topicName);
		
		String subtopicName = csvLibrary.readColValue(Scenario, "subTopicName");
		
		objtop.search_for_subTopic_click_to_suscribe(subtopicName);
		
		if(!csvLibrary.readColValue(Scenario, "Unsuscribe").contains("No")) {
			
			objHome.selectUserMono("My Subscriptions");
			
			objtop.want_to_unsuscribe(subtopicName);
			
		}
	
		
	}

}
