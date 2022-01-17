package api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_User_Interaction_Part1 {
	WebDriver driver;
	Actions action;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	
	public void TC1_Hover_Element() throws InterruptedException {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		action.moveToElement(driver.findElement(By.xpath("//a[text()='ThemeRoller']"))).perform();
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).isDisplayed());
	}

	//@Test
	public void TC2_Click_And_Hold() throws InterruptedException {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> numberSelect = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		action.clickAndHold(numberSelect.get(0)).moveToElement(numberSelect.get(3)).release().perform();
		Thread.sleep(500);
		
		//verify
		List<WebElement> numberSelected = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
		Assert.assertEquals(numberSelected.size(), 4);
		for (WebElement number : numberSelected) {
			System.out.println(number.getText());
		}
	}
	
	public void TC3_Click_With_Ctrl() throws InterruptedException {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> numberSelect = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		
		action.keyDown(Keys.CONTROL).perform();		
		action.click(numberSelect.get(0)).click(numberSelect.get(2)).click(numberSelect.get(4)).click(numberSelect.get(6)).perform();		
		action.keyUp(Keys.CONTROL).perform();
		Thread.sleep(500);

	}
		//right click = action.contextClick(...).perform();
	@Test
	public void TC4_DragAndDrop() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		WebElement sourcCircle = driver.findElement(By.id("draggable"));
		WebElement targetCircle = driver.findElement(By.id("droptarget"));
		action.dragAndDrop(sourcCircle, targetCircle).perform();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='droptarget' and text()='You did great!']")).isDisplayed());
		
		//hàm đổi hexa sang rgb
		Assert.assertEquals(getHexaValue(driver.findElement(By.id("droptarget")).getCssValue("background-color")), "#03a9f4");
	}

	public String getHexaValue (String rgbValue) {
		return Color.fromString(rgbValue).asHex();
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
