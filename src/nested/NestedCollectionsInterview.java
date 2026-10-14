package nested;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * NestedCollectionsInterview.java
 *
 * Java 17+
 *
 * Complete interview practice for: 1. List<List<T>> 2. List<Set<T>> 3.
 * Set<List<T>> 4. Set<Set<T>> 5. Map<K,List<V>> 6. Map<K,Set<V>> 7.
 * Map<K,Map<K,V>> 8. List<Map<K,V>> 9. Nested iteration 10. Nested collection
 * modification/search 11. Stream map / flatMap 12. groupingBy / counting / sum
 * / average 13. max/min, toMap, partitioningBy 14. Frequency, duplicates,
 * unique values 15. Real interview questions
 */
public class NestedCollectionsInterview {

	public static void main(String[] args) {

		// =====================================================
		// EXACT TOPIC ORDER
		// =====================================================

		// 1. BASIC NESTED COLLECTIONS
		listOfLists();
		listOfSets();
		setOfLists();
		setOfSets();

		// 2. MAP + COLLECTION
		mapOfLists();
		mapOfSets();
		mapOfMaps();
		listOfMaps();

		// 3. NESTED COLLECTION OPERATIONS
		iterateNestedList();
		iterateMapOfList();
		iterateMapOfMap();

		addElementToNestedList();
		removeElementFromNestedList();
		searchInsideNestedCollection();

		// 4. STREAM LOGIC
		mapExample();
		flattenNestedList();
		flattenEmployeeSkills();

		groupByDepartment();
		groupAndCount();
		groupAndSum();
		groupAndAverage();

		highestSalaryPerDepartment();
		secondHighestSalary();
		sortNestedCollection();
		partitionData();

		// 5. FREQUENCY / DUPLICATES
		countFrequency();
		findDuplicates();
		findUniqueElements();

		// 6. REAL INTERVIEW QUESTIONS
		question1();
		question2();
		question3();
		question4();
		question5();
		question6();
		question7();
		question8();
		question9();
		question10();
	}

	// =========================================================
	// 1. BASIC NESTED COLLECTIONS
	// =========================================================

	// 1.1 LIST OF LISTS
	static void listOfLists() {

		List<List<Integer>> numbers = List.of(List.of(1, 2, 3), List.of(4, 5, 6), List.of(7, 8, 9));

		System.out.println("\n1.1 List of Lists:");
		System.out.println(numbers);
	}

	// 1.2 LIST OF SETS
	static void listOfSets() {

		List<Set<String>> skills = List.of(Set.of("Java", "Spring"), Set.of("Kafka", "AWS"),
				Set.of("Docker", "Kubernetes"));

		System.out.println("\n1.2 List of Sets:");
		System.out.println(skills);
	}

	// 1.3 SET OF LISTS
	static void setOfLists() {

		Set<List<Integer>> data = new HashSet<>();

		data.add(List.of(1, 2, 3));
		data.add(List.of(4, 5, 6));
		data.add(List.of(7, 8, 9));

		System.out.println("\n1.3 Set of Lists:");
		System.out.println(data);

		// List order matters
		data.add(List.of(1, 2, 3));
		data.add(List.of(3, 2, 1));

		System.out.println("After adding [1,2,3] and [3,2,1]:");
		System.out.println(data);
	}

	// 1.4 SET OF SETS
	static void setOfSets() {

		Set<Set<Integer>> data = new HashSet<>();

		data.add(Set.of(1, 2, 3));
		data.add(Set.of(4, 5, 6));
		data.add(Set.of(7, 8, 9));

		System.out.println("\n1.4 Set of Sets:");
		System.out.println(data);

		// Duplicate inner Set is ignored
		data.add(Set.of(1, 2, 3));

		System.out.println("After adding duplicate {1,2,3}:");
		System.out.println(data);

		// Set order does NOT matter
		data.add(Set.of(3, 2, 1));

		System.out.println("After adding {3,2,1}:");
		System.out.println(data);

		System.out.println("Size = " + data.size());
	}

	// =========================================================
	// 2. MAP + COLLECTION
	// =========================================================

	// 2.1 MAP OF LISTS
	static void mapOfLists() {

		Map<String, List<String>> employees = new HashMap<>();

		employees.put("IT", new ArrayList<>(List.of("Rahul", "Aman")));

		employees.put("HR", new ArrayList<>(List.of("Priya", "Simran")));

		System.out.println("\n2.1 Map of Lists:");
		System.out.println(employees);
	}

	// 2.2 MAP OF SETS
	static void mapOfSets() {

		Map<String, Set<String>> employeeSkills = new HashMap<>();

		employeeSkills.put("Rahul", new HashSet<>(Set.of("Java", "Spring", "Kafka")));

		employeeSkills.put("Aman", new HashSet<>(Set.of("Java", "AWS")));

		System.out.println("\n2.2 Map of Sets:");
		System.out.println(employeeSkills);
	}

	// 2.3 MAP OF MAPS
	static void mapOfMaps() {

		Map<String, Map<String, Integer>> company = new HashMap<>();

		Map<String, Integer> it = new HashMap<>();
		it.put("Rahul", 80000);
		it.put("Aman", 70000);

		Map<String, Integer> hr = new HashMap<>();
		hr.put("Priya", 60000);

		company.put("IT", it);
		company.put("HR", hr);

		System.out.println("\n2.3 Map of Maps:");
		System.out.println(company);

		// Get Rahul salary
		int salary = company.get("IT").get("Rahul");

		System.out.println("Rahul salary = " + salary);
	}

	// 2.4 LIST OF MAPS
	static void listOfMaps() {

		List<Map<String, Object>> employees = new ArrayList<>();

		Map<String, Object> employee1 = new HashMap<>();
		employee1.put("name", "Rahul");
		employee1.put("age", 28);
		employee1.put("salary", 80000);

		Map<String, Object> employee2 = new HashMap<>();
		employee2.put("name", "Aman");
		employee2.put("age", 25);
		employee2.put("salary", 70000);

		employees.add(employee1);
		employees.add(employee2);

		System.out.println("\n2.4 List of Maps:");
		System.out.println(employees);
	}

	// =========================================================
	// 3. NESTED COLLECTION OPERATIONS
	// =========================================================

	// 3.1 ITERATE NESTED LIST
	static void iterateNestedList() {

		List<List<Integer>> numbers = List.of(List.of(1, 2, 3), List.of(4, 5, 6));

		System.out.println("\n3.1 Iterate Nested List:");

		for (List<Integer> list : numbers) {

			for (Integer number : list) {
				System.out.println(number);
			}
		}
	}

	// 3.2 ITERATE MAP OF LIST
	static void iterateMapOfList() {

		Map<String, List<String>> employees = Map.of("IT", List.of("Rahul", "Aman"), "HR", List.of("Priya", "Simran"));

		System.out.println("\n3.2 Iterate Map of List:");

		for (Map.Entry<String, List<String>> entry : employees.entrySet()) {

			String department = entry.getKey();
			List<String> names = entry.getValue();

			System.out.println("Department: " + department);

			for (String name : names) {
				System.out.println("  " + name);
			}
		}
	}

	// 3.3 ITERATE MAP OF MAP
	static void iterateMapOfMap() {

		Map<String, Map<String, Integer>> data = new HashMap<>();

		data.put("IT", Map.of("Rahul", 80000, "Aman", 70000));

		data.put("HR", Map.of("Priya", 60000));

		System.out.println("\n3.3 Iterate Map of Map:");

		data.forEach((department, employees) -> {

			employees.forEach((name, salary) -> {

				System.out.println(department + " -> " + name + " -> " + salary);
			});
		});
	}

	// 3.4 ADD ELEMENT TO NESTED LIST
	static void addElementToNestedList() {

		Map<String, List<String>> employees = new HashMap<>();

		employees.put("IT", new ArrayList<>(List.of("Rahul")));

		employees.get("IT").add("Aman");

		System.out.println("\n3.4 Add Element:");
		System.out.println(employees);
	}

	// 3.5 REMOVE ELEMENT FROM NESTED LIST
	static void removeElementFromNestedList() {

		Map<String, List<String>> employees = new HashMap<>();

		employees.put("IT", new ArrayList<>(List.of("Rahul", "Aman", "Neha")));

		employees.get("IT").remove("Aman");

		System.out.println("\n3.5 Remove Element:");
		System.out.println(employees);
	}

	// 3.6 SEARCH INSIDE NESTED COLLECTION
	static void searchInsideNestedCollection() {

		Map<String, List<String>> employees = Map.of("IT", List.of("Rahul", "Aman"), "HR", List.of("Priya", "Simran"));

		boolean exists = employees.values().stream().anyMatch(list -> list.contains("Rahul"));

		System.out.println("\n3.6 Search:");
		System.out.println("Rahul exists = " + exists);
	}

	// =========================================================
	// 4. STREAM LOGIC
	// =========================================================

	// 4.1 MAP
	static void mapExample() {

		List<Employee> employees = getEmployees();

		List<String> names = employees.stream().map(Employee::name).toList();

		System.out.println("\n4.1 map():");
		System.out.println(names);
	}

	// 4.2 FLATTEN LIST OF LIST
	static void flattenNestedList() {

		List<List<Integer>> numbers = List.of(List.of(1, 2, 3), List.of(4, 5), List.of(6, 7));

		List<Integer> result = numbers.stream().flatMap(List::stream).toList();

		System.out.println("\n4.2 flatMap() Nested List:");
		System.out.println(result);
	}

	// 4.3 FLATTEN EMPLOYEE SKILLS
	static void flattenEmployeeSkills() {

		List<Employee> employees = getEmployees();

		List<String> skills = employees.stream().flatMap(employee -> employee.skills().stream()).distinct().toList();

		System.out.println("\n4.3 flatMap() Employee Skills:");
		System.out.println(skills);
	}

	// 4.4 GROUP BY DEPARTMENT
	static void groupByDepartment() {

		List<Employee> employees = getEmployees();

		Map<String, List<Employee>> result = employees.stream().collect(Collectors.groupingBy(Employee::department));

		System.out.println("\n4.4 groupingBy():");
		System.out.println(result);
	}

	// 4.5 GROUP AND COUNT
	static void groupAndCount() {

		List<Employee> employees = getEmployees();

		Map<String, Long> result = employees.stream()
				.collect(Collectors.groupingBy(Employee::department, Collectors.counting()));

		System.out.println("\n4.5 groupingBy() + counting():");
		System.out.println(result);
	}

	// 4.6 GROUP AND SUM
	static void groupAndSum() {

		List<Employee> employees = getEmployees();

		Map<String, Integer> result = employees.stream()
				.collect(Collectors.groupingBy(Employee::department, Collectors.summingInt(Employee::salary)));

		System.out.println("\n4.6 groupingBy() + summingInt():");
		System.out.println(result);
	}

	// 4.7 GROUP AND AVERAGE
	static void groupAndAverage() {

		List<Employee> employees = getEmployees();

		Map<String, Double> result = employees.stream()
				.collect(Collectors.groupingBy(Employee::department, Collectors.averagingInt(Employee::salary)));

		System.out.println("\n4.7 groupingBy() + averagingInt():");
		System.out.println(result);
	}

	// 4.8 HIGHEST SALARY PER DEPARTMENT
	static void highestSalaryPerDepartment() {

		List<Employee> employees = getEmployees();

		Map<String, Employee> result = employees.stream()
				.collect(Collectors.groupingBy(Employee::department, Collectors.collectingAndThen(
						Collectors.maxBy(Comparator.comparingInt(Employee::salary)), Optional::orElseThrow)));

		System.out.println("\n4.8 Highest Salary Per Department:");
		System.out.println(result);
	}

	// 4.9 SECOND HIGHEST SALARY
	static void secondHighestSalary() {

		List<Employee> employees = getEmployees();

		Integer result = employees.stream().map(Employee::salary).distinct().sorted(Comparator.reverseOrder()).skip(1)
				.findFirst().orElse(null);

		System.out.println("\n4.9 Second Highest Salary:");
		System.out.println(result);
	}

	// 4.10 SORT NESTED COLLECTION
	static void sortNestedCollection() {

		List<Employee> employees = getEmployees();

		List<Employee> result = employees.stream().sorted(Comparator.comparingInt(Employee::salary).reversed())
				.toList();

		System.out.println("\n4.10 Sort by Salary Descending:");
		result.forEach(System.out::println);
	}

	// 4.11 PARTITION DATA
	static void partitionData() {

		List<Employee> employees = getEmployees();

		Map<Boolean, List<Employee>> result = employees.stream()
				.collect(Collectors.partitioningBy(e -> e.salary() >= 70000));

		System.out.println("\n4.11 partitioningBy():");
		System.out.println(result);
	}

	// =========================================================
	// 5. FREQUENCY / DUPLICATES
	// =========================================================

	// 5.1 COUNT FREQUENCY
	static void countFrequency() {

		List<String> skills = List.of("Java", "Spring", "Java", "Kafka", "Java", "Spring");

		Map<String, Long> frequency = skills.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		System.out.println("\n5.1 Frequency:");
		System.out.println(frequency);
	}

	// 5.2 FIND DUPLICATES
	static void findDuplicates() {

		List<String> skills = List.of("Java", "Spring", "Java", "Kafka", "Java", "Spring");

		Map<String, Long> frequency = skills.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		List<String> duplicates = frequency.entrySet().stream().filter(entry -> entry.getValue() > 1)
				.map(Map.Entry::getKey).toList();

		System.out.println("\n5.2 Duplicates:");
		System.out.println(duplicates);
	}

	// 5.3 FIND UNIQUE ELEMENTS
	static void findUniqueElements() {

		List<String> skills = List.of("Java", "Spring", "Java", "Kafka");

		List<String> unique = skills.stream().distinct().toList();

		System.out.println("\n5.3 Unique Elements:");
		System.out.println(unique);
	}

	// =========================================================
	// 6. REAL INTERVIEW QUESTIONS
	// =========================================================

	// Q1. Sum all values in List<List<Integer>>
	static void question1() {

		List<List<Integer>> numbers = List.of(List.of(1, 2, 3), List.of(4, 5, 6));

		int sum = numbers.stream().flatMap(List::stream).mapToInt(Integer::intValue).sum();

		System.out.println("\nQ1 Sum = " + sum);
	}

	// Q2. Find maximum from nested List
	static void question2() {

		List<List<Integer>> numbers = List.of(List.of(10, 20), List.of(30, 40), List.of(50, 60));

		int max = numbers.stream().flatMap(List::stream).max(Integer::compareTo).orElseThrow();

		System.out.println("\nQ2 Max = " + max);
	}

	// Q3. Find employees who know Java
	static void question3() {

		List<Employee> employees = getEmployees();

		List<Employee> result = employees.stream().filter(e -> e.skills().contains("Java")).toList();

		System.out.println("\nQ3 Java Employees:");
		result.forEach(System.out::println);
	}

	// Q4. Department with highest average salary
	static void question4() {

		List<Employee> employees = getEmployees();

		Map<String, Double> average = employees.stream()
				.collect(Collectors.groupingBy(Employee::department, Collectors.averagingInt(Employee::salary)));

		Map.Entry<String, Double> result = average.entrySet().stream().max(Map.Entry.comparingByValue()).orElseThrow();

		System.out.println("\nQ4 Highest Average Department:");
		System.out.println(result);
	}

	// Q5. Department -> unique skills
	static void question5() {

		List<Employee> employees = getEmployees();

		Map<String, Set<String>> result = employees.stream().collect(Collectors.groupingBy(Employee::department,
				Collectors.flatMapping(e -> e.skills().stream(), Collectors.toSet())));

		System.out.println("\nQ5 Department -> Skills:");
		System.out.println(result);
	}

	// Q6. Convert List<Employee> -> Map<Integer, Employee>
	static void question6() {

		List<Employee> employees = getEmployees();

		Map<Integer, Employee> result = employees.stream().collect(Collectors.toMap(Employee::id, Function.identity()));

		System.out.println("\nQ6 ID -> Employee:");
		System.out.println(result);
	}

	// Q7. Convert List<Employee> -> Map<String, Integer>
	// name -> salary
	static void question7() {

		List<Employee> employees = getEmployees();

		Map<String, Integer> result = employees.stream().collect(Collectors.toMap(Employee::name, Employee::salary));

		System.out.println("\nQ7 Name -> Salary:");
		System.out.println(result);
	}

	// Q8. Find highest paid employee overall
	static void question8() {

		List<Employee> employees = getEmployees();

		Employee result = employees.stream().max(Comparator.comparingInt(Employee::salary)).orElseThrow();

		System.out.println("\nQ8 Highest Paid Employee:");
		System.out.println(result);
	}

	// Q9. Find top employee in every department
	static void question9() {

		List<Employee> employees = getEmployees();

		Map<String, Employee> result = employees.stream()
				.collect(Collectors.groupingBy(Employee::department, Collectors.collectingAndThen(
						Collectors.maxBy(Comparator.comparingInt(Employee::salary)), Optional::orElseThrow)));

		System.out.println("\nQ9 Top Employee Per Department:");
		System.out.println(result);
	}

	// Q10. Group by department and then salary range
	static void question10() {

		List<Employee> employees = getEmployees();

		Map<String, Map<String, List<Employee>>> result = employees.stream().collect(Collectors
				.groupingBy(Employee::department, Collectors.groupingBy(e -> e.salary() >= 75000 ? "HIGH" : "LOW")));

		System.out.println("\nQ10 Nested Grouping:");
		System.out.println(result);
	}

	// =========================================================
	// SAMPLE DATA
	// =========================================================

	static List<Employee> getEmployees() {

		return List.of(

				new Employee(1, "Rahul", "IT", 80000, List.of("Java", "Spring", "Kafka")),

				new Employee(2, "Aman", "IT", 70000, List.of("Java", "AWS")),

				new Employee(3, "Priya", "HR", 60000, List.of("Excel", "Recruitment")),

				new Employee(4, "Neha", "IT", 90000, List.of("Java", "Spring", "AWS")),

				new Employee(5, "Simran", "HR", 65000, List.of("Excel", "Recruitment")));
	}

	// =========================================================
	// EMPLOYEE RECORD
	// =========================================================

	record Employee(int id, String name, String department, int salary, List<String> skills) {
	}
}

/*
 * ================================================================ INTERVIEW
 * CHEAT SHEET ================================================================
 * 
 * NESTED COLLECTION TYPES -----------------------
 * 
 * 1. List<List<T>> List of Lists
 * 
 * 2. List<Set<T>> List of Sets
 * 
 * 3. Set<List<T>> Set of Lists
 * 
 * 4. Set<Set<T>> Set of Sets
 * 
 * 5. Map<K,List<V>> One key -> multiple values
 * 
 * 6. Map<K,Set<V>> One key -> multiple unique values
 * 
 * 7. Map<K,Map<K,V>> Nested key/value structure
 * 
 * 8. List<Map<K,V>> List of JSON-like objects
 * 
 * 
 * IMPORTANT DIFFERENCES ---------------------
 * 
 * List: Allows duplicates Maintains insertion order
 * 
 * Set: Does not allow duplicates HashSet does not guarantee order
 * 
 * Map: Stores key/value pairs Keys are unique
 * 
 * 
 * STREAM METHODS --------------
 * 
 * map() One input -> one output
 * 
 * flatMap() One input -> multiple outputs Then flatten them
 * 
 * filter() Keep matching elements
 * 
 * distinct() Remove duplicates
 * 
 * sorted() Sort elements
 * 
 * groupingBy() Group elements into Map
 * 
 * counting() Count elements
 * 
 * summingInt() Calculate integer sum
 * 
 * averagingInt() Calculate average
 * 
 * maxBy() Find maximum using Comparator
 * 
 * minBy() Find minimum using Comparator
 * 
 * toMap() Convert stream to Map
 * 
 * partitioningBy() Split into true/false groups
 * 
 * 
 * MOST IMPORTANT INTERVIEW PATTERNS ---------------------------------
 * 
 * List<Employee> | | groupingBy(department) v Map<String, List<Employee>>
 * 
 * 
 * List<Employee> | | flatMap(skills) v List<String>
 * 
 * 
 * List<Employee> | | groupingBy(department, | counting()) v Map<String, Long>
 * 
 * 
 * List<Employee> | | groupingBy(department, | maxBy(salary)) v Map<String,
 * Employee>
 * 
 * 
 * List<Employee> | | groupingBy(department, | groupingBy(salaryRange)) v
 * Map<String, Map<String, List<Employee>>>
 * 
 * 
 * COMMON LOGICAL QUESTIONS ------------------------
 * 
 * 1. Flatten List<List<Integer>> 2. Find sum of nested lists 3. Find max/min
 * from nested lists 4. Find duplicate elements 5. Find frequency of elements 6.
 * Find unique elements 7. Group employees by department 8. Count employees per
 * department 9. Calculate average salary per department 10. Find highest salary
 * per department 11. Find second-highest salary 12. Find department with
 * highest average salary 13. Find employees with Java skill 14. Find all unique
 * skills 15. Create Department -> Set<Skill> 16. Convert List<Employee> ->
 * Map<Id, Employee> 17. Sort employees by salary 18. Partition employees by
 * salary 19. Nested grouping 20. Find top employee in every department
 * 
 * 
 * KEY INTERVIEW CONCEPT ---------------------
 * 
 * When you see a nested collection problem, ask:
 * 
 * 1. What is the input type? 2. What is the required output type? 3. Do I need
 * map()? 4. Do I need flatMap()? 5. Do I need groupingBy()? 6. Do I need
 * distinct()? 7. Do I need sorting? 8. Do I need counting/sum/average? 9. Can
 * duplicate keys occur? 10. Do I need a nested grouping?
 * 
 * 
 * EXAMPLE -------
 * 
 * Input:
 * 
 * List<Employee>
 * 
 * Question:
 * 
 * Find highest salary in every department.
 * 
 * Think:
 * 
 * List<Employee> | | groupingBy(department) | | maxBy(salary) v Map<String,
 * Employee>
 * 
 * 
 * ANOTHER EXAMPLE ---------------
 * 
 * Input:
 * 
 * List<Employee>
 * 
 * Question:
 * 
 * Find all unique skills.
 * 
 * Think:
 * 
 * List<Employee> | | flatMap(employee -> employee.skills()) v Stream<String> |
 * | distinct() v List<String>
 * 
 * 
 * ANOTHER EXAMPLE ---------------
 * 
 * Input:
 * 
 * List<Employee>
 * 
 * Question:
 * 
 * Create Department -> unique Skills.
 * 
 * Think:
 * 
 * List<Employee> | | groupingBy(department) | | flatMapping(skills, toSet()) v
 * Map<String, Set<String>>
 * ================================================================
 */