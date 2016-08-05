package testcases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import pages.CalculatorPage;

public class TC004 extends CalculatorPage {
	
	ExtentReports report = new ExtentReports("./reports/TC004/Report.html");
	ExtentTest logger = report.startTest("TC003_02_VerifyBlankValueInTheFirstield");
	
	@Test
	public void checkTC004(){
		prop = new Properties();
		try {
			prop.load(new FileInputStream(new File("./exceptedresult.properties")));
			prop.load(new FileInputStream(new File("./config.properties")));
			invokeApp(prop.getProperty("CHROME"));
			if(verifyUrl(prop.getProperty("EURL"))){
				logger.log(LogStatus.PASS, "Application was correctly launched on chrome");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		clickCalcualte();
        String check = driver.switchTo().alert().getText();
		
		Assert.assertEquals(check, "Please Enter values.");
		logger.log(LogStatus.INFO, "If we click without value in the both fields");
		logger.log(LogStatus.PASS, "System will throw alert - 'Please Enter First values.'");
		logger.log(LogStatus.PASS, "TestCase TCOO4 Passed Succesfully");
		driver.switchTo().alert().accept();
		
		closeBrowser();
	}
	
	@AfterMethod
	public void tearDown(ITestResult result){
		
		if(result.getStatus()==ITestResult.FAILURE){
			logger.log(LogStatus.FAIL,"Page Verification");
			throw new RuntimeException("FAILED");
		}
		
		report.endTest(logger);
		report.flush();		
	}

}
