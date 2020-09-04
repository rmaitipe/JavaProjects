package com;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


/*
This v2 class is to Unit test the ZipLimiter class. 
*/
public class ZipLimiterTestv2 {

	List<Pair> expectedtest1;
	List<Pair> expectedtest2;
	List<Pair> expectedtest3;
	List<Pair> expectedtest4;
	
	/*
	* These are the values to be compared against the data read from files in test case scenarios.
	* Called automatically before the Test class is run.
	*/
 	@BeforeMethod
    public void setUp() {
    	System.out.println("@Before - setUp");
    	expectedtest1 = read("resources/test/expectedv2/expectedZipInputNoConflictPairs.txt");
		expectedtest2 = read("resources/test/expectedv2/expectedZipInputMergeSortedPairs.txt");
		expectedtest3 = read("resources/test/expectedv2/expectedZipInputMergeUnsortedPairs.txt");
		expectedtest4 = read("resources/test/expectedv2/expectedZipInputBadDataPairs.txt");
    }
    
    @AfterMethod
    public void tearDown() {
        System.out.println("@After - tearDown");
    }
    
    /*
    * Runs test cases for different scenarios comparing size of the lists and content of the lists
    * Test cases: No Merge, Sorted Merge, UnSorted Merge, Merge With BadData
    *
    */
    @Test
	public void zipLimiterDataTest() {
		ZipLimiter zip = new ZipLimiter();
		
		List<Pair> test1 = zip.test("resources/test/zipInputNoConflictPairs.txt");
		Assert.assertEquals(expectedtest1.size(), test1.size());
		Assert.assertEquals(expectedtest1, test1);
		
		List<Pair> test2 = zip.test("resources/test/zipInputMergeSortedPairs.txt");
		Assert.assertEquals(expectedtest2.size(), test2.size());
		Assert.assertEquals(expectedtest2, test2);
		
		List<Pair> test3 = zip.test("resources/test/zipInputMergeUnsortedPairs.txt");
		Assert.assertEquals(expectedtest3.size(), test3.size());
		Assert.assertEquals(expectedtest3, test3);
		
		List<Pair> test4 = zip.test("resources/test/zipInputBadDataPairs.txt");
		Assert.assertEquals(expectedtest4.size(), test4.size());
		Assert.assertEquals(expectedtest4, test4);
		
	}
    
    /*
     * Returns the compressed List<Pair>.
     *
     * @param  String The location of the text file with input zip ranges.
     * @return List<Pair>  list of zip codes
     */
 	public List<Pair> read(String fileInput){
 		int lineNumber=0;
 		List<Pair> zipOutputMatchList = new ArrayList<Pair>();
         	//try with resources
         	try (BufferedReader br = new BufferedReader(new FileReader(fileInput))){
             String line = null;
             System.out.println("Reading and Validating contents of file  :" +fileInput);
             while((line = br.readLine()) != null){
             	 lineNumber++;
                  String [] strArray =line.split(",");
                  if (strArray[0].length()==6 & strArray[1].length()==6){
                	  String zipA =strArray[0].substring(1, 6);
                	  String zipB =strArray[1].substring(0, 5);
              	 	  Pair pair= new Pair(Integer.parseInt(zipA),Integer.parseInt(zipB));
              	 	  zipOutputMatchList.add(pair);
                  } else{
                 	 System.out.println("Error at line number  : "+lineNumber+" Input is not in a 5 digit format");
                  }
             }
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }
 	    return zipOutputMatchList;
 	} 	
}
