package by.onliner.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import junit.framework.Assert;

public class Radiocontrolair
{
	WebDriver driver;
	WebDriverWait wait;
	
	String radioControlHeader = "Радиоуправляемые авиамодели";
	String xpathForQuadroParameters = "//div[@id='schema-filter']//span[text()='%s']";
	String xpathListOfCheckboxesToCompare = "//div[@id='schema-products']/div[%s]//span[@class='i-checkbox__faux']";
	By radiocontrolAirModelHeader = By.className("schema-header__title");
	By quadrocopterCheckbox = By.xpath(String.format(xpathForQuadroParameters, "квадрокоптер"));
	By quadrocopterBodyTypePlastic = By.xpath(String.format(xpathForQuadroParameters, "пластик"));
	By quadrocopterBodyTypeMetal = By.xpath(String.format(xpathForQuadroParameters, "металл"));
	By rangeOfAction = By.xpath("//input[@placeholder='6']");
	By additionalParametersLink = By.linkText("Дополнительные параметры");
	By engineTypeBeskollektornyi = By.xpath(String.format(xpathForQuadroParameters, "бесколлекторный"));
	By numberOfItemsFound = By.xpath("//div[@id='schema-filter-button']//span[starts-with(text(), 'Найдено 20')]");
	By sortOrderIcon = By.xpath("//span[@class='schema-order__text']");
	By sortOrderDropDowOptionCheap = By.xpath("//div[@id='schema-order']//span[text()='Дешевые']");
	By firstPrice = By.xpath("//div[@id=\"schema-products\"]/div[1]//div[@class=\"schema-product__price\"]/a/span");
	By secondPrice = By.xpath("//div[@id=\"schema-products\"]/div[2]//div[@class=\"schema-product__line\"]//a/span");
	By firstCheckbox = By.xpath(String.format(xpathListOfCheckboxesToCompare, 1));
	By thirdCheckbox = By.xpath(String.format(xpathListOfCheckboxesToCompare, 4));
	By fifthsCheckbox = By.xpath(String.format(xpathListOfCheckboxesToCompare, 6));
	By sixthsCheckbox = By.xpath(String.format(xpathListOfCheckboxesToCompare, 7));
	By numberOfItemsToCompare = By.xpath("//div[@id='compare-button-container']//a[@class='compare-button__sub compare-button__sub_main']");
	

	public Radiocontrolair(WebDriver driver, WebDriverWait wait)
	{
		this.driver = driver;
		this.wait = wait;
	}
	
	
	public void verifyRadioControlModelsHeader()
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(radiocontrolAirModelHeader));
		String actualHeader = driver.findElement(radiocontrolAirModelHeader).getText();
		Assert.assertEquals(actualHeader, radioControlHeader);	
	}
	
	
	public void selectQuadrocopterCheckbox()
	{
		driver.findElement(quadrocopterCheckbox);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		WebElement quadrocopterCheckboxElement = driver.findElement(quadrocopterCheckbox);
		jse.executeScript("window.scrollBy(0," + quadrocopterCheckboxElement.getLocation().y + ")");
		wait.until(ExpectedConditions.elementToBeClickable(quadrocopterCheckbox)).click();
	}

	
	public void selectBodyTypePlasticCheckbox()
	{
		wait.until(ExpectedConditions.elementToBeClickable(quadrocopterBodyTypePlastic)).click();
	}

	
	public void selectBodyTypeMetalCheckbox() 
	{
		wait.until(ExpectedConditions.elementToBeClickable(quadrocopterBodyTypeMetal)).click();
	}
	
	
	public void specifyRangeOfAction(String range) 
	{
		wait.until(ExpectedConditions.elementToBeClickable(rangeOfAction)).sendKeys(range);
	}
	
	
	public void clickAdditionalParameters() 
	{
		wait.until(ExpectedConditions.elementToBeClickable(additionalParametersLink)).click();;
	}
	
	
	public void checkEngineTypeBeskollektornyi() 
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		WebElement engineTypeBeskollektornyiElement = driver.findElement(engineTypeBeskollektornyi);
		jse.executeScript("window.scrollBy(0," + engineTypeBeskollektornyiElement.getLocation().y + ")");
		wait.until(ExpectedConditions.elementToBeClickable(engineTypeBeskollektornyi)).click();
	}
	
	
	public void verifyNumberOfFoundItemsIsCorrect(String numberOfTheFoundItems) 
	{
		wait.until(ExpectedConditions.presenceOfElementLocated(numberOfItemsFound));
		String actualNumberOfFoundItems = driver.findElement(numberOfItemsFound).getText();
		Assert.assertEquals(actualNumberOfFoundItems, numberOfTheFoundItems);	
	}
	
	
	public void changeSortOrderCheapGoFirst() 
	{
		wait.until(ExpectedConditions.presenceOfElementLocated(sortOrderIcon)).click();
		wait.until(ExpectedConditions.elementToBeClickable(sortOrderDropDowOptionCheap)).click();	
	}
	
	
	public void verifySortOrderIsCorrectCheapGoFirst() 
	{
		wait.until(ExpectedConditions.elementToBeClickable(firstPrice));
		
		// Formatting the first product
	    String min_formatted = "";
		
	    for (int i = 0; i < driver.findElement(firstPrice).getText().length(); i++){
	      char c = driver.findElement(firstPrice).getText().charAt(i);    
	      String cs = String.valueOf(c);
	        if(c == ' ') {
	          break;
	        }
	      min_formatted = min_formatted.concat(cs);
	    }
	    double value_min = Double.parseDouble(min_formatted.replace(",","."));
	    
	    // String some = "50.00 р.";
	    // String array[]= some.split(" ");
	    // System.out.println(array[0]);

	    // Formatting the second product
		String max_formatted = "";
	    for (int i = 0; i < driver.findElement(secondPrice).getText().length(); i++){
	      char b = driver.findElement(secondPrice).getText().charAt(i);    
	      String cs1 = String.valueOf(b);
	        if(b == ' ') {
	          break;
	        }
	      max_formatted = max_formatted.concat(cs1);
	    }
	    double value_max = Double.parseDouble(max_formatted.replace(",","."));
		
	    // to verify the first product on the list is cheaper than the second product
	    Assert.assertTrue(value_min <= value_max);
	}
	
	
	public void checkFirstQuadroCheckbox ()
	{
		wait.until(ExpectedConditions.elementToBeClickable(firstCheckbox)).click();
	}
	
	
	public void checkThirdQuadroCheckbox ()
	{
		wait.until(ExpectedConditions.elementToBeClickable(thirdCheckbox)).click();
	}
	
	
	public void checkFifthsQuadroCheckbox ()
	{
		wait.until(ExpectedConditions.elementToBeClickable(fifthsCheckbox)).click();
	}
	
	
	public void checkSixthsQuadroCheckbox ()
	{
		wait.until(ExpectedConditions.elementToBeClickable(sixthsCheckbox)).click();
	}
	
	
	public void checkNumberOfItemsToCompare(String expectedText)
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(numberOfItemsToCompare));
		Assert.assertEquals(expectedText, driver.findElement(numberOfItemsToCompare).getText());
	}
	
	public void clickOnNumberOfItemsToCompare()
	{
		wait.until(ExpectedConditions.elementToBeClickable(numberOfItemsToCompare)).click();
	}
	
}

