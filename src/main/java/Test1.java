import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Test1 {
	WebDriver driver;

	@BeforeTest
	public void setup() throws MalformedURLException {   

		String browser=System.getProperty("BROWSER","firefox");
		String execution=System.getProperty("EXECUTION","local");
		DesiredCapabilities caps=new DesiredCapabilities();
		System.out.println("============>In setUP<==============");


		if(execution.equalsIgnoreCase("local")) {
			if(browser.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver","./geckodriver_76" );  
				// Initialize Gecko Driver using Desired Capabilities Class  
				FirefoxOptions options = new FirefoxOptions().addPreference("marionette",true);
				// capabilities.setCapability();  
				driver = new FirefoxDriver(options);
			}
			else if(browser.equalsIgnoreCase("chrome"))
			{

			}
		}
		else if(execution.equalsIgnoreCase("remote")) {
			if(browser.equalsIgnoreCase("firefox")) {
				caps=new DesiredCapabilities();
				caps.setBrowserName("firefox");
				caps.setPlatform(Platform.LINUX);
				driver=new RemoteWebDriver(new URL("http://10.100.93.238:4444/wd/hub"), caps);
			}
			else if(browser.equalsIgnoreCase("chrome"))
			{
				caps.setBrowserName("chrome");
				caps.setPlatform(Platform.LINUX);
				caps.setVersion("83");
				driver=new RemoteWebDriver(new URL("http://10.100.93.238:4444/wd/hub"), caps);
			}
		}




	}
	
	@AfterTest()
	public void tearDown() {
		System.out.println("============>In TearDown<==============");

		driver.quit();
	}

	@Test(invocationCount=5, threadPoolSize=1)
	public void remoteTest_firefox() throws MalformedURLException {

		driver.get("http://thedemosite.co.uk/");
		System.out.println("firefox========>"+driver.findElement(By.xpath("//center/big")).getText());

	}

	@Test(invocationCount=5, threadPoolSize=1)
	public void remoteTest_chrome() throws MalformedURLException {

		driver.get("http://thedemosite.co.uk/");
		System.out.println("chrome========>"+driver.findElement(By.xpath("//center/big")).getText());

	}
	
	

}
