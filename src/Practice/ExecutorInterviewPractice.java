package Practice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorInterviewPractice {

	public static void main(String[] args) throws Exception {

		// Uncomment one question at a time.

		question1();
		// question2();
		// question3();
		// question4();
		// question5();
		// question6();
		// question7();
		// question8();
		// question9();
		// question10();
		// question11();
		// question12();
		// question13();
		// question14();
		// question15();
		// question16();
		// question17();
		// question18();
		// question19();
		// question20();
		// question21();
	}

	// ============================================================
	// QUESTION 1
	// Create an ExecutorService with 3 threads and submit 10 tasks.
	// Each task should print its task number and thread name.
	// ============================================================
	static void question1() throws InterruptedException {
		ExecutorService executor = Executors.newFixedThreadPool(3);

		for (int i = 1; i <= 10; i++) {
			int taskNumber = i;

			executor.submit(() -> {
				System.out.println("Task " + taskNumber + " executed by " + Thread.currentThread().getName());
				sleep(500);
			});
		}

		executor.shutdown();
		executor.awaitTermination(10, TimeUnit.SECONDS);
	}

	// ============================================================
	// QUESTION 2
	// Create one Runnable task that prints "Hello" and one
	// Callable<Integer> that returns 100. Execute both.
	// ============================================================
	static void question2() throws Exception {
		ExecutorService executor = Executors.newFixedThreadPool(2);

		Runnable runnable = () -> System.out.println("Hello from Runnable");

		Callable<Integer> callable = () -> 100;

		executor.submit(runnable);

		Future<Integer> future = executor.submit(callable);

		System.out.println("Callable result = " + future.get());

		executor.shutdown();
	}

	// ============================================================
	// QUESTION 3
	// Create a Callable<Integer> that calculates:
	// 1 + 2 + 3 + ... + 100
	// Submit it and print the result using Future.
	// ============================================================
	static void question3() throws Exception {
		ExecutorService executor = Executors.newFixedThreadPool(2);

		Callable<Integer> task = () -> {
			int sum = 0;

			for (int i = 1; i <= 100; i++) {
				sum += i;
			}

			return sum;
		};

		Future<Integer> future = executor.submit(task);

		System.out.println("Result = " + future.get());

		executor.shutdown();
	}

	// ============================================================
	// QUESTION 4
	// Create 5 Callable<Integer> tasks.
	// Each task should return taskNumber * taskNumber.
	// Print all results.
	// ============================================================
	static void question4() throws Exception {
		ExecutorService executor = Executors.newFixedThreadPool(3);

		List<Future<Integer>> futures = new ArrayList<>();

		for (int i = 1; i <= 5; i++) {
			int taskNumber = i;

			futures.add(executor.submit(() -> taskNumber * taskNumber));
		}

		for (int i = 0; i < futures.size(); i++) {
			System.out.println("Task " + (i + 1) + " = " + futures.get(i).get());
		}

		executor.shutdown();
	}

	// ============================================================
	// QUESTION 5
	// Create 5 Callable<String> tasks:
	// Task-1, Task-2, Task-3, Task-4, Task-5.
	// Execute them using invokeAll() and print all results.
	// ============================================================
	static void question5() throws Exception {
		ExecutorService executor = Executors.newFixedThreadPool(3);

		List<Callable<String>> tasks = new ArrayList<>();

		for (int i = 1; i <= 5; i++) {
			int taskNumber = i;
			tasks.add(() -> "Task-" + taskNumber);
		}

		List<Future<String>> futures = executor.invokeAll(tasks);

		for (Future<String> future : futures) {
			System.out.println(future.get());
		}

		executor.shutdown();
	}

	// ============================================================
	// QUESTION 6
	// Create a custom ThreadPoolExecutor:
	// corePoolSize = 2
	// maximumPoolSize = 4
	// queue capacity = 2
	// Submit 10 tasks and observe:
	// core threads -> queue -> maximum threads -> rejection.
	// ============================================================
	static void question6() throws InterruptedException {
		ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2),
				new ThreadPoolExecutor.CallerRunsPolicy());

		for (int i = 1; i <= 10; i++) {
			int taskNumber = i;

			executor.submit(() -> {
				System.out.println("Task " + taskNumber + " -> " + Thread.currentThread().getName());
				sleep(1000);
			});
		}

		executor.shutdown();
		executor.awaitTermination(20, TimeUnit.SECONDS);
	}

	// ============================================================
	// QUESTION 7
	// Create a ThreadPoolExecutor with:
	// corePoolSize = 2
	// maximumPoolSize = 2
	// queue capacity = 2
	// Use AbortPolicy.
	// Submit 10 tasks and handle RejectedExecutionException.
	// ============================================================
	static void question7() throws InterruptedException {
		ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 2, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2),
				new ThreadPoolExecutor.AbortPolicy());

		for (int i = 1; i <= 10; i++) {
			int taskNumber = i;

			try {
				executor.submit(() -> {
					System.out.println("Executing Task " + taskNumber);
					sleep(2000);
				});
			} catch (RejectedExecutionException e) {
				System.out.println("Task " + taskNumber + " rejected");
			}
		}

		executor.shutdown();
		executor.awaitTermination(10, TimeUnit.SECONDS);
	}

	// ============================================================
	// QUESTION 8
	// Repeat Question 7 using CallerRunsPolicy.
	// Print the thread executing each task.
	// Identify tasks executed by the main thread.
	// ============================================================
	static void question8() throws InterruptedException {
		ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 2, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2),
				new ThreadPoolExecutor.CallerRunsPolicy());

		for (int i = 1; i <= 10; i++) {
			int taskNumber = i;

			executor.submit(() -> {
				System.out.println("Task " + taskNumber + " executed by " + Thread.currentThread().getName());
				sleep(1000);
			});
		}

		executor.shutdown();
		executor.awaitTermination(15, TimeUnit.SECONDS);
	}

	// ============================================================
	// QUESTION 9
	// Use DiscardPolicy with:
	// corePoolSize = 2
	// maximumPoolSize = 2
	// queue capacity = 2
	// Submit 10 tasks and observe which tasks execute.
	// ============================================================
	static void question9() throws InterruptedException {
		ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 2, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2),
				new ThreadPoolExecutor.DiscardPolicy());

		for (int i = 1; i <= 10; i++) {
			int taskNumber = i;

			executor.submit(() -> {
				System.out.println("Executing Task " + taskNumber);
				sleep(2000);
			});
		}

		executor.shutdown();
		executor.awaitTermination(15, TimeUnit.SECONDS);
	}

	// ============================================================
	// QUESTION 10
	// Use DiscardOldestPolicy with:
	// corePoolSize = 2
	// maximumPoolSize = 2
	// queue capacity = 2
	// Submit multiple tasks and observe which queued task is removed.
	// ============================================================
	static void question10() throws InterruptedException {
		ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 2, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2),
				new ThreadPoolExecutor.DiscardOldestPolicy());

		for (int i = 1; i <= 10; i++) {
			int taskNumber = i;

			executor.submit(() -> {
				System.out.println("Executing Task " + taskNumber);
				sleep(2000);
			});
		}

		executor.shutdown();
		executor.awaitTermination(15, TimeUnit.SECONDS);
	}

	// ============================================================
	// QUESTION 11
	// Create a task that sleeps for 10 seconds.
	// Call future.get(2, TimeUnit.SECONDS).
	// Handle TimeoutException.
	// ============================================================
	static void question11() throws Exception {
		ExecutorService executor = Executors.newFixedThreadPool(2);

		Future<String> future = executor.submit(() -> {
			sleep(10000);
			return "Completed";
		});

		try {
			String result = future.get(2, TimeUnit.SECONDS);
			System.out.println(result);
		} catch (TimeoutException e) {
			System.out.println("Task timed out");
			future.cancel(true);
		}

		executor.shutdown();
	}

	// ============================================================
	// QUESTION 12
	// Create a task that runs for 10 seconds.
	// After 2 seconds call future.cancel(true).
	// Observe interruption/cancellation.
	// ============================================================
	static void question12() throws Exception {
		ExecutorService executor = Executors.newFixedThreadPool(2);

		Future<?> future = executor.submit(() -> {
			try {
				for (int i = 1; i <= 10; i++) {
					System.out.println("Running " + i);
					Thread.sleep(1000);
				}
			} catch (InterruptedException e) {
				System.out.println("Task interrupted");
				Thread.currentThread().interrupt();
			}
		});

		Thread.sleep(2000);

		boolean cancelled = future.cancel(true);

		System.out.println("Cancelled = " + cancelled);

		executor.shutdown();
	}

	// ============================================================
	// QUESTION 13
	// Create a task and demonstrate:
	// future.isDone()
	// future.isCancelled()
	// before and after completion.
	// ============================================================
	static void question13() throws Exception {
		ExecutorService executor = Executors.newFixedThreadPool(2);

		Future<String> future = executor.submit(() -> {
			sleep(2000);
			return "Completed";
		});

		System.out.println("Done = " + future.isDone());
		System.out.println("Cancelled = " + future.isCancelled());

		System.out.println("Result = " + future.get());

		System.out.println("Done = " + future.isDone());
		System.out.println("Cancelled = " + future.isCancelled());

		executor.shutdown();
	}

	// ============================================================
	// QUESTION 14
	// Given List<Integer> 1..10, use ExecutorService to calculate
	// the square of every number concurrently.
	// ============================================================
	static void question14() throws Exception {
		ExecutorService executor = Executors.newFixedThreadPool(4);

		List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

		List<Future<Integer>> futures = new ArrayList<>();

		for (Integer number : numbers) {
			futures.add(executor.submit(() -> number * number));
		}

		for (Future<Integer> future : futures) {
			System.out.println(future.get());
		}

		executor.shutdown();
	}

	// ============================================================
	// QUESTION 15
	// Divide 1..100 into four tasks:
	// 1-25, 26-50, 51-75, 76-100.
	// Execute concurrently and calculate final sum.
	// Expected result: 5050.
	// ============================================================
	static void question15() throws Exception {
		ExecutorService executor = Executors.newFixedThreadPool(4);

		List<Callable<Integer>> tasks = List.of(() -> calculateSum(1, 25), () -> calculateSum(26, 50),
				() -> calculateSum(51, 75), () -> calculateSum(76, 100));

		List<Future<Integer>> futures = executor.invokeAll(tasks);

		int total = 0;

		for (Future<Integer> future : futures) {
			total += future.get();
		}

		System.out.println("Total = " + total);

		executor.shutdown();
	}

	static int calculateSum(int start, int end) {
		int sum = 0;

		for (int i = start; i <= end; i++) {
			sum += i;
		}

		return sum;
	}

	// ============================================================
	// QUESTION 16
	// Implement Producer-Consumer using:
	// ExecutorService
	// BlockingQueue<Integer>
	// Producer adds 1..100.
	// Consumer processes values.
	// ============================================================
	static void question16() throws InterruptedException {
		ExecutorService executor = Executors.newFixedThreadPool(2);

		BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

		Runnable producer = () -> {
			try {
				for (int i = 1; i <= 100; i++) {
					queue.put(i);
					System.out.println("Produced: " + i);
				}

				// Poison pill
				queue.put(-1);

			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		};

		Runnable consumer = () -> {
			try {
				while (true) {
					int value = queue.take();

					if (value == -1) {
						break;
					}

					System.out.println("Consumed: " + value);
				}
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		};

		executor.submit(producer);
		executor.submit(consumer);

		executor.shutdown();
		executor.awaitTermination(30, TimeUnit.SECONDS);
	}

	// ============================================================
	// QUESTION 17
	// Simulate three independent microservices:
	// getUser()
	// getOrders()
	// getPayments()
	// Each takes 2 seconds.
	// Execute all three in parallel using CompletableFuture.
	// ============================================================
	static void question17() {
		ExecutorService executor = Executors.newFixedThreadPool(3);

		CompletableFuture<String> userFuture = CompletableFuture.supplyAsync(() -> {
			sleep(2000);
			return "User Data";
		}, executor);

		CompletableFuture<String> ordersFuture = CompletableFuture.supplyAsync(() -> {
			sleep(2000);
			return "Orders Data";
		}, executor);

		CompletableFuture<String> paymentsFuture = CompletableFuture.supplyAsync(() -> {
			sleep(2000);
			return "Payments Data";
		}, executor);

		CompletableFuture.allOf(userFuture, ordersFuture, paymentsFuture).join();

		System.out.println(userFuture.join());
		System.out.println(ordersFuture.join());
		System.out.println(paymentsFuture.join());

		executor.shutdown();
	}

	// ============================================================
	// QUESTION 18
	// Implement dependent asynchronous calls:
	// getUser()
	// -> getOrders(userId)
	// -> getOrderDetails(orderId)
	// Use thenCompose().
	// ============================================================
	static void question18() {
		CompletableFuture<String> result = getUser().thenCompose(user -> getOrders(user))
				.thenCompose(order -> getOrderDetails(order));

		System.out.println(result.join());
	}

	static CompletableFuture<String> getUser() {
		return CompletableFuture.supplyAsync(() -> {
			sleep(1000);
			return "User-101";
		});
	}

	static CompletableFuture<String> getOrders(String user) {
		return CompletableFuture.supplyAsync(() -> {
			sleep(1000);
			return "Order-500";
		});
	}

	static CompletableFuture<String> getOrderDetails(String order) {
		return CompletableFuture.supplyAsync(() -> {
			sleep(1000);
			return "Order Details for " + order;
		});
	}

	// ============================================================
	// QUESTION 19
	// Implement two independent calls:
	// getUser()
	// getOrders()
	// Combine their results using thenCombine().
	// ============================================================
	static void question19() {
		CompletableFuture<String> userFuture = CompletableFuture.supplyAsync(() -> {
			sleep(2000);
			return "Rubal";
		});

		CompletableFuture<String> ordersFuture = CompletableFuture.supplyAsync(() -> {
			sleep(2000);
			return "10 Orders";
		});

		CompletableFuture<String> result = userFuture.thenCombine(ordersFuture,
				(user, orders) -> user + " has " + orders);

		System.out.println(result.join());
	}

	// ============================================================
	// QUESTION 20
	// Create 5 CompletableFuture<Integer> tasks:
	// 10, 20, 30, 40, 50.
	// Use CompletableFuture.allOf() and calculate total = 150.
	// ============================================================
	static void question20() {
		List<CompletableFuture<Integer>> futures = new ArrayList<>();

		for (int i = 1; i <= 5; i++) {
			int value = i * 10;

			futures.add(CompletableFuture.supplyAsync(() -> value));
		}

		CompletableFuture<Void> all = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

		all.join();

		int total = futures.stream().mapToInt(CompletableFuture::join).sum();

		System.out.println("Total = " + total);
	}

	// ============================================================
	// QUESTION 21 - PRODUCTION-STYLE CHALLENGE
	//
	// Create:
	// corePoolSize = 3
	// maximumPoolSize = 6
	// queue capacity = 10
	// keepAliveTime = 30 seconds
	//
	// Simulate:
	// PaymentService
	// InventoryService
	// CustomerService
	//
	// Each service takes 1-3 seconds and may throw an exception.
	// Execute them concurrently using CompletableFuture.
	// Handle failures and combine the final results.
	// ============================================================
	static void question21() {
		ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 6, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10),
				new ThreadPoolExecutor.CallerRunsPolicy());

		CompletableFuture<String> payment = CompletableFuture
				.supplyAsync(ExecutorInterviewPractice::paymentService, executor).exceptionally(ex -> "Payment Failed");

		CompletableFuture<String> inventory = CompletableFuture
				.supplyAsync(ExecutorInterviewPractice::inventoryService, executor)
				.exceptionally(ex -> "Inventory Failed");

		CompletableFuture<String> customer = CompletableFuture
				.supplyAsync(ExecutorInterviewPractice::customerService, executor)
				.exceptionally(ex -> "Customer Failed");

		CompletableFuture.allOf(payment, inventory, customer).join();

		System.out.println("Payment   = " + payment.join());

		System.out.println("Inventory = " + inventory.join());

		System.out.println("Customer  = " + customer.join());

		executor.shutdown();
	}

	static String paymentService() {
		sleep(1000);

		System.out.println("Payment executed by " + Thread.currentThread().getName());

		if (Math.random() < 0.3) {
			throw new RuntimeException("Payment service unavailable");
		}

		return "Payment Success";
	}

	static String inventoryService() {
		sleep(2000);

		System.out.println("Inventory executed by " + Thread.currentThread().getName());

		if (Math.random() < 0.3) {
			throw new RuntimeException("Inventory service unavailable");
		}

		return "Inventory Available";
	}

	static String customerService() {
		sleep(3000);

		System.out.println("Customer executed by " + Thread.currentThread().getName());

		if (Math.random() < 0.3) {
			throw new RuntimeException("Customer service unavailable");
		}

		return "Customer Valid";
	}

	// ============================================================
	// UTILITY METHOD
	// ============================================================
	static void sleep(long milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
