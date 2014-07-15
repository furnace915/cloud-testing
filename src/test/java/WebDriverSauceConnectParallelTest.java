import static junit.framework.Assert.assertEquals;

import java.net.URL;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.junit.Parallelized;
import com.saucelabs.junit.SauceOnDemandTestWatcher;


/*If running from Eclipse, you must create SauceConnect Local Tunnel before running any test cases that hit urls behind Cengage firewall
	To create the tunnel, navigate to location of Sauce-Connect.jar and run the following command:
	java -jar Sauce-Connect.jar [sauce-id][sauce-token]
	e.g., java -jar Sauce- Connect.jar cengage-qa 1dcb7c05-fe9f-4cc3-8986-3d8023fa254b
*/


@RunWith(Parallelized.class)
public class WebDriverSauceConnectParallelTest implements SauceOnDemandSessionIdProvider {
	
	private WebDriver driver;
	private String sessionId;
	private String browser;
    private String os;
    private String version;
	
	public WebDriverSauceConnectParallelTest(String os, String version, String browser) {
		super();
		this.browser = browser;
		this.os = os;
		this.version = version;
	}
	
	public SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication(
			"cengage-qa", "1dcb7c05-fe9f-4cc3-8986-3d8023fa254b");

	public @Rule
	SauceOnDemandTestWatcher resultReportingTestWatcher = new SauceOnDemandTestWatcher(
			this, authentication);

	public @Rule
	TestName testname = new TestName();
		    
	    @Parameterized.Parameters
	    public static LinkedList<String[]> browsersStrings() throws Exception {
	        LinkedList<String[]> browsers = new LinkedList<String[]>();

//	        browsers.add(new String[]{"Windows 2003", "7", "internet explorer"});
	        browsers.add(new String[]{"Windows 2008", "8", "internet explorer"});
//	        browsers.add(new String[]{"Windows 2012", "10", "internet explorer"});
//	        browsers.add(new String[]{"Windows 2012", "17", "firefox"});
	        browsers.add(new String[]{"Windows 2012", "19", "firefox"});
	        return browsers;
	    }

	@Before
	public void setUp() throws Exception {
			
		DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, browser);
        if (version != null)
            capabilities.setCapability(CapabilityType.VERSION, version);
        capabilities.setCapability(CapabilityType.PLATFORM, os);

        capabilities.setCapability("name", testname.getMethodName());
        
/*		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		capabilities.setCapability("version", "8");
		capabilities.setCapability("platform", Platform.XP);
*/		
		/*DesiredCapabilities capabilities = new DesiredCapabilities();
		
		capabilities.setCapability("name", testname.getMethodName());
        capabilities.setCapability(CapabilityType.PLATFORM, System.getenv("SELENIUM_PLATFORM"));
        capabilities.setBrowserName(System.getenv("SELENIUM_BROWSER"));
        capabilities.setVersion(System.getenv("SELENIUM_VERSION"));*/
        
		this.driver = new RemoteWebDriver(new URL("http://"
				+ authentication.getUsername() + ":"
				+ authentication.getAccessKey()
				+ "@ondemand.saucelabs.com:80/wd/hub"), capabilities);

		this.driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		this.sessionId = ((RemoteWebDriver) driver).getSessionId().toString();
			
	}

	@Override
	public String getSessionId() {
		return sessionId;
	}

	
	@Test
	public void webDriverWithLocalTunnelBIG() throws Exception {
		driver.get("http://bi.qa.gale.web/global");
		driver.findElement(By.name("locid")).sendKeys("big_ft");
		driver.findElement(By.name("locpword")).sendKeys("big_ft");
		driver.findElement(By.xpath("//input[@value='Authenticate']")).click();
		assertEquals("Business Insights: Global",driver.getTitle());		
	}
	
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
