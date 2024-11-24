package testBase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import utilities.ScreenRecorderUtil;

public class BaseClass {

	public static WebDriver driver;
	public ResourceBundle rb;

	@Parameters({"browser"})
	@BeforeClass()
	public void setUp(String br) throws Exception
	{
		rb=ResourceBundle.getBundle("Config");
		ScreenRecorderUtil.startRecord(br);
		
		if(br.equals("chrome"))
		{
		    driver=new ChromeDriver();
		}
		else if(br.equals("edge"))
		{
			driver=new EdgeDriver();
		}
		else
		{
			driver=new FirefoxDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(rb.getString("appurl"));
		driver.manage().window().maximize();
	}

	@AfterClass()
	public void teardown() throws Exception {
		
		driver.quit();
		ScreenRecorderUtil.stopRecord();
	}
	
	public String captureScreen(String tname) throws IOException
	{
		SimpleDateFormat df=new SimpleDateFormat("yyyyMMddhhmmss");
		Date dt=new Date();
		String timestamp=df.format(dt);
		
		TakesScreenshot ts=(TakesScreenshot)driver;
		
		File src=ts.getScreenshotAs(OutputType.FILE);
		String trg=System.getProperty("user.dir")+"\\Screenshots\\"+tname+"_"+timestamp+".png";
		
		try
		{
		FileUtils.copyFile(src, new File(trg));
		}
		catch(Exception e)
		{
			e.getMessage();
		}
		
		return trg;
		
	}

}
