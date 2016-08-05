package testcases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import pages.CalculatorPage;

public class TC006 extends CalculatorPage {
	
	ExtentReports report = new ExtentReports("./reports/TC006/Report.html");
	ExtentTest logger = report.startTest("TC006_VerifyDefaultOperatorIsSubtract");

	@Test
	public void checkTC006(){
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
		
		try {
			prop.load(new FileInputStream(new File("./object.properties")));
			dropdown = new Select(driver.findElementByName(prop.getProperty("CalculatorPage.Operator.Name")));
			List<WebElement> allOptions = dropdown.getOptions();
			int totalOperators = allOptions.size();
			System.out.println("Number of Operators in the dropdown: "+totalOperators);
			String defaultOperator = allOptions.get(0).getText();
			if(defaultOperator.equals("-")){
			logger.log(LogStatus.PASS, "The default operator in calculator is '-'");
			logger.log(LogStatus.PASS, "TestCase TCOO6 Passed Succesfully");	
			}else{logger.log(LogStatus.FAIL,"The default operator in calculator is not '-'");
			throw new RuntimeException("FAILED");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
	}
	
	@AfterMethod
	public void tearDown(ITestResult result){
		
		closeBrowser();		
		report.endTest(logger);
		report.flush();		
	}
}
