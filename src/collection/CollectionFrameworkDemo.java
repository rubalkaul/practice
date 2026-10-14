package collection;

import java.util.*;

public class CollectionFrameworkDemo {

	public static void main(String[] args) {

		demoArrayListVsLinkedList();
		demoHashSet();
		demoLinkedHashSet();
		demoTreeSet();
		demoHashMap();
		demoLinkedHashMap();
		demoTreeMap();
		demoQueueWithLinkedList();
		demoArrayDeque();
		demoPriorityQueue();
		demoIteratorAndFailFast();
		demoComparableVsComparator();
		demoImmutableCollections();

		demoTraverseList();
		demoTraverseSet();
		demoTraverseMap();
		demoTraverseQueueAndDeque();
		demoTraverseUsingWhileLoop();
	}

	// =================================================================
	// 1. List — ArrayList vs LinkedList
	// =================================================================
	// ArrayList: backed by a resizable Object[] array.
	// - get(index) -> O(1) (direct array index access)
	// - add at end -> O(1) amortized (occasional resize copies the array, O(n))
	// - add/remove in the MIDDLE -> O(n) (everything after must shift)
	//
	// LinkedList: backed by a doubly-linked list of nodes (prev/next pointers).
	// - get(index) -> O(n) (must walk the chain from the nearest end)
	// - add/remove at the ENDS -> O(1) (just relink pointers, no shifting)
	// - add/remove in the MIDDLE -> still O(n) to find the position first,
	// but the actual insertion itself is O(1) once you're there
	//
	// Rule of thumb: ArrayList by default (better cache locality, simpler).
	// LinkedList only when you genuinely do frequent add/remove at the head
	// or need it as a Deque (Stage 5).
	private static void demoArrayListVsLinkedList() {
		System.out.println("\n===== ArrayList vs LinkedList =====");

		List<Integer> arrayList = new ArrayList<>();
		List<Integer> linkedList = new LinkedList<>();

		for (int i = 0; i < 5; i++) {
			arrayList.add(i);
			linkedList.add(i);
		}

		System.out.println("ArrayList: " + arrayList);
		System.out.println("LinkedList: " + linkedList);

		// fast random access on ArrayList
		System.out.println("ArrayList.get(2) = " + arrayList.get(2));

		// LinkedList shines at the ends
		((LinkedList<Integer>) linkedList).addFirst(-1);
		((LinkedList<Integer>) linkedList).addLast(99);
		System.out.println("LinkedList after addFirst/addLast: " + linkedList);
	}

	// =================================================================
	// 2. Set — HashSet
	// =================================================================
	// Backed internally by a HashMap (each element is stored as a KEY,
	// with a dummy constant value). No duplicates, NO ordering guarantee
	// at all — iteration order depends on hash bucket layout, not insertion.
	//
	// Uniqueness is decided by equals()/hashCode() — two objects that are
	// .equals() to each other AND have the same hashCode() are treated as
	// the same element. If you don't override both consistently on your
	// own classes, HashSet (and HashMap) will silently misbehave — e.g.
	// letting in "duplicates" that are logically equal, or failing to find
	// an element that IS present, because default Object.hashCode() is
	// based on identity, not content.
	private static void demoHashSet() {
		System.out.println("\n===== HashSet =====");

		Set<String> set = new HashSet<>();
		set.add("banana");
		set.add("apple");
		set.add("cherry");
		set.add("apple"); // duplicate — silently ignored, add() returns false

		System.out.println("HashSet (no guaranteed order): " + set);
		System.out.println("Contains 'apple'? " + set.contains("apple"));
	}

	// =================================================================
	// 3. Set — LinkedHashSet
	// =================================================================
	// Same uniqueness rules as HashSet, but ALSO maintains a doubly-linked
	// list running through the entries to preserve INSERTION order.
	// Slightly more memory/overhead than HashSet for that ordering guarantee.
	// Use when you need "no duplicates" + "predictable iteration order".
	private static void demoLinkedHashSet() {
		System.out.println("\n===== LinkedHashSet =====");

		Set<String> set = new LinkedHashSet<>();
		set.add("banana");
		set.add("apple");
		set.add("cherry");

		System.out.println("LinkedHashSet (insertion order preserved): " + set);
	}

	// =================================================================
	// 4. Set — TreeSet
	// =================================================================
	// Backed by a Red-Black tree (self-balancing binary search tree).
	// Elements are always kept in SORTED order (natural ordering via
	// Comparable, or a Comparator you supply — see Stage 7).
	// - add/remove/contains -> O(log n), not O(1) like HashSet
	// - gives you extra navigation methods: first(), last(), floor(),
	// ceiling(), headSet(), tailSet() etc. (the NavigableSet contract)
	private static void demoTreeSet() {
		System.out.println("\n===== TreeSet =====");

		TreeSet<Integer> set = new TreeSet<>();
		set.add(50);
		set.add(10);
		set.add(30);
		set.add(20);

		System.out.println("TreeSet (always sorted): " + set);
		System.out.println("First (smallest): " + set.first());
		System.out.println("Last (largest): " + set.last());
		System.out.println("Elements < 30 (headSet): " + set.headSet(30));
		System.out.println("Closest value >= 25 (ceiling): " + set.ceiling(25));
	}

	// =================================================================
	// 5. Map — HashMap
	// =================================================================
	// Array of "buckets"; each bucket's index is derived from key.hashCode().
	// Multiple keys landing in the same bucket (a "collision") are stored
	// as a small linked list within that bucket (or, since Java 8, converted
	// to a mini red-black tree if a single bucket gets too crowded — a
	// performance safeguard against worst-case O(n) lookups).
	// Average case: get/put/remove -> O(1). Worst case (bad hashCode,
	// lots of collisions): degrades toward O(log n) with treeified buckets
	// (pre-Java 8, it degraded all the way to O(n)).
	// No ordering guarantee at all — same caveat as HashSet, for the same
	// underlying reason (HashSet IS a HashMap internally).
	private static void demoHashMap() { 
		System.out.println("\n===== HashMap =====");

		Map<String, Integer> map = new HashMap<>();
		map.put("apple", 3);
		map.put("banana", 5);
		map.put("cherry", 7);
		map.put("apple", 4); // overwrites the previous value for "apple"

		System.out.println("HashMap (no guaranteed order): " + map);
		System.out.println("map.get(\"apple\") = " + map.get("apple"));
		System.out.println("map.getOrDefault(\"banana\", 0) = " + map.getOrDefault("banana", 0));
		System.out.println("map.getOrDefault(\"grape\", 0) = " + map.getOrDefault("grape", 0));
	}

	// =================================================================
	// 6. Map — LinkedHashMap
	// =================================================================
	// Same bucket structure as HashMap, plus a linked list preserving
	// insertion order (or, with the right constructor, ACCESS order —
	// which is exactly what makes it useful for building a simple LRU cache
	// by overriding removeEldestEntry()).
	private static void demoLinkedHashMap() {
		System.out.println("\n===== LinkedHashMap =====");

		Map<String, Integer> map = new LinkedHashMap<>();
		map.put("banana", 5);
		map.put("apple", 3);
		map.put("cherry", 7);

		System.out.println("LinkedHashMap (insertion order preserved): " + map);
	}

	// =================================================================
	// 7. Map — TreeMap
	// =================================================================
	// Red-black tree keyed by... the keys. Always sorted by key (natural
	// order or a Comparator). O(log n) operations, same navigation methods
	// as TreeSet but for keys: firstKey(), lastKey(), floorKey(), etc.
	private static void demoTreeMap() {
		System.out.println("\n===== TreeMap =====");

		TreeMap<String, Integer> map = new TreeMap<>();
		map.put("banana", 5);
		map.put("apple", 3);
		map.put("cherry", 7);

		System.out.println("TreeMap (sorted by key): " + map);
		System.out.println("First key: " + map.firstKey());
		System.out.println("Entries before 'cherry': " + map.headMap("cherry"));
	}

	// =================================================================
	// 8. Queue — using LinkedList as a Queue (FIFO)
	// =================================================================
	// Queue is an interface; LinkedList implements it directly, giving you
	// FIFO behavior via offer()/poll()/peek() in addition to its List methods.
	private static void demoQueueWithLinkedList() {
		System.out.println("\n===== Queue (LinkedList) =====");

		Queue<String> queue = new LinkedList<>();
		queue.offer("first");
		queue.offer("second");
		queue.offer("third");

		System.out.println("Queue: " + queue);
		System.out.println("peek() (look, don't remove): " + queue.peek());
		System.out.println("poll() (remove head): " + queue.poll());
		System.out.println("Queue after poll: " + queue);
	}

	// =================================================================
	// 9. Deque — ArrayDeque
	// =================================================================
	// Backed by a resizable array (circular buffer), NOT a linked list —
	// despite the name similarity to LinkedList's Deque usage. Generally
	// faster than LinkedList for stack/queue use because of better cache
	// locality and no per-node object overhead.
	// Can be used as:
	// - a STACK (LIFO) via push()/pop() (JDK docs recommend ArrayDeque
	// over the old legacy Stack class for this)
	// - a QUEUE (FIFO) via offer()/poll()
	// - a genuine double-ended structure via
	// addFirst/addLast/removeFirst/removeLast
	private static void demoArrayDeque() {
		System.out.println("\n===== ArrayDeque =====");

		Deque<Integer> stack = new ArrayDeque<>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		System.out.println("Stack (LIFO) after pushes: " + stack);
		System.out.println("pop(): " + stack.pop()); // removes 3 (last pushed)

		Deque<Integer> deque = new ArrayDeque<>();
		deque.addFirst(10);
		deque.addLast(20);
		deque.addFirst(5);
		System.out.println("Deque (both ends used): " + deque);
	}

	// =================================================================
	// 10. PriorityQueue
	// =================================================================
	// Backed by a binary heap (array-based). NOT sorted overall — only
	// guarantees the SMALLEST (by natural order or Comparator) element is
	// always at the head, retrievable in O(log n) via poll(). Iterating it
	// directly does NOT give you sorted order — only repeated poll() does.
	private static void demoPriorityQueue() {
		System.out.println("\n===== PriorityQueue =====");

		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.offer(50);
		pq.offer(10);
		pq.offer(30);
		pq.offer(20);

		System.out.println("Internal array is NOT fully sorted: " + pq);
		System.out.print("But poll() always gives smallest-first: ");
		while (!pq.isEmpty()) {
			System.out.print(pq.poll() + " ");
		}
		System.out.println();

		// Max-heap instead, using a Comparator (see Stage 7 below for more)
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
		maxHeap.addAll(List.of(50, 10, 30, 20));
		System.out.println("Max-heap poll order: ");
		while (!maxHeap.isEmpty()) {
			System.out.print(maxHeap.poll() + " ");
		}
		System.out.println();
	}

	// =================================================================
	// 11. Iterator — safe removal, and fail-fast ConcurrentModificationException
	// =================================================================
	// Modifying a collection with its own add()/remove() WHILE iterating
	// with a for-each loop throws ConcurrentModificationException, because
	// the collection keeps an internal modCount that the iterator checks
	// on every next() call — this is "fail-fast" behavior, designed to
	// surface bugs immediately instead of letting you silently corrupt state.
	// The correct fix is Iterator.remove(), which updates modCount safely.
	private static void demoIteratorAndFailFast() {
		System.out.println("\n===== Iterator: safe removal vs fail-fast =====");

		List<Integer> numbers = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));

		// WRONG way — will throw ConcurrentModificationException
		try {
			for (Integer n : numbers) {
				if (n % 2 == 0) {
					numbers.remove(n); // structural modification during for-each
				}
			}
		} catch (ConcurrentModificationException e) {
			System.out.println("Caught expected CME from unsafe removal during for-each");
		}

		// RIGHT way — use the Iterator's own remove()
		List<Integer> numbers2 = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));
		Iterator<Integer> it = numbers2.iterator();
		while (it.hasNext()) {
			int n = it.next();
			if (n % 2 == 0) {
				it.remove(); // safe — the iterator updates its own bookkeeping
			}
		}
		System.out.println("Safely removed evens using Iterator.remove(): " + numbers2);
	}

	// =================================================================
	// 12. Comparable vs Comparator
	// =================================================================
	// Comparable<T>: the class itself defines its ONE natural ordering,
	// via compareTo(). Implemented BY the class being sorted.
	// Comparator<T>: an EXTERNAL, pluggable ordering, via compare(a, b).
	// You can define as many Comparators as you want for the same class,
	// without touching the class itself — useful when you don't own the
	// class, or need different sort orders in different places.
	private static void demoComparableVsComparator() {
		System.out.println("\n===== Comparable vs Comparator =====");

		List<Person> people = new ArrayList<>(
				List.of(new Person("Charlie", 35), new Person("Alice", 28), new Person("Bob", 42)));

		// Comparable: Person defines its own natural order (by name, see class below)
		Collections.sort(people);
		System.out.println("Sorted by natural order (name, via Comparable): " + people);

		// Comparator: external ordering, by age, without changing Person at all
		people.sort(Comparator.comparingInt(Person::getAge));
		System.out.println("Sorted by age (via Comparator): " + people);

		// Comparator chaining: age descending, then name ascending as tie-break
		people.sort(Comparator.comparingInt(Person::getAge).reversed().thenComparing(Person::getName));
		System.out.println("Sorted by age desc, name asc (chained Comparator): " + people);
	}

	private static class Person implements Comparable<Person> {
		private final String name;
		private final int age;

		Person(String name, int age) {
			this.name = name;
			this.age = age;
		}

		String getName() {
			return name;
		}

		int getAge() {
			return age;
		}

		@Override
		public int compareTo(Person other) {
			return this.name.compareTo(other.name); // natural order = by name
		}

		@Override
		public String toString() {
			return name + "(" + age + ")";
		}
	}

	// =================================================================
	// 13. Immutable / unmodifiable collections
	// =================================================================
	// List.of(...) / Set.of(...) / Map.of(...) (Java 9+): TRULY immutable —
	// any mutation attempt throws UnsupportedOperationException, and they
	// reject null elements outright.
	//
	// Collections.unmodifiableList(list): a VIEW over an existing mutable
	// list. You can't modify it through the view, but if someone still
	// holds a reference to the original mutable list, changes there DO
	// show up through the "unmodifiable" view — it's a read-only window,
	// not a true immutable copy. Real immutability requires nobody else
	// holding a mutable reference to the backing data.
	private static void demoImmutableCollections() {
		System.out.println("\n===== Immutable collections =====");

		List<String> trulyImmutable = List.of("a", "b", "c");
		try {
			trulyImmutable.add("d");
		} catch (UnsupportedOperationException e) {
			System.out.println("List.of() rejected add(): UnsupportedOperationException");
		}

		List<String> mutable = new ArrayList<>(List.of("x", "y", "z"));
		List<String> view = Collections.unmodifiableList(mutable);
		System.out.println("Unmodifiable view: " + view);

		mutable.add("w"); // legal — we're modifying the ORIGINAL list, not the view
		System.out.println("View reflects the change made via the original list: " + view);

		try {
			view.add("v"); // still illegal directly through the view
		} catch (UnsupportedOperationException e) {
			System.out.println("Direct add() through the view still rejected");
		}
	}

	// =================================================================
	// 14. Traversing a List — every common way, in order of how you'd
	// usually reach for them
	// =================================================================
	// 1) classic index-based for loop — needs the index itself; only
	// efficient for ArrayList (see O(n) get() cost on LinkedList above)
	// 2) enhanced for-each loop — cleanest, uses the List's
	// iterator() under the hood, read-only intent
	// 3) explicit Iterator — needed when you must REMOVE
	// elements safely while traversing (see demoIteratorAndFailFast)
	// 4) ListIterator — like Iterator, but bidirectional
	// (hasPrevious/previous) and supports in-place set()/add() too,
	// which plain Iterator does not
	// 5) forEach(Consumer) with a lambda — functional style, internally
	// just does the iterator loop for you
	private static void demoTraverseList() {
		System.out.println("\n===== Traversing a List =====");

		List<String> fruits = new ArrayList<>(List.of("apple", "banana", "cherry"));

		System.out.print("1) Index-based for loop: ");
		for (int i = 0; i < fruits.size(); i++) {
			System.out.print(fruits.get(i) + " ");
		}
		System.out.println();

		System.out.print("2) Enhanced for-each: ");
		for (String fruit : fruits) {
			System.out.print(fruit + " ");
		}
		System.out.println();

		System.out.print("3) Explicit Iterator: ");
		Iterator<String> it = fruits.iterator();
		while (it.hasNext()) {
			System.out.print(it.next() + " ");
		}
		System.out.println();

		System.out.print("4) ListIterator (forward then backward): ");
		ListIterator<String> lit = fruits.listIterator();
		while (lit.hasNext()) {
			System.out.print(lit.next() + " ");
		}
		System.out.print("| backward: ");
		while (lit.hasPrevious()) {
			System.out.print(lit.previous() + " ");
		}
		System.out.println();

		System.out.print("5) forEach with lambda: ");
		fruits.forEach(fruit -> System.out.print(fruit + " "));
		System.out.println();
	}

	// =================================================================
	// 15. Traversing a Set — no index exists (Set has no get(i)), so
	// only iteration-style traversal is possible
	// =================================================================
	// Same 3 iteration styles as List minus the index-based loop, since
	// Set doesn't support positional access at all. Order seen depends on
	// which Set implementation you're traversing (HashSet: unordered,
	// LinkedHashSet: insertion order, TreeSet: sorted order).
	private static void demoTraverseSet() {
		System.out.println("\n===== Traversing a Set =====");

		Set<String> set = new LinkedHashSet<>(List.of("banana", "apple", "cherry"));

		System.out.print("Enhanced for-each: ");
		for (String s : set) {
			System.out.print(s + " ");
		}
		System.out.println();

		System.out.print("Explicit Iterator: ");
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			System.out.print(it.next() + " ");
		}
		System.out.println();

		System.out.print("forEach with lambda: ");
		set.forEach(s -> System.out.print(s + " "));
		System.out.println();
	}

	// =================================================================
	// 16. Traversing a Map — a Map is NOT Iterable itself (no iterator()
	// method on Map directly). You always traverse it through one of
	// its three "view" collections instead.
	// =================================================================
	// - entrySet() -> Set<Map.Entry<K,V>> — BEST when you need both key
	// and value together; only ONE lookup per entry, not two
	// - keySet() -> Set<K> — when you only need keys (or
	// need to call map.get(key) yourself per key — an extra lookup cost
	// per entry compared to entrySet)
	// - values() -> Collection<V> — when you only care about values
	// - forEach(BiConsumer) — functional style, gives you
	// key AND value per call without manually unpacking an Entry
	private static void demoTraverseMap() {
		System.out.println("\n===== Traversing a Map =====");

		Map<String, Integer> map = new LinkedHashMap<>();
		map.put("apple", 3);
		map.put("banana", 5);
		map.put("cherry", 7);

		System.out.println("1) entrySet() — key+value together, one lookup each:");
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			System.out.println("   " + entry.getKey() + " -> " + entry.getValue());
		}

		System.out.print("2) keySet() — keys only (then get() per key if needed): ");
		for (String key : map.keySet()) {
			System.out.print(key + "=" + map.get(key) + " ");
		}
		System.out.println();

		System.out.print("3) values() — values only, no keys available: ");
		for (Integer value : map.values()) {
			System.out.print(value + " ");
		}
		System.out.println();

		System.out.println("4) forEach(BiConsumer) — functional style:");
		map.forEach((key, value) -> System.out.println("   " + key + " -> " + value));

		System.out.print("5) entrySet().iterator() — explicit Iterator, needed for safe removal: ");
		Iterator<Map.Entry<String, Integer>> mapIt = map.entrySet().iterator();
		while (mapIt.hasNext()) {
			Map.Entry<String, Integer> e = mapIt.next();
			System.out.print(e.getKey() + " ");
			// mapIt.remove() would safely remove from the underlying map here
		}
		System.out.println();
	}

	// =================================================================
	// 17. Traversing a Queue and a Deque
	// =================================================================
	// Queue/Deque support the SAME for-each/Iterator/forEach traversal as
	// any Collection, but that just walks the current internal order —
	// it does NOT consume the queue. To process elements IN QUEUE ORDER
	// while removing them (the usual real-world use), you drain it with
	// poll() in a loop instead of iterating.
	// Deque additionally offers descendingIterator() to walk it back-to-front
	// without disturbing the original order at all.
	private static void demoTraverseQueueAndDeque() {
		System.out.println("\n===== Traversing a Queue / Deque =====");

		Queue<Integer> queue = new LinkedList<>(List.of(10, 20, 30));

		System.out.print("Queue via for-each (peek-only, does NOT remove): ");
		for (Integer n : queue) {
			System.out.print(n + " ");
		}
		System.out.println("| queue still has " + queue.size() + " elements: " + queue);

		System.out.print("Queue drained IN ORDER via poll() loop (this DOES remove): ");
		while (!queue.isEmpty()) {
			System.out.print(queue.poll() + " ");
		}
		System.out.println();

		Deque<Integer> deque = new ArrayDeque<>(List.of(1, 2, 3, 4));

		System.out.print("Deque forward (normal iterator): ");
		for (Integer n : deque) {
			System.out.print(n + " ");
		}
		System.out.println();

		System.out.print("Deque backward (descendingIterator, non-destructive): ");
		Iterator<Integer> descending = deque.descendingIterator();
		while (descending.hasNext()) {
			System.out.print(descending.next() + " ");
		}
		System.out.println();
	}

	// =================================================================
	// 18. Traversing using a while loop — every collection type, done
	// with plain while(...) instead of for / for-each / lambda
	// =================================================================
	// List: while loop driven by an index counter — only cheap for
	// ArrayList (index-based get() is O(1)); on LinkedList each get(i)
	// call is O(n), so a while-with-index loop on a LinkedList is
	// actually O(n^2) overall — use an Iterator's while loop instead
	// for LinkedList (shown further down).
	// Set / Map / Queue / Deque: no index exists at all, so the while
	// loop is always driven by an Iterator's hasNext()/next() instead —
	// this is in fact exactly what the enhanced for-each loop compiles
	// down to internally (see Stage 6 / 1.5 in our multithreading-style
	// walkthrough: Iterable.iterator() powers for-each).
	private static void demoTraverseUsingWhileLoop() {
		System.out.println("\n===== Traversing using a while loop =====");

		// ---- List: index-based while loop (fine for ArrayList) ----
		List<String> arrayList = new ArrayList<>(List.of("apple", "banana", "cherry"));
		System.out.print("List (ArrayList) while loop by index: ");
		int i = 0;
		while (i < arrayList.size()) {
			System.out.print(arrayList.get(i) + " ");
			i++;
		}
		System.out.println();

		// ---- List: Iterator-driven while loop (correct choice for LinkedList) ----
		List<String> linkedList = new LinkedList<>(List.of("x", "y", "z"));
		System.out.print("List (LinkedList) while loop via Iterator (avoids O(n^2) get()): ");
		Iterator<String> listIt = linkedList.iterator();
		while (listIt.hasNext()) {
			System.out.print(listIt.next() + " ");
		}
		System.out.println();

		// ---- Set: no index, so while loop must use an Iterator ----
		Set<String> set = new LinkedHashSet<>(List.of("banana", "apple", "cherry"));
		System.out.print("Set while loop via Iterator: ");
		Iterator<String> setIt = set.iterator();
		while (setIt.hasNext()) {
			System.out.print(setIt.next() + " ");
		}
		System.out.println();

		// ---- Map: while loop over entrySet()'s Iterator ----
		Map<String, Integer> map = new LinkedHashMap<>();
		map.put("apple", 3);
		map.put("banana", 5);
		map.put("cherry", 7);

		System.out.println("Map while loop via entrySet().iterator():");
		Iterator<Map.Entry<String, Integer>> mapIt = map.entrySet().iterator();
		while (mapIt.hasNext()) {
			Map.Entry<String, Integer> entry = mapIt.next();
			System.out.println("   " + entry.getKey() + " -> " + entry.getValue());
		}

		// ---- Queue: while loop that DRAINS it in FIFO order (the normal
		// real-world pattern — process-and-remove until empty) ----
		Queue<Integer> queue = new LinkedList<>(List.of(10, 20, 30));
		System.out.print("Queue while loop, draining with poll(): ");
		while (!queue.isEmpty()) {
			System.out.print(queue.poll() + " ");
		}
		System.out.println();

		// ---- Deque: while loop from both ends at once ----
		Deque<Integer> deque = new ArrayDeque<>(List.of(1, 2, 3, 4, 5));
		System.out.print("Deque while loop, removing from both ends alternately: ");
		while (!deque.isEmpty()) {
			System.out.print(deque.pollFirst() + " ");
			if (!deque.isEmpty()) {
				System.out.print(deque.pollLast() + " ");
			}
		}
		System.out.println();
	}
}