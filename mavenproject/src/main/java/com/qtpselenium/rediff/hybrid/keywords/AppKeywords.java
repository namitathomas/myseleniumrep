package com.qtpselenium.rediff.hybrid.keywords;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

public class AppKeywords extends GenericKeywords{
	
	
	//App specific function for LOGIN
	public void login(){
		//login code
		test.log(Status.INFO, "EXECUTING LOGIN FUNCTION");
		System.out.println("LOGIN >>>>FUNCTION EXECUTING");
		
		//codedsds
		
		String username="";
		String password="";
		
		
		if(data.get("Username")==null){
			username=envprop.getProperty("defaultusername");
			password=envprop.getProperty("defaultpassword");	
		}
		else{
			username=data.get("Username");
			password=data.get("Password");
			
		}
		
		
		System.out.println(username);
		getObject("emailidfield_name").sendKeys(username);
		getObject("passwordfield_name").sendKeys(password);
		getObject("submitbutton_name").click();
		
		WebDriverWait wait=new WebDriverWait(driver, 10);
		//wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.name(prop.getProperty("submitbutton_name")))));
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.name(prop.getProperty("submitbutton_name"))));
		
		//System.out.println("WAIT IS OVER");
	}

	//application specific function
	public void validatetitle(){
		System.out.println("Validating Title");
		
		
	}
	
	//validate login
	public void validateLogin(){
		test.log(Status.INFO, "VALIDATING LOGIN");
		System.out.println("VALIDATE LOGIN >>>>FUNCTION EXECUTING");
		
		boolean result=isElementPresent("homepage_xpath");
		System.out.println("RESULT---"+result);
		
		String expected_result=data.get("ExpectedResult");
		String actualresult="";
		
		if(result){
			actualresult="LoginSuccess";
			System.out.println(actualresult);
		}
		else{
			
			actualresult="Loginfailure";
			System.out.println(actualresult);
		}
		
		if(!actualresult.equals(expected_result)){
			reportFailure("Login was not Successfull, Actual result :"+actualresult);
		}
		
		
	}
	
	public void defaultlogin(){
		test.log(Status.INFO, "Running Default Login Test");
		String test_username=envprop.getProperty("adminusername");
		String test_password=envprop.getProperty("adminpassword");
		System.out.println("Default Login Credentials: "+test_username+"and "+test_password);
	}
	
	public void testcresconnect() throws InterruptedException{
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='tasks3_chosen']/a/span")).click();
		
		driver.findElement(By.xpath("/html/body/div/div/div/div/div[2]/div/div[1]/div/table/tbody/tr/td/form[1]/table[2]/tbody/tr[6]/td/div/div/table/tbody/tr/td[2]/span/div/div/div/input")).sendKeys("Testing");
		driver.findElement(By.xpath("/html/body/div/div/div/div/div[2]/div/div[1]/div/table/tbody/tr/td/form[1]/table[2]/tbody/tr[6]/td/div/div/table/tbody/tr/td[2]/span/div/div/div/input")).sendKeys(Keys.ENTER);
		
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div/div/div/div/div[2]/div/div[1]/div/table/tbody/tr/td/form[1]/table[2]/tbody/tr[6]/td/div/div/table/tbody/tr/td[3]/span/div/a/span")).click();
		
		driver.findElement(By.xpath("/html/body/div/div/div/div/div[2]/div/div[1]/div/table/tbody/tr/td/form[1]/table[2]/tbody/tr[6]/td/div/div/table/tbody/tr/td[3]/span/div/div/div/input")).sendKeys("Nidhi");
		driver.findElement(By.xpath("/html/body/div/div/div/div/div[2]/div/div[1]/div/table/tbody/tr/td/form[1]/table[2]/tbody/tr[6]/td/div/div/table/tbody/tr/td[3]/span/div/div/div/input")).sendKeys(Keys.ENTER);
		
		
		driver.findElement(By.xpath("//*[@id='submitsave']")).click();
		
		//System.out.println(driver.switchTo().alert().getText());
		
		Thread.sleep(2000);
		//
		
		acceptalert();
		
		
		//waiting
       //wait_time(3);
		
        waitForPageToLoad();
		
		//driver.findElement(By.xpath("/html/body/div/div/div/div/div[2]/div/div[1]/div/table/tbody/tr/td/form[1]/table[2]/tbody/tr[6]/td[5]/a")).click();
	}
	
	public void gotoleagesubmitpage(){
		Actions act=new Actions(driver);
		WebElement myspace_element=driver.findElement(By.xpath("//*[@id='mainMenu']/li[4]/a"));
		act.moveToElement(myspace_element).perform();
		System.out.println("Mouse over : MySapce Main Menu");
		driver.findElement(By.xpath("//*[@id='mainMenu']/li[4]/ul/li[7]/a")).click();
		
		waitForPageToLoad();
		click("applyleave_xpath");
		
		System.out.println("ON APPLY LEAVE PAGE..");
		click("projectdropdown_xpath");
		click("options_project_xpath");
		wait(3);
		click("approverdropdown_xpath");
		click("options_approver_xpath");
		
		WebDriverWait waitingnow=new WebDriverWait(driver, 10);
		waitingnow.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='form1']/div[2]/div[1]/button/i")));
		
		
		click("leaverequiredfrom_xpath");
		click("highlighteddate_xpath");
		//selectDate(data.get("ToDate"));
		
		wait(10);
		click("leaverequiredto_xpath");
		selectDate(data.get("ToDate"));
		
		wait(10);
		click("typeofleave_xpath");
		click("option_typeofleave_xpath");
		
		type("leavepurpose_xpath", data.get("leavepurpose"));
		type("remarks_xpath", data.get("remarks"));
		
		
		
	}

	//select date function
	public void selectDate(String d){
		// day, month , year
		Date current = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date selected = sd.parse(d);
			String day = new SimpleDateFormat("d").format(selected);
			String month = new SimpleDateFormat("MMMM").format(selected);
			String year = new SimpleDateFormat("yyyy").format(selected);
			System.out.println(day+" --- "+month+" --- "+ year);
			String desiredMonthYear=month+" "+year;
			
			while(true){
				String displayedMonth=driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/span[1]")).getText();
				String displayedYear=driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/span[2]")).getText();
				
				String displayedMonthYear=displayedMonth+" "+displayedYear;
				System.out.println("Displayed month and Year is :"+displayedMonthYear);
				
				if(desiredMonthYear.equals(displayedMonthYear)){
					// select the day
					driver.findElement(By.xpath("//a[text()='"+day+"']")).click();
					System.out.println("if loop executed");
					break;
				}else{
					System.out.println("elese loop excuted");
					if(selected.compareTo(current) > 0)
						driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
					else if(selected.compareTo(current) < 0)
					driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[1]/span")).click();
//
				}
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void verifyprojectdropdown(){
		
		test.log(Status.INFO, "Verifying the Dropdown Value"+data.get(datakey));
		System.out.println("Verifying the dropdown value");
		
		waitTillSelectionToBe("aftersaving_projectthirdrow_xpath", data.get(datakey));
		/*
		Select s=new Select(getObject("aftersaving_projectthirdrow_xpath"));
		String projecttext=s.getFirstSelectedOption().getText();
		System.out.println(projecttext);
		if(!projecttext.equals(data.get(datakey))){
			System.out.println("Doesnot match text");
			reportFailure("Reporting Failure: Text doesnot match");
		}else{
			System.out.println("SUCCESS : project name matches");
		}
		*/
	}
	
	
	
	
}
