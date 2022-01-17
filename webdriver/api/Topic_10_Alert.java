package api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Alert {
	WebDriver driver;
	WebDriverWait explicitWait;
	Alert alert;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//@Test
	public void TC1_Accept_Alert() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/");
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();

		// đợi alert sau đó switch luôn sang alert vì hàm explicit dưới cũng trả về kiểu
		// alert | làm tắt ko cần dùng switch to
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());

		Assert.assertEquals(alert.getText(), "I am a JS Alert");

		Thread.sleep(1000);
		alert.accept();
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked an alert successfully");

	}
	//@Test
	public void TC2_Confirm_Alert() throws InterruptedException {
		
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();

		alert = explicitWait.until(ExpectedConditions.alertIsPresent());

		Assert.assertEquals(alert.getText(), "I am a JS Confirm");

		Thread.sleep(1000);
		alert.dismiss();
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked: Cancel");

	}
	//@Test
	public void TC3_Prompt_Alert() throws InterruptedException {
		String alertText = 	"Good Student";
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();

		alert = explicitWait.until(ExpectedConditions.alertIsPresent());

		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		
		Thread.sleep(1000);
		alert.sendKeys(alertText);
		Thread.sleep(1000);
		alert.accept();
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You entered: "+alertText);
	}
	//@Test
	public void TC4_Authentication_Alert() {
		//selenium by pass
		//driver.get("http://the-internet.herokuapp.com/basic_auth");
		//id và pass là "admin" 
		driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
		
		//nếu có chứa special chars thì phải encode
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
			}
	
	@Test
	public void TC5_Authentication_Alert_Without_Click_Advance() {
		
		//trường hợp nhấn vào link chứa authen alert sẽ buộc nhập
		
		driver.get("https://the-internet.herokuapp.com/");
		String url = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		driver.get(getCredentialToUrl(url, "admin", "admin"));
		
	}
	
	public String getCredentialToUrl (String url, String username, String password) {
		//split
		String[] urlPart = url.split("//");
		url = urlPart[0]+"//"+username+":"+password+"@"+urlPart[1];
		return url;
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
