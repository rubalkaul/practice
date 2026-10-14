package pattern;

/**
 * PatternInterviewQuestions
 *
 * Common Java pattern programs for coding/interview practice. Every pattern is
 * implemented in a separate method.
 */
public class PatternInterviewQuestions {

	public static void main(String[] args) {

		int n = 5;

		System.out.println("===== PATTERN INTERVIEW QUESTIONS =====");

		System.out.println("\n1. Solid Square");
		solidSquare(n);

		System.out.println("\n2. Rectangle");
		rectangle(4, 7);

		System.out.println("\n3. Right Triangle");
		rightTriangle(n);

		System.out.println("\n4. Inverted Right Triangle");
		invertedRightTriangle(n);

		System.out.println("\n5. Pyramid");
		pyramid(n);

		System.out.println("\n6. Inverted Pyramid");
		invertedPyramid(n);

		System.out.println("\n7. Diamond");
		diamond(n);

		System.out.println("\n8. Hollow Square");
		hollowSquare(n);

		System.out.println("\n9. Hollow Right Triangle");
		hollowRightTriangle(n);

		System.out.println("\n10. Number Triangle");
		numberTriangle(n);

		System.out.println("\n11. Inverted Number Triangle");
		invertedNumberTriangle(n);

		System.out.println("\n12. Floyd's Triangle");
		floydTriangle(n);

		System.out.println("\n13. Pascal's Triangle");
		pascalTriangle(n);

		System.out.println("\n14. 0-1 Triangle");
		zeroOneTriangle(n);

		System.out.println("\n15. Character Triangle");
		characterTriangle(n);

		System.out.println("\n16. Alphabet Pyramid");
		alphabetPyramid(n);

		System.out.println("\n17. Number Pyramid");
		numberPyramid(n);

		System.out.println("\n18. Hollow Pyramid");
		hollowPyramid(n);

		System.out.println("\n19. Butterfly Pattern");
		butterfly(n);

		System.out.println("\n20. X Pattern");
		xPattern(n);

		System.out.println("\n21. Plus Pattern");
		plusPattern(n);

		System.out.println("\n22. Hourglass Pattern");
		hourglass(n);

		System.out.println("\n23. Rhombus Pattern");
		rhombus(n);

		System.out.println("\n24. Hollow Rhombus");
		hollowRhombus(n);

		System.out.println("\n25. Increasing-Decreasing Number Pattern");
		increasingDecreasingNumberPattern(n);
	}

	// 1. Solid Square
	//
	// *****
	// *****
	// *****
	// *****
	// *****
	static void solidSquare(int n) {
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				System.out.print("* ");
			}
			System.out.println();
		}
	}

	// 2. Rectangle
	//
	// *******
	// *******
	// *******
	// *******
	static void rectangle(int rows, int columns) {
		for (int i = 1; i <= rows; i++) {
			for (int j = 1; j <= columns; j++) {
				System.out.print("* ");
			}
			System.out.println();
		}
	}

	// 3. Right Triangle
	//
	// *
	// * *
	// * * *
	// * * * *
	// * * * * *
	static void rightTriangle(int n) {
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= i; j++) {
				System.out.print("* ");
			}
			System.out.println();
		}
	}

	// 4. Inverted Right Triangle
	//
	// * * * * *
	// * * * *
	// * * *
	// * *
	// *
	static void invertedRightTriangle(int n) {
		for (int i = n; i >= 1; i--) {
			for (int j = 1; j <= i; j++) {
				System.out.print("* ");
			}
			System.out.println();
		}
	}

	// 5. Pyramid
	//
	// *
	// * * *
	// * * * * *
	// * * * * * * *
	// * * * * * * * * *
	static void pyramid(int n) {
		for (int i = 1; i <= n; i++) {

			for (int space = 1; space <= n - i; space++) {
				System.out.print("  ");
			}

			for (int j = 1; j <= 2 * i - 1; j++) {
				System.out.print("* ");
			}

			System.out.println();
		}
	}

	// 6. Inverted Pyramid
	static void invertedPyramid(int n) {
		for (int i = n; i >= 1; i--) {

			for (int space = 1; space <= n - i; space++) {
				System.out.print("  ");
			}

			for (int j = 1; j <= 2 * i - 1; j++) {
				System.out.print("* ");
			}

			System.out.println();
		}
	}

	// 7. Diamond
	static void diamond(int n) {

		for (int i = 1; i <= n; i++) {

			for (int space = 1; space <= n - i; space++) {
				System.out.print("  ");
			}

			for (int j = 1; j <= 2 * i - 1; j++) {
				System.out.print("* ");
			}

			System.out.println();
		}

		for (int i = n - 1; i >= 1; i--) {

			for (int space = 1; space <= n - i; space++) {
				System.out.print("  ");
			}

			for (int j = 1; j <= 2 * i - 1; j++) {
				System.out.print("* ");
			}

			System.out.println();
		}
	}

	// 8. Hollow Square
	//
	// * * * * *
	// * *
	// * *
	// * *
	// * * * * *
	static void hollowSquare(int n) {
		for (int i = 1; i <= n; i++) {

			for (int j = 1; j <= n; j++) {

				if (i == 1 || i == n || j == 1 || j == n) {
					System.out.print("* ");
				} else {
					System.out.print("  ");
				}
			}

			System.out.println();
		}
	}

	// 9. Hollow Right Triangle
	static void hollowRightTriangle(int n) {

		for (int i = 1; i <= n; i++) {

			for (int j = 1; j <= i; j++) {

				if (j == 1 || j == i || i == n) {
					System.out.print("* ");
				} else {
					System.out.print("  ");
				}
			}

			System.out.println();
		}
	}

	// 10. Number Triangle
	//
	// 1
	// 1 2
	// 1 2 3
	// 1 2 3 4
	// 1 2 3 4 5
	static void numberTriangle(int n) {

		for (int i = 1; i <= n; i++) {

			for (int j = 1; j <= i; j++) {
				System.out.print(j + " ");
			}

			System.out.println();
		}
	}

	// 11. Inverted Number Triangle
	static void invertedNumberTriangle(int n) {

		for (int i = n; i >= 1; i--) {

			for (int j = 1; j <= i; j++) {
				System.out.print(j + " ");
			}

			System.out.println();
		}
	}

	// 12. Floyd's Triangle
	//
	// 1
	// 2 3
	// 4 5 6
	// 7 8 9 10
	static void floydTriangle(int n) {

		int number = 1;

		for (int i = 1; i <= n; i++) {

			for (int j = 1; j <= i; j++) {
				System.out.print(number++ + " ");
			}

			System.out.println();
		}
	}

	// 13. Pascal's Triangle
	//
	// 1
	// 1 1
	// 1 2 1
	// 1 3 3 1
	// 1 4 6 4 1
	static void pascalTriangle(int n) {

		for (int i = 0; i < n; i++) {

			for (int space = 0; space < n - i; space++) {
				System.out.print(" ");
			}

			long value = 1;

			for (int j = 0; j <= i; j++) {
				System.out.print(value + " ");
				value = value * (i - j) / (j + 1);
			}

			System.out.println();
		}
	}

	// 14. 0-1 Triangle
	//
	// 1
	// 0 1
	// 1 0 1
	// 0 1 0 1
	static void zeroOneTriangle(int n) {

		for (int i = 1; i <= n; i++) {

			for (int j = 1; j <= i; j++) {

				if ((i + j) % 2 == 0) {
					System.out.print("1 ");
				} else {
					System.out.print("0 ");
				}
			}

			System.out.println();
		}
	}

	// 15. Character Triangle
	//
	// A
	// A B
	// A B C
	// A B C D
	static void characterTriangle(int n) {

		for (int i = 1; i <= n; i++) {

			for (int j = 1; j <= i; j++) {
				System.out.print((char) ('A' + j - 1) + " ");
			}

			System.out.println();
		}
	}

	// 16. Alphabet Pyramid
	//
	// A
	// ABA
	// ABCBA
	// ABCDCBA
	static void alphabetPyramid(int n) {

		for (int i = 1; i <= n; i++) {

			for (int space = 1; space <= n - i; space++) {
				System.out.print(" ");
			}

			for (int j = 1; j <= i; j++) {
				System.out.print((char) ('A' + j - 1));
			}

			for (int j = i - 1; j >= 1; j--) {
				System.out.print((char) ('A' + j - 1));
			}

			System.out.println();
		}
	}

	// 17. Number Pyramid
	//
	// 1
	// 121
	// 12321
	// 1234321
	static void numberPyramid(int n) {

		for (int i = 1; i <= n; i++) {

			for (int space = 1; space <= n - i; space++) {
				System.out.print(" ");
			}

			for (int j = 1; j <= i; j++) {
				System.out.print(j);
			}

			for (int j = i - 1; j >= 1; j--) {
				System.out.print(j);
			}

			System.out.println();
		}
	}

	// 18. Hollow Pyramid
	//
	// *
	// * *
	// * *
	// * *
	// *********
	static void hollowPyramid(int n) {

		for (int i = 1; i <= n; i++) {

			for (int space = 1; space <= n - i; space++) {
				System.out.print(" ");
			}

			for (int j = 1; j <= 2 * i - 1; j++) {

				if (i == n || j == 1 || j == 2 * i - 1) {
					System.out.print("*");
				} else {
					System.out.print(" ");
				}
			}

			System.out.println();
		}
	}

	// 19. Butterfly
	//
	// * *
	// ** **
	// *** ***
	// **** ****
	// **********
	// **** ****
	// *** ***
	// ** **
	// * *
	static void butterfly(int n) {

		for (int i = 1; i <= n; i++) {

			for (int j = 1; j <= i; j++) {
				System.out.print("*");
			}

			for (int space = 1; space <= 2 * (n - i); space++) {
				System.out.print(" ");
			}

			for (int j = 1; j <= i; j++) {
				System.out.print("*");
			}

			System.out.println();
		}

		for (int i = n; i >= 1; i--) {

			for (int j = 1; j <= i; j++) {
				System.out.print("*");
			}

			for (int space = 1; space <= 2 * (n - i); space++) {
				System.out.print(" ");
			}

			for (int j = 1; j <= i; j++) {
				System.out.print("*");
			}

			System.out.println();
		}
	}

	// 20. X Pattern
	//
	// * *
	// * *
	// *
	// * *
	// * *
	static void xPattern(int n) {

		for (int i = 1; i <= n; i++) {

			for (int j = 1; j <= n; j++) {

				if (j == i || j == n - i + 1) {
					System.out.print("*");
				} else {
					System.out.print(" ");
				}
			}

			System.out.println();
		}
	}

	// 21. Plus Pattern
	//
	// *
	// *
	// *****
	// *
	// *
	static void plusPattern(int n) {

		int middle = n / 2;

		for (int i = 0; i < n; i++) {

			for (int j = 0; j < n; j++) {

				if (i == middle || j == middle) {
					System.out.print("*");
				} else {
					System.out.print(" ");
				}
			}

			System.out.println();
		}
	}

	// 22. Hourglass
	//
	// *****
	// ***
	// *
	// ***
	// *****
	static void hourglass(int n) {

		for (int i = n; i >= 1; i--) {

			for (int space = 0; space < n - i; space++) {
				System.out.print(" ");
			}

			for (int j = 0; j < 2 * i - 1; j++) {
				System.out.print("*");
			}

			System.out.println();
		}

		for (int i = 2; i <= n; i++) {

			for (int space = 0; space < n - i; space++) {
				System.out.print(" ");
			}

			for (int j = 0; j < 2 * i - 1; j++) {
				System.out.print("*");
			}

			System.out.println();
		}
	}

	// 23. Rhombus
	//
	// *****
	// *****
	// *****
	// *****
	// *****
	static void rhombus(int n) {

		for (int i = 1; i <= n; i++) {

			for (int space = 1; space <= n - i; space++) {
				System.out.print(" ");
			}

			for (int j = 1; j <= n; j++) {
				System.out.print("*");
			}

			System.out.println();
		}
	}

	// 24. Hollow Rhombus
	static void hollowRhombus(int n) {

		for (int i = 1; i <= n; i++) {

			for (int space = 1; space <= n - i; space++) {
				System.out.print(" ");
			}

			for (int j = 1; j <= n; j++) {

				if (i == 1 || i == n || j == 1 || j == n) {
					System.out.print("*");
				} else {
					System.out.print(" ");
				}
			}

			System.out.println();
		}
	}

	// 25. Increasing-Decreasing Number Pattern
	//
	// 1
	// 12
	// 123
	// 1234
	// 12345
	// 1234
	// 123
	// 12
	// 1
	static void increasingDecreasingNumberPattern(int n) {

		for (int i = 1; i <= n; i++) {

			for (int j = 1; j <= i; j++) {
				System.out.print(j);
			}

			System.out.println();
		}

		for (int i = n - 1; i >= 1; i--) {

			for (int j = 1; j <= i; j++) {
				System.out.print(j);
			}

			System.out.println();
		}
	}
}
