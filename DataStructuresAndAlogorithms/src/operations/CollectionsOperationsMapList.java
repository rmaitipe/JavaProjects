package operations;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Map.entry;

public class CollectionsOperationsMapList {
    //legacy 	//Collections.synchronizedMap();	//Collections.synchronizedList();

    public static void main(String arg[]){
//====================================================================================================================//
        /*
         * (a,b)->a-b can produce overflow errors, By contrast the Integer.compare(int,int) gives the correct answer
         */
        List<String> names = Arrays.asList("aa","v","d","yey","t","a","e","hdh","jh","r","aa");
        Collections.sort(names);//default
        Collections.sort(names, (a, b) -> b.compareTo(a));//reusing the defined compareTo method

        Collections.sort(names, (a, b) -> Integer.compare(a.length(), b.length()));
        Collections.sort(names, (Comparator.comparingInt(String::length)));
        Collections.sort(names, (a, b) -> b.length()-a.length());//custom method
//====================================================================================================================//
// Initialize
        //Example: Accumulate names into a List via collect(Collectors.toList())
        List<Employee> empList = IntStream.rangeClosed(1, 4).mapToObj(Employee::new).toList();
        List<String> nameList= empList.stream().map(Employee::getName).collect(Collectors.toList());

        List<String> resultList = List.of("a","c","d","b","t","a","e","b","z","r");

        List<Employee>empList2=List.of(new Employee("a",1),new Employee("c",4), new Employee("d",34), new Employee("b",4));

        Map<String,Integer>map2=Map.ofEntries(entry("a",2),entry("c",4), entry("d",10), entry("b",4));
//====================================================================================================================//
        // Example: Accumulate into a Map.   map.put(k, map.getOrDefault(k, 0) + 1);
        Map<String,Long>resultKVMap = resultList.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
            //.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,(oldValue, newValue) -> oldValue, LinkedHashMap::new));

        Map<String,Long> sortedKVMap = resultKVMap.entrySet().stream().sorted(Map.Entry.comparingByValue())
            .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,(oldValue,newValue)->oldValue,LinkedHashMap::new));

        PriorityQueue<Map.Entry<String,Integer>> pq=new PriorityQueue<>((a, b) -> Integer.compare(b.getValue(), a.getValue()));
        map2.entrySet().forEach(a->pq.add(a));

//====================================================================================================================//
        //Use a TreeMap for out of box solution for sorted by Key
        Map<Long,List<String>> sortedVKMap = sortedKVMap.entrySet().stream().collect(Collectors.groupingBy(
                Map.Entry::getValue, Collectors.mapping(Map.Entry::getKey, Collectors.toList())));

        Map<Integer,Employee>empMap2=empList.stream().collect(Collectors.toMap(Employee::getId, item -> item));

        // Accumulate names into a TreeSet
        Set<String> set = empList2.stream().map(Employee::getName).collect(Collectors.toCollection(TreeSet::new));
        //====================================================================================================================//
        String str ="SALES:0,SALE_PRODUCTS:1,EXPENSES:2,EXPENSES_ITEMS:3";
        HashMap<String,Integer>map=(HashMap<String,Integer>) Arrays.asList(str.split(",")).stream()
                .map(s->s.split(":")).collect(Collectors.toMap(e -> e[0], e -> Integer.parseInt(e[1])));

        // Convert elements to strings and concatenate them, separated by commas
        String joined = resultList.stream().map(Object::toString).collect(Collectors.joining(", "));

        // Compute sum of salaries of employee
        int total = empList.stream().collect(Collectors.summingInt(Employee::getSalary));
        String max=resultList.stream().max(Comparator.comparingInt(String::length)).orElse("MAX N/A");//Preferred over get
        String max2=resultList.stream().max(Comparator.comparingInt(String::length)).get();
        // Group employees by department
        Map<Department, List<Employee>> byDept
                = empList.stream().collect(Collectors.groupingBy(Employee::getDepartment));

        // Compute sum of salaries by department
        Map<Department, Integer> totalByDept = empList.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,Collectors.summingInt(Employee::getSalary)));

        // Partition students into passing and failing
        Map<Boolean, List<Employee>> passingFailing =
                empList.stream().collect(Collectors.partitioningBy(s -> s.getGrade() >= 80));

        Map<String,Integer> chainMap =empList.stream().collect(Collectors.groupingBy(Employee::getName, Collectors.collectingAndThen
                (Collectors.counting(),f->f.intValue())));
    }


    static class Employee{
        Department dept;
        int salary;
        String name;
        int grade;
        int id;

        public Employee(String name, int id) {
            this.id=id;
            this.name=name;
        }

        int getSalary(){return salary;}
        String getName(){return name;}
        int getGrade(){return grade;}
        int getId(){return id;}
        Department getDepartment(){return dept;}
        Employee(int id){
            this.id=id;
        }
    }

    static class Department{
        int salary;
        int getSalary(){return salary;}
    }

}
