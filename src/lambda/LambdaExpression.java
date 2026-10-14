package lambda;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * =====================================================================================
 * JAVA LAMBDA EXPRESSIONS - COMPLETE GUIDE
 * =====================================================================================
 * One file, every major lambda concept, in order, with runnable examples.
 *
 * HOW TO RUN: javac LambdaExpressionsCompleteGuide.java java
 * LambdaExpressionsCompleteGuide
 *
 * TOPICS (in order): 1. What is a Lambda? Syntax variations 2. Custom
 * Functional Interfaces + @FunctionalInterface 3. Lambda vs Anonymous Class 4.
 * Variable Capture (effectively final) + "this" behavior 5. Built-in Functional
 * Interfaces: Predicate, Function, Consumer, Supplier 6. BiFunction,
 * BiConsumer, BiPredicate 7. UnaryOperator, BinaryOperator 8. Function
 * Composition: andThen(), compose(), Predicate.and/or/negate 9. Method
 * References (4 types) 10. Comparator with Lambdas (sorting) 11. Streams with
 * Lambdas (map, filter, reduce, sorted, collect, forEach) 12. Optional with
 * Lambdas 13. Runnable / Threads with Lambdas 14. Exception Handling inside
 * Lambdas 15. Custom Generic Functional Interface 16. Recursion with Lambdas
 * 17. Why Lambdas Exist + Limitations (invokedynamic, checked exceptions,
 * self-recursion)
 * =====================================================================================
 */
public class LambdaExpression {

	public static void main(String[] args) throws Exception {
		section1_WhatIsALambda();
		section2_CustomFunctionalInterface();
		section3_LambdaVsAnonymousClass();
		section4_VariableCapture();
		section5_BuiltInFunctionalInterfaces();
		section6_BiFunctionalInterfaces();
		section7_UnaryBinaryOperator();
		section8_FunctionComposition();
		section9_MethodReferences();
		section10_ComparatorWithLambda();
		section11_StreamsWithLambda();
		section12_OptionalWithLambda();
		section13_RunnableAndThreads();
		section14_ExceptionHandling();
		section15_CustomGenericFunctionalInterface();
		section16_RecursionWithLambda();
		section17_WhyLambdasExistAndLimitations();
	}

	private static void printHeader(String title) {
		System.out.println("\n=====================================================");
		System.out.println(title);
		System.out.println("=====================================================");
	}

	// =====================================================================================
	// 1. WHAT IS A LAMBDA EXPRESSION?
	// =====================================================================================
	// A lambda expression is an anonymous (unnamed) block of code that can be
	// passed
	// around like a value. It implements a "functional interface" -- an interface
	// with
	// exactly ONE abstract method (SAM = Single Abstract Method).
	//
	// Syntax: (parameters) -> { body }
	//
	// Several equivalent ways to write the same lambda are shown below.
	private static void section1_WhatIsALambda() {
		printHeader("1. WHAT IS A LAMBDA EXPRESSION");

		// (a) No parameters
		Runnable noArgs = () -> System.out.println("Lambda with no arguments running");
		noArgs.run();

		// (b) Single parameter -- parentheses optional
		Consumer<String> singleArgWithParens = (name) -> System.out.println("Hello " + name);
		Consumer<String> singleArgNoParens = name -> System.out.println("Hi " + name);
		singleArgWithParens.accept("Rubal");
		singleArgNoParens.accept("Rubal");

		// (c) Multiple parameters -- parentheses required, types optional (inferred)
		BinaryOperator<Integer> addInferred = (a, b) -> a + b;
		BinaryOperator<Integer> addExplicit = (Integer a, Integer b) -> a + b;
		System.out.println("addInferred(2,3) = " + addInferred.apply(2, 3));
		System.out.println("addExplicit(2,3) = " + addExplicit.apply(2, 3));

		// (d) Multi-statement body needs braces + explicit return
		Function<Integer, Integer> square = (x) -> {
			int result = x * x;
			return result;
		};
		System.out.println("square(5) = " + square.apply(5));

		// (e) Single expression body -- no braces, no "return", no semicolon needed
		Function<Integer, Integer> squareShort = x -> x * x;
		System.out.println("squareShort(6) = " + squareShort.apply(6));
	}

	// =====================================================================================
	// 2. CUSTOM FUNCTIONAL INTERFACES
	// =====================================================================================
	// Any interface with exactly one abstract method can be used as a lambda
	// target.
	// @FunctionalInterface is optional but recommended -- the compiler will error
	// out
	// if you accidentally add a second abstract method.
	@FunctionalInterface
	interface Greeting {
		String greet(String name); // the single abstract method (SAM)

		// default and static methods are allowed -- they don't count against the
		// "single abstract method" rule
		default Greeting shout() {
			return name -> greet(name).toUpperCase() + "!!!";
		}
	}

	private static void section2_CustomFunctionalInterface() {
		printHeader("2. CUSTOM FUNCTIONAL INTERFACES");

		Greeting formal = name -> "Good day, " + name;
		Greeting casual = name -> "Hey " + name + "!";

		System.out.println(formal.greet("Rubal"));
		System.out.println(casual.greet("Rubal"));
		System.out.println(formal.shout().greet("Rubal")); // uses the default method
	}

	// =====================================================================================
	// 3. LAMBDA vs ANONYMOUS CLASS
	// =====================================================================================
	// A lambda is essentially a lighter-weight replacement for a single-method
	// anonymous
	// class -- less boilerplate, and (unlike an anonymous class) "this" inside a
	// lambda
	// refers to the ENCLOSING class, not the lambda itself (see section 4).
	private static void section3_LambdaVsAnonymousClass() {
		printHeader("3. LAMBDA vs ANONYMOUS CLASS");

		// Old style: anonymous inner class
		Comparator<String> byLengthAnon = new Comparator<String>() {
			@Override
			public int compare(String a, String b) {
				return Integer.compare(a.length(), b.length());
			}
		};

		// New style: lambda -- same behavior, far less code
		Comparator<String> byLengthLambda = (a, b) -> Integer.compare(a.length(), b.length());

		List<String> words = new ArrayList<>(List.of("banana", "fig", "apple"));
		words.sort(byLengthLambda);
		System.out.println("Sorted by length: " + words);
		System.out.println("Anonymous class result same? "
				+ (byLengthAnon.compare("a", "bb") == byLengthLambda.compare("a", "bb")));
	}

	// =====================================================================================
	// 4. VARIABLE CAPTURE (EFFECTIVELY FINAL) + "this" BEHAVIOR
	// =====================================================================================
	// A lambda can use local variables from the enclosing scope, but ONLY if they
	// are
	// "effectively final" (never reassigned after initialization). This is because
	// the
	// lambda may outlive the method call (e.g. run on another thread later), so
	// Java
	// captures a snapshot/copy, not a live reference.
	private int instanceCounter = 100; // used to show "this" behavior below

	private static void section4_VariableCapture() {
		printHeader("4. VARIABLE CAPTURE (EFFECTIVELY FINAL)");

		int localBase = 10; // effectively final -- never reassigned below
		Function<Integer, Integer> addBase = x -> x + localBase;
		System.out.println("addBase(5) = " + addBase.apply(5));

		// localBase = 20; // <-- Uncommenting this line would cause a COMPILE ERROR:
		// "local variables referenced from a lambda expression must be final or
		// effectively final"

		// Mutable workaround: use a holder object (e.g. array or AtomicInteger) since
		// its reference is final even though its contents can change.
		int[] counterHolder = { 0 };
		Runnable incrementer = () -> counterHolder[0]++;
		incrementer.run();
		incrementer.run();
		incrementer.run();
		System.out.println("counterHolder after 3 runs = " + counterHolder[0]);

		// "this" inside a lambda == the enclosing instance's "this" (NOT the lambda
		// itself)
		new LambdaExpression().demoThisInLambda();
	}

	private void demoThisInLambda() {
		Runnable r = () -> {
			// "this" here refers to the LambdaExpressionsCompleteGuide instance,
			// so we can access instance fields directly -- unlike an anonymous class,
			// where "this" would refer to the anonymous class instance itself.
			this.instanceCounter++;
			System.out.println("instanceCounter via lambda 'this' = " + this.instanceCounter);
		};
		r.run();
	}

	// =====================================================================================
	// 5. BUILT-IN FUNCTIONAL INTERFACES (java.util.function)
	// =====================================================================================
	// Java ships a standard set of functional interfaces so you rarely need to
	// write
	// your own. The four core ones:
	// Predicate<T> : T -> boolean (a test / condition)
	// Function<T,R> : T -> R (a transformation)
	// Consumer<T> : T -> void (an action, no return value)
	// Supplier<T> : () -> T (a factory, no input)
	private static void section5_BuiltInFunctionalInterfaces() {
		printHeader("5. PREDICATE / FUNCTION / CONSUMER / SUPPLIER");

		// Predicate<T>: tests a condition, returns boolean
		Predicate<Integer> isEven = n -> n % 2 == 0;
		System.out.println("isEven(4) = " + isEven.test(4));
		System.out.println("isEven(7) = " + isEven.test(7));

		// Function<T, R>: transforms input T into output R
		Function<String, Integer> stringLength = String::length; // method reference, see section 9
		System.out.println("stringLength(\"Kafka\") = " + stringLength.apply("Kafka"));

		// Consumer<T>: consumes a value, performs a side effect, returns nothing
		Consumer<String> printer = msg -> System.out.println("Consumed: " + msg);
		printer.accept("Spring Boot microservice started");

		// Supplier<T>: supplies/produces a value with no input
		Supplier<String> idGenerator = () -> "ORDER-" + UUID.randomUUID().toString().substring(0, 8);
		System.out.println("Generated ID: " + idGenerator.get());
	}

	// =====================================================================================
	// 6. BiFunction, BiConsumer, BiPredicate -- two-argument versions
	// =====================================================================================
	private static void section6_BiFunctionalInterfaces() {
		printHeader("6. BiFUNCTION / BiCONSUMER / BiPREDICATE");

		// BiFunction<T, U, R>: takes two inputs, produces one output
		BiFunction<Integer, Integer, Integer> multiply = (a, b) -> a * b;
		System.out.println("multiply(6, 7) = " + multiply.apply(6, 7));

		// BiConsumer<T, U>: takes two inputs, no output (side effect)
		BiConsumer<String, Integer> logKeyValue = (key, value) -> System.out.println(key + " -> " + value);
		logKeyValue.accept("kafka.partitions", 3);

		// BiPredicate<T, U>: takes two inputs, returns boolean
		BiPredicate<String, Integer> isLongerThan = (str, len) -> str.length() > len;
		System.out.println("isLongerThan(\"microservice\", 5) = " + isLongerThan.test("microservice", 5));

		// Practical example: iterating over a Map using BiConsumer via forEach
		Map<String, Integer> topicPartitions = Map.of("orders-topic", 3, "payments-topic", 6);
		topicPartitions.forEach(
				(topic, partitions) -> System.out.println("Topic '" + topic + "' has " + partitions + " partitions"));
	}

	// =====================================================================================
	// 7. UnaryOperator<T> and BinaryOperator<T>
	// =====================================================================================
	// Specializations of Function/BiFunction where input and output are the SAME
	// type.
	// UnaryOperator<T> extends Function<T, T>
	// BinaryOperator<T> extends BiFunction<T, T, T>
	private static void section7_UnaryBinaryOperator() {
		printHeader("7. UNARY / BINARY OPERATOR");

		UnaryOperator<Integer> increment = n -> n + 1;
		System.out.println("increment(9) = " + increment.apply(9));

		UnaryOperator<String> upperCase = String::toUpperCase;
		System.out.println("upperCase(\"spring\") = " + upperCase.apply("spring"));

		BinaryOperator<Integer> max = BinaryOperator.maxBy(Comparator.naturalOrder());
		System.out.println("max(15, 42) = " + max.apply(15, 42));

		BinaryOperator<Integer> sum = Integer::sum;
		System.out.println("sum(4, 5) = " + sum.apply(4, 5));
	}

	// =====================================================================================
	// 8. FUNCTION COMPOSITION
	// =====================================================================================
	// Functional interfaces provide default methods to chain/combine lambdas:
	// Function: andThen(after) -> run this, THEN after (this -> after)
	// compose(before) -> run before, THEN this (before -> this)
	// Predicate: and(other), or(other), negate()
	private static void section8_FunctionComposition() {
		printHeader("8. FUNCTION COMPOSITION");

		Function<Integer, Integer> doubleIt = n -> n * 2;
		Function<Integer, Integer> addTen = n -> n + 10;

		// andThen: doubleIt first, then addTen => (5*2)+10 = 20
		Function<Integer, Integer> doubleThenAddTen = doubleIt.andThen(addTen);
		System.out.println("doubleThenAddTen(5) = " + doubleThenAddTen.apply(5));

		// compose: addTen first, then doubleIt => (5+10)*2 = 30
		Function<Integer, Integer> addTenThenDouble = doubleIt.compose(addTen);
		System.out.println("addTenThenDouble(5) = " + addTenThenDouble.apply(5));

		// Predicate composition
		Predicate<Integer> isPositive = n -> n > 0;
		Predicate<Integer> isEven = n -> n % 2 == 0;

		Predicate<Integer> isPositiveAndEven = isPositive.and(isEven);
		Predicate<Integer> isPositiveOrEven = isPositive.or(isEven);
		Predicate<Integer> isNotPositive = isPositive.negate();

		System.out.println("isPositiveAndEven(4) = " + isPositiveAndEven.test(4));
		System.out.println("isPositiveAndEven(-4) = " + isPositiveAndEven.test(-4));
		System.out.println("isPositiveOrEven(-4) = " + isPositiveOrEven.test(-4));
		System.out.println("isNotPositive(-4) = " + isNotPositive.test(-4));

		// Consumer composition: andThen runs both consumers in sequence on the same
		// input
		Consumer<String> logToConsole = msg -> System.out.println("[LOG] " + msg);
		Consumer<String> logWithPrefix = logToConsole.andThen(msg -> System.out.println("[AUDIT] " + msg));
		logWithPrefix.accept("Order created");
	}

	// =====================================================================================
	// 9. METHOD REFERENCES -- shorthand syntax for lambdas that just call one
	// method
	// =====================================================================================
	// Four kinds:
	// 1. Static method reference ClassName::staticMethod
	// 2. Instance method on a particular object instance::instanceMethod
	// 3. Instance method on an arbitrary object of a type ClassName::instanceMethod
	// 4. Constructor reference ClassName::new
	static int square(int n) { // used for the static method reference example
		return n * n;
	}

	private static void section9_MethodReferences() {
		printHeader("9. METHOD REFERENCES");

		// 1. Static method reference -- equivalent to: n -> square(n)
		Function<Integer, Integer> squareRef = LambdaExpression::square;
		System.out.println("squareRef(9) = " + squareRef.apply(9));

		// 2. Instance method reference on a PARTICULAR object -- equivalent to: () ->
		// System.out.println(...)
		String message = "Hello from a bound instance method reference";
		Supplier<Integer> lengthOfMessage = message::length; // "message" is the particular bound object
		System.out.println("lengthOfMessage.get() = " + lengthOfMessage.get());

		// 3. Instance method reference on an ARBITRARY object of a type
		// equivalent to: (s1, s2) -> s1.compareToIgnoreCase(s2)
		Comparator<String> ignoreCaseCompare = String::compareToIgnoreCase;
		System.out.println("ignoreCaseCompare(\"Apple\",\"apple\") = " + ignoreCaseCompare.compare("Apple", "apple"));

		// 4. Constructor reference -- equivalent to: () -> new ArrayList<>()
		Supplier<List<String>> listFactory = ArrayList::new;
		List<String> newList = listFactory.get();
		newList.add("created via constructor reference");
		System.out.println(newList);

		// Constructor reference with an argument -- equivalent to: s -> new
		// StringBuilder(s)
		Function<String, StringBuilder> sbFactory = StringBuilder::new;
		System.out.println("sbFactory(\"abc\").reverse() = " + sbFactory.apply("abc").reverse());
	}

	// =====================================================================================
	// 10. COMPARATOR WITH LAMBDAS (sorting is the #1 real-world lambda use case)
	// =====================================================================================
	record Employee(String name, int age, double salary) {
	}

	private static void section10_ComparatorWithLambda() {
		printHeader("10. COMPARATOR WITH LAMBDAS");

		List<Employee> employees = new ArrayList<>(List.of(new Employee("Rubal", 28, 95000),
				new Employee("Tejinder", 31, 87000), new Employee("Aman", 25, 102000)));

		// Basic lambda comparator
		employees.sort((e1, e2) -> Integer.compare(e1.age(), e2.age()));
		System.out.println("Sorted by age: " + employees);

		// Cleaner using Comparator.comparing + method reference
		employees.sort(Comparator.comparing(Employee::salary));
		System.out.println("Sorted by salary: " + employees);

		// Reversed order
		employees.sort(Comparator.comparing(Employee::salary).reversed());
		System.out.println("Sorted by salary DESC: " + employees);

		// Multi-level sorting: by age, then by name if ages tie
		employees.sort(Comparator.comparing(Employee::age).thenComparing(Employee::name));
		System.out.println("Sorted by age then name: " + employees);
	}

	// =====================================================================================
	// 11. STREAMS WITH LAMBDAS -- this is where lambdas shine the most
	// =====================================================================================
	private static void section11_StreamsWithLambda() {
		printHeader("11. STREAMS WITH LAMBDAS");

		List<Employee> employees = List.of(new Employee("Rubal", 28, 95000), new Employee("Tejinder", 31, 87000),
				new Employee("Aman", 25, 102000), new Employee("Simran", 34, 78000));

		// filter: keep elements matching a Predicate
		List<Employee> highEarners = employees.stream().filter(e -> e.salary() > 90000).collect(Collectors.toList());
		System.out.println("High earners: " + highEarners);

		// map: transform each element using a Function
		List<String> names = employees.stream().map(Employee::name).collect(Collectors.toList());
		System.out.println("Names: " + names);

		// sorted: order elements using a Comparator
		List<String> namesSortedByAge = employees.stream().sorted(Comparator.comparing(Employee::age))
				.map(Employee::name).collect(Collectors.toList());
		System.out.println("Names sorted by age: " + namesSortedByAge);

		// reduce: combine all elements into a single result using a BinaryOperator
		double totalSalary = employees.stream().map(Employee::salary).reduce(0.0, Double::sum);
		System.out.println("Total salary: " + totalSalary);

		// forEach: perform a Consumer action on each element
		employees.stream().filter(e -> e.age() > 30).forEach(e -> System.out.println(e.name() + " is over 30"));

		// count, anyMatch, allMatch, noneMatch -- all take Predicates
		long countOver30 = employees.stream().filter(e -> e.age() > 30).count();
		boolean anyoneUnder20 = employees.stream().anyMatch(e -> e.age() < 20);
		boolean allEarnAbove50k = employees.stream().allMatch(e -> e.salary() > 50000);
		System.out.println("countOver30=" + countOver30 + ", anyoneUnder20=" + anyoneUnder20 + ", allEarnAbove50k="
				+ allEarnAbove50k);

		// Collectors.groupingBy: group employees by a classification lambda
		Map<Boolean, List<Employee>> groupedByHighEarner = employees.stream()
				.collect(Collectors.groupingBy(e -> e.salary() > 90000));
		System.out.println("Grouped by high earner: " + groupedByHighEarner);

		// Collectors.toMap: build a Map using two lambdas (key extractor, value
		// extractor)
		Map<String, Double> nameToSalary = employees.stream()
				.collect(Collectors.toMap(Employee::name, Employee::salary));
		System.out.println("Name -> Salary map: " + nameToSalary);
	}

	// =====================================================================================
	// 12. OPTIONAL WITH LAMBDAS -- avoiding null checks
	// =====================================================================================
	private static void section12_OptionalWithLambda() {
		printHeader("12. OPTIONAL WITH LAMBDAS");

		Optional<String> maybeName = Optional.of("Rubal");
		Optional<String> empty = Optional.empty();

		// ifPresent: run a Consumer only if value exists
		maybeName.ifPresent(name -> System.out.println("Present: " + name));

		// ifPresentOrElse: Consumer if present, Runnable if absent
		empty.ifPresentOrElse(name -> System.out.println("Present: " + name),
				() -> System.out.println("No value present"));

		// map: transform the value inside the Optional if present
		Optional<Integer> nameLength = maybeName.map(String::length);
		System.out.println("nameLength = " + nameLength.orElse(0));

		// filter: keep the value only if it matches a Predicate
		Optional<String> longNameOnly = maybeName.filter(n -> n.length() > 10);
		System.out.println("longNameOnly present? " + longNameOnly.isPresent());

		// orElseGet: Supplier used only if empty (lazy -- better than orElse for
		// expensive defaults)
		String result = empty.orElseGet(() -> "DEFAULT_NAME");
		System.out.println("orElseGet result = " + result);
	}

	// =====================================================================================
	// 13. RUNNABLE / THREADS WITH LAMBDAS
	// =====================================================================================
	private static void section13_RunnableAndThreads() throws InterruptedException {
		printHeader("13. RUNNABLE / THREADS WITH LAMBDAS");

		// Old style: Runnable r = new Runnable() { public void run() {...} };
		Runnable task = () -> System.out.println("Running on thread: " + Thread.currentThread().getName());

		Thread thread = new Thread(task);
		thread.start();
		thread.join(); // wait for it to finish before continuing

		// Common in Spring Boot: submitting lambdas to an ExecutorService
		java.util.concurrent.ExecutorService executor = java.util.concurrent.Executors.newSingleThreadExecutor();
		executor.submit(() -> System.out.println("Executed via ExecutorService lambda"));
		executor.shutdown();
	}

	// =====================================================================================
	// 14. EXCEPTION HANDLING INSIDE LAMBDAS
	// =====================================================================================
	// Lambdas that implement standard functional interfaces (Function, Consumer,
	// etc.)
	// CANNOT throw checked exceptions directly, because the interface's abstract
	// method
	// doesn't declare "throws". Two common solutions are shown below.
	@FunctionalInterface
	interface CheckedFunction<T, R> {
		R apply(T t) throws Exception; // custom interface that DOES declare "throws"
	}

	private static void section14_ExceptionHandling() {
		printHeader("14. EXCEPTION HANDLING INSIDE LAMBDAS");

		// Solution 1: catch and wrap the checked exception inside the lambda body
		Function<String, Integer> safeParseInt = str -> {
			try {
				return Integer.parseInt(str);
			} catch (NumberFormatException e) {
				System.out.println("Invalid number: " + str + " -- defaulting to 0");
				return 0;
			}
		};
		System.out.println("safeParseInt(\"42\") = " + safeParseInt.apply("42"));
		System.out.println("safeParseInt(\"abc\") = " + safeParseInt.apply("abc"));

		// Solution 2: define your own functional interface that declares "throws"
		CheckedFunction<String, Integer> checkedParse = Integer::parseInt;
		try {
			System.out.println("checkedParse(\"123\") = " + checkedParse.apply("123"));
		} catch (Exception e) {
			System.out.println("Caught: " + e.getMessage());
		}
	}

	// =====================================================================================
	// 15. CUSTOM GENERIC FUNCTIONAL INTERFACE
	// =====================================================================================
	// You can define your own generic functional interfaces for domain-specific use
	// cases
	// -- e.g. this mirrors the shape of a Spring AI "tool" or Kafka message
	// transformer.
	@FunctionalInterface
	interface Transformer<INPUT, OUTPUT> {
		OUTPUT transform(INPUT input);

		default <NEXT> Transformer<INPUT, NEXT> then(Transformer<OUTPUT, NEXT> next) {
			return input -> next.transform(this.transform(input));
		}
	}

	private static void section15_CustomGenericFunctionalInterface() {
		printHeader("15. CUSTOM GENERIC FUNCTIONAL INTERFACE");

		Transformer<String, String> toUpper = String::toUpperCase;
		Transformer<String, Integer> lengthOf = String::length;

		Transformer<String, Integer> upperThenLength = toUpper.then(lengthOf);
		System.out.println("upperThenLength(\"microservice\") = " + upperThenLength.transform("microservice"));
	}

	// =====================================================================================
	// 16. RECURSION WITH LAMBDAS
	// =====================================================================================
	// A lambda variable can't directly reference itself while being assigned (it's
	// not
	// "effectively final" during its own initialization). Common workarounds: use
	// an
	// array/field holder, or define a static/instance method and reference it.
	private static Function<Integer, Integer> factorial;

	private static void section16_RecursionWithLambda() {
		printHeader("16. RECURSION WITH LAMBDAS");

		// Workaround: assign to a static field so the lambda can call "factorial"
		// itself
		factorial = n -> (n <= 1) ? 1 : n * factorial.apply(n - 1);
		System.out.println("factorial(5) = " + factorial.apply(5));

		// Cleaner alternative for real projects: just use a plain recursive method
		System.out.println("fibonacci(10) = " + fibonacci(10));
	}

	private static int fibonacci(int n) {
		return (n <= 1) ? n : fibonacci(n - 1) + fibonacci(n - 2);
	}

	// =====================================================================================
	// 17. WHY LAMBDAS EXIST + KNOWN LIMITATIONS
	// =====================================================================================
	// WHY THEY EXIST:
	// Before Java 8, "passing behavior as a value" meant writing an anonymous inner
	// class -- lots of ceremony (new Interface() { public R method(T t) {...} })
	// just
	// to express one line of logic. Lambdas let you write ONLY the essential part:
	// the parameters and the body. This is what makes Streams, event callbacks, and
	// functional-style APIs (like Spring's RowMapper, @EventListener handlers,
	// Kafka
	// consumer record handlers) so much more compact than the old-style
	// equivalents.
	//
	// HOW THEY'RE COMPILED (good to know, rarely needs action):
	// Unlike anonymous classes, the compiler does NOT generate a separate .class
	// file
	// for every lambda. Lambdas are compiled using the "invokedynamic" bytecode
	// instruction; the actual implementation class is generated lazily at runtime
	// by
	// the JVM the first time the lambda is executed. This makes lambdas
	// lighter-weight
	// at startup and avoids class-file bloat in large codebases with many lambdas.
	//
	// LIMITATION 1 -- Checked exceptions:
	// A lambda can only throw a checked exception if the functional interface's
	// abstract method declares "throws" in its signature. Function<T,R>.apply()
	// does
	// NOT declare throws, so this would NOT compile:
	// Function<String,Integer> bad = s -> { throw new java.io.IOException(); }; //
	// ERROR
	// Fix: catch it inside the lambda body (see section 14), or define your own
	// functional interface whose method declares "throws Exception".
	//
	// LIMITATION 2 -- No self-recursion during initialization:
	// A lambda variable is not "effectively final" while it is still being
	// assigned,
	// so it cannot call itself by its own local variable name:
	// Function<Integer,Integer> fact = n -> n <= 1 ? 1 : n * fact.apply(n - 1); //
	// ERROR
	// Fix: store it in a field (see section 16), or just use a normal recursive
	// method
	// -- which is usually the cleaner choice for real recursion anyway.
	//
	// LIMITATION 3 -- No overloading / no state of their own:
	// A lambda always implements exactly one method of exactly one functional
	// interface -- it cannot have multiple overloaded behaviors or its own instance
	// fields the way a full class can. If you need real state or multiple methods,
	// write a class instead.
	private static void section17_WhyLambdasExistAndLimitations() {
		printHeader("17. WHY LAMBDAS EXIST + LIMITATIONS");

		System.out.println("Reason lambdas exist: replace boilerplate anonymous classes");
		System.out.println("with just (parameters) -> body for single-method interfaces.");

		System.out.println("\nCompiled via 'invokedynamic' -- no extra .class file per lambda,");
		System.out.println("implementation class generated lazily by the JVM at first use.");

		System.out.println("\nLimitation demo -- checked exception handled INSIDE the lambda:");
		Function<String, Integer> safeRead = text -> {
			try {
				return Integer.parseInt(text.trim());
			} catch (NumberFormatException e) {
				return -1; // can't let a checked/unchecked exception escape Function's contract cleanly
			}
		};
		System.out.println("safeRead(\"  99 \") = " + safeRead.apply("  99 "));

		System.out.println("\nLimitation demo -- lambdas hold no extra state of their own,");
		System.out.println("they only capture effectively-final variables from the enclosing scope.");
	}
}