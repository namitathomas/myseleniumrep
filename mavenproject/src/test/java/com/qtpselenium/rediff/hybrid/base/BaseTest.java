package com.qtpselenium.rediff.hybrid.base;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Properties;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.qtpselenium.rediff.hybrid.driver.DriverScript;
import com.qtpselenium.rediff.hybrid.reports.ExtentManager;
import com.qtpselenium.rediff.hybrid.util.DataUtil;
import com.qtpselenium.rediff.hybrid.util.Xls_Reader;

public class BaseTest {
	public Properties prop;
	public Properties envprop;
	//public Properties prodprop;//Prod properties
	public Xls_Reader xls;
	public String Testname;
	public DriverScript ds;
	public ExtentReports rep;
	public ExtentTest test;
	
	//public DataUtil 
	
	
	@BeforeTest
	public void init(){
		
		//init test name
		//System.out.println("****"+this.getClass().getSimpleName());
		//Testname=this.getClass().getSimpleName();
		//properties file
		System.out.println("Before running the test- TEST1");
		
		String package_name=this.getClass().getPackage().getName();
		System.out.println(package_name);
		
		String arr[]=this.getClass().getPackage().getName().split("\\.");
		System.out.println(arr[2]);
		System.out.println(arr.length-1);
		System.out.println("Package name ends with : "+arr[arr.length-1]);
		String suitename=arr[arr.length-1];
		System.out.println("SUITE NAME IS :"+suitename);
		
		
		
		prop=new Properties();
		try {
			FileInputStream fs=new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//env.properties");
			//System.out.println(System.getProperty("user.dir"));
			prop.load(fs);
			System.out.println(prop.getProperty("env"));
			
			String env=prop.getProperty("env");
			
			envprop=new Properties();
			fs=new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//"+env+".properties");
			envprop.load(fs);
			System.out.println(envprop.getProperty("url"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//System.out.println(envprop.getProperty("suitea_xls"));
		String excel_path=envprop.getProperty("portfoliosuite_xls");
		System.out.println("SINGLE SLASH EXCEL PATH"+excel_path);
		//System.out.println(excel_path.replaceAll("\\", "\\"));

				
		
		
		xls=new Xls_Reader("C:\\SELENIUM PROJECTS\\mavenproject\\Files\\StockSuite.xlsx");
		//"C:\Users\thomas namita\Desktop\SELENIUM\w_hybrid_testdata\suitea.xlsx"
		//xls=new Xls_Reader("C:\Users\thomas namita\Desktop\SELENIUM\w_hybrid_testdata\suitea.xlsx");
		//System.out.println("EXCEL NAME IS : "+portfoliosuite_xls+"_xls");
		//xls=new Xls_Reader("excel_path");
		
		//String cell_data=xls.getCellData("Data", 0, 7);
		//System.out.println(cell_data);
		
		
		ds=new DriverScript();
		ds.setEnvprop(envprop);
		ds.setProp(prop);
		
		
	}
	
	
	@BeforeMethod
	public void inittest(){
		rep=ExtentManager.getInstance(prop.getProperty("reportpath"));
		System.out.println("BEFORE METHOD");
		test=rep.createTest(Testname);
		ds.setExtentTest(test);
		//
	}
	
	@AfterMethod
	public void quit(){
		//quit the browser
		if(ds!=null)
		ds.quit();			
		
		if(rep!=null)
			rep.flush();
	}
	@DataProvider
	public Object[][] get_data(Method method){  //Method method
		System.out.println("inside the data provider : "+method.getName());
		
		// Xls_Reader xls=new Xls_Reader("C:\\Users\\thomas namita\\Desktop\\SELENIUM\\w_hybrid_testdata\\SuiteA.xlsx");
	
	Testname=method.getName();
		
	return DataUtil.gettestdata(Testname, xls);	
	}
	
	
}


  