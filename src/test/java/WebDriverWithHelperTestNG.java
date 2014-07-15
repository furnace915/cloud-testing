import static junit.framework.Assert.assertEquals;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.junit.SauceOnDemandTestWatcher;

public class WebDriverWithHelperTestNG implements SauceOnDemandSessionIdProvider {

	public SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication(
			"cengage-qa", "1dcb7c05-fe9f-4cc3-8986-3d8023fa254b");

	public @Rule
	SauceOnDemandTestWatcher resultReportingTestWatcher = new SauceOnDemandTestWatcher(
			this, authentication);

	public @Rule
	TestName testname = new TestName();

	private WebDriver driver;

	private String sessionId;

	@Before
	public void setUp() throws Exception {

		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setCapability("version", "20");
		capabilities.setCapability("platform", Platform.WINDOWS);
		
		/*DesiredCapabilities capabilities = new DesiredCapabilities();
		
		capabilities.setCapability("name", testname.getMethodName());
        capabilities.setCapability(CapabilityType.PLATFORM, System.getenv("SELENIUM_PLATFORM"));
        capabilities.setBrowserName(System.getenv("SELENIUM_BROWSER"));
        capabilities.setVersion(System.getenv("SELENIUM_VERSION"));*/
        
		this.driver = new RemoteWebDriver(new URL("http://"
				+ authentication.getUsername() + ":"
				+ authentication.getAccessKey()
				+ "@ondemand.saucelabs.com:80/wd/hub"), capabilities);
		this.driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		this.sessionId = ((RemoteWebDriver) driver).getSessionId().toString();

	}

	@Override
	public String getSessionId() {
		return sessionId;
	}


	@Test
	public void webDriverWithHelper() throws Exception {
		driver.get("http://www.amazon.com");
		assertEquals(
				"Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more",
				driver.getTitle());
	}
	
/*	@Test
	public void webDriverWithLocalTunnel() throws Exception {
		driver.get("http://bi.qa.gale.web/global");
		driver.findElement(By.name("locid")).sendKeys("big_ft");
		driver.findElement(By.name("locpword")).sendKeys("big_ft");
		driver.findElement(By.xpath("//input[@value='Authenticate']")).click();
		assertEquals("Business Insights: Global", driver.getTitle());
	}*/
	
	@Test
	public void webDriverWithLocalTunnelOMNI() throws Exception {
		driver.get("http://omni-as01.dev.gale.web:8080/" +
				"ps/start.do?u=gale&p=AONE");
		driver.findElement(By.name("password")).sendKeys("qa");
		driver.findElement(By.name("proceed")).click();
		assertEquals("Academic OneFile - Basic Search", driver.getTitle());
	}
	
	
	
	
	
	@After
	public void tearDown() throws Exception{
		driver.quit();
	}

}
