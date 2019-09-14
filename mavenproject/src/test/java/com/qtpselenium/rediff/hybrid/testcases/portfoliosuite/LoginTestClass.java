package com.qtpselenium.rediff.hybrid.testcases.portfoliosuite;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.qtpselenium.rediff.hybrid.base.BaseTest;
import com.qtpselenium.rediff.hybrid.util.Constants;
import com.qtpselenium.rediff.hybrid.util.DataUtil;

public class LoginTestClass extends BaseTest{
	
	//dry run
	@Test(dataProvider="get_data")
	public void LoginTest(Hashtable<String, String> data) throws Exception{
		
		test.log(Status.INFO, "STARTING TEST :"+Testname);
		System.out.println("LOGIN TEST EXECUTING");
		//test.log(Status.INFO, data.toString());
		
		if(DataUtil.isSkip(Testname, xls)||data.get(Constants.RUNMODE_COL).equals(Constants.RUNMODE_NO)){ // 
			test.log(Status.SKIP, "Runmode is SET to N");
			throw new SkipException("Runmode is N");
			
			//System.out.println(data);
			
	}
		System.out.println("Running Login Test");
		ds.ExecuteKeywords(Testname, xls, data);
		
		
	}

}
