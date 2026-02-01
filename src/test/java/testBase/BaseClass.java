package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;
import java.net.URL;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

public class BaseClass {

    public static WebDriver driver;
    public Logger logger;    // Log4j
    public Properties p;
    
    
    @BeforeClass(groups= {"Master", "Sanity", "Regression"})
    @Parameters({"os","browser"})
    public void setup(String os, String br) throws IOException
    {
    	//loading config.properties file
    	FileReader file = new FileReader("./src//test//resources//config.properties");
    	p=new Properties();
    	p.load(file);
    	
    	
        logger=LogManager.getLogger(this.getClass());   //LOG4J2
        
        if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
        		{
        	DesiredCapabilities Capabilities = new DesiredCapabilities();
        	//operatin system
        	if(os.equalsIgnoreCase("windows"))
        	{
        		Capabilities.setPlatform(Platform.WIN11);
        	}
        	else if(os.equalsIgnoreCase("mac"))
        	{
        		Capabilities.setPlatform(Platform.MAC);
        	}
        	else
        	{
        		System.out.println("No matching found");
        		return;
        	}
        			
        	//Browser		
        	switch(br.toLowerCase())
        	{
        	case "chrome" : Capabilities.setBrowserName("chrome"); break;
        	case "edge" : Capabilities.setBrowserName("edge"); break;
        	case "firefox" : Capabilities.setBrowserName("firefox"); break;
        	
        	default: System.out.println("No matching browser"); return;
        	}
        	
        	driver = new RemoteWebDriver(new URL("http://10.188.41.202:4444/wd/hub"), Capabilities);
        		}
        
        
        
        
        if(p.getProperty("execution_env").equalsIgnoreCase("local"))
        {
        switch(br.toLowerCase())
            {
        case "chrome" : driver=new ChromeDriver(); break;
        case "edge" : driver=new EdgeDriver(); break;
        case "firefox" : driver=new FirefoxDriver(); break;
        
        default : System.out.println("Invalid browser name.."); return;
            }
        }
    	logger = LogManager.getLogger(this.getClass());
        //logger.info("Starting test on OS: " + os + ", Browser: " + br);

    	
        //driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
         
//      driver.get("https://tutorialsninja.com/demo/");
//      //driver.get("http://localhost/opencart/upload/index.php");
        
        driver.get(p.getProperty("appURL1"));    //Reading url from properties file
        driver.manage().window().maximize();
    }

    @AfterClass(groups= {"Master", "Sanity", "Regression"})
    public void tearDown()
    {
        driver.quit();
    }

    public String randomString() 
    {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    public String randomNumber() 
    {
        return UUID.randomUUID().toString().replaceAll("\\D", "").substring(0, 10);
    }

    public String randomAlphaNumeric() {
        String letters = UUID.randomUUID().toString().replaceAll("[^A-Za-z]", "").substring(0, 3);
        String numbers = UUID.randomUUID().toString().replaceAll("\\D", "").substring(0, 3);
        return letters + "@" + numbers;
    }
    
    public String captureScreen(String tname) throws IOException {

    	String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

    	TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
    	File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

    	String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" 
    			+ tname + "_" + timeStamp + ".png";
    	File targetFile = new File(targetFilePath);

    	sourceFile.renameTo(targetFile);

    	return targetFilePath;
    }

    
 }