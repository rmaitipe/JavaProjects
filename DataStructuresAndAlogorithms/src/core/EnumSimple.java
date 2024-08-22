package core;

public enum EnumSimple {
    HIGH, MEDIUM, LOW;
    /*
    Enum Iteration
    You can obtain an array of all the possible values of a Java enum type by calling its static values() method.
    All enum types get a static values() method automatically by the Java compiler. Here is an example of iterating all values of an enum:
    */
    public static void main(String args []){
        for (EnumSimple level : EnumSimple.values()) {
            System.out.println(level);
        }
    }

}
/*
Enum toString()
An enum class automatically gets a toString() method in the class when compiled. The toString() method returns a string value of the name of the given enum instance. Here is an example:
String levelText = Level.HIGH.toString();
*/