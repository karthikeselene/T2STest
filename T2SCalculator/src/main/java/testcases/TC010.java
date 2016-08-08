package testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.CalculatorPage;
import utils.Reporter;
import wapper.ApplicationWrapper;

public class TC010 extends ApplicationWrapper {
	
	@Test
	public void checkTC010(){
		
		new CalculatorPage().
		enterFirstNumber("100").
		enterSecondNumber("10").
		selectOperatorByIndex(3).
		clickCalcualte();
		String check = outputValue("//tr[5]/td[2]");
		Reporter.reportStep("Enter First Number is: 100", "INFO");
		Reporter.reportStep("The Selected Operator is: /", "INFO");
		Reporter.reportStep("Enter Second Number is: 10", "INFO");
		Reporter.reportStep("The Actual Output is: "+check, "INFO");
		Reporter.reportStep("The Expected Output is: 10", "INFO");
		if(check.equals("10")){		
			Reporter.reportStep("Division operation working fine", "PASS");
            Reporter.reportStep("TestCase TCOO9 Passed Succesfully", "PASS");
			}else{Reporter.reportStep("Division operation was not working properly", "FAIL");
				throw new RuntimeException("FAILED");
			}
		
	}	
	
	@BeforeClass
	public void beforeClass(){
		browserName="chrome";
		testCaseName="TC010";
		testDescription="Verify the division operation";
	}

}
