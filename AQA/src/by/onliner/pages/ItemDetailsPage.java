package by.onliner.pages;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class ItemDetailsPage {
	WebDriver driver;
	WebDriverWait wait;

	private final String xpathVerifyInitialParametersAreCorrectPrototype = "//table[@class='product-specs__table']//tr[%s]/td[2]/span";
	private final By quadroTypeValue = By.xpath(String.format(xpathVerifyInitialParametersAreCorrectPrototype, "2"));
	private final By materialTypeValue = By.xpath(String.format(xpathVerifyInitialParametersAreCorrectPrototype, "3"));
	private final By engineTypeValue = By.xpath(String.format(xpathVerifyInitialParametersAreCorrectPrototype, "5"));
	
	//private final By cartLink = By.xpath("//a[@data-href='https://cart.onliner.by']");
	private final By cartLink = By.xpath("//a[contains(text(), 'В корзину')]");
	

	private final By itemInCartLink = By.xpath("//ul[@id='b-top-navigation-cart']//span");

	public ItemDetailsPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}

	public void verifyInitiallySelectedParametersAreCorrect(String[] listOfExpectedValues) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(quadroTypeValue));
		Set<String> setOfExpectedValues = new HashSet<String>(Arrays.asList(listOfExpectedValues));

		Assert.assertTrue(setOfExpectedValues.contains(driver.findElement(quadroTypeValue).getText()));
		Assert.assertTrue(setOfExpectedValues.contains(driver.findElement(materialTypeValue).getText()));
		Assert.assertTrue(setOfExpectedValues.contains(driver.findElement(engineTypeValue).getText()));
	}

	public void addToCart() {
		// Actions act = new Actions(driver);
		// WebElement cartDiv = driver.findElement(cartLinkPrototype);
		// act.moveToElement(cartDiv).perform();
		wait.until(ExpectedConditions.elementToBeClickable(cartLink)).click();
	}

	public void verifyNumerOfItemsInTheCartIsCorrect() {
		wait.until(ExpectedConditions.textToBePresentInElementLocated(itemInCartLink, "1 товар"));
		Assert.assertEquals("1 товар", driver.findElement(itemInCartLink).getText());
	}

	public void clickNumberOfItemsInCartLink() {
		wait.until(ExpectedConditions.textToBePresentInElementLocated(itemInCartLink, "1 товар"));
		driver.findElement(itemInCartLink).click();
	}

}
