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
 * =====================================================================================
 */
public class NumberArrayInterviewQuestions {

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
		System.out.println("After 1:  " + Arrays.toString(arr));
		int[] result = Arrays.copyOf(arr, uniqueIndex + 1);
		System.out.println("After 2:  " + Arrays.toString(result));
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
}