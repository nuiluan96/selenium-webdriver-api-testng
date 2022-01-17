package api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Frame_Iframe {
	WebDriver driver;
	Select select;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	
	public void TC1_Wor() {
		driver.get("https://automationfc.com/2020/02/18/training-online-automation-testing/");
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@data-testid='fb:page Facebook Social Plugin']")));
		
		//Get Like amounts
		String likeNumber = driver.findElement(By.xpath("//div[@class='_1drq']")).getText();
		System.out.println(likeNumber);
		
		//Switch về frame chính để có thể tiếp tục switch sang frame khác (google docs)
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'https://docs.google.com')]")));
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='KHÓA HỌC SELENIUM AUTOMATION TESTING']")).isDisplayed());
	}
	
	@Test
	public void TC2() throws InterruptedException {
		driver.get("https://kyna.vn/");
		
		// frame có ID thì chọn dòng thứ 2 - xuất kiểu String
		Thread.sleep(500);
		driver.switchTo().frame("cs_chat_iframe");
		Thread.sleep(500);
		
		driver.findElement(By.xpath("//div[contains(@class,'border_overlay')]")).click();
		Thread.sleep(500);

		
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("Naul Go");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("01203262725");
		select = new Select(driver.findElement(By.id("serviceSelect")));
		select.selectByVisibleText("TƯ VẤN TUYỂN SINH");
		driver.findElement(By.cssSelector("textarea[name='message']")).sendKeys("im training dont worrry!");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("input.submit")).click();
		Thread.sleep(1000);
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(@class,'logged_in_name') and text()='Naul Go']")).isDisplayed());
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
