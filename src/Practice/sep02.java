package Practice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class sep02 {
	public static void main(String[] args) {
//		int[] arr = { 10, 50, 20, 80, 30, 80 };
//		List<Integer> lst = Arrays.stream(arr).boxed().toList();
//		lst.stream().distinct().forEach(System.out::print);

		List<Employee> employees = List.of(new Employee(1, "Rubal", "Engineering", 95000),
				new Employee(2, "Tejinder", "Engineering", 88000), new Employee(3, "Simran", "HR", 62000));
		System.out.println(employees.stream().max(Comparator.comparingDouble(Employee::getSalary)).get());
		System.out.println(employees.stream().collect(Collectors.groupingBy(Employee::getDepartment)));
	}
}
