package testcases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
//import org.testng.Assert;
import org.testng.annotations.Test;

import pages.CalculatorPage;
import utils.Reporter;
//import pages.CalculatorPage;
import wapper.ApplicationWrapper;

public class TC002_01 extends ApplicationWrapper {
	
	@Test(dataProvider="fecthData")
	public void checkTC002_01(String fNumber,String sNumber){
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
		Assert.assertEquals(alrt, "Please Enter First value.");
		Reporter.reportStep("System will throw validation message - 'Please Enter First value'.", "PASS");
        Reporter.reportStep("TestCase TCOO2_01 Passed Succesfully", "PASS");		
	} 
	}
	
	@BeforeClass
	public void beforeClass(){
		browserName = "Chrome";
		dataSheetName="TC002_01";
		testCaseName="TC002_01";
		testDescription="Verify Alphabetic Character Allowed in First Field in Chrome";
	}
	
	
}
