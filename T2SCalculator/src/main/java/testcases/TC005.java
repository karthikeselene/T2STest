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

public class TC005 extends CalculatorPage{
	
	ExtentReports report = new ExtentReports("./reports/TC005/Report.html");
	ExtentTest logger = report.startTest("TC005_VerifyApplictionAllowsAlphabets");
	
	@Test
	public void checkTC005(){
		prop = new Properties();
		try {
			prop.load(new FileInputStream(new File("./exceptedresult.properties")));
			prop.load(new FileInputStream(new File("./config.properties")));
			invokeApp(prop.getProperty("FIREFOX"));
			if(verifyUrl(prop.getProperty("EURL"))){
				logger.log(LogStatus.PASS, "Application is correctly launched");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		enterFirstNumber("22").
		enterSecondNumber("a").
		clickCalcualte();
		String check = outputValue("//tr[5]/td[2]");
		
		Assert.assertEquals(check, "");
		logger.log(LogStatus.PASS, "Calculator doesn't allow alphabets");
		logger.log(LogStatus.PASS, "TestCase TCOO5 Passed Succesfully");
				
		closeBrowser();
	}
	
	@AfterMethod
	public void tearDown(ITestResult result){
		
		if(result.getStatus()==ITestResult.FAILURE){
			logger.log(LogStatus.FAIL,"Calculator doesn't allow alphabets");
			throw new RuntimeException("FAILED");
		}
		
		report.endTest(logger);
		report.flush();		
	}

}
