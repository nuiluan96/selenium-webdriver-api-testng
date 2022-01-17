package api;

import java.util.ArrayList;
import java.util.List;
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

public class Topic_07_Multiple_Default_Dropdowns {
	WebDriver driver;
	Select select;
	@BeforeClass  
	  public void beforeClass() {
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
	  }
  @Test
  public void TC1_Multiple()  {
		driver.get("https://automationfc.github.io/basic-form/"); 
		select = new Select(driver.findElement(By.id("job2")));
		select.selectByVisibleText("Automation");
		select.selectByVisibleText("Mobile");
		select.selectByVisibleText("Performance");
		select.selectByVisibleText("Functional UI");
		
		//Kiểm trả dropdown có hỗ trợ multiple
		Assert.assertTrue(select.isMultiple());
		// KT đã chọn đúng 4 items thành công
		List<WebElement> allSelectedItems = select.getAllSelectedOptions();	//lấy list những option đã chọn
		ArrayList<String> allSelectedText= new ArrayList<String>(); 		//tạo list trung gian để verify
		for(WebElement item: allSelectedItems) {
			allSelectedText.add(item.getText());							//truyền lại những option đã chọn vào list trung gian
		}																	//(có thể tạo list thủ công rồi so sánh vs list trung gian)
		Assert.assertEquals(allSelectedText.size(), 4);						//đo list trung gian == true => confirmed
		
  }
   @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
