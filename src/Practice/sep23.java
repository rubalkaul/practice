package Practice;

import java.util.*;
import java.lang.*;
import java.io.*;

public class sep23 {
	public static void main(String[] args) {

		// Find the longest substring without repeating characters
		List<String> lst = Arrays.asList("Rubal", "ghjkloip", "asdfghjklpoiu", "tgvbujilpohebdm", "qwertyuioplkjhgfdsazxcvbnm");

		List<StringBuffer> lstfinal = new ArrayList<>();
		int lenght = 0;
		String finalStr = "";
		for (String str : lst) {
			char[] charr = str.toCharArray();
			Set<Character> set = new HashSet<>();
			StringBuffer strbfr = new StringBuffer();
			for (char ch : charr) {
				set.add(ch);
			}
			for (char ch : set) {
				strbfr.append(ch);
			}
			lstfinal.add(strbfr);
		}

		for (StringBuffer str : lstfinal) {
			if (lenght < str.capacity()) {
				lenght = str.capacity();
				finalStr = str.toString();
			}
		}
		System.out.println(finalStr);

		/*
		 * // Reverse an integer int num = 123; int newnum = 0; while (num > 0) { int
		 * temp = num % 10; newnum = newnum * 10 + temp; num = num / 10; }
		 * System.out.println(newnum);
		 * 
		 * 
		 * String str = "rubul"; char[] strArr = str.toCharArray(); for (int i = 0; i <
		 * strArr.length / 2; i++) { if (strArr[i] != strArr[strArr.length - 1 - i]) {
		 * System.out.println("No Palindrome"); } }
		 * 
		 * 
		 * int[] arr = { 5, 2, 2, 5, 9, 4, 1, 2, 3, 8, 7, 6, 8, 5 }; Set<Integer> intset
		 * = new HashSet<>(); for (int ele : arr) { intset.add(ele); } //
		 * System.out.println(intset); int uniqueIndex = 0; int[] unique = new
		 * int[arr.length]; for (int i = 0; i < arr.length; i++) { boolean duplicate =
		 * false; for (int j = 0; j < arr.length; j++) { if (arr[i] == unique[j]) {
		 * duplicate = true; } } if (!duplicate) { unique[uniqueIndex++] = arr[i]; } }
		 * for (int i = 0; i < uniqueIndex; i++) { System.out.print(unique[i] + " ,"); }
		 * System.out.println();
		 * 
		 * for (int i = 0; i < arr.length; i++) { int ele = arr[i]; int index = i; for
		 * (int j = i; j < arr.length; j++) { if (ele > arr[j]) { ele = arr[j]; index =
		 * j; } if (arr[i] != ele) { int temp = arr[i]; arr[i] = arr[index]; arr[index]
		 * = temp; } } for (int e : arr) { // System.out.print(e + ","); } //
		 * System.out.println(); } // System.out.println("Selection Sort");
		 * 
		 * int left = 0; int right = arr.length;
		 * 
		 * while (left < right) { int mid = (right - left) / 2; if (2 == arr[mid]) {
		 * System.out.println("Present"); break; } if (2 > arr[mid]) { right = mid; }
		 * else { left = mid; } }
		 * 
		 * for (int ele : arr) { System.out.print(ele + ","); }
		 * 
		 * for (int i = 0; i < arr.length; i++) { for (int j = i; j < arr.length; j++) {
		 * if (arr[i] < arr[j]) { int temp = arr[i]; arr[i] = arr[j]; arr[j] = temp; } }
		 * for (int ele : arr) { // System.out.print(ele + ","); } System.out.println();
		 * }
		 * 
		 * for (int ele : arr) { // System.out.print(ele + ","); }
		 * 
		 */
	}
}
