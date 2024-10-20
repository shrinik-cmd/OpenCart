package utilities;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.opentelemetry.context.Context;
import testBase.BaseTest;


public class ExtentReportManager implements ITestListener
{
	public ExtentSparkReporter sparkReporter;	// Represents UI of the report (theme, location, look&feel of report)
	public ExtentReports extent;				// Populating common info on the report (Tester name, browser name, OS name, project name etc.)
	public ExtentTest test;						// Creating test case entries in report & update status of the test methods.
	
	String reportName;
	public void onStart(ITestContext context) 
	{
		/*
		SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date dt=new Date();
		String currentDateTimestamp=df.format(dt);
		*/
		String timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());	//Time stamp
		
		reportName= "Test-Report-"+timestamp+".html";
		sparkReporter=new ExtentSparkReporter(".\\reports\\"+reportName);		// specify the location
		sparkReporter.config().setDocumentTitle("OpenCart Automation Report");	// Title of Report
		sparkReporter.config().setReportName("OpenCart Functional Test");		// Name of report
		sparkReporter.config().setTheme(Theme.DARK);							// Theme of the report
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);	// It will attach the info & report to each other
		
		// this info can get dynamically or at run time
		extent.setSystemInfo("Application Name", "OpenCart");	// Key value pair
		extent.setSystemInfo("Module", "Admin"); 	
		extent.setSystemInfo("Environment", "QA"); 
		extent.setSystemInfo("Tester Name", System.getProperty("user.name"));
		
		String operatingSystem=context.getCurrentXmlTest().getParameter("OS");
		extent.setSystemInfo("Operating System", operatingSystem); 
		
		String brw=context.getCurrentXmlTest().getParameter("Browser");
		extent.setSystemInfo("Browser name", brw);
		
		List<String> includedGroups= context.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty())
		{
			extent.setSystemInfo("Groups", includedGroups.toString());
		}
	}
	
	public void onTestSuccess(ITestResult result)
	{
		test = extent.createTest(result.getTestClass().getName());			// Create a new entry in the report
		test.assignCategory(result.getMethod().getGroups());				// Display Groups in report
		test.log(Status.PASS, "Test case is Passed - "+result.getName());	// Update the status as Pass
		
	}
	
	public void onTestFailure(ITestResult result)
	{
		test = extent.createTest(result.getTestClass().getName());			// Create a new entry in the report
		test.assignCategory(result.getMethod().getGroups());				// Display Groups in report
		test.log(Status.FAIL, "Test case is Failed - "+result.getName());	// Update the status as Fail
		test.log(Status.FAIL, "Test case is Failed because - "+result.getThrowable());	// Update the reason to fail.
		
		try
		{
			String impPath=new BaseTest().captureScreen(result.getName());
			test.addScreenCaptureFromPath(impPath);
		}
		catch(Exception e)
		{
			e.getMessage();
			e.printStackTrace();
		}	
	}
	
	public void onTestSkipped(ITestResult result)
	{
		test = extent.createTest(result.getTestClass().getName());		// Create a new entry in the report
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, "Test case is Skipped - "+result.getName());	// Update the status as Skipped
		test.log(Status.SKIP, "Test case is Skipped because - "+result.getThrowable());	// Update the reason to Skip.
	}
	
	// Mandatory method to every report. Without this it won't work.
	public void onFinish(ITestContext context)
	{
		System.out.println("Generating the report...");
		extent.flush();		// This writes all the test info from the standard repositories to their output view.
	    System.out.println("Report generated.");
	    
	    String pathofExtentReport=System.getProperty("user.dir")+"\\reports\\"+reportName;
	    File extentReport=new File(pathofExtentReport);
	    try 
	    {
	    	Desktop.getDesktop().browse(extentReport.toURI());    	
	    }
	    catch(Exception e) 
	    {
	    	e.printStackTrace();
	    }
		
	}

}
