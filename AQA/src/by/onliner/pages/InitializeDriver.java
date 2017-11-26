package by.onliner.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

public class InitializeDriver
{
	
	WebDriver driver;
	
	public void StartDriver()
	{
		String driverPath = "drivers/chromedriver";
		System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, driverPath);
		WebDriver driver = new ChromeDriver();
		driver.manage().window().fullscreen();
		driver.navigate().to("http://onliner.by");
	}
}
