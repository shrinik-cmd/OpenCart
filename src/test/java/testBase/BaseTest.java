package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;	//Log4j
import org.apache.logging.log4j.Logger;		//Log4j
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseTest 
{
	public static WebDriver driver;
	public Logger logs;		// Log4j
	public Properties p;
	
	@BeforeClass(groups= {"Sanity","Regression","Master"})
	@Parameters({"OS","Browser"})
	public void setup(String OS, String Browse) throws Exception 
	{
		// Loading config.properties File
		FileReader file=new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);									// Loaded the properties file
		
		// Logging the info
		logs=LogManager.getLogger(this.getClass());		// LOG4j2
		
		
		// Remote Execution
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities cap=new DesiredCapabilities();
			
			// Selecting Operating system for Remote execution
			if(OS.equalsIgnoreCase("Windows"))
			{
				cap.setPlatform(Platform.WIN10);
			}
			else if(OS.equalsIgnoreCase("Mac"))
			{
				cap.setPlatform(Platform.MAC);
			}
			else
			{
				System.out.println("No Matching Operating System");
			}
			
			// Selecting Browser for Remote execution
			
			switch(Browse.toLowerCase())
			{
			case "chrome":cap.setBrowserName("Chrome"); break;
			case "edge":cap.setBrowserName("MicrosoftEdge"); break;
			default:System.out.println("Invalid browser name...");
			return;
			}
			
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
			
		}
		
		//Selecting the browser from xml file for Local Execution
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			switch(Browse.toLowerCase())
			{
			case "chrome":driver=new ChromeDriver(); break;
			case "edge":driver=new EdgeDriver(); break;
			case "firfox":driver=new FirefoxDriver(); break;
			default:System.out.println("Invalid browser name...");
			return;
			}
			
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.get(p.getProperty("appURL"));			//Reading URL & URL will captured from properties file
			driver.manage().window().maximize();
		}
		
	}
	
	@AfterClass(groups= {"Sanity","Regression","Master"})
	public void tearDown() 
	{
		driver.quit();
	}
	
	// Creating Random Strings, Numbers, AlphaNumeric Strings
	public String randomString() 
	{
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	
	public String randomNumber() 
	{
		String generatedNumber = RandomStringUtils.randomNumeric(10);
		return generatedNumber;
	}
	
	public String randomAlphaNumeric() 
	{
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		String generatedNumber = RandomStringUtils.randomNumeric(5);
		return (generatedString+"@"+generatedNumber);
	}

	public String captureScreen(String tname) 
	{
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()); 
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver; 
		File sourceFile = takesScreenshot.getScreenshotAs (OutputType.FILE); 
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath); 
		sourceFile.renameTo(targetFile); 
		return targetFilePath;
	}

}
