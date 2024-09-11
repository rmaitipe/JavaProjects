package core;

public class Inheritance {
    /*
    Method overriding
    If a Parent type reference refers to a Child object method is called. This is called RUN TIME POLYMORPHISM
     */
    public static void main(String args[]){
        Parent p =new Child();
        p.test();
    }
    static class Parent {
        int value = 1000;
        Parent() {
            System.out.println("Parent Constructor");
        }
        public void test(){
            System.out.println("Parent test"+ value);
        }
    }

    static class Child extends Parent {
        int value = 10;
        Child() {
            System.out.println("Child Constructor");
        }
        public void test(){
            System.out.println("Child test:"+ value);
        }
    }
}

