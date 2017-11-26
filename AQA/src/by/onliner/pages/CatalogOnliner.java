package by.onliner.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CatalogOnliner
{
	WebDriver driver;
	
	By beautyAndSportLink = By.xpath("//*[@id='container']//span[contains(text(), 'Красота')]");
	By hobbyMenuItemLink = By.xpath("//*[@id='container']//div[contains(text(), 'Хобби')]");
	By radioControlAirModelsLink = By.xpath("//div[@id='container']//span[contains(text(), 'Радиоуправляемые авиамодели')]");
	
	
	public CatalogOnliner(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void clickBeautyAndSport()
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(beautyAndSportLink)).click();
	}
	
	public void clickHobby()
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(hobbyMenuItemLink)).click();
	}
	
	public void clickRadioControlAirModels()
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(radioControlAirModelsLink)).click();
	}
	
}