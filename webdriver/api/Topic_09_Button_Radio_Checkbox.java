package api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Button_Radio_Checkbox {
	WebDriver driver;
	JavascriptExecutor jvExecutor;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		jvExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	}

	
	public void TC1_Button() throws InterruptedException {
		driver.get("https://www.fahasa.com/customer/account/create?attempt=1");
		By loginBtn = By.xpath("//button[@class='fhs-btn-login']");

		driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();
		Assert.assertFalse(driver.findElement(loginBtn).isEnabled());

		driver.findElement(By.xpath("//input[@id='login_username']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@id='login_password']")).sendKeys("automation");
		Thread.sleep(2500);
		Assert.assertTrue(driver.findElement(loginBtn).isEnabled());

		driver.navigate().refresh();
		driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();

		// CHỖ NÀY TRỌNG TÂM
		removeDisableAttributeByJS(loginBtn);
		driver.findElement(loginBtn).click();
		Thread.sleep(2500);
		Assert.assertEquals(driver
				.findElement(By.xpath(
						"//label[text()='Số điện thoại/Email']//following-sibling::div[@class='fhs-input-alert']"))
				.getText(), "Thông tin này không thể để trống");
		Assert.assertEquals(driver
				.findElement(By.xpath("//label[text()='Mật khẩu']//following-sibling::div[@class='fhs-input-alert']"))
				.getText(), "Thông tin này không thể để trống");
	}
	@Test
	//vô nghĩa
	public void TC2_Checkbox() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		// click to all checkbox
		List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
		for (WebElement checkbox : checkboxes) {
			checkbox.click();
		}
		
	}

	public void TC3_Checkbox() {
	}

	public void removeDisableAttributeByJS(By by) {
		WebElement element = driver.findElement(by);
		jvExecutor.executeScript("arguments[0].removeAttribute('disabled')", element);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
