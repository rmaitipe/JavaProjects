package operations;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorOperations {

    public static void main(String [] args){
        List<Employee> empList=List.of(new Employee("a",1),new Employee("c",4),
                new Employee("d",34), new Employee("b",4));
        List<String> strList=List.of("a","c","d","b");
        /*
         * compare() Prerequisite: Comparator Interface - essential for custom sorting
         * CompareTo() Prerequisite: Comparable Interface
         * Multiple implementations vs. single implementation
         * example: String & Integer classes has both and implements Comparable so has compareTo() which is natural ordering
         *
         * Behind the scenes compareTo will call a compare method or a similar method with same signature.
         * Integer.compare(x, y) returns -1 if x is less than y, 0 if they’re equal, and 1 otherwise.
         */
        Comparator<Employee> compareByLastName = Comparator.comparing(Employee::getLastName);
        Comparator<Employee> compareById = Comparator.comparingInt(Employee::getId);
        Comparator<Employee> compareByFirstName= (h1,h2)->h1.getFirstName().compareTo(h2.getFirstName());
        Comparator<Employee> compareByRank=(emp1,emp2)->Integer.compare(emp1.getId(),emp2.getId());

        Collections.sort(empList, compareByLastName);

        empList.sort(compareByLastName);//pass Comparator
        empList.sort(compareByFirstName.reversed());//pass Comparator reversed
        strList.sort(Comparator.reverseOrder());//pass Natural Comparator reversed where it exists.
        /*
         * Any Comparable type can be turned into a Comparator object by using a method reference
         * (Comparator<String>) String::compareTo, (Comparator<Integer>) Integer::compareTo
         */
        strList.sort(String::compareTo);
        empList.sort(Employee::compareByNameThenAge);//refer by custom static method
        empList.sort((h1, h2) -> h1.getFirstName().compareTo(h2.getFirstName()));//lambda

        Comparator<Employee> compareByFullName = compareByFirstName.thenComparing(compareByLastName);
        empList.sort((h1,h2) -> h1.getLastName().compareTo(h2.getLastName()));
    }

    static class Employee{
        CollectionsOperationsMapList.Department dept;
        int salary;
        String firstName;
        String lastName;
        int grade;
        int id;

        public Employee(String name, int id) {
            this.id=id;
            this.lastName=name;
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

    static class Department{
        int salary;
        int getSalary(){return salary;}
    }

}
