package com.aqalab;
//import java.util.List;
//import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FirstTestCase {

	public static void main(String[] args) {
		
		String driverPath = "drivers/chromedriver";
		System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, driverPath);
		WebDriver driver = new ChromeDriver();
		driver.manage().window().fullscreen();

		driver.navigate().to("http://onliner.by");
		
		WebElement catalog = driver.findElement(By.linkText("Каталог"));
		catalog.click();
		
		WebElement krasota_sport = driver.findElement(By.xpath(".//*[@id='container']/div/div[2]/div/div/div[1]/ul/li[8]/span[2]/span"));
		krasota_sport.click();
		
		WebElement hobby = driver.findElement(By.xpath(".//*[@id='container']/div/div[2]/div/div/div[1]/div[2]/div/div[7]/div[1]/div/div[7]/div[1]"));
		hobby.click();
		
		driver.navigate().to("https://catalog.onliner.by/radiocontrolair");
		
		WebElement radiocontrolair = driver.findElement(By.className("schema-header__title"));
		// "//h1[contains(@class, 'schema-header__title')]"
		
		String radiocontrol_header = radiocontrolair.getText();
		String text_to_compare_with = "Радиоуправляемые авиамодели";
		
		/* assert the header is correct */
		if (radiocontrol_header.toLowerCase().contains(text_to_compare_with.toLowerCase())) {
			System.out.println("The header text is correct");
			}else {
				System.out.println("The header is: " + radiocontrol_header);
		}

		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,800)", "");
		
		// Waiting till the needed element appears on the page
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement additional_parameters = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Дополнительные параметры")));
				
		WebElement quadrocopter = driver.findElement(By.xpath(".//*[@id='schema-filter']/div[1]/div[6]/div[3]/ul/li[1]/label/span[1]/span"));
		quadrocopter.click();
		
		WebElement plastic_checkbox = driver.findElement(By.xpath(".//*[@id='schema-filter']/div[1]/div[8]/div[3]/ul/li[2]/label/span[1]/span"));
		plastic_checkbox.click();
				
		WebElement metal_checkbox = driver.findElement(By.xpath(".//*[@id='schema-filter']/div[1]/div[8]/div[3]/ul/li[3]/label/span[1]/span"));
		metal_checkbox.click();
	
		WebElement range = driver.findElement(By.xpath(".//*[@id='schema-filter']/div[1]/div[11]/div[3]/div/div[1]/input"));
		range.sendKeys("100");
		
		additional_parameters.click();
		
		JavascriptExecutor jse1 = (JavascriptExecutor)driver;
		jse1.executeScript("window.scrollBy(0,600)", "");
		
		WebElement engine_type_beskollektornyi = driver.findElement(By.xpath(".//*[@id='schema-filter']/div[2]/div[2]/div/div[3]/div[3]/ul/li[2]/label/span[1]/span"));
		engine_type_beskollektornyi.click();
		
		//driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement number_of_items_found = driver.findElement(By.xpath(".//*[@id='schema-filter-button']/div/div/div[1]"));
		String found_items = number_of_items_found.getText();
		String items_count_expected = "Найдено 20 товаров";
		

		
		if (found_items.toLowerCase().contains(items_count_expected.toLowerCase())) {
			System.out.println("The number of the items found is equal to " + found_items + " - expected value");
			}else {
				System.out.println(found_items + " - which is not what was expected");
		}
		
		JavascriptExecutor jse2 = (JavascriptExecutor)driver;
		jse2.executeScript("window.scrollBy(0,-1400)", "");
		
		
		WebElement sort_order = driver.findElement(By.xpath(".//*[@id='schema-order']/a"));
		sort_order.click();
		
		WebElement sort_order_cheap_first = driver.findElement(By.xpath(".//*[@id='schema-order']/div/div/div[2]/span"));
		sort_order_cheap_first.click();
		

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebElement first_price = driver.findElement(By.xpath(".//*[@id='schema-products']/div[1]/div/div[2]/div[1]/div/div/div[1]/a/span"));
		WebElement second_price = driver.findElement(By.xpath(".//*[@id='schema-products']/div[2]/div/div[2]/div[1]/div/div/div[1]/a/span"));
		

	    String first_price_text = first_price.getText();
	    String second_price_text = second_price.getText();
	    
	    String min_formatted = "";
	    String max_formatted = "";
	    
	    // Formatting the first product
	    for(int i = 0; i < first_price_text.length(); i++){
	      char c = first_price_text.charAt(i);    
	      String cs = String.valueOf(c);
	        if(c == ' ') {
	          break;
	        }
	      min_formatted = min_formatted.concat(cs);
	    }
	    double value_min = Double.parseDouble(min_formatted.replace(",","."));
		
	    // Formatting the second product
	    for(int i = 0; i < first_price_text.length(); i++){
	      char b = second_price_text.charAt(i);    
	      String cs1 = String.valueOf(b);
	        if(b == ' ') {
	          break;
	        }
	      max_formatted = max_formatted.concat(cs1);
	    }
	    double value_max = Double.parseDouble(max_formatted.replace(",","."));
	    
	    //Integer min = Integer.valueOf(min_formatted);
	    //Integer max = Integer.valueOf(max_formatted);
	    
		if (value_min <= value_max) {
			System.out.println("The first product is cheaper than the second one. I am satisfied with the result");
		}else {
			System.out.println("The first product is NOT cheaper that the second one");
		}
		
		WebElement checkbox1 = driver.findElement(By.xpath(".//*[@id='schema-products']/div[1]/div/div[1]/div[1]/div/label/span/span"));
		checkbox1.click();

		WebElement checkbox3 = driver.findElement(By.xpath(".//*[@id='schema-products']/div[4]/div/div[1]/div[1]/div/label/span/span"));
		checkbox3.click();
		
		WebElement checkbox5 = driver.findElement(By.xpath(".//*[@id='schema-products']/div[6]/div/div[1]/div[1]/div/label/span/span"));
		checkbox5.click();
		
		WebElement checkbox6 = driver.findElement(By.xpath(".//*[@id='schema-products']/div[7]/div/div[1]/div[1]/div/label/span/span"));
		checkbox6.click();
			
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String bottom_label = "4 товара";
		
		WebElement goods_to_compare = driver.findElement(By.xpath(".//*[@id='compare-button-container']/div/div[1]/div/div/div[1]/a[2]/span"));
		String goods_to_compare_text_value = goods_to_compare.getText();
		if (goods_to_compare_text_value.toLowerCase().contains(bottom_label.toLowerCase())) {
			System.out.println("4 items to compare appeared at the bottom of the page. Test passed");
		}else {
			System.out.println(goods_to_compare_text_value + "Fucking shit - the label is missing or is not correct");
		}
			
		goods_to_compare.click();
		
		
		WebElement third_item = driver.findElement(By.xpath(".//*[@id='product-table']/tbody[2]/tr/th[5]/div/div/a/span"));
		third_item.click();
		
		JavascriptExecutor jse3 = (JavascriptExecutor)driver;
		jse3.executeScript("window.scrollBy(0,800)", "");
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// To verify the item type is "квадрокоптер"
		WebElement quadro_check = driver.findElement(By.xpath(".//*[@id='specs']/div[2]/div/table/tbody[1]/tr[2]/td[2]/span"));
		if (quadro_check.getText().toLowerCase().contains("квадрокоптер".toLowerCase())) {
			System.out.println("Type = Квадрокоптер. Test passed");
		}else {
			System.out.println("Type = " + quadro_check.getText() + " Test failed");
		}
		
		// To verify the item type is made of "пластик или металл"
		WebElement material_check = driver.findElement(By.xpath(".//*[@id='specs']/div[2]/div/table/tbody[1]/tr[3]/td[2]/span"));	
		if (material_check.getText().toLowerCase().contains("пластик".toLowerCase()) || material_check.getText().toLowerCase().contains("металл".toLowerCase())) {
			System.out.println("Material = пластик или металл. Test passed");
		}else {
			System.out.println("Material = " + material_check.getText() + " Test failed");
		}
				
		// To verify the item engine type is "бесколлекторный"
		WebElement engine_check = driver.findElement(By.xpath(".//*[@id='specs']/div[2]/div/table/tbody[1]/tr[5]/td[2]/span"));
		if (engine_check.getText().toLowerCase().contentEquals("бесколлекторный".toLowerCase())) {
			System.out.println("Engine type = бесколлекторный. Test passed");
		}else {
			System.out.println("Engine Type = " + engine_check.getText() + ". Test failed");
		}
		
		// To scroll the page up to the very top so that the cart is displayed and can be clicked
		//JavascriptExecutor jse4 = (JavascriptExecutor)driver;
		
		jse.executeScript("window.scrollBy(0,-800)", "");
		
		WebElement cart = driver.findElement(By.xpath(".//*[@id='container']/div/div[2]/div/div/div[2]/div[1]/main/aside/div[1]/div/div/div/div[1]/a[2]"));
		cart.click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebElement item_in_cart = driver.findElement(By.linkText("1 товар"));
		if (item_in_cart.getText().contentEquals("1 товар")) {
			System.out.println("The item was added into the cart. Test passed");
		}
		
	    // opening the cart to view the products added there
		item_in_cart.click();
		
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//hower the mouse over the div with the item description
		Actions act = new Actions(driver);
		WebElement cart_div = driver.findElement(By.cssSelector(".cart-product-add-box"));
		act.moveToElement(cart_div).perform();
			
		WebElement plus_icon = driver.findElement(By.cssSelector(".button-style.button-style_auxiliary.button-style_small.cart-product-add-box__button.cart-product-add-box__button_add"));
		plus_icon.click();	
		
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
		System.out.println(value_1_item);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		// Locating the element indicating total price
		WebElement total_price = driver.findElement(By.xpath(".//*[@id='cart-main-container']/div[3]/div[2]/div[1]/span/span[3]"));
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
		System.out.println(total_value);
		
		WebElement number_of_items_total = driver.findElement(By.xpath(".//*[@id='cart-main-container']/div[3]/div[2]/div[1]/span/span[1]"));
		String number_of_items_total_text = number_of_items_total.getText();
		
		if (value_1_item == total_value/2) {
			System.out.println("Total value is correct: " + total_value);			
		}else {
			System.out.println("Total value is incorrect: " + total_value);			
		}
		
		Integer number_of_items_integer = Integer.valueOf(number_of_items_total_text);
					
		if (number_of_items_integer.equals(2)) {
			System.out.println("Number of items is correct: " + number_of_items_integer);			
		}else {
			System.out.println("Number of items is incorrect: " + number_of_items_integer);			
		}
		
		
		WebElement confirm_order = driver.findElement(By.xpath(".//*[@id='cart-main-container']/div[3]/div[2]/a"));
		confirm_order.click();
		
		WebElement login_check = driver.findElement(By.xpath("html/body/div[6]/div/div[1]/div[1]"));
		System.out.println(login_check.getText());
		
		if (login_check.getText().contentEquals("Войдите на сайт")) {
			System.out.println("Popup showed up. Test passed");
		} else {
			System.out.println("Test passed");
		}
		
		
		}
		
	    
		//driver.close();
	    
		// TODO Auto-generated method stub

	}



//((JavascriptExecutor) driver)
//.executeScript("window.scrollTo(0, document.body.scrollHeight)");

