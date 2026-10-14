package Practice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class MAIN {
	public static void main(String[] args) {

		Map<String, Integer> mapp = new HashMap<>();
		mapp.put("A", 50);
		mapp.put("B", 20);
		mapp.put("C", 40);
		mapp.put("D", 10);
		mapp.entrySet().stream().sorted(Map.Entry.comparingByValue())
				.forEach((entry) -> System.out.println(entry.getKey() + " " + entry.getValue()));

		int[] arr11 = { 1, 3, 5, 7 };
		int[] arr22 = { 2, 4, 6, 8 };
		Set<Integer> mergedArray = new TreeSet<>();
		for (int i : arr11)
			mergedArray.add(i);
		for (int i : arr22)
			mergedArray.add(i);

		System.out.println(mergedArray);

		char[] ch1 = "listen".toCharArray();
		char[] ch2 = "silent".toCharArray();
		Set<Character> set1 = new TreeSet();
		Set<Character> set2 = new TreeSet();
		if (ch1.length == ch2.length) {
			for (char ch : ch1)
				set1.add(ch);
			for (char ch : ch2)
				set2.add(ch);
			System.out.println(set1.equals(set2) ? "yes" : "no");
		} else
			System.out.println("NO");

		int[] rotatearr = { 1, 2, 3, 4, 5, 6, 7 };
		int k = 3;
		int[] newrotatedarray = new int[rotatearr.length];
		int index2 = 0;
		for (int i = rotatearr.length - k; i <= rotatearr.length - 1; i++) {
			newrotatedarray[index2++] = rotatearr[i];
		}
		for (int i = 0; index2 <= rotatearr.length - 1; i++) {
			newrotatedarray[index2++] = rotatearr[i];
		}

		System.out.println(index2 + " " + Arrays.toString(newrotatedarray));

		Integer[] arre = { 5, 1, 2, 3, 1, 4, 4, 4 };
		List<Integer> numbers = Arrays.asList(arre);
		numbers.stream().filter(n -> n % 2 == 0).distinct().forEach(n -> System.out.println(n + ", "));

		numbers.stream().distinct().sorted(Comparator.reverseOrder()).skip(1).findFirst()
				.ifPresent(n -> System.out.println("Second largest: " + n));
		;

		List<Employee> employees = Arrays.asList(new Employee(101, "Amit", "IT", 70000),
				new Employee(102, "Rahul", "HR", 50000), new Employee(103, "Priya", "IT", 90000),
				new Employee(104, "Neha", "Finance", 80000), new Employee(105, "Vikas", "IT", 90000),
				new Employee(106, "Simran", "HR", 60000), new Employee(107, "Arjun", "Finance", 75000));

		Employee highestPaid = employees.stream().max(Comparator.comparingDouble(Employee::getSalary))
				.orElseThrow(() -> new NoSuchElementException("List is empty"));
		System.out.println(highestPaid);

		Optional<Double> secondHighest = employees.stream().map(Employee::getSalary).distinct()
				.sorted(Comparator.reverseOrder()).skip(1).findFirst();
		System.out.println(secondHighest.orElse(-1.0));

		System.out.println(employees.stream().collect(Collectors.groupingBy(Employee::getDepartment)));

		Map<String, Optional<Employee>> highestPaidByDept = employees.stream().collect(Collectors.groupingBy(
				Employee::getDepartment, Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))));

		highestPaidByDept.forEach((dept, emp) -> System.out
				.println(dept + " -> " + emp.get().getName() + " (" + emp.get().getSalary() + ")"));

		Map<String, Long> countByDept = employees.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));

		countByDept.forEach((dept, count) -> System.out.println(dept + " -> " + count));

		Map<String, Integer> map = new HashMap<>();
		map.put("A", 50);
		map.put("B", 20);
		map.put("C", 80);
		map.put("D", 40);
		// Sort the Map by value ascending.
		map.entrySet().stream().sorted(Map.Entry.comparingByValue())
				.forEach(entry -> System.out.println(entry.getKey() + " = " + entry.getValue()));
		int[] arr1 = { 0, 1, 0, 3, 12, 0, 5 };
		int[] newarr = new int[arr1.length];
		int index = 0;
		for (int ele : arr1) {
			if (ele != 0)
				newarr[index++] = ele;
		}
		Arrays.fill(arr1, 0);
		System.out.println("arr1 " + Arrays.toString(arr1));
		System.arraycopy(newarr, 0, arr1, 0, arr1.length);
		newarr = null;
		System.out.println("newarr " + Arrays.toString(newarr));
		System.out.println("arr1 " + Arrays.toString(arr1));

		int n = 29;
		if (n <= 0 || n == 1)
			System.out.println(" prime");
		if (n == 2)
			System.out.println(" na prime");
//
//		for (int i = 3; i * i <= n; i++) {
//			if (n % i == 0) {
//				System.out.println(" na prime");
//				return;
//			}
//		}
		System.out.println(" prime");

		int sumofdigit = 9876;
		int mid = 0;
		int mid2 = 0;
		while (sumofdigit > 0) {
			int i = sumofdigit % 10;
			mid2 += i;
			mid = mid * 10 + i;
			sumofdigit /= 10;
		}
		System.out.println("Reverse of digit in " + sumofdigit + " is = " + mid);
		System.out.println("Sum of digit in " + sumofdigit + " is = " + mid2);
		int palindrom = 12321;
		int original = palindrom;
		int reversed = 0;
		while (palindrom != 0) {
			int digit = palindrom % 10;
			reversed = reversed * 10 + digit;
			palindrom /= 10;
		}
		System.out.println(original == reversed ? "Yess" : "No");

		int[] arr = { 5, 1, 2, 3, 1, 4, 4, 4 };
		Set<Integer> sett = new LinkedHashSet<>();
		Set<Integer> sett2 = new LinkedHashSet<>();
		Map<Integer, Integer> mappp = new LinkedHashMap<>();

		for (int ele : arr) {
			if (!sett.add(ele))
				sett2.add(ele);
		}

		System.out.println(sett);
		System.out.println(sett2);

		List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
		System.out.println(list);

		for (int ele : arr) {
			mappp.put(ele, mappp.getOrDefault(ele, 0) + 1);
		}
		System.out.println(mappp);

		Map<Character, Integer> mapch = new LinkedHashMap<>();
		String str = "programming";
		for (char chr : str.toCharArray()) {
			mapch.put(chr, mapch.getOrDefault(chr, 0) + 1);

		}

		String str2 = "programming";
		char[] chr = str2.toCharArray();
		int left = 0;
		int right = str2.length() - 1;
		while (left < right) {
			char ch = chr[left];
			chr[left] = chr[right];
			chr[right] = ch;
			left++;
			right--;
		}
		for (char c : chr)
			System.out.print(c);

	}
}