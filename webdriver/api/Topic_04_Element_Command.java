package api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Element_Command {
	WebDriver driver;
	By ageUnder18 = By.id("under_18");
	By emailTextBox = By.id("mail");
	By slider1 = By.id("slider-1");
	By slider2 = By.id("slider-2");
	By javaLanguageCheckBox = By.id("java");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	 @Test
	public void TC1_Displayed() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		boolean emailTextboxStatus = driver.findElement(By.id("mail")).isDisplayed();
		if (emailTextboxStatus == true) {
			driver.findElement(By.id("mail")).sendKeys("toilahv@gmail.com");
			System.out.println("Email text box is displayed");
		} else {
			System.out.println("Email text box is not displayed");
		}

		boolean ageUnder18 = driver.findElement(By.id("under_18")).isDisplayed();
		if (ageUnder18 == true) {
			driver.findElement(By.id("under_18")).click();
			System.out.println("Under 18 checkbox is displayed");
		} else {
			System.out.println("Under 18 checkbox is not displayed");
		}

		boolean nameUser5 = driver.findElement(By.xpath("//h5[text()='Name: User5']")).isDisplayed();
		if (nameUser5 == true) {
			System.out.println("Name user 5 is displayed");
		} else {
			System.out.println("Name user 5 is not displayed");
		}
	}

// cách viết nâng cao, viết thành hàm 

	// hàm check enable
	public boolean isElementEnabled(By by) {
		WebElement element = driver.findElement(by);
		if (element.isEnabled()) {
			System.out.println(by + " is enabled");
			return true;
		} else {
			System.out.println(by + " is disabled");
			return false;
		}
	}

	// hàm selected
	public boolean isElementSelected(By by) {
		WebElement element = driver.findElement(by);
		if (element.isSelected()) {
			System.out.println(by + " is selected");
			return true;
		} else {
			System.out.println(by + " is deselected");
			return false;
		}
	}

	// hàm click
	public void clickToElement(By by) {
		WebElement element = driver.findElement(by);
		element.click();
	}

	// hàm sendkey
	public void sendKeyToElement(By by, String value) {
		WebElement element = driver.findElement(by);
		element.clear();
		element.sendKeys(value);
	}

	@Test
	public void TC2_Enabled_Selected() {

//Enable
		driver.get("https://automationfc.github.io/basic-form/index.html");
		Assert.assertTrue(isElementEnabled(emailTextBox));
		Assert.assertTrue(isElementEnabled(slider1));
		Assert.assertFalse(isElementEnabled(slider2)); // mong muốn disable

//Select
		clickToElement(ageUnder18);
		clickToElement(javaLanguageCheckBox);
		Assert.assertTrue(isElementSelected(ageUnder18));
		Assert.assertTrue(isElementSelected(javaLanguageCheckBox));

		clickToElement(ageUnder18);
		clickToElement(javaLanguageCheckBox);
		Assert.assertTrue(isElementSelected(ageUnder18));
		Assert.assertFalse(isElementSelected(javaLanguageCheckBox));
	}

	@AfterClass
	
	public void afterClass() {
		driver.quit();
	}

}
