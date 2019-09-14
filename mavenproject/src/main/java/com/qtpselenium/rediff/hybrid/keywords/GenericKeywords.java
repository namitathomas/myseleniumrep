/**
 * 
 */
package com.qtpselenium.rediff.hybrid.keywords;


import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qtpselenium.rediff.hybrid.reports.ExtentManager;

/**
 * @author Thomas Namita
 *
 */
public class GenericKeywords {
	
	
	
	
	public WebDriver driver;
	public Properties prop;
	public Properties envprop;
	public String Objctkey;
	public String datakey;
	public String proceedonfail;
	public SoftAssert softassert=new SoftAssert();
	
	
	


	


	public Hashtable<String, String> data;
	public ExtentTest test;
	
	/*************************Setter Functions******************/


	public void setProp(Properties prop) {
		this.prop = prop;
	}

	public void setEnvprop(Properties envprop) {
		this.envprop = envprop;
	}

	public void setObjctkey(String Objctkey) {
		this.Objctkey = Objctkey;
	}

	public void setDatakey(String datakey) {
		this.datakey = datakey;
		
	}

	public void setData(Hashtable<String, String> data) {
		this.data = data;
	}
	
	
	public void setExtentTest(ExtentTest test) {
		this.test = test;
	}
	
	public String getProceedonfail() {
		return proceedonfail;
	}

	public void setProceedonfail(String proceedonfail) {
		this.proceedonfail = proceedonfail;
	}
	
	public void namita(){
		System.out.println("NAMITA...");
	}
	
	public void testing(){
		System.out.println("test success");
	}
	
	public void openmozilla(){
		System.out.println("Keywords working successfully");
		//System.setProperty("webdriver.gecko.driver","C:\\Users\\thomas namita\\Desktop\\SELENIUM\\geckodriver\\geckodriver.exe");
		
		//No Logs needed
	//	System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "null");
		
		//driver =new FirefoxDriver();
		//System.out.println("Mozilla opening...");
	}
	
	public void openMozillaBrowser(){
		System.out.println("Keywords working successfully");
		System.setProperty("webdriver.gecko.driver","C:\\Users\\thomas namita\\Desktop\\SELENIUM\\geckodriver\\geckodriver.exe");
		
		//No Logs needed
		System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "null");
		
		driver =new FirefoxDriver();
		System.out.println("Mozilla opening...");
	}
	
	
	//Code for OPENING a BROWSER
	public void openBrowser(){
			System.out.println("*************************************");
			System.out.println("**********GENERIC CLASS IMPLEMNETED*************");
			
			
			String browser=data.get(datakey);
						
						if(browser.equals("Mozilla")){
							//gecko
							System.setProperty("webdriver.gecko.driver","C:\\SELENIUM PROJECTS\\DOWNLOADS\\gecko driver\\geckodriver.exe");
							
							//No Logs needed
							System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "null");
							
							driver =new FirefoxDriver();
							
							test.log(Status.INFO, "OPENING BROWSER :" + browser);
						}
						else if(browser.equals("Chrome")){
							System.setProperty("webdriver.chrome.driver", "C:\\Users\\thomas namita\\Desktop\\SELENIUM\\CHROME DRIVER\\chromedriver.exe");
							driver=new ChromeDriver();
							System.out.println("Browser is CHROME");
						}
						else
							System.out.println("NONE");
					
			System.out.println("Opening Browser:  "+browser);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.manage().window().maximize();
						
						//
						// System.out.println("KEYWORD CLASS:  "+data);
						// System.out.println("KEYWORD CLASS DATA KEY:  "+datakey);
						// System.out.println("KEYWORD CLASS DATA is : "+data.get(datakey));
					}
					
	//Code for NAVIGATING to a PAGE
	public void navigate(){
		System.out.println("Navigating to Website : " + envprop.getProperty(Objctkey));
		test.log(Status.INFO, "Navigating to new page");
		driver.get(envprop.getProperty(Objctkey));
		
	}
	
	//wait code
	public void waiting() throws InterruptedException{
		test.log(Status.INFO, "WAITING FOR 3 Seconds");
		Thread.sleep(3000);
	}
	
	
	public void wait(int timesec){
		test.log(Status.INFO, "Waiting for some time");
		try {
			Thread.sleep(timesec*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//Code for CLICKING an ELEMENT
	public void click(){
		test.log(Status.INFO, "CLICKING ON AN ELEMENT");
		System.out.println("Clicking an element :" + prop.getProperty(Objctkey));
		//driver.findElement(By.name(prop.getProperty(Objctkey))).click();
		getObject(Objctkey).click();
	}
	
	
	public void click(String Objctkey){
		setObjctkey(Objctkey);
		
		click();
	}
	
	
	
	public void select(){
		//validate if expected dropdown is selected
		
		if(!isElementInList()){
			reportFailure("Option is not in the List"+ data.get(datakey));
		}
			Select sel_option=new Select(getObject(Objctkey));
			sel_option.selectByVisibleText(data.get(datakey));			
		
	}
	
	//Code for TYPING a TEXT
	public void type(){
		test.log(Status.INFO, "TYPING AN ELEMENT");
		
		getObject(Objctkey).sendKeys(data.get(datakey));
	}
	
	public void type(String Objctkey, String datakey){
		setObjctkey(Objctkey);
		setDatakey(datakey);
		type();
	}
	
	
	//HIT ENTER
	public void hit_enter(){
		test.log(Status.INFO, "Press ENTER...");
		getObject(Objctkey).sendKeys(Keys.ENTER);
	}
	
	
	
	//Code for Validating LOGIN
	//public void validateLogin(){
	//	System.out.println("Validating Login: ");
	//}
	
	//Code For Validating the PAGE TITLE
	/*public void ValidateTitle(){
		test.log(Status.INFO, "VALIDATING PAGE TITLE");
		System.out.println("Validating Title: "+prop.getProperty(Objctkey));
		String ExpectedTitle=prop.getProperty(Objctkey);
		String ActualTitle=driver.getTitle();
		System.out.println("Actual Title is:  "+ActualTitle);
		if(!ActualTitle.equals(ExpectedTitle)){
			
			System.out.println("Report an Error");
			
			reportFailure("Title doesnot match.Actual Title was :" +ActualTitle);
		}
		
	}*/
	
	public void validateElementisNotInList(){
		test.log(Status.INFO, "Validating Element is not in List");
		System.out.println("Validating Element is not in List");
		
		if(isElementInList()){
			reportFailure("Couldnot delete from List");
		}
		
	}
	
	public void validateElementPresent(){
		test.log(Status.INFO, "VALIDATING ELEMENT PRESENT");
		System.out.println("validate Element present");
		if(!isElementPresent(Objctkey)){
			//Report a failure
			reportFailure("Element not FOUND: "+Objctkey);
		}
	}
	
	public void validateElementNotInList(){
		System.out.println("Validate Element Not in List Executing....");
	}
	
	
	
	
	public void acceptalert() {
		// TODO Auto-generated method stub
		test.log(Status.INFO, "Switching to Alert");
		try{
		driver.switchTo().alert().accept();
		driver.switchTo().defaultContent();
		test.log(Status.INFO, "Alert accepted successfully");
		}catch(Exception e){
			test.log(Status.INFO, "Alert NOT PRESENT");
			if(Objctkey.equals("Y")){
				reportFailure("Alert is mandatory but is not present");
			}
		}
	}
	
	//wait till selection 
	public void waitTillSelectionToBe(String objectkey , String expected) {
		int i=0;
		String actual="";
		while(i!=10){
			WebElement e = getObject(objectkey);
			Select s = new Select(e);
		    actual = s.getFirstSelectedOption().getText();
			if(actual.equals(expected))
				return;
			else
				wait(1);			
				i++;	
		}
	reportFailure("Values donot match "+actual);
	
	}
	
	//wait for page to load
		public void waitForPageToLoad() {
			JavascriptExecutor js = (JavascriptExecutor)driver;
			int i=0;
			
			while(i!=10){
			String state = (String)js.executeScript("return document.readyState;");
			System.out.println(state);

			if(state.equals("complete"))
				break;
			else
					wait(2);
			i++;
			}
			// check for jquery status
			i=0;
			while(i!=10){
		
				Long d= (Long) js.executeScript("return jQuery.active;");
				System.out.println(d);
				if(d.longValue() == 0 )
				 	break;
				else{
						wait(2);
				}
				 i++;
					
				}
			
			}
	
	
	
	public void quit(){
		if (driver!=null){
			driver.quit();
		}
	}
	
	//*******************UTILITY FUNCTIONS*****************************

	//find an Object
		public WebElement getObject(String Objctkey){
			WebElement e=null;
			try{
			if(Objctkey.endsWith("_xpath")){	
			e=driver.findElement(By.xpath(prop.getProperty(Objctkey)));
			}
			else if (Objctkey.endsWith("_css")) {
				e=driver.findElement(By.cssSelector(prop.getProperty(Objctkey)));
			}
			else if (Objctkey.endsWith("_name")) {
				e=driver.findElement(By.name(prop.getProperty(Objctkey)));
			}
			
			else if (Objctkey.endsWith("_id")) {
				e=driver.findElement(By.id(prop.getProperty(Objctkey)));
			}
			
			
			
			WebDriverWait wait=new WebDriverWait(driver, 20);
			//Visibility of Object
			wait.until(ExpectedConditions.visibilityOf(e));
			//State of Object
			wait.until(ExpectedConditions.elementToBeClickable(e));
			
			}catch(Exception ex){
				//Report the failure
				reportFailure("Object Not Found"+Objctkey);
			//System.out.println(ex.printStackTrace());
			}
			return e;
	
	}
	
		
	//To find if an element is present or not
	public boolean isElementPresent(String Objctkey ){
		List <WebElement> list=null;
		
		if(Objctkey.endsWith("_xpath")){	
			list=driver.findElements(By.xpath(prop.getProperty(Objctkey)));
			}
		else if (Objctkey.endsWith("_css")) {
				list=driver.findElements(By.cssSelector(prop.getProperty(Objctkey)));
			}
		else if (Objctkey.endsWith("_name")) {
				list=driver.findElements(By.name(prop.getProperty(Objctkey)));
			}
			
		else if (Objctkey.endsWith("_id")) {
				list=driver.findElements(By.id(prop.getProperty(Objctkey)));
			}
	
		
							if(list.size()==0){
								return false;
							}
							
							else{
								return true;
							}
		
			
		
	}
		
	
	public boolean isElementInList(){
		Select project_drpdwn=new Select(getObject(Objctkey));
		List<WebElement> opt = project_drpdwn.getOptions();
		System.out.println("OPTIONS size: "+opt.size());
		
		
		for(int i=0;i<opt.size();i++){
			System.out.println(opt.get(i).getText());
			if(opt.get(i).getText().equals(data.get(datakey))){
				System.out.println("Expected option exists in dropdown");		
				return true;
			}
			
			}
		return false;
	}
		
		/*********************REPORTING A FAILURE*************************/
		
		public void reportFailure(String Failure_msg){
			//Fail the Test
			//Take Screenshots, embed screenshots in Reports
			takescreenshots();
			//System.out.println("Inside Report failure function");
			test.log(Status.FAIL, Failure_msg);
			//Assert.fail(Failure_msg);//stop on this line
			if(proceedonfail.equals("Y")){
				softassert.fail(Failure_msg);
			}
			else{
				softassert.fail(Failure_msg);
				softassert.assertAll();
			}
			
		}
		
		public void assertall(){
		softassert.assertAll();
		}
	
		
		public void takescreenshots(){
			// fileName of the screenshot
			Date d=new Date();
			String screenshotFile=d.toString().replace(":", "_").replace(" ", "_")+".png";
			// take screenshot
			File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			try {
				// get the dynamic folder name
				FileUtils.copyFile(srcFile, new File(ExtentManager.screenshotFolderPath+screenshotFile));
				//put screenshot file in reports
				test.log(Status.INFO,"Screenshot-> "+ test.addScreenCaptureFromPath(ExtentManager.screenshotFolderPath+screenshotFile));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
}
	
