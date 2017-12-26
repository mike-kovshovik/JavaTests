package by.onliner.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class QuadrokoptersPage {

	private static final Logger log = Logger.getLogger(QuadrokoptersPage.class);

	private WebDriver driver;
	private WebDriverWait wait;

	private static final String xpathForQuadroParameters = "//div[@id='schema-filter']//span[text()='%s']";
	private static final String xpathListOfCheckboxesToCompare = "//div[@id='schema-products']/div[%s]//span[@class='i-checkbox__faux']";
	private static final By radiocontrolAirModelHeader = By.className("schema-header__title");
	private static final By rangeOfAction = By.xpath("//input[@placeholder='6']");
	private static final By additionalParametersLink = By.linkText("Дополнительные параметры");
	private static final By numberOfItemsFound = By.xpath("//div[@id='schema-filter-button']//span[starts-with(text(), 'Найдено 27')]");
	private static final By sortOrderIcon = By.xpath("//span[@class='schema-order__text']");
	private static final By sortOrderDropDowOptionCheap = By.xpath("//div[@id='schema-order']//span[text()='Дешевые']");
	private static final By firstPrice = By.xpath("//div[@id=\"schema-products\"]/div[1]//div[@class=\"schema-product__price\"]/a/span");
	private static final By secondPrice = By.xpath("//div[@id=\"schema-products\"]/div[2]//div[@class=\"schema-product__line\"]//a/span");
	private static final By numberOfItemsToCompare = By.xpath("//div[@id='compare-button-container']//a[@class='compare-button__sub compare-button__sub_main']");

	public QuadrokoptersPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}

	public QuadrokoptersPage verifyIsPageHeaderEqualTo(String headerText) {
		log.info("[Step] verify page header");
		wait.until(ExpectedConditions.visibilityOfElementLocated(radiocontrolAirModelHeader));
		String actualHeader = driver.findElement(radiocontrolAirModelHeader).getText();
		Assert.assertEquals(actualHeader, headerText);
		return this;
	}

	public QuadrokoptersPage setParameter(String parameterName)
	{
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		driver.findElement(By.xpath(String.format(xpathForQuadroParameters, parameterName)));
		jse.executeScript("window.scrollBy(0,"
				+ driver.findElement(By.xpath(String.format(xpathForQuadroParameters, parameterName))).getLocation().y
				+ ")");
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(String.format(xpathForQuadroParameters, parameterName))))).click();
		return this;
	}


	public QuadrokoptersPage specifyRangeOfAction(String range) {
		wait.until(ExpectedConditions.elementToBeClickable(rangeOfAction)).sendKeys(range);
		return this;
	}

	public QuadrokoptersPage clickAdditionalParameters() {
		wait.until(ExpectedConditions.elementToBeClickable(additionalParametersLink)).click();
		return this;
	}

	public QuadrokoptersPage verifyNumberOfFoundItemsIsCorrect(String numberOfTheFoundItems) {
		wait.until(ExpectedConditions.presenceOfElementLocated(numberOfItemsFound));
		String actualNumberOfFoundItems = driver.findElement(numberOfItemsFound).getText();
		Assert.assertEquals(actualNumberOfFoundItems, numberOfTheFoundItems);
		return this;
	}

	public QuadrokoptersPage changeSortOrderCheapGoFirst() {
		wait.until(ExpectedConditions.presenceOfElementLocated(sortOrderIcon)).click();
		wait.until(ExpectedConditions.elementToBeClickable(sortOrderDropDowOptionCheap)).click();
		return this;
	}

	public QuadrokoptersPage verifyIsPriceSortedDesc() {
		wait.until(ExpectedConditions.elementToBeClickable(firstPrice));

		String arrayFirstProduct[] = driver.findElement(firstPrice).getText().split(" ");
		double value_min = Double.parseDouble(arrayFirstProduct[0].replace(",", "."));

		String arraySecondProduct[] = driver.findElement(secondPrice).getText().split(" ");
		double value_max = Double.parseDouble(arraySecondProduct[0].replace(",", "."));

		Assert.assertTrue(value_min <= value_max);
		return this;
	}

	public QuadrokoptersPage selectItemsToCompare(int[] itemIndexes) {
		for (int i = 0; i < itemIndexes.length; i++) {
			wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(String.format(xpathListOfCheckboxesToCompare, itemIndexes[i]))))
					.click();
		}
		return this;
	}

	public QuadrokoptersPage checkNumberOfItemsToCompare(String expectedText) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(numberOfItemsToCompare));
		Assert.assertEquals(expectedText, driver.findElement(numberOfItemsToCompare).getText());
		return this;
	}

	public CompareItemsPage clickOnNumberOfItemsToCompare() {
		wait.until(ExpectedConditions.elementToBeClickable(numberOfItemsToCompare)).click();
		return new CompareItemsPage(driver, wait);
	}

}
