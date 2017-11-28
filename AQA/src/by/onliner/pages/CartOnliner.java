package by.onliner.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;


public class CartOnliner
{
	WebDriver driver;
	WebDriverWait wait;
	
	By plusIcon = By.cssSelector(".button-style.button-style_auxiliary.button-style_small.cart-product-add-box__button.cart-product-add-box__button_add");
	By priceForOneItem = By.cssSelector(".cart-product__price-value.cart-product__price-value_primary>span");
	By totalPrice = By.xpath("//div[@id='cart-main-container']//span/span[3]");
	By totalNumberOfItemsLabel = By.xpath("//div[@id='cart-main-container']/div[3]//span/span");
	By placeOrderLink = By.xpath("//a[contains(text(),'Оформить весь заказ')]");
	By enterSitePopup = By.xpath("//div[@class='cart-popup__title' and contains(text(),'Войдите')]");
	
	
	
	public CartOnliner(WebDriver driver, WebDriverWait wait)
	{
		this.driver=driver;
		this.wait=wait;
	}
	
	public void addPlusOneItem()
	{
	    Actions act = new Actions(driver);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cart-product-add-box")));
	    WebElement cartDiv = driver.findElement(By.cssSelector(".cart-product-add-box"));
	    act.moveToElement(cartDiv).perform();	
		driver.findElement(plusIcon).click();
	}
	
	public void verifyTotalNumberOfItemsAndPrice() 
	{
		String priceForOneItemText = driver.findElement(priceForOneItem).getText();
		String priceOneItemFormatted = "";
	    
	    // Formatting the price for 1 item
	    for(int i = 0; i < priceForOneItemText.length(); i++){
	      char c = priceForOneItemText.charAt(i);    
	      String qw = String.valueOf(c);
	        if(c == ' ') {
	          break;
	        }
	        priceOneItemFormatted = priceOneItemFormatted.concat(qw);
	    }
	    double priceOfOneItem = Double.parseDouble(priceOneItemFormatted.replace(",","."));

	    // Locating the element indicating total price
	    wait.until(ExpectedConditions.visibilityOfElementLocated(totalPrice));
	 	String totalPriceText = driver.findElement(totalPrice).getText();
	 	String totalPriceFormatted = "";
	 	    
	 	// Formatting the value of total price
	 	for(int i = 0; i < totalPriceText.length(); i++){
	 	   char c = totalPriceText.charAt(i);    
	 	   String sd = String.valueOf(c);
	 	     if(c == ' ') {
	 	       break;
	 	     }
	 	    totalPriceFormatted = totalPriceFormatted.concat(sd);
	 	 }
	 	    double totalPriceValue = Double.parseDouble(totalPriceFormatted.replace(",","."));
	 	    
	 	// to verify total number of items equals 2
	    Assert.assertEquals("2", driver.findElement(totalNumberOfItemsLabel).getText());
	        
	    // to verify total amount figure is correct
	    Assert.assertTrue(priceOfOneItem == totalPriceValue/2);
	}
	
	
	public void placeOrder()
	{
	    wait.until(ExpectedConditions.visibilityOfElementLocated(placeOrderLink));
	    driver.findElement(placeOrderLink).click();
	}
	
	
	public void assertLoginToSitePopupAppeared()
	{
	    wait.until(ExpectedConditions.visibilityOfElementLocated(enterSitePopup));
	    Assert.assertTrue(driver.findElement(enterSitePopup).getText().length() > 0);
	}
	
	
}
