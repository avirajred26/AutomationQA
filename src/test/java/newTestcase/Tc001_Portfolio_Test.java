package newTestcase;

import org.testng.annotations.Test;
import org.testng.annotations.Test;

import base.TestBase;

public class Tc001_Portfolio_Test extends TestBase {

	
	@Test(priority = 3)
	public void portfolioTest() {
		objPort.selectLoanbyNumber("1","5","");
		
	}

}
