package api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_04_Ex_MailChimp {
	WebDriver driver;
	@BeforeClass  
	  public void beforeClass() {
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.get("https://login.mailchimp.com/signup/"); 
	  }
  @Test
  public void TC1_Validate() throws InterruptedException {
	  driver.findElement(By.id("email")).sendKeys("goodattitude@gmail.com");
	  driver.findElement(By.id("new_username")).sendKeys("mrgooda");
	  
	  //Lower case
	  driver.findElement(By.id("new_password")).sendKeys("auto");
	  Thread.sleep(3500);
	  Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
	  Assert.assertFalse(driver.findElement(By.id("create-account")).isEnabled());
	  
	  //Upper case
	  driver.findElement(By.id("new_password")).clear();
	  driver.findElement(By.id("new_password")).sendKeys("Auto");
	  Thread.sleep(3500);
	  Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
	  Assert.assertFalse(driver.findElement(By.id("create-account")).isEnabled());

	  //Number
	  driver.findElement(By.id("new_password")).clear();
	  driver.findElement(By.id("new_password")).sendKeys("20202");
	  Thread.sleep(3500);
	  Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
	  Assert.assertFalse(driver.findElement(By.id("create-account")).isEnabled());

	  //Special
	  driver.findElement(By.id("new_password")).clear();
	  driver.findElement(By.id("new_password")).sendKeys("!@#$");
	  Thread.sleep(3500);
	  Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
	  Assert.assertFalse(driver.findElement(By.id("create-account")).isEnabled());
  
	  //Max-length
	  driver.findElement(By.id("new_password")).clear();
	  driver.findElement(By.id("new_password")).sendKeys("automation20@");
	  Thread.sleep(3500);
	  Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
	  Assert.assertFalse(driver.findElement(By.id("create-account")).isEnabled());
  
	  //Valid
	  driver.findElement(By.id("new_password")).clear();
	  driver.findElement(By.id("new_password")).sendKeys("Automation20@");
	  Thread.sleep(3500);
	  Assert.assertTrue(driver.findElement(By.id("create-account")).isEnabled());

  }
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
