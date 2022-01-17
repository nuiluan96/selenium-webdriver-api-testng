package api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic_02_Xpath_Css2_Part2 {
	WebDriver driver;
	@BeforeClass  
	  public void beforeClass() {
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.get("http://live.demoguru99.com/index.php/customer/account/login/"); 
	  }
  
  public void TC1_ID() throws InterruptedException {
	driver.findElement(By.id("email")).sendKeys("SorryImLearning@gmail.com");
	driver.findElement(By.id("pass")).sendKeys("123456987");
	Thread.sleep(3000);

  }
  @Test
  public void TC2_Class() throws InterruptedException {
	 // driver.navigate().refresh();
	  driver.findElement(By.className("validate-password")).sendKeys("123654");
	  Thread.sleep(3000);
	  
  }
  
 public void TC7_Css_Selector() {
	 
	  
 }
 public void TC8_Xpath() {
	  
 }
 
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
