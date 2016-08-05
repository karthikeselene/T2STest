package testcases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import pages.CalculatorPage;

public class TC009 extends CalculatorPage {
	
	ExtentReports report = new ExtentReports("./reports/TC009/Report.html");
	ExtentTest logger = report.startTest("TC009_VerifyMuitiplicationOperation");

	@Test
	public void checkTC00(){
		prop = new Properties();
		try {
			prop.load(new FileInputStream(new File("./exceptedresult.properties")));
			prop.load(new FileInputStream(new File("./config.properties")));
			invokeApp(prop.getProperty("CHROME"));
			if(verifyUrl(prop.getProperty("EURL"))){
				logger.log(LogStatus.PASS, "Application is correctly launched on chrome browser");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		enterFirstNumber("100").
		enterSecondNumber("10");
		selectByNameVisibleText("operator", "*");
		clickCalcualte();
		String check = outputValue("//tr[5]/td[2]");
		logger.log(LogStatus.INFO, "Enter First Number is: 100");
		logger.log(LogStatus.INFO, "Enter Second Number is: 10");
		logger.log(LogStatus.INFO, "The Selected Operator is: *");
		logger.log(LogStatus.INFO, "The Actual Output is: "+check);
		logger.log(LogStatus.INFO, "The Expected Output is: 1000");
		if(check.equals("1000")){		
			logger.log(LogStatus.PASS, "Multiplication operation was working fine");
			logger.log(LogStatus.PASS, "TestCase TCOO9 Passed Succesfully");	
			}else{logger.log(LogStatus.FAIL,"Multiplication operation was not working properly");
			throw new RuntimeException("FAILED");
			}
		
	}	
	
	@AfterMethod
	public void tearDown(ITestResult result){
		
		closeBrowser();		
		report.endTest(logger);
		report.flush();		
	}
	

}
