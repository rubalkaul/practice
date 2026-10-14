package stream;

import java.util.*;
import java.util.stream.*;

public class StreamApiDemo {

	public static void main(String[] args) {

		demoCreatingStreams();
		demoLaziness();
		demoIntermediateOperations();
		demoTerminalOperations();
		demoCollectorsBasics();
		demoGroupingBy();
		demoPartitioningBy();
		demoJoining();
		demoReduce();
		demoPrimitiveStreams();
		demoInfiniteStreams();
		demoParallelStreams();
		demoCommonPitfalls();
	}

	// =================================================================
	// 1. Creating streams — from collections, arrays, values, ranges
	// =================================================================
	private static void demoCreatingStreams() {
		System.out.println("\n===== Creating streams =====");

		List<String> list = List.of("a", "b", "c");
		Stream<String> fromList = list.stream();
		System.out.println("From collection: " + fromList.collect(Collectors.toList()));

		String[] array = { "x", "y", "z" };
		Stream<String> fromArray = Arrays.stream(array);
		System.out.println("From array: " + fromArray.collect(Collectors.toList()));

		Stream<Integer> fromValues = Stream.of(1, 2, 3, 4);
		System.out.println("From explicit values: " + fromValues.collect(Collectors.toList()));

		IntStream fromRange = IntStream.range(1, 5); // 1,2,3,4 — end EXCLUSIVE
		IntStream fromRangeClosed = IntStream.rangeClosed(1, 5); // 1,2,3,4,5 — end INCLUSIVE
		System.out.println("range(1,5): " + fromRange.boxed().collect(Collectors.toList()));
		System.out.println("rangeClosed(1,5): " + fromRangeClosed.boxed().collect(Collectors.toList()));

		Stream<String> empty = Stream.empty(); // useful as a safe default, avoids null checks
		System.out.println("Empty stream count: " + empty.count());
	}

	// =================================================================
	// 2. Laziness — intermediate ops do nothing until a terminal op runs
	// =================================================================
	// Each element flows through the WHOLE pipeline before the next element
	// starts (like one item passing every station on a conveyor belt),
	// rather than "filter everything, then map everything".
	private static void demoLaziness() {
		System.out.println("\n===== Laziness (intermediate vs terminal) =====");

		Stream<String> pipeline = Stream.of("Alice", "Bob", "Charlie").filter(name -> {
			System.out.println("Filtering: " + name);
			return name.length() > 3;
		}).map(name -> {
			System.out.println("Mapping: " + name);
			return name.toUpperCase();
		});

		System.out.println("Pipeline built — nothing has run yet");

		List<String> result = pipeline.collect(Collectors.toList()); // NOW it runs, per-element
		System.out.println("Result: " + result);
	}

	// =================================================================
	// 3. Intermediate operations — filter, map, sorted, distinct, limit, skip, peek
	// =================================================================
	private static void demoIntermediateOperations() {
		System.out.println("\n===== Intermediate operations =====");

		List<Integer> numbers = List.of(5, 3, 8, 3, 1, 9, 5, 2);

		List<Integer> result = numbers.stream().filter(n -> n > 2) // keep n > 2
				.distinct() // remove duplicates
				.sorted() // natural ascending order
				.map(n -> n * n) // transform each element
				.limit(3) // keep only first 3
				.collect(Collectors.toList());

		System.out.println("filter->distinct->sorted->map->limit: " + result);

		List<Integer> skipped = numbers.stream().sorted().skip(2) // drop the first 2 elements
				.collect(Collectors.toList());
		System.out.println("sorted then skip(2): " + skipped);

		// flatMap: flatten a stream of collections into one single stream
		List<List<Integer>> nested = List.of(List.of(1, 2), List.of(3, 4), List.of(5));
		List<Integer> flat = nested.stream().flatMap(List::stream) // each inner list becomes its own stream, then
																	// merged
				.collect(Collectors.toList());
		System.out.println("flatMap on nested lists: " + flat);
	}

	// =================================================================
	// 4. Terminal operations — collect, forEach, count, anyMatch, findFirst,
	// min/max
	// =================================================================
	private static void demoTerminalOperations() {
		System.out.println("\n===== Terminal operations =====");

		List<Integer> numbers = List.of(4, 9, 2, 7, 5);

		numbers.stream().forEach(n -> System.out.print(n + " "));
		System.out.println();

		System.out.println("count(): " + numbers.stream().filter(n -> n > 4).count());

		System.out.println("anyMatch(>8): " + numbers.stream().anyMatch(n -> n > 8));
		System.out.println("allMatch(>0): " + numbers.stream().allMatch(n -> n > 0));
		System.out.println("noneMatch(<0): " + numbers.stream().noneMatch(n -> n < 0));

		// findFirst short-circuits — stops as soon as a match is found,
		// without processing the rest of the source
		Optional<Integer> firstBig = numbers.stream().filter(n -> n > 5).findFirst();
		System.out.println("findFirst(>5): " + firstBig.orElse(-1));

		System.out.println("max(): " + numbers.stream().max(Integer::compareTo).orElseThrow());
		System.out.println("min(): " + numbers.stream().min(Integer::compareTo).orElseThrow());
	}

	// =================================================================
	// 5. Collectors basics — toList, toSet, toMap
	// =================================================================
	private static void demoCollectorsBasics() {
		System.out.println("\n===== Collectors: toList / toSet / toMap =====");

		List<String> names = List.of("Alice", "Bob", "Charlie", "Dave");

		List<String> asList = names.stream().collect(Collectors.toList());
		Set<String> asSet = names.stream().collect(Collectors.toSet());
		System.out.println("toList: " + asList);
		System.out.println("toSet: " + asSet);

		// toMap needs a key-mapper and a value-mapper; here: name -> its length
		Map<String, Integer> nameLengths = names.stream().collect(Collectors.toMap(name -> name, String::length));
		System.out.println("toMap (name -> length): " + nameLengths);
	}

	// =================================================================
	// 6. Collectors.groupingBy — the "SQL GROUP BY" of streams
	// =================================================================
	private static void demoGroupingBy() {
		System.out.println("\n===== Collectors.groupingBy =====");

		List<String> words = List.of("apple", "banana", "avocado", "blueberry", "cherry", "cantaloupe");

		// group by first letter -> Map<Character, List<String>>
		Map<Character, List<String>> byFirstLetter = words.stream().collect(Collectors.groupingBy(w -> w.charAt(0)));
		System.out.println("Grouped by first letter: " + byFirstLetter);

		// groupingBy + downstream collector: count per group instead of the list itself
		Map<Character, Long> countByFirstLetter = words.stream()
				.collect(Collectors.groupingBy(w -> w.charAt(0), Collectors.counting()));
		System.out.println("Count per first letter: " + countByFirstLetter);
	}

	// =================================================================
	// 7. Collectors.partitioningBy — a special case of groupingBy with
	// exactly 2 buckets, keyed by true/false, always both present
	// even if one group ends up empty
	// =================================================================
	private static void demoPartitioningBy() {
		System.out.println("\n===== Collectors.partitioningBy =====");

		List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

		Map<Boolean, List<Integer>> evenOdd = numbers.stream().collect(Collectors.partitioningBy(n -> n % 2 == 0));

		System.out.println("Evens: " + evenOdd.get(true));
		System.out.println("Odds: " + evenOdd.get(false));
	}

	// =================================================================
	// 8. Collectors.joining — building a single String from a stream
	// =================================================================
	private static void demoJoining() {
		System.out.println("\n===== Collectors.joining =====");

		List<String> names = List.of("Alice", "Bob", "Charlie");

		String simple = names.stream().collect(Collectors.joining());
		String withComma = names.stream().collect(Collectors.joining(", "));
		String withPrefixSuffix = names.stream().collect(Collectors.joining(", ", "[", "]"));

		System.out.println("joining(): " + simple);
		System.out.println("joining(\", \"): " + withComma);
		System.out.println("joining with prefix/suffix: " + withPrefixSuffix);
	}

	// =================================================================
	// 9. reduce — combining all elements into a single result
	// =================================================================
	// reduce(identity, accumulator): starts from identity, folds elements
	// one by one using the accumulator function. This is the general-purpose
	// tool that sum/max/min/count are all really specific cases of.
	private static void demoReduce() {
		System.out.println("\n===== reduce =====");

		List<Integer> numbers = List.of(1, 2, 3, 4, 5);

		int sum = numbers.stream().reduce(0, (a, b) -> a + b);
		System.out.println("Sum via reduce: " + sum);

		int product = numbers.stream().reduce(1, (a, b) -> a * b);
		System.out.println("Product via reduce: " + product);

		// reduce WITHOUT identity returns Optional<T> — the stream could be
		// empty, in which case there's no sensible result to return
		Optional<Integer> max = numbers.stream().reduce((a, b) -> a > b ? a : b);
		System.out.println("Max via reduce: " + max.orElseThrow());
	}

	// =================================================================
	// 10. Primitive streams — IntStream / LongStream / DoubleStream
	// =================================================================
	// Avoid the cost of boxing every int into an Integer object. Also
	// provide numeric-specific terminal ops: sum(), average(), summaryStatistics().
	private static void demoPrimitiveStreams() {
		System.out.println("\n===== Primitive streams =====");

		int sum = IntStream.rangeClosed(1, 10).sum();
		System.out.println("Sum 1..10: " + sum);

		OptionalDouble average = IntStream.rangeClosed(1, 10).average();
		System.out.println("Average 1..10: " + average.orElseThrow());

		IntSummaryStatistics stats = IntStream.of(4, 9, 2, 7, 5).summaryStatistics();
		System.out.println("Stats -> min: " + stats.getMin() + ", max: " + stats.getMax() + ", avg: "
				+ stats.getAverage() + ", count: " + stats.getCount());

		// converting between primitive and boxed streams
		List<Integer> boxedList = IntStream.rangeClosed(1, 5).boxed().collect(Collectors.toList());
		System.out.println("boxed() -> List<Integer>: " + boxedList);
	}

	// =================================================================
	// 11. Infinite streams — Stream.iterate / Stream.generate (must limit!)
	// =================================================================
	// These streams have no natural end — you MUST use limit() (or another
	// short-circuiting op) or the pipeline will run forever.
	private static void demoInfiniteStreams() {
		System.out.println("\n===== Infinite streams =====");

		// iterate: each next element derived from the previous one
		List<Integer> powersOfTwo = Stream.iterate(1, n -> n * 2).limit(6).collect(Collectors.toList());
		System.out.println("Powers of two via iterate: " + powersOfTwo);

		// generate: each element produced independently by a Supplier
		List<Double> randoms = Stream.generate(Math::random).limit(3).collect(Collectors.toList());
		System.out.println("Random values via generate: " + randoms);
	}

	// =================================================================
	// 12. Parallel streams — same pipeline, split across multiple threads
	// =================================================================
	// .parallelStream() (or .stream().parallel()) splits the source and
	// processes chunks concurrently using the common ForkJoinPool, then
	// merges results. Best for large datasets with CPU-heavy, STATELESS,
	// independent operations. NOT automatically faster for small data —
	// the overhead of splitting/merging can outweigh the gains.
	private static void demoParallelStreams() {
		System.out.println("\n===== Parallel streams =====");

		List<Integer> numbers = IntStream.rangeClosed(1, 20).boxed().collect(Collectors.toList());

		long count = numbers.parallelStream().filter(n -> n % 2 == 0)
				.peek(n -> System.out.println("Processed " + n + " on " + Thread.currentThread().getName())).count();

		System.out.println("Even count: " + count);
		System.out.println("(Notice multiple thread names above — work was split across threads)");
	}

	// =================================================================
	// 13. Common pitfalls
	// =================================================================
	private static void demoCommonPitfalls() {
		System.out.println("\n===== Common pitfalls =====");

		// Pitfall 1: a stream can only be consumed ONCE
		Stream<String> stream = Stream.of("a", "b", "c");
		stream.forEach(System.out::println);
		try {
			stream.forEach(System.out::println); // throws IllegalStateException
		} catch (IllegalStateException e) {
			System.out.println("Caught expected error: stream already consumed — " + e.getMessage());
		}

		// Pitfall 2: intermediate operations with NO terminal operation do NOTHING
		Stream.of("x", "y", "z").map(s -> {
			System.out.println("This will NEVER print — no terminal operation follows");
			return s.toUpperCase();
		});
		System.out.println("(the map() above silently did nothing)");

		// Pitfall 3: side effects inside map()/filter() are discouraged —
		// streams don't guarantee execution order or thread context,
		// especially once parallel() is involved. Prefer pure functions
		// and collect results properly instead of mutating external state.
	}
}
