package api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_02_Xpath_Css2_Part3 {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
	}

	@Test
	public void TC1_Login_With_Empty_Email_And_Password() {
		driver.findElement(By.id("email")).sendKeys("");
		driver.findElement(By.name("login[password]")).sendKeys("");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		String emailErrorMessage = driver.findElement(By.id("advice-required-entry-email")).getText();
		Assert.assertEquals(emailErrorMessage, "This is a required field.");
		
		//dài dòng, nhét code vô lệnh so sánh luôn
	}
	@Test
	public void TC2_Login_With_Invalid_Email() {
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.name("login[password]")).clear();
		driver.findElement(By.id("email")).sendKeys("123@123.123");
		driver.findElement(By.name("login[password]")).sendKeys("123123");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");

	}
	@Test
	public void TC3_Login_With_Invalid_Password() {
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.name("login[password]")).clear();
		driver.findElement(By.id("email")).sendKeys("luan@gmail.com");
		driver.findElement(By.name("login[password]")).sendKeys("123");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");

	}
	@Test
	public void TC4_Login_With_Incorrect_Password() {
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.name("login[password]")).clear();
		driver.findElement(By.id("email")).sendKeys("luan@gmail.com");
		driver.findElement(By.name("login[password]")).sendKeys("123123");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(), "Invalid login or password.");
		

	}
	@AfterClass
	  public void afterClass() {
		  driver.quit();
	  }

}
