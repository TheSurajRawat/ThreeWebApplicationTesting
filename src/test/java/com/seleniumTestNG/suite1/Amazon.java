package com.seleniumTestNG.suite1;

import org.testng.Assert;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class Amazon {

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

	@Test(priority = 1)
	public void verifyAmazonHomePage() {
		driver.get("https://www.Amazon.in");
		driver.manage().window().maximize();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		String expectedTitle = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
		String actualTitle = driver.getTitle();

		Assert.assertEquals(actualTitle, expectedTitle, "Amazon Homepage is not available");

	}

	@Test(priority = 2)
	public void goToPantryPage() {
		driver.findElement(By.xpath("//*[@id='twotabsearchtextbox']")).click();
		driver.findElement(By.xpath("//*[@id='twotabsearchtextbox']")).clear();
		driver.findElement(By.xpath("//*[@id='twotabsearchtextbox']")).sendKeys("Pantry");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.id("nav-search-bar-form")).submit();
	}

	@Test (priority = 3)
	public void openItemDetails() {

		driver.findElement(By.id("a-autoid-0-announce")).click();
		driver.findElement(By.id("s-result-sort-select_1")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		List<WebElement> resultsList = driver.findElements(By.xpath(".//span[@class='a-size-base-plus a-color-base a-text-normal']"));
		resultsList.get(0).click();

		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}