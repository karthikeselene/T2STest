package testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.CalculatorPage;
import utils.Reporter;
import wapper.ApplicationWrapper;

public class TC008 extends ApplicationWrapper {
	
	@Test
	public void checkTC008(){
		
		new CalculatorPage().
		enterFirstNumber("1989").
		enterSecondNumber("11").
		selectOperatorByIndex(0).
		clickCalcualte();
		String check = outputValue("//tr[5]/td[2]");
		Reporter.reportStep("Enter First Number is: 1989", "INFO");
		Reporter.reportStep("The Selected Operator is: +", "INFO");
		Reporter.reportStep("Enter Second Number is: 11", "INFO");
		Reporter.reportStep("The Actual Output is: "+check, "INFO");
		Reporter.reportStep("The Expected Output is: 2000", "INFO");
		if(check.equals("2000")){		
			Reporter.reportStep("Addition operation working fine", "PASS");
            Reporter.reportStep("TestCase TCOO8 Passed Succesfully", "PASS");
			}else{Reporter.reportStep("Addition operation was not working properly", "FAIL");
				throw new RuntimeException("FAILED");
			}
		
	}	
	
	@BeforeClass
	public void beforeClass(){
		browserName="chrome";
		testCaseName="TC008";
		testDescription="Verify the Addition operation";
	}

}
