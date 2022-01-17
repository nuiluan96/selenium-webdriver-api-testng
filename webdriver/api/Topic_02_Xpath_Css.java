package api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic_02_Xpath_Css {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC1_Validate() throws InterruptedException {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("automationFC L");
		Thread.sleep(2000);
		driver.findElement(By.id("txtPassword")).sendKeys("123456789");
		Thread.sleep(2000);
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
