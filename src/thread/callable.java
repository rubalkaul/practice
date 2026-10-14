package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class callable {

	public static void main(String[] args) throws Exception {

		ExecutorService executor = Executors.newSingleThreadExecutor();

		Callable<Integer> task = () -> {

			System.out.println("Calculating...");
			Thread.sleep(2000);
			return 100 + 200;
		};

		var future = executor.submit(task);
		System.out.println("Doing other work...1");
		Integer result = future.get();

		System.out.println("Result: " + result);
		System.out.println("Doing other work...2");
		executor.shutdown();
	}
}
