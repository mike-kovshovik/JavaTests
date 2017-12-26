package by.onliner.testcases;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import by.onliner.pages.*;
import by.onliner.testData.*;



public class OnlinerTestCases{
	
	public static void main(String[] args) throws InterruptedException {
		
		Logger logger = Logger.getLogger("OnlinerTestCases");
		//PropertyConfigurator.configure("log4j.properties");
		
		
		// initializing webdriver and wait object
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		driver.manage().window().fullscreen();
		logger.info("Initialized driver and opened a full screen window");
				
		
		// 1. Opens www.onliner.by
		driver.navigate().to("http://onliner.by");
		logger.info("opened Onliner.by");
		
		
		//2. Navigate to catalog
		OnlinerHomePage onlinerLandingPage = new OnlinerHomePage(driver, wait);
		onlinerLandingPage.clickCatalogLink();
		logger.info("Opened catalog");
		
		
		// 3. Select beauty and sport section
		// 4. Select Hobby from the left-side menu
		// 5. Select radio_control models
		OnlinerCatalogPage OnlinerCatalog = new OnlinerCatalogPage(driver, wait);
		//OnlinerCatalog.clickBeautyAndSport().clickHobby().clickRadioControlAirModels();
		
		logger.debug("selected Красота и спорт, clicked Hobby, selected RadioAirModels");
		logger.warn("selected Красота и спорт, clicked Hobby, selected RadioAirModels");
		
		// 6. Assert Radio header
		QuadrokoptersPage radiocontrolAirPage = new QuadrokoptersPage(driver, wait);
		radiocontrolAirPage.verifyIsPageHeaderEqualTo("Радиоуправляемые авиамодели");
		logger.error("Verified page header is Радиоуправляемые авиамодели");
		
		
		
		// 7. Select Type = Quadcopter
		// 8. Select Body = Plastic and Metall
		//radiocontrolAirPage.selectQuadrocopterCheckbox().selectBodyTypePlasticCheckbox().selectBodyTypeMetalCheckbox();
		logger.error("Some text");
		
		
		// 9. Specify range
		radiocontrolAirPage.specifyRangeOfAction("100");
		logger.fatal("Fatal message");
		

		// 10. Open additional parameters
		// 11. Select engine type = Beskollektornyi
		//radiocontrolAirPage.clickAdditionalParameters().checkEngineTypeBeskollektornyi();

		
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
	    CompareItemsPage compareItemsPage = new CompareItemsPage(driver, wait); 
	    //compareItemsPage.selectItemFromComparisonTable();
	   
	    
	    // 19.Проверить что присутствуют изначально выбранные параметры (квадрокоптер,пластик или металл,бесколлекторный)
	    ProductDetailsPage detailsPage = new ProductDetailsPage(driver, wait);
		detailsPage.verifyInitiallySelectedParametersAreCorrect(testdata.initiallySelectedParameters);
	    
	    // 20.Добавить в корзину
	    detailsPage.addToCart();
	    	    

	    // 21.Проверить, что 1 товар в корзине (нотификация в хедере)   
	    detailsPage.verifyNumerOfItemsInTheCartIsCorrect();
		
	    
	    // 22.Перейти в корзину (кликнуть на линку в хедере)
	    detailsPage.clickNumberOfItemsInCartLink();
	   
	    
	    // 23.Добавить +1 товар (нажать +)
	  	OnlinerCartPage cartOnliner = new OnlinerCartPage(driver, wait);
	  	cartOnliner.addPlusOneItem();
	   
		
		// 24.Проверить, что в Итого стало 2 товара и цена за 2 товара посчитана верно
	  	cartOnliner.verifyTotalNumberOfItemsAndPrice();

		 
        // 25.Нажать оформить заказ
	  	cartOnliner.placeOrder();
	  	
	  	
        // 26.Проверить что логин попап появился
	  	cartOnliner.assertLoginToSitePopupAppeared();
       
	  	
		driver.quit();
		
	}
}




