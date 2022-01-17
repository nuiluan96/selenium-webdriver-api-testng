package api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Custom_Dropdown {
	WebDriver driver;
	WebDriverWait explicitWait;
	@BeforeClass  
	  public void beforeClass() {
			driver = new FirefoxDriver();
			explicitWait = new WebDriverWait(driver, 30);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
	  }
  @Test
  public void TC1_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		
		//lỗi số 2, số 2 cũng là số đc chọn mặc định
		
		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li[@class='ui-menu-item']", "15");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(), "15");
		
		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li[@class='ui-menu-item']", "5");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(), "5");
		
		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li[@class='ui-menu-item']", "16");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(), "16");

		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li[@class='ui-menu-item']", "8");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(), "8");

  }
	/* Hành vi của một dropdown:
	Click vào dropdown
	Chờ > tìm items hiển thị ra
 	- Item nằm trong tầm nhìn của user > click
 	- Item invisible > scroll xuống đến khi visible > click
	Kiểm tra xem chọn đúng không.
*/

  public void selectItemInCustomDropdown(String parentXpath, String allItemXpath, String expectedText) {
	  //click vào dropdown
	  driver.findElement(By.xpath(parentXpath)).click();
	  //chờ các item hiển thị ra trước khi chọn ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	  explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));
	  //Lấy hết các item đưa vào 1 list để duyệt qua
	  List<WebElement> allItem = driver.findElements(By.xpath(allItemXpath)); // nhớ là find.elements
	  //Dùng vòng lặp duyệt qua từng item
	  for(WebElement item:allItem) {
		  //nếu get được item mong muốn thì click rồi break
		  if(item.getText().equals(expectedText)) {
			  item.click();
			  break;
		  }
	  }
	  
  }
  
  public void TC3() {
  }
 
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
