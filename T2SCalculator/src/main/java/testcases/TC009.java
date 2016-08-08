package testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.CalculatorPage;
import utils.Reporter;
import wapper.ApplicationWrapper;

public class TC009 extends ApplicationWrapper {
	
	@Test
	public void checkTC009(){
		
		new CalculatorPage().
		enterFirstNumber("100").
		enterSecondNumber("10").
		selectOperatorByIndex(2).
		clickCalcualte();
		String check = outputValue("//tr[5]/td[2]");
		Reporter.reportStep("Enter First Number is: 100", "INFO");
		Reporter.reportStep("The Selected Operator is: *", "INFO");
		Reporter.reportStep("Enter Second Number is: 10", "INFO");
		Reporter.reportStep("The Actual Output is: "+check, "INFO");
		Reporter.reportStep("The Expected Output is: 1000", "INFO");
		if(check.equals("1000")){		
			Reporter.reportStep("Multiplication operation working fine", "PASS");
            Reporter.reportStep("TestCase TCOO9 Passed Succesfully", "PASS");
			}else{Reporter.reportStep("Multiplication operation was not working properly", "FAIL");
				throw new RuntimeException("FAILED");
			}
		
	}	
	
	@BeforeClass
	public void beforeClass(){
		browserName="chrome";
		testCaseName="TC009";
		testDescription="Verify the multiplication operation";
	}

}
