package wapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import utils.Reporter;

public class GenericWapper {

	protected static RemoteWebDriver driver;
	protected static Properties prop;
	protected static Select dropdown;
	String url;
	
	public GenericWapper(){
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(new File("./config.properties")));
			url=prop.getProperty("URL");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method will launch both firefox and chrome according to the testcase and 
	 * maximize the browser and set the wait for 30 seconds and load the url
	 * @author Karthikeyan Rajendran
	 * @param url - The url with http or https
	 * 
	 */	
	public void invokeApp(String browser){
		
		try {
			if(browser.equalsIgnoreCase("firefox")){
				driver = new FirefoxDriver();
			} else if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", "./driver/chromedriver");
				driver = new ChromeDriver();
			}
			
			driver.get(url);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			Reporter.reportStep("The Application launched in " + browser + " successfully", "PASS");
			
		} catch (WebDriverException e){
			System.out.println("WebDriver Exception"+e.getMessage());
		}
		
	}
	
	/**
	 * This method will enter the value to the text field using id attribute to locate
	 * 
	 * @param name - name of the webelement
	 * @param enter - The data to be sent to the webelement
	 * @author Karthikeyan Rajendran
	 * @throws NoSuchElementException 
	 * @throws WebDriverException 
	 */	
	public void enterByName(String name,String enter){
		
		try {
			driver.findElementByName(name).sendKeys(enter);
			Reporter.reportStep("Successfully Enter Value into the field", "PASS");
		} catch (NoSuchElementException e) {			
			System.out.println("No such element is found at the Name" + name);			
		}catch (WebDriverException e){
			System.out.println("WebDriver Exception"+e.getMessage());
		}
	}
	
	/**
	 * This method will click the element using name as locator
	 * @param name  The name (locator) of the element to be clicked
	 * @author Karthikeyan Rajendran
	 * @throws NoSuchElementException
	 * @throws WebDriverException
	 */	
	public void clickByName(String name){
		
		try {
			driver.findElementByName(name).click();
			Reporter.reportStep("Successfully click the button", "PASS");
		} catch (NoSuchElementException e) {			
			System.out.println("No such element is found at the Name" + name);			
		}catch (WebDriverException e){
			System.out.println("WebDriver Exception"+e.getMessage());
		}
	}
	
	/**
	 * This method will get the content of the tag using xpath
	 * @param name  The name (locator) of the element to be clicked
	 * @author Karthikeyan Rajendran
	 * @throws NoSuchElementException
	 * @throws WebDriverException
	 * @return this method return string value
	 */	
	public String outputValue(String xpathVal){
		String oValue = null;
		try {
			oValue = driver.findElementByXPath(xpathVal).getText();
		} catch (NoSuchElementException e) {			
			System.out.println("No such element is found at the Name" + xpathVal);			
		}catch (WebDriverException e){
			System.out.println("WebDriver Exception"+e.getMessage());
		}
		
		return oValue;
	}
	
	/**
	 * This method verify the url of the application
	 * @param eUrl - The expected application url
	 * @author Karthikeyan Rajendran
	 * @throws WebDriverException
	 * @return boolean
	 */	
	public boolean verifyUrl(String eUrl){
		boolean bReturn = false;
		try {
			if(driver.getCurrentUrl().equalsIgnoreCase(eUrl)){
				bReturn = true;
			}
		}catch (WebDriverException e){
			System.out.println("WebDriver Exception"+e.getMessage());
		}
		
		return bReturn;
	}
	
	/**
	 * This method will select the drop down value using name as locator
	 * @param name The name (locator) of the drop down element
	 * @param value The value to be selected (visibletext) from the dropdown
	 * @author Karthikeyan Rajendran
	 * @throws WebDriverException
	 */	
	public void selectByNameVisibleText(String name,String value){
		
		try {
			dropdown = new Select(driver.findElementByName(name));
			dropdown.selectByVisibleText(value);
		} catch (NoSuchElementException e) {			
			System.out.println("No such element is found at the Name" + name);			
		}catch (WebDriverException e){
			System.out.println("WebDriver Exception"+e.getMessage());
		}
		
	}
	
	/**
	 * This method will select the drop down value using name as locator
	 * @param name The name (locator) of the drop down element
	 * @param value The value to be selected by index from the dropdown
	 * @author Karthikeyan Rajendran
	 * @throws WebDriverException
	 * @return boolean
	 */	
	public void selectByNameIndex(String name,int value){
		
		try {
			dropdown = new Select(driver.findElementByName(name));
			dropdown.selectByIndex(value);
		} catch (NoSuchElementException e) {			
			System.out.println("No such element is found at the Name" + name);			
		}catch (WebDriverException e){
			System.out.println("WebDriver Exception"+e.getMessage());
		}
		
	}
	
	/**
	 * This method will close current open browser
	 * @author Karthikeyan Rajendran
	 * @throws WebDriverException
	 */
	public void closeBrowser(){
		try {
			driver.close();
		} catch (WebDriverException e){
			System.out.println("WebDriver Exception"+e.getMessage());
		}
	}
	
	public void loadObjects() throws FileNotFoundException, IOException{
		prop = new Properties();
		prop.load(new FileInputStream(new File("./object.properties")));
	}
	
	/**
	 * This method will verify the given text
	 * @param xpath - The locator of the object in xpath
	 * @param text  - The text to be verified
	 * @author Karthikeyan Rajendran
	 */
	public boolean verifyTextByXpath(String xpath, String text){
		
		boolean bReturn = false;
		
		String sText = driver.findElementByXPath(xpath).getText();
		if (driver.findElementByXPath(xpath).getText().trim().equalsIgnoreCase(text)){
			Reporter.reportStep("The text: "+sText+" matches with the value :"+text, "PASS");
            bReturn = true;		
		}else{
			Reporter.reportStep("The text: "+sText+" did not match with the value :"+text, "FAIL");
		}
		return bReturn;
	}
	
}
