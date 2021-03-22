package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.ZipCodePair;
import com.ZipLimiter;


/*
 * This class is to Unit test the ZipLimiter class. 
 */
public class ZipLimiterTest {

	List<ZipCodePair> expectedtest1;
	List<ZipCodePair> expectedtest2;
	List<ZipCodePair> expectedtest3;
	List<ZipCodePair> expectedtest4;
	private static Log log = LogFactory.getLog(ZipLimiterTest.class);
	
	/*
	* These are the values to be compared against the data read from files in test case scenarios.
	* Called automatically before the Test class is run.
	*/
 	@Before
    public void setUp() {
 		log.info("@Before - setUp");
    	expectedtest1 = read("resources/test/expectedv2/expectedZipInputNoConflictPairs.txt");
		expectedtest2 = read("resources/test/expectedv2/expectedZipInputMergeSortedPairs.txt");
		expectedtest3 = read("resources/test/expectedv2/expectedZipInputMergeUnsortedPairs.txt");
		expectedtest4 = read("resources/test/expectedv2/expectedZipInputBadDataPairs.txt");
    }
    
    @After
    public void tearDown() {
    	log.info("@After - tearDown");
    }
    
    /*
    * Runs test cases for different scenarios comparing size of the lists and content of the lists
    * Test cases: No Merge, Sorted Merge, UnSorted Merge, Merge With BadData
    *
    */
    @Test
	public void zipLimiterDataTest() {
		ZipLimiter zip = new ZipLimiter();
		
		List<ZipCodePair> test1 = zip.test("resources/test/zipInputNoConflictPairs.txt");
		Assert.assertEquals(expectedtest1, test1);
		
		List<ZipCodePair> test2 = zip.test("resources/test/zipInputMergeSortedPairs.txt");
		Assert.assertEquals(expectedtest2, test2);
		
		List<ZipCodePair> test3 = zip.test("resources/test/zipInputMergeUnsortedPairs.txt");
		Assert.assertEquals(expectedtest3, test3);
		
		List<ZipCodePair> test4 = zip.test("resources/test/zipInputBadDataPairs.txt");
		Assert.assertEquals(expectedtest4, test4);		
	}
    
    /*
     * Returns the compressed List<ZipCodePair>.
     *
     * @param  String The location of the text file with input zip ranges.
     * @return List<Pair>  list of zip codes
     */
 	public List<ZipCodePair> read(String fileInput){
 		int lineNumber=0;
 		List<ZipCodePair> zipOutputMatchList = new ArrayList<ZipCodePair>();
         	//try with resources
         	try (BufferedReader br = new BufferedReader(new FileReader(fileInput))){
             String line = null;
             log.info("Reading and Validating contents of file  :" +fileInput);
             while((line = br.readLine()) != null){
             	 lineNumber++;
                  String [] strArray =line.split(",");
                  if (strArray[0].length()==6 && strArray[1].length()==6){
                	  String zipA =strArray[0].substring(1, 6);
                	  String zipB =strArray[1].substring(0, 5);
              	 	  ZipCodePair pair= new ZipCodePair(Integer.parseInt(zipA),Integer.parseInt(zipB));
              	 	  zipOutputMatchList.add(pair);
                  } else{
                	  log.info("Error at line number  : "+lineNumber+" Input is not in a 5 digit format");
                  }
             }
         } catch (IOException e) {
             e.printStackTrace();
         }
 	    return zipOutputMatchList;
 	} 	
}
