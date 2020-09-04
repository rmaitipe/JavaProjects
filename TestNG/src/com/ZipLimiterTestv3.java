package com;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;

import dro.ZipPairDRO;

/*
This v3 class is to Unit test the ZipLimiter class. 
*/
public class ZipLimiterTestv3 {
	
	/*
	* These are the values to be compared against the data read from files in test case scenarios.
	* Called automatically before the Test class is run.
	*/
 	@BeforeClass
    public void setUp() {
    	System.out.println("@Before - setUp");
    }
    
    @AfterClass
    public void tearDown() {
        System.out.println("@After - tearDown");
    }
    
	@DataProvider
	public ZipPairDRO[] getData() throws IOException
	{
		//Rows - Number of times your test has to be repeated.
		//Columns - Number of parameters in test data.
		ZipPairDRO[] list=new ZipPairDRO[1];
		CSVReader csvReader = new CSVReader(new FileReader("C://Users/rmait/git/JavaProjects/TestNG/resources/test/v3/completev3.csv"), ',');
		String[] csvData =null;
		while ((csvData =csvReader.readNext()) !=null) {
	    	for (int i=0;i<1;i++) {
	    		list[i] =new ZipPairDRO(csvData[0],csvData[1],csvData[2],csvData[3]);
	    	}
	    }
	return list;
	}
    
    /*
    * Runs test cases for different scenarios comparing size of the lists and content of the lists
    * Test cases: No Merge, Sorted Merge, UnSorted Merge, Merge With BadData
    *
    */
    @Test(dataProvider = "getData")
	public void zipLimiterDataTest(ZipPairDRO dro) {
		ZipLimiter zip = new ZipLimiter();
		List<Pair> test1 = zip.testv3(dro.getInput_zips());
		Assert.assertEquals(test1.toString().trim(),dro.getExpected_zips().trim(), "failure - expected result content match!");
	} 	
}
