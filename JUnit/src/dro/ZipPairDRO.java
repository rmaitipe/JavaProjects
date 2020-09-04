package dro;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;

public class ZipPairDRO {

    private static final String [] FILE_HEADER = {""};

    private String key;
    private String description;
    private String input_zips;
    private String expected_zips;   

    public ZipPairDRO(String Key, String description, String input, String expected) {
		this.key=Key;
		this.description=description;
		this.input_zips=input;
		this.expected_zips=expected;
	}

	public List<ZipPairDRO> readCsvFile(File fileName) {
        FileReader fileReader = null;
        CSVParser csvFileParser = null;

        CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader(FILE_HEADER);
        List<ZipPairDRO> droRecords = null;
        try {
        	droRecords = new ArrayList<>();
            String file = FileUtils.readFileToString(fileName);
            fileReader = new FileReader(file);
            csvFileParser = new CSVParser(fileReader, csvFormat);
            List<CSVRecord> records = csvFileParser.getRecords();

            for (int i = 1; i < records.size(); i++) {
                CSVRecord record = records.get(i);
                ZipPairDRO droRecord = new ZipPairDRO(record.get(key), record.get(description),record.get(input_zips), record.get(expected_zips));
                droRecords.add(droRecord);
            }
        } 
        catch(Throwable t) {
            t.printStackTrace();
        } 
        finally {
            try {
                fileReader.close();
                csvFileParser.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        return droRecords;
    }

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getInput_zips() {
		return input_zips;
	}

	public void setInput_zips(String input_zips) {
		this.input_zips = input_zips;
	}

	public String getExpected_zips() {
		return expected_zips;
	}

	public void setExpected_zips(String expected_zips) {
		this.expected_zips = expected_zips;
	}
	
}