package utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import wapper.ApplicationWrapper;

public class Reporter extends ApplicationWrapper {
	
	private static ExtentTest logger;
	private static ExtentReports report;
	
	public static void reportStep(String desc, String status){
		
		long number = (long) Math.floor(Math.random() * 900000000L) + 10000000L;
		try {
			FileUtils.copyFile(driver.getScreenshotAs(OutputType.FILE), new File("./report/images/"+number+".png"));
		} catch (WebDriverException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Write if it is successful or failure or information
				if(status.toUpperCase().equals("PASS")){
					logger.log(LogStatus.PASS,desc+logger.addScreenCapture("./../report/images/"+number+".png"));
				}else if(status.toUpperCase().equals("FAIL")){
					logger.log(LogStatus.FAIL,desc+logger.addScreenCapture("./../report/images/"+number+".png"));
					throw new RuntimeException("FAILED");
				}else if(status.toUpperCase().equals("INFO")){
					logger.log(LogStatus.INFO,desc+logger.addScreenCapture("./../report/images/"+number+".png"));
				}
		
	}
	
	public static void startResult(){
		report = new ExtentReports("./report/Report.html", false);
		report.loadConfig(new File("./extent-config.xml"));
		
	}
	
	public static void startTestCase(){
		logger = report.startTest(testCaseName, testDescription);
	}
	
	public static void endResult(){
		report.endTest(logger);
		report.flush();
	}

}
