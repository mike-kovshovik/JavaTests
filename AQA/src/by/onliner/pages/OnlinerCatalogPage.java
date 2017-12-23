package by.onliner.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class OnlinerCatalogPage
{
	private static final Logger log = Logger.getLogger(OnlinerCatalogPage.class);
	private WebDriver driver;
	private WebDriverWait wait;
	
	public static final By beautyAndSportLink = By.xpath("//*[@id='container']//span[contains(text(), 'Красота')]");
	public static final By hobbyMenuItemLink = By.xpath("//*[@id='container']//div[contains(text(), 'Хобби')]");
	public static final By radioControlAirModelsLink = By.xpath("//div[@id='container']//span[contains(text(), 'Радиоуправляемые авиамодели')]");
	
	
	public OnlinerCatalogPage(WebDriver driver, WebDriverWait wait)
	{
		this.driver=driver;
		this.wait=wait;
	}
	
	public OnlinerCatalogPage selectCatalogTopMenuItem(By topMenuItem)
	{
		log.info("[Step] select top menu item from Onliner Catalog");
		wait.until(ExpectedConditions.elementToBeClickable(topMenuItem)).click();
		return this;
	}
	
	
	public OnlinerCatalogPage selectLeftMenuItem(By leftMenuItem)
	{
		log.info("[Step] select left side menu item from Onliner Catalog");
		wait.until(ExpectedConditions.elementToBeClickable(leftMenuItem)).click();
		return this;
	}
	
	
	public QuadrokoptersPage selectCategoryFromGrid(By itemFromTheGrid)
	{
		wait.until(ExpectedConditions.elementToBeClickable(itemFromTheGrid)).click();
		return new QuadrokoptersPage(driver, wait);
	}
	
}