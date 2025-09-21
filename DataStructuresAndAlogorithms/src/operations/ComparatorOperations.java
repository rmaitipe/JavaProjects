package operations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorOperations {

    public static void main(String [] args){
        Employee a= new Employee("a","dev",1);
        Employee b= new Employee("b","sri",4);
        Employee c= new Employee("c","ray",4);
        Employee d= new Employee("d","mal",34);

        List<Employee> empList=List.of(a,b,c,d);//Immutable-> will produce exception
        List<String> strList=List.of("a","c","d","b");

        List<Employee> empList2=new ArrayList<>();
        empList2.add(a);
        empList2.add(b);
        empList2.add(c);
        empList2.add(d);
        List<String> strList2=new ArrayList<>();
        strList2.add("a");
        strList2.add("b");
        strList2.add("c");
        strList2.add("d");

        /*
         * compare() Prerequisite: Comparator Interface - essential for custom sorting
         * CompareTo() Prerequisite: Comparable Interface - defines natural ordering
         * Multiple implementations vs. single implementation
         * example: String & Integer classes has both and implements Comparable so has compareTo() which is natural ordering
         *
         * Behind the scenes compareTo will call a compare method or a similar method with same signature.
         * Integer.compare(x, y) returns -1 if x is less than y, 0 if they’re equal, and 1 otherwise.
         */
        Comparator<Employee> compareByFirstNameOldFormat = new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.firstName.compareTo(o2.firstName);
            }
        };
        Comparator<Employee> compareByLastName = Comparator.comparing(Employee::getLastName);
        Comparator<Employee> compareById = Comparator.comparingInt(Employee::getId);
        Comparator<Employee> compareByFirstName= (h1,h2)->h1.getFirstName().compareTo(h2.getFirstName());
        Comparator<Employee> compareByIdReverse=(emp1,emp2)->Integer.compare(emp2.getId(),emp1.getId());

        Collections.sort(empList2, compareById);
        System.out.println("compareById");
        empList2.stream().forEach(x -> System.out.print(x.firstName+x.lastName));
        empList2.sort(compareByLastName);//pass Comparator
        System.out.println("compareByLastName");
        empList2.stream().forEach(x -> System.out.print(x.firstName+x.lastName));
        empList2.sort(compareByFirstName.reversed());//pass Comparator reversed
        System.out.println("compareByFirstName Reverse");
        empList2.stream().forEach(x -> System.out.print(x.firstName+x.lastName));
        strList2.sort(Comparator.reverseOrder());//pass Natural Comparator reversed where it exists.
        System.out.println("compareById Reverse Order");
        empList2.stream().forEach(x -> System.out.print(x.firstName+x.lastName));
        /*
         * Any Comparable type can be turned into a Comparator object by using a method reference
         * (Comparator<String>) String::compareTo, (Comparator<Integer>) Integer::compareTo
         */
        strList2.sort(String::compareTo);
        empList2.sort(Employee::compareByNameThenAge);//refer by custom static method
        empList2.sort((h1, h2) -> h1.getFirstName().compareTo(h2.getFirstName()));//lambda

        Comparator<Employee> compareByFullName = compareByFirstName.thenComparing(compareByLastName);
        empList2.sort((h1,h2) -> h1.getLastName().compareTo(h2.getLastName()));
    }

    static class Employee{
        CollectionsOperationsMapList.Department dept;
        int salary;
        String firstName;
        String lastName;
        int grade;
        int id;

        public Employee(String firstName, String lastName,int id) {
            this.id=id;
            this.lastName=lastName;
            this.firstName=firstName;
        }

        int getSalary(){return salary;}
        String getLastName(){return lastName;}
        String getFirstName(){return firstName;}
        int getGrade(){return grade;}
        int getId(){return id;}

        CollectionsOperationsMapList.Department getDepartment(){return dept;}
        Employee(int id){
            this.id=id;
        }

        public static int compareByNameThenAge(Employee lhs, Employee rhs) {
            if (lhs.lastName.equals(rhs.lastName)) {
                return Integer.compare(lhs.grade, rhs.grade);
            } else {
                return lhs.lastName.compareTo(rhs.lastName);
            }
        }
    }

}
