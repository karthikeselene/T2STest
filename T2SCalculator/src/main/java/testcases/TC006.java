package testcases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.CalculatorPage;
import utils.Reporter;
import wapper.ApplicationWrapper;

public class TC006 extends ApplicationWrapper {
	
	@Test
	public void checkTC006(){
		
		new CalculatorPage();
		
		try {
			prop.load(new FileInputStream(new File("./object.properties")));
			dropdown = new Select(driver.findElementByName(prop.getProperty("CalculatorPage.Operator.Name")));
			List<WebElement> allOptions = dropdown.getOptions();
			int totalOperators = allOptions.size();
			System.out.println("Number of Operators in the dropdown: "+totalOperators);
			String defaultOperator = allOptions.get(0).getText();
			if(defaultOperator.equals("-")){
				Reporter.reportStep("The default operator in calculator is '-'", "PASS");
			    Reporter.reportStep("TestCase TCOO6 Passed Succesfully", "PASS");				
			}else{Reporter.reportStep("The default operator in calculator is not '-'", "FAIL");
				throw new RuntimeException("FAILED");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
	}
	
	@BeforeClass
	public void beforeClass(){
		browserName="chrome";
		testCaseName="TC006";
		testDescription="Verify the default operator is subtract in the application";
	}
}
