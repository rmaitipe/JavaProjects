package com;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

/*
 * This v3 class is to Unit test the ZipLimiter class. 
 */
public class ZipLimiterTestv3 {

	/*
	* These are the values to be compared against the data read from files in test case scenarios.
	* Called automatically before the Test class is run.
	*/
 	@BeforeAll
    public void setUp() {
    	System.out.println("@Before - setUp");
    }
    
    @AfterAll
    public void tearDown() {
        System.out.println("@After - tearDown");
    }
    
    /*
    * Runs test cases for different scenarios comparing size of the lists and content of the lists
    * Test cases: No Merge, Sorted Merge, UnSorted Merge, Merge With BadData
    *
    */
    @ParameterizedTest
    @CsvFileSource(resources = "C://Users/rmait/git/JavaProjects/JUnit/resources/test/v3", numLinesToSkip = 1)
	public void zipLimiterDataTest(String key, String description, String input, String expected) {
		ZipLimiter zip = new ZipLimiter();
		List<Pair> test1 = zip.testv3(input);
		Assertions.assertEquals("failure - expected result content match", test1, expected);
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
