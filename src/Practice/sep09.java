package Practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class sep09 {
	public static void main(String[] args) {

		int[] arr = { 10, 1, 8, 9 };
		Set st = new HashSet<Integer>();
		List lst = new ArrayList<Integer>();
		for (int ele : arr) {
			if (!st.add(ele))
				lst.add(ele);
		}
		// System.out.println("Uniques " + st);
		// System.out.println("Duplicates " + lst);

		int largest = Integer.MIN_VALUE;
		int secondlargest = Integer.MIN_VALUE;

		for (int ele : arr) {
			if (ele > largest) {
				secondlargest = largest;
				largest = ele;
			}
			if (ele > secondlargest && ele != largest) {
				secondlargest = ele;
			}
		}
		// System.out.println("Largest " + largest + "|| SecondLargest " +
		// secondlargest);

		List lst21 = Arrays.asList(1, 5);
		List lst22 = Arrays.asList(3, 6, 4, 7);
		List lst23 = Arrays.asList(1, lst21, lst22, 8);
		System.out.println(lst23);
		printList(lst23);
	}

	private static void printList(List<?> nestedList) {
		for (Object obj : nestedList) {
			if (obj instanceof List<?>) {
				printList((List<?>) obj);
			} else
				System.out.print(obj + ", ");
		}
	}

}
