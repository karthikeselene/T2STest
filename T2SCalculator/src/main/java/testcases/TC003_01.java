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

public class TC003_01 extends ApplicationWrapper {
	
	@Test
	public void checkTC003_01(){
		new CalculatorPage().
		enterFirstNumber("25").
		enterSecondNumber(null).
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
		Reporter.reportStep("If we click without value in second field", "INFO");
		Reporter.reportStep("System will throw alert - 'Please Enter second value.'", "PASS");
        Reporter.reportStep("TestCase TCOO3_01 Passed Succesfully", "PASS");
		}
        
				
	}
	
	@BeforeClass
	public void beforeClass(){
		browserName="chrome";
		testCaseName="TC003_01";
		testDescription="Verify blank value in the second field in firefox";
	}

}
