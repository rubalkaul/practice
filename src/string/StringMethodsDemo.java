package string;

import java.util.*;
import java.util.stream.*;

public class StringMethodsDemo {

	public static void main(String[] args) {

		demoCreatingStrings();
		demoLengthAndEmptyChecks();
		demoCharacterAccess();

		// String traversal methods
		demoForLoopCharAt();
		demoToCharArrayForEach();
		demoWhileLoopCharAt();
		demoDoWhileLoop();
		demoCharsStream();
		demoCharsStreamMapToObj();
		demoCodePoints();
		demoCodePointsUnicode();
		demoForwardTraversalWithIndex();
		demoReverseForLoop();
		demoReverseWhileLoop();

		demoIndexOfAndSearching();
		demoSubstring();
		demoCaseConversion();
		demoTrimAndStrip();
		demoEqualityAndComparison();
		demoConcatenation();
		demoReplace();
		demoSplit();
		demoJoin();
		demoContainsStartsEndsWith();
		demoMatchesRegex();
		demoFormat();
		demoConversionMethods();
		demoCharsAndCodePoints();
		demoRepeatAndBlank();
		demoInternAndPool();
		demoHashCode();
		demoImmutabilityDemo();
		demoStringBuilderVsString();
		demoCommonPitfalls();
	}

	// =================================================================
	// 1. Creating strings
	// =================================================================
	private static void demoCreatingStrings() {

		System.out.println("\n===== Creating strings =====");

		String literal = "hello";
		String viaNew = new String("hello");

		char[] chars = { 'j', 'a', 'v', 'a' };

		String fromChars = new String(chars);
		String fromCharsRange = new String(chars, 1, 2);

		byte[] bytes = { 72, 105 };

		String fromBytes = new String(bytes);

		System.out.println("literal: " + literal);
		System.out.println("new String(\"hello\"): " + viaNew);
		System.out.println("literal == viaNew: " + (literal == viaNew));
		System.out.println("new String(char[]): " + fromChars);
		System.out.println("new String(char[], offset, len): " + fromCharsRange);
		System.out.println("new String(byte[]): " + fromBytes);
	}

	// =================================================================
	// 2. length() / isEmpty() / isBlank()
	// =================================================================
	private static void demoLengthAndEmptyChecks() {

		System.out.println("\n===== length() / isEmpty() / isBlank() =====");

		String s = "hello world";

		System.out.println("length(): " + s.length());
		System.out.println("\"\".isEmpty(): " + "".isEmpty());
		System.out.println("\"   \".isEmpty(): " + "   ".isEmpty());
		System.out.println("\"   \".isBlank(): " + "   ".isBlank());
	}

	// =================================================================
	// 3. Character access
	// =================================================================
	private static void demoCharacterAccess() {

		System.out.println("\n===== Character access =====");

		String s = "hello";

		System.out.println("charAt(1): " + s.charAt(1));
		System.out.println("codePointAt(0): " + s.codePointAt(0));
		System.out.println("toCharArray(): " + Arrays.toString(s.toCharArray()));

		try {
			s.charAt(10);
		} catch (StringIndexOutOfBoundsException e) {
			System.out.println("charAt(10) threw: " + e.getClass().getSimpleName());
		}
	}

	// =================================================================
	// 4. Traversal using for loop + charAt()
	// =================================================================
	private static void demoForLoopCharAt() {

		System.out.println("\n===== Traversal: for + charAt() =====");

		String s = "hello";

		for (int i = 0; i < s.length(); i++) {
			System.out.println(s.charAt(i));
		}
	}

	// =================================================================
	// 5. Traversal using toCharArray() + enhanced for
	// =================================================================
	private static void demoToCharArrayForEach() {

		System.out.println("\n===== Traversal: toCharArray() + for-each =====");

		String s = "hello";

		for (char ch : s.toCharArray()) {
			System.out.println(ch);
		}
	}

	// =================================================================
	// 6. Traversal using while + charAt()
	// =================================================================
	private static void demoWhileLoopCharAt() {

		System.out.println("\n===== Traversal: while + charAt() =====");

		String s = "hello";

		int i = 0;

		while (i < s.length()) {

			System.out.println(s.charAt(i));

			i++;
		}
	}

	// =================================================================
	// 7. Traversal using do-while
	// =================================================================
	private static void demoDoWhileLoop() {

		System.out.println("\n===== Traversal: do-while =====");

		String s = "hello";

		if (!s.isEmpty()) {

			int i = 0;

			do {

				System.out.println(s.charAt(i));

				i++;

			} while (i < s.length());
		}
	}

	// =================================================================
	// 8. Traversal using chars()
	// =================================================================
	private static void demoCharsStream() {

		System.out.println("\n===== Traversal: chars() =====");

		String s = "hello";

		s.chars().forEach(ch -> {

			System.out.println((char) ch);

		});
	}

	// =================================================================
	// 9. Traversal using chars() + mapToObj()
	// =================================================================
	private static void demoCharsStreamMapToObj() {

		System.out.println("\n===== Traversal: chars() + mapToObj() =====");

		String s = "hello";

		s.chars().mapToObj(ch -> (char) ch).forEach(System.out::println);
	}

	// =================================================================
	// 10. Traversal using codePoints()
	// =================================================================
	private static void demoCodePoints() {

		System.out.println("\n===== Traversal: codePoints() =====");

		String s = "hello";

		s.codePoints().forEach(codePoint -> {

			System.out.println(codePoint);

		});
	}

	// =================================================================
	// 11. Unicode-aware traversal using codePoints()
	// =================================================================
	private static void demoCodePointsUnicode() {

		System.out.println("\n===== Unicode traversal: codePoints() =====");

		String s = "A😊B";

		s.codePoints().forEach(codePoint -> {

			System.out.println("Code point: " + codePoint + " -> " + new String(Character.toChars(codePoint)));

		});
	}

	// =================================================================
	// 12. Forward traversal with index + character
	// =================================================================
	private static void demoForwardTraversalWithIndex() {

		System.out.println("\n===== Forward traversal with index =====");

		String s = "hello";

		for (int i = 0; i < s.length(); i++) {

			char ch = s.charAt(i);

			System.out.println("Index: " + i + ", Character: " + ch);
		}
	}

	// =================================================================
	// 13. Reverse traversal using for loop
	// =================================================================
	private static void demoReverseForLoop() {

		System.out.println("\n===== Reverse traversal: for loop =====");

		String s = "hello";

		for (int i = s.length() - 1; i >= 0; i--) {

			System.out.println(s.charAt(i));
		}
	}

	// =================================================================
	// 14. Reverse traversal using while loop
	// =================================================================
	private static void demoReverseWhileLoop() {

		System.out.println("\n===== Reverse traversal: while loop =====");

		String s = "hello";

		int i = s.length() - 1;

		while (i >= 0) {

			System.out.println(s.charAt(i));

			i--;
		}
	}

	// =================================================================
	// 15. Searching
	// =================================================================
	private static void demoIndexOfAndSearching() {

		System.out.println("\n===== indexOf / lastIndexOf =====");

		String s = "banana";

		System.out.println("s = \"" + s + "\"");

		System.out.println("indexOf('a'): " + s.indexOf('a'));

		System.out.println("indexOf('a', 2): " + s.indexOf('a', 2));

		System.out.println("lastIndexOf('a'): " + s.lastIndexOf('a'));

		System.out.println("indexOf(\"na\"): " + s.indexOf("na"));

		System.out.println("indexOf(\"z\"): " + s.indexOf("z"));
	}

	// =================================================================
	// 16. substring()
	// =================================================================
	private static void demoSubstring() {

		System.out.println("\n===== substring =====");

		String s = "hello world";

		System.out.println("substring(6): " + s.substring(6));

		System.out.println("substring(0, 5): " + s.substring(0, 5));

		try {

			s.substring(20);

		} catch (StringIndexOutOfBoundsException e) {

			System.out.println("substring(20) threw: " + e.getClass().getSimpleName());
		}
	}

	// =================================================================
	// 17. Case conversion
	// =================================================================
	private static void demoCaseConversion() {

		System.out.println("\n===== Case conversion =====");

		String s = "Hello World";

		System.out.println("toUpperCase(): " + s.toUpperCase());

		System.out.println("toLowerCase(): " + s.toLowerCase());
	}

	// =================================================================
	// 18. trim / strip
	// =================================================================
	private static void demoTrimAndStrip() {

		System.out.println("\n===== trim / strip =====");

		String s = "   padded string   ";

		System.out.println("trim(): [" + s.trim() + "]");
		System.out.println("strip(): [" + s.strip() + "]");
		System.out.println("stripLeading(): [" + s.stripLeading() + "]");
		System.out.println("stripTrailing(): [" + s.stripTrailing() + "]");
	}

	// =================================================================
	// 19. Equality and comparison
	// =================================================================
	private static void demoEqualityAndComparison() {

		System.out.println("\n===== equals / compareTo / == =====");

		String a = "apple";
		String b = "APPLE";
		String c = new String("apple");

		System.out.println("a.equals(\"apple\"): " + a.equals("apple"));

		System.out.println("a.equals(b): " + a.equals(b));

		System.out.println("a.equalsIgnoreCase(b): " + a.equalsIgnoreCase(b));

		System.out.println("a == c: " + (a == c));

		System.out.println("a.equals(c): " + a.equals(c));

		System.out.println("a.compareTo(\"banana\"): " + a.compareTo("banana"));

		System.out.println("a.compareToIgnoreCase(b): " + a.compareToIgnoreCase(b));
	}

	// =================================================================
	// 20. Concatenation
	// =================================================================
	private static void demoConcatenation() {

		System.out.println("\n===== Concatenation =====");

		String first = "Hello";
		String second = "World";

		System.out.println("concat(): " + first.concat(" ").concat(second));

		System.out.println("+ operator: " + first + " " + second);
	}

	// =================================================================
	// 21. replace / replaceAll / replaceFirst
	// =================================================================
	private static void demoReplace() {

		System.out.println("\n===== replace =====");

		String s = "cat bat hat";

		System.out.println("replace('a','o'): " + s.replace('a', 'o'));

		System.out.println("replace(\"at\",\"og\"): " + s.replace("at", "og"));

		System.out.println("replaceAll(\"[abc]\",\"*\"): " + s.replaceAll("[abc]", "*"));

		System.out.println("replaceFirst(\"at\",\"OG\"): " + s.replaceFirst("at", "OG"));
	}

	// =================================================================
	// 22. split()
	// =================================================================
	private static void demoSplit() {

		System.out.println("\n===== split =====");

		String csv = "a,b,,c,";

		System.out.println("split(\",\"): " + Arrays.toString(csv.split(",")));

		System.out.println("split(\",\", -1): " + Arrays.toString(csv.split(",", -1)));

		System.out.println("split(\",\", 2): " + Arrays.toString(csv.split(",", 2)));

		String sentence = "The quick   brown fox";

		System.out.println("split(\"\\\\s+\"): " + Arrays.toString(sentence.split("\\s+")));
	}

	// =================================================================
	// 23. String.join()
	// =================================================================
	private static void demoJoin() {

		System.out.println("\n===== String.join =====");

		System.out.println(String.join("-", "a", "b", "c"));

		List<String> list = List.of("x", "y", "z");

		System.out.println(String.join(", ", list));
	}

	// =================================================================
	// 24. contains / startsWith / endsWith
	// =================================================================
	private static void demoContainsStartsEndsWith() {

		System.out.println("\n===== contains / startsWith / endsWith =====");

		String s = "hello world";

		System.out.println("contains(\"lo wo\"): " + s.contains("lo wo"));

		System.out.println("startsWith(\"hello\"): " + s.startsWith("hello"));

		System.out.println("startsWith(\"world\", 6): " + s.startsWith("world", 6));

		System.out.println("endsWith(\"world\"): " + s.endsWith("world"));
	}

	// =================================================================
	// 25. matches()
	// =================================================================
	private static void demoMatchesRegex() {

		System.out.println("\n===== matches =====");

		System.out.println("12345: " + "12345".matches("\\d+"));

		System.out.println("12a45: " + "12a45".matches("\\d+"));

		System.out.println("user@mail.com: " + "user@mail.com".matches("^[\\w.]+@[\\w.]+\\.[a-z]{2,}$"));
	}

	// =================================================================
	// 26. String.format()
	// =================================================================
	private static void demoFormat() {

		System.out.println("\n===== String.format =====");

		String formatted = String.format("Name: %s, Age: %d, Score: %.2f", "Alice", 30, 92.456);

		System.out.println(formatted);

		String formatted2 = "Name: %s, Age: %d".formatted("Bob", 25);

		System.out.println(formatted2);
	}

	// =================================================================
	// 27. Conversion methods
	// =================================================================
	private static void demoConversionMethods() {

		System.out.println("\n===== valueOf / conversions =====");

		System.out.println(String.valueOf(123));

		System.out.println(String.valueOf(3.14));

		System.out.println(String.valueOf(true));

		System.out.println(String.valueOf(new char[] { 'h', 'i' }));

		System.out.println(Integer.parseInt("42"));

		System.out.println(Double.parseDouble("3.14"));
	}

	// =================================================================
	// 28. chars() / codePoints() examples
	// =================================================================
	private static void demoCharsAndCodePoints() {

		System.out.println("\n===== chars() / codePoints() =====");

		String s = "hello";

		List<Character> charList = s.chars().mapToObj(c -> (char) c).collect(Collectors.toList());

		System.out.println("chars() -> List<Character>: " + charList);

		long vowelCount = s.chars().filter(c -> "aeiou".indexOf(c) != -1).count();

		System.out.println("Vowel count: " + vowelCount);
	}

	// =================================================================
	// 29. repeat()
	// =================================================================
	private static void demoRepeatAndBlank() {

		System.out.println("\n===== repeat =====");

		System.out.println("\"ab\".repeat(3): " + "ab".repeat(3));

		System.out.println("\"-\".repeat(10): " + "-".repeat(10));
	}

	// =================================================================
	// 30. intern() and String Pool
	// =================================================================
	private static void demoInternAndPool() {

		System.out.println("\n===== intern() / String Pool =====");

		String heapString = new String("pooled");

		String internedVersion = heapString.intern();

		String literalVersion = "pooled";

		System.out.println("heapString == literalVersion: " + (heapString == literalVersion));

		System.out.println("internedVersion == literalVersion: " + (internedVersion == literalVersion));
	}

	// =================================================================
	// 31. hashCode()
	// =================================================================
	private static void demoHashCode() {

		System.out.println("\n===== hashCode =====");

		String a = "hello";
		String b = "hello";

		System.out.println("hello hashCode: " + a.hashCode());

		System.out.println("Equal strings same hashCode: " + (a.hashCode() == b.hashCode()));
	}

	// =================================================================
	// 32. Immutability
	// =================================================================
	private static void demoImmutabilityDemo() {

		System.out.println("\n===== Immutability =====");

		String original = "immutable";

		String upper = original.toUpperCase();

		System.out.println("original: " + original);

		System.out.println("new object: " + upper);

		System.out.println("original == upper: " + (original == upper));
	}

	// =================================================================
	// 33. String vs StringBuilder
	// =================================================================
	private static void demoStringBuilderVsString() {

		System.out.println("\n===== String vs StringBuilder =====");

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < 5; i++) {

			sb.append(i).append(",");
		}

		System.out.println("Built with StringBuilder: " + sb);

		System.out.println("reverse(): " + sb.reverse());

		System.out.println("length(): " + sb.length());
	}

	// =================================================================
	// 34. Common pitfalls
	// =================================================================
	private static void demoCommonPitfalls() {

		System.out.println("\n===== Common pitfalls =====");

		// Pitfall 1: == vs equals()
		String a = new String("test");
		String b = new String("test");

		System.out.println("a == b: " + (a == b));

		System.out.println("a.equals(b): " + a.equals(b));

		// Pitfall 2: NullPointerException
		String nullStr = null;

		try {

			nullStr.length();

		} catch (NullPointerException e) {

			System.out.println("Caught expected NPE");
		}

		// Pitfall 3: split() with dot
		String dotSeparated = "a.b.c";

		System.out.println("split(\".\"): " + Arrays.toString(dotSeparated.split(".")));

		System.out.println("split(\"\\\\.\"): " + Arrays.toString(dotSeparated.split("\\.")));

		// Pitfall 4: String concatenation in loop
		System.out.println("Use StringBuilder for repeated concatenation.");
	}
}