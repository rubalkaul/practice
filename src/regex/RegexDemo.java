package regex;

import java.util.*;
import java.util.regex.*;
import java.util.stream.*;

public class RegexDemo {

	public static void main(String[] args) {

		demoBasicMatching();
		demoPatternAndMatcherBasics();
		demoFindVsMatches();
		demoGroups();
		demoNamedGroups();
		demoQuantifiers();
		demoCharacterClasses();
		demoAnchors();
		demoAlternationAndGrouping();
		demoBackreferences();
		demoLookaheadAndLookbehind();
		demoFlags();
		demoReplaceWithRegex();
		demoSplitWithRegex();
		demoPatternSplitAndPredicate();
		demoCommonRealWorldPatterns();
		demoCommonPitfalls();
	}

	// =================================================================
	// 1. Basic matching — String.matches() (whole-string match)
	// =================================================================
	// matches() requires the ENTIRE string to satisfy the regex, not just
	// part of it. Equivalent to wrapping the pattern in ^...$.
	private static void demoBasicMatching() {
		System.out.println("\n===== Basic matching: String.matches() =====");

		System.out.println("\"12345\".matches(\"\\\\d+\"): " + "12345".matches("\\d+"));
		System.out.println("\"12a45\".matches(\"\\\\d+\"): " + "12a45".matches("\\d+"));
		System.out.println("\"hello\".matches(\"h.*o\"): " + "hello".matches("h.*o"));
		System.out.println("\"hello world\".matches(\"hello\") [partial != whole]: " + "hello world".matches("hello"));
	}

	// =================================================================
	// 2. Pattern + Matcher — the core regex API for anything beyond a
	// one-off check. Pattern.compile() parses the regex once; Matcher
	// applies it to specific input and tracks match state/position.
	// =================================================================
	private static void demoPatternAndMatcherBasics() {
		System.out.println("\n===== Pattern & Matcher basics =====");

		Pattern pattern = Pattern.compile("\\d+");
		Matcher matcher = pattern.matcher("Order 42 has 7 items");

		while (matcher.find()) { // find() scans for the NEXT match, advancing internal position
			System.out.println("Found \"" + matcher.group() + "\" at [" + matcher.start() + "," + matcher.end() + ")");
		}
	}

	// =================================================================
	// 3. find() vs matches() on a Matcher
	// =================================================================
	// matcher.matches() -> whole input must match (like String.matches)
	// matcher.find() -> looks for a match ANYWHERE, can be called repeatedly
	// matcher.lookingAt() -> match must start at the beginning, but need not
	// consume the whole string
	private static void demoFindVsMatches() {
		System.out.println("\n===== find() vs matches() vs lookingAt() =====");

		Pattern p = Pattern.compile("\\d+");

		Matcher m1 = p.matcher("42");
		System.out.println("matches() on \"42\": " + m1.matches());

		Matcher m2 = p.matcher("abc42");
		System.out.println("matches() on \"abc42\" [fails, not whole string]: " + m2.matches());
		System.out.println("find() on \"abc42\" [succeeds, matches part]: " + p.matcher("abc42").find());

		Matcher m3 = p.matcher("42abc");
		System.out.println("lookingAt() on \"42abc\" [starts with match]: " + m3.lookingAt());
	}

	// =================================================================
	// 4. Capturing groups — parentheses () capture sub-matches, group(0)
	// is the whole match, group(1), group(2)... are the parenthesized parts
	// =================================================================
	private static void demoGroups() {
		System.out.println("\n===== Capturing groups =====");

		Pattern p = Pattern.compile("(\\d{4})-(\\d{2})-(\\d{2})"); // date: yyyy-mm-dd
		Matcher m = p.matcher("Event date: 2026-08-20");

		if (m.find()) {
			System.out.println("Full match group(0): " + m.group(0));
			System.out.println("Year group(1): " + m.group(1));
			System.out.println("Month group(2): " + m.group(2));
			System.out.println("Day group(3): " + m.group(3));
			System.out.println("groupCount(): " + m.groupCount());
		}
	}

	// =================================================================
	// 5. Named groups — (?<name>...) lets you reference by name instead
	// of by position, which is more readable for patterns with many groups
	// =================================================================
	private static void demoNamedGroups() {
		System.out.println("\n===== Named groups =====");

		Pattern p = Pattern.compile("(?<year>\\d{4})-(?<month>\\d{2})-(?<day>\\d{2})");
		Matcher m = p.matcher("2026-08-20");

		if (m.matches()) {
			System.out.println("year: " + m.group("year"));
			System.out.println("month: " + m.group("month"));
			System.out.println("day: " + m.group("day"));
		}
	}

	// =================================================================
	// 6. Quantifiers — *, +, ?, {n}, {n,}, {n,m}, and greedy vs reluctant
	// =================================================================
	private static void demoQuantifiers() {
		System.out.println("\n===== Quantifiers =====");

		System.out.println("\"aaa\".matches(\"a*\") [0 or more]: " + "aaa".matches("a*"));
		System.out.println("\"\".matches(\"a+\") [1 or more, empty fails]: " + "".matches("a+"));
		System.out.println("\"color\".matches(\"colou?r\") [0 or 1]: " + "color".matches("colou?r"));
		System.out.println("\"colour\".matches(\"colou?r\"): " + "colour".matches("colou?r"));
		System.out.println("\"12345\".matches(\"\\\\d{3}\") [exactly 3]: " + "12345".matches("\\d{3}"));
		System.out.println("\"12345\".matches(\"\\\\d{3,}\") [3 or more]: " + "12345".matches("\\d{3,}"));
		System.out.println("\"12345\".matches(\"\\\\d{2,4}\") [between 2 and 4]: " + "12345".matches("\\d{2,4}"));

		// Greedy (default) takes as much as possible; reluctant (?) takes as little as
		// possible
		String html = "<b>bold</b> and <i>italic</i>";
		Matcher greedy = Pattern.compile("<.*>").matcher(html);
		Matcher reluctant = Pattern.compile("<.*?>").matcher(html);
		System.out.println("Greedy <.*> find(): " + (greedy.find() ? greedy.group() : "no match"));
		System.out.println("Reluctant <.*?> find(): " + (reluctant.find() ? reluctant.group() : "no match"));
	}

	// =================================================================
	// 7. Character classes — [...], predefined classes \d \w \s and negations
	// =================================================================
	private static void demoCharacterClasses() {
		System.out.println("\n===== Character classes =====");

		System.out.println("\"cat\".matches(\"[bc]at\"): " + "cat".matches("[bc]at"));
		System.out.println("\"hat\".matches(\"[bc]at\") [not in class]: " + "hat".matches("[bc]at"));
		System.out.println("\"5\".matches(\"[0-9]\") [range]: " + "5".matches("[0-9]"));
		System.out.println("\"x\".matches(\"[^0-9]\") [negated range]: " + "x".matches("[^0-9]"));

		System.out.println("\\d (digit): " + "7".matches("\\d"));
		System.out.println("\\D (non-digit): " + "a".matches("\\D"));
		System.out.println("\\w (word char): " + "_".matches("\\w"));
		System.out.println("\\W (non-word char): " + "!".matches("\\W"));
		System.out.println("\\s (whitespace): " + " ".matches("\\s"));
		System.out.println("\\S (non-whitespace): " + "x".matches("\\S"));
		System.out.println(". (any char except newline): " + "x".matches("."));
	}

	// =================================================================
	// 8. Anchors — ^ (start), $ (end), \b (word boundary), \B (non-boundary)
	// =================================================================
	private static void demoAnchors() {
		System.out.println("\n===== Anchors =====");

		System.out.println("\"hello world\".matches(\"^hello.*\"): " + "hello world".matches("^hello.*"));
		System.out.println("\"hello world\".matches(\".*world$\"): " + "hello world".matches(".*world$"));

		Pattern wordBoundary = Pattern.compile("\\bcat\\b");
		System.out.println("\\bcat\\b find() in \"cat category\" [only standalone 'cat']: "
				+ wordBoundary.matcher("cat category").find());
		System.out.println("Matches found: " + countMatches(wordBoundary, "the cat sat, category ignored"));
	}

	private static int countMatches(Pattern p, String input) {
		Matcher m = p.matcher(input);
		int count = 0;
		while (m.find())
			count++;
		return count;
	}

	// =================================================================
	// 9. Alternation | and grouping ()
	// =================================================================
	private static void demoAlternationAndGrouping() {
		System.out.println("\n===== Alternation and grouping =====");

		System.out.println("\"cat\".matches(\"cat|dog\"): " + "cat".matches("cat|dog"));
		System.out.println("\"dog\".matches(\"cat|dog\"): " + "dog".matches("cat|dog"));
		System.out.println("\"bird\".matches(\"cat|dog\"): " + "bird".matches("cat|dog"));

		// grouping controls precedence: (gray|grey) vs gray|grey applied to "s" suffix
		System.out.println("\"grays\".matches(\"(gray|grey)s\"): " + "grays".matches("(gray|grey)s"));
		System.out.println("\"greys\".matches(\"(gray|grey)s\"): " + "greys".matches("(gray|grey)s"));
	}

	// =================================================================
	// 10. Backreferences — \1, \2... refer back to an earlier captured group
	// =================================================================
	private static void demoBackreferences() {
		System.out.println("\n===== Backreferences =====");

		// find repeated words, e.g. "the the"
		Pattern p = Pattern.compile("\\b(\\w+)\\s+\\1\\b");
		Matcher m = p.matcher("this is is a test test sentence");
		while (m.find()) {
			System.out.println("Repeated word found: \"" + m.group(1) + "\"");
		}
	}

	// =================================================================
	// 11. Lookahead / lookbehind — assert context WITHOUT consuming it
	// =================================================================
	// (?=...) positive lookahead (?!...) negative lookahead
	// (?<=...) positive lookbehind (?<!...) negative lookbehind
	private static void demoLookaheadAndLookbehind() {
		System.out.println("\n===== Lookahead / lookbehind =====");

		// password must contain at least one digit (lookahead, doesn't consume)
		System.out.println(
				"\"abc123\".matches(\".*(?=.*\\\\d).*\") [contains a digit]: " + "abc123".matches(".*(?=.*\\d).*"));

		// match "foo" only when NOT followed by "bar"
		Pattern negLookahead = Pattern.compile("foo(?!bar)");
		System.out
				.println("\"foobar\" find(\"foo(?!bar)\") [should be false]: " + negLookahead.matcher("foobar").find());
		System.out
				.println("\"foobaz\" find(\"foo(?!bar)\") [should be true]: " + negLookahead.matcher("foobaz").find());

		// extract digits only when preceded by "$" (lookbehind, doesn't consume the $)
		Pattern lookbehind = Pattern.compile("(?<=\\$)\\d+");
		Matcher lm = lookbehind.matcher("Price: $100, Quantity: 5");
		if (lm.find()) {
			System.out.println("Amount after '$' via lookbehind: " + lm.group());
		}
	}

	// =================================================================
	// 12. Flags — CASE_INSENSITIVE, MULTILINE, DOTALL
	// =================================================================
	private static void demoFlags() {
		System.out.println("\n===== Flags =====");

		Pattern caseInsensitive = Pattern.compile("hello", Pattern.CASE_INSENSITIVE);
		System.out
				.println("CASE_INSENSITIVE \"HELLO\" matches \"hello\": " + caseInsensitive.matcher("HELLO").matches());

		// MULTILINE makes ^ and $ match at line boundaries, not just string boundaries
		String multiline = "line1\nline2\nline3";
		Pattern ml = Pattern.compile("^line\\d$", Pattern.MULTILINE);
		System.out.println("MULTILINE matches per line: " + countMatches(ml, multiline));

		// DOTALL makes . also match newline characters (normally it doesn't)
		Pattern dotAll = Pattern.compile("a.b", Pattern.DOTALL);
		System.out.println("DOTALL \"a\\nb\" matches \"a.b\": " + dotAll.matcher("a\nb").matches());
		System.out.println("Without DOTALL \"a\\nb\" matches \"a.b\": " + "a\nb".matches("a.b"));

		// combining flags with bitwise OR
		Pattern combined = Pattern.compile("^hello$", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
		System.out.println("Combined flags used to build pattern: " + combined.pattern());
	}

	// =================================================================
	// 13. Replacing with regex — replaceAll, replaceFirst, Matcher.replaceAll,
	// and group references in the replacement string ($1, $2...)
	// =================================================================
	private static void demoReplaceWithRegex() {
		System.out.println("\n===== Replacing with regex =====");

		String s = "cat bat hat";
		System.out.println("replaceAll(\"[bc]at\",\"*\"): " + s.replaceAll("[bc]at", "*"));
		System.out.println("replaceFirst(\"at\",\"OG\"): " + s.replaceFirst("at", "OG"));

		// referencing captured groups in the replacement: swap first/last name
		String name = "Doe, John";
		String swapped = name.replaceAll("(\\w+),\\s*(\\w+)", "$2 $1");
		System.out.println("Swap via group refs: " + swapped);

		// Matcher-based replacement (equivalent, useful when you already have a
		// Matcher)
		Matcher m = Pattern.compile("\\d+").matcher("I have 3 cats and 2 dogs");
		String masked = m.replaceAll("#");
		System.out.println("Matcher.replaceAll (mask numbers): " + masked);
	}

	// =================================================================
	// 14. Splitting with regex — String.split()
	// =================================================================
	private static void demoSplitWithRegex() {
		System.out.println("\n===== Splitting with regex =====");

		String sentence = "The  quick,brown ; fox";
		String[] parts = sentence.split("[\\s,;]+"); // one or more of: whitespace, comma, semicolon
		System.out.println("split on mixed delimiters: " + Arrays.toString(parts));

		String csv = "a,b,,c,";
		System.out.println("split(\",\") drops trailing empties: " + Arrays.toString(csv.split(",")));
		System.out.println("split(\",\", -1) keeps them: " + Arrays.toString(csv.split(",", -1)));
	}

	// =================================================================
	// 15. Pattern.split() and Pattern.asPredicate() — reusing a compiled
	// Pattern is more efficient than calling String.split()/matches()
	// repeatedly, since those recompile the regex every call
	// =================================================================
	private static void demoPatternSplitAndPredicate() {
		System.out.println("\n===== Pattern.split() / asPredicate() =====");

		Pattern commaPattern = Pattern.compile(",\\s*");
		System.out.println("Pattern.split(): " + Arrays.toString(commaPattern.split("a, b,c ,  d")));

		Pattern digitsOnly = Pattern.compile("\\d+");
		List<String> input = List.of("123", "abc", "456", "a1b2");
		List<String> onlyNumeric = input.stream().filter(digitsOnly.asPredicate()) // reuse compiled pattern as a
																					// Predicate<String>
				.collect(Collectors.toList());
		System.out.println("Filtered with asPredicate(): " + onlyNumeric);
	}

	// =================================================================
	// 16. Common real-world patterns — email, phone, URL, password strength,
	// IP address, hex color
	// =================================================================
	private static void demoCommonRealWorldPatterns() {
		System.out.println("\n===== Common real-world patterns =====");

		String emailRegex = "^[\\w.+-]+@[\\w-]+\\.[a-zA-Z]{2,}$";
		System.out.println("Valid email: " + "user.name+tag@example.com".matches(emailRegex));
		System.out.println("Invalid email: " + "not-an-email".matches(emailRegex));

		String phoneRegex = "^\\+?\\d{1,3}[- ]?\\d{10}$";
		System.out.println("Valid phone: " + "+91 9876543210".matches(phoneRegex));

		String urlRegex = "^(https?://)?([\\w-]+\\.)+[a-z]{2,}(/\\S*)?$";
		System.out.println("Valid URL: " + "https://www.example.com/path".matches(urlRegex));

		// at least 8 chars, 1 upper, 1 lower, 1 digit, 1 special char
		String strongPasswordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";
		System.out.println("Strong password \"Passw0rd!\": " + "Passw0rd!".matches(strongPasswordRegex));
		System.out.println("Weak password \"password\": " + "password".matches(strongPasswordRegex));

		String ipv4Regex = "^(\\d{1,3}\\.){3}\\d{1,3}$";
		System.out.println("Valid IPv4 shape: " + "192.168.1.1".matches(ipv4Regex));

		String hexColorRegex = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$";
		System.out.println("Valid hex color: " + "#1A2b3C".matches(hexColorRegex));
	}

	// =================================================================
	// 17. Common pitfalls
	// =================================================================
	private static void demoCommonPitfalls() {
		System.out.println("\n===== Common pitfalls =====");

		// Pitfall 1: forgetting to escape regex metacharacters when you mean
		// them literally — "." means "any character", not a literal dot
		System.out.println(
				"split(\".\") on \"a.b.c\" [wrong, treats . as 'any char']: " + Arrays.toString("a.b.c".split(".")));
		System.out.println("split(\"\\\\.\") [correct, escaped dot]: " + Arrays.toString("a.b.c".split("\\.")));
		// Alternative: Pattern.quote() escapes an entire literal string safely
		System.out.println("Pattern.quote(\".\") -> " + Pattern.quote("."));

		// Pitfall 2: matches() vs find() confusion — matches() needs the WHOLE
		// string to match, a very common source of "why doesn't my regex work"
		System.out.println("\"abc123\".matches(\"\\\\d+\") [false — 'abc' isn't digits]: " + "abc123".matches("\\d+"));
		System.out.println("Pattern.compile(\"\\\\d+\").matcher(\"abc123\").find() [true]: "
				+ Pattern.compile("\\d+").matcher("abc123").find());

		// Pitfall 3: greedy quantifiers over-matching with generic patterns like <.*>
		// on multi-tag input — use reluctant .*? or a more specific character class
		String tags = "<a><b><c>";
		System.out.println("Greedy <.*> on \"<a><b><c>\" [over-matches]: "
				+ Pattern.compile("<.*>").matcher(tags).results().findFirst().map(MatchResult::group).orElse("n/a"));

		// Pitfall 4: recompiling the same Pattern.compile(...) inside a loop is
		// wasteful — compile once outside the loop and reuse the Pattern object
		// (String.matches()/replaceAll()/split() all recompile internally every call)

		// Pitfall 5: an invalid regex throws PatternSyntaxException at compile time
		try {
			Pattern.compile("[unclosed");
		} catch (PatternSyntaxException e) {
			System.out.println("Caught expected PatternSyntaxException for invalid regex");
		}
	}
}
