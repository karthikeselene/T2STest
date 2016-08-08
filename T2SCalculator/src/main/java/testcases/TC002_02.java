package testcases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.CalculatorPage;
import utils.Reporter;
import wapper.ApplicationWrapper;

public class TC002_02 extends ApplicationWrapper {
	
	@Test(dataProvider="fecthData")
	public void checkTC002_02(String fNumber,String sNumber) throws UnhandledAlertException{
		new CalculatorPage().		
		enterFirstNumber(fNumber).
		enterSecondNumber(sNumber).
		clickCalcualte();
		WebDriverWait wait = new WebDriverWait(driver, 100);
			if(wait.until(ExpectedConditions.alertIsPresent())==null){
			    throw new UnhandledAlertException("alert was not present");
			}    
			else{			    
		    Alert alert = driver.switchTo().alert();
		    String alrt = alert.getText();
			alert.accept();
			Assert.assertEquals(alrt, "Please Enter second value.");
			Reporter.reportStep("System will throw validation message - 'Please Enter second value'.", "PASS");
	        Reporter.reportStep("TestCase TCOO2_02 Passed Succesfully", "PASS");		
		} 		
		
	}
	
	@BeforeClass
	public void beforeClass(){
		browserName="chrome";
		dataSheetName="TC002_02";
		testCaseName="TC002_02";
		testDescription="Verify Alphabetic Character Allowed in First Field in chrome";
	}
}
