package api;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Window_Tab {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC1_Greater_Two_Window_Or_Tab() {
		driver.get("https://kyna.vn/");
		driver.findElement(By.xpath("//div[@id='k-footer']//img[@alt='facebook']")).click();
		Switch_To_Window_By_Title("Kyna.vn - Trang chủ | Facebook");
		Assert.assertTrue(driver.getCurrentUrl().contains("facebook.com"));
		Switch_To_Window_By_Title("Kyna.vn - Học online cùng chuyên gia");		
		driver.findElement(By.xpath("//img[@alt='youtube']")).click();
		Switch_To_Window_By_Title("Kyna.vn - YouTube");
		Assert.assertTrue(driver.getCurrentUrl().contains("youtube.com"));
		Switch_To_Window_By_Title("Kyna.vn - Học online cùng chuyên gia");
		String kynaID = driver.getCurrentUrl();
		driver.findElement(By.xpath("//a[text()='Thông tin hữu ích']")).click();
		Switch_To_Window_By_Title("Tổng hợp bài viết hay, thông tin hữu ích - Kyna.vn");
		
		//close all window without kyna.vn
		closeAllWindowWithoutExpectedWindow(kynaID);
		
	}

	public void Switch_To_Window_By_Title(String expectedWindowTitle) {
		Set<String> allIDs = driver.getWindowHandles();
		for (String id : allIDs) {
			driver.switchTo().window(id);
			if (driver.getTitle().equals(expectedWindowTitle)) {
				break;
			}
		}
	}

	public void closeAllWindowWithoutExpectedWindow(String expectedWindow) {
		Set<String> allIDs= driver.getWindowHandles();
		for (String id :allIDs) {
			if(!id.equals(expectedWindow)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
