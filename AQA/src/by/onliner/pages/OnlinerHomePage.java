package by.onliner.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author michaelkovshovik
 *
 */
public class OnlinerHomePage
{
	WebDriver driver;
	
	By catalogLink = By.linkText("Каталог");
	
	
	public OnlinerHomePage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void clickCatalogLink()
	{
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.elementToBeClickable(catalogLink));
		driver.findElement(catalogLink).click();
	}

}
