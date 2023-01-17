package com.seleniumTestNG.suite1;

import org.testng.Assert;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class HdfcBank {
	
	private static WebDriver driver = null;
		
	@BeforeSuite
	public static void setup() {
		String driverPath = "lib/chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		
	}
	
	@AfterSuite
	public static void windup() {
		driver.quit();
	}
	
	@Test (priority = 1)
	public void verifyHDFCBankHomePage() {
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		driver.get("https://www.hdfcbank.com");
		driver.manage().window().maximize();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		String expectedTitle = "Netbanking Services";
		String actualTitle = driver.getTitle();

		Assert.assertTrue(actualTitle.contains(expectedTitle));
		
	}
	
	@Test (priority = 2)
	public void goToLocateUsPage() {

		WebElement locateUsPath = driver.findElement(By.xpath("//a[text()='Locate us']"));
		locateUsPath.click();
		ArrayList<String> newTb = new ArrayList<String>(driver.getWindowHandles());

//		String expectedHeader = "Net Banking";
//		WebElement locateUsPageHeading = driver.findElement(By.xpath("//span[text()='Net Banking']"));
//		String actualHeader = locateUsPageHeading.getText();
//		Assert.assertEquals(actualHeader,expectedHeader, "Locate Us page is not available");

		driver.switchTo().window(newTb.get(1));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		driver.switchTo().window(newTb.get(0));

	}
	
	@Test (priority = 3)
	public void goToNRIPage() {

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		String expectedUrl = "https://www.hdfcbank.com/nri-banking";
		WebElement nriPage = driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/header/div/div/div/div/div[2]/div/div[3]/div/div/div/div/div[1]/div[2]/ul[1]/li[2]/a"));
		nriPage.click();
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl,expectedUrl, "NRI page is not available");

	}

	@Test (priority = 4)
	public void goToSMEPage() {

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		String expectedUrl = "https://www.hdfcbank.com/sme";
		WebElement smePage = driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/header/div/div/div/div/div[2]/div/div[3]/div/div/div/div/div[1]/div[2]/ul[1]/li[3]/a"));
		smePage.click();
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl,expectedUrl, "SME page is not available");

	}

	@Test (priority = 5)
	public void goToPreviousPage() {
		driver.navigate().back();
		driver.navigate().back();
	}
	@Test (priority = 6)
	public void goToForwardPage() {
		driver.navigate().forward();
	}

	@Test (priority = 7)
	public void switchToLocateUsPage() {
		ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(newTab.get(1));	
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
