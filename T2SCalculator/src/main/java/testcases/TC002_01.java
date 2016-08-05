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

public class TC002_01 extends CalculatorPage {
	
	ExtentReports report = new ExtentReports("./reports/TC002_01/Report.html");
	ExtentTest logger = report.startTest("TC002_01_VerifyAlphabeticCharacterAllowedinFirstField");
	
	@Test
	public void checkTC002_01(){
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
		
		enterFirstNumber("a").
		enterSecondNumber("22").
		clickCalcualte();
		String check = outputValue("//tr[5]/td[2]");
		
		Assert.assertEquals(check, "");
		logger.log(LogStatus.PASS, "System will throw validation message - 'Please enter a number'.");
		logger.log(LogStatus.PASS,"TestCase TCOO2_01 Passed Succesfully");
		
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
