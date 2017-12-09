package by.onliner.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class CartOnliner {
	private WebDriver driver;
	private WebDriverWait wait;

	private final By plusIcon = By.cssSelector(
			".button-style.button-style_auxiliary.button-style_small.cart-product-add-box__button.cart-product-add-box__button_add");
	
	private final By priceForOneItem = By
			.cssSelector(".cart-product__price-value.cart-product__price-value_primary>span");
	
	private final By totalPrice = By.xpath("//div[@id='cart-main-container']//span/span[3]");
	private final By totalNumberOfItemsLabel = By.xpath("//div[@id='cart-main-container']/div[3]//span/span");
	private final By placeOrderLink = By.xpath("//a[contains(text(),'Оформить весь заказ')]");
	private final By enterSitePopup = By.xpath("//div[@class='cart-popup__title' and contains(text(),'Войдите')]");

	public CartOnliner(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}
	

	public void addPlusOneItem() {
		Actions act = new Actions(driver);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cart-product-add-box")));
		WebElement cartDiv = driver.findElement(By.cssSelector(".cart-product-add-box"));
		act.moveToElement(cartDiv).perform();
		driver.findElement(plusIcon).click();
	}
	

	public void verifyTotalNumberOfItemsAndPrice()
	{
		String itemPrice[] = driver.findElement(priceForOneItem).getText().split(" ");
		double itemPriceFormatted = Double.parseDouble(itemPrice[0].replace(",", "."));
		
		String totalPriceOfTwoItems[] = driver.findElement(totalPrice).getText().split(" ");
		double totalPriceFormatted = Double.parseDouble(totalPriceOfTwoItems[0].replace(",", "."));
		
		double numberOfItems = Double.parseDouble(driver.findElement(totalNumberOfItemsLabel).getText());

		Assert.assertTrue(totalPriceFormatted == itemPriceFormatted * numberOfItems);
	}
	

	public void placeOrder() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(placeOrderLink));
		driver.findElement(placeOrderLink).click();
	}
	

	public void assertLoginToSitePopupAppeared() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(enterSitePopup));
		Assert.assertTrue(driver.findElement(enterSitePopup).getText().length() > 0);
	}

}
