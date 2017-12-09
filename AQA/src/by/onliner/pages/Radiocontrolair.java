package by.onliner.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class Radiocontrolair {
	private WebDriver driver;
	private WebDriverWait wait;

	private final String xpathForQuadroParameters = "//div[@id='schema-filter']//span[text()='%s']";
	private final String xpathListOfCheckboxesToCompare = "//div[@id='schema-products']/div[%s]//span[@class='i-checkbox__faux']";
	private final By radiocontrolAirModelHeader = By.className("schema-header__title");
	private final By quadrocopterCheckbox = By.xpath(String.format(xpathForQuadroParameters, "квадрокоптер"));
	private final By quadrocopterBodyTypePlastic = By.xpath(String.format(xpathForQuadroParameters, "пластик"));
	private final By quadrocopterBodyTypeMetal = By.xpath(String.format(xpathForQuadroParameters, "металл"));
	private final By rangeOfAction = By.xpath("//input[@placeholder='6']");
	private final By additionalParametersLink = By.linkText("Дополнительные параметры");
	private final By engineTypeBeskollektornyi = By.xpath(String.format(xpathForQuadroParameters, "бесколлекторный"));
	private final By numberOfItemsFound = By
			.xpath("//div[@id='schema-filter-button']//span[starts-with(text(), 'Найдено 27')]");
	private final By sortOrderIcon = By.xpath("//span[@class='schema-order__text']");
	private final By sortOrderDropDowOptionCheap = By.xpath("//div[@id='schema-order']//span[text()='Дешевые']");
	private final By firstPrice = By
			.xpath("//div[@id=\"schema-products\"]/div[1]//div[@class=\"schema-product__price\"]/a/span");
	private final By secondPrice = By
			.xpath("//div[@id=\"schema-products\"]/div[2]//div[@class=\"schema-product__line\"]//a/span");
	private final By numberOfItemsToCompare = By
			.xpath("//div[@id='compare-button-container']//a[@class='compare-button__sub compare-button__sub_main']");

	public Radiocontrolair(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}

	public void verifyIsPageHeaderEqualTo(String headerText) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(radiocontrolAirModelHeader));
		String actualHeader = driver.findElement(radiocontrolAirModelHeader).getText();
		Assert.assertEquals(actualHeader, headerText);
	}

	public Radiocontrolair selectQuadrocopterCheckbox() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebElement quadrocopterCheckboxElement = driver.findElement(quadrocopterCheckbox);
		jse.executeScript("window.scrollBy(0," + quadrocopterCheckboxElement.getLocation().y + ")");
		wait.until(ExpectedConditions.elementToBeClickable(quadrocopterCheckbox)).click();
		return this;
	}

	public Radiocontrolair selectBodyTypePlasticCheckbox() {
		wait.until(ExpectedConditions.elementToBeClickable(quadrocopterBodyTypePlastic)).click();
		return this;
	}

	public Radiocontrolair selectBodyTypeMetalCheckbox() {
		wait.until(ExpectedConditions.elementToBeClickable(quadrocopterBodyTypeMetal)).click();
		return this;
	}

	public Radiocontrolair specifyRangeOfAction(String range) {
		wait.until(ExpectedConditions.elementToBeClickable(rangeOfAction)).sendKeys(range);
		return this;
	}

	public Radiocontrolair clickAdditionalParameters() {
		wait.until(ExpectedConditions.elementToBeClickable(additionalParametersLink)).click();
		return this;
	}

	public Radiocontrolair checkEngineTypeBeskollektornyi() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebElement engineTypeBeskollektornyiElement = driver.findElement(engineTypeBeskollektornyi);
		jse.executeScript("window.scrollBy(0," + engineTypeBeskollektornyiElement.getLocation().y + ")");
		wait.until(ExpectedConditions.elementToBeClickable(engineTypeBeskollektornyi)).click();
		return this;
	}

	public void verifyNumberOfFoundItemsIsCorrect(String numberOfTheFoundItems) {
		wait.until(ExpectedConditions.presenceOfElementLocated(numberOfItemsFound));
		String actualNumberOfFoundItems = driver.findElement(numberOfItemsFound).getText();
		Assert.assertEquals(actualNumberOfFoundItems, numberOfTheFoundItems);
	}

	public void changeSortOrderCheapGoFirst() {
		wait.until(ExpectedConditions.presenceOfElementLocated(sortOrderIcon)).click();
		wait.until(ExpectedConditions.elementToBeClickable(sortOrderDropDowOptionCheap)).click();
	}

	public void verifyIsPriceSortedDesc() {
		wait.until(ExpectedConditions.elementToBeClickable(firstPrice));

		String arrayFirstProduct[] = driver.findElement(firstPrice).getText().split(" ");
		double value_min = Double.parseDouble(arrayFirstProduct[0].replace(",", "."));

		String arraySecondProduct[] = driver.findElement(secondPrice).getText().split(" ");
		double value_max = Double.parseDouble(arraySecondProduct[0].replace(",", "."));

		Assert.assertTrue(value_min <= value_max);
	}

	public void selectItemsToCompare(int[] itemIndexes) {
		for (int i = 0; i < itemIndexes.length; i++) {
			wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(String.format(xpathListOfCheckboxesToCompare, itemIndexes[i]))))
					.click();
		}
	}

	public void checkNumberOfItemsToCompare(String expectedText) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(numberOfItemsToCompare));
		Assert.assertEquals(expectedText, driver.findElement(numberOfItemsToCompare).getText());
	}

	public void clickOnNumberOfItemsToCompare() {
		wait.until(ExpectedConditions.elementToBeClickable(numberOfItemsToCompare)).click();
	}

}
