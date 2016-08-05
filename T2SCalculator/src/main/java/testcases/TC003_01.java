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

public class TC003_01 extends CalculatorPage {
	
	ExtentReports report = new ExtentReports("./reports/TC003_01/Report.html");
	ExtentTest logger = report.startTest("TC003_01_VerifyBlankValueInTheSecondField");
	
	@Test
	public void checkTC003_01(){
		prop = new Properties();
		try {
			prop.load(new FileInputStream(new File("./exceptedresult.properties")));
			prop.load(new FileInputStream(new File("./config.properties")));
			invokeApp(prop.getProperty("FIREFOX"));
			if(verifyUrl(prop.getProperty("EURL"))){
				logger.log(LogStatus.PASS, "Application was correctly launched on firefox");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		enterFirstNumber("25").
		enterSecondNumber(null).
		clickCalcualte();
        String check = driver.switchTo().alert().getText();
		
		Assert.assertEquals(check, "Please Enter second value.");
		logger.log(LogStatus.INFO, "If we click without value in second field");
		logger.log(LogStatus.PASS, "System will throw alert - 'Please Enter second value.'");
		logger.log(LogStatus.PASS, "TestCase TCOO3_01 Passed Succesfully");
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
