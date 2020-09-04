package dro;

public class ZipPairDRO {

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