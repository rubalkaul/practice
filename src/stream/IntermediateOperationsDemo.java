package stream;

import java.util.*;
import java.util.stream.*;

public class IntermediateOperationsDemo {

	public static void main(String[] args) {

		List<String> names = Arrays.asList("Rahul", "Amit", "Rohit", "Amit", "Raj", "Ravi", "Rohit", "Ankit");

		// 1. filter()
		System.out.println("1. filter()");
		names.stream().filter(name -> name.startsWith("R")).forEach(System.out::println);

		// 2. map()
		System.out.println("\n2. map()");
		names.stream().map(String::toUpperCase).forEach(System.out::println);

		// 3. distinct()
		System.out.println("\n3. distinct()");
		names.stream().distinct().forEach(System.out::println);

		// 4. sorted()
		System.out.println("\n4. sorted()");
		names.stream().distinct().sorted().forEach(System.out::println);

		// 5. sorted() with Comparator
		System.out.println("\n5. sorted(Comparator)");
		names.stream().distinct().sorted(Comparator.reverseOrder()).forEach(System.out::println);

		// 6. limit()
		System.out.println("\n6. limit()");
		names.stream().limit(3).forEach(System.out::println);

		// 7. skip()
		System.out.println("\n7. skip()");
		names.stream().skip(3).forEach(System.out::println);

		// 8. peek()
		System.out.println("\n8. peek()");
		names.stream().filter(name -> name.length() > 3).peek(name -> System.out.println("After filter: " + name))
				.map(String::toUpperCase).peek(name -> System.out.println("After map: " + name))
				.forEach(System.out::println);

		// 9. flatMap()
		System.out.println("\n9. flatMap()");

		List<List<String>> nestedNames = Arrays.asList(Arrays.asList("Amit", "Rahul"), Arrays.asList("Rohit", "Raj"),
				Arrays.asList("Ankit", "Ravi"));

		nestedNames.stream().flatMap(List::stream).forEach(System.out::println);

		// 10. takeWhile()
		System.out.println("\n10. takeWhile()");

		List<Integer> numbers = Arrays.asList(2, 4, 6, 8, 9, 10, 12);

		numbers.stream().takeWhile(n -> n % 2 == 0).forEach(System.out::println);

		// 11. dropWhile()
		System.out.println("\n11. dropWhile()");

		numbers.stream().dropWhile(n -> n % 2 == 0).forEach(System.out::println);

		// 12. Combining intermediate operations
		System.out.println("\n12. Combining operations");

		names.stream().filter(name -> name.length() > 3).map(String::toUpperCase).distinct().sorted().limit(3)
				.forEach(System.out::println);
	}
}