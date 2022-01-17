package api;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class Topic_16_Upload_Files {
	WebDriver driver;
	String seleniumPath = "D:\\Automation testing\\02 - Selenium API\\uploadFiles\\cloud.jpg";
	String projectPath = System.getProperty("user.dir");


	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");


	}

	@Test
	public void TC1_Validate() throws InterruptedException {
		
		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='File']"));
		uploadFile.sendKeys(seleniumPath);
		Thread.sleep(3000);
	
	}

	public void TC2() {
	}

	public void TC3() {
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
