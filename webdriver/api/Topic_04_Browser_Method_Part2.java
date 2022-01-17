package api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Browser_Method_Part2 {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}

	@Test
	public void TC1_Get_URL() throws InterruptedException {
		driver.get("http://live.demoguru99.com");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		//Thread.sleep(2500);
		
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
		
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		//Thread.sleep(2500);
		
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");

	}
	
	@Test
	
	public void TC2_Verify_Title() throws InterruptedException {
		driver.get("http://live.demoguru99.com");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		//Thread.sleep(2500);

		Assert.assertEquals(driver.getTitle(), "Customer Login");
		
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		//Thread.sleep(2500);

		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");

	}
	@Test
	public void TC3_Navigate_Back_Forward() throws InterruptedException {
		driver.get("http://live.demoguru99.com");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		//Thread.sleep(2500);
		
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");

		driver.navigate().back();
		//Thread.sleep(3500);


		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");

		driver.navigate().forward();
		//Thread.sleep(2500);

		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");

	}
	@Test
	public void TC4_Page_source_Text() throws InterruptedException {
		driver.navigate().refresh();
		driver.get("http://live.demoguru99.com");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		//Thread.sleep(2500);
		// verify trang có chứa text
		
		Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
		
		//do quá nhiều nên phải assert true
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		//Thread.sleep(2500);

		Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
		
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
