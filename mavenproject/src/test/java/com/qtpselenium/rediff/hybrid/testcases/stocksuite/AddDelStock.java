package com.qtpselenium.rediff.hybrid.testcases.stocksuite;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.qtpselenium.rediff.hybrid.base.BaseTest;
import com.qtpselenium.rediff.hybrid.util.Constants;
import com.qtpselenium.rediff.hybrid.util.DataUtil;

public class AddDelStock extends BaseTest {
	
	@Test(dataProvider="get_data")
	public void addstocktest(Hashtable<String, String> data) throws Exception{
		test.log(Status.INFO, "STARTING TEST :"+Testname);
		System.out.println("STARTING ADD STOCK TEST....");
		
		//code to skip
		if(DataUtil.isSkip(Testname, xls)||data.get(Constants.RUNMODE_COL).equals(Constants.RUNMODE_NO)){ // 
			test.log(Status.SKIP, "Runmode is SET to N");
			throw new SkipException("Runmode is N");
		}
		System.out.println("CREATE TEST EXECUTING....");
		ds.ExecuteKeywords(Testname, xls, data);
		
		
	}

	
	
	@Test
	public void deletestocktest(){
		
		System.out.println("DELETE STOCK EXECUTING....");
		
	}
}
