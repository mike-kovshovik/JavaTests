package by.onliner.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class QuadroHubsanDetails
{
	WebDriver driver;
	WebDriverWait wait;
	
	By quadroType = By.xpath("//table[@class='product-specs__table']//tr[2]/td");
	By quadroTypeValue = By.xpath("//table[@class='product-specs__table']//tr[2]/td[2]");
	
	By materialType = By.xpath("//table[@class='product-specs__table']//tr[3]/td");
	By materialTypeValue = By.xpath("//table[@class='product-specs__table']//tr[3]/td[2]");
	
	By engineType = By.xpath("//table[@class='product-specs__table']//tr[5]/td");
	By engineTypeValue = By.xpath("//table[@class='product-specs__table']//tr[5]/td[2]");
	
	By cartLink = By.xpath("//a[@data-href='https://cart.onliner.by']");
	By itemInCartLink = By.xpath("//ul[@id='b-top-navigation-cart']//span");
	
	
	
	
	public QuadroHubsanDetails(WebDriver driver, WebDriverWait wait)
	{
		this.driver = driver;
		this.wait = wait;
	}
	
	
	public void verifyInitiallySelectedParametersAreCorrect()
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(quadroType));
		
		Assert.assertEquals("Типквадрокоптер", driver.findElement(quadroType).getText()+driver.findElement(quadroTypeValue).getText());
		
		Assert.assertTrue((driver.findElement(materialType).getText().contentEquals("Материал") && driver.findElement(materialTypeValue).getText().contentEquals("пластик")) || 
	    		(driver.findElement(materialType).getText().contentEquals("Материал") && driver.findElement(materialTypeValue).getText().contentEquals("металл")));
		
		Assert.assertEquals("Тип электродвигателябесколлекторный", driver.findElement(engineType).getText()+driver.findElement(engineTypeValue).getText());
	}
	
	public void addToCart()
	{
		wait.until(ExpectedConditions.elementToBeClickable(cartLink)).click();
	}
	
	
	public void verifyNumerOfItemsInTheCartIsCorrect()
	{
		wait.until(ExpectedConditions.textToBePresentInElementLocated(itemInCartLink, "1 товар"));
		Assert.assertEquals("1 товар", driver.findElement(itemInCartLink).getText());
	}
	
	
	public void clickNumberOfItemsInCartLink()
	{
		wait.until(ExpectedConditions.textToBePresentInElementLocated(itemInCartLink, "1 товар"));
		driver.findElement(itemInCartLink).click();
	}
	
}
