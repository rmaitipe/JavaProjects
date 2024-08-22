package operations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollectionsOperations {
    //legacy 	//Collections.synchronizedMap();	//Collections.synchronizedList();

    public static void main(String arg[]){

        List<String> names = new ArrayList<>();
        Collections.sort(names);//default
        Collections.sort(names, (a, b) -> b.compareTo(a));
        //collect(Collectors.toMap());
        // Accumulate names into a List
        List<Employee> list = new ArrayList<>();
        List<String> list2= list.stream().map(Employee::getName).collect(Collectors.toList());

        // Accumulate names into a Map
        Map<String, Long> result1 = list2.stream().collect(Collectors.groupingBy(Function.identity() , Collectors.counting())).entrySet()
                .stream().sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,(oldValue, newValue) -> oldValue, LinkedHashMap::new));

        // Accumulate names into a TreeSet
        Set<String> set = list.stream().map(Employee::getName).collect(Collectors.toCollection(TreeSet::new));

        // Convert elements to strings and concatenate them, separated by commas
        String joined = list.stream().map(Object::toString).collect(Collectors.joining(", "));

        // Compute sum of salaries of employee
        int total = list.stream().collect(Collectors.summingInt(Employee::getSalary));

        // Group employees by department
        Map<Department, List<Employee>> byDept
                = list.stream().collect(Collectors.groupingBy(Employee::getDepartment));

        // Compute sum of salaries by department
        Map<Department, Integer> totalByDept = list.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,Collectors.summingInt(Employee::getSalary)));

        // Partition students into passing and failing
        Map<Boolean, List<Employee>> passingFailing =
                list.stream().collect(Collectors.partitioningBy(s -> s.getGrade() >= 80));
    }


    static class Employee{
        Department dept;
        int salary;
        String name;
        int grade;
        int getSalary(){return salary;}
        String getName(){return name;}
        int getGrade(){return grade;}
        Department getDepartment(){return dept;}
    }
    static class Department{
        int salary;
        int getSalary(){return salary;}
    }

}
