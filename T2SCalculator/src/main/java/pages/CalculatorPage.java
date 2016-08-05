package pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import wapper.GenericWapper;

public class CalculatorPage extends GenericWapper{
	
	public CalculatorPage enterFirstNumber(String one){
		prop = new Properties();
		try {
			prop.load(new FileInputStream(new File("./object.properties")));
			enterByName(prop.getProperty("Calculatorpage.EnterFirstNumber.Name"), one);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return this;
	}
	
	public CalculatorPage enterSecondNumber(String second){
		prop = new Properties();
		try {
			prop.load(new FileInputStream(new File("./object.properties")));
			enterByName(prop.getProperty("Calculatorpage.EnterSecondNumber.Name"), second);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return this;
	}
	
	public CalculatorPage selectOperatorByIndex(int value){
		prop = new Properties();
		try {
			prop.load(new FileInputStream(new File("./object.properties")));
			selectByNameIndex(prop.getProperty("CalculatorPage.Operator.Name"), value);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return this;
	}
	
	public CalculatorPage clickCalcualte(){
		prop = new Properties();
		try {
			prop.load(new FileInputStream(new File("./object.properties")));
			clickByName(prop.getProperty("CalclatorPage.CalculateButton.Name"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return this;
	}
	
	
}
