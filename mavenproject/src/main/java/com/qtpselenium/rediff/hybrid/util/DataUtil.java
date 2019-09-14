package com.qtpselenium.rediff.hybrid.util;

import java.util.Hashtable;

public class DataUtil {
	
	public static Object[][] gettestdata(String Testname, Xls_Reader xls){
	
		//
		int teststartrownum=1;
		while(!xls.getCellData(Constants.DATA_SHEET, 0, teststartrownum).equalsIgnoreCase(Testname)){
			teststartrownum++;
			//System.out.println("yes it is equal");
			
		}
		System.out.println("Row Number of Testcases:"+teststartrownum);
		
		//to find the total number of columns in a testcase
		int teststartcolnum=teststartrownum+1;
		int colnum=0;
		while(!xls.getCellData(Constants.DATA_SHEET, colnum, teststartcolnum).equals("")){
			colnum++;
		}
		System.out.println("Total Number of Columns: "+colnum);
		
		//to find total number of rows in testcase
		int datastart_rownum=teststartrownum+2;
		int totaldatarow=0;
		while(!xls.getCellData(Constants.DATA_SHEET, 0, datastart_rownum).equals("")){
			totaldatarow++;
			datastart_rownum++;
			
		}
		System.out.println("Total number of data rows :  "+totaldatarow);
	
		//Read all data
	datastart_rownum=teststartrownum+2;
	System.out.println(datastart_rownum);
	int final_rows=datastart_rownum+totaldatarow;
	System.out.println(final_rows);
	
	Hashtable<String, String> table=null;
	Object[][] mydata=new Object[totaldatarow][1];
	int i=0;
	
	for(int row_num=datastart_rownum;row_num<final_rows; row_num++){
		table=new Hashtable<String, String>();//every row new Hahtable should be created
		for(int start_colnum=0;start_colnum<colnum;start_colnum++){//putt data into the hashtable
			System.out.println("success");
			String get_data=xls.getCellData(Constants.DATA_SHEET, start_colnum, row_num);	
			String key=xls.getCellData(Constants.DATA_SHEET, start_colnum, teststartcolnum);
			//System.out.println(key+"---"+get_data);
			table.put(key, get_data);
		}
		//dfdf
		System.out.println("Values in HashTable are :"+table);
		mydata[i][0]=table;
		i++;
		System.out.println("---------------------------------");
	}	
	
	
	//
		
		//to find the row num of the testcases
	return mydata;

	}
	
	//function to check the runmode of the test
	//true - N
	//False - Y
	public static boolean isSkip(String Testname, Xls_Reader xls){
		
		int rows=xls.getRowCount(Constants.TESTCASES_SHEET);
		
		for(int rNum=2;rNum<rows;rNum++){
			String tcid=xls.getCellData(Constants.TESTCASES_SHEET, Constants.TCID_COL, rNum);
				if(tcid.equalsIgnoreCase(Testname)){//testcase found
					String runmode=xls.getCellData(Constants.TESTCASES_SHEET, Constants.RUNMODE_COL, rNum);
					if(runmode.equals(Constants.RUNMODE_NO))
						return true;	
					else
						return false;
				}
				
					
		}
		
		return true;
	}

}
