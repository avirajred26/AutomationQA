package base;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageBase {
	protected static WebDriver pbDriver;
	private WebDriverWait wait;
	private static final int iTimeOut = 5; //seconds
	private  static final int iPolling = 1; //milliseconds
	
	    

	public void setWebDriver(WebDriver driver) {
		PageBase.pbDriver = driver;
		wait =  new WebDriverWait(driver, Duration.ofSeconds(iTimeOut,iPolling));
		
	    PageFactory.initElements(PageBase.pbDriver, this);
	}
	
	protected void waitForElementToAppear(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }
	
	
	
	protected Boolean waitForElementToAppearBoolean(WebElement element) {
       return  wait.until(ExpectedConditions.visibilityOf(element))!=null;
    }
	
	
	protected Boolean waitForElementsToAppearBoolean(List <WebElement> element) {
	       return  wait.until(ExpectedConditions.visibilityOfAllElements(element))!=null;
	    }
	
	
	
	@SuppressWarnings("deprecation")
	public void waitDriver() throws Throwable {
		pbDriver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
		Thread.sleep(3000);
	}
	
	public void waitForElementToClickable(WebElement element) throws Throwable{
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	
	
	
	public Boolean checkForElementToClickable(WebElement element) throws Throwable{
		return wait.until(ExpectedConditions.elementToBeClickable(element))!=null;
	}
	
		public void moveToElement(WebElement element) {
			Actions actions = new Actions(pbDriver); 
			   actions.moveToElement(element); 
		}
		public static void jsExecutorClickOn(WebElement element){
	        ((JavascriptExecutor) pbDriver).executeScript("arguments[0].click();",element);
	    }
		
		
		public void jsExecutorscrollIntoView(WebElement element){
	        ((JavascriptExecutor) pbDriver).executeScript("arguments[0].scrollIntoView();",element);
	    }
		
	
		
		public  void openTab() throws InterruptedException {
			Thread.sleep(2000);
			JavascriptExecutor jse = (JavascriptExecutor)pbDriver;
			jse.executeScript("window.open()");
			
		}
		
		public void sendKeysINTByJS( WebElement element, int attributeValue){
		    JavascriptExecutor js = ((JavascriptExecutor) pbDriver);
		    js.executeScript("arguments[0].setAttribute('value','"+attributeValue+"');", element);
		}
		
		
		public static void clickOnElement(WebElement el) {
			
			
			int width = el.getSize().getWidth();
	
			                Actions action = new Actions(pbDriver);
			                action.moveToElement(el).build().perform();
			            
			                action.moveToElement(el).moveByOffset((width/2)-2, 0).click().perform();
		}
		
}
