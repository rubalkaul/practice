package number;

import java.util.*;

/**
 * NumberLogicalInterviewQuestions
 *
 * Java interview programs based on numbers.
 * Each method is independent and can be tested from main().
 *
 * Covers:
 * 1. Even / Odd
 * 2. Positive / Negative / Zero
 * 3. Prime number
 * 4. Prime numbers in range
 * 5. Palindrome number
 * 6. Armstrong number
 * 7. Perfect number
 * 8. Strong number
 * 9. Neon number
 * 10. Automorphic number
 * 11. Harshad / Niven number
 * 12. Spy number
 * 13. Happy number
 * 14. Fibonacci series
 * 15. Factorial
 * 16. Reverse number
 * 17. Sum of digits
 * 18. Product of digits
 * 19. Count digits
 * 20. Largest digit
 * 21. Smallest digit
 * 22. Sum of first N natural numbers
 * 23. GCD / HCF
 * 24. LCM
 * 25. Swap two numbers
 * 26. Decimal to binary
 * 27. Binary to decimal
 * 28. Power without Math.pow()
 * 29. Number of trailing zeros in factorial
 * 30. Second largest digit
 * 31. Remove duplicate digits
 * 32. Frequency of digits
 * 33. Decimal palindrome
 * 34. Check two numbers are coprime
 * 35. Missing number from 1..N
 * 36. Two numbers with given sum
 * 37. Find duplicate number
 * 38. Reverse without converting to String
 * 39. Check number is a power of 2
 * 40. Find all factors
 */
public class NumberLogicalInterviewQuestions {

    public static void main(String[] args) {

        System.out.println("===== NUMBER LOGICAL INTERVIEW QUESTIONS =====");

        // 1. Even / Odd
        System.out.println("1. 10 is even: " + isEven(10));

        // 2. Positive / Negative / Zero
        System.out.println("2. Sign of -15: " + checkSign(-15));

        // 3. Prime
        System.out.println("3. 29 is prime: " + isPrime(29));

        // 4. Prime numbers in range
        System.out.print("4. Primes 1-50: ");
        printPrimes(1, 50);

        // 5. Palindrome
        System.out.println("5. 121 is palindrome: " + isPalindrome(121));

        // 6. Armstrong
        System.out.println("6. 153 is Armstrong: " + isArmstrong(153));

        // 7. Perfect
        System.out.println("7. 28 is perfect: " + isPerfect(28));

        // 8. Strong
        System.out.println("8. 145 is strong: " + isStrong(145));

        // 9. Neon
        System.out.println("9. 9 is neon: " + isNeon(9));

        // 10. Automorphic
        System.out.println("10. 25 is automorphic: " + isAutomorphic(25));

        // 11. Harshad
        System.out.println("11. 18 is Harshad: " + isHarshad(18));

        // 12. Spy
        System.out.println("12. 1124 is spy: " + isSpy(1124));

        // 13. Happy
        System.out.println("13. 19 is happy: " + isHappy(19));

        // 14. Fibonacci
        System.out.print("14. Fibonacci 10 terms: ");
        fibonacci(10);

        // 15. Factorial
        System.out.println("15. Factorial of 5: " + factorial(5));

        // 16. Reverse
        System.out.println("16. Reverse of 12345: " + reverseNumber(12345));

        // 17. Sum of digits
        System.out.println("17. Sum of digits of 12345: " + sumOfDigits(12345));

        // 18. Product of digits
        System.out.println("18. Product of digits of 1234: " + productOfDigits(1234));

        // 19. Count digits
        System.out.println("19. Number of digits in 123456: " + countDigits(123456));

        // 20. Largest digit
        System.out.println("20. Largest digit in 58329: " + largestDigit(58329));

        // 21. Smallest digit
        System.out.println("21. Smallest digit in 58329: " + smallestDigit(58329));

        // 22. Sum of first N natural numbers
        System.out.println("22. Sum 1..10: " + sumNaturalNumbers(10));

        // 23. GCD / HCF
        System.out.println("23. GCD of 48 and 18: " + gcd(48, 18));

        // 24. LCM
        System.out.println("24. LCM of 12 and 18: " + lcm(12, 18));

        // 25. Swap
        int[] swapped = swap(10, 20);
        System.out.println("25. Swap 10,20: " + swapped[0] + ", " + swapped[1]);

        // 26. Decimal to binary
        System.out.println("26. 10 in binary: " + decimalToBinary(10));

        // 27. Binary to decimal
        System.out.println("27. 1010 in decimal: " + binaryToDecimal("1010"));

        // 28. Power
        System.out.println("28. 2^10: " + power(2, 10));

        // 29. Trailing zeros
        System.out.println("29. Trailing zeros in 100!: " + trailingZerosInFactorial(100));

        // 30. Second largest digit
        System.out.println("30. Second largest digit in 58329: " + secondLargestDigit(58329));

        // 31. Remove duplicate digits
        System.out.println("31. Remove duplicates from 1122334455: "
                + removeDuplicateDigits(1122334455));

        // 32. Frequency of digits
        System.out.println("32. Digit frequency of 1122334455:");
        printDigitFrequency(1122334455);

        // 33. Decimal palindrome
        System.out.println("33. 12321 is palindrome: " + isPalindrome(12321));

        // 34. Coprime
        System.out.println("34. 8 and 15 are coprime: " + areCoprime(8, 15));

        // 35. Missing number
        int[] numbers = {1, 2, 3, 5, 6};
        System.out.println("35. Missing number: " + findMissingNumber(numbers, 6));

        // 36. Pair with given sum
        int[] arr = {2, 7, 11, 15};
        System.out.println("36. Pair with sum 9: " + Arrays.toString(findPairWithSum(arr, 9)));

        // 37. Duplicate number
        int[] duplicateArray = {1, 3, 4, 2, 2};
        System.out.println("37. Duplicate number: " + findDuplicate(duplicateArray));

        // 38. Reverse without String
        System.out.println("38. Reverse 9876 without String: " + reverseNumber(9876));

        // 39. Power of 2
        System.out.println("39. 64 is power of 2: " + isPowerOfTwo(64));

        // 40. Factors
        System.out.print("40. Factors of 36: ");
        printFactors(36);
    }

    // 1. Even / Odd
    static boolean isEven(int n) {
        return n % 2 == 0;
    }

    // 2. Positive / Negative / Zero
    static String checkSign(int n) {
        if (n > 0) return "Positive";
        if (n < 0) return "Negative";
        return "Zero";
    }

    // 3. Prime number
    static boolean isPrime(int n) {
        if (n < 2) return false;

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    // 4. Prime numbers in range
    static void printPrimes(int start, int end) {
        for (int i = start; i <= end; i++) {
            if (isPrime(i)) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    // 5. Palindrome number
    static boolean isPalindrome(int n) {
        if (n < 0) return false;
        return n == reverseNumber(n);
    }

    // 6. Armstrong number
    static boolean isArmstrong(int n) {
        if (n < 0) return false;

        int digits = countDigits(n);
        int original = n;
        long sum = 0;

        while (n > 0) {
            int digit = n % 10;
            sum += power(digit, digits);
            n /= 10;
        }

        return sum == original;
    }

    // 7. Perfect number
    static boolean isPerfect(int n) {
        if (n <= 1) return false;

        int sum = 1;

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                sum += i;

                if (i != n / i) {
                    sum += n / i;
                }
            }
        }

        return sum == n;
    }

    // 8. Strong number
    static boolean isStrong(int n) {
        if (n < 0) return false;

        int original = n;
        int sum = 0;

        do {
            int digit = n % 10;
            sum += factorial(digit);
            n /= 10;
        } while (n > 0);

        return sum == original;
    }

    // 9. Neon number
    // Example: 9 -> 9^2 = 81 -> 8 + 1 = 9
    static boolean isNeon(int n) {
        if (n < 0) return false;
        return sumOfDigits((long) n * n) == n;
    }

    // 10. Automorphic number
    // Example: 25 -> 625 ends with 25
    static boolean isAutomorphic(int n) {
        if (n < 0) return false;

        long square = (long) n * n;
        int digits = countDigits(n);
        long divisor = power(10, digits);

        return square % divisor == n;
    }

    // 11. Harshad / Niven number
    static boolean isHarshad(int n) {
        if (n <= 0) return false;

        int sum = sumOfDigits(n);
        return n % sum == 0;
    }

    // 12. Spy number
    // Sum of digits == Product of digits
    static boolean isSpy(int n) {
        if (n < 0) return false;

        int sum = 0;
        int product = 1;

        do {
            int digit = n % 10;
            sum += digit;
            product *= digit;
            n /= 10;
        } while (n > 0);

        return sum == product;
    }

    // 13. Happy number
    static boolean isHappy(int n) {
        Set<Integer> seen = new HashSet<>();

        while (n != 1 && !seen.contains(n)) {
            seen.add(n);
            n = sumOfSquaresOfDigits(n);
        }

        return n == 1;
    }

    static int sumOfSquaresOfDigits(int n) {
        int sum = 0;

        while (n > 0) {
            int digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }

        return sum;
    }

    // 14. Fibonacci series
    static void fibonacci(int n) {
        long a = 0;
        long b = 1;

        for (int i = 0; i < n; i++) {
            System.out.print(a + " ");

            long next = a + b;
            a = b;
            b = next;
        }

        System.out.println();
    }

    // 15. Factorial
    static long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial is not defined for negative numbers");
        }

        long result = 1;

        for (int i = 2; i <= n; i++) {
            result *= i;
        }

        return result;
    }

    // 16. Reverse number
    static int reverseNumber(int n) {
        int sign = n < 0 ? -1 : 1;
        n = Math.abs(n);

        int reverse = 0;

        while (n > 0) {
            int digit = n % 10;
            reverse = reverse * 10 + digit;
            n /= 10;
        }

        return reverse * sign;
    }

    // 17. Sum of digits
    static int sumOfDigits(int n) {
        return sumOfDigits((long) n);
    }

    static int sumOfDigits(long n) {
        n = Math.abs(n);

        int sum = 0;

        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }

        return sum;
    }

    // 18. Product of digits
    static long productOfDigits(long n) {
        n = Math.abs(n);

        if (n == 0) return 0;

        long product = 1;

        while (n > 0) {
            product *= n % 10;
            n /= 10;
        }

        return product;
    }

    // 19. Count digits
    static int countDigits(long n) {
        n = Math.abs(n);

        if (n == 0) return 1;

        int count = 0;

        while (n > 0) {
            count++;
            n /= 10;
        }

        return count;
    }

    // 20. Largest digit
    static int largestDigit(long n) {
        n = Math.abs(n);

        if (n == 0) return 0;

        int max = 0;

        while (n > 0) {
            int digit = (int) (n % 10);
            max = Math.max(max, digit);
            n /= 10;
        }

        return max;
    }

    // 21. Smallest digit
    static int smallestDigit(long n) {
        n = Math.abs(n);

        if (n == 0) return 0;

        int min = 9;

        while (n > 0) {
            int digit = (int) (n % 10);
            min = Math.min(min, digit);
            n /= 10;
        }

        return min;
    }

    // 22. Sum of first N natural numbers
    static long sumNaturalNumbers(int n) {
        return (long) n * (n + 1) / 2;
    }

    // 23. GCD / HCF using Euclidean algorithm
    static int gcd(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);

        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }

        return a;
    }

    // 24. LCM
    static long lcm(int a, int b) {
        if (a == 0 || b == 0) return 0;
        return Math.abs((long) a / gcd(a, b) * b);
    }

    // 25. Swap two numbers
    static int[] swap(int a, int b) {
        int temp = a;
        a = b;
        b = temp;

        return new int[]{a, b};
    }

    // 26. Decimal to binary without Integer.toBinaryString()
    static String decimalToBinary(int n) {
        if (n == 0) return "0";

        boolean negative = n < 0;
        n = Math.abs(n);

        StringBuilder binary = new StringBuilder();

        while (n > 0) {
            binary.append(n % 2);
            n /= 2;
        }

        binary.reverse();

        return negative ? "-" + binary : binary.toString();
    }

    // 27. Binary to decimal
    static int binaryToDecimal(String binary) {
        int result = 0;

        for (char c : binary.toCharArray()) {
            if (c != '0' && c != '1') {
                throw new IllegalArgumentException("Invalid binary number");
            }

            result = result * 2 + (c - '0');
        }

        return result;
    }

    // 28. Power without Math.pow()
    static long power(long base, int exponent) {
        if (exponent < 0) {
            throw new IllegalArgumentException("Negative exponent not supported");
        }

        long result = 1;

        while (exponent > 0) {
            if ((exponent & 1) == 1) {
                result *= base;
            }

            base *= base;
            exponent /= 2;
        }

        return result;
    }

    // 29. Number of trailing zeros in n!
    static long trailingZerosInFactorial(int n) {
        long count = 0;

        while (n > 0) {
            n /= 5;
            count += n;
        }

        return count;
    }

    // 30. Second largest digit
    static int secondLargestDigit(long n) {
        n = Math.abs(n);

        int largest = -1;
        int secondLargest = -1;

        do {
            int digit = (int) (n % 10);

            if (digit > largest) {
                secondLargest = largest;
                largest = digit;
            } else if (digit > secondLargest && digit != largest) {
                secondLargest = digit;
            }

            n /= 10;
        } while (n > 0);

        return secondLargest;
    }

    // 31. Remove duplicate digits while preserving first occurrence
    static String removeDuplicateDigits(long n) {
        String value = String.valueOf(Math.abs(n));
        boolean[] seen = new boolean[10];
        StringBuilder result = new StringBuilder();

        for (char c : value.toCharArray()) {
            int digit = c - '0';

            if (!seen[digit]) {
                seen[digit] = true;
                result.append(c);
            }
        }

        return result.toString();
    }

    // 32. Frequency of digits
    static void printDigitFrequency(long n) {
        int[] frequency = new int[10];
        n = Math.abs(n);

        if (n == 0) {
            frequency[0] = 1;
        }

        while (n > 0) {
            frequency[(int) (n % 10)]++;
            n /= 10;
        }

        for (int i = 0; i <= 9; i++) {
            if (frequency[i] > 0) {
                System.out.println("    " + i + " -> " + frequency[i]);
            }
        }
    }

    // 34. Coprime numbers
    static boolean areCoprime(int a, int b) {
        return gcd(a, b) == 1;
    }

    // 35. Missing number from 1..N
    static int findMissingNumber(int[] arr, int n) {
        long expected = (long) n * (n + 1) / 2;
        long actual = 0;

        for (int value : arr) {
            actual += value;
        }

        return (int) (expected - actual);
    }

    // 36. Find two numbers whose sum equals target
    static int[] findPairWithSum(int[] arr, int target) {
        Set<Integer> seen = new HashSet<>();

        for (int value : arr) {
            int required = target - value;

            if (seen.contains(required)) {
                return new int[]{required, value};
            }

            seen.add(value);
        }

        return new int[0];
    }

    // 37. Find duplicate number using Floyd's cycle detection
    static int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[0];

        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        slow = nums[0];

        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }

    // 39. Power of two
    static boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    // 40. Factors
    static void printFactors(int n) {
        if (n <= 0) {
            System.out.println("Factors are defined here for positive numbers only.");
            return;
        }

        List<Integer> factors = new ArrayList<>();

        for (int i = 1; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                factors.add(i);

                if (i != n / i) {
                    factors.add(n / i);
                }
            }
        }

        Collections.sort(factors);
        System.out.println(factors);
    }
}