package by.onliner.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author michaelkovshovik
 *
 */
public class OnlinerHomePage
{
	WebDriver driver;
	WebDriverWait wait;
	
	
	By catalogLink = By.linkText("Каталог");
	
	
	public OnlinerHomePage(WebDriver driver, WebDriverWait wait)
	{
		this.driver=driver;
		this.wait=wait;
	}
	
	public void clickCatalogLink()
	{
		wait.until(ExpectedConditions.elementToBeClickable(catalogLink));
		driver.findElement(catalogLink).click();
	}

}
