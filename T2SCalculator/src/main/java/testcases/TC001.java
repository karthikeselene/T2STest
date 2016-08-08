package testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.CalculatorPage;
import wapper.ApplicationWrapper;

public class TC001 extends ApplicationWrapper {
	
	@Test
	public void checkTC001() {
		new CalculatorPage();	
	}
	
	@BeforeClass
	public void beforeClass(){
		browserName="chrome";
		testCaseName="TC001";
		testDescription="Verify Application Launcing";
				
	}

}
