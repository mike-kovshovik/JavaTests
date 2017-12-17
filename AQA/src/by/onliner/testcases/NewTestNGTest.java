package by.onliner.testcases;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import by.onliner.pages.OnlinerCatalogPage;
import by.onliner.pages.CompareItemsPage;
import by.onliner.pages.OnlinerHomePage;
import by.onliner.testData.TestData;


public class NewTestNGTest {
	
	public WebDriver driver;
	public WebDriverWait wait;
	
	@BeforeTest
	public void beforeTest()
	{
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 10);
		driver.manage().window().fullscreen();
		driver.navigate().to("http://onliner.by");		
	}	
		
	@Test
	public void e2eOnlinerCatalogTest()
	{
		//2. Navigate to catalog
		OnlinerHomePage onlinerLandingPage = new OnlinerHomePage(driver, wait);
		onlinerLandingPage
			.clickCatalogLink()
			.selectCatalogTopMenuItem(OnlinerCatalogPage.beautyAndSportLink)
			.selectLeftMenuItem(OnlinerCatalogPage.hobbyMenuItemLink)
			.selectCategoryFromGrid(OnlinerCatalogPage.radioControlAirModelsLink)
			.verifyIsPageHeaderEqualTo("Радиоуправляемые авиамодели")  // 6. Assert Radio header
			.selectQuadrocopterCheckbox()  // 7. Select Type = Quadcopter
			.selectBodyTypePlasticCheckbox()   // 8. Select Body = Plastic and Metall
			.selectBodyTypeMetalCheckbox()
			.specifyRangeOfAction(TestData.RANGE) // 9. Specify range
			.clickAdditionalParameters()  // 10. Open additional parameters
			.checkEngineTypeBeskollektornyi()  // 11. Select engine type = Beskollektornyi
			.verifyNumberOfFoundItemsIsCorrect("Найдено 27 товаров")    //12. Verify 27 mathes were found
			.changeSortOrderCheapGoFirst()  // 13. Change sort order. Cheap should go first
			.verifyIsPriceSortedDesc()    // 14. Проверить что цена товара для результата < цены товара для результата 2 в списке			
			.selectItemsToCompare(TestData.indexesOfCheckboxes)  // 15.Пометить 1,3,5 и 6 товары для добавления в сравнение
			.checkNumberOfItemsToCompare("4 товара в сравнении")  // 16.Проверить, что появился стикер с "4 товара в сравнении"
			.clickOnNumberOfItemsToCompare()   // 17.Перейти в сравнение (кликнуть на стикер)
			.selectItemFromComparisonTable(CompareItemsPage.thirdItemInComparisonTable)  // 18.Открыть товар 3
			.verifyInitiallySelectedParametersAreCorrect(TestData.initiallySelectedParameters)  // 19.Проверить что присутствуют изначально выбранные параметры
			.addToCart()   // 20.Добавить в корзину
			.verifyNumerOfItemsInTheCartIsCorrect()   // 21.Проверить, что 1 товар в корзине (нотификация в хедере) 
			.clickNumberOfItemsInCartLink()   // 22.Перейти в корзину (кликнуть на линку в хедере)
			.addPlusOneItem()  // 23.Добавить +1 товар (нажать +)
			.verifyTotalNumberOfItemsAndPrice()  // 24.Проверить, что в Итого стало 2 товара и цена за 2 товара посчитана верно
			.placeOrder()    // 25.Нажать оформить заказ
			.assertLoginToSitePopupAppeared();  // 26.Проверить что логин попап появился
	}

	@AfterTest
	public void afterTest()
	{	
		driver.quit();
	}

}
