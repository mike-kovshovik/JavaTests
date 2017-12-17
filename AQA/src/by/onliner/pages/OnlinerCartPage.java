package by.onliner.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class OnlinerCartPage {
	private static final Logger log = Logger.getLogger(OnlinerCartPage.class);
	private WebDriver driver;
	private WebDriverWait wait;

	private static final By plusIcon = By.cssSelector(
			".button-style.button-style_auxiliary.button-style_small.cart-product-add-box__button.cart-product-add-box__button_add");
	
	private static final By priceForOneItem = By
			.cssSelector(".cart-product__price-value.cart-product__price-value_primary>span");
	
	private static final By totalPrice = By.xpath("//div[@id='cart-main-container']//span/span[3]");
	private static final By totalNumberOfItemsLabel = By.xpath("//div[@id='cart-main-container']/div[3]//span/span");
	private static final By placeOrderLink = By.xpath("//a[contains(text(),'Оформить весь заказ')]");
	private static final By enterSitePopup = By.xpath("//div[@class='cart-popup__title' and contains(text(),'Войдите')]");

	public OnlinerCartPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}
	

	public OnlinerCartPage addPlusOneItem() {
		log.info("[Step] add one more item (press '+' icon)");
		Actions act = new Actions(driver);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cart-product-add-box")));
		WebElement cartDiv = driver.findElement(By.cssSelector(".cart-product-add-box"));
		act.moveToElement(cartDiv).perform();
		driver.findElement(plusIcon).click();
		return this;
	}
	

	public OnlinerCartPage verifyTotalNumberOfItemsAndPrice()
	{
		log.info("[Step] verification of the total number of the items");
		String itemPrice[] = driver.findElement(priceForOneItem).getText().split(" ");
		double itemPriceFormatted = Double.parseDouble(itemPrice[0].replace(",", "."));
		
		String totalPriceOfTwoItems[] = driver.findElement(totalPrice).getText().split(" ");
		double totalPriceFormatted = Double.parseDouble(totalPriceOfTwoItems[0].replace(",", "."));
		
		double numberOfItems = Double.parseDouble(driver.findElement(totalNumberOfItemsLabel).getText());

		Assert.assertTrue(totalPriceFormatted == itemPriceFormatted * numberOfItems);
		return this;
	}
	

	public OnlinerCartPage placeOrder() {
		log.info("[Step] press Place order");
		wait.until(ExpectedConditions.visibilityOfElementLocated(placeOrderLink));
		driver.findElement(placeOrderLink).click();
		return this;
	}
	

	public void assertLoginToSitePopupAppeared() {
		log.info("[Step] verify whether the log in pop up shows up");
		wait.until(ExpectedConditions.visibilityOfElementLocated(enterSitePopup));
		Assert.assertTrue(driver.findElement(enterSitePopup).getText().length() > 0);
	}

}
