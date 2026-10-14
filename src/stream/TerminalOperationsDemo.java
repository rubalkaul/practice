package stream;

import java.util.*;
import java.util.stream.*;

public class TerminalOperationsDemo {

	public static void main(String[] args) {

		List<Integer> numbers = Arrays.asList(10, 20, 30, 40, 50, 20, 30);

		// 1. forEach()
		System.out.println("1. forEach()");
		numbers.stream().forEach(n -> System.out.println(n));

		// 2. toList()
		System.out.println("\n2. toList()");
		List<Integer> list = numbers.stream().filter(n -> n > 20).toList();

		System.out.println(list);

		// 3. collect()
		System.out.println("\n3. collect()");
		List<Integer> collectedList = numbers.stream().filter(n -> n > 20).collect(Collectors.toList());

		System.out.println(collectedList);

		// 4. count()
		System.out.println("\n4. count()");
		long count = numbers.stream().count();

		System.out.println("Count = " + count);

		// 5. min()
		System.out.println("\n5. min()");
		Optional<Integer> min = numbers.stream().min(Integer::compareTo);

		System.out.println("Minimum = " + min.orElse(null));

		// 6. max()
		System.out.println("\n6. max()");
		Optional<Integer> max = numbers.stream().max(Integer::compareTo);

		System.out.println("Maximum = " + max.orElse(null));

		// 7. findFirst()
		System.out.println("\n7. findFirst()");
		Optional<Integer> first = numbers.stream().findFirst();

		System.out.println("First = " + first.orElse(null));

		// 8. findAny()
		System.out.println("\n8. findAny()");
		Optional<Integer> any = numbers.stream().findAny();

		System.out.println("Any = " + any.orElse(null));

		// 9. anyMatch()
		System.out.println("\n9. anyMatch()");
		boolean anyMatch = numbers.stream().anyMatch(n -> n > 45);

		System.out.println("Any number > 45? " + anyMatch);

		// 10. allMatch()
		System.out.println("\n10. allMatch()");
		boolean allMatch = numbers.stream().allMatch(n -> n > 0);

		System.out.println("All numbers > 0? " + allMatch);

		// 11. noneMatch()
		System.out.println("\n11. noneMatch()");
		boolean noneMatch = numbers.stream().noneMatch(n -> n < 0);

		System.out.println("No number < 0? " + noneMatch);

		// 12. reduce()
		System.out.println("\n12. reduce()");
		Optional<Integer> sum = numbers.stream().reduce((a, b) -> a + b);

		System.out.println("Sum = " + sum.orElse(0));

		// 13. reduce() with identity
		System.out.println("\n13. reduce() with identity");

		int total = numbers.stream().reduce(0, (a, b) -> a + b);

		System.out.println("Total = " + total);

		// 14. toArray()
		System.out.println("\n14. toArray()");

		Object[] array = numbers.stream().toArray();

		System.out.println(Arrays.toString(array));
	}
}