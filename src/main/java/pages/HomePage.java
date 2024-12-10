package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.PageBase;
import utils.TestUtil;

public class HomePage extends PageBase {
	@FindBy(xpath = "//nav/child::a")
	public List<WebElement> subTopicNames;
	
	
	
	
}
