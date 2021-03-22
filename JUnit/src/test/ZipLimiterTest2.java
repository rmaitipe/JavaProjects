package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.ZipCodePair;
import com.ZipLimiter;


/*
 * This class is to Unit test the ZipLimiter class. 
 */
@RunWith(Parameterized.class)
public class ZipLimiterTest2 {


	private static Log log = LogFactory.getLog(ZipLimiterTest2.class);
	private String inputFile;
	private String outputFile;
	
    @Parameterized.Parameters
    public static List<Object[]> fileNames() {
      return Arrays.asList(new Object[][] {
         { "resources/test/zipInputNoConflictPairs.txt", "resources/test/expectedv2/expectedZipInputNoConflictPairs.txt" },
         { "resources/test/zipInputMergeSortedPairs.txt", "resources/test/expectedv2/expectedZipInputMergeSortedPairs.txt"},
         { "resources/test/zipInputMergeUnsortedPairs.txt", "resources/test/expectedv2/expectedZipInputMergeUnsortedPairs.txt" },
         { "resources/test/zipInputBadDataPairs.txt", "resources/test/expectedv2/expectedZipInputBadDataPairs.txt"}
      });
    }

    
	// Constructor is initialized with one set of parameters every time
	public ZipLimiterTest2(String input, String output) 
	{
		this.inputFile = input;
		this.outputFile = output;
	}
	/*
	* These are the values to be compared against the data read from files in test case scenarios.
	* Called automatically before the Test class is run.
	*/
 	@Before
    public void setUp() {
 		log.info("@Before - setUp");
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
		List<ZipCodePair> testDataSet = zip.test(inputFile);
		List<ZipCodePair> outputDataSet = read(outputFile);
		Assert.assertEquals(outputDataSet, testDataSet);
	}
    
    /*
     * Returns the compressed List<ZipCodePair>.
     *
     * @param  String The location of the text file with input zip ranges.
     * @return List<Pair>  list of zip codes
     */
 	public List<ZipCodePair> read(String fileInput){
 		int lineNumber=0;
 		List<ZipCodePair> zipOutputMatchList = new ArrayList<>();
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
