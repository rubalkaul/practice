package array;

import java.util.*;

/**
 * =====================================================================================
 * JAVA ARRAYS - COMPLETE INTERVIEW QUESTIONS GUIDE
 * =====================================================================================
 * One file, every classic array interview problem, in order, with runnable
 * examples. Each method includes the APPROACH and TIME/SPACE COMPLEXITY in
 * comments.
 *
 * HOW TO RUN: javac ArrayInterviewQuestions.java java ArrayInterviewQuestions
 *
 * TOPICS (in order): 1. Traverse & Print an Array 2. Find Max and Min 3. Find
 * Second Largest / Second Smallest 4. Reverse an Array (two-pointer, in place)
 * 5. Sum and Average 6. Check if Array is Sorted 7. Rotate Array by K (left &
 * right, using reversal algorithm) 8. Move All Zeroes to End 9. Remove
 * Duplicates from a Sorted Array 10. Find All Duplicates (HashSet) 11. Find the
 * Missing Number (1 to N) 12. Find the Single Non-Repeating Element (XOR trick)
 * 13. Two Sum Problem (HashMap, O(n)) 14. Maximum Subarray Sum -- Kadane's
 * Algorithm 15. Maximum Product Subarray 16. Majority Element -- Boyer-Moore
 * Voting Algorithm 17. Binary Search (iterative + recursive) -- requires sorted
 * array 18. Merge Two Sorted Arrays 19. Intersection of Two Arrays 20. Union of
 * Two Arrays 21. Dutch National Flag -- Sort an array of 0s, 1s, 2s 22.
 * Trapping Rain Water 23. Best Time to Buy and Sell Stock (single pass) 24.
 * Subarray with Given Sum (sliding window, positive numbers) 25. Sliding Window
 * Maximum (Deque) 26. Prefix Sum Array + Range Sum Queries 27. Pair with Given
 * Sum in a Sorted Array (two-pointer) 28. Equilibrium Index 29. Leaders in an
 * Array (element with nothing bigger to its right) 30. Kth Largest Element
 * (PriorityQueue / min-heap)
 * -------------------------------------------------------------------------------------
 * ARRAYS OF OTHER TYPES: NUMBERS, STRINGS, CHARS, OBJECTS, 2D/3D
 * -------------------------------------------------------------------------------------
 * 31. Arrays of Every Primitive/Reference Type (declaration & initialization)
 * 32. Multi-Dimensional Arrays (2D array basics) 33. Jagged Arrays (irregular
 * row lengths) 34. Array of Strings -- common operations (sort, search,
 * longest/shortest) 35. Char Array Basics -- String <-> char[] conversions 36.
 * Palindrome Check (char array, two-pointer) 37. Anagram Check (char frequency
 * count) 38. Character Frequency Count (HashMap<Character,Integer>) 39. Count
 * Vowels and Consonants 40. Array of Objects -- custom class, sorting by field
 * with Comparator 41. 2D Matrix Traversal (row-major & column-major) 42. Matrix
 * Transpose 43. Rotate Matrix 90 Degrees (in place) 44. Spiral Matrix Traversal
 * 45. Search in a Row-wise & Column-wise Sorted 2D Matrix 46. Sum of Matrix
 * Diagonals 47. Array <-> List Conversions 48. java.util.Arrays Utility Methods
 * (sort, fill, copyOf, equals, deepToString...) 49. Varargs (variable-length
 * arguments are just arrays) 50. 3D Arrays (array of 2D arrays) 51. Array
 * Covariance & ArrayStoreException 52. Cloning Arrays -- Shallow Copy vs Deep
 * Copy
 * =====================================================================================
 */
public class MixedArrayInterviewQuestions {

	public static void main(String[] args) {
		q1_TraverseAndPrint();
		q2_FindMaxMin();
		q3_SecondLargestSmallest();
		q4_ReverseArray();
		q5_SumAndAverage();
		q6_CheckSorted();
		q7_RotateArray();
		q8_MoveZeroesToEnd();
		q9_RemoveDuplicatesSorted();
		q10_FindDuplicates();
		q11_FindMissingNumber();
		q12_SingleNonRepeatingElement();
		q13_TwoSum();
		q14_MaxSubarraySumKadane();
		q15_MaxProductSubarray();
		q16_MajorityElement();
		q17_BinarySearch();
		q18_MergeTwoSortedArrays();
		q19_IntersectionOfTwoArrays();
		q20_UnionOfTwoArrays();
		q21_DutchNationalFlag();
		q22_TrappingRainWater();
		q23_BestTimeToBuySellStock();
		q24_SubarrayWithGivenSum();
		q25_SlidingWindowMaximum();
		q26_PrefixSumRangeQueries();
		q27_PairWithGivenSumSorted();
		q28_EquilibriumIndex();
		q29_LeadersInArray();
		q30_KthLargestElement();

		q31_ArraysOfEveryType();
		q32_MultiDimensionalArrays();
		q33_JaggedArrays();
		q34_ArrayOfStringsOperations();
		q35_CharArrayBasics();
		q36_PalindromeCheck();
		q37_AnagramCheck();
		q38_CharacterFrequencyCount();
		q39_CountVowelsConsonants();
		q40_ArrayOfObjects();
		q41_MatrixTraversal();
		q42_MatrixTranspose();
		q43_RotateMatrix90();
		q44_SpiralMatrixTraversal();
		q45_SearchIn2DMatrix();
		q46_SumOfMatrixDiagonals();
		q47_ArrayListConversions();
		q48_ArraysUtilityMethods();
		q49_Varargs();
		q50_ThreeDArrays();
		q51_ArrayCovariance();
		q52_CloneArrays();
	}

	private static void printHeader(String title) {
		System.out.println("\n=====================================================");
		System.out.println(title);
		System.out.println("=====================================================");
	}

	// =====================================================================================
	// 1. TRAVERSE & PRINT AN ARRAY -- the absolute basics
	// =====================================================================================
	// Time: O(n) Space: O(1)
	private static void q1_TraverseAndPrint() {
		printHeader("1. TRAVERSE & PRINT AN ARRAY");
		int[] arr = { 10, 20, 30, 40, 50 };

		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();

		// Enhanced for-loop (use when index isn't needed)
		for (int value : arr) {
			System.out.print(value + " ");
		}
		System.out.println();
	}

	// =====================================================================================
	// 2. FIND MAX AND MIN
	// =====================================================================================
	// Approach: single pass, track running max/min. Time: O(n) Space: O(1)
	private static void q2_FindMaxMin() {
		printHeader("2. FIND MAX AND MIN");
		int[] arr = { 4, 2, 9, 7, 1, 8 };

		int max = arr[0], min = arr[0];
		for (int num : arr) {
			if (num > max)
				max = num;
			if (num < min)
				min = num;
		}
		System.out.println("Array: " + Arrays.toString(arr));
		System.out.println("Max = " + max + ", Min = " + min);
	}

	// =====================================================================================
	// 3. SECOND LARGEST / SECOND SMALLEST
	// =====================================================================================
	// Approach: track two variables (largest, secondLargest) in a single pass.
	// Avoid sorting (O(n log n)) when a single pass (O(n)) is enough.
	private static void q3_SecondLargestSmallest() {
		printHeader("3. SECOND LARGEST / SECOND SMALLEST");
		int[] arr = { 12, 35, 1, 10, 34, 1 };

		int largest = Integer.MIN_VALUE, secondLargest = Integer.MIN_VALUE;
		int smallest = Integer.MAX_VALUE, secondSmallest = Integer.MAX_VALUE;

		for (int num : arr) {
			// Track largest / second largest
			if (num > largest) {
				secondLargest = largest;
				largest = num;
			} else if (num > secondLargest && num != largest) {
				secondLargest = num;
			}
			// Track smallest / second smallest
			if (num < smallest) {
				secondSmallest = smallest;
				smallest = num;
			} else if (num < secondSmallest && num != smallest) {
				secondSmallest = num;
			}
		}
		System.out.println("Array: " + Arrays.toString(arr));
		System.out.println("Largest = " + largest + ", Second Largest = " + secondLargest);
		System.out.println("Smallest = " + smallest + ", Second Smallest = " + secondSmallest);
	}

	// =====================================================================================
	// 4. REVERSE AN ARRAY (IN PLACE) -- classic two-pointer pattern
	// =====================================================================================
	// Approach: swap elements from both ends moving inward. Time: O(n) Space: O(1)
	private static void q4_ReverseArray() {
		printHeader("4. REVERSE AN ARRAY (TWO-POINTER)");
		int[] arr = { 1, 2, 3, 4, 5, 6 };
		System.out.println("Before: " + Arrays.toString(arr));

		int left = 0, right = arr.length - 1;
		while (left < right) {
			int temp = arr[left];
			arr[left] = arr[right];
			arr[right] = temp;
			left++;
			right--;
		}
		System.out.println("After:  " + Arrays.toString(arr));
	}

	// =====================================================================================
	// 5. SUM AND AVERAGE
	// =====================================================================================
	private static void q5_SumAndAverage() {
		printHeader("5. SUM AND AVERAGE");
		int[] arr = { 5, 10, 15, 20, 25 };

		long sum = 0;
		for (int num : arr)
			sum += num;
		double average = (double) sum / arr.length;

		System.out.println("Array: " + Arrays.toString(arr));
		System.out.println("Sum = " + sum + ", Average = " + average);
	}

	// =====================================================================================
	// 6. CHECK IF ARRAY IS SORTED
	// =====================================================================================
	// Approach: compare each element with the next; bail out early on the first
	// violation.
	private static void q6_CheckSorted() {
		printHeader("6. CHECK IF ARRAY IS SORTED");
		int[] sortedArr = { 1, 2, 3, 4, 5 };
		int[] unsortedArr = { 1, 3, 2, 4, 5 };

		System.out.println(Arrays.toString(sortedArr) + " sorted? " + isSortedAscending(sortedArr));
		System.out.println(Arrays.toString(unsortedArr) + " sorted? " + isSortedAscending(unsortedArr));
	}

	private static boolean isSortedAscending(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			if (arr[i] > arr[i + 1])
				return false;
		}
		return true;
	}

	// =====================================================================================
	// 7. ROTATE ARRAY BY K -- using the "reversal algorithm" (O(n) time, O(1)
	// space)
	// =====================================================================================
	// Approach for LEFT rotation by k:
	// 1. Reverse first k elements
	// 2. Reverse remaining n-k elements
	// 3. Reverse the whole array
	// Example: [1,2,3,4,5,6,7] rotate left by 2 -> [3,4,5,6,7,1,2]
	private static void q7_RotateArray() {
		printHeader("7. ROTATE ARRAY BY K (REVERSAL ALGORITHM)");
		int[] arr = { 1, 2, 3, 4, 5, 6, 7 };
		int k = 2;
		System.out.println("Original: " + Arrays.toString(arr));

		rotateLeft(arr, k);
		System.out.println("Rotated left by " + k + ": " + Arrays.toString(arr));

		rotateRight(arr, k); // rotate back right to restore original order
		System.out.println("Rotated right by " + k + " (restored): " + Arrays.toString(arr));
	}

	private static void rotateLeft(int[] arr, int k) {
		int n = arr.length;
		k = k % n;
		reverse(arr, 0, k - 1);
		reverse(arr, k, n - 1);
		reverse(arr, 0, n - 1);
	}

	private static void rotateRight(int[] arr, int k) {
		int n = arr.length;
		k = k % n;
		reverse(arr, 0, n - 1);
		reverse(arr, 0, k - 1);
		reverse(arr, k, n - 1);
	}

	private static void reverse(int[] arr, int start, int end) {
		while (start < end) {
			int temp = arr[start];
			arr[start] = arr[end];
			arr[end] = temp;
			start++;
			end--;
		}
	}

	// =====================================================================================
	// 8. MOVE ALL ZEROES TO THE END -- keep relative order of non-zero elements
	// =====================================================================================
	// Approach: two-pointer. "insertPos" tracks where the next non-zero element
	// should go.
	// Time: O(n) Space: O(1)
	private static void q8_MoveZeroesToEnd() {
		printHeader("8. MOVE ALL ZEROES TO THE END");
		int[] arr = { 0, 1, 0, 3, 12, 0, 5 };
		System.out.println("Before: " + Arrays.toString(arr));

		int insertPos = 0;
		for (int num : arr) {
			if (num != 0) {
				arr[insertPos++] = num;
			}
		}
		while (insertPos < arr.length) {
			arr[insertPos++] = 0;
		}
		System.out.println("After:  " + Arrays.toString(arr));
	}

	// =====================================================================================
	// 9. REMOVE DUPLICATES FROM A SORTED ARRAY (IN PLACE)
	// =====================================================================================
	// Approach: two-pointer. Since the array is sorted, duplicates are always
	// adjacent.
	// Time: O(n) Space: O(1)
	private static void q9_RemoveDuplicatesSorted() {
		printHeader("9. REMOVE DUPLICATES FROM SORTED ARRAY");
		int[] arr = { 1, 1, 2, 2, 2, 3, 4, 4, 5 };
		System.out.println("Before: " + Arrays.toString(arr));

		int uniqueIndex = 0;
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] != arr[uniqueIndex]) {
				uniqueIndex++;
				arr[uniqueIndex] = arr[i];
			}
		}
		int[] result = Arrays.copyOf(arr, uniqueIndex + 1);
		System.out.println("After:  " + Arrays.toString(result));
	}

	// =====================================================================================
	// 10. FIND ALL DUPLICATES (UNSORTED ARRAY) -- using HashSet
	// =====================================================================================
	// Time: O(n) Space: O(n)
	private static void q10_FindDuplicates() {
		printHeader("10. FIND ALL DUPLICATES (HASHSET)");
		int[] arr = { 4, 3, 2, 7, 8, 2, 3, 1 };

		Set<Integer> seen = new HashSet<>();
		Set<Integer> duplicates = new LinkedHashSet<>(); // preserves insertion order
		for (int num : arr) {
			if (!seen.add(num)) { // add() returns false if already present
				duplicates.add(num);
			}
		}
		System.out.println("Array: " + Arrays.toString(arr));
		System.out.println("Duplicates: " + duplicates);
	}

	// =====================================================================================
	// 11. FIND THE MISSING NUMBER (1 to N, one number missing)
	// =====================================================================================
	// Approach: sum formula. Expected sum of 1..n = n*(n+1)/2. Missing = expected -
	// actual.
	// Time: O(n) Space: O(1)
	private static void q11_FindMissingNumber() {
		printHeader("11. FIND THE MISSING NUMBER (1 TO N)");
		int[] arr = { 1, 2, 4, 5, 6 }; // 3 is missing, n = 6
		int n = 6;

		long expectedSum = (long) n * (n + 1) / 2;
		long actualSum = 0;
		for (int num : arr)
			actualSum += num;

		System.out.println("Array: " + Arrays.toString(arr) + " (expected range 1.." + n + ")");
		System.out.println("Missing number = " + (expectedSum - actualSum));
	}

	// =====================================================================================
	// 12. FIND THE SINGLE NON-REPEATING ELEMENT (every other element appears twice)
	// =====================================================================================
	// Approach: XOR trick. a ^ a = 0, and a ^ 0 = a. XOR-ing everything cancels out
	// pairs,
	// leaving only the single element. Time: O(n) Space: O(1) -- no HashSet needed!
	private static void q12_SingleNonRepeatingElement() {
		printHeader("12. SINGLE NON-REPEATING ELEMENT (XOR TRICK)");
		int[] arr = { 4, 1, 2, 1, 2 }; // 4 appears once, rest appear twice

		int result = 0;
		for (int num : arr) {
			result ^= num;
		}
		System.out.println("Array: " + Arrays.toString(arr));
		System.out.println("Single element = " + result);
	}

	// =====================================================================================
	// 13. TWO SUM PROBLEM -- find two numbers that add up to a target
	// =====================================================================================
	// Approach: HashMap stores {value -> index} as we scan. For each number, check
	// if
	// (target - number) was already seen. Time: O(n) Space: O(n)
	// (Brute force nested loop would be O(n^2) -- HashMap avoids that.)
	private static void q13_TwoSum() {
		printHeader("13. TWO SUM PROBLEM (HASHMAP)");
		int[] arr = { 2, 7, 11, 15 };
		int target = 9;

		Map<Integer, Integer> valueToIndex = new HashMap<>();
		int[] resultIndices = null;
		for (int i = 0; i < arr.length; i++) {
			int complement = target - arr[i];
			if (valueToIndex.containsKey(complement)) {
				resultIndices = new int[] { valueToIndex.get(complement), i };
				break;
			}
			valueToIndex.put(arr[i], i);
		}
		System.out.println("Array: " + Arrays.toString(arr) + ", target = " + target);
		System.out.println("Indices: " + Arrays.toString(resultIndices));
	}

	// =====================================================================================
	// 14. MAXIMUM SUBARRAY SUM -- KADANE'S ALGORITHM (one of the most-asked
	// problems)
	// =====================================================================================
	// Approach: keep a running sum "currentSum". If it ever drops below 0, reset it
	// to 0
	// (a negative running sum can only hurt future sums, so discard it). Track the
	// best
	// sum seen so far. Time: O(n) Space: O(1)
	private static void q14_MaxSubarraySumKadane() {
		printHeader("14. MAXIMUM SUBARRAY SUM (KADANE'S ALGORITHM)");
		int[] arr = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };

		int maxSoFar = arr[0];
		int currentSum = arr[0];
		for (int i = 1; i < arr.length; i++) {
			currentSum = Math.max(arr[i], currentSum + arr[i]);
			maxSoFar = Math.max(maxSoFar, currentSum);
		}
		System.out.println("Array: " + Arrays.toString(arr));
		System.out.println("Maximum subarray sum = " + maxSoFar + " (subarray [4,-1,2,1])");
	}

	// =====================================================================================
	// 15. MAXIMUM PRODUCT SUBARRAY
	// =====================================================================================
	// Approach: unlike sum, a negative number can flip a very small product into a
	// very
	// large one. So track BOTH the running max product AND running min product at
	// each
	// step (since min * negative = max). Time: O(n) Space: O(1)
	private static void q15_MaxProductSubarray() {
		printHeader("15. MAXIMUM PRODUCT SUBARRAY");
		int[] arr = { 2, 3, -2, 4 };

		int maxProduct = arr[0], minProduct = arr[0], result = arr[0];
		for (int i = 1; i < arr.length; i++) {
			int num = arr[i];
			if (num < 0) { // swap max/min since multiplying by negative flips order
				int temp = maxProduct;
				maxProduct = minProduct;
				minProduct = temp;
			}
			maxProduct = Math.max(num, maxProduct * num);
			minProduct = Math.min(num, minProduct * num);
			result = Math.max(result, maxProduct);
		}
		System.out.println("Array: " + Arrays.toString(arr));
		System.out.println("Maximum product subarray = " + result + " (subarray [2,3])");
	}

	// =====================================================================================
	// 16. MAJORITY ELEMENT -- BOYER-MOORE VOTING ALGORITHM
	// =====================================================================================
	// Problem: find the element that appears MORE THAN n/2 times.
	// Approach: maintain a "candidate" and a "count". If count hits 0, pick a new
	// candidate. Matching elements increment count, non-matching decrement it. The
	// majority element always survives this cancellation process. Time: O(n) Space:
	// O(1)
	private static void q16_MajorityElement() {
		printHeader("16. MAJORITY ELEMENT (BOYER-MOORE VOTING)");
		int[] arr = { 2, 2, 1, 1, 1, 2, 2 };

		int candidate = arr[0], count = 0;
		for (int num : arr) {
			if (count == 0)
				candidate = num;
			count += (num == candidate) ? 1 : -1;
		}
		System.out.println("Array: " + Arrays.toString(arr));
		System.out.println("Majority element = " + candidate);
	}

	// =====================================================================================
	// 17. BINARY SEARCH -- requires a SORTED array. Iterative + Recursive versions.
	// =====================================================================================
	// Approach: repeatedly halve the search range by comparing the middle element
	// to the
	// target. Time: O(log n) Space: O(1) iterative / O(log n) recursive (call
	// stack)
	private static void q17_BinarySearch() {
		printHeader("17. BINARY SEARCH (ITERATIVE + RECURSIVE)");
		int[] arr = { 2, 4, 6, 8, 10, 12, 14 };
		int target = 10;

		System.out.println("Array: " + Arrays.toString(arr) + ", target = " + target);
		System.out.println("Iterative result -> index " + binarySearchIterative(arr, target));
		System.out.println("Recursive result -> index " + binarySearchRecursive(arr, target, 0, arr.length - 1));
	}

	private static int binarySearchIterative(int[] arr, int target) {
		int low = 0, high = arr.length - 1;
		while (low <= high) {
			int mid = low + (high - low) / 2; // avoids potential overflow of (low+high)/2
			if (arr[mid] == target)
				return mid;
			else if (arr[mid] < target)
				low = mid + 1;
			else
				high = mid - 1;
		}
		return -1;
	}

	private static int binarySearchRecursive(int[] arr, int target, int low, int high) {
		if (low > high)
			return -1;
		int mid = low + (high - low) / 2;
		if (arr[mid] == target)
			return mid;
		else if (arr[mid] < target)
			return binarySearchRecursive(arr, target, mid + 1, high);
		else
			return binarySearchRecursive(arr, target, low, mid - 1);
	}

	// =====================================================================================
	// 18. MERGE TWO SORTED ARRAYS INTO ONE SORTED ARRAY
	// =====================================================================================
	// Approach: two-pointer merge, same core idea as the merge step of Merge Sort.
	// Time: O(n+m) Space: O(n+m)
	private static void q18_MergeTwoSortedArrays() {
		printHeader("18. MERGE TWO SORTED ARRAYS");
		int[] a = { 1, 3, 5, 7 };
		int[] b = { 2, 4, 6, 8, 9 };

		int[] merged = new int[a.length + b.length];
		int i = 0, j = 0, k = 0;
		while (i < a.length && j < b.length) {
			merged[k++] = (a[i] <= b[j]) ? a[i++] : b[j++];
		}
		while (i < a.length)
			merged[k++] = a[i++];
		while (j < b.length)
			merged[k++] = b[j++];

		System.out.println("Array A: " + Arrays.toString(a));
		System.out.println("Array B: " + Arrays.toString(b));
		System.out.println("Merged:  " + Arrays.toString(merged));
	}

	// =====================================================================================
	// 19. INTERSECTION OF TWO ARRAYS (common elements)
	// =====================================================================================
	// Approach: put one array's elements into a HashSet, then scan the other array
	// and
	// keep matches. Time: O(n+m) Space: O(n)
	private static void q19_IntersectionOfTwoArrays() {
		printHeader("19. INTERSECTION OF TWO ARRAYS");
		int[] a = { 1, 2, 2, 3, 4 };
		int[] b = { 2, 2, 4, 6 };

		Set<Integer> setA = new HashSet<>();
		for (int num : a)
			setA.add(num);

		Set<Integer> intersection = new LinkedHashSet<>();
		for (int num : b) {
			if (setA.contains(num))
				intersection.add(num);
		}
		System.out.println("Array A: " + Arrays.toString(a));
		System.out.println("Array B: " + Arrays.toString(b));
		System.out.println("Intersection: " + intersection);
	}

	// =====================================================================================
	// 20. UNION OF TWO ARRAYS (all distinct elements from both)
	// =====================================================================================
	// Approach: add every element from both arrays into a single LinkedHashSet
	// (automatically de-duplicates, keeps first-seen order). Time: O(n+m) Space:
	// O(n+m)
	private static void q20_UnionOfTwoArrays() {
		printHeader("20. UNION OF TWO ARRAYS");
		int[] a = { 1, 2, 2, 3 };
		int[] b = { 3, 4, 5, 5 };

		Set<Integer> union = new LinkedHashSet<>();
		for (int num : a)
			union.add(num);
		for (int num : b)
			union.add(num);

		System.out.println("Array A: " + Arrays.toString(a));
		System.out.println("Array B: " + Arrays.toString(b));
		System.out.println("Union: " + union);
	}

	// =====================================================================================
	// 21. DUTCH NATIONAL FLAG -- SORT AN ARRAY OF ONLY 0s, 1s, 2s
	// =====================================================================================
	// Approach: three pointers (low, mid, high) partition the array into three
	// regions
	// in a SINGLE pass -- no need for a general-purpose sort. Time: O(n) Space:
	// O(1)
	private static void q21_DutchNationalFlag() {
		printHeader("21. DUTCH NATIONAL FLAG (SORT 0s, 1s, 2s)");
		int[] arr = { 2, 0, 1, 2, 1, 0, 0, 1 };
		System.out.println("Before: " + Arrays.toString(arr));

		int low = 0, mid = 0, high = arr.length - 1;
		while (mid <= high) {
			if (arr[mid] == 0) {
				swap(arr, low, mid);
				low++;
				mid++;
			} else if (arr[mid] == 1) {
				mid++;
			} else { // arr[mid] == 2
				swap(arr, mid, high);
				high--; // don't increment mid -- need to re-check the swapped-in value
			}
		}
		System.out.println("After:  " + Arrays.toString(arr));
	}

	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	// =====================================================================================
	// 22. TRAPPING RAIN WATER -- how much water can be trapped between bars of a
	// histogram
	// =====================================================================================
	// Approach: water trapped at index i = min(maxLeft[i], maxRight[i]) -
	// height[i].
	// Precompute maxLeft and maxRight arrays, then sum the trapped water at each
	// index.
	// Time: O(n) Space: O(n) (can be optimized to O(1) space with two pointers)
	private static void q22_TrappingRainWater() {
		printHeader("22. TRAPPING RAIN WATER");
		int[] height = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };

		int n = height.length;
		int[] maxLeft = new int[n];
		int[] maxRight = new int[n];

		maxLeft[0] = height[0];
		for (int i = 1; i < n; i++)
			maxLeft[i] = Math.max(maxLeft[i - 1], height[i]);

		maxRight[n - 1] = height[n - 1];
		for (int i = n - 2; i >= 0; i--)
			maxRight[i] = Math.max(maxRight[i + 1], height[i]);

		int totalWater = 0;
		for (int i = 0; i < n; i++) {
			totalWater += Math.min(maxLeft[i], maxRight[i]) - height[i];
		}
		System.out.println("Heights: " + Arrays.toString(height));
		System.out.println("Total trapped water = " + totalWater + " units");
	}

	// =====================================================================================
	// 23. BEST TIME TO BUY AND SELL STOCK -- maximize profit from ONE buy + ONE
	// sell
	// =====================================================================================
	// Approach: track the minimum price seen so far while scanning; at each day,
	// check
	// the profit if we sold today (price - minPriceSoFar). Time: O(n) Space: O(1)
	private static void q23_BestTimeToBuySellStock() {
		printHeader("23. BEST TIME TO BUY AND SELL STOCK");
		int[] prices = { 7, 1, 5, 3, 6, 4 };

		int minPrice = Integer.MAX_VALUE;
		int maxProfit = 0;
		for (int price : prices) {
			minPrice = Math.min(minPrice, price);
			maxProfit = Math.max(maxProfit, price - minPrice);
		}
		System.out.println("Prices: " + Arrays.toString(prices));
		System.out.println("Max profit = " + maxProfit + " (buy at 1, sell at 6)");
	}

	// =====================================================================================
	// 24. SUBARRAY WITH GIVEN SUM (positive numbers only) -- sliding window
	// =====================================================================================
	// Approach: expand the window by moving "end" forward, adding to windowSum. If
	// windowSum exceeds target, shrink from "start" until it's back in range.
	// Time: O(n) Space: O(1) -- much better than the O(n^2) brute-force nested
	// loop.
	private static void q24_SubarrayWithGivenSum() {
		printHeader("24. SUBARRAY WITH GIVEN SUM (SLIDING WINDOW)");
		int[] arr = { 1, 4, 20, 3, 10, 5 };
		int targetSum = 33;

		int start = 0, windowSum = 0;
		int foundStart = -1, foundEnd = -1;
		for (int end = 0; end < arr.length; end++) {
			windowSum += arr[end];
			while (windowSum > targetSum && start < end) {
				windowSum -= arr[start];
				start++;
			}
			if (windowSum == targetSum) {
				foundStart = start;
				foundEnd = end;
				break;
			}
		}
		System.out.println("Array: " + Arrays.toString(arr) + ", target sum = " + targetSum);
		if (foundStart != -1) {
			System.out.println("Found subarray: indices [" + foundStart + ".." + foundEnd + "] = "
					+ Arrays.toString(Arrays.copyOfRange(arr, foundStart, foundEnd + 1)));
		} else {
			System.out.println("No subarray found with that sum");
		}
	}

	// =====================================================================================
	// 25. SLIDING WINDOW MAXIMUM -- max of every window of size K
	// =====================================================================================
	// Approach: use a Deque to store INDICES, keeping it in decreasing order of
	// value.
	// Front of deque is always the max for the current window. Remove indices that
	// fall
	// out of the window, and remove smaller values from the back before adding new
	// ones.
	// Time: O(n) -- each index is added/removed at most once. Space: O(k)
	private static void q25_SlidingWindowMaximum() {
		printHeader("25. SLIDING WINDOW MAXIMUM");
		int[] arr = { 1, 3, -1, -3, 5, 3, 6, 7 };
		int k = 3;

		Deque<Integer> deque = new ArrayDeque<>(); // stores indices
		List<Integer> result = new ArrayList<>();

		for (int i = 0; i < arr.length; i++) {
			// Remove indices that are out of the current window
			while (!deque.isEmpty() && deque.peekFirst() <= i - k) {
				deque.pollFirst();
			}
			// Remove indices whose values are smaller than the current value
			// (they can never be the max while arr[i] is still in the window)
			while (!deque.isEmpty() && arr[deque.peekLast()] < arr[i]) {
				deque.pollLast();
			}
			deque.offerLast(i);

			if (i >= k - 1) { // window is fully formed
				result.add(arr[deque.peekFirst()]);
			}
		}
		System.out.println("Array: " + Arrays.toString(arr) + ", window size k = " + k);
		System.out.println("Max of each window: " + result);
	}

	// =====================================================================================
	// 26. PREFIX SUM ARRAY + RANGE SUM QUERIES
	// =====================================================================================
	// Approach: precompute prefix[i] = sum of arr[0..i-1]. Then any range sum
	// [l..r] can
	// be answered in O(1) as prefix[r+1] - prefix[l], instead of re-summing each
	// time.
	// Build: O(n) Each query: O(1) -- huge win if you have many queries.
	private static void q26_PrefixSumRangeQueries() {
		printHeader("26. PREFIX SUM ARRAY + RANGE SUM QUERIES");
		int[] arr = { 2, 4, 6, 8, 10 };

		int[] prefix = new int[arr.length + 1]; // prefix[0] = 0
		for (int i = 0; i < arr.length; i++) {
			prefix[i + 1] = prefix[i] + arr[i];
		}
		System.out.println("Array:  " + Arrays.toString(arr));
		System.out.println("Prefix: " + Arrays.toString(prefix));

		int l = 1, r = 3; // sum of arr[1..3] = 4+6+8 = 18
		int rangeSum = prefix[r + 1] - prefix[l];
		System.out.println("Sum of range [" + l + ".." + r + "] = " + rangeSum);
	}

	// =====================================================================================
	// 27. PAIR WITH GIVEN SUM IN A SORTED ARRAY -- two-pointer (no extra space
	// needed)
	// =====================================================================================
	// Approach: since the array is SORTED, start pointers at both ends. If the sum
	// is too
	// small, move left pointer right (increase sum); if too big, move right pointer
	// left.
	// Time: O(n) Space: O(1) -- better than HashMap approach (O(n) space) when
	// sorted.
	private static void q27_PairWithGivenSumSorted() {
		printHeader("27. PAIR WITH GIVEN SUM (SORTED ARRAY, TWO-POINTER)");
		int[] arr = { 1, 2, 3, 4, 6, 8, 9 };
		int target = 10;

		int left = 0, right = arr.length - 1;
		int[] pair = null;
		while (left < right) {
			int sum = arr[left] + arr[right];
			if (sum == target) {
				pair = new int[] { arr[left], arr[right] };
				break;
			} else if (sum < target) {
				left++;
			} else {
				right--;
			}
		}
		System.out.println("Array: " + Arrays.toString(arr) + ", target = " + target);
		System.out.println("Pair found: " + Arrays.toString(pair));
	}

	// =====================================================================================
	// 28. EQUILIBRIUM INDEX -- an index where left-side sum == right-side sum
	// =====================================================================================
	// Approach: compute total sum first. Then walk left to right, tracking leftSum;
	// the
	// right sum at index i is totalSum - leftSum - arr[i]. Time: O(n) Space: O(1)
	private static void q28_EquilibriumIndex() {
		printHeader("28. EQUILIBRIUM INDEX");
		int[] arr = { -7, 1, 5, 2, -4, 3, 0 };

		int totalSum = 0;
		for (int num : arr)
			totalSum += num;

		int leftSum = 0;
		int equilibriumIndex = -1;
		for (int i = 0; i < arr.length; i++) {
			int rightSum = totalSum - leftSum - arr[i];
			if (leftSum == rightSum) {
				equilibriumIndex = i;
				break;
			}
			leftSum += arr[i];
		}
		System.out.println("Array: " + Arrays.toString(arr));
		System.out.println("Equilibrium index = " + equilibriumIndex);
	}

	// =====================================================================================
	// 29. LEADERS IN AN ARRAY -- an element is a "leader" if nothing to its right
	// is bigger
	// =====================================================================================
	// Approach: scan from RIGHT to LEFT, tracking the max seen so far. If the
	// current
	// element is >= that max, it's a leader (and becomes the new max). Time: O(n)
	// Space: O(1)
	// (excluding the output list)
	private static void q29_LeadersInArray() {
		printHeader("29. LEADERS IN AN ARRAY");
		int[] arr = { 16, 17, 4, 3, 5, 2 };

		List<Integer> leaders = new ArrayList<>();
		int maxFromRight = Integer.MIN_VALUE;
		for (int i = arr.length - 1; i >= 0; i--) {
			if (arr[i] >= maxFromRight) {
				leaders.add(arr[i]);
				maxFromRight = arr[i];
			}
		}
		Collections.reverse(leaders); // we collected them right-to-left; reverse for natural order
		System.out.println("Array: " + Arrays.toString(arr));
		System.out.println("Leaders: " + leaders);
	}

	// =====================================================================================
	// 30. KTH LARGEST ELEMENT -- using a min-heap (PriorityQueue) of size K
	// =====================================================================================
	// Approach: maintain a min-heap of size k. Push every element; whenever heap
	// size
	// exceeds k, pop the smallest. After processing all elements, the heap's
	// smallest
	// element (its root) is the Kth largest overall. Time: O(n log k) Space: O(k)
	// (Better than sorting the whole array -- O(n log n) -- when k is small.)
	private static void q30_KthLargestElement() {
		printHeader("30. KTH LARGEST ELEMENT (MIN-HEAP)");
		int[] arr = { 3, 2, 1, 5, 6, 4 };
		int k = 2;

		PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // natural ordering = min-heap
		for (int num : arr) {
			minHeap.offer(num);
			if (minHeap.size() > k) {
				minHeap.poll(); // remove the smallest, keeping only the top k largest
			}
		}
		System.out.println("Array: " + Arrays.toString(arr) + ", k = " + k);
		System.out.println("Kth largest element = " + minHeap.peek());
	}

	// =====================================================================================
	// 31. ARRAYS OF EVERY PRIMITIVE / REFERENCE TYPE -- declaration &
	// initialization
	// =====================================================================================
	// Java arrays can hold any type: the 8 primitives (int, double, char, boolean,
	// long,
	// float, byte, short) OR any reference type (String, custom objects, other
	// arrays).
	// Default values differ: numeric types default to 0, boolean to false, char to
	// '\u0000',
	// and any reference type (String, Object, ...) defaults to null.
	private static void q31_ArraysOfEveryType() {
		printHeader("31. ARRAYS OF EVERY TYPE (DECLARATION & INITIALIZATION)");

		int[] intArr = { 1, 2, 3 };
		double[] doubleArr = { 1.1, 2.2, 3.3 };
		long[] longArr = { 100L, 200L, 300L };
		float[] floatArr = { 1.5f, 2.5f };
		byte[] byteArr = { 1, 2, 3 };
		short[] shortArr = { 10, 20, 30 };
		char[] charArr = { 'J', 'a', 'v', 'a' };
		boolean[] booleanArr = { true, false, true };
		String[] stringArr = { "Rubal", "Tejinder", "Aman" };
		Object[] objectArr = { "mixed", 42, 3.14, true }; // Object[] can hold ANY reference type

		System.out.println("int[]     : " + Arrays.toString(intArr));
		System.out.println("double[]  : " + Arrays.toString(doubleArr));
		System.out.println("long[]    : " + Arrays.toString(longArr));
		System.out.println("float[]   : " + Arrays.toString(floatArr));
		System.out.println("byte[]    : " + Arrays.toString(byteArr));
		System.out.println("short[]   : " + Arrays.toString(shortArr));
		System.out.println("char[]    : " + Arrays.toString(charArr) + "  (as String: " + new String(charArr) + ")");
		System.out.println("boolean[] : " + Arrays.toString(booleanArr));
		System.out.println("String[]  : " + Arrays.toString(stringArr));
		System.out.println("Object[]  : " + Arrays.toString(objectArr));

		// Default values when an array is created with "new" but not explicitly filled
		int[] defaultInts = new int[3];
		boolean[] defaultBooleans = new boolean[3];
		char[] defaultChars = new char[3];
		String[] defaultStrings = new String[3];
		System.out.println("\nDefault int[3]     : " + Arrays.toString(defaultInts) + " (defaults to 0)");
		System.out.println("Default boolean[3] : " + Arrays.toString(defaultBooleans) + " (defaults to false)");
		System.out.println(
				"Default char[3]    : " + Arrays.toString(defaultChars) + " (defaults to '\\u0000', prints blank)");
		System.out.println(
				"Default String[3]  : " + Arrays.toString(defaultStrings) + " (defaults to null -- reference type)");
	}

	// =====================================================================================
	// 32. MULTI-DIMENSIONAL ARRAYS -- 2D array basics (an "array of arrays")
	// =====================================================================================
	// A 2D array in Java is literally an array where each element is itself an
	// array.
	// arr[row][col] accesses row "row", column "col". Time to traverse fully:
	// O(rows*cols)
	private static void q32_MultiDimensionalArrays() {
		printHeader("32. MULTI-DIMENSIONAL ARRAYS (2D BASICS)");

		int[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };

		System.out.println("Full matrix: " + Arrays.deepToString(matrix));
		System.out.println("matrix[1][2] (row 1, col 2) = " + matrix[1][2]); // 6
		System.out.println("Number of rows = " + matrix.length);
		System.out.println("Number of cols in row 0 = " + matrix[0].length);

		// Alternative creation: new int[rows][cols], filled afterward
		int[][] grid = new int[2][3];
		for (int r = 0; r < grid.length; r++) {
			for (int c = 0; c < grid[r].length; c++) {
				grid[r][c] = r * 3 + c;
			}
		}
		System.out.println("Manually filled grid: " + Arrays.deepToString(grid));
	}

	// =====================================================================================
	// 33. JAGGED ARRAYS -- rows with DIFFERENT lengths (Java allows this; C/C++
	// doesn't)
	// =====================================================================================
	// Since a 2D array is really "an array of array references," each row can
	// independently
	// point to an array of any length -- this is called a jagged (ragged) array.
	private static void q33_JaggedArrays() {
		printHeader("33. JAGGED ARRAYS (IRREGULAR ROW LENGTHS)");

		int[][] jagged = new int[3][]; // only specify number of rows, not column count
		jagged[0] = new int[] { 1 };
		jagged[1] = new int[] { 1, 2, 3 };
		jagged[2] = new int[] { 1, 2 };

		System.out.println("Jagged array: " + Arrays.deepToString(jagged));
		for (int i = 0; i < jagged.length; i++) {
			System.out.println("Row " + i + " has " + jagged[i].length + " column(s)");
		}
	}

	// =====================================================================================
	// 34. ARRAY OF STRINGS -- common operations
	// =====================================================================================
	// Approach: String[] sorts lexicographically (dictionary order) by default via
	// Arrays.sort(), since String implements Comparable<String>. Time: O(n log n)
	// to sort.
	private static void q34_ArrayOfStringsOperations() {
		printHeader("34. ARRAY OF STRINGS -- COMMON OPERATIONS");

		String[] names = { "Rubal", "Aman", "Tejinder", "Bob", "Zara" };
		System.out.println("Original: " + Arrays.toString(names));

		String[] sortedNames = names.clone(); // clone so we don't mutate the original
		Arrays.sort(sortedNames);
		System.out.println("Sorted (lexicographic): " + Arrays.toString(sortedNames));

		// Longest and shortest string -- single pass
		String longest = names[0], shortest = names[0];
		for (String name : names) {
			if (name.length() > longest.length())
				longest = name;
			if (name.length() < shortest.length())
				shortest = name;
		}
		System.out.println("Longest = " + longest + ", Shortest = " + shortest);

		// Search for a value (unsorted -> linear scan; sorted -> could binarySearch)
		String target = "Tejinder";
		boolean found = false;
		for (String name : names) {
			if (name.equals(target)) {
				found = true;
				break;
			}
		}
		System.out.println("Contains \"" + target + "\"? " + found);

		// Concatenate all strings using String.join
		System.out.println("Joined with ', ': " + String.join(", ", names));
	}

	// =====================================================================================
	// 35. CHAR ARRAY BASICS -- String <-> char[] conversions
	// =====================================================================================
	// Strings in Java are immutable, so many algorithms convert a String to a
	// char[] first
	// to do in-place mutation-style work, then convert back with "new
	// String(charArray)".
	private static void q35_CharArrayBasics() {
		printHeader("35. CHAR ARRAY BASICS (STRING <-> char[])");

		String text = "Kafka";
		char[] chars = text.toCharArray(); // String -> char[]
		System.out.println("Original string: " + text);
		System.out.println("As char[]: " + Arrays.toString(chars));

		// Mutate the char array in place (Strings themselves can't be mutated)
		chars[0] = 'K'; // already uppercase, but demonstrates direct index mutation is legal
		chars[chars.length - 1] = 'A';
		String rebuilt = new String(chars); // char[] -> String
		System.out.println("Rebuilt string after mutation: " + rebuilt);

		// Access individual characters directly on a String without converting
		System.out.println("charAt(0) = " + text.charAt(0));
	}

	// =====================================================================================
	// 36. PALINDROME CHECK -- using a char array + two-pointer technique
	// =====================================================================================
	// Approach: convert to char[], compare from both ends moving inward. Time: O(n)
	// Space: O(n)
	private static void q36_PalindromeCheck() {
		printHeader("36. PALINDROME CHECK (CHAR ARRAY, TWO-POINTER)");

		String word1 = "madam";
		String word2 = "hello";

		System.out.println("\"" + word1 + "\" is palindrome? " + isPalindrome(word1));
		System.out.println("\"" + word2 + "\" is palindrome? " + isPalindrome(word2));
	}

	private static boolean isPalindrome(String str) {
		char[] chars = str.toCharArray();
		int left = 0, right = chars.length - 1;
		while (left < right) {
			if (chars[left] != chars[right])
				return false;
			left++;
			right--;
		}
		return true;
	}

	// =====================================================================================
	// 37. ANAGRAM CHECK -- do two strings use exactly the same letters (same
	// frequency)?
	// =====================================================================================
	// Approach: build a frequency count array of size 26 (for lowercase a-z).
	// Increment for
	// string 1's letters, decrement for string 2's letters. If all counts return to
	// 0, and
	// lengths matched, they're anagrams. Time: O(n) Space: O(1) -- fixed size 26
	// array.
	private static void q37_AnagramCheck() {
		printHeader("37. ANAGRAM CHECK (CHAR FREQUENCY COUNT)");

		String s1 = "listen";
		String s2 = "silent";
		String s3 = "hello";

		System.out.println("\"" + s1 + "\" & \"" + s2 + "\" anagrams? " + isAnagram(s1, s2));
		System.out.println("\"" + s1 + "\" & \"" + s3 + "\" anagrams? " + isAnagram(s1, s3));
	}

	private static boolean isAnagram(String a, String b) {
		if (a.length() != b.length())
			return false;

		int[] freq = new int[26]; // assumes lowercase a-z only
		for (char c : a.toCharArray())
			freq[c - 'a']++;
		for (char c : b.toCharArray())
			freq[c - 'a']--;

		for (int count : freq) {
			if (count != 0)
				return false;
		}
		return true;
	}

	// =====================================================================================
	// 38. CHARACTER FREQUENCY COUNT -- using HashMap<Character,Integer> (works for
	// any chars)
	// =====================================================================================
	// Unlike the fixed-size array trick above (which only works for a-z), a HashMap
	// handles
	// ANY character (uppercase, digits, symbols, unicode). Time: O(n) Space: O(k)
	// distinct chars.
	private static void q38_CharacterFrequencyCount() {
		printHeader("38. CHARACTER FREQUENCY COUNT (HASHMAP)");

		String text = "programming";
		Map<Character, Integer> freqMap = new LinkedHashMap<>(); // preserves first-seen order

		for (char c : text.toCharArray()) {
			freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
		}
		System.out.println("Text: \"" + text + "\"");
		System.out.println("Frequency map: " + freqMap);

		// Find the first non-repeating character -- a very common follow-up question
		char firstNonRepeating = '\0';
		for (char c : text.toCharArray()) {
			if (freqMap.get(c) == 1) {
				firstNonRepeating = c;
				break;
			}
		}
		System.out.println("First non-repeating character: " + firstNonRepeating);
	}

	// =====================================================================================
	// 39. COUNT VOWELS AND CONSONANTS
	// =====================================================================================
	// Approach: single pass over the char array, classify each letter. Time: O(n)
	// Space: O(1)
	private static void q39_CountVowelsConsonants() {
		printHeader("39. COUNT VOWELS AND CONSONANTS");

		String sentence = "Java Lambda Expressions";
		int vowels = 0, consonants = 0;

		for (char c : sentence.toLowerCase().toCharArray()) {
			if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
				vowels++;
			} else if (c >= 'a' && c <= 'z') { // letter, but not a vowel
				consonants++;
			} // anything else (spaces, etc.) is ignored
		}
		System.out.println("Sentence: \"" + sentence + "\"");
		System.out.println("Vowels = " + vowels + ", Consonants = " + consonants);
	}

	// =====================================================================================
	// 40. ARRAY OF OBJECTS -- custom class, sorting by a field
	// =====================================================================================
	// Approach: arrays can hold any reference type, including your own classes. To
	// sort by
	// a specific field, provide a Comparator (via lambda or Comparator.comparing).
	record Product(String name, double price) {
	}

	private static void q40_ArrayOfObjects() {
		printHeader("40. ARRAY OF OBJECTS (CUSTOM CLASS, SORT BY FIELD)");

		Product[] products = { new Product("Keyboard", 45.99), new Product("Monitor", 199.99),
				new Product("Mouse", 15.50) };
		System.out.println("Original: " + Arrays.toString(products));

		Arrays.sort(products, Comparator.comparingDouble(Product::price));
		System.out.println("Sorted by price: " + Arrays.toString(products));

		Arrays.sort(products, Comparator.comparing(Product::name));
		System.out.println("Sorted by name:  " + Arrays.toString(products));
	}

	// =====================================================================================
	// 41. 2D MATRIX TRAVERSAL -- row-major vs column-major order
	// =====================================================================================
	// Row-major: outer loop = rows, inner loop = columns (the natural/default way).
	// Column-major: outer loop = columns, inner loop = rows (visits down each
	// column first).
	private static void q41_MatrixTraversal() {
		printHeader("41. 2D MATRIX TRAVERSAL (ROW-MAJOR & COLUMN-MAJOR)");

		int[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 } };

		System.out.print("Row-major order:    ");
		for (int r = 0; r < matrix.length; r++) {
			for (int c = 0; c < matrix[r].length; c++) {
				System.out.print(matrix[r][c] + " ");
			}
		}
		System.out.println();

		System.out.print("Column-major order: ");
		for (int c = 0; c < matrix[0].length; c++) {
			for (int r = 0; r < matrix.length; r++) {
				System.out.print(matrix[r][c] + " ");
			}
		}
		System.out.println();
	}

	// =====================================================================================
	// 42. MATRIX TRANSPOSE -- flip rows and columns (matrix[r][c] -> result[c][r])
	// =====================================================================================
	// Time: O(rows*cols) Space: O(rows*cols) for the result matrix
	private static void q42_MatrixTranspose() {
		printHeader("42. MATRIX TRANSPOSE");

		int[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 } };
		System.out.println("Original (2x3): " + Arrays.deepToString(matrix));

		int rows = matrix.length, cols = matrix[0].length;
		int[][] transposed = new int[cols][rows]; // dimensions flip
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				transposed[c][r] = matrix[r][c];
			}
		}
		System.out.println("Transposed (3x2): " + Arrays.deepToString(transposed));
	}

	// =====================================================================================
	// 43. ROTATE MATRIX 90 DEGREES CLOCKWISE -- IN PLACE (square matrix only)
	// =====================================================================================
	// Approach: 1) Transpose the matrix in place. 2) Reverse each row.
	// This two-step trick avoids needing a separate output matrix. Time: O(n^2)
	// Space: O(1)
	private static void q43_RotateMatrix90() {
		printHeader("43. ROTATE MATRIX 90 DEGREES (IN PLACE)");

		int[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		System.out.println("Before: " + Arrays.deepToString(matrix));

		int n = matrix.length;
		// Step 1: transpose in place (swap matrix[r][c] with matrix[c][r])
		for (int r = 0; r < n; r++) {
			for (int c = r + 1; c < n; c++) {
				int temp = matrix[r][c];
				matrix[r][c] = matrix[c][r];
				matrix[c][r] = temp;
			}
		}
		// Step 2: reverse each row
		for (int[] row : matrix) {
			for (int left = 0, right = row.length - 1; left < right; left++, right--) {
				int temp = row[left];
				row[left] = row[right];
				row[right] = temp;
			}
		}
		System.out.println("After 90-deg rotation: " + Arrays.deepToString(matrix));
	}

	// =====================================================================================
	// 44. SPIRAL MATRIX TRAVERSAL -- visit elements in a clockwise spiral
	// =====================================================================================
	// Approach: maintain 4 boundaries (top, bottom, left, right). Traverse the top
	// row left
	// to right, then the right column top to bottom, then the bottom row right to
	// left, then
	// the left column bottom to top -- shrinking boundaries after each side. Time:
	// O(rows*cols)
	private static void q44_SpiralMatrixTraversal() {
		printHeader("44. SPIRAL MATRIX TRAVERSAL");

		int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
		System.out.println("Matrix: " + Arrays.deepToString(matrix));

		List<Integer> spiralOrder = new ArrayList<>();
		int top = 0, bottom = matrix.length - 1;
		int left = 0, right = matrix[0].length - 1;

		while (top <= bottom && left <= right) {
			for (int c = left; c <= right; c++)
				spiralOrder.add(matrix[top][c]);
			top++;
			for (int r = top; r <= bottom; r++)
				spiralOrder.add(matrix[r][right]);
			right--;
			if (top <= bottom) {
				for (int c = right; c >= left; c--)
					spiralOrder.add(matrix[bottom][c]);
				bottom--;
			}
			if (left <= right) {
				for (int r = bottom; r >= top; r--)
					spiralOrder.add(matrix[r][left]);
				left++;
			}
		}
		System.out.println("Spiral order: " + spiralOrder);
	}

	// =====================================================================================
	// 45. SEARCH IN A ROW-WISE & COLUMN-WISE SORTED 2D MATRIX
	// =====================================================================================
	// Approach: start at the TOP-RIGHT corner. If current > target, move left
	// (eliminate
	// column). If current < target, move down (eliminate row). This works because
	// rows
	// increase left-to-right and columns increase top-to-bottom. Time: O(rows+cols)
	// Space: O(1)
	private static void q45_SearchIn2DMatrix() {
		printHeader("45. SEARCH IN SORTED 2D MATRIX (ROWS & COLS SORTED)");

		int[][] matrix = { { 1, 4, 7, 11 }, { 2, 5, 8, 12 }, { 3, 6, 9, 16 } };
		int target = 8;
		System.out.println("Matrix: " + Arrays.deepToString(matrix) + ", target = " + target);

		int row = 0, col = matrix[0].length - 1; // start top-right
		boolean found = false;
		while (row < matrix.length && col >= 0) {
			if (matrix[row][col] == target) {
				found = true;
				System.out.println("Found at [" + row + "][" + col + "]");
				break;
			} else if (matrix[row][col] > target) {
				col--; // too big -- eliminate this column
			} else {
				row++; // too small -- eliminate this row
			}
		}
		if (!found)
			System.out.println("Target not found");
	}

	// =====================================================================================
	// 46. SUM OF MATRIX DIAGONALS -- primary diagonal and secondary (anti) diagonal
	// =====================================================================================
	// Primary diagonal: matrix[i][i]. Secondary diagonal: matrix[i][n-1-i]. Time:
	// O(n)
	private static void q46_SumOfMatrixDiagonals() {
		printHeader("46. SUM OF MATRIX DIAGONALS");

		int[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		System.out.println("Matrix: " + Arrays.deepToString(matrix));

		int n = matrix.length;
		int primarySum = 0, secondarySum = 0;
		for (int i = 0; i < n; i++) {
			primarySum += matrix[i][i];
			secondarySum += matrix[i][n - 1 - i];
		}
		System.out.println("Primary diagonal sum (top-left to bottom-right) = " + primarySum);
		System.out.println("Secondary diagonal sum (top-right to bottom-left) = " + secondarySum);
	}

	// =====================================================================================
	// 47. ARRAY <-> LIST CONVERSIONS
	// =====================================================================================
	// Common interview gotcha: Arrays.asList() returns a FIXED-SIZE list backed by
	// the
	// array -- you can SET elements but cannot add()/remove() from it (throws
	// UnsupportedOperationException). For a fully mutable list, wrap it in "new
	// ArrayList<>(...)".
	private static void q47_ArrayListConversions() {
		printHeader("47. ARRAY <-> LIST CONVERSIONS");

		Integer[] arr = { 1, 2, 3, 4 }; // must be Integer[], NOT int[], to work with generics

		List<Integer> fixedSizeList = Arrays.asList(arr); // backed by the array, fixed size
		System.out.println("Arrays.asList(arr): " + fixedSizeList);
		try {
			fixedSizeList.add(5); // will throw -- fixed-size list doesn't support structural changes
		} catch (UnsupportedOperationException e) {
			System.out.println("Cannot add() to Arrays.asList() result -- fixed size! (as expected)");
		}

		List<Integer> mutableList = new ArrayList<>(Arrays.asList(arr)); // fully mutable copy
		mutableList.add(5);
		System.out.println("Mutable ArrayList after add: " + mutableList);

		// List -> Array
		Integer[] backToArray = mutableList.toArray(new Integer[0]);
		System.out.println("List back to array: " + Arrays.toString(backToArray));
	}

	// =====================================================================================
	// 48. java.util.Arrays UTILITY METHODS -- the toolbox you should know by heart
	// =====================================================================================
	private static void q48_ArraysUtilityMethods() {
		printHeader("48. Arrays UTILITY METHODS");

		int[] arr = { 5, 3, 8, 1, 9 };

		System.out.println("Original: " + Arrays.toString(arr));

		int[] sortedCopy = arr.clone();
		Arrays.sort(sortedCopy);
		System.out.println("Arrays.sort()        : " + Arrays.toString(sortedCopy));

		System.out.println("Arrays.binarySearch(sorted, 8) : index " + Arrays.binarySearch(sortedCopy, 8));

		int[] filled = new int[5];
		Arrays.fill(filled, 7);
		System.out.println("Arrays.fill(arr, 7)  : " + Arrays.toString(filled));

		int[] copy = Arrays.copyOf(arr, 3); // first 3 elements
		System.out.println("Arrays.copyOf(arr,3) : " + Arrays.toString(copy));

		int[] range = Arrays.copyOfRange(arr, 1, 4); // indices [1,4)
		System.out.println("Arrays.copyOfRange(1,4): " + Arrays.toString(range));

		System.out.println("Arrays.equals(a, a.clone()) : " + Arrays.equals(arr, arr.clone()));

		int[][] matrix = { { 1, 2 }, { 3, 4 } };
		System.out.println("Arrays.deepToString(2D) : " + Arrays.deepToString(matrix));

		List<Integer> asList = Arrays.stream(arr).boxed().toList(); // int[] -> List<Integer> via Stream
		System.out.println("Arrays.stream(arr).boxed().toList() : " + asList);

		System.out.println("Arrays.stream(arr).sum() : " + Arrays.stream(arr).sum());
		System.out.println("Arrays.stream(arr).max() : " + Arrays.stream(arr).max().getAsInt());
	}

	// =====================================================================================
	// 49. VARARGS -- variable-length arguments are secretly just an array
	// =====================================================================================
	// A method parameter written as "int... nums" is received inside the method as
	// a
	// regular "int[] nums". This is how methods like String.format(...) or
	// List.of(...)
	// accept any number of arguments.
	private static void q49_Varargs() {
		printHeader("49. VARARGS (VARIABLE-LENGTH ARGUMENTS)");

		System.out.println("sum() = " + sumOfNumbers());
		System.out.println("sum(1,2,3) = " + sumOfNumbers(1, 2, 3));
		System.out.println("sum(10,20,30,40) = " + sumOfNumbers(10, 20, 30, 40));

		int[] existingArray = { 5, 5, 5 };
		System.out.println("sum(existingArray) = " + sumOfNumbers(existingArray)); // arrays can be passed directly too
	}

	private static int sumOfNumbers(int... nums) { // "nums" is just an int[] inside the method
		int total = 0;
		for (int n : nums)
			total += n;
		return total;
	}

	// =====================================================================================
	// 50. 3D ARRAYS -- an "array of 2D arrays" (array of arrays of arrays)
	// =====================================================================================
	// Rarely asked in depth, but good to recognize: arr[i][j][k]. Common use:
	// representing
	// a stack of matrices, or a 3D grid (e.g. x,y,z coordinates in a
	// game/simulation).
	private static void q50_ThreeDArrays() {
		printHeader("50. 3D ARRAYS (ARRAY OF 2D ARRAYS)");

		int[][][] cube = new int[2][2][2]; // 2 layers, each a 2x2 matrix
		int value = 1;
		for (int layer = 0; layer < cube.length; layer++) {
			for (int row = 0; row < cube[layer].length; row++) {
				for (int col = 0; col < cube[layer][row].length; col++) {
					cube[layer][row][col] = value++;
				}
			}
		}
		System.out.println("3D array: " + Arrays.deepToString(cube));
		System.out.println("cube[1][0][1] = " + cube[1][0][1]);
	}

	// =====================================================================================
	// 51. ARRAY COVARIANCE & ArrayStoreException
	// =====================================================================================
	// Java arrays are COVARIANT: a String[] can be assigned to an Object[]
	// variable, because
	// String IS-A Object. This is convenient but UNSAFE -- the JVM has to check
	// every write
	// at RUNTIME, and throws ArrayStoreException if you try to store the wrong
	// type.
	// (Generics, by contrast, are invariant and catch this at COMPILE time
	// instead.)
	private static void q51_ArrayCovariance() {
		printHeader("51. ARRAY COVARIANCE & ArrayStoreException");

		String[] stringArr = { "a", "b", "c" };
		Object[] objectArr = stringArr; // legal -- String[] IS-A Object[] (covariance)
		System.out.println("Object[] reference pointing at a String[]: " + Arrays.toString(objectArr));

		try {
			objectArr[0] = 42; // compiles fine (Object[] accepts Integer)... but runtime check fails!
		} catch (ArrayStoreException e) {
			System.out.println("Caught ArrayStoreException: tried to store an Integer into a String[] "
					+ "(runtime type check failed, even though it compiled)");
		}
	}

	// =====================================================================================
	// 52. CLONING ARRAYS -- SHALLOW COPY vs DEEP COPY
	// =====================================================================================
	// arr.clone() (or Arrays.copyOf) makes a SHALLOW copy: for primitive arrays
	// this is a
	// true independent copy, but for OBJECT arrays, only the references are copied
	// -- both
	// arrays still point to the SAME underlying objects. Mutating a shared object
	// through
	// one array is visible through the other.
	private static void q52_CloneArrays() {
		printHeader("52. CLONING ARRAYS (SHALLOW vs DEEP COPY)");

		// Primitive array clone -- truly independent copy
		int[] originalInts = { 1, 2, 3 };
		int[] clonedInts = originalInts.clone();
		clonedInts[0] = 99;
		System.out.println("Original ints after cloning + mutating clone: " + Arrays.toString(originalInts));
		System.out.println(
				"Cloned ints: " + Arrays.toString(clonedInts) + " (independent -- primitives are copied by value)");

		// Object array clone -- SHALLOW copy, shares references
		int[][] originalMatrix = { { 1, 2 }, { 3, 4 } };
		int[][] shallowClone = originalMatrix.clone();
		shallowClone[0][0] = 999; // mutates the SHARED inner array!
		System.out.println("\nOriginal matrix after mutating shallow clone's inner array: "
				+ Arrays.deepToString(originalMatrix) + " <- changed too! (shared reference)");

		// Proper DEEP copy: clone each inner array individually
		int[][] deepClone = new int[originalMatrix.length][];
		for (int i = 0; i < originalMatrix.length; i++) {
			deepClone[i] = originalMatrix[i].clone(); // clone each row separately
		}
		deepClone[0][0] = -1;
		System.out.println("Original matrix after mutating DEEP clone: " + Arrays.deepToString(originalMatrix)
				+ " <- unchanged (truly independent)");
	}
}