package by.onliner.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class OnlinerHomePage
{
	private WebDriver driver;
	private WebDriverWait wait;
	
	
	private static final By catalogLink = By.linkText("Каталог");
	
	
	public OnlinerHomePage(WebDriver driver, WebDriverWait wait)
	{
		this.driver=driver;
		this.wait=wait;
	}
	
	public OnlinerCatalogPage clickCatalogLink()
	{
		wait.until(ExpectedConditions.elementToBeClickable(catalogLink));
		driver.findElement(catalogLink).click();
		return new OnlinerCatalogPage(driver, wait);
	}

}
