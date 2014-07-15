package com.gale.sauce.quickstart.testNG;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
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
import org.testng.annotations.Configuration;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class BaseWebDriverTestNG {
	protected ThreadLocal<RemoteWebDriver> threadDriver = null;

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("running @BeforeSuite() in base class");
	}

	@BeforeTest
	public void beforeTest() throws MalformedURLException {
		System.out.println("running @BeforeTest()");
		
	}

	@BeforeClass
	public void beforeClass() throws MalformedURLException {
		System.out.println("running @BeforeClass");
	}

	@BeforeMethod
	public void beforeMethod() throws MalformedURLException {
		System.out.println("running @BeforeMethod in base class");

		threadDriver = new ThreadLocal<RemoteWebDriver>();
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME,"firefox");
		capabilities.setCapability(CapabilityType.VERSION, "26");
		capabilities.setCapability(CapabilityType.PLATFORM,"LINUX");

		threadDriver.set(new RemoteWebDriver(
				new URL("http://cengage-qa:1dcb7c05-fe9f-4cc3-8986-3d8023fa254b@ondemand.saucelabs.com:80/wd/hub"),capabilities));
		
		
	}
	
	public WebDriver getDriver(){
		return threadDriver.get();
	}

	
	@AfterMethod
	public void closeBrowser() {
		System.out.println("running @AfterMethod in base class");	
		getDriver().quit();
	}

	@AfterClass
	public void afterClass() {
		System.out.println("running @AfterClass() in base class");
	}

	@AfterTest
	public void afterTest() {
		System.out.println("running @AfterTest() in base class");
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("running @AfterSuite() in base class");
	}

}
