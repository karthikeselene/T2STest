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

public class TC003_02 extends ApplicationWrapper {
	
	@Test
	public void checkTC003_02(){
		
		new CalculatorPage().
		enterFirstNumber(null).
		enterSecondNumber("25").
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
		Reporter.reportStep("If we click without value in first field", "INFO");
		Reporter.reportStep("System will throw alert - 'Please Enter First value.'", "PASS");
        Reporter.reportStep("TestCase TCOO3_02 Passed Succesfully", "PASS");
		}
		
		
		
	}
	
	@BeforeClass
	public void beforeClass(){
		browserName="chrome";
		testCaseName="TC003_02";
		testDescription="Verify blank value in the first field in chrome";
	}

}
