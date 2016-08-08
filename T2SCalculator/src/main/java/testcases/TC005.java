package testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.CalculatorPage;
import wapper.ApplicationWrapper;

public class TC005 extends ApplicationWrapper{
	
	@Test
	public void checkTC005(){
		new CalculatorPage(). 	
	    getTextInCalculatorPage("Enter Second Number");
	
	}
	
	@BeforeClass
	public void beforeClass(){
		browserName="firefox";
		testCaseName="TC005";
		testDescription="Verify label of the second field";
	}
}
