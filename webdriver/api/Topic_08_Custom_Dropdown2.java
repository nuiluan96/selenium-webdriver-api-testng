package api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Custom_Dropdown2 {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromeDriver.exe");
		driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver;
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@Test
	public void TC1_Angular() {
		driver.get(
				"https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");
		selectItemInCustomDropdown("//ejs-dropdownlist[@id='games']", "//ul[@id='games_options']/li", "Golf");
		// dùng hàm để verify
		Assert.assertEquals(getAngularDropdownSelectedText(), "Golf");
	}

	@Test
	public void TC2_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		selectItemInCustomDropdown("//div[@role='listbox']", "//div[@role='option']", "Jenny Hess");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Jenny Hess");
		selectItemInCustomDropdown("//div[@role='listbox']", "//div[@role='option']", "Elliot Fu");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Elliot Fu");
		selectItemInCustomDropdown("//div[@role='listbox']", "//div[@role='option']", "Justen Kitsune");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Justen Kitsune");
	}

	@Test
	public void TC3_Editable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		selectItemInEditableDropdown("//input[@type='text']", "Albania");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Albania");
				
	}

	public void selectItemInCustomDropdown(String parentXpath, String allItemXpath, String expectedText) {
		// click vào dropdown
		driver.findElement(By.xpath(parentXpath)).click();
		// chờ các item hiển thị ra trước khi chọn
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));
		// Lấy hết các item đưa vào 1 list để duyệt qua
		List<WebElement> allItem = driver.findElements(By.xpath(allItemXpath)); // nhớ là find.elements
		// Dùng vòng lặp duyệt qua từng item
		for (WebElement item : allItem) {
			// nếu get được item mong muốn thì click rồi break
			if (item.getText().equals(expectedText)) {
				item.click();
				break;
			}
		}
	}
	
	// verify selected Angular's dropdown item
	public String getAngularDropdownSelectedText() {
		return (String) jsExecutor.executeScript("return document.querySelector(\"select[name='games']>option[selected]\").text");
	}
	
	public void selectItemInEditableDropdown(String parentXpath, String expectedText) {
		driver.findElement(By.xpath(parentXpath)).clear();
		driver.findElement(By.xpath(parentXpath)).sendKeys(expectedText);
		driver.findElement(By.xpath(parentXpath)).sendKeys(Keys.TAB);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
