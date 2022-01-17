package api;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class Topic_03_Run_On_Browsers {
	WebDriver driver;

	
	public void TC1_Run_On_Firefox() {
		FirefoxDriver driver = new FirefoxDriver();
		driver.get("https:google.com");
		driver.quit();
	}

	@Test

	// Chạy chrome ko thấy thư mục browserDrivers bên trái

	public void TC2_Run_On_Chrome() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https:google.com");
		driver.quit();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
