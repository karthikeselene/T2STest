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

public class TC004 extends ApplicationWrapper {
	
	@Test
	public void checkTC004(){
		
		new CalculatorPage().
		clickCalcualte();
		WebDriverWait wait = new WebDriverWait(driver, 100);
		if(wait.until(ExpectedConditions.alertIsPresent())==null){
		    throw new UnhandledAlertException("alert was not present");
		}    
		else{			    
	    Alert alert = driver.switchTo().alert();
	    String alrt = alert.getText();
		alert.accept();
		Assert.assertEquals(alrt, "Please Enter values.");
		Reporter.reportStep("If we click without value in the both fields", "INFO");
		Reporter.reportStep("System will throw alert - 'Please Enter values.'", "PASS");
        Reporter.reportStep("TestCase TCOO4 Passed Succesfully", "PASS");
		}
	}
	
	@BeforeClass
	public void beforeClass(){
		browserName="chrome";
		testCaseName="TC004";
		testDescription="Verify the blank value in the both field";
	}

}
