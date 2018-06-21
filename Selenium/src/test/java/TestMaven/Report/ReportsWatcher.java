package TestMaven.Report;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.AssumptionViolatedException;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import TestMaven.Page.SchiappPage_Home;


public class ReportsWatcher extends TestWatcher {
	static List<TestCase> executedTestCases = new ArrayList<TestCase>();
    private ExtentReports extent;
    private static ExtentTest testReport;
	private TestCase testCaseUnderExecution;
	WebDriver driver ; 
	 
    public ReportsWatcher(ExtentReports extent) {
        this.extent =  extent;
    }
    
    public static List<TestCase> getExecutedTestCases() {
        return executedTestCases;
    }
      
  //Screeshot
	   public static String saveScreenShot(WebDriver driver, String testName) throws IOException { 
		       String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		       File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		       File screenShotPath = new File(System.getProperty("user.dir")
	               + "\\Tests-Results\\screenshots\\" +testName +"_"+ dateName +".jpg");
		       FileUtils.copyFile(scrFile,screenShotPath);
		       String pj=System.getProperty("user.dir")
				     + "\\Tests-Results\\screenshots\\" +testName +"_"+ dateName +".jpg" ;
		       testReport.log(LogStatus.INFO, testReport.addScreenCapture(pj));
		       return screenShotPath.getPath(); 
		}
	   
    @Override
    protected void failed(Throwable e, Description description) {
        testReport.log(LogStatus.FAIL, "Test Status", "Test Failed");
        testCaseUnderExecution.setTestCaseResultStatus("Failed");
        executedTestCases.add(testCaseUnderExecution);
        extent.endTest(testReport);
    }
     
    //When passed only write to the log.
    @Override
    protected void succeeded(Description description) {
        testReport.log(LogStatus.PASS,"Test Status", "Test Passed");
        testCaseUnderExecution.setTestCaseResultStatus("Success");
        executedTestCases.add(testCaseUnderExecution);
        extent.endTest(testReport);
    }
    
    @Override
    protected void skipped(AssumptionViolatedException e, Description description) { 
        testReport.log(LogStatus.SKIP ,"Test Status", "Test Skipped");
        testCaseUnderExecution.setTestCaseResultStatus("Skipped");
        executedTestCases.add(testCaseUnderExecution);
        extent.endTest(testReport);
    }

    public ExtentTest createReportForTest(String testName) {
        this.testReport = extent.startTest(testName);   
        return this.testReport;
    }
   
	@Override
	protected void starting(Description description) {
		testCaseUnderExecution = new TestCase();
		testCaseUnderExecution.setTestCaseName(description.getMethodName());
		testCaseUnderExecution.setTestCaseDrescription(description.getTestClass().getSimpleName()+" : "+description.getMethodName());
		testCaseUnderExecution.setTestCaseExecutionTime(new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss").format(Calendar.getInstance().getTime()));
	}
}
