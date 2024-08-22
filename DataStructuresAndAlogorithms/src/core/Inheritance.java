package core;

public class Inheritance {

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
            System.out.println("Parent test");
        }
    }

    static class Child extends Parent {
        int value = 10;
        Child() {
            System.out.println("Child Constructor");
        }
        public void test(){
            System.out.println("Child test");
        }
    }
}

