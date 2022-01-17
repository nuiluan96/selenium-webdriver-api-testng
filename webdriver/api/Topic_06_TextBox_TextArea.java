package api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_06_TextBox_TextArea {
	WebDriver driver;
	String loginPageURL, userID, userPassword, customerID;
	String name, dob, addr, city, state, pin, phone, email;
	String eaddr, ecity, estate, epin, ephone, eemail;
	By nameTextBoxBy = By.name("name");
	By dobTextBoxBy = By.name("dob");
	By addressTextAreaBy = By.name("addr");
	By cityTextBoxBy = By.name("city");
	By stateTextBoxBy = By.name("state");
	By pinTextBoxBy = By.name("pinno");
	By phoneTextBoxBy = By.name("telephoneno");
	By emailTextBoxBy = By.name("emailid");
	By passwordTextBoxBy = By.name("password");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/v4/");
		name = "MrQuangTeo";
		dob = "1999-01-01";
		addr = "565 Suitalbe Address";
		city = "Newyork";
		state = "Califonia";
		pin = "696969";
		phone = "0927659262";
		email = "imgoodstudent1@gmail.com";
		eaddr = "391 LaPu";
		ecity = "Hano";
		estate = "HoDu";
		epin = "123999";
		ephone = "0902111111";
		eemail = "imgoodstudent2@gmail.com";
	}

	@Test
	public void TC1_Register() throws InterruptedException {
		loginPageURL = driver.getCurrentUrl();
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.name("emailid")).sendKeys(email);
		Thread.sleep(4000);
		driver.findElement(By.name("btnLogin")).click();
		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		userPassword = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();

	}
	@Test
	public void TC2_Login() throws InterruptedException {
		driver.get(loginPageURL);
		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(userPassword);
		Thread.sleep(4000);

		driver.findElement(By.name("btnLogin")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='Manger Id : " + userID + "']")).isDisplayed());

	}
	@Test
	public void TC3_New_Customer() throws InterruptedException {
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		
		driver.findElement(nameTextBoxBy).sendKeys(name);
		driver.findElement(dobTextBoxBy).sendKeys(dob);
		driver.findElement(addressTextAreaBy).sendKeys(addr);
		driver.findElement(phoneTextBoxBy).sendKeys(phone);
		driver.findElement(stateTextBoxBy).sendKeys(state);
		driver.findElement(cityTextBoxBy).sendKeys(city);
		driver.findElement(emailTextBoxBy).sendKeys(email);
		driver.findElement(passwordTextBoxBy).sendKeys(userPassword);
		driver.findElement(pinTextBoxBy).sendKeys(pin);
		driver.findElement(By.name("sub")).click();
		Thread.sleep(4000);
		// server process + response / verify output
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[text()='Customer Registered Successfully!!!']")).isDisplayed());
		Assert.assertEquals(
				driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),
				dob);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),
				addr);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),
				state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),
				phone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),
				email);

		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();

	}
	@Test
	public void TC4_Edit_Customer() throws InterruptedException {
		
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.name("cusid")).sendKeys(customerID);
		Thread.sleep(2000);
		driver.findElement(By.name("AccSubmit")).click();
		Thread.sleep(4000);

		Assert.assertFalse(driver.findElement(By.name("name")).isEnabled());
		Assert.assertFalse(driver.findElement(By.name("gender")).isEnabled());
		Assert.assertFalse(driver.findElement(dobTextBoxBy).isEnabled());

		Assert.assertEquals(driver.findElement(nameTextBoxBy).getAttribute("value"), name);
		Assert.assertEquals(driver.findElement(dobTextBoxBy).getAttribute("value"), dob);
		Assert.assertEquals(driver.findElement(addressTextAreaBy).getText(), addr);
		Assert.assertEquals(driver.findElement(cityTextBoxBy).getAttribute("value"), city);
		Assert.assertEquals(driver.findElement(stateTextBoxBy).getAttribute("value"), state);
		Assert.assertEquals(driver.findElement(pinTextBoxBy).getAttribute("value"), pin);
		Assert.assertEquals(driver.findElement(phoneTextBoxBy).getAttribute("value"), phone);
		Assert.assertEquals(driver.findElement(emailTextBoxBy).getAttribute("value"), email);

		// Edit
		driver.findElement(addressTextAreaBy).clear();
		driver.findElement(addressTextAreaBy).sendKeys(eaddr);
		driver.findElement(phoneTextBoxBy).clear();
		driver.findElement(phoneTextBoxBy).sendKeys(ephone);
		driver.findElement(stateTextBoxBy).clear();
		driver.findElement(stateTextBoxBy).sendKeys(estate);
		driver.findElement(cityTextBoxBy).clear();
		driver.findElement(cityTextBoxBy).sendKeys(ecity);
		driver.findElement(pinTextBoxBy).clear();
		driver.findElement(pinTextBoxBy).sendKeys(epin);
		driver.findElement(emailTextBoxBy).clear();
		driver.findElement(emailTextBoxBy).sendKeys(eemail);
		Thread.sleep(3000);
		driver.findElement(By.name("sub")).click();
		// verify
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Customer details updated Successfully!!!']")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), eaddr);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), ecity);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), estate);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), epin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),ephone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),eemail);
		Thread.sleep(4000);

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
