package core;


import java.util.Arrays;

public class PassByValue {
    /*593,
    In Java, when primitive data types (like int, char, boolean, double, etc.) are passed as arguments to a method,
    they are always passed by value. This means that a copy of the primitive variable's value is created and passed to the method.

    When an object is passed to a method, the reference to that object is passed by value. This means the method receives
    a copy of the reference, which points to the same object in memory as the original reference. Any changes made to
    the object through this copied reference will affect the original object because both references point to the same object in memory.

    All wrapper classes in Java are immutable. They replicate the behaviour of primitives
    Arrays - even of primitives are objects
     */

    public static void main(String args[]) {
        PassByValue ob = new PassByValue();
        Double k=0.0;
        int l=0;
        int[] scores = new int[]{1,3,5,7};
        Vehicle vehicle1=new Vehicle();
        System.out.println(vehicle1.color);
        Vehicle vehicle2=new Vehicle();
        System.out.println(vehicle2.color);
        ob.validate(k,l,vehicle1,vehicle2,scores);
        System.out.println(vehicle1.color);
        System.out.println(vehicle2.color);
        System.out.println(k);
        System.out.println(l);
        System.out.println(Arrays.toString(scores));
    }

    private void validate(Double k, int l, Vehicle vehicle, Vehicle vehicle2, int[] scores) {
        k=5.0;
        l=3;
        vehicle.color="BLUE";
        vehicle2 =new Vehicle();
        vehicle2.color="GREEN";
        scores[0]=0;
    }

    static class Vehicle{
        String color;

        Vehicle(){
            color="RED";
        }
    }


}
