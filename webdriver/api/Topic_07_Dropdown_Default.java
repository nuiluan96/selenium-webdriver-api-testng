package api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Dropdown_Default {
	WebDriver driver;
	Select select;
	String firstName, lastName, emailAddress, companyName, password, date, month, year;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com/");
		firstName = "John";
		lastName = "Cena";
		emailAddress = "ucant"+getRandomNumber()+"@gmail.com";
		companyName = "WWE";
		password = "123654";
		date="30";
		month="September";
		year="1999";

	}

	@Test (invocationCount =5) //lặp lại 5 lần
	public void TC1_Validate() throws InterruptedException {

		driver.findElement(By.xpath("//a[@class='ico-register']")).click();

		checkToTextboxOrRadio(By.id("gender-male"));
		Thread.sleep(1500);
		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);

		// khởi tạo biến select để thao tác với dropdown Date/Month/Year
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
		select.selectByVisibleText(date);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "30");

		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
		select.selectByVisibleText(month);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "September");

		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
		select.selectByVisibleText(year);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "1999");
		
		//fake sll
		emailAddress = "ucant"+getRandomNumber()+"@gmail.com";

		driver.findElement(By.id("Email")).sendKeys(emailAddress);
		driver.findElement(By.id("Company")).sendKeys(companyName);
		checkToTextboxOrRadio(By.id("Newsletter"));
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);

		driver.findElement(By.id("ConfirmPassword")).submit();

		
		//Đăng ký rồi vào trang my account
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Your registration completed']")).isDisplayed());
		driver.findElement(By.xpath("//a[@class='ico-account']")).click();
		//Verify output
		Assert.assertTrue(driver.findElement(By.id("gender-male")).isSelected());
		Assert.assertTrue(driver.findElement(By.id("Newsletter")).isSelected());
		Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"), lastName);
		Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"), emailAddress);
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "30");

		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "September");

		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "1999");
		
		//log out để lặp lại
		driver.findElement(By.cssSelector(".ico-logout")).click();

	}


	public void checkToTextboxOrRadio(By by) {
		WebElement element = driver.findElement(by);
		if (!element.isSelected()) {
			element.click();
		}
	}

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
		
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
