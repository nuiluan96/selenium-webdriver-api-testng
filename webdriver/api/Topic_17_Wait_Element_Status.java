package api;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Wait_Element_Status {
	WebDriver driver;
	WebDriverWait explicitWait;
	Actions action;
	JavascriptExecutor jsExecutor;
	

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 15);
		jsExecutor = (JavascriptExecutor) driver;
		action = new Actions(driver);
		// driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		// driver.manage().window().maximize();
	}

	public void TC1_Visible() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");

		// chờ (trong 15s) submit button's visible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='SubmitLogin']")));

	}

	@Test
	public void TC2_() throws InterruptedException {
		driver.get(
				"https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
		scrollToElement("//h1[@class='kd-title']");
		// Wait for day time picker visible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_ContentPlaceholder1_Panel1")));

		// Verify text in selected dates
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText(),
				"No Selected Dates to display.");

		explicitWait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@title='Tuesday, August 17, 2021']")));
		driver.findElement(By.xpath("//td[@title='Tuesday, August 17, 2021']")).click();

		// sao lại phải move chuột??
		action.moveToElement(driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']"))).perform();

		explicitWait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[not(@style='display:none;')]//div[@class='raDiv']")));
		explicitWait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//div[not(@style='display:none;')]//div[@class='raDiv']")));

		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//td[@class='rcSelected' and @title='Tuesday, August 17, 2021']")));
		Assert.assertTrue(
				driver.findElement(By.xpath("//td[@title='Tuesday, August 17, 2021' and @class='rcSelected']"))
						.isDisplayed());

		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText(),
				"Tuesday, August 17, 2021");

	}

	public void scrollToElement(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}
	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
