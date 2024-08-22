package core;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;

class SingletonResolvedTest
{
    public enum Singleton {
        INSTANCE;//JVM does the instantiation
        private Singleton() {
        }
    }

    static class Singleton2 implements Serializable  {
        // public instance initialized when loading the class
        public static Singleton2 instance = new Singleton2();

        private Singleton2()
        {
            // private constructor
        }
        // implement readResolve method
        protected Object readResolve()
        {
            return instance;
        }
    }

    static class SuperClass implements Cloneable 	{
        int i = 10;
        @Override
        protected Object clone() throws CloneNotSupportedException
        {
            return super.clone();
        }
    }

    static class Singleton3 extends SuperClass {
        // public instance initialized when loading the class
        public static Singleton3 instance = new Singleton3();

        private Singleton3()
        {
            // private constructor
        }
        @Override
        protected Object clone() throws CloneNotSupportedException
        {
            throw new CloneNotSupportedException();
        }
    }

    public static void main(String[] args) {
        Singleton instance1 = Singleton.INSTANCE;
        Singleton instance2 = null;
        try {
            Constructor[] constructors =  Singleton.class.getDeclaredConstructors();
            for (Constructor constructor : constructors)  {   // Below code will destroy the singleton pattern
                constructor.setAccessible(true);
                instance2 = (Singleton) constructor.newInstance();
                break;
            }
            System.out.println("Reflection - instance1.hashCode():- " + instance1.hashCode());
            System.out.println("Reflection - instance2.hashCode():- " + instance2.hashCode());
        }
        catch (Exception e){ e.printStackTrace(); }
        try{
            Singleton2 instanc1 = Singleton2.instance;
            ObjectOutput out = new ObjectOutputStream(new FileOutputStream("file.text"));
            out.writeObject(instanc1);
            out.close();
            // de-serialize from file to object
            ObjectInput in  = new ObjectInputStream(new FileInputStream("file.text"));
            Singleton2 instanc2 = (Singleton2) in.readObject();
            in.close();

            System.out.println("Serilization - instance1 hashCode:- " + instanc1.hashCode());
            System.out.println("Serilization - instance2 hashCode:- " + instanc2.hashCode());

            Singleton3 inst1 = Singleton3.instance;
            Singleton3 inst2 = (Singleton3) inst1.clone();
            System.out.println("Cloning instance1 hashCode:- " + inst1.hashCode());
            System.out.println("Cloning instance2 hashCode:- " + inst2.hashCode());
        }
        catch (Exception e){ e.printStackTrace(); }
    }
}

