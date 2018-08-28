package com;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*
	This class uses the zipInputPair.txt as source to read a zip code range and compresses the range where values overlap 
	Values in the file are assumed to be in comma separated pairs
*/
public class ZipLimiter {
	
	private List<Pair> zipOutputMatchList;
	private List<Pair> zipInputMatchList;
	private BufferedReader br = null;
	private String zipA;
	private String zipB;
	private static String fileInput ="resources/zipInputPair.txt";
	private int lineNumber;
	
    /**
    * Returns the compressed List<Pair> after combining overlapping ranges.
    *
    * @param  String The location of the text file with input zip ranges.
    * @return List<Pair> merged list of zip codes after combining overlapping ranges
    */
	public List<Pair> test(String fileInput){
		lineNumber=0;
        zipOutputMatchList = new ArrayList<Pair>();
        zipInputMatchList = new ArrayList<Pair>();
        	try {
            br = new BufferedReader(new FileReader(fileInput));
            String line = null;
            System.out.println("Reading and Validating contents of file  :" +fileInput);
            while((line = br.readLine()) != null){
            	 lineNumber++;
                 String [] strArray =line.split(",");
                 if (strArray[0].length()==6 & strArray[1].length()==6){
                 zipA =strArray[0].substring(1, 6);
                 zipB =strArray[1].substring(0, 5);
             	 	if (!validateStop(zipA) && !validateStop(zipB)){
             	 		Pair pair= new Pair(Integer.parseInt(zipA),Integer.parseInt(zipB));
             	 		zipInputMatchList.add(pair);
             	 	}
                 }
                 else{
                	 System.out.println("Error at line number  : "+lineNumber+" Input is not in a 5 digit format");
                	 
                 }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try{if(br != null) br.close();}catch(Exception ex){}
        }

	    zipInputMatchList.sort(Comparator.comparing(Pair::getZipA));
	    zipOutputMatchList =mergeBoundries(zipInputMatchList);
	    zipOutputMatchList.forEach(item->System.out.println(item));
	    return zipOutputMatchList;
	    
	}

    /**
    * Indicates if the input from file is a valid integer along with the line number where the failure occurred
    *
    * @param  String The input is a zip code.
    * @return boolean Fails validation if not an Integer, Not 5 digits
    */
	private boolean validateStop(String zip) {
		boolean isStop = false;
	    	try{
	    		Integer.parseInt(zip);
	    	}
	    	catch(NumberFormatException e){
	    		System.out.println("Error at line number  : "+lineNumber+" Input is not a valid number");
	    		isStop =true;
	    	}
	    	if(zip.length()!=5 && !isStop){
	    		System.out.println("Error at line number  : "+lineNumber+" Input is not a 5 digit zip code");
	    		isStop =true;
	    	}
		return isStop;
	}

    /**
    * Iterate and merge the zip codes where overlap exists
    *
    * @param  List<Pair> This input list consists of Pair values created from data read from file.
    * @return List<Pair> This output list consists of the merged Pair values.
    */
	private List<Pair> mergeBoundries(List<Pair> zipList) {
		for (Pair current: zipList){
			//new range is out of existing range, add it
			if (zipOutputMatchList.isEmpty()){
				zipOutputMatchList.add(current);
			}
			else{
				Pair prevPair =zipOutputMatchList.get(zipOutputMatchList.size()-1);
				if(prevPair.getZipA()<=current.getZipA() && prevPair.getZipB()>=current.getZipB()){//already included
				}
				else if(prevPair.getZipA()>current.getZipB() || prevPair.getZipB()<current.getZipA()){
					zipOutputMatchList.add(current);//New Range
				}
				else{
					Pair temp =new Pair(prevPair.getZipA(),current.getZipB());
					zipOutputMatchList.remove(zipOutputMatchList.size()-1);
					zipOutputMatchList.add(temp);
				}
			}
		}
		return zipOutputMatchList;
	}

	public static void main(String args[]){
		ZipLimiter zip = new ZipLimiter();
		zip.test(fileInput);
	}
}
