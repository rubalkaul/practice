package thread;

import java.util.List;
import java.util.concurrent.*;

public class executorService {

	public static void main(String[] args) throws Exception {

		demoExecute();
		demoSubmitRunnable();
		demoSubmitCallableWithFuture();
		demoInvokeAll();
		demoInvokeAny();
		demoCompletableFuture();
		demoConcurrentHashMap();
		demoCopyOnWriteArrayList();
		demoBlockingQueue();
	}

	// ---------------------------------------------------------------
	// 1. execute(Runnable) — fire and forget, no result, no Future
	// ---------------------------------------------------------------
	private static void demoExecute() throws InterruptedException {
		System.out.println("\n===== execute() demo =====");

		ExecutorService executor = Executors.newFixedThreadPool(3);

		for (int i = 1; i <= 5; i++) {
			int taskNumber = i;
			executor.execute(() -> {
				System.out.println("Executing Task " + taskNumber + " by " + Thread.currentThread().getName());
				sleep(300);
				System.out.println("End Task " + taskNumber + " by " + Thread.currentThread().getName());
			});
		}

		shutdownAndWait(executor);
	}

	// ---------------------------------------------------------------
	// 2. submit(Runnable) — returns a Future<?>, useful only to know
	// when it's done / whether it threw, not for a return value
	// ---------------------------------------------------------------
	private static void demoSubmitRunnable() throws InterruptedException, ExecutionException {
		System.out.println("\n===== submit(Runnable) demo =====");

		ExecutorService executor = Executors.newFixedThreadPool(2);

		Future<?> future = executor.submit(() -> {
			System.out.println("Runnable task running on " + Thread.currentThread().getName());
			sleep(300);
		});

		future.get(); // blocks until finished; returns null for a Runnable
		System.out.println("Runnable task finished");

		shutdownAndWait(executor);
	}

	// ---------------------------------------------------------------
	// 3. submit(Callable) + Future<T> — task that RETURNS a value
	// ---------------------------------------------------------------
	private static void demoSubmitCallableWithFuture() throws InterruptedException, ExecutionException {
		System.out.println("\n===== submit(Callable) + Future demo =====");

		ExecutorService executor = Executors.newFixedThreadPool(2);

		Future<Integer> future = executor.submit(() -> {
			System.out.println("Callable task computing on " + Thread.currentThread().getName());
			sleep(1000);
			return 42;
		});

		System.out.println("Doing other work while task runs...");
		Integer result = future.get(); // BLOCKS until the task finishes
		System.out.println("Result: " + result);

		shutdownAndWait(executor);
	}

	// ---------------------------------------------------------------
	// 4. invokeAll — run several Callables, wait for ALL to finish
	// ---------------------------------------------------------------
	private static void demoInvokeAll() throws InterruptedException {
		System.out.println("\n===== invokeAll() demo =====");

		ExecutorService executor = Executors.newFixedThreadPool(3);

		List<Callable<Integer>> tasks = List.of(() -> {
			sleep(200);
			return 1;
		}, () -> {
			sleep(400);
			return 2;
		}, () -> {
			sleep(100);
			return 3;
		});

		List<Future<Integer>> results = executor.invokeAll(tasks);

		for (Future<Integer> f : results) {
			try {
				System.out.println("invokeAll result: " + f.get());
			} catch (ExecutionException e) {
				System.out.println("Task failed: " + e.getCause());
			}
		}

		shutdownAndWait(executor);
	}

	// ---------------------------------------------------------------
	// 5. invokeAny — run several Callables, return whichever finishes FIRST
	// ---------------------------------------------------------------
	private static void demoInvokeAny() throws InterruptedException, ExecutionException {
		System.out.println("\n===== invokeAny() demo =====");

		ExecutorService executor = Executors.newFixedThreadPool(3);

		List<Callable<String>> tasks = List.of(() -> {
			sleep(500);
			return "Slow result";
		}, () -> {
			sleep(100);
			return "Fast result";
		}, () -> {
			sleep(300);
			return "Medium result";
		});

		String fastest = executor.invokeAny(tasks); // cancels the rest once one wins
		System.out.println("invokeAny winner: " + fastest);

		shutdownAndWait(executor);
	}

	// ---------------------------------------------------------------
	// 6. CompletableFuture — non-blocking, chainable async pipeline
	// ---------------------------------------------------------------
	private static void demoCompletableFuture() throws InterruptedException, ExecutionException {
		System.out.println("\n===== CompletableFuture demo =====");

		CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> { // runs async, produces a value
			System.out.println("Fetching data on " + Thread.currentThread().getName());
			sleep(300);
			return 10;
		}).thenApply(data -> { // transform the value, still async chain
			System.out.println("Processing data on " + Thread.currentThread().getName());
			return data * 2;
		}).thenApply(processed -> processed + 5).exceptionally(ex -> { // runs only if something above threw
			System.out.println("Failed: " + ex.getMessage());
			return -1;
		});

		future.thenAccept(result -> System.out.println("Final result: " + result));

		// block here only because main() would otherwise exit before the async chain
		// finishes
		System.out.println("Blocking wait just for demo purposes: " + future.get());
	}

	// ---------------------------------------------------------------
	// 7. ConcurrentHashMap — thread-safe map, atomic compound ops
	// ---------------------------------------------------------------
	private static void demoConcurrentHashMap() throws InterruptedException {
		System.out.println("\n===== ConcurrentHashMap demo =====");

		ConcurrentHashMap<String, Integer> wordCount = new ConcurrentHashMap<>();
		String[] words = { "apple", "banana", "apple", "cherry", "banana", "apple" };

		ExecutorService executor = Executors.newFixedThreadPool(4);

		for (String word : words) {
			executor.submit(() -> {
				// atomic: read current count (or 0) and increment it, as ONE operation
				wordCount.merge(word, 1, Integer::sum);
			});
		}

		shutdownAndWait(executor);
		System.out.println("Word counts: " + wordCount);
	}

	// ---------------------------------------------------------------
	// 8. CopyOnWriteArrayList — safe iteration while others may write
	// ---------------------------------------------------------------
	private static void demoCopyOnWriteArrayList() throws InterruptedException {
		System.out.println("\n===== CopyOnWriteArrayList demo =====");

		CopyOnWriteArrayList<String> listeners = new CopyOnWriteArrayList<>();
		listeners.add("listenerA");
		listeners.add("listenerB");

		ExecutorService executor = Executors.newFixedThreadPool(2);

		// one thread iterates (simulating "firing an event to all listeners")
		executor.submit(() -> {
			for (String listener : listeners) {
				System.out.println("Notifying " + listener);
				sleep(50);
			}
		});

		// another thread modifies the list WHILE the first is iterating
		executor.submit(() -> {
			sleep(20);
			listeners.add("listenerC");
			System.out.println("Added listenerC concurrently — no ConcurrentModificationException");
		});

		shutdownAndWait(executor);
	}

	// ---------------------------------------------------------------
	// 9. BlockingQueue — producer/consumer with put()/take()
	// ---------------------------------------------------------------
	private static void demoBlockingQueue() throws InterruptedException {
		System.out.println("\n===== BlockingQueue demo =====");

		BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(5); // bounded, capacity 5

		ExecutorService executor = Executors.newFixedThreadPool(2);

		Runnable producer = () -> {
			try {
				for (int i = 1; i <= 5; i++) {
					queue.put(i); // blocks if queue is full
					System.out.println("Produced: " + i);
				}
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		};

		Runnable consumer = () -> {
			try {
				for (int i = 1; i <= 5; i++) {
					int val = queue.take(); // blocks if queue is empty
					System.out.println("Consumed: " + val);
				}
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		};

		executor.submit(producer);
		executor.submit(consumer);

		shutdownAndWait(executor);
	}

	// ---------------------------------------------------------------
	// Helpers
	// ---------------------------------------------------------------
	private static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	private static void shutdownAndWait(ExecutorService executor) throws InterruptedException {
		executor.shutdown();
		if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
			executor.shutdownNow();
		}
	}
}