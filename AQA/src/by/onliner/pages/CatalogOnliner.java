package by.onliner.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CatalogOnliner
{
	private WebDriver driver;
	private WebDriverWait wait;
	
	private final By beautyAndSportLink = By.xpath("//*[@id='container']//span[contains(text(), 'Красота')]");
	private final By hobbyMenuItemLink = By.xpath("//*[@id='container']//div[contains(text(), 'Хобби')]");
	private final By radioControlAirModelsLink = By.xpath("//div[@id='container']//span[contains(text(), 'Радиоуправляемые авиамодели')]");
	
	
	public CatalogOnliner(WebDriver driver, WebDriverWait wait)
	{
		this.driver=driver;
		this.wait=wait;
	}
	
	public CatalogOnliner clickBeautyAndSport()
	{
		wait.until(ExpectedConditions.elementToBeClickable(beautyAndSportLink)).click();
		return this;
	}
	
	public CatalogOnliner clickHobby()
	{
		wait.until(ExpectedConditions.elementToBeClickable(hobbyMenuItemLink)).click();
		return this;
	}
	
	public void clickRadioControlAirModels()
	{
		wait.until(ExpectedConditions.elementToBeClickable(radioControlAirModelsLink)).click();
	}
	
}