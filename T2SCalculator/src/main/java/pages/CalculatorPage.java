package pages;

import utils.Reporter;
import wapper.ApplicationWrapper;

public class CalculatorPage extends ApplicationWrapper{ 
	
	public CalculatorPage(){
		
			if(!verifyUrl("http://test-app.t2scdn.com/")){
				Reporter.reportStep("This not online calculator page", "FAIL");				
			}
	}
	
	
	public CalculatorPage enterFirstNumber(String one){
		enterByName(prop.getProperty("Calculatorpage.EnterFirstNumber.Name"), one);
		return this;
	}
	
	public CalculatorPage enterSecondNumber(String second){
		enterByName(prop.getProperty("Calculatorpage.EnterSecondNumber.Name"), second);
		return this;
	}
	
	public CalculatorPage selectOperatorByIndex(int value){
		selectByNameIndex(prop.getProperty("CalculatorPage.Operator.Name"), value);
		return this;
	}
	
	public CalculatorPage clickCalcualte(){
		clickByName(prop.getProperty("CalclatorPage.CalculateButton.Name"));
		return this;
	}
	
	public CalculatorPage getTextInCalculatorPage(String data){
		verifyTextByXpath(prop.getProperty("CalclatorPage.SecondLable.Xpath"), data);
		return this;
	}
	
	
}
