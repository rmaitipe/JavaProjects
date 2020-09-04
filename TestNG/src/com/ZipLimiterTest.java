package com;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/*
This class is to Unit test the ZipLimiter class. 
It users the files to load different data sets and compare with expectations using Junit4 test cases
*/
public class ZipLimiterTest {
	
	private ArrayList<Pair> testNonConflict;
	private ArrayList<Pair> testSorted;
	private ArrayList<Pair> testUnsorted;
	private ArrayList<Pair> testInputData;
	private ArrayList<Pair> testBadInput;
	
	/*
	* These are the values to be compared against the data read from files in test case scenarios.
	* Called automatically before the Test class is run.
	*/
 	@BeforeMethod
    public void setUp() {
    	System.out.println("@Before - setUp");
    	this.testNonConflict = new ArrayList<Pair>();
    	testNonConflict.add(new Pair(94200,94299));
    	testNonConflict.add(new Pair(94500,94699));
    	testNonConflict.add(new Pair(94800,94850));
    	testNonConflict.add(new Pair(95133,95133));
    	testNonConflict.add(new Pair(95135,95153));
    	testNonConflict.add(new Pair(96845,96950));
    	testNonConflict.add(new Pair(96955,96999));
    	
    	this.testSorted = new ArrayList<Pair>();
    	testSorted.add(new Pair(94200,94699));
    	testSorted.add(new Pair(94800,94850));
    	testSorted.add(new Pair(95133,95153));
    	testSorted.add(new Pair(96845,96950));
    	
    	this.testUnsorted = new ArrayList<Pair>();
    	testUnsorted.add(new Pair(94200,94699));
    	testUnsorted.add(new Pair(94800,94850));
    	testUnsorted.add(new Pair(95133,95153));
    	testUnsorted.add(new Pair(96845,96950));	    
    	
    	this.testInputData = new ArrayList<Pair>();
    	testInputData.add(new Pair(94200,94299));
    	testInputData.add(new Pair(95133,95133));
    	testInputData.add(new Pair(94800,94850));
    	
    	this.testBadInput = new ArrayList<Pair>();
    	testBadInput.add(new Pair(94200,94299));
    	testBadInput.add(new Pair(94800,94850));
    }
    
    @AfterMethod
    public void tearDown() {
        System.out.println("@After - tearDown");
        this.testNonConflict.clear();
        this.testSorted.clear();
        this.testUnsorted.clear();
        this.testInputData.clear();
        this.testBadInput.clear();
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
		Assert.assertEquals(7, test1.size());
		Assert.assertEquals(testNonConflict, test1);
		
		List<Pair> test2 = zip.test("resources/test/zipInputMergeSortedPairs.txt");
		Assert.assertEquals(4, test2.size());
		Assert.assertEquals(testSorted, test2);
		
		List<Pair> test3 = zip.test("resources/test/zipInputMergeUnsortedPairs.txt");
		Assert.assertEquals(4, test3.size());
		Assert.assertEquals(testUnsorted, test3);
		
		List<Pair> test4 = zip.test("resources/test/zipInputBadDataPairs.txt");
		Assert.assertEquals(2, test4.size());
		Assert.assertEquals(testBadInput, test4);
	}
}
