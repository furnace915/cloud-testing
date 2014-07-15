import static junit.framework.Assert.assertEquals;

import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Simple {@link RemoteWebDriver} test that demonstrates how to run your
 * Selenium tests with <a href="http://ondemand.saucelabs.com/ondemand">Sauce
 * OnDemand</a>. *
 * 
 * @author Ross Rowe
 */
public class WebDriverTest {

	private WebDriver driver;

	@Before
	public void setUp() throws Exception {

		DesiredCapabilities capabilities = DesiredCapabilities
				.internetExplorer();
		capabilities.setCapability("version", "8");
		capabilities.setCapability("platform", Platform.WINDOWS);

		/*
		 * DesiredCapabilities capabilities = new DesiredCapabilities();
		 * 
		 * capabilities.setCapability(CapabilityType.PLATFORM,
		 * System.getenv("SELENIUM_PLATFORM"));
		 * capabilities.setBrowserName(System.getenv("SELENIUM_BROWSER"));
		 * capabilities.setVersion(System.getenv("SELENIUM_VERSION"));
		 */
		this.driver = new RemoteWebDriver(
				new URL(
						"http://cengage-qa:1dcb7c05-fe9f-4cc3-8986-3d8023fa254b@ondemand.saucelabs.com:80/wd/hub"),
				capabilities);

	}

	@Test
	public void simplePublicURLTest() throws Exception {
		driver.get("http://www.amazon.com/");
		assertEquals(
				"Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more",
				driver.getTitle());
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

}
