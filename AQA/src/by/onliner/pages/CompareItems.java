package by.onliner.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CompareItems 
{
	
	WebDriver driver;
	WebDriverWait wait;
	
	By thirdItemInComparisonTable = By.xpath("//tr[@class='product-table__row product-table__row_header product-table__row_top']/th[5]//a");
	
	
	public CompareItems(WebDriver driver, WebDriverWait wait)
	{
		this.driver = driver;
		this.wait = wait;
	}
	
	public void clickThirdItemInComparisonTable()
	{
		wait.until(ExpectedConditions.elementToBeClickable(thirdItemInComparisonTable)).click();
	}
	
}
