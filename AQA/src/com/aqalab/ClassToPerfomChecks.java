package com.aqalab;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import junit.framework.Assert;


public class ClassToPerfomChecks {
	
	public static void main(String[] args) throws InterruptedException {
	
		String driverPath = "drivers/chromedriver";
		System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, driverPath);
		WebDriver driver = new ChromeDriver();
		driver.manage().window().fullscreen();
		
		
		// 1. Opens www.onliner.by
		driver.navigate().to("http://onliner.by");
		
		
		// 2. Navigate to catalog
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement catalogLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Каталог")));
		catalogLink.click();
		
		
		// 3. Select beauty and sport section
		WebElement beauty_and_sport_link = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='container']//span[contains(text(), 'Красота')]")));
		beauty_and_sport_link.click();
	
		
		// 4. Select Hobby from the left-side menu
		WebElement hobby_link = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='container']//div[contains(text(), 'Хобби')]")));
		hobby_link.click();
		
		
		// 5. Select radio_control models
		WebElement radio_control_models_link = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='container']//span[contains(text(), 'Радиоуправляемые авиамодели')]")));
		radio_control_models_link.click();
	
		
		// 6. Assert Radio header
		WebElement radiocontrol_air_model_header = driver.findElement(By.className("schema-header__title"));
		Assert.assertEquals("Радиоуправляемые авиамодели", radiocontrol_air_model_header.getText());
		
		
		// 7. Select Type = Quadcopter
		String xpath = "//div[@id='schema-filter']//span[text()='%s']";
		WebElement quadcopter_checkbox = driver.findElement(By.xpath(String.format(xpath, "квадрокоптер")));
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0," + quadcopter_checkbox.getLocation().y + ")"); // scrolling down the page by 1459 pixels until Additional Parameters link text is displayed
		
		// jse.executeScript("window.scrollBy(0,1459)", ""); // scrolling down the page by 1459 pixels until Additional Parameters link text is displayed
		
		// quadcopter_parameter_checkbox
		
		WebElement quadcopter_checkbox1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(xpath, "квадрокоптер"))));
		//WebElement quadcopter_checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='schema-filter']//span[text()='квадрокоптер']")));
		quadcopter_checkbox1.click();
		

		// 8. Select Body = Plastic and Metall
		WebElement plastic_checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(xpath, "пластик"))));
		//WebElement plastic_checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='schema-filter']//span[text()='пластик']")));
		plastic_checkbox.click();		

		WebElement metal_checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(xpath, "металл"))));
		//WebElement metal_checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='schema-filter']//span[text()='металл']")));
		metal_checkbox.click();		

		
		// 9. Specify range
		//WebElement range_checkbox = driver.findElement(By.xpath("//div[@class='schema-filter__group']/div[@class='schema-filter-control schema-filter-control_input']/input[@placeholder='6']"));
		WebElement range_checkbox = driver.findElement(By.xpath("//input[@placeholder='6']"));
		range_checkbox.sendKeys("100");
		
		
		// 10. Open additional parameters
		WebElement additional_parameters_button = driver.findElement(By.linkText("Дополнительные параметры"));
		additional_parameters_button.click();
		//System.out.println(additional_parameters_button.getLocation());
		
		
		jse.executeScript("window.scrollBy(0,260)", ""); // scrolling down the page by 260 pixels until Beskollektornyi checkbox is visible
		
		
		// 11. Select engine type = Beskollektornyi
		WebElement engine_type_beskollektornyi_checkbox = driver.findElement(By.xpath(String.format(xpath, "бесколлекторный")));
		engine_type_beskollektornyi_checkbox.click();
		//System.out.println(engine_type_beskollektornyi_checkbox.getLocation());
		
		
		// 12. Verify 20 mathes were found
		WebElement number_of_items_found_link = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='schema-filter-button']//span[starts-with(text(), 'Найдено 20')]")));
		Assert.assertEquals("Найдено 20 товаров", number_of_items_found_link.getText());
		
		
		// 13. Change sort order. Cheap should go first
		jse.executeScript("window.scrollBy(0,-1719)", ""); // scrolling the page to the top
		
		WebElement sort_order_icon = driver.findElement(By.xpath("//span[@class='schema-order__text']"));
		sort_order_icon.click();
	
		WebElement cheap_sort_item_from_dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='schema-order']//span[text()='Дешевые']")));
		cheap_sort_item_from_dropdown.click();
		
        // 14. Проверить что цена товара для результата < цены товара для результата 2 в списке			
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebElement first_price = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id=\"schema-products\"]/div[1]//div[@class=\"schema-product__price\"]/a/span")));
		WebElement second_price = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id=\"schema-products\"]/div[2]//div[@class=\"schema-product__line\"]//a/span")));

		
		
		// Formatting the first product
	    String min_formatted = "";
		
	    for (int i = 0; i < first_price.getText().length(); i++){
	      char c = first_price.getText().charAt(i);    
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
	    for (int i = 0; i < second_price.getText().length(); i++){
	      char b = second_price.getText().charAt(i);    
	      String cs1 = String.valueOf(b);
	        if(b == ' ') {
	          break;
	        }
	      max_formatted = max_formatted.concat(cs1);
	    }
	    double value_max = Double.parseDouble(max_formatted.replace(",","."));
		
	    // to verify the first product on the list is cheaper than the second product
	    Assert.assertTrue(value_min <= value_max);
		
	    
	    
	    // 15.Пометить 1,3,5 и 6 товары для добавления в сравнение
	    String xpathListOfCheckboxesToCompare = "//div[@id='schema-products']/div[%s]//span[@class='i-checkbox__faux']";
	   
	    WebElement firstQuadroCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(xpathListOfCheckboxesToCompare, 1))));
	    // WebElement firstQuadroCheckbox = driver.findElement(By.xpath(String.format(xpathListOfCheckboxesToCompare, 1)));
	    firstQuadroCheckbox.click();
	    
	    // WebElement thirdQuadroCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(xpathListOfCheckboxesToCompare, 4))));
	    WebElement thirdQuadroCheckbox = driver.findElement(By.xpath(String.format(xpathListOfCheckboxesToCompare, 4)));
	    thirdQuadroCheckbox.click();
	    
	    WebElement fifthQuadroCheckbox = driver.findElement(By.xpath(String.format(xpathListOfCheckboxesToCompare, 6)));
	    fifthQuadroCheckbox.click();	    
	    
	    WebElement sixthsQuadroCheckbox = driver.findElement(By.xpath(String.format(xpathListOfCheckboxesToCompare, 7)));
	    sixthsQuadroCheckbox.click();	    
	    
	    
	    // 16.Проверить, что появился стикер с "4 товара в сравнении"
	    WebElement FourItemsToCompareLabel = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='compare-button-container']//a[@class='compare-button__sub compare-button__sub_main']")));
	    Assert.assertEquals("4 товара в сравнении", FourItemsToCompareLabel.getText());

	    
	    // 17.Перейти в сравнение (кликнуть на стикер)
	    FourItemsToCompareLabel.click();
	    
	    
	    // 18.Открыть товар 3
	    WebElement thirdProductLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tr[@class='product-table__row product-table__row_header product-table__row_top']/th[5]")));
	    thirdProductLink.click();
	    
	    
	    // 19.Проверить что присутствуют изначально выбранные параметры (квадрокоптер,пластик или металл,бесколлекторный)
	    WebElement quadroType = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='product-specs__table']//tr[2]/td")));
	    WebElement quadroTypeValue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='product-specs__table']//tr[2]/td[2]")));
	    Assert.assertEquals("Типквадрокоптер", quadroType.getText()+quadroTypeValue.getText());
	  
	    WebElement materialType = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='product-specs__table']//tr[3]/td")));
	    WebElement materialTypeValue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='product-specs__table']//tr[3]/td[2]")));	    
	    Assert.assertTrue((materialType.getText().contentEquals("Материал") && materialTypeValue.getText().contentEquals("пластик")) || 
	    		(materialType.getText().contentEquals("Материал") && materialTypeValue.getText().contentEquals("металл")));
	    
	    WebElement engineType = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='product-specs__table']//tr[5]/td")));
	    WebElement engineTypeValue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='product-specs__table']//tr[5]/td[2]")));
	    Assert.assertEquals("Тип электродвигателябесколлекторный", engineType.getText()+engineTypeValue.getText());
	    
	    
	    // 20.Добавить в корзину
	    WebElement cartLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-href='https://cart.onliner.by']")));
	    cartLink.click();
	    

	    // 21.Проверить, что 1 товар в корзине (нотификация в хедере)   
	    wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//ul[@id='b-top-navigation-cart']//span"), "1 товар"));
	    WebElement itemInCartLink = driver.findElement(By.xpath("//ul[@id='b-top-navigation-cart']//span"));
	    
	    //WebElement itemInCartLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@id='b-top-navigation-cart']//span[contains(text(), '1 товар')]")));	
	    
	    Assert.assertEquals("1 товар", itemInCartLink.getText());
	   
	    
	    // 22.Перейти в корзину (кликнуть на линку в хедере)
	    itemInCartLink.click();
	    
	    
	    // 23.Добавить +1 товар (нажать +)
	    Actions act = new Actions(driver);
	    WebElement cart_div = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cart-product-add-box")));
		act.moveToElement(cart_div).perform();
			
		WebElement plusIcon = driver.findElement(By.cssSelector(".button-style.button-style_auxiliary.button-style_small.cart-product-add-box__button.cart-product-add-box__button_add"));
		plusIcon.click();	
		
		
		// 24.Проверить, что в Итого стало 2 товара и цена за 2 товара посчитана верно
		WebElement price_for_one_item = driver.findElement(By.cssSelector(".cart-product__price-value.cart-product__price-value_primary>span"));
		String price_for_one_item_text = price_for_one_item.getText();
		
		String price_one_item_formatted = "";
	    
	    // Formatting the price for 1 item
	    for(int i = 0; i < price_for_one_item_text.length(); i++){
	      char c = price_for_one_item_text.charAt(i);    
	      String qw = String.valueOf(c);
	        if(c == ' ') {
	          break;
	        }
	        price_one_item_formatted = price_one_item_formatted.concat(qw);
	    }
	    double value_1_item = Double.parseDouble(price_one_item_formatted.replace(",","."));

	    // Locating the element indicating total price
	    WebElement total_price = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='cart-main-container']//span/span[3]")));
	 	String total_price_text = total_price.getText();
	 	String total_price_formatted = "";
	 	    
	 	// Formatting the value of total price
	 	for(int i = 0; i < total_price_text.length(); i++){
	 	   char c = total_price_text.charAt(i);    
	 	   String sd = String.valueOf(c);
	 	     if(c == ' ') {
	 	       break;
	 	     }
	 	     total_price_formatted = total_price_formatted.concat(sd);
	 	 }
	 	    double total_value = Double.parseDouble(total_price_formatted.replace(",","."));
	 	    
        // to verify total number of items equals 2
	 	WebElement number_of_items_total = driver.findElement(By.xpath("//*[@id=\"cart-main-container\"]/div[3]//span/span"));
        Assert.assertEquals("2", number_of_items_total.getText());
        
        // to verify total amount figure is correct
        Assert.assertTrue(value_1_item == total_value/2);
		
		 
        // 25.Нажать оформить заказ
        WebElement placeOrderLink = driver.findElement(By.xpath("//a[contains(text(),'Оформить весь заказ')]"));
        placeOrderLink.click();
        
        
        // 26.Проверить что логин попап появился
        WebElement enterSitePopup = driver.findElement(By.xpath("//div[@class='cart-popup__title' and contains(text(),'Войдите')]"));
        Assert.assertTrue(enterSitePopup.getText().length() > 0);
        
		driver.quit();
		
	}
}
