package by.onliner.testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import by.onliner.pages.*;

public class OnlinerTestCases{
	
	public static void main(String[] args) throws InterruptedException {
		
		// initializing webdriver and wait object
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		driver.manage().window().fullscreen();
				
		
		// 1. Opens www.onliner.by
		driver.navigate().to("http://onliner.by");
		
		
		//2. Navigate to catalog
		OnlinerHomePage OnlinerLandingPage = new OnlinerHomePage(driver, wait);
		OnlinerLandingPage.clickCatalogLink();
		
		
		// 3. Select beauty and sport section
		CatalogOnliner OnlinerCatalog = new CatalogOnliner(driver, wait);
		OnlinerCatalog.clickBeautyAndSport();
		
		
		// 4. Select Hobby from the left-side menu
		OnlinerCatalog.clickHobby();
		
		
		// 5. Select radio_control models
		OnlinerCatalog.clickRadioControlAirModels();
		
		
		// 6. Assert Radio header
		Radiocontrolair radiocontrolAirPage = new Radiocontrolair(driver, wait);
		radiocontrolAirPage.verifyRadioControlModelsHeader();
		
		
		// 7. Select Type = Quadcopter
		radiocontrolAirPage.selectQuadrocopterCheckbox();
				

		// 8. Select Body = Plastic and Metall
		radiocontrolAirPage.selectBodyTypePlasticCheckbox();
		radiocontrolAirPage.selectBodyTypeMetalCheckbox();
		
		
		// 9. Specify range
		radiocontrolAirPage.specifyRangeOfAction("100");
		

		// 10. Open additional parameters
		radiocontrolAirPage.clickAdditionalParameters();

		
		// 11. Select engine type = Beskollektornyi
		radiocontrolAirPage.checkEngineTypeBeskollektornyi();
		
		
		// 12. Verify 20 mathes were found
		radiocontrolAirPage.verifyNumberOfFoundItemsIsCorrect("Найдено 20 товаров");

		
		// 13. Change sort order. Cheap should go first
		radiocontrolAirPage.changeSortOrderCheapGoFirst();
		
		
        // 14. Проверить что цена товара для результата < цены товара для результата 2 в списке			
		radiocontrolAirPage.verifySortOrderIsCorrectCheapGoFirst();
		
	    
	    // 15.Пометить 1,3,5 и 6 товары для добавления в сравнение
		radiocontrolAirPage.checkFirstQuadroCheckbox();
		radiocontrolAirPage.checkThirdQuadroCheckbox();
		radiocontrolAirPage.checkFifthsQuadroCheckbox();
		radiocontrolAirPage.checkSixthsQuadroCheckbox();
		
		
	    // 16.Проверить, что появился стикер с "4 товара в сравнении"
	    radiocontrolAirPage.checkNumberOfItemsToCompare("4 товара в сравнении");
	    
		
	    // 17.Перейти в сравнение (кликнуть на стикер)
	    radiocontrolAirPage.clickOnNumberOfItemsToCompare();

	    
	    // 18.Открыть товар 3
	    CompareItems compareItemsPage = new CompareItems(driver, wait); 
	    compareItemsPage.clickThirdItemInComparisonTable();
	   
	    
	    // 19.Проверить что присутствуют изначально выбранные параметры (квадрокоптер,пластик или металл,бесколлекторный)
	    QuadroHubsanDetails quadroHubsanDetails = new QuadroHubsanDetails(driver, wait);
	    quadroHubsanDetails.verifyInitiallySelectedParametersAreCorrect();
		
	    
	    // 20.Добавить в корзину
	    quadroHubsanDetails.addToCart();
	    	    

	    // 21.Проверить, что 1 товар в корзине (нотификация в хедере)   
	    quadroHubsanDetails.verifyNumerOfItemsInTheCartIsCorrect();
		
	    
	    // 22.Перейти в корзину (кликнуть на линку в хедере)
	    quadroHubsanDetails.clickNumberOfItemsInCartLink();
	   
	    
	    // 23.Добавить +1 товар (нажать +)
	  	CartOnliner cartOnliner = new CartOnliner(driver, wait);
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




