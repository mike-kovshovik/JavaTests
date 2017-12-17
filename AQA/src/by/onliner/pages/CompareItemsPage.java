package by.onliner.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CompareItemsPage {

	private static final Logger log = Logger.getLogger(CompareItemsPage.class);
	private WebDriver driver;
	private WebDriverWait wait;

	public static final By thirdItemInComparisonTable = By
			.xpath("//tr[@class='product-table__row product-table__row_header product-table__row_top']/th[5]//a");

	public CompareItemsPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}

	public ProductDetailsPage selectItemFromComparisonTable(By element)
	{
		log.info("[Step] Select third item in the comparison table");
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
		return new ProductDetailsPage(driver, wait);
	}

}
