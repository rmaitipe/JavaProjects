package csv.reader;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SampleTest{

	/*The purpose of these package is to understand how data is setup for DataProvider Class
	 * test method declares that its data should be supplied by the DataProvider named "getdata"
	 * Number of columns should match the number of input parameters
	 */ 

	@Test(dataProvider="getData")
	public void setData(String username, String password)
	{
	System.out.println("your username is :: "+username);
	System.out.println("your password is :: "+password);
	}
	
	@DataProvider
	public Object[][] getData()
	{
	//Rows - Number of test cases.
	//Columns - Number of parameters in test data.
	Object[][] data = new Object[3][2];
	
	// 1st row
	data[0][0] ="user1";
	data[0][1] = "abc";
	
	// 2nd row
	data[1][0] ="user2";
	data[1][1] = "xyz";
	
	// 3rd row
	data[2][0] ="user3";
	data[2][1] = "def";
	
	return data;
	}
}
