package com.qtpselenium.rediff.hybrid.driver;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Properties;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qtpselenium.rediff.hybrid.keywords.AppKeywords;
import com.qtpselenium.rediff.hybrid.util.Constants;
import com.qtpselenium.rediff.hybrid.util.Xls_Reader;


public class DriverScript {
	
	public Properties prop;
	public Properties envprop;//create variables
	AppKeywords app;
	public ExtentTest test;
	
	

	public void ExecuteKeywords(String Testname, Xls_Reader xls, Hashtable<String, String> testdata) throws Exception{
		int total_rows=xls.getRowCount(Constants.KEYWORDS_SHEET);
		System.out.println("Total number of rows in KEYWORD Sheet:"+total_rows);
		
		app=new AppKeywords();
		
		//sending properties to keyword class
		app.setProp(prop);
		app.setEnvprop(envprop);
		
		//sending data to keyword class
		app.setData(testdata);
		app.setExtentTest(test);
		
		for(int rowNum=2;rowNum<total_rows;rowNum++){
		String tcid=xls.getCellData(Constants.KEYWORDS_SHEET, Constants.TCID_COL, rowNum);
		if(tcid.equals(Testname)){
		String key_wrd=xls.getCellData(Constants.KEYWORDS_SHEET, Constants.KEYWORD_COL, rowNum);
		String Objctkey=xls.getCellData(Constants.KEYWORDS_SHEET, Constants.OBJECT_COL, rowNum);
		String datakey=xls.getCellData(Constants.KEYWORDS_SHEET, Constants.DATA_COL, rowNum);
		String proceedonfail=xls.getCellData(Constants.KEYWORDS_SHEET, Constants.PROCEED_ON_FAIL, rowNum);
		
		//sending Object key and Datakey to Keywords class
		app.setDatakey(datakey);
		app.setObjctkey(Objctkey);
		app.setProceedonfail(proceedonfail);
		
		
		String data=testdata.get(datakey);
		//test.log(Status.INFO, tcid+"---------"+key_wrd+"-------"+Objctkey+"------------"+prop.getProperty(Objctkey)+"-----------"+datakey+"-----"+data);
	    //System.out.println("DATA is : "+testdata);
		
		//System.out.println(tcid+"---------"+key_wrd+"-------"+Objctkey+"------------"+prop.getProperty(Objctkey)+"-----------"+datakey+"-----"+data);
		
		/*
		if(key_wrd.equals("openBrowser")){
			app.openBrowser();
		}
		else if (key_wrd.equals("navigate")) {
			app.navigate();
			
		}
		else if (key_wrd.equals("click")) {
			app.click();
			
		}
		//type
		
		else if (key_wrd.equals("type")) {
		app.type();
			
		}*/
		
		Method method;
		
		method=app.getClass().getMethod(key_wrd);
		method.invoke(app);
		
		
		
		
		}
		
		}
		app.assertall();
		
	}



	public Properties getProp() {
		return prop;
	}



	public void setProp(Properties prop) {
		this.prop = prop;
	}



	public Properties getEnvprop() {
		return envprop;
	}



	public void setEnvprop(Properties envprop) {
		this.envprop = envprop;
	}

	public void setExtentTest(ExtentTest test) {
		this.test = test;
	}
	
	
	public void quit(){	
		if(app!=null){
		app.quit();
		}
	}
	
	

	
	
	
}
