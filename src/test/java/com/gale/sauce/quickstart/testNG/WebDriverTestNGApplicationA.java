package com.gale.sauce.quickstart.testNG;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class WebDriverTestNGApplicationA extends BaseWebDriverTestNG {

	@BeforeSuite
	public void beforeSuite_TestClass() {
		System.out.println("running @BeforeSuite() in test class");
	}

	@BeforeTest
	public void beforeTest_TestClass() throws MalformedURLException {
		System.out.println("running @BeforeTest() in test class");

	}

	@BeforeClass
	public void beforeClass_TestClass() throws MalformedURLException {
		System.out.println("running @BeforeClass in test class");
	}

	@BeforeMethod
	public void beforeMethod_TestClass() throws MalformedURLException {
		System.out.println("running @BeforeMethod in test class");
		getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		getDriver().get("http://www.amazon.com");

	}

	@Test
	public void searchForItemAndCheckFirstResultAllDepartments()
			throws Exception {
		System.out
				.println("running @Test searchForItemAndCheckFirstResultAllDepartments()");

		getDriver().findElement(By.id("twotabsearchtextbox")).sendKeys(
				"beats by dre");
		getDriver().findElement(By.className("nav-submit-input")).click();

		WebElement firstLink = getDriver().findElement(
				By.xpath(".//*[@id='result_1']/h3/a"));

		Assert.assertThat(firstLink.getText(), Matchers.containsString("Beats"));
		Assert.assertThat(firstLink.getText(),
				Matchers.containsString("Headphone"));
	}

	@Test
	public void searchForItemByDepartmentAndCheckFirstResult() throws Exception {
		System.out
				.println("running @Test searchForItemByDepartmentAndCheckFirstResult()");

		Select ddl = new Select(getDriver().findElement(
				By.className("searchSelect")));
		ddl.selectByVisibleText("Electronics");

		getDriver().findElement(By.id("twotabsearchtextbox")).sendKeys(
				"beats by dre");
		getDriver().findElement(By.className("nav-submit-input")).click();

		WebElement firstLink = getDriver().findElement(
				By.xpath(".//*[@id='result_1']/h3/a"));
		System.out.println("link getText()" + firstLink.getText());

		Assert.assertThat(firstLink.getText(), Matchers.containsString("Beats"));
		Assert.assertThat(firstLink.getText(),
				Matchers.containsString("Headphone"));
	}

	@AfterMethod
	public void afterMethod_TestClass() {
		System.out.println("running @AfterMethod in test class");
		getDriver().get("http://www.amazon.com/");

	}

	@AfterClass
	public void afterClass_TestClass() {
		System.out.println("running @AfterClass() in test class");
	}

	@AfterTest
	public void afterTest_TestClass() {
		System.out.println("running @AfterTest() in test class");
	}

	@AfterSuite
	public void afterSuite_TestClass() {
		System.out.println("running @AfterSuite() in test class");
	}

}
