package TestMaven.Page;

import static org.junit.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Config.ReadPropert;
import TestMaven.Report.ReportsWatcher;

public class SchiappPage_Home extends ReadPropert{
	
	static String drivers =""; 
	static String exe ="";
	static String url ="";
	private static WebDriver driver;
	private static ReadPropert read=new ReadPropert();
	public static ExtentReports extent;
	private static ExtentTest testReport;
	protected String testSuitName= null;
	
	@Rule
    public  ReportsWatcher reportWatcher = new ReportsWatcher(extent);
	
    
	@Rule
    public TestName testName = new TestName();
    
	
    @BeforeClass
    public static void loadSessionConfiguration() {
    //REPORTING CONFIGURATION
    String reportsFile = System.getProperty("user.dir") + "\\Tests-Results\\reports.html";
      extent = new ExtentReports(reportsFile, false);
      extent.loadConfig(new File(System.getProperty("user.dir") + "\\Config\\reports-config.xml"));
    }
        
    @Before
    public void setup() throws IOException {
        testReport = reportWatcher.createReportForTest(getTestSuiteName() +" : "+ testName.getMethodName());
    }
     
	public static void OpenBrowser() throws IOException {		
		String driversTemp=ReadPropert.ReadPropertFileDriver(drivers);
		String exeTemp=ReadPropert.ReadPropertFileExe(exe);
		String urlTemp=ReadPropert.ReadPropertFileUrl(url); 
	
		System.setProperty(driversTemp,exeTemp);
		driver = new ChromeDriver(); 
	    driver.get(urlTemp);	
	    testReport.log(LogStatus.INFO, "Message", "The schiapparelli site : " + urlTemp);
	    boolean elemTitle=driver.getTitle().contains("Maison Schiaparelli - Bienvenue");
	    testReport.log(LogStatus.INFO, "Message", "The title of page : " +driver.getTitle());
	    assertTrue(elemTitle);   
	}	
	public static void OpenComboboxLanguage() {
		//Open the combobox 
		WebElement LanguageClick = driver.findElement(By.xpath("//button[@data-id='hdr-langues']"));
		LanguageClick.click();
		//Check the displayed language in this combobox 
		List<WebElement> list=driver.findElements(By.id("hdr-langues"));
		for(WebElement elem:list) {
			boolean LanguageFr=elem.getText().contains("fr");
			boolean LanguageEn=elem.getText().contains("en");
			testReport.log(LogStatus.INFO, "Message", "The languages availables in the combobox : " + elem.getText());
			assertTrue(LanguageFr);
			assertTrue(LanguageEn);
		}
	}
	
    protected String getTestSuiteName() {
        if(testSuitName==null){
           testSuitName= this.getClass().getSimpleName();
        }
        return testSuitName;
        
    }
    
    @AfterClass
    public static synchronized void finishReporting() throws Exception {
    	extent.flush();
    }
    
    @After
    public void tearDown() throws Exception {
    	ReportsWatcher.saveScreenShot(driver,"testName");
    	driver.quit();
    }
		}