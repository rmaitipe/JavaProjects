package com.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.sun.xml.internal.ws.util.StringUtils;
/*
 * Simple REST webservice
 * returns anagrams of the given word
 * Returns [] if no matches found
 */
@Path("/{word}")
public class JSONService {

	private static ArrayList<String> wordList = null;
	private static final String errCode = "404";
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAnagramInJSON(@PathParam("word") String target) throws Exception {

    	String line;
		BufferedReader br = null;
		ArrayList<String> anagramList = new ArrayList<String>();
		if (wordList == null){
			try {
				wordList = new ArrayList<String>();
				InputStreamReader ir = new InputStreamReader(new URL("http://www-01.sil.org/linguistics/wordlists/english/wordlist/wordsEn.txt").openStream(), "UTF-8");
			    br = new BufferedReader(ir);
				while((line = br.readLine()) != null){	
					wordList.add(line);
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}	
		}
		if (wordList.contains(target.toLowerCase())){
			anagramList = hasAnagram(target);
		}
		else{
			return Response.status(200).entity(errCode).build();
		}
		return Response.status(201).entity(anagramList).build();
	}
	
	public static ArrayList<String> hasAnagram(String word) {
		ArrayList<String> anagramList = new ArrayList<String>();
		char[] chArray =word.toLowerCase().toCharArray();
		Arrays.sort(chArray);
		String wordSorted = new String(chArray);
		
		for (String varWord: wordList){
			if (varWord.length()==word.length()){
				char[] chArray2= varWord.toCharArray();
				Arrays.sort(chArray2);
				String varSorted = new String(chArray2);
				if(wordSorted.equals(varSorted)){	
					if (word.toUpperCase().equals(word)){//Alternative is a StringUtils method
						anagramList.add(varWord.toUpperCase());	
					}
					else if (Character.isUpperCase(word.charAt(0))){
						anagramList.add(StringUtils.capitalize(varWord));
					}
					else{
						anagramList.add(varWord);
					}
				}
			}
		}
		anagramList.remove(word);
		return anagramList;
	}
}