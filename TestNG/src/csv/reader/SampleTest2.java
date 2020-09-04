package csv.reader;

import java.io.FileReader;
import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;

import dro.ZipPairDRO;

public class SampleTest2{

	
	/*The purpose of these package is to understand how data is setup for DataProvider Class
	 * test method declares that its data should be supplied by the DataProvider named "getdata"
	 * Here the data is converted to an object and passed
	 */ 
	@Test(dataProvider="getData")
	public void setData(ZipPairDRO dro)
	{
	System.out.println("your key is::"+dro.getKey());
	System.out.println("your description is::"+dro.getDescription());
	System.out.println("your inputpair is::"+dro.getInput_zips());
	System.out.println("your outputpair is::"+dro.getExpected_zips());
	}
	
	@DataProvider
	public ZipPairDRO[] getData() throws IOException
	{
		//Rows - Number of test cases.
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
    
}