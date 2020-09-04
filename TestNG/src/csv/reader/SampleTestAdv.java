package csv.reader;

import org.testng.annotations.Test;

import dro.ZipPairDRO;

public class SampleTestAdv{

	
	/*The purpose of these package is to understand how data is setup for DataProvider Class
	 * test method declares that its data should be supplied by the DataProvider named "getdata"
	 * Here the data is converted to an object and passed
	 */ 
	@Test(dataProvider="getData", dataProviderClass=DP.class)
	public void setData(ZipPairDRO dro)
	{
	System.out.println("your key is::"+dro.getKey());
	System.out.println("your description is::"+dro.getDescription());
	System.out.println("your inputpair is::"+dro.getInput_zips());
	System.out.println("your outputpair is::"+dro.getExpected_zips());
	}
	

    
}