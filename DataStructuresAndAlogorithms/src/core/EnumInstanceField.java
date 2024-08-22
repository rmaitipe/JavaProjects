package core;

public enum EnumInstanceField {//Used to provide abstraction for types
    HIGH("One"),
    MEDIUM("Two"),
    LOW("Three");

    private String url;

    EnumInstanceField(String envUrl) {
        this.url = envUrl;
    }

    public String getUrl() {
        return url;
    }
    public static void main(String args []){
        for (EnumInstanceField level : EnumInstanceField.values()) {
            System.out.println(level);
        }
        for (EnumInstanceField level : EnumInstanceField.values()) {
            System.out.println(level.getUrl());
        }
    }

}
