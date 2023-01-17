package com.seleniumTestNG.suite1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import java.util.ArrayList;


public class TechBeamerSite {
	
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
	public void verifyDemoSiteHomePage() {
		driver.get("https://phptravels.com/demo/");
		driver.manage().window().maximize();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		String expectedTitle = "Book Your Free Demo Now - Phptravels";
		String actualTitle = driver.getTitle();
		System.out.println(actualTitle);
		Assert.assertEquals(actualTitle,expectedTitle, "Phptravels homepage is not available");
		
	}
	
	@Test (priority = 2)
	public void login() throws Throwable {

		WebElement loginLink = driver.findElement(By.xpath("//*[@id=\'loginSignup\']/li[1]/a"));
		loginLink.click();
		ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(newTab.get(1));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		WebElement username = driver.findElement(By.xpath("//*[@id='inputEmail']"));
		WebElement password = driver.findElement(By.xpath("//*[@id='inputPassword']"));


		if(username.isDisplayed() && username.isEnabled() && password.isDisplayed() && password.isEnabled()){
			username.sendKeys("testuser@yahoo.com");
			password.sendKeys("Test@123");
			Thread.sleep(10000);
			//driver.findElement(By.xpath("//*[@id='recaptcha-anchor']/div[1]")).click();
			driver.findElement(By.xpath("//*[@id='login']")).click();
		}

		String expectedUsername = "TEST USER";
		WebElement loggedUser = driver.findElement(By.xpath("//span[normalize-space()='TEST USER']"));
		String actualUsername = loggedUser.getText();
		System.out.println("Print--- "+actualUsername);
		//Assert.assertEquals(actualUsername,expectedUsername, "User page is not available");


	}
	
//	@Test (priority = 3)
//	public void verifySubModule() {
//
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		String[] options = {"My Services","My Licenses","Order New Services","View Available Addons"};
//
//		int count =0;
//		WebElement dropdown= driver.findElement(By.id("Primary_Navbar-Services"));
//		Select select = new Select(dropdown);
//
//		List<WebElement> list = select.getOptions();
//		for (WebElement we : list) {
//			for (int i = 0; i < options.length; i++) {
//				if (we.getText().equals(options[i])) {
//					count++;
//				}
//				if (count == options.length) {
//					Assert.assertEquals(options[i], list.get(i), "Dropdown option is not available");
//				}
//			}
//		}
//
//	}

	@Test (priority = 4)
	public void verifySearch() {

		String expectedUrl = "https://phptravels.org/knowledgebase/search";
		WebElement searchPage = driver.findElement(By.name("search"));
		searchPage.sendKeys("Bill");
		driver.findElement(By.xpath("//div[@class='input-group search d-none d-xl-flex']//button[@type='submit']")).click();
		String actualUrl = driver.getCurrentUrl();
		if(driver.findElement(By.xpath("//*[@id='Secondary_Sidebar-Support-Knowledgebase']")).isSelected())
		Assert.assertEquals(actualUrl,expectedUrl, "Search page is not available");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
