package com.mystore.testcases;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class test {

	static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	
	@Parameters("browser")
	@BeforeTest
	public static void beforeTest(String browser) throws MalformedURLException {
		MutableCapabilities options = null;
		if (browser.equalsIgnoreCase("chrome")) {
//			WebDriverManager.chromedriver().setup();
//			driver.set(new ChromeDriver());
			options = new ChromeOptions();
			
        } else if (browser.equalsIgnoreCase("firefox")) {
//        	WebDriverManager.firefoxdriver().setup();
//        	driver.set(new FirefoxDriver());
        	options = new FirefoxOptions();
        }
		WebDriver remote = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),options);
		driver.set(remote);
		driver.get().manage().window().maximize();
	}
	
	@Test
	public static void tests() {
		
		Actions action = new Actions(driver.get());
		
		driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//		wait.pollingEvery(Duration.ofMillis(100));
		
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver.get())
				.pollingEvery(Duration.ofSeconds(5))
			    .ignoring(NoSuchElementException.class);

		
		driver.get().get("https://www.myntra.com/");
		
		WebElement WomanTab = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//a[@class='desktop-main'][text()='Women']")));
		
		action.moveToElement(WomanTab).perform();
		
		List<WebElement> womanTab_All_Links = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
			By.xpath("//a[@class='desktop-main'][text()='Women']/following-sibling::*//a")));
		
		for(WebElement ele : womanTab_All_Links) {

			System.out.println(ele.getText());
		}
		
		WebElement jeans_WomanTab = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//a[@class='desktop-main']"
				+ "[text()='Women']/following-sibling::*"
				+ "//a[@class='desktop-categoryLink'][text()='Jeans']")));
		
		jeans_WomanTab.click();
		
		
	}
	
	@AfterTest
	public static void terminate() {

		driver.get().quit();
	}

}
