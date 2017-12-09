package by.onliner.testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import by.onliner.pages.CartOnliner;
import by.onliner.pages.CatalogOnliner;
import by.onliner.pages.CompareItems;
import by.onliner.pages.ItemDetailsPage;
import by.onliner.pages.OnlinerHomePage;
import by.onliner.pages.Radiocontrolair;
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
	}	
	
	
	@Test
	public void e2eOnlinerCatalogTest()
	{
		// 1. Opens www.onliner.by
		driver.navigate().to("http://onliner.by");
		
		//2. Navigate to catalog
		OnlinerHomePage onlinerLandingPage = new OnlinerHomePage(driver, wait);
		onlinerLandingPage.clickCatalogLink();
	
		// 3. Select beauty and sport section
		// 4. Select Hobby from the left-side menu
		// 5. Select radio_control models
		CatalogOnliner OnlinerCatalog = new CatalogOnliner(driver, wait);
		OnlinerCatalog.clickBeautyAndSport().clickHobby().clickRadioControlAirModels();
		
		// 6. Assert Radio header
		Radiocontrolair radiocontrolAirPage = new Radiocontrolair(driver, wait);
		radiocontrolAirPage.verifyIsPageHeaderEqualTo("Радиоуправляемые авиамодели");
		
		
		// 7. Select Type = Quadcopter
		// 8. Select Body = Plastic and Metall
		radiocontrolAirPage.selectQuadrocopterCheckbox().selectBodyTypePlasticCheckbox().selectBodyTypeMetalCheckbox();
		
		
		// 9. Specify range
		radiocontrolAirPage.specifyRangeOfAction("100");
		

		// 10. Open additional parameters
		// 11. Select engine type = Beskollektornyi
		radiocontrolAirPage.clickAdditionalParameters().checkEngineTypeBeskollektornyi();

		
		// 12. Verify 27 mathes were found
		radiocontrolAirPage.verifyNumberOfFoundItemsIsCorrect("Найдено 27 товаров");

		
		// 13. Change sort order. Cheap should go first
		radiocontrolAirPage.changeSortOrderCheapGoFirst();
		
		
        // 14. Проверить что цена товара для результата < цены товара для результата 2 в списке			
		radiocontrolAirPage.verifyIsPriceSortedDesc();
		
	    
	    // 15.Пометить 1,3,5 и 6 товары для добавления в сравнение
		TestData testdata = new TestData();
		radiocontrolAirPage.selectItemsToCompare(testdata.indexesOfCheckboxes);

		
	    // 16.Проверить, что появился стикер с "4 товара в сравнении"
	    radiocontrolAirPage.checkNumberOfItemsToCompare("4 товара в сравнении");
	    
		
	    // 17.Перейти в сравнение (кликнуть на стикер)
	    radiocontrolAirPage.clickOnNumberOfItemsToCompare();

	    
	    // 18.Открыть товар 3
	    CompareItems compareItemsPage = new CompareItems(driver, wait); 
	    compareItemsPage.clickThirdItemInComparisonTable();
	   
	    
	    // 19.Проверить что присутствуют изначально выбранные параметры (квадрокоптер,пластик или металл,бесколлекторный)
	    ItemDetailsPage detailsPage = new ItemDetailsPage(driver, wait);
		detailsPage.verifyInitiallySelectedParametersAreCorrect(testdata.initiallySelectedParameters);
	    
	    // 20.Добавить в корзину
	    detailsPage.addToCart();
	    	    

	    // 21.Проверить, что 1 товар в корзине (нотификация в хедере)   
	    detailsPage.verifyNumerOfItemsInTheCartIsCorrect();
		
	    
	    // 22.Перейти в корзину (кликнуть на линку в хедере)
	    detailsPage.clickNumberOfItemsInCartLink();
	   
	    
	    // 23.Добавить +1 товар (нажать +)
	  	CartOnliner cartOnliner = new CartOnliner(driver, wait);
	  	cartOnliner.addPlusOneItem();
	   
		
		// 24.Проверить, что в Итого стало 2 товара и цена за 2 товара посчитана верно
	  	cartOnliner.verifyTotalNumberOfItemsAndPrice();

		 
        // 25.Нажать оформить заказ
	  	cartOnliner.placeOrder();
	  	
	  	
        // 26.Проверить что логин попап появился
	  	cartOnliner.assertLoginToSitePopupAppeared();

	}

	@AfterTest
	public void afterTest()
	{	
		driver.quit();
	}

}
