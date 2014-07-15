package com.gale.sauce.quickstart.testNG;

import static org.junit.Assert.assertThat;

import java.net.MalformedURLException;
import java.net.URL;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class WebDriverTestNGApplicationB {

	private WebDriver driver;

	/*
	 * @Test(dataProvider = "dp") public void f(Integer n, String s) { }
	 */

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("running @BeforeSuite()");
	}

	@BeforeTest
	public void beforeTest() throws MalformedURLException {
		System.out.println("running @BeforeTest()");
	}

	@BeforeClass
	public void beforeClass() throws MalformedURLException {
		System.out.println("running @BeforeClass");
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setCapability("version", "25");
		capabilities.setCapability("platform", Platform.LINUX);

		this.driver = new RemoteWebDriver(
				new URL("http://cengage-qa:1dcb7c05-fe9f-4cc3-8986-3d8023fa254b@ondemand.saucelabs.com:80/wd/hub"),capabilities);
	}

	@BeforeMethod
	public void beforeMethod() throws MalformedURLException {
		System.out.println("running @BeforeMethod");
//		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
//		capabilities.setCapability("version", "26");
//		capabilities.setCapability("platform", Platform.LINUX);
//
//		this.driver = new RemoteWebDriver(
//				new URL("http://cengage-qa:1dcb7c05-fe9f-4cc3-8986-3d8023fa254b@ondemand.saucelabs.com:80/wd/hub"),capabilities);
	}

	@Test
	public void amazonTest() throws Exception {
		System.out.println("running @Test amazonTest() ");
		driver.get("http://www.amazon.com/");
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(
				"beats by dre");
		driver.findElement(By.className("nav-submit-input")).click();
		Thread.sleep(13000);
		String currentUrl = driver.getCurrentUrl();
		assertThat(currentUrl, Matchers.containsString("beats"));

	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("running @AfterMethod");
		driver.quit();
	}

	/*
	 * @DataProvider public Object[][] dp() { return new Object[][] { new
	 * Object[] { 1, "a" }, new Object[] { 2, "b" }, }; }
	 */

	@AfterClass
	public void afterClass() {
		System.out.println("running @AfterClass()");
	}

	@AfterTest
	public void afterTest() {
		System.out.println("running @AfterTest()");
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("running @AfterSuite()");
	}

}
