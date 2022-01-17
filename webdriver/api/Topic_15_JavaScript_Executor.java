package api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_JavaScript_Executor {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 15);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	
	public void TC1_Click_Hidden_Element() throws InterruptedException {
		driver.get("https://zingpoll.com/");
		/*
		 * WebElement vietnameseLanguage = driver .findElement(By.
		 * xpath("//li[@class='dropdown hidden-xs']//a[contains(string(),'Tiếng Việt')]"
		 * )); jsExecutor.executeScript("arguments[0].click();", vietnameseLanguage);
		 */
		clickToElementByJS("//li[@class='dropdown hidden-xs']//a[contains(string(),'Tiếng Việt')]");
		Thread.sleep(2000);
	}

	@Test
	public void TC2() throws InterruptedException {
		navigateToUrlByJS("http://live.demoguru99.com/");
		String homepageDomain = (String) executeForBrowser("return document.domain;");
		Thread.sleep(2000);
		Assert.assertEquals(homepageDomain, "live.demoguru99.com");
		
		String homepageURL = (String) executeForBrowser("return document.URL;");
		Assert.assertEquals(homepageURL, "http://live.demoguru99.com/");
		
		highlightElement("//a[text()='Mobile']");
		clickToElementByJS("//a[text()='Mobile']");
		
		highlightElement("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div/button");
		clickToElementByJS("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div/button");
		
		highlightElement("//li[@class='success-msg']//span");
		Assert.assertTrue(getInnerText().contains("Samsung Galaxy was added to your shopping cart."));
		
		scrollToElement("//a[text()='Customer Service']");
		highlightElement("//a[text()='Customer Service']");
		clickToElementByJS("//a[text()='Customer Service']");
		
		scrollToElement("//input[@id='newsletter']");
		highlightElement("//input[@id='newsletter']");
		sendkeyToElementByJS("//input[@id='newsletter']", "mamaboy@gma.com");
		
		highlightElement("//button[@title='Subscribe']");
		clickToElementByJS("//button[@title='Subscribe']");
		
		highlightElement("//li[@class='success-msg']//span");
		Assert.assertTrue(getInnerText().contains("Thank you for your subscription."));
		
		navigateToUrlByJS("http://demo.guru99.com/V4/");
		String demoGuruDomain = (String) executeForBrowser("return document.domain;");
		Assert.assertEquals(demoGuruDomain, "demo.guru99.com");

		
	}

	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor
				.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void highlightElement(String locator) throws InterruptedException {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				"border: 2px solid red; border-style: dashed;");
		Thread.sleep(2000);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	public void scrollToElement(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
				getElement(locator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}

	public boolean areJQueryAndJSLoadedSucess(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, 15);
		jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return JQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("completed");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
